package controller;

import view.CourseManager.CourseManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Arrays;

public class ClassRoomController {
    private CourseManager courseManager;
    private CardLayout cardLayout;
    private String host = "localhost";
    private int port = 2108;
    private Socket socket;
    private Thread receiver;
    private DataInputStream dis;
    private DataOutputStream dos;
    private String email;
    public ClassRoomController(CourseManager courseManager, String email)
    {
        this.courseManager = courseManager;
        this.cardLayout = (CardLayout) courseManager.cardClassRoom.getLayout();
        this.courseManager.btnRoom1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if(Connect())
                {
                    try{
                                receiver = new Thread(new Receiver(dis, courseManager));
                                receiver.start();
                                dos.writeUTF("intoRoom");
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
        });
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

    private boolean enrollRoom()
    {

        return false;
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
