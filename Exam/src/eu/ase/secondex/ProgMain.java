package eu.ase.secondex;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class ProgMain {
    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        List<Apartament> apartaments = Utils.readApartaments("apartaments.txt");
        ExecutorService executor = Executors.newFixedThreadPool(3);

        Future<Double> total = executor.submit(() -> {
            return apartaments.stream().mapToDouble(a -> a.pretFinal()).sum();
        });

        Future<List<Apartament>> top2 = executor.submit(() -> {
            return apartaments.stream().sorted((a, b) -> Double.compare(b.pretFinal(), a.pretFinal())).limit(2).collect(Collectors.toList());
        });

        Future<?> report = executor.submit(() -> {
            try {
                Utils.writeReport(apartaments, "report.txt");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        Double totalFuture = total.get();
        List<Apartament> top2Future = top2.get();

        executor.shutdown();

        TCPServer server = new TCPServer(7997);
        server.start();
    }
}
