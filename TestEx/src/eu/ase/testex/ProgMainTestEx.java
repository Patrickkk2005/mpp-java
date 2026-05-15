package eu.ase.testex;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProgMainTestEx {
    public static void main(String[] args) throws IOException, InterruptedException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("players.txt")));
        String line;
        List<Player> players = new ArrayList<>();
        while ((line = br.readLine()) != null){
            String[] parts = line.split(",");
            String playerName = parts[0];
            String teamName = parts[1];
            int points = Integer.parseInt(parts[2]);
            int rebounds = Integer.parseInt(parts[3]);
            int assists = Integer.parseInt(parts[4]);
            Player p = new Player(playerName, teamName, points, rebounds, assists);
            players.add(p);
        }

        for(Player p : players){
            System.out.println(p);
        }

        Map<String, List<Player>> teams = players.stream().collect(Collectors.groupingBy(p->p.getTeam()));
        teams.forEach((team,playerList)->System.out.println(team+": "+playerList));

        List<Player> filter = players.stream().filter(p->p.getPoints() > 20).collect(Collectors.toList());
        filter.forEach(p->System.out.println(p));

        Map<String,Double> avgPoints = players.stream().collect(Collectors.groupingBy(p->p.getTeam(),Collectors.averagingInt(p->p.getPoints())));
        avgPoints.forEach((k,v)->System.out.println(k+": "+v));

        Report report = new Report();


        Thread t1 = new Thread(() -> {
            teams.forEach((team, playerList) -> {
                double avg = avgPoints.get(team);
                Player top = playerList.stream()
                        .max(Comparator.comparingInt(p -> p.getPoints()))
                        .get();
                report.setResults(team, top.getPlayerName(), avg);
            });
        });
        Thread t2 = new Thread(() -> {
            teams.forEach((team, playerList) -> {
                Player top = playerList.stream()
                        .max(Comparator.comparingInt(p -> p.getPoints()))
                        .get();
                report.setTopScorer(team, top.getPlayerName());
            });
        });

        Thread t3 = new Thread(()->{System.out.println(report);});

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        t3.start();
        t3.join();

    }
}
