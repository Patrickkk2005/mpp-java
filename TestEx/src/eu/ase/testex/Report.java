package eu.ase.testex;

public class Report {
    private String team;
    private String topScorer;
    private double avgPoints;

    public synchronized void setAvgPoints(String team, double avg) {
        this.team = team;
        this.avgPoints = avg;
    }

    public synchronized void setTopScorer(String team, String scorer) {
        this.team = team;
        this.topScorer = scorer;
    }

    public synchronized void setResults(String team, String topScorer, double avgPoints) {
        this.team = team;
        this.topScorer = topScorer;
        this.avgPoints = avgPoints;
    }

    @Override
    public String toString() {
        return team + " | " + topScorer + " | " + avgPoints;
    }
}
