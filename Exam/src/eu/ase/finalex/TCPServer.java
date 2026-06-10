package eu.ase.finalex;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class TCPServer {
    private int port;
    private ServerSocket serverSocket;
    private List<Book> books;

    public TCPServer(int port) throws IOException {
        this.port = port;
        this.serverSocket = new ServerSocket(port);
        this.books = Utils.readBooks("books.txt");
    }

    public void start() throws IOException {
        while(true){
            Socket client = serverSocket.accept();
            Thread t =  new Thread(() -> {
                BufferedReader in = null;
                try {
                    in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                PrintWriter out = null;
                try {
                    out = new PrintWriter(client.getOutputStream(), true);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                String command = null;
                try {
                    command = in.readLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                try {
                    if(command.equals("GETLIST")){
                        ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(client.getOutputStream()));
                        oos.writeObject(books);
                        oos.flush();
                    }
                    else if(command.equals("EXIT")){
                        client.close();
                        return;
                    }
                    else{
                        out.println(":ERR");
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            t.start();
        }
    }
}
