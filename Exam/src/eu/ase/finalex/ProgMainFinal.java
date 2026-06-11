package eu.ase.finalex;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ProgMainFinal {
    public static void main(String[] args) throws IOException, ClassNotFoundException, ExecutionException, InterruptedException {
        List<Book> books = new ArrayList<>();
        books=Utils.readBooks("books.txt");
        Utils.writeBinaryBooks(books,"books.dat");
        List<Book> readBooks = Utils.readBinaryBooks("books.dat");
        Map<String, List<Book>> booksByType = Utils.groupByType(books);
        System.out.println(booksByType);
        System.out.println(Utils.getTop2(books));
        System.out.println(Utils.filterByAuthor(books,"Martin"));
        VectThread vt = new VectThread("books.dat");
        Thread t = new Thread(vt);
        t.start();
        t.join();

        ExecutorService executor = Executors.newFixedThreadPool(3);

        List<Book> finalBooks = books;
        Future<Double> futureTotal = executor.submit(()->{
            return finalBooks.stream().mapToDouble(p->p.finalPrice()).sum();
        });

        Future<List<Book>> futureTop2 = executor.submit(()->{
           return Utils.getTop2(finalBooks);
        });

        Future<?> writeReport = executor.submit(()->{
            try {
                Utils.writeBooks(finalBooks,"report.txt");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        System.out.println("Total: " + futureTotal.get());
        System.out.println("Top2: " + futureTop2.get());
        writeReport.get();
        executor.shutdown();

        Thread serverThread = new Thread(()->{
            try {
                TCPServer server = new TCPServer(50001);
                server.start();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        serverThread.setDaemon(true);
        serverThread.start();
        Thread.sleep(1000);

        Socket socket = new Socket("localhost", 50001);
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        out.println("GETLIST");
        ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
        List<Book> serverBooks = (List<Book>) in.readObject();
        System.out.println(serverBooks.toString());
        socket.close();
    }
}