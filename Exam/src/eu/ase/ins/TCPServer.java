package eu.ase.ins;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class TCPServer {
    private ServerSocket serverSocket;
    private List<Playable> instruments;
    private int port;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public TCPServer(int port) throws Exception {
        serverSocket = new ServerSocket(port);
        this.instruments = Utils.readInstruments("instruments.txt");
    }

    public void start() throws Exception {
        while (true) {
            Socket client  = serverSocket.accept();
            Thread t = new Thread(() -> {
                try {
                    BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                    PrintWriter out = new PrintWriter(client.getOutputStream(), true);
                    String command = in.readLine();
                    if(command.equals("EXIT")) {
                        client.close();
                        return;
                    }
                    else if(command.equals("GETFILE")) {
                        ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(client.getOutputStream()));
                        oos.writeObject(instruments);
                        oos.close();
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            t.start();
        }
    }
}
