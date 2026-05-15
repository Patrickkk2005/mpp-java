package eu.ase.testexv2;

import eu.ase.testex.Player;
import eu.ase.testex.Report;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProgMainTestExV2 {
    public static void main(String[] args) throws Exception {

        List<PlayerV2> players = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("players.txt")));
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            String playerName = parts[0];
            String teamName = parts[1];
            int playerPoints = Integer.parseInt(parts[2]);
            int rebounds = Integer.parseInt(parts[3]);
            int assists = Integer.parseInt(parts[4]);
            PlayerV2 p = new PlayerV2(playerName, teamName, playerPoints, rebounds, assists);
            players.add(p);
        }

        Map<String, List<PlayerV2>> teams = new HashMap<>();
        for (PlayerV2 p : players) {
            if(!teams.containsKey(p.getTeam())){
                teams.put(p.getTeam(), new ArrayList<>());
            }
            teams.get(p.getTeam()).add(p);
        }

        PlayerOperationV2 calcAvg = playerList -> {
            int sum = 0;
            for(PlayerV2 p : playerList){
                sum += p.getPoints();
            }
            return (double)sum/playerList.size();
        };

        Map<String, Double> avgPoints = new HashMap<>();
        for(Map.Entry<String, List<PlayerV2>> entry : teams.entrySet()){
            String team = entry.getKey();
            List<PlayerV2> playerList = entry.getValue();
            avgPoints.put(team, calcAvg.process(playerList));
        }

        ReportV2 report = new ReportV2();
        for(Map.Entry<String, List<PlayerV2>> entry : teams.entrySet()) {
            PlayerThreadV2 task = new PlayerThreadV2(report, entry.getKey(), entry.getValue(), avgPoints.get(entry.getKey()));
            Thread thread = new Thread(task);
            thread.start();
            thread.join();
        }
    }
}
