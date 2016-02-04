package main;
//java imports
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;


//local imports
import movieControl.Movie;
import movieControl.MovieBase;
import parsers.LocalParser;
import resources.ImageSaver;
import parsers.WebParser;
import processManagers.ImportMovies;
import inputOutput.DirectoryReader;
import gui.*;
import inputOutput.XMLWriter;
import resources.StaticTestObjects;

public class MovieTest
{

	public MovieTest()
	{
		//testInputReader();
		//testWebParserByTitle();
		//sidePanelTest();
		//moviePanelTest();
		
		moviePanelTestFromLocal();
		
		//testDirectoryReader();
		//xmlWriterTest();
		
		//ImportMovies importMovieProcess = new ImportMovies("K:\\Films");
		//importMovieProcess.importMovies();
		//new ImportMovies("K:\\films").importMovies();
		//testImageDownloader();
		
	}

	//retrieve list of files given the directory
	//print list to screen
	public void testInputReader()
	{
		DirectoryReader movieDirectory = new DirectoryReader("K:\\Films");
		movieDirectory.readDirectory();
		movieDirectory.printMovieList();
	}

	public void checkGetRuntime()
	{
		//int time = ReadMediaInfo.getRunTimeFromFile("K:\\Films\\300");
		//System.out.println(time);
	}
	
	public void testWebParserByTitle()
	{
		MovieBase base = new MovieBase();
		//WebParser parser = new WebParser("http://www.omdbapi.com/?t=The+Italian+Job&y=&plot=short&r=xml");
		//base.addMovie(parser.getWebMovieByTitle(new Movie()));
		/*
		try {
			parser.getParsedDoc("http://www.omdbapi.com/?t=Space+Jam&y=&plot=short&r=xml");
		} catch (SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		base.addMovie(parser.getWebMovieByTitle(new Movie()));
		base.printMovies();
		*/
	}
	
	private void testIsMovieInXML()
	{
		//LocalParser parser = new LocalParser("Y:\\Development\\SinSoftMovieApplication\\bin\\main\\movieData.xml");
		//Boolean isIn = parser.isAlreadyInFileByTitle("The Italkian Job");
		//System.out.println(isIn);
	}

	public void testDirectoryReader()
	{
		/*
		DirectoryReader reader = new DirectoryReader("K:\\films");
		reader.readDirectory();
		WebParser parser = null;
		
		MovieBase base = new MovieBase();
		int count = 0;
		for (File i: reader.getFileList())
		{
			String searchUrl = APIControl.getSearchTitle(reader.cleanMovieName(i).getTitle());
			parser = new WebParser(searchUrl);
			ArrayList<Movie> moviesNotFound = new ArrayList<>();
			Movie retrievedMovie = reader.cleanMovieName(i);
			
			if(parser.isValidFetch())
			{
				base.addMovie(parser.getWebMovieByTitle(retrievedMovie));
			}
			
			else 
			{
				System.out.println("Could not get info for " + retrievedMovie.getTitle());
				moviesNotFound.add(retrievedMovie);
			}
			
			count++;
			
			if (count==10)
			{
				base.printMovies();
				break;
			}
		}
		
		System.out.println("Test finished");
		
		*/
	}

	public void sidePanelTest()
	{
		MainWindow mainWindow = new MainWindow();
		//LocalParser lParser = new LocalParser("C:\\Program Files\\Sinnerman Software\\Movie Base\\movieData.xml");
		//MovieBase base = new MovieBase();
		//base.addMovies(lParser.getMovies());
		//base.printMovies();
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mainWindow.setSidePanel(StaticTestObjects.getTestMovie1());
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mainWindow.setSidePanel(StaticTestObjects.getTestMovie2());
		
		//mainWindow.setSidePanel(base.getMovie(1));
		//mainWindow.setSidePanel(base.getMovie(2));
	}
	
	public void xmlWriterTest()
	{
		XMLWriter writer = new XMLWriter();
		LocalParser lParser = new LocalParser();
		try {
			lParser.getParsedDoc("Y:\\Development\\SinSoftMovieApplication\\bin\\main\\movieData.xml");
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Movie movie = lParser.getMovies().get(0);
		Document doc = lParser.getXMLDoc();
		writer.addMovie(doc,movie);
	}
	
	public void testImageDownloader()
	{
		//System.out.println(ImageDownloader.getImage("http://i.stack.imgur.com/ILTQq.png", "Boogie Nights", 1986));
	}
	
	public void moviePanelTest()
	{
		ArrayList<Movie> movieList = new ArrayList<>();
		
		movieList.add(StaticTestObjects.getTestMovie1());
		movieList.add(StaticTestObjects.getTestMovie2());
		
		MainWindow mainWindow = new MainWindow();
		mainWindow.addMoviesToPanel(movieList);
	}
	
	public void moviePanelTestFromLocal()
	{
		LocalParser lParser = new LocalParser();
		MovieBase base = new MovieBase();
		base = lParser.readMovies(base);
		
		MainWindow mainWindow = new MainWindow();
		mainWindow.addMoviesToPanel(base.getMovieBase());
		
	}
}
