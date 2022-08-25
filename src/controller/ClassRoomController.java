package controller;

import library.fileProcess.FileProcess;
import model.course.Course;
import model.user.User;
import server.Server;
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
    private List<CourseBox> courseBoxList;
    private DefaultListModel messListModel;
    private DefaultListModel userListModel;
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
                    if(IsAdminRole())
                    {
                        if(Connect())
                        {
                            try {
                                dos.writeUTF("killServer,");
                                System.exit(0);
                            }catch (Exception ex){
                                JOptionPane.showMessageDialog(courseManager.cardMainPanelAdmin, "Server Error!", "Admin: Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(courseManager, "Something wrong!", "Error", JOptionPane.ERROR_MESSAGE);
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
                            JOptionPane.showMessageDialog(courseManager, "Something wrong! can't exit T_T", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        });
    }

    private boolean IsAdminRole()
    {
        String pathUserList = System.getProperty("user.dir") + "\\src\\model\\user\\userList.dat";
        TreeMap<String, User> userList =  (TreeMap<String, User>) FileProcess.readObject(pathUserList);

        return userList.get(this.email).getRole().equals("admin");
    }

    private void LeaveRoom()
    {
        String roomId = courseManager.lbRoomId.getText().substring(courseManager.lbRoomId.getText().lastIndexOf(" ")+1);
        try {
            dos.writeUTF("leaveRoom,"+roomId);
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
                this.messListModel =  new DefaultListModel();
                this.courseManager.listMess.setModel(this.messListModel);

                this.userListModel = new DefaultListModel<>();
                this.courseManager.listUserInRoom.setModel(this.userListModel);

                this.courseManager.lbRoomId.setText("Room ID: "+roomId);

                receiver = new Thread(new Receiver(socket, dis, courseManager, this.messListModel, this.userListModel, cardLayout, email));
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

    private void SendMess(String mess)
    {
        try {
            dos.writeUTF("Mess,"+mess);
            this.messListModel.addElement(this.username +": "+mess);
            AutoScroll();
        }catch (Exception ex){
            JOptionPane.showMessageDialog(courseManager, "Can't send message", "Room: error", JOptionPane.ERROR_MESSAGE);
        }
    }
}


class Receiver implements Runnable
{
    private Socket socket;
    private DataInputStream dis;
    private CourseManager courseManager;
    private DefaultListModel messListModel;
    private DefaultListModel userListModel;

    private CardLayout cardLayout;
    private String email;

    public Receiver(Socket socket, DataInputStream dis, CourseManager courseManager,
                    DefaultListModel messListModel, DefaultListModel userListModel,
                    CardLayout cardLayout, String email)
    {
        this.socket = socket;
        this.dis = dis;
        this.courseManager = courseManager;
        this.messListModel = messListModel;
        this.userListModel = userListModel;
        this.cardLayout = cardLayout;
        this.email = email;
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
                    String mess = ResponseElement[1];
                    System.out.println(mess);
                    NewMess(mess);
                }
                else if(ResponseElement[0].equals("intoRoom"))
                {
                    System.out.println(ResponseElement[1]);
                    this.userListModel.addElement(ResponseElement[1]);
                }
                else if (ResponseElement[0].equals("leaveRoom"))
                {
                    LeaveRoom(this.email);
                    cardLayout.show(courseManager.cardClassRoom, "cardChooseRoom");
                    break;
                }
                else if (ResponseElement[0].equals("userLeaveRoom"))
                {
                    String email = ResponseElement[1];
                    LeaveRoom(email);
                }
                else if (ResponseElement[0].equals("serverDead"))
                {
                    JOptionPane.showMessageDialog(courseManager, "server down", "Warning", JOptionPane.WARNING_MESSAGE);
                    cardLayout.show(courseManager.cardClassRoom, "cardChooseRoom");
                    socket.close();
                    System.out.println(socket.isConnected());
                    break;
                }
            }
        }catch (Exception ex){
            this.socket = null;
            JOptionPane.showMessageDialog(courseManager.cardClassRoom, "Something wrong", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void LeaveRoom(String email)
    {
        for (int i = 0; i < userListModel.size(); i++)
        {
            System.out.println(userListModel.getElementAt(i));
            if(userListModel.getElementAt(i).equals(email))
            {
                userListModel.removeElementAt(i);
            }
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


