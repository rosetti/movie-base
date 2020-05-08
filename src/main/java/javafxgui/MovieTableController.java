package javafxgui;

import inputOutput.SQLiteDatabase;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import movieControl.Movie;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by Vin on 03/12/2017.
 */
public class MovieTableController {

    private MovieTableModel model;
    private MovieTableView view;
    MovieDetailController movieDetailController;
    StatusBarController statusBarController;
    MovieEditController movieEditController;

    public MovieTableController(MovieTableModel model, MovieTableView view, MovieDetailController movieDetailController,
                                StatusBarController statusBarController, MovieEditController movieEditController) {
        this.model = model;
        this.view = view;
        this.movieDetailController = movieDetailController;
        this.statusBarController = statusBarController;
        this.movieEditController = movieEditController;

        setContextMenuActions();
        setRowClickedAction();
        loadMovies();
    }

    public void loadMovies() {
        view.loadMoviesFromMovieBase();
        statusBarController.setStatusBarText(view.getLoadedMovieCount() + " Movies loaded.");
    }

    public void setRowClickedAction() {
        view.setRowClickedAction((obs, oldSelection, newSelection) -> { ;

            Movie movie = (Movie) newSelection;
            if (newSelection != null) {
                model.setSelectedMovie(movie);
                movieDetailController.loadMovieDetail(movie);
            }
        });
    }

    private void setContextMenuActions() {
        view.setContextMenuAction(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                view.hideContextMenu();
                if (t.getButton() == MouseButton.SECONDARY) {
                    view.showContextMenu(t.getScreenX(), t.getScreenY());
                }
            }
        });

        view.setYoutubeAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                try {
                    java.awt.Desktop.getDesktop().browse(new URI("https://www.youtube.com/results?search_query="+ model.getSelectedMovie().getTitle().replace(" ", "+") + "+trailer"));
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
            }
        });

        view.setPlayAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                try {
                    java.awt.Desktop.getDesktop().open(new File(model.getSelectedMovie().getFileLocation()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        view.setExplorerAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                try {
                    Runtime.getRuntime().exec("explorer.exe /select," + model.getSelectedMovie().getFileLocation());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        view.setEditAction(e -> {
            movieEditController.show(model.getSelectedMovie());
        });

        view.setToggleWatchedAction(e-> {
            SQLiteDatabase db = SQLiteDatabase.getInstance();
            db.toggleWatchedUnwatched(model.getSelectedMovie());
        });

        view.setDeleteMovieAction(e-> {
            YesNoMessageBox.show("Are you sure you want to delete " + "\"" +
                    model.getSelectedMovie().getTitle() +"\"?", "Are you sure you want to delete this movie?");
            if (YesNoMessageBox.getResponse()) {
                SQLiteDatabase db = SQLiteDatabase.getInstance();
                db.deleteMovie(model.getSelectedMovie());
            }
        });
    }


}
