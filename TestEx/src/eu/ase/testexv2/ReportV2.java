package eu.ase.testexv2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ReportV2 {
    private String team;
    private String topScorer;
    private double avgPoints;
    private static final Object myLock = new Object();

    public void setAvgPoints(String team, double avg) {
        synchronized (myLock) {
            this.team = team;
            this.avgPoints = avg;
        }
    }

    public void setTopScorer(String team, String scorer) {
        synchronized (myLock) {
            this.team = team;
            this.topScorer = scorer;
        }
    }

    public void setResults(String team, String topScorer, double avgPoints) {
        synchronized (myLock) {
            this.team = team;
            this.topScorer = topScorer;
            this.avgPoints = avgPoints;
        }
    }

    public void writeResults() throws IOException {
        synchronized (myLock) {
            BufferedWriter bw = new BufferedWriter(new FileWriter("results.txt",true));
            bw.write(team + " | " + topScorer + " | " + avgPoints);
            bw.newLine();
            bw.close();
        }
    }

    @Override
    public String toString() {
        return team + " | " + topScorer + " | " + avgPoints;
    }
}
