package eu.ase.ins;

import java.util.List;

public class VectThread implements Runnable {
    private List<Playable> instruments;
    private double avgPrice;
    private static final Object lock = new Object();
    public VectThread(String file) throws Exception {
        this.instruments = Utils.readInstruments(file);

    }

    public double getAvgPrice() {
        return avgPrice;
    }

    @Override
    public void run() {
        avgPrice = instruments.stream().mapToDouble(p->((Instrument)p).getPrice()).average().orElse(0.0);
    }
}
