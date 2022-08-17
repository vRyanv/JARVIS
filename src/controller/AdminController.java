package controller;

import model.course.Course;
import model.fileProcess.FileProcess;
import view.CourseManager.CourseManager;

import java.util.TreeMap;

public class AdminController {
    private TreeMap<String, Course> courseTreeMap;
    private String pathCourse = "src/model/course/course.dat";
    public CourseManager courseManager;

    public AdminController(CourseManager courseManager)
    {
        this.courseTreeMap = (TreeMap<String, Course>) FileProcess.readObject(this.pathCourse);
        System.out.println("admin");
    }

}

