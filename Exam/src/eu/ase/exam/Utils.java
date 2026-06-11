package eu.ase.exam;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Utils {
    public static List<Car> readCars(String file) throws IOException {
        List<Car> cars = new ArrayList<>();
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        String line;
        in.readLine();
        while ((line = in.readLine()) != null) {
            String[] tokens = line.split(";");
            String id = tokens[0];
            String type = tokens[1];
            String brand = tokens[2];
            String city = tokens[3];
            int year = Integer.parseInt(tokens[4]);
            double price_day = Double.parseDouble(tokens[5]);
            float dis = Float.parseFloat(tokens[6]);
            switch (type) {
                case ("sedan"): {
                    cars.add(new Sedan(id,brand,city,year,price_day,dis));
                }
                case ("suv"):{
                    cars.add(new SUV(id,brand,city,year,price_day,dis));
                }
                case ("electric"):{
                    cars.add(new Electric(id,brand,city,year,price_day,dis));
                }
            }
        }
        in.close();
        return cars;
    }

    public static void writeReport(String file, List<Car> list) throws IOException {
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
        out.write("total oferte: "+list.stream().count());
        out.newLine();
        out.write("pret mediu: "+list.stream().mapToDouble(p->p.rentalPrice()).average().orElse(0.0));
        out.newLine();
        out.write("top 2: "+list.stream().sorted((a,b)->Double.compare(b.rentalPrice(),a.rentalPrice())).limit(2).collect(Collectors.toList()));
        out.close();
    }

}
