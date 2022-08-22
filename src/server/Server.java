package server;

import view.CourseManager.CourseManager;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
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
//    static TreeMap<String, List<handler>> clients = new TreeMap<>();
    static TreeMap<String, List<handler>> clients = new TreeMap<>();
    private CourseManager courseManager;
    public Server(CourseManager courseManager)
    {
        try {
            this.courseManager = courseManager;
            this.serverSocket = new ServerSocket(2108);
            System.out.println("running");
            while (serverIsOn)
            {

                socket = serverSocket.accept();
                System.out.println("new User");

                DataInputStream dis =new DataInputStream(socket.getInputStream());
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

                String request = dis.readUTF();
                String[] requestElement = request.split(",");
                String username = requestElement[2];
                if(requestElement[0].equals("intoRoom"))
                {
                    handler handler = new handler(socket, username);
                    if(clients.containsKey(requestElement[1]))
                    {
                        clients.get(requestElement[1]).add(handler);
                    }
                    else
                    {
                        clients.put(requestElement[1], new ArrayList<>());
                        clients.get(requestElement[1]).add(handler);
                    }
                    Thread threadHandler = new Thread(handler);
                    threadHandler.start();
                }

            }
            this.serverSocket.close();
        }catch (Exception exception){
            JOptionPane.showMessageDialog(courseManager, "server has stopped", "Server: Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}

class handler implements Runnable
{
    private Socket socket;
    private DataInputStream dis;
    private DataOutputStream dos;
    private String username;
    public handler(Socket socket, String username)
    {
        HandlerConfig(socket, username);
    }

    private void HandlerConfig(Socket socket, String username)
    {
        try {
            this.username = username;
            this.socket = socket;
            this.dis = new DataInputStream(socket.getInputStream());
            this.dos = new DataOutputStream(socket.getOutputStream());
        }catch (IOException ioException) {
        }
    }

    @Override
    public void run() {
        while (true)
        {
            try{
                String requests = dis.readUTF();
                String[] requestElement =  requests.split(",");
                if(requestElement[0].equals("Mess"))
                {
                    String mess = "message,"+username+": "+requestElement[2];
                    for (handler handlers: Server.clients.get(requestElement[1]))
                    {
                        if(handlers.username.equals(username))
                        {
                            handlers.dos.writeUTF(mess);
                        }
                    }
                }
//                else if(requestElement[0].equals("logout"))
//                {
//                    System.out.println(requestElement[1]);
//                    for (handler handlers: Server.clients.get(requestElement[1]))
//                    {
//                        if(handlers.username.equals(username))
//                        {
//                            handlers.dos.writeUTF("logout, ");
//                        }
//                    }
//                    this.socket.close();
//                    break;
//                }
            }catch (IOException ioException){
            }

        }
    }
}
