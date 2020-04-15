package javafxgui;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import movieControl.Movie;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by Vin on 03/12/2017.
 */
public class MainWindowController{

    MainWindowView mainWindowView;
    MovieTableView movieTableView;
    MovieTableController movieTableController;
    MovieTableModel movieTableModel;
    MovieDetailView movieDetailView;
    MenuBarView menuBarView;
    Movie selectedMovie;
    StatusBarView statusBarView;


//    public MainWindowController() {
//
//    }

    public MainWindowController(Stage primaryStage, MovieTableView movieTableView, MovieDetailView movieDetailView, MenuBarView menuBarView, StatusBarView statusBarView, MovieTableController movieTableController) {
        this.movieDetailView = movieDetailView;
        this.movieTableView = movieTableView;
        this.menuBarView = menuBarView;
        this.statusBarView  = statusBarView;
        this.movieTableController = movieTableController;

        mainWindowView = new MainWindowView(this.movieTableView, this.movieDetailView, this.menuBarView, this.statusBarView, this.movieTableController); //TODO: I don't like this. The controller shouldn't create the class
        mainWindowView.start(primaryStage);
        setRowClickedAction();
        setContextMenuActions();
    }

    /*
    public void start(Stage primaryStage) {

        movieTableView = new MovieTableView();
        movieTableModel = new MovieTableModel();
        movieTableController = new MovieTableController(movieTableModel, movieTableView);
        movieDetailView = new MovieDetailView();
        StatusBarModel statusBarModel = new StatusBarModel();
        StatusBarView statusBarView = new StatusBarView();
        StatusBarController statusBarController = new StatusBarController(statusBarModel, statusBarView);
        menuBarView = new MenuBarView();


        mainWindowView = new MainWindowView(movieTableView, movieDetailView, menuBarView, statusBarView, movieTableController); //TODO: I don't like this. The controller shouldn't create the class
        mainWindowView.start(primaryStage);
        setRowClickedAction();
        setContextMenuActions();

    }
     */

    public double getStageX() { return mainWindowView.x; }

    public double getStageY() {
        return mainWindowView.y;
    }

    private void setRowClickedAction() {
        movieTableView.setRowClickedAction((obs, oldSelection, newSelection) -> {
            System.out.println(obs.getValue());
            System.out.println(obs.hashCode());
            //System.out.println(oldSelection.);

            Movie movie = (Movie) newSelection;
            if (newSelection != null) {
                selectedMovie = movie;
                movieDetailView.loadMovieDetail(movie);
            }
        });
    };

    private void setContextMenuActions() {
        movieTableView.setContextMenuAction(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                movieTableView.hideContextMenu();
                if (t.getButton() == MouseButton.SECONDARY) {
                    movieTableView.showContextMenu(t.getScreenX(), t.getScreenY());
                }
            }
        });

        movieTableView.setYoutubeAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                try {
                    java.awt.Desktop.getDesktop().browse(new URI("https://www.youtube.com/results?search_query="+selectedMovie.getTitle().replace(" ", "+") + "+trailer"));
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
            }
        });

        movieTableView.setPlayAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                try {
                    java.awt.Desktop.getDesktop().open(new File(selectedMovie.getFileLocation()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        movieTableView.setExplorerAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                try {
                    //java.awt.Desktop.getDesktop().browse(new File(selectedMovie.getFileLocation()).toURI());
                    Runtime.getRuntime().exec("explorer.exe /select," + selectedMovie.getFileLocation());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }


}
