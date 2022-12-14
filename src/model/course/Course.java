package model.course;

import library.fileProcess.FileProcess;
import model.user.User;

import java.io.File;
import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

public class Course implements Serializable {
    private String id;
    private String name;
    private int numberOfLessons;
    private String description;

    private List<String> studentList;

    public Course(String id, String name, int numberOfLessons, String description, List<User> studentList) {
        this.id = id;
        this.name = name;
        this.numberOfLessons = numberOfLessons;
        this.description = description;
        this.studentList = new ArrayList<>();
    }


    public static Object readCourseList(String path)
    {
        return FileProcess.readObject(path);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getNumberOfLessons() {
        return numberOfLessons;
    }

    public String getDescription()
    {
        return description;
    }

    public List<String> getStudentList() {
        return studentList;
    }


    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumberOfLessons(int numberOfLessons) {
        this.numberOfLessons = numberOfLessons;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }
    public void setStudentList(List<String> studentList) {
        this.studentList = studentList;
    }

}
