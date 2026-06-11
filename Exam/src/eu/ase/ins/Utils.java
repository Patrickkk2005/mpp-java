package eu.ase.ins;

import eu.ase.firstex.Phone;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Utils {
    public static List<Playable> createInstruments(int n){
        List<Playable> instruments = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            instruments.add(new Instrument());
        }
        return instruments;
    }

    public static List<Playable> readInstruments(String file) throws Exception {
        List<Playable> instruments = new ArrayList<>();
        RandomAccessFile raf = new RandomAccessFile(file,"r");
        String line;
        while((line=raf.readLine())!=null){
            Instrument instrument = new Instrument();
            instrument.setBrand(line);
            instrument.setPrice(Double.parseDouble(raf.readLine()));
            instrument.setWeight(Float.parseFloat(raf.readLine()));
            instruments.add(instrument);
        }
        raf.close();
        return instruments;
    }

    public static void writeBinaryInstruments(List<Playable> instruments, String file) throws Exception {
        ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
        oos.writeObject(instruments);
        oos.close();
    }

    public static List<Playable> readBinaryInstruments(String file) throws Exception {
        List<Playable> instruments = new ArrayList<>();
        ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)));
        instruments = (ArrayList<Playable>) ois.readObject();
        ois.close();
        return instruments;
    }
}
