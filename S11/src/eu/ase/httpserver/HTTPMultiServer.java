package eu.ase.httpserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HTTPMultiServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        boolean listening = true;

        try {
            int port = Integer.parseInt(args[0]);
            serverSocket = new ServerSocket(port);
            while (listening) {
                Socket client = serverSocket.accept();
                HTTPMultiServerThread objClient = new HTTPMultiServerThread(client);
                objClient.start();
                //client.close(); - GRESIT
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (serverSocket != null) {
            serverSocket.close();
        }
    }
}
