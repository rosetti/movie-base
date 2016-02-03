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
	
	public ImportMovies(String inputPath)
	{
		this.inputPath = inputPath;
		outputPath = SinSoftMovieAppMain.pwd + "\\movieData.xml";
	}
	
	public void importMovies()
	{
		DirectoryReader directoryReader = new DirectoryReader(inputPath);
		directoryReader.readDirectory();

		WebParser wParser = null;

		MovieBase base = new MovieBase();
		int count = 0;
		for (File i: directoryReader.getFileList())
		{
			String searchUrl = APIControl.getSearchTitle(directoryReader.cleanMovieName(i).getTitle());
			wParser = new WebParser(searchUrl);
			ArrayList<Movie> moviesNotFound = new ArrayList<>();
			Movie retrievedMovie = directoryReader.cleanMovieName(i);
			

			if(wParser.isValidFetch())
			{
				base.addMovie(wParser.getWebMovieByTitle(retrievedMovie));
			}
			
			else 
			{
				System.out.println("Could not get info for " + retrievedMovie.getTitle());
				moviesNotFound.add(retrievedMovie);
			}
			
			count++;
			
			if (count ==10)
			{
				break;
			}
		}
		
		XMLWriter writer = new XMLWriter();
		
		
		
		File output = new File(outputPath);
		
		if (!output.exists()) 
		{
			writer.makeBlankXMLFile(outputPath);
		}
		
		LocalParser lParser = new LocalParser(outputPath);
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
	
	
}
