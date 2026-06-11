package eu.ase.ins;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ProgMain {
    public static void main(String[] args) throws Exception {
        List<Playable> instruments = Utils.createInstruments(3);
        instruments = Utils.readInstruments("instruments.txt");
        Utils.writeBinaryInstruments(instruments,"instruments.dat");
        System.out.println(instruments.toString());

        VectThread vt = new VectThread("instruments.txt");
        Thread t = new Thread(vt);
        t.start();
        t.join();
        System.out.println(vt.getAvgPrice());

        Thread serverThread= new Thread(()->{
            try {
                TCPServer sv = new TCPServer(50001);
                sv.start();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        serverThread.setDaemon(true);
        serverThread.start();
        Thread.sleep(300);

        Socket socket = new Socket("localhost", 50001);
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        out.println("GETFILE");
        ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
        List<Playable> playables = (List<Playable>) ois.readObject();
        System.out.println(playables.toString());
        socket.close();
    }
}
