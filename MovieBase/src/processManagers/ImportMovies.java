package processManagers;

//java imports
import java.io.File;
import java.util.ArrayList;
import org.w3c.dom.Document;

//local imports
import parsers.LocalParser;
import parsers.WebParser;
import main.SinSoftMovieAppMain;
import inputOutput.DirectoryReader;
import inputOutput.XMLWriter;
import movieControl.MovieBase;
import movieControl.Movie;
import resources.APIControl;


public class ImportMovies
{
	String inputPath;
	String outputPath;
	private int goodCount;
	private int badCount;
	ArrayList<Movie> moviesNotFound;
	MovieBase base;
	
	public ImportMovies(String inputPath)
	{
		this.inputPath = inputPath;
		outputPath = SinSoftMovieAppMain.pwd + SinSoftMovieAppMain.slash + "movieData.xml";
		
		base = new MovieBase();
		goodCount = 0;
		badCount = 0;
		moviesNotFound = new ArrayList<>();
	}
	
	public void importMoviesFromWeb()
	{
		DirectoryReader directoryReader = new DirectoryReader(inputPath);
		directoryReader.readDirectory();

		WebParser wParser = null;
		
		for (File i: directoryReader.getFileList())
		{
			String searchUrl = APIControl.getSearchTitle(directoryReader.cleanMovieName(i).getTitle());
			wParser = new WebParser(searchUrl);
			Movie retrievedMovie = directoryReader.cleanMovieName(i);

			if(wParser.isValidFetch())
			{
				base.addMovie(wParser.getWebMovieByTitle(retrievedMovie));
			}
			
			else 
			{
				System.out.println("Could not get info for " + retrievedMovie.getTitle());
				moviesNotFound.add(retrievedMovie);
				badCount++;
			}
			
			System.out.println(goodCount + ". " + "Successfully Read: " + retrievedMovie.getTitle());
			goodCount++;
			
			//limit testing runs to a low amount
			if (goodCount + badCount == 200)
			{
				break;
			}
		}
		
	}
	
	private void writeToXml()
	{
		XMLWriter writer = new XMLWriter();
		
		File output = new File(outputPath);
		
		if (!output.exists()) 
		{
			writer.makeBlankXMLFile(outputPath);
		}
		
		LocalParser lParser = new LocalParser();
		Document xmlImportDoc = lParser.getXMLDoc();
		
		for (Movie i: base.getMovieBase())
		{
			
			if (!lParser.isAlreadyInFileByTitle(i.getTitle()))
			{
				writer.addMovie(xmlImportDoc, i);
			}
			
		}
		
		writer.outputXML(xmlImportDoc, outputPath);
	}
	
	private void printSummary()
	{
		System.out.println("--------------------------------------------------");
		System.out.println("Import process Completed");
		System.out.println(goodCount + " Movies imported");
		System.out.println(badCount + " Movies failed");
		
		for (Movie i: moviesNotFound)
		{
			System.out.println(i.getTitle());
		}
	}
	
	public ArrayList<Movie> getMoviesNotFound()
	{
		return moviesNotFound;
	}
	
	
}
