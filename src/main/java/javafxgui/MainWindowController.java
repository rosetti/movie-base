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
    MenuBarController menuBarController;

    public MainWindowController(Stage primaryStage, MainWindowView mainWindowView, MovieTableView movieTableView, MovieDetailView movieDetailView, MenuBarView menuBarView, MovieTableController movieTableController, MenuBarController menuBarController) {
        this.menuBarController = menuBarController;
        this.mainWindowView = mainWindowView;
        mainWindowView.start(primaryStage);
        setPosterViewAction();

    }

    public double getStageX() { return mainWindowView.x; }

    public double getStageY() { return mainWindowView.y; }

    private void setPosterViewAction() {
        menuBarController.setPosterMenuAction(e-> {
            mainWindowView.changeView();
        });
    }

}
