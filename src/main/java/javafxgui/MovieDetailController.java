package javafxgui;

import movieControl.Movie;

public class MovieDetailController {

    MovieDetailModel model;
    MovieDetailView view;

    public MovieDetailController(MovieDetailModel model, MovieDetailView view) {
        this.model = model;
        this.view = view;
    }

    public void loadMovieDetail(Movie movie) {
        view.loadMovieDetail(movie);
    }
}
