package eu.ase.firstex;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Utils {
    private static List<ElectronicDevices> list;

    public static List<ElectronicDevices> createPhones(int n) throws Exception {
        list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(new Phone());
        }
        return list;
    }

    public static void writeBinaryPhones(String file, List<ElectronicDevices> listP) throws Exception {
        ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
        out.writeObject(listP);
        out.close();
    }

    public static List<ElectronicDevices> readBinaryPhones(String file) throws Exception {
        ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)));
        List<ElectronicDevices> list = (List<ElectronicDevices>) in.readObject();
        in.close();
        return list;
    }

    public static List<ElectronicDevices> readPhones(String file) throws Exception {
        list = new ArrayList<>();
        RandomAccessFile raf = new RandomAccessFile(file, "r");
        String line;
        while ((line = raf.readLine()) != null) {
            Phone p = new Phone();
            p.setWeight(Float.parseFloat(line));
            p.setDiagonal(Double.parseDouble(raf.readLine()));
            p.setProducer(raf.readLine());
            list.add(p);
        }
        raf.close();
        return list;
    }
}
