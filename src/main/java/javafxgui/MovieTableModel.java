package javafxgui;

import movieControl.Movie;

public class MovieTableModel {

    Movie selectedMovie;

    public MovieTableModel() {

    }

    public void setSelectedMovie(Movie movie) {
        selectedMovie = movie;
    }

    public Movie getSelectedMovie() {
        return selectedMovie;
    }

}
