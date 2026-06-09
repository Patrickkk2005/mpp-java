package eu.ase.secondex;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Utils {
    public static List<Apartament> readApartaments(String file) throws IOException {
        List<Apartament> apartaments = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        br.readLine();
        String line;
        while ((line = br.readLine()) != null) {
            String[] tokens = line.split(";");
            String id = tokens[0];
            String type = tokens[1];
            String city = tokens[2];
            String zone = tokens[3];
            int area = Integer.parseInt(tokens[4]);
            int price_sqm = Integer.parseInt(tokens[5]);
            float discount = Float.parseFloat(tokens[6]);
            if (type.equals("studio")) {
                Studio a = new Studio(id, city, zone, area, price_sqm, discount);
                apartaments.add(a);
            } else if (type.equals("2rooms")) {
                twoRooms a = new twoRooms(id, city, zone, area, price_sqm, discount);
                apartaments.add(a);
            } else if (type.equals("3rooms")) {
                threeRooms a = new threeRooms(id, city, zone, area, price_sqm, discount);
                apartaments.add(a);
            } else if (type.equals("penthouse")) {
                penthouse a = new penthouse(id, city, zone, area, price_sqm, discount);
                apartaments.add(a);
            }
        }
        br.close();
        return apartaments;
    }

    public static void writeReport(List<Apartament> apartaments, String file) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
        bw.write("total oferte: " + apartaments.size() + "\n");
        double avg = apartaments.stream().mapToDouble(a -> a.pretFinal()).average().orElse(0.0);
        List<Apartament> top2 = apartaments.stream().sorted((a, b) -> Double.compare(b.pretFinal(), a.pretFinal())).limit(2).collect(Collectors.toList());
        bw.write("pret mediu: " + avg + "\n");
        bw.write("cele mai scumpe 2:\n");
        for (Apartament a : top2) {
            bw.write(a.getId() + ";" + a.getType() + ";" + a.getCity() + ";" + a.getZone() + ";" + a.pretFinal() + "\n");
        }
        bw.close();
    }

    public static Map<String,List<Apartament>> groupByCity(List<Apartament> apartaments) {
        return apartaments.stream().collect(Collectors.groupingBy(a->a.getCity()));
    }
}
