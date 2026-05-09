package eu.ase.httpserver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class HTTPSeminarProtocol {
    public String processInput(String theInput) {
        String theOuput = "";
        byte[] buffResp = new byte[4096];

        //GET /indextest.html HTTP/1.1

        if (theInput.indexOf("GET") != 0) {
            theOuput = "HTTP/1.1 200 OK\r\nContent-Length:19\r\n NU STIU COMANDA \r\n\r\n";
        } else {
            String filename = theInput.substring(theInput.indexOf("/") + 1, theInput.indexOf(" HTTP/"));
            String fileExt = filename.substring(filename.indexOf(".") + 1);

            String contentType = "";
            String fileContent = "";
            if (fileExt.compareToIgnoreCase("html") == 0) {
                contentType = "text/html";
            } else if (fileExt.compareToIgnoreCase("txt") == 0) {
                contentType = "text/html";
            } else if (fileExt.compareToIgnoreCase("gif") == 0) {
                contentType = "image/gif";
            }

            try {
                FileInputStream fis = new FileInputStream(filename);
                int bRead = 0;
                while ((bRead = fis.read(buffResp)) != -1) {
                    fileContent += new String(buffResp, 0, bRead);
                }

                fis.close();
                theOuput = "HTTP/1.1 200 OK\r\nContent-Type: " + contentType + "\r\nContent-Length: " + fileContent.length() + "\r\n\r\n" + fileContent + "\r\n";
            } catch (IOException e) {
                e.printStackTrace();
                theOuput = "HTTP/1.1 404\r\n\r\n";
            }
        }

        return theOuput;
    }
}
