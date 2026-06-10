package eu.ase.claudeex;

import java.io.IOException;
import java.util.List;

public class VectThread implements Runnable {
    private List<Printable> list;
    private double avgPrice;

    public VectThread(String file) throws IOException, ClassNotFoundException {
        this.list = Utils.readBinaryLaptops(file);
    }

    public List<Printable> getList() {
        return list;
    }

    public double getAvgPrice() {
        return avgPrice;
    }


    @Override
    public void run() {
        this.avgPrice = list.stream().mapToDouble(a->((Laptop)a).getPrice()).average().orElse(0.0);
    }
}
