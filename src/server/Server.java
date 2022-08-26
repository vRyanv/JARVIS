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
    static TreeMap<String, List<handler>> clients = new TreeMap<>(); //TreeMap<roomId, client>
    private CourseManager courseManager;
    public Server(CourseManager courseManager)
    {
        try {
            this.courseManager = courseManager;
            this.serverSocket = new ServerSocket(2108);
            System.out.println("Server: running!");
            while (serverIsOn)
            {
                socket = serverSocket.accept();
                DataInputStream dis =new DataInputStream(socket.getInputStream());
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                String request = dis.readUTF();
                String[] requestElement = request.split(",");
                if(requestElement[0].equals("killServer"))
                {
                    for (String roomId: Server.clients.keySet())
                    {
                        for (handler handlers: Server.clients.get(roomId))
                        {
                            handlers.dos.writeUTF("serverDead,");
                        }
                    }
                    break;
                }
                else
                {
                    String roomId = requestElement[1];
                    String email = requestElement[2];
                    if(requestElement[0].equals("intoRoom"))
                    {
                        handler handler = new handler(socket, roomId,email);
                        if(clients.containsKey(roomId))
                        {
                            clients.get(roomId).add(handler);
                        }
                        else
                        {
                            clients.put(roomId, new ArrayList<>());
                            clients.get(roomId).add(handler);
                        }
                        Thread threadHandler = new Thread(handler);
                        threadHandler.start();
                    }
                }
            }
            this.serverSocket.close();
            System.out.println("Server: stop!");
        }catch (Exception exception){
            JOptionPane.showMessageDialog(courseManager, "server has stopped", "Server: Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}

class handler implements Runnable
{
    private Socket socket;
    private DataInputStream dis;
    public DataOutputStream dos;
    private String email;
    private String username;
    private String roomId;
    public handler(Socket socket, String roomId, String email)
    {
        HandlerConfig(socket, roomId, email);
    }

    private void HandlerConfig(Socket socket, String roomId,String email)
    {
        try {
            this.email = email;
            this.roomId = roomId;
            this.username = email.substring(0, email.indexOf("@"));
            this.socket = socket;
            this.dis = new DataInputStream(socket.getInputStream());
            this.dos = new DataOutputStream(socket.getOutputStream());
        }catch (IOException ioException) {
        }
    }

    @Override
    public void run() {

        try {
            for (handler handlers : Server.clients.get(this.roomId)) {
                if (!handlers.username.equals(username)) {
                    handlers.dos.writeUTF("intoRoom,"+email);
                }
            }
            while (true)
            {
                String requests = dis.readUTF();
                String[] requestElement = requests.split(",");

                if (requestElement[0].equals("Mess"))
                {
                    String mess = requestElement[1];
                    String messResponse = "message," + username + ": " + mess;
                    System.out.println(username + " has send: " + mess);
                    for (handler handlers : Server.clients.get(this.roomId))
                    {
                        if (!handlers.username.equals(username))
                        {
                            handlers.dos.writeUTF(messResponse);
                        }
                    }
                } else if (requestElement[0].equals("leaveRoom")) {
                    for (handler handlers : Server.clients.get(this.roomId))
                    {
                        if (handlers.email.equals(email))
                        {
                            handlers.dos.writeUTF("leaveRoom, ");
                            this.socket.close();
                            Server.clients.get(this.roomId).remove(handlers);
                            System.out.println(username + " has leave room: " + this.roomId);
                        }
                        else
                        {
                            handlers.dos.writeUTF("userLeaveRoom,"+email);
                        }
                    }
                    break;
                }
            }
        }catch (Exception ex){

        }

    }
}
