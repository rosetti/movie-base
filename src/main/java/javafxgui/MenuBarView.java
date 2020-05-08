package javafxgui;

import inputOutput.SQLiteDatabase;
import javafx.event.EventHandler;
import javafx.scene.control.*;


public class MenuBarView extends MenuBar implements Confirmable{

    MenuBarView mainMenuBar;

    Menu fileMenu;
    Menu libraryMenu;
    MenuItem addMovie;
    Menu viewMenu;
    Menu aboutMenu;
    String menuFx = "";//"-fx-background-color:f4f4f4";

    boolean continueAction;

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
        setStyle("-fx-background-color:f4f4f4");

    }

    private void addMenus() {
        getMenus().addAll(fileMenu, libraryMenu, viewMenu, aboutMenu);
    }

    private void makeFileMenu() {
        fileMenu = new Menu("_File");
        fileMenu.setStyle(menuFx);

        MenuItem addMovies = new MenuItem("Add Movies");
        addMovies.setOnAction(e -> showImportWindow());

        MenuItem clearMovieBaseData = new MenuItem("Clear MovieBase Data");
        clearMovieBaseData.setOnAction(e -> {
            YesNoMessageBox.show("This action will clear your database of all movie data. " +
                    "\n Are you sure you wish to continue?","Warning");
            if (YesNoMessageBox.getResponse()) {
                System.out.println("Clearing Database");
                SQLiteDatabase.getInstance().clearDatabase();
            }
        });

        MenuItem exit = new MenuItem("Exit");
        exit.setOnAction(e -> System.exit(0));

        fileMenu.getItems().addAll(addMovies, clearMovieBaseData, new SeparatorMenuItem(), exit);
    }

    public void setval(boolean val) {
        continueAction = val;
    }
    private void makeLibraryMenu() {
        libraryMenu = new Menu("_Library");
        libraryMenu.setStyle(menuFx);
        addMovie = new MenuItem("Add Movie");

        libraryMenu.getItems().addAll(addMovie);
    }

    public void setAddMovieAction(EventHandler handler) {
        addMovie.setOnAction(handler);
    }

    private void makeViewMenu() {
        viewMenu = new Menu("_View");
        viewMenu.setStyle(menuFx);
    }

    private void showImportWindow() {

        ImportView view = new ImportView();
        ImportModel model = new ImportModel();

        ImportController controller = new ImportController(view, model);
        controller.show();
    }

    private void makeAboutMenu() {
        aboutMenu = new Menu("_About");
        aboutMenu.setStyle(menuFx);
    }

    @Override
    public void setContinue(boolean continueAction) {
        this.continueAction = continueAction;
    }
}
