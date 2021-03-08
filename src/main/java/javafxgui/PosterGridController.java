package javafxgui;

public class PosterGridController {

    PosterGridView view;

    public PosterGridController(PosterGridView posterGridView) {
        view = posterGridView;
    }

    public void loadMovies() {
        view.loadMoviesFromMovieBase();
    }
}
