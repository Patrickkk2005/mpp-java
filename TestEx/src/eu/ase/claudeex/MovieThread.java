package eu.ase.claudeex;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovieThread extends Thread {
    public static Map<String, Double> avgRating;
    public static Map<String, List<Movie>> grouped;
    public static final Object lock = new Object();
    private List<Movie> movies;
    private String mode;

    public MovieThread(String mode, List<Movie> movies) {
        this.mode = mode;
        this.movies = movies;
    }

    @Override
    public void run() {
        if (mode.equals("grouped")) {
            synchronized (lock) {
                grouped = new HashMap<>();
                for (Movie m : movies) {
                    if (!grouped.containsKey(m.getGenre())) {
                        grouped.put(m.getGenre(), new ArrayList<>());
                    }
                    grouped.get(m.getGenre()).add(m);
                }
            }
        } else if (mode.equals("average")) {
            synchronized (lock) {
                MovieOperation averageRating = movieList -> {
                    int sum = 0;
                    for (Movie m : movieList) {
                        sum += m.getRating();
                    }
                    return (double) sum / movieList.size();
                };
                avgRating = new HashMap<>();
                for (Map.Entry<String, List<Movie>> m : grouped.entrySet()) {
                    String key = m.getKey();
                    List<Movie> list = m.getValue();
                    avgRating.put(key, averageRating.process(list));
                }
            }
        } else if (mode.equals("report")) {
            synchronized (lock) {
                try {
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("report.txt")));
                    for(Map.Entry<String, Double> m : avgRating.entrySet()){
                        bw.write(m.getKey() + "\t" + m.getValue() + "\n");
                        bw.newLine();
                    }
                    bw.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}
