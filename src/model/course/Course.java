package model.course;

import model.user.User;

import java.sql.Time;
import java.util.Date;
import java.util.List;

public class Course {
    private String id;
    private String name;
    private byte numberOfLessons;
    private String description;
    private List<User> studentList;

    public Course(String id, String name, byte numberOfLessons, String description, List<User> studentList) {
        this.id = id;
        this.name = name;
        this.numberOfLessons = numberOfLessons;
        this.description = description;
        this.studentList = studentList;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public byte getNumberOfLessons() {
        return numberOfLessons;
    }

    public String getDescription()
    {
        return description;
    }

    public List<User> getStudentList() {
        return studentList;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumberOfLessons(byte numberOfLessons) {
        this.numberOfLessons = numberOfLessons;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public void setStudentList(List<User> studentList) {
        this.studentList = studentList;
    }



}
