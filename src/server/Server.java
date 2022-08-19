package server;

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
    private boolean serverIsOn = true;
    private ServerSocket serverSocket;
    private Socket socket;
    static TreeMap<String, Handler> clients = new TreeMap<>();

    public Server()
    {
        try {
            this.serverSocket = new ServerSocket(2108);

            while (serverIsOn)
            {
                System.out.println("running");
                socket = serverSocket.accept();
                System.out.println("new User");

                DataInputStream dis =new DataInputStream(socket.getInputStream());
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

                String request = dis.readUTF();
                String[] requestElement = request.split(",");
                if(requestElement[0].equals("intoRoom"))
                {
                    dos.writeUTF(requestElement[0]);
                }
            }

        }catch (Exception exception){

        }

    }
}

class handler
{

}
