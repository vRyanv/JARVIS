package controller;

import model.course.Course;
import view.CourseBox.CourseBox;
import view.CourseManager.CourseManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

public class ClassRoomController {
    private CourseManager courseManager;
    private CardLayout cardLayout;
    private String host = "localhost";
    private int port = 2108;
    private Socket socket;
    private Thread receiver;
    private DataInputStream dis;
    private DataOutputStream dos;
    private TreeMap<String, Course> room;
    private List<CourseBox> courseBoxList;
    private String email;
    private String path = System.getProperty("user.dir")+"\\src\\model\\course\\courseList.dat";
    public ClassRoomController(CourseManager courseManager, String email)
    {
        this.courseManager = courseManager;
        this.courseBoxList = new ArrayList<>();
        this.LoadRoom();
        this.courseManager.btnRefreshRoom.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                LoadRoom();
            }
        });
        this.courseManager.btnSendMess.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                SendMess(courseManager.txtMess.getText());
            }
        });
        this.cardLayout = (CardLayout) courseManager.cardClassRoom.getLayout();

    }

    private void LoadRoom()
    {
        this.room = (TreeMap<String, Course>) Course.readCourseList(this.path);
        if(this.room == null)
        {
            JOptionPane.showMessageDialog(courseManager.cardClassRoom, "Can't load room", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            courseManager.containerRoomPanel.removeAll();
            for (Course course: this.room.values())
            {
                CourseBox courseBox = new CourseBox(course.getName(), course.getId());
                courseBox.btnCourseId.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        enrollRoom(e.getActionCommand());
                    }
                });
                this.courseBoxList.add(courseBox);
                courseManager.containerRoomPanel.add(courseBox, FlowLayout.LEFT);
            }
            courseManager.containerRoomPanel.revalidate();
            courseManager.containerRoomPanel.repaint();
        }
    }



    //====== chat =====
    private void enrollRoom(String roomId)
    {
        if(Connect())
        {
            try{
                receiver = new Thread(new Receiver(dis, courseManager));
                receiver.start();
                dos.writeUTF("intoRoom,"+roomId);
                cardLayout.show(courseManager.cardClassRoom, "cardRoom");
            }catch (Exception ex){
                JOptionPane.showMessageDialog(courseManager.cardClassRoom, "Can't into  room", "Error", JOptionPane.ERROR_MESSAGE);
            }

        }
        else
        {
            JOptionPane.showMessageDialog(courseManager.cardClassRoom, "Can't connect to server", "Error", JOptionPane.ERROR_MESSAGE);
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

    private boolean SendMess(String mess)
    {
        try {
            dos.writeUTF("Mess,"+mess);
            return true;
        }catch (Exception ex){
            return false;
        }
    }
}


class Receiver implements Runnable
{
    private DataInputStream dis;
    private CourseManager courseManager;

    public Receiver(DataInputStream dis, CourseManager courseManager)
    {
        this.dis = dis;
        this.courseManager = courseManager;
    }
    @Override
    public void run() {
        try {
            while (true)
            {
                String getResponseFromServer = dis.readUTF();
                System.out.println(getResponseFromServer);
//                String[] ResponseElement = getResponseFromServer.split(",");
//                if(ResponseElement[0].equals("message"))
//                {
//                    NewMess();
//                }
            }
        }catch (Exception ex){
            JOptionPane.showMessageDialog(courseManager.cardClassRoom, "Something wrong", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void NewMess()
    {

    }
}


