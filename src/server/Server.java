package server;

import view.CourseManager.CourseManager;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.logging.Handler;

public class Server
{
    public static boolean serverIsOn = false;
    private ServerSocket serverSocket;
    private Socket socket;
    static TreeMap<String, Handler> clients = new TreeMap<>();
    private CourseManager courseManager;
    public Server(CourseManager courseManager)
    {
        try {
            this.courseManager = courseManager;
            this.serverSocket = new ServerSocket(2108);

            while (serverIsOn)
            {
                System.out.println("running");
                System.out.println(serverIsOn);
                Thread.sleep(1000);
//                socket = serverSocket.accept();
//                System.out.println("new User");
//
//                DataInputStream dis =new DataInputStream(socket.getInputStream());
//                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
//
//                String request = dis.readUTF();
//                String[] requestElement = request.split(",");
//                if(requestElement[0].equals("intoRoom"))
//                {
//                    dos.writeUTF(requestElement[0]);
//                    dos.writeUTF(requestElement[1]);
//                }
            }
            this.serverSocket.close();
            System.out.println("server dead");

        }catch (Exception exception){
            JOptionPane.showMessageDialog(courseManager, "server has stopped", "Server: Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}

class handler
{

}
