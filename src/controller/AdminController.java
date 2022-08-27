package controller;

import library.colorCustom.ColorCustom;
import model.course.Course;
import library.fileProcess.FileProcess;
import model.data.Data;
import server.Server;
import view.CourseBox.CourseBox;
import view.Jarvis.Jarvis;
import view.EditCourseDialog.EditCourseDialog;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.net.Socket;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdminController {
    private Thread threadServer;
    private Server server;
    private String host = "localhost";
    private int port = 2108;
    private Socket socket;
    private DataInputStream dis;
    private DataOutputStream dos;

    public boolean ConnectKillServer()
    {
        try{
            this.socket = new Socket(this.host, this.port);
            this.dis = new DataInputStream(this.socket.getInputStream());
            this.dos = new DataOutputStream(this.socket.getOutputStream());
            return true;
        }catch (Exception ex){
            return false;
        }
    }

    public boolean KillServer()
    {
        try {
            dos.writeUTF("killServer,");
            return true;
        }catch (Exception ex){
            return false;
        }
    }

    public boolean NewServer()
    {
        if(Server.serverIsOn == false)
        {
                Server.serverIsOn = true;
            try {
                this.threadServer = new Thread() {
                    @Override
                    public void run() {
                        try {
                            server = new Server();
                        } catch (Exception ex) {
                        }
                    }
                };
                this.threadServer.start();
                return true;
            } catch (Exception ex) {
                return false;
            }
        }
        return false;
    }

    public boolean SaveAs(String path)
    {
        if(path.lastIndexOf(".") == -1 || !path.substring(path.lastIndexOf(".")).equals(".dat"))
        {
            path =  path + ".dat";
        }
        return FileProcess.writeObject(path, Data.courseList);
    }

    public String LoadDataFromDisk(File file)
    {
        if(file != null)
        {
            String extension = file.getName().substring(file.getName().lastIndexOf("."));

            if(extension.equals(".dat"))
            {
                Data.courseList = (TreeMap<String, Course>) FileProcess.readObject(file);
                FileProcess.writeObject(Data.pathCourseList, Data.courseList);
                return "success";
            }
            else
            {
                return extension;
            }
        }
        else
        {
            return "null";
        }
    }

    public String AddNewCourse(String id, String name, int numOfLesion, String description)
    {
        if(id.equals(""))
        {
            return "idBlank";
        }
        else if(Data.courseList.containsKey(id))
        {
            return "idExisted";
        }
        else if(name.equals(""))
        {
            return "nameBlank";
        }
        else
        {
            if(Regex("[0-9]+",id))
            {
                if(saveCourse(id, name, numOfLesion, description, "newCourse"))
                {
                    return "success";
                }
            }
            else
            {
                return "idMustPositive";
            }
        }
        return "";
    }

    public Course getCourseDetail(String courseId)
    {
        if(Data.courseList.containsKey(courseId))
        {
            return Data.courseList.get(courseId);
        }
        return null;
    }

    public boolean DeleteCourse(String courseId)
    {
        if(Data.courseList.containsKey(courseId))
        {
            Data.courseList.remove(courseId);
            FileProcess.writeObject(Data.pathCourseList, Data.courseList);
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean saveCourse(String id, String name, int numOfLesson, String description, String type)
    {
        if(type.equals("newCourse"))
        {
            Course course = new Course(id, name, numOfLesson, description, new ArrayList<>());
            Data.courseList.put(id, course);
        }
        else
        {
            if(Data.courseList.containsKey(id))
            {
                Data.courseList.get(id).setName(name);
                Data.courseList.get(id).setNumberOfLessons(numOfLesson);
                Data.courseList.get(id).setDescription(description);
            }
            else
            {
                return false;
            }
        }
        return Data.SaveCourseList();
    }

    private boolean Regex(String regex, String str)
    {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
}

