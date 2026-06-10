package eu.ase.claudeex;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Utils {
    public static List<Printable> createLaptops(int n) {
        List<Printable> laptops = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            laptops.add(new Laptop());
        }
        return laptops;
    }

    public static List<Printable> readLaptops(String file) throws IOException {
        List<Printable> laptops = new ArrayList<>();
        RandomAccessFile raf = new RandomAccessFile(file, "r");
        String line;
        while ((line = raf.readLine()) != null) {
            Laptop laptop = new Laptop();
            laptop.setBrand(line);
            laptop.setPrice(Double.parseDouble(raf.readLine()));
            laptop.setWeight(Float.parseFloat(raf.readLine()));
            laptops.add(laptop);
        }
        raf.close();
        return laptops;
    }

    public static void writeBinaryLaptops(List<Printable> laptops, String file) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
        oos.writeObject(laptops);
        oos.close();
    }

    public static List<Printable> readBinaryLaptops(String file) throws IOException, ClassNotFoundException {
        List<Printable> laptops = new ArrayList<>();
        ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)));
        laptops = (List<Printable>) ois.readObject();
        ois.close();
        return laptops;
    }
}
