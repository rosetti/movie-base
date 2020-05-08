package main;

//java imports
import java.io.File;

//local imports
import inputOutput.SQLiteDatabase;
import movieControl.*;
import parsers.LocalParser;
import gui.*;
import javafx.application.Application;

/**
 *
 */
public class ProgramLaunch {
    static LocalParser lParser;

    public ProgramLaunch() {

        if (isExistingLibrary()) {
            //loadLibrary();
        }
        if (isExistingSQLiteDB()) {
            loadLibraryFromDb();
         }
        //initialiseMainWindow();
        startJavaFXGui();
    }

    /**
     * Method initialises Swing based GUI
     */
    private void initialiseMainWindow() {
        MainWindow mainWindow = new MainWindow();
        mainWindow.addMoviesToPanel(MovieBase.getInstance().getIterator());
        mainWindow.showMainWindow();

    }

    /**
     * Method initialises JavaFX Based GUI
     */
    private void startJavaFXGui() {
        //Application.launch(MainWindowController.class);
        //Application.launch(MainWindowView.class);
        Application.launch(MainUIController.class);
    }

    public static void loadLibrary() {
        System.out.println("Loading library");
        lParser = new LocalParser(ApplicationMain.pwd + ApplicationMain.slash + "movieData.xml");
        lParser.readMovies();
        lParser = null;
    }

    private boolean isExistingLibrary() {
        File library = new File(ApplicationMain.pwd + ApplicationMain.slash + "movieData.xml");
        return library.exists();
    }

    private boolean isExistingSQLiteDB() {
        File library = new File(ApplicationMain.pwd + ApplicationMain.slash + "movieData.db");
        return library.exists();
    }

    private void loadLibraryFromDb(){
        SQLiteDatabase db = SQLiteDatabase.getInstance();
        db.loadAllMovies();
    }
}
