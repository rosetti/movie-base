package javafxgui;

import inputOutput.SQLiteDatabase;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;


public class MenuBarView extends MenuBar {

    MenuBarView mainMenuBar;

    Menu fileMenu;
    Menu libraryMenu;
    MenuItem addMovie;
    Menu viewMenu;
    Menu aboutMenu;

    public MenuBarView getMenuBar() {
        if (this.mainMenuBar == null) {
            mainMenuBar = new MenuBarView();
            mainMenuBar.setMinWidth(500);
            //mainMenuBar.setStyle("-fx-background-color:f4f4f4");
            //mainMenuBar.setBackground(new Background(new BackgroundFill(Color.rgb(244,244,244), CornerRadii.EMPTY, Insets.EMPTY)));
        }
        return mainMenuBar;
    }

    public MenuBarView() {
        makeFileMenu();
        makeLibraryMenu();
        makeViewMenu();
        makeAboutMenu();
        addMenus();
    }

    private void addMenus() {
        getMenus().addAll(fileMenu, libraryMenu, viewMenu, aboutMenu);
    }

    private void makeFileMenu() {
        fileMenu = new Menu("_File");
        //fileMenu.setStyle("-fx-background-color:f4f4f4");

        MenuItem addMovies = new MenuItem("Add Movies");
        addMovies.setOnAction(e -> showImportWindow());

        MenuItem clearMovieBaseData = new MenuItem("Clear MovieBase Data");
        clearMovieBaseData.setOnAction(e -> SQLiteDatabase.getInstance().clearDatabase());

        MenuItem exit = new MenuItem("Exit");
        exit.setOnAction(e -> System.exit(0));

        fileMenu.getItems().addAll(addMovies, clearMovieBaseData, new SeparatorMenuItem(), exit);
    }

    private void makeLibraryMenu() {
        libraryMenu = new Menu("_Library");

        addMovie = new MenuItem("Add Movie");

        libraryMenu.getItems().addAll(addMovie);
    }

    public void setAddMovieAction(EventHandler handler) {
        addMovie.setOnAction(handler);
    }

    private void makeViewMenu() {
        viewMenu = new Menu("_View");
    }

    private void showImportWindow() {

        ImportView view = new ImportView();
        ImportModel model = new ImportModel();

        ImportController controller = new ImportController(view, model);
        controller.show();
    }

    private void makeAboutMenu() {
        aboutMenu = new Menu("_About");
    }

}
