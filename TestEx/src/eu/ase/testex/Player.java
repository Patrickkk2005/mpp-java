package eu.ase.testex;

public class Player {
    private String PlayerName;
    private String Team;
    private int Points;
    private int Rebounds;
    private int Assists;

    public Player(String PlayerName, String Team, int Points, int Rebounds, int Assists) {
        this.PlayerName = PlayerName;
        this.Team = Team;
        this.Points = Points;
        this.Rebounds = Rebounds;
        this.Assists = Assists;
    }

    public String getPlayerName() {
        return PlayerName;
    }

    public void setPlayerName(String PlayerName) {
        this.PlayerName = PlayerName;
    }

    public String getTeam() {
        return Team;
    }

    public void setTeam(String Team) {
        this.Team = Team;
    }

    public int getPoints() {
        return Points;
    }

    public void setPoints(int Points) {
        this.Points = Points;
    }

    public int getRebounds() {
        return Rebounds;
    }

    public void setRebounds(int Rebounds) {
        this.Rebounds = Rebounds;
    }

    public int getAssists() {
        return Assists;
    }

    public void setAssists(int Assists) {
        this.Assists = Assists;
    }

    @Override
    public String toString() {
        return PlayerName + " | " + Team + " | " + Points + " | " + Rebounds + " | " + Assists;
    }
}
