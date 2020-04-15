package javafxgui;

/**
 * Created by Vin on 03/12/2017.
 */
public class MovieTableController {

    MovieTableModel model;
    MovieTableView view;

    public MovieTableController(MovieTableModel model, MovieTableView view) {
        this.model = model;
        this.view = view;
    }

    public void addMovies() {
        view.loadMoviesFromMovieBase();
    }

    public void loadMovies() {
        view.loadMoviesFromMovieBase();
    }

}
