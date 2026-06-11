package eu.ase.exam;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class ProgMain {
    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        List<Car> cars = new ArrayList<>();
        cars = Utils.readCars("cars.txt");
        Utils.writeReport("rep.txt",cars);

        ExecutorService executor = Executors.newFixedThreadPool(3);
        List<Car> finalCars = cars;
        Future<Long> totalFuture = executor.submit(()->{
            return finalCars.stream().count();
        });

        Future<List<Car>> top2Future = executor.submit(()->{
            return finalCars.stream().sorted((a,b)->Double.compare(b.rentalPrice(),a.rentalPrice())).limit(2).collect(Collectors.toList());
        });

        System.out.println(totalFuture.get());
        System.out.println(top2Future.get().toString());
        executor.shutdown();

    }
}
