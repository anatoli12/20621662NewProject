package com.company;

import com.sun.beans.decoder.DocumentHandler;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws TransformerException {
        try {
            // CHETENE OT XML
            File inputFile=new File("test.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder= factory.newDocumentBuilder();
            Document document = documentBuilder.parse(inputFile);
            document.getDocumentElement().normalize();
            System.out.println("Root element :" + document.getDocumentElement().getNodeName());
            NodeList nodeList = document.getElementsByTagName("student");
            System.out.println("----------------------------");
            for(int i=0;i< nodeList.getLength();i++){
                Node node=nodeList.item(i);
                System.out.println("\nCurrent element: "+ node.getNodeName());
                if(node.getNodeType()==Node.ELEMENT_NODE){
                    Element element=(Element) node;
                    System.out.println("Student facnum: "+element.getAttribute("facnum"));
                    System.out.println("Name: "+ element.getElementsByTagName("name").item(0).getTextContent());
                }
            } // KRAI NA CHETENETO
            //SUZDAVANE NA XML FILE
            DocumentBuilderFactory documentBuilderFactory=DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder1=documentBuilderFactory.newDocumentBuilder();
            Document document1=documentBuilder1.newDocument();
            Element root=document1.createElement("student_group");
            document1.appendChild(root);
            Element student=document1.createElement("student");
            root.appendChild(student);
            Attr attr = document1.createAttribute("sex");
            attr.setValue("male");
            student.setAttributeNode(attr);
            Element name=document1.createElement("name");
            name.appendChild(document1.createTextNode("Niazi"));
            student.appendChild(name);
            //TRANSFORMIRANE NA DOKUMENTA V XML
            TransformerFactory transformerFactory= TransformerFactory.newInstance();
            Transformer transformer= transformerFactory.newTransformer();
            DOMSource domSource=new DOMSource(document1);
            StreamResult streamResult=new StreamResult(new File("create_test.xml"));
            transformer.transform(domSource,streamResult);
            // KRAI NA TRANSFORMACIQTA
            // MODIFICIRANE NA XML
            inputFile=new File("test.xml");
            DocumentBuilderFactory dbFactory= DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc=dBuilder.parse(inputFile);
            Node stud=doc.getElementsByTagName("student").item(0);
            NamedNodeMap attrib=stud.getAttributes();
            Node nodeAttrib=attrib.getNamedItem("facnum");
            nodeAttrib.setTextContent("22421144");
            NodeList list=stud.getChildNodes();
            for(int i=0;i<list.getLength();i++){
                Node node=list.item(i);
                if(node.getNodeType()==Node.ELEMENT_NODE){
                    Element stuElement=(Element) node;
                    if("name".equals(stuElement.getNodeName())){
                        stuElement.setTextContent("Kaloyan Kokov");
                    }
                }
            }
            transformerFactory=TransformerFactory.newInstance();
            transformer=transformerFactory.newTransformer();
            domSource= new DOMSource(doc);
            StreamResult result=new StreamResult(new File("test.xml"));
            transformer.transform(domSource, result);
            // RABOTI!!!

        } catch (ParserConfigurationException | IOException | SAXException | TransformerConfigurationException e) {
            e.printStackTrace();
        }
    }
}
