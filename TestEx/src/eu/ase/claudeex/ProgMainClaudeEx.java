package eu.ase.claudeex;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProgMainClaudeEx {
    public static void main(String[] args) throws IOException, InterruptedException {
        List<Movie> movies = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("movies.txt")));
        String line;
        while((line=br.readLine())!=null){
            String[] split = line.split(",");
            String title = split[0];
            String genre = split[1];
            int rating = Integer.parseInt(split[2]);
            int duration = Integer.parseInt(split[3]);
            int year = Integer.parseInt(split[4]);
            Movie m =  new Movie(title, genre, rating, duration, year);
            movies.add(m);
        }

        Map<String, List<Movie>> grouped = new HashMap<>();
        for(Movie m : movies){
            if(!grouped.containsKey(m.getGenre())){
                grouped.put(m.getGenre(), new ArrayList<>());
            }
            grouped.get(m.getGenre()).add(m);
        }

        MovieOperation averageRating = movieList -> {
            int sum = 0;
            for(Movie m : movieList){
                sum+=m.getRating();
            }
            return (double)sum/movieList.size();
        };

        Map<String, Double> avgR = new HashMap<>();
        for(Map.Entry<String, List<Movie>> m : grouped.entrySet()){
            String key = m.getKey();
            List<Movie> list = m.getValue();
            avgR.put(key, averageRating.process(list));
        }

        MovieThread t1 = new MovieThread("grouped",movies);
        MovieThread t2 = new MovieThread("average",movies);
        MovieThread t3 = new MovieThread("report",movies);
        t1.start();
        t1.join();
        t2.start();
        t2.join();
        t3.start();
        t3.join();

    }
}
