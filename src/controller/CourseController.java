package controller;

import library.colorCustom.ColorCustom;
import library.fileProcess.FileProcess;
import model.course.Course;
import model.data.Data;
import view.CourseBox.CourseBox;
import view.Jarvis.Jarvis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.TreeMap;

public class CourseController {

    public boolean LoadData()
    {
        Data.courseList = Data.getCourseList();
        return Data.courseList != null;
    }

    public boolean RegisterCourse(String courseId, String email)
    {
        if(Data.courseList.containsKey(courseId))
        {
            Data.courseList.get(courseId).getStudentList().add(email);
            Data.SaveCourseList();
            return true;
        }
        else
        {
            return false;
        }
    }

}
