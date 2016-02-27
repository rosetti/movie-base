package main;

//java imports
import java.io.File;

//local imports
import movieControl.*;
import parsers.LocalParser;
import gui.*;

public class ProgramLaunch 
{
	static LocalParser lParser;
	static MovieBase coreBase;
	
	public ProgramLaunch()
	{
	
		coreBase = new MovieBase();
		
		if (isExistingLibrary())
		{
			loadLibrary();
		}
		
		initialiseMainWindow();
	}
	
	private void initialiseMainWindow()
	{
		MainWindow mainWindow = new MainWindow();
		mainWindow.addMoviesToPanel(coreBase.getMovieBase());
		mainWindow.refreshByResize();
	}
	
	public static MovieBase getCoreBase()
	{
		return coreBase;
	}
	
	public static void setCoreBase(MovieBase base)
	{
		coreBase = base;
	}
	
	public static void loadLibrary()
	{
		lParser = new LocalParser(ApplicationMain.pwd + ApplicationMain.slash + "movieData.xml");
		coreBase = lParser.readMovies(coreBase);
		lParser = null;
	}
	
	private boolean isExistingLibrary()
	{
		File library = new File(ApplicationMain.pwd + ApplicationMain.slash + "movieData.xml");
		return library.exists();
	}
}
