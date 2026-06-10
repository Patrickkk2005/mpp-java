package eu.ase.secondex;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.stream.Collectors;

public class TCPServer {
    private ServerSocket serverSocket;
    private int port;
    private List<Apartament> apartaments;

    public TCPServer(int port) throws IOException {
        this.port = port;
        this.serverSocket = new ServerSocket(port);
        this.apartaments = Utils.readApartaments("apartaments.txt");
    }

    public void start() throws IOException {
        while (true) {
            Socket client =  serverSocket.accept();
            Thread t =  new Thread(() -> {
                try {
                    BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                    PrintWriter out = new PrintWriter(new OutputStreamWriter(client.getOutputStream()), true);

                    String line =  in.readLine();
                    if(line.equals("MEDIA")) {
                        out.println(apartaments.stream().mapToDouble(a->a.pretFinal()).average().orElse(0));
                    }
                    else if(line.equals("FILTRU BUCURESTI")) {
                        out.println(apartaments.stream().filter(a->a.getCity().equals("Bucuresti")).collect(Collectors.toList()));
                    }
                    else if(line.equals("TOP 2")) {
                        out.println(apartaments.stream().sorted((a,b)-> Double.compare( b.pretFinal(), a.pretFinal())).limit(2).collect(Collectors.toList()));
                    }
                    client.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            t.start();
        }
    }
}
