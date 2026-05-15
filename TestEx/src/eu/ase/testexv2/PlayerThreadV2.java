package eu.ase.testexv2;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

public class PlayerThreadV2 implements Runnable {

    private ReportV2 report;
    private String team;
    private List<PlayerV2> players;
    private double avgPoints;

    public PlayerThreadV2(ReportV2 report, String team, List<PlayerV2> players, double avgPoints) {
        this.report = report;
        this.team = team;
        this.players = players;
        this.avgPoints = avgPoints;
    }

    @Override
    public void run() {
        PlayerV2 top = players.stream().max(Comparator.comparingInt(p-> p.getPoints())).get();
        report.setResults(team,top.getPlayerName(),avgPoints);
        try {
            report.writeResults();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
