package javafxgui;

import movieControl.Movie;

public class MovieCheckModel {

    Movie currentMovie;
    Movie movieToPersist;

    public MovieCheckModel() {

    }

    public Movie getCurrentMovie() {
        return currentMovie;
    }

    public void setCurrentMovie(Movie currentMovie) {
        this.currentMovie = currentMovie;
    }

    public Movie getMovieToPersist() {
        return movieToPersist;
    }

    public void setMovieToPersist(Movie movieToPersist) {
        this.movieToPersist = movieToPersist;
    }
}
