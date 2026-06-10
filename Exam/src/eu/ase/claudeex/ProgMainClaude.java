package eu.ase.claudeex;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.ArrayList;
import java.util.List;

public class ProgMainClaude {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        List<Printable> laptops = Utils.createLaptops(3);
        laptops = Utils.readLaptops("laptops.txt");
        System.out.println("Laptops:");
        for (Printable laptop : laptops) {
            System.out.println(laptop.getInfo());
        }
        Utils.writeBinaryLaptops(laptops, "laptops.dat");
        List<Printable> laptopsB = Utils.readBinaryLaptops("laptops.dat");
        VectThread vt = new VectThread("laptops.dat");
        Thread t = new Thread(vt);
        t.start();
        t.join();
        System.out.println("avg: "+vt.getAvgPrice());
        TCPServer server = new TCPServer(7997);
        server.start();
    }
}
