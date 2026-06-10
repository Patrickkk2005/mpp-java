package eu.ase.claudeex;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TCPServer {
    private ServerSocket serverSocket;
    private int port;
    private List<Printable> list;

    public TCPServer(int port) {
        this.port = port;
        try {
            this.serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            this.list = Utils.readLaptops("laptops.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String handleCommand(String command) {
        if(command.equals("EXIT")){
            return "EXIT";
        }
        else if(command.equals("GETINFO")){
            return list.stream().map(p->p.toString()).collect(Collectors.joining(" | "));
        }
        return "ERROR";
    }

    public void start() throws IOException {
        while(true) {
            Socket client = serverSocket.accept();
            Thread t = new Thread(() -> {
                try {
                    BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                    PrintWriter out = new PrintWriter(new OutputStreamWriter(client.getOutputStream()),true);

                    String line = in.readLine();
                    if(line.equals("GETLIST")){
                        ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(client.getOutputStream()));
                        oos.writeObject(list);
                        oos.flush();
                    }
                    else out.println(handleCommand(line));

                    client.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            });
            t.start();
        }
    }
}
