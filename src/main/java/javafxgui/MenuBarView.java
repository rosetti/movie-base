package javafxgui;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;

public class MenuBarView extends MenuBar {

    MenuBarView mainMenuBar;

    Menu fileMenu;
    Menu libraryMenu;
    Menu viewMenu;
    Menu aboutMenu;

    public MenuBarView getMenuBar() {
        if (this.mainMenuBar == null) {
            mainMenuBar = new MenuBarView();
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

        MenuItem addMovies = new MenuItem("Add Movies");
        addMovies.setOnAction(e -> showImportWindow());

        MenuItem clearMovieBaseData = new MenuItem("Clear MovieBase Data");

        MenuItem exit = new MenuItem("Exit");
        exit.setOnAction(e -> System.exit(0));

        fileMenu.getItems().addAll(addMovies, clearMovieBaseData, new SeparatorMenuItem(), exit);
    }

    private void makeLibraryMenu() {
        libraryMenu = new Menu("_Library");
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
