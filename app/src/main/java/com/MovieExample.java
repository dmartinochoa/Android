package com;

public class MovieExample {
    private int imageResouse;
    private String movieName;
    private String movieScore;
    private String movieOriginalName;
    private String releaseDateValue;

    public MovieExample(int imageResouse, String movieName, String movieScore, String movieOriginalName, String releaseDateValue) {
        this.imageResouse = imageResouse;
        this.movieName = movieName;
        this.movieScore = movieScore;
        this.movieOriginalName = movieOriginalName;
        this.releaseDateValue = releaseDateValue;
    }
    public int getImageResouse(){
        return this.imageResouse;
    }

    public String getMovieName() {
        return this.movieName;
    }

    public String getMovieOriginalName() {
        return this.movieOriginalName;
    }

    public String getMovieScore() {
        return this.movieScore;
    }

    public String getReleaseDateValue() {
        return this.releaseDateValue;
    }
}
