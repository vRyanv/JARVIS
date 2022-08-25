package controller;

import model.course.Course;
import view.CourseBox.CourseBox;
import view.CourseManager.CourseManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

public class ClassRoomController {
    private CourseManager courseManager;
    public CardLayout cardLayout;
    private String host = "localhost";
    private int port = 2108;
    private Socket socket;
    private Thread receiver;
    private DataInputStream dis;
    private DataOutputStream dos;
    private TreeMap<String, Course> room;
    private String currentRoom;
    private List<CourseBox> courseBoxList;
    private DefaultListModel currentModel;
    private String email;
    private String username;
    private String path = System.getProperty("user.dir")+"\\src\\model\\course\\courseList.dat";

    public ClassRoomController(CourseManager courseManager, String email)
    {
        this.email = email;
        this.username = email.substring(0, email.indexOf("@"));
        this.courseManager = courseManager;
        this.courseBoxList = new ArrayList<>();
        this.cardLayout = (CardLayout) courseManager.cardClassRoom.getLayout();
        this.LoadRoom();

        this.courseManager.btnLeaveRoom.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                LeaveRoom();
            }
        });
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

        this.courseManager.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int choice = JOptionPane.showConfirmDialog(courseManager, "Logout ?", "Confirm",JOptionPane.OK_CANCEL_OPTION);
                if(choice == JOptionPane.OK_OPTION)
                {
                    try {
                        dos.writeUTF("logout,"+username);
                    }catch (Exception ex){
                        JOptionPane.showMessageDialog(courseManager, "Something wrong!", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                }
            }
        });
    }

    private void LeaveRoom()
    {
        String roomId = courseManager.lbRoomId.getText().substring(courseManager.lbRoomId.getText().lastIndexOf(" ")+1);
        try {
            dos.writeUTF("leaveRoom,"+roomId);
            System.out.println(roomId);
        }catch (Exception ex)
        {
            System.out.println(ex);
        }
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
                courseBox.btnRegisterCourse.setVisible(false);
                courseBox.btnCourseId.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        enrollRoom(e.getActionCommand(), email);
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
    private void enrollRoom(String roomId, String email)
    {
        if(Connect())
        {
            try{
                this.currentModel =  new DefaultListModel();
                this.courseManager.listMess.setModel(this.currentModel);
                this.currentRoom = roomId;
                this.courseManager.lbRoomId.setText("Room ID: "+roomId);

                receiver = new Thread(new Receiver(dis, courseManager, this.currentModel, cardLayout));
                receiver.start();
                dos.writeUTF("intoRoom,"+roomId+","+email);

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

    private void AutoScroll()
    {
        courseManager.scrollPane5.getVerticalScrollBar().setValue(courseManager.scrollPane5.getVerticalScrollBar().getMaximum());
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
            dos.writeUTF("Mess,"+currentRoom+","+mess);
            this.currentModel.addElement(this.username +": "+mess);
            AutoScroll();
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
    private DefaultListModel messListModel;

    private CardLayout cardLayout;

    public Receiver(DataInputStream dis, CourseManager courseManager, DefaultListModel messListModel, CardLayout cardLayout)
    {
        this.dis = dis;
        this.courseManager = courseManager;
        this.messListModel = messListModel;
        this.cardLayout = cardLayout;
    }
    @Override
    public void run() {
        try {
            while (true)
            {
                String getResponseFromServer = dis.readUTF();
                String[] ResponseElement = getResponseFromServer.split(",");
                if(ResponseElement[0].equals("message"))
                {
                    NewMess(ResponseElement[1]);
                }
                else if (ResponseElement[0].equals("leaveRoom"))
                {
                    System.out.println("out");
                    cardLayout.show(courseManager.cardClassRoom, "cardChooseRoom");
                    break;
                }
            }
        }catch (Exception ex){
            JOptionPane.showMessageDialog(courseManager.cardClassRoom, "Something wrong", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void NewMess(String mess)
    {
        System.out.println(mess);
        this.messListModel.addElement(mess);
        AutoScroll();
    }

    private void AutoScroll()
    {
        courseManager.scrollPane5.getVerticalScrollBar().setValue(courseManager.scrollPane5.getVerticalScrollBar().getMaximum());
    }
}


