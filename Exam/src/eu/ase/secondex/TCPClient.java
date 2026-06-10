package eu.ase.secondex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TCPClient {
    public static void main(String[] args) throws IOException {
        Socket socker = new Socket("127.0.0.1", 7997);

        PrintWriter out = new PrintWriter(socker.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socker.getInputStream()));

        out.println("TOP 2");

        String response = in.readLine();
        System.out.println(response);

        out.println("MEDIA");

        String response2 = in.readLine();
        System.out.println(response2);

        out.println("FILTRU BUCURESTI");

        String response3 = in.readLine();
        System.out.println(response3);
        socker.close();
    }
}
