package controller;

import library.fileProcess.FileProcess;
import model.course.Course;
import model.data.Data;
import model.user.User;
import server.Server;
import view.CourseBox.CourseBox;
import view.Jarvis.Jarvis;

import javax.sound.midi.Receiver;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class ClassRoomController {
    private String host = "localhost";
    private int port = 2108;
    private Socket socket;
    private DataInputStream dis;
    private DataOutputStream dos;

    public boolean ExitRoom(String email)
    {
        if(Data.IsAdminRole(email) && Server.serverIsOn)
        {
            if(Connect())
            {
                try {
                    dos.writeUTF("killServer,");
                    System.exit(0);
                }catch (Exception ex){
                   return false;
                }
            }
            else
            {
                return false;
            }
        }
        else
        {
            try {
                if(socket != null && !socket.isClosed())
                {
                    dos.writeUTF("logout,");
                }
                System.exit(0);
            }catch (Exception ex){
                return false;
            }
        }
        return false;
    }
    public boolean LeaveRoom(String roomId)
    {
        try {
            dos.writeUTF("leaveRoom,"+roomId);
            return true;
        }catch (Exception ex)
        {
            return false;
        }
    }

    public List<Course> GetRoom(String email)
    {
        TreeMap<String, Course> courseList = Data.getCourseList();
        List<Course> roomList = new ArrayList<>();
        for (Course course: courseList.values())
        {
            if(course.getStudentList().contains(email))
            {
                roomList.add(course);
            }
        }
        return roomList;
    }

    //====== chat =====
    public Socket EnrollRoom(String roomId, String email)
    {
        if(Connect())
        {
            try{
                dos.writeUTF("intoRoom,"+roomId+","+email);
                return socket;
            }catch (Exception ex){
              return null;
            }
        }
        else
        {
            return null;
        }
    }


    private boolean Connect()
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

    public boolean SendMess(String mess)
    {
        try {
            dos.writeUTF("Mess,"+mess);
            return true;
        }catch (Exception ex){
            return false;
        }
    }
}



