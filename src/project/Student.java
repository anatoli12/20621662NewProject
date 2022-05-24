package project;

import java.security.acl.Group;
import java.util.HashMap;

public class Student {
    private String name;
    private String facnum;
    private Year year;
    private Program program;
    private int groupID;
    private Status status;
    private double average_grade;
    private HashMap<Discipline, Double> disciplineDoubleHashMap;
}
