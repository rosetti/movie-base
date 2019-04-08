package javafxgui;

import javafx.application.Application;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import movieControl.Movie;

/**
 * Created by Vin on 03/12/2017.
 */
public class MainWindowController extends Application{

    MainWindowView mainWindowView;
    MovieTableView tableView;
    MovieDetailView detailView;
    MenuBarView menuBar;




    public void start(Stage primaryStage) {

        tableView = new MovieTableView();
        detailView = new MovieDetailView();
        menuBar = new MenuBarView();


        mainWindowView = new MainWindowView(tableView, detailView, menuBar); //TODO: I don't like this. The controller shouldn't create the class
        mainWindowView.start(primaryStage);
        setRowClickedAction();
        setContextMenuAction();

    }

    private void setRowClickedAction() {
        tableView.setRowClickedAction((obs, oldSelection, newSelection) -> {
            System.out.println(obs.getValue());
            System.out.println(obs.hashCode());

            Movie movie = (Movie) newSelection;
            System.out.println("You clicked: " + movie.getTitle());
            detailView.loadMovieDetail(movie);
        });
    };

    private void setContextMenuAction() {
        tableView.setContextMenuAction((MouseEvent) -> {
            System.out.println("MouseEvent!");
        });
    }
}
