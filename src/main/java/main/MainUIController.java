package main;


import inputOutput.SQLiteDatabase;
import javafx.application.Application;
import javafx.stage.Stage;
import javafxgui.*;

public class MainUIController extends Application {

    MovieEditModel movieEditModel;
    MovieEditView movieEditView;
    MovieEditController movieEditController;

    MovieTableModel movieTableModel;
    MovieTableView movieTableView;
    MovieTableController movieTableController;

    MovieDetailModel movieDetailModel;
    MovieDetailView movieDetailView;
    MovieDetailController movieDetailController;

    //MenuBarModel menuBarModel();
    MenuBarView menuBarView;
    //MenuBarController(menuBarModel, menuBarView);

    //MainWindowModel mainWindowModel;
    MainWindowController mainWindowController;
    MainWindowView mainWindowView;

    StatusBarModel statusBarModel;
    StatusBarView statusBarView;
    StatusBarController statusBarController;

    AdvancedSearchModel advancedSearchModel;
    AdvancedSearchView advancedSearchView;
    AdvancedSearchController advancedSearchController;

    SearchBarModel searchBarModel;
    SearchBarView searchBarView;
    SearchBarController searchBarController;

    ImageFetchView imageFetchView;
    ImageFetchViewController imageFetchViewController;

    public MainUIController() {

    }

    //@Override
    public void start(Stage primaryStage) throws Exception {

        movieDetailModel = new MovieDetailModel();
        movieDetailView = new MovieDetailView();
        movieDetailController = new MovieDetailController(movieDetailModel, movieDetailView);

        statusBarModel = new StatusBarModel();
        statusBarView = new StatusBarView();
        statusBarController = new StatusBarController(statusBarModel, statusBarView);

        MovieCheckModel movieCheckModel = new MovieCheckModel();
        MovieCheckView movieCheckView = new MovieCheckView();
        MovieCheckController movieCheckController = new MovieCheckController(movieCheckModel, movieCheckView);

        imageFetchView = new ImageFetchView();
        imageFetchViewController = new ImageFetchViewController(imageFetchView);

        movieEditModel = new MovieEditModel();
        movieEditView = new MovieEditView();
        movieEditController = new MovieEditController(movieEditModel, movieEditView, movieCheckController, imageFetchViewController);

        MovieContextMenu movieContextMenu = new MovieContextMenu(movieEditController);

        movieTableModel = new MovieTableModel();
        movieTableView = new MovieTableView();
        movieTableController = new MovieTableController(movieTableModel, movieTableView, movieDetailController, statusBarController, movieEditController);

        PosterGridView posterGridView = new PosterGridView(movieContextMenu, movieDetailController);
        PosterGridController posterGridController = new PosterGridController(posterGridView);

        MenuBarModel menuBarModel = new MenuBarModel();
        menuBarView = new MenuBarView();
        MenuBarController menuBarController = new MenuBarController(menuBarModel, menuBarView, movieEditController);

        advancedSearchView = new AdvancedSearchView();
        advancedSearchModel = new AdvancedSearchModel();
        advancedSearchController = new AdvancedSearchController(advancedSearchView, advancedSearchModel, movieTableController, posterGridController);

        MovieDisplayPane movieDisplayPane = new MovieDisplayPane(posterGridView, movieTableView);


        searchBarView = new SearchBarView(movieTableView, movieTableController);
        searchBarModel = new SearchBarModel();
        searchBarController = new SearchBarController(searchBarModel, searchBarView, movieTableController, advancedSearchController, posterGridController, movieDisplayPane);

        mainWindowView = new MainWindowView(movieTableView, movieDetailView, menuBarView, statusBarView, searchBarView, movieDisplayPane, posterGridView);

        mainWindowController = new MainWindowController(primaryStage, mainWindowView, movieTableView, movieDetailView, menuBarView, movieTableController, menuBarController);
        SQLiteDatabase db = SQLiteDatabase.getInstance();
        db.loadAllMovies();
        movieTableController.loadMovies();
        posterGridController.loadMovies();
    }

}
