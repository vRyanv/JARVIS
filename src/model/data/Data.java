package model.data;

import library.fileProcess.FileProcess;
import model.course.Course;
import model.user.User;

import java.util.List;
import java.util.TreeMap;

public class Data {
    public static String pathCourseList = System.getProperty("user.dir")+"\\src\\model\\course\\courseList.dat";
    public static String pathUserList =  System.getProperty("user.dir")+"\\src\\model\\user\\userList.dat";
    public static TreeMap<String, Course> courseList = (TreeMap<String, Course>) FileProcess.readObject(Data.pathCourseList);
    public static TreeMap<String, User> userList = (TreeMap<String, User>) FileProcess.readObject(Data.pathUserList);
    public static boolean SaveCourseList()
    {
        return FileProcess.writeObject(Data.pathCourseList, courseList);
    }

    public static boolean IsAdminRole(String email)
    {
        return Data.userList.get(email).getRole().equals("admin");
    }

    public static TreeMap<String, Course> getCourseList()
    {
        return (TreeMap<String, Course> )FileProcess.readObject(Data.pathCourseList);
    }

}
