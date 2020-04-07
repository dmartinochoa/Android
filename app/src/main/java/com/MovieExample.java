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

    int getImageResouse() {
        return this.imageResouse;
    }

    String getMovieName() {
        return this.movieName;
    }

    String getMovieOriginalName() {
        return this.movieOriginalName;
    }

    String getMovieScore() {
        return this.movieScore;
    }

    String getReleaseDateValue() {
        return this.releaseDateValue;
    }
}
