package eu.ase.finalex;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Utils {
    public static List<Book> readBooks(String file) throws IOException {
        List<Book> books = new ArrayList<>();
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        String line;
        in.readLine();
        while ((line = in.readLine()) != null) {
            String[] tokens = line.split(";");
            String id = tokens[0];
            String type = tokens[1];
            String title = tokens[2];
            String author = tokens[3];
            int pages = Integer.parseInt(tokens[4]);
            double price = Double.parseDouble(tokens[5]);
            float discount = Float.parseFloat(tokens[6]);
            if (type.equals("novel")) {
                books.add(new Novel(id, type, title, author, pages, price, discount));
            } else if (type.equals("textbook")) {
                books.add(new Textbook(id, type, title, author, pages, price, discount));
            } else if (type.equals("comic")) {
                books.add(new Comic(id, type, title, author, pages, price, discount));
            }
        }
        in.close();
        return books;
    }

    public static void writeBooks(List<Book> books, String file) throws IOException {
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
        for (Book book : books) {
            out.write(book.toString());
            out.newLine();
            out.write(String.valueOf(book.finalPrice()));
        }
        out.close();
    }

    public static List<Book> readBinaryBooks(String file) throws IOException, ClassNotFoundException {
        List<Book> books = new ArrayList<>();
        ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)));
        books = (List<Book>) in.readObject();
        in.close();
        return books;
    }

    public static void writeBinaryBooks(List<Book> books, String file) throws IOException, ClassNotFoundException {
        ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
        out.writeObject(books);
        out.close();
    }

    public static Map<String, List<Book>> groupByType(List<Book> books) {
        return books.stream().collect(Collectors.groupingBy(a -> a.getType()));
    }

    public static List<Book> getTop2(List<Book> books) {
        return books.stream().sorted((a, b) -> Double.compare(b.finalPrice(), a.finalPrice())).limit(2).collect(Collectors.toList());
    }

    public static List<Book> filterByAuthor(List<Book> books, String author) {
        return books.stream().filter(a -> a.getAuthor().equals(author)).collect(Collectors.toList());
    }
}
