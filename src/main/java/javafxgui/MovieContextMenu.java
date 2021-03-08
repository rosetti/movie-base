package javafxgui;

import inputOutput.SQLiteDatabase;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import movieControl.Movie;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class MovieContextMenu extends ContextMenu {

    MenuItem menuItemYoutube;
    MenuItem menuItemToggleWatched;
    MenuItem menuItemOpenExplorer;
    MenuItem menuItemEdit;
    MenuItem menuItemDelete;
    MenuItem menuItemPlay;
    ContextMenu contextMenu;
    Movie selectedMovie;
    MovieEditController movieEditController;

    public MovieContextMenu(MovieEditController movieEditController) {
        this.movieEditController = movieEditController;
        contextMenu = new ContextMenu();
        menuItemPlay = new MenuItem("Play");
        menuItemYoutube = new MenuItem("Watch Trailer on Youtube");
        menuItemToggleWatched = new MenuItem("Toggle Watched/Unwatched");
        menuItemOpenExplorer = new MenuItem("Open In Explorer");
        menuItemEdit = new MenuItem("Edit Entry");
        menuItemDelete = new MenuItem("Remove from MovieBase");
        setContextMenuActions();

        contextMenu.getItems().addAll(menuItemPlay, menuItemYoutube, menuItemToggleWatched, menuItemOpenExplorer, menuItemEdit, menuItemDelete);
    }

    public void showContextMenu(Node node, double x, double y, Movie movie) {
        selectedMovie = movie;
        contextMenu.show(node, x, y);
    }

    private void setContextMenuActions() {
        menuItemYoutube.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                try {
                    java.awt.Desktop.getDesktop().browse(new URI("https://www.youtube.com/results?search_query="+ selectedMovie.getTitle().replace(" ", "+") + "+trailer"));
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
            }
        });

        menuItemPlay.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                try {
                    java.awt.Desktop.getDesktop().open(new File(selectedMovie.getFileLocation()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        menuItemOpenExplorer.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                try {
                    Runtime.getRuntime().exec("explorer.exe /select," + selectedMovie.getFileLocation());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        menuItemEdit.setOnAction(e -> {
            movieEditController.show(selectedMovie);
        });

        menuItemToggleWatched.setOnAction(e-> {
            SQLiteDatabase db = SQLiteDatabase.getInstance();
            db.toggleWatchedUnwatched(selectedMovie);
        });

        menuItemDelete.setOnAction(e-> {
            YesNoMessageBox.show("Are you sure you want to delete " + "\"" +
                    selectedMovie.getTitle() +"\"?", "Are you sure you want to delete this movie?");
            if (YesNoMessageBox.getResponse()) {
                SQLiteDatabase db = SQLiteDatabase.getInstance();
                db.deleteMovie(selectedMovie);
            }
        });
    }

    public void hideContextMenu() {
        System.out.println("Hide!");
        hide();
    }
}
