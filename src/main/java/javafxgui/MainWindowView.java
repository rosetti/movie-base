package javafxgui;

//Java Imports

import javafx.application.*;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.*;
//Local Imports


public class MainWindowView {
    Stage mainStage;
    Scene mainScene;
    BorderPane mainPane;
    MovieTableView tableView;
    MovieDetailView detailView;
    MenuBarView menuBar;
    SearchBarView searchBarView;
    StatusBarView statusBarView;
    SearchBarModel searchBarModel;
    SearchBarController searchBarController;
    MovieTableController movieTableController;
    double x;
    double y;


    public MainWindowView(MovieTableView tableView, MovieDetailView detailView, MenuBarView menuBar, StatusBarView statusBarView,MovieTableController movieTableController) {
        this.tableView = tableView;
        this.detailView = detailView;
        this.menuBar = menuBar;
        this.statusBarView = statusBarView;
        this.movieTableController = movieTableController;
    }

    public void start(Stage primaryStage) {
        prepScene();
        prepMainStage(primaryStage);
        addXYListeners();
        mainStage.show();
    }

    private void prepMainStage(Stage primaryStage) {
        mainStage = primaryStage;
        mainStage.setTitle("VKLX MovieBase");
        prepDimensions();
        mainStage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream("Logo.png")));
        mainStage.setScene(mainScene);
    }

    private void addXYListeners() {
        mainStage.xProperty().addListener((obs, oldVal, newVal) -> {
            x = newVal.doubleValue();
            System.out.println(x + ", " + y);
        });

        mainStage.yProperty().addListener((obs, oldVal, newVal) -> {
            y = newVal.doubleValue();
        });
    }

    private void prepDimensions() {
        mainStage.setHeight(800);
        mainStage.setMinHeight(300);
        mainStage.setWidth(1200);
        mainStage.setMaxWidth(2000);
        mainStage.setMinWidth(1200);
    }

    private void prepScene() {
        mainPane = new BorderPane();

        HBox topBar = new HBox();
        HBox spacer = new HBox();
        spacer.setMinWidth(81);
        spacer.setMaxHeight(10);
        spacer.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));

        makeSearchBar();
        topBar.getChildren().addAll(menuBar, spacer, searchBarView);


        mainPane.setTop(topBar);
        mainPane.setLeft(detailView);
        mainPane.setCenter(tableView);
        mainPane.setBottom(statusBarView);
        detailView.setVisible(true);
        mainScene = new Scene(mainPane);
    }

    private void makeSearchBar() {
        searchBarView = new SearchBarView(tableView, movieTableController);
        searchBarModel = new SearchBarModel();
        searchBarController = new SearchBarController(searchBarModel, searchBarView, movieTableController);
    }

    //public void ma

}
