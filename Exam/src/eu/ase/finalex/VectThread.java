package eu.ase.finalex;

import java.io.IOException;
import java.util.List;

public class VectThread implements Runnable {
    private List<Book> books;
    private double aFP;
    private static final Object lock = new Object();

    public VectThread(String file) throws IOException, ClassNotFoundException {
        this.books=Utils.readBinaryBooks(file);
    }

    public void avgFinalPrice() {
        synchronized (lock) {
            aFP = books.stream().mapToDouble(p -> p.finalPrice()).average().orElse(0.0);
        }
    }

    public void printFinalPrice() {
        synchronized (lock) {
            System.out.println(aFP);
        }
    }

    @Override
    public void run() {
        avgFinalPrice();
        printFinalPrice();
    }
}
