package javafxgui;


import javafx.application.Application;
import javafx.stage.Stage;

public class MainUIController extends Application
{

    MovieTableModel movieTableModel;
    MovieTableView movieTableView;
    MovieTableController movieTableController;

    //MovieDetailModel movieDetailModel;
    MovieDetailView movieDetailView;
    //MovieDetailController movieDetailController;

    //MenuBarModel menuBarModel();
    MenuBarView menuBarView;
    //MenuBarController(menuBarModel, menuBarView);

    //MainWindowModel mainWindowModel;
    MainWindowController mainWindowController;
    //MainWindowView mainWindowView;

    StatusBarModel statusBarModel;
    StatusBarView statusBarView;
    StatusBarController statusBarController;

    public MainUIController() {

    }

    //@Override
    public void start(Stage primaryStage) throws Exception {
        movieTableModel = new MovieTableModel();
        movieTableView = new MovieTableView();
        movieTableController = new MovieTableController(movieTableModel, movieTableView);

        //movieDetailModel = new MovieDetailModel();
        movieDetailView = new MovieDetailView();
        //movieDetailController = new MovieDetailController(movieDetailModel, movieDetailController);

        //menuBarModel = new MenuBarModel();
        menuBarView = new MenuBarView();
        //menuBarController = new MenuBarController(menuBarModel, menuBarView);

        statusBarModel = new StatusBarModel();
        statusBarView = new StatusBarView();
        statusBarController = new StatusBarController(statusBarModel, statusBarView);


        mainWindowController = new MainWindowController(primaryStage, movieTableView, movieDetailView, menuBarView, statusBarView, movieTableController);
    }

}
