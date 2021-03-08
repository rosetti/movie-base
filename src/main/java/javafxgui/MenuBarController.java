package javafxgui;

import javafx.event.EventHandler;
import movieControl.Movie;



public class MenuBarController {

    MenuBarModel model;
    MenuBarView view;
    MovieEditController movieEditController;

    public MenuBarController(MenuBarModel menuBarModel, MenuBarView menuBarView, MovieEditController movieEditController) {
        this.model = menuBarModel;
        this.view = menuBarView;
        this.movieEditController = movieEditController;
        setAddMovieAction();
    }

    private void setAddMovieAction() {
        view.setAddMovieAction(e -> {
            movieEditController.showBlank();
        });
    }

    public void setPosterMenuAction(EventHandler handler) {
        view.setPosterViewAction(handler);
    }
}
