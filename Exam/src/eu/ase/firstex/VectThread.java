package eu.ase.firstex;

import java.util.List;

public class VectThread implements Runnable {
    private List<ElectronicDevices> phonesList;
    private double avgWeight;

    public VectThread(String file) throws Exception {
        phonesList = Utils.readPhones(file);
    }

    public List<ElectronicDevices> getList() {
        return phonesList;
    }


    public double getAvgWeight() {
        return avgWeight;
    }


    @Override
    public void run() {
        int s = 0;
        for (ElectronicDevices phone : phonesList) {
            s+= ((Phone)phone).getWeight();
        }
        avgWeight = (double) s / (phonesList.size());
    }
}
