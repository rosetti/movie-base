package main;

//java imports
import java.io.File;

//local imports
import movieControl.*;
import parsers.LocalParser;
import gui.*;
import javafx.application.Application;
import javafxgui.MainWindowView;

/**
 *
 */
public class ProgramLaunch {
    static LocalParser lParser;

    public ProgramLaunch() {

        if (isExistingLibrary()) {
            loadLibrary();
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
        Application.launch(MainWindowView.class);
    }

    public static void loadLibrary() {
        lParser = new LocalParser(ApplicationMain.pwd + ApplicationMain.slash + "movieData.xml");
        lParser.readMovies();
        lParser = null;
    }

    private boolean isExistingLibrary() {
        File library = new File(ApplicationMain.pwd + ApplicationMain.slash + "movieData.xml");
        return library.exists();
    }

}
