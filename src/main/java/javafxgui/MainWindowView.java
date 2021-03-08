package javafxgui;

//Java Imports

import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.*;

import java.awt.*;
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
    PosterGridView posterGridView;
    MovieDisplayPane movieDisplayPane;
    SearchBarModel searchBarModel;
    SearchBarController searchBarController;
    MovieTableController movieTableController;
    static double x;
    static double y;
    static double width;
    static double height;


    public MainWindowView(MovieTableView tableView, MovieDetailView detailView, MenuBarView menuBar, StatusBarView statusBarView, SearchBarView searchBarView, MovieDisplayPane movieDisplayPane, PosterGridView posterGridView) {
        this.tableView = tableView;
        this.detailView = detailView;
        this.menuBar = menuBar;
        this.statusBarView = statusBarView;
        this.searchBarView = searchBarView;
        this.posterGridView = posterGridView;
        this.movieDisplayPane = movieDisplayPane;
    }

    public void start(Stage primaryStage) {
        prepScene();
        prepMainStage(primaryStage);
        addXYListeners();
        addHeightWidthListeners();
        mainStage.show();
    }

    private void prepMainStage(Stage primaryStage) {
        mainStage = primaryStage;
        mainStage.setTitle("VKLX MovieBase");
        prepDimensions();
        getClass().getClassLoader().getResource("imdb-star-small.png").getFile();
        mainStage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream("Logo.png")));
        mainStage.setScene(mainScene);
    }

    private void addXYListeners() {
        mainStage.xProperty().addListener((obs, oldVal, newVal) -> {
            x = newVal.doubleValue();
        });

        mainStage.yProperty().addListener((obs, oldVal, newVal) -> {
            y = newVal.doubleValue();
        });
    }

    private void addHeightWidthListeners() {
        mainStage.widthProperty().addListener((Obs, oldVal, newVal) -> {
            width = newVal.doubleValue();
        });

        mainStage.heightProperty().addListener((Obs, oldVal, newVal) -> {
            height = newVal.doubleValue();
        });
    }

    private void prepDimensions() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double screenWidth = screenSize.getWidth();
        double screenHeight = screenSize.getHeight();

        if (screenWidth > 1300) {
            mainStage.setHeight(900);
            mainStage.setWidth(1400);
        } else {
            mainStage.setHeight(700);
            mainStage.setWidth(1200);
        }



        mainStage.setMinHeight(300);
        mainStage.setMinWidth(1200);

        mainStage.setMaxWidth(2000);
        width = 1200;
        height = 800;
    }

    private void prepScene() {
        mainPane = new BorderPane();

        HBox topBar = new HBox();
        topBar.setMinHeight(32);
        HBox spacer = new HBox();
        spacer.setMinWidth(100);
        spacer.setMaxHeight(10);
        spacer.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));

        topBar.getChildren().addAll(menuBar,spacer, searchBarView);
        mainPane.setTop(topBar);
        mainPane.setLeft(detailView);
        mainPane.setCenter(movieDisplayPane);
        mainPane.setBottom(statusBarView);
        detailView.setVisible(true);
        mainScene = new Scene(mainPane);
    }

    public void changeView() {
        mainPane.setCenter(posterGridView);
        posterGridView.show();
    }

}
