package eu.ase.claudeex;

public class Movie {
    private String title;
    private String genre;
    private int rating;
    private int duration;
    private int year;

    public Movie(String title, String genre, int rating, int duration, int year) {
        this.title = title;
        this.genre = genre;
        this.rating = rating;
        this.duration = duration;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Movie [title=" + title + ", genre=" + genre + ", rating=" + rating + ", duration=" + duration + ", year=" + year + "]";
    }
}
