package model.data;

import library.fileProcess.FileProcess;
import model.course.Course;

import java.util.List;
import java.util.TreeMap;

public class Data {
    public static String pathCourseList = System.getProperty("user.dir")+"\\src\\model\\course\\courseList.dat";
    public static TreeMap<String, Course> courseList = (TreeMap<String, Course>) FileProcess.readObject(Data.pathCourseList);
    public static boolean SaveCourseList()
    {
        return FileProcess.writeObject(Data.pathCourseList, courseList);
    }
}
