package eu.ase.firstex;

import java.util.List;

public class ProgMainPlayGround {
    public static void main(String[] args) throws Exception {
        SmartPhone sp = new SmartPhone();
        sp.setBatteryDuration(100);
        System.out.println(sp.infoDevice());

        List<ElectronicDevices> phones = Utils.createPhones(3);
        System.out.println(phones.size());

        List<ElectronicDevices> phonesFromText = Utils.readPhones("phones.txt");
        System.out.println(phonesFromText.size());

        Utils.writeBinaryPhones("phones.dat", phones);
        List<ElectronicDevices> readPhones = Utils.readBinaryPhones("phones.dat");
        System.out.println(readPhones.size());

        VectThread vt = new VectThread("phones.txt");
        Thread t = new Thread(vt);
        t.start();
        t.join();
        System.out.println("avg weight = " + vt.getAvgWeight());
    }
}
