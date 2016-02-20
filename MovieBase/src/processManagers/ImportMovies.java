package processManagers;

//java imports
import java.io.File;
import java.util.ArrayList;
import org.w3c.dom.Document;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.JLabel;

//local imports
import parsers.LocalParser;
import parsers.WebParser;
import main.ApplicationMain;
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
	JTextArea progressBox;
	JProgressBar progressBar;
	JLabel progressLabel;
	public volatile boolean stop;
	
	//constructor
	public ImportMovies(String inputPath, JTextArea progressBox, JProgressBar progressBar, JLabel progressLabel)
	{
		this.inputPath = inputPath;
		this.progressBox = progressBox;
		this.progressBar = progressBar;
		this.progressLabel = progressLabel;
		outputPath = ApplicationMain.pwd + ApplicationMain.slash + "movieData.xml";
		base = new MovieBase();
		goodCount = 0;
		badCount = 0;
		moviesNotFound = new ArrayList<>();
		
	}
	
	public void readMoviesFromOmdb()
	{
		DirectoryReader directoryReader = new DirectoryReader(inputPath);
		directoryReader.readDirectory();

		WebParser wParser = null;
		
		int length = directoryReader.getFileList().length;
		
		progressBar.setMaximum(length);
		
		for (File i: directoryReader.getFileList())
		{
			String searchUrl = APIControl.getSearchTitle(directoryReader.cleanMovieName(i).getTitle());
			wParser = new WebParser(searchUrl);
			Movie retrievedMovie = directoryReader.cleanMovieName(i);
			stop = false;
			
			if(wParser.isValidFetch())
			{
				base.addMovie(wParser.getWebMovieByTitle(retrievedMovie));
				goodCount++;
				String successfulOutput = (goodCount + badCount) + ". " + "Successfully Read: " + retrievedMovie.getTitle() + "\n"; 
				progressBox.append(successfulOutput);
			}
			
			else 
			{
				String badOutput = (goodCount + badCount) + ". " + "Could not get info for " + retrievedMovie.getTitle() +"\n";
				progressBox.append(badOutput);
				moviesNotFound.add(retrievedMovie);
				badCount++;
			}
			
			progressBar.setValue(goodCount + badCount);
			progressLabel.setText((goodCount + badCount) + " of " + length);
			
			//limit testing runs to a low amount
			
			if (stop)
			{
				break;
			}
		}
		
	}
	
	public void writeResultsToXml()
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
	
	public ArrayList<Movie> getMoviesNotFound()
	{
		return moviesNotFound;
	}
	
	public void stop()
	{
		stop = true;
	}
	
	
}
