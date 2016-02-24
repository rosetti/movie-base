package main;

//java imports
import java.io.File;

//local imports
import movieControl.*;
import parsers.LocalParser;
import gui.*;

public class ProgramLaunch 
{
	LocalParser lParser;
	MovieBase coreBase;
	
	public ProgramLaunch()
	{
	
		coreBase = new MovieBase();
		
		if (isExistingLibrary())
		{
			lParser = new LocalParser(ApplicationMain.pwd + ApplicationMain.slash + "movieData.xml");
			coreBase = lParser.readMovies(coreBase);
			lParser = null;
		}
		
		initialiseMainWindow();
	}
	
	private void initialiseMainWindow()
	{
		MainWindow mainWindow = new MainWindow();
		mainWindow.addMoviesToPanel(coreBase.getMovieBase());
		mainWindow.refreshByResize();
	}
	
	private boolean isExistingLibrary()
	{
		File library = new File(ApplicationMain.pwd + ApplicationMain.slash + "movieData.xml");
		return library.exists();
	}
}
