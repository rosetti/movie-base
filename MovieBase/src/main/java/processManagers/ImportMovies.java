package processManagers;

//java imports
import java.io.File;
import java.util.Iterator;
import java.util.ArrayList;
import org.w3c.dom.Document;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

//local imports
import parsers.LocalParser;
import parsers.WebParser;
import main.ApplicationMain;
import inputOutput.DirectoryReader;
import inputOutput.XMLWriter;
import movieControl.MovieBase;
import movieControl.Movie;
import resources.APIControl;
import resources.Logger;
import gui.ImportResults;


public class ImportMovies
{
	private String inputPath, outputPath;
	private int goodCount, badCount;
	ArrayList<Movie> moviesNotFound;
	ArrayList<Movie> base;
	JTextArea progressBox;
	JProgressBar progressBar;
	JLabel progressLabel;
	public volatile boolean stop = false;
	
	//constructor
	public ImportMovies(String inputPath, JTextArea progressBox, JProgressBar progressBar, JLabel progressLabel)
	{
		//TODO: Remove this Swing shit from this process. Decouple it you twit!
		this.inputPath = inputPath;
		this.progressBox = progressBox;
		this.progressBar = progressBar;
		this.progressLabel = progressLabel;
		outputPath = ApplicationMain.pwd + ApplicationMain.slash + "movieData.xml";
		base = new ArrayList<Movie>();;
		goodCount = 0;
		badCount = 0;
		moviesNotFound = new ArrayList<>();
		
	}
	
	public void readMoviesFromOmdb()
	{
		DirectoryReader directoryReader = new DirectoryReader(inputPath);
		directoryReader.readDirectory();

		WebParser wParser = null;
		int length = 0;
		
		
		if (new File(inputPath).isFile())
		{
			length = 1;
		}
		
		else
		{
			length = directoryReader.getFileList().length;
		}
		
		progressBar.setMaximum(length);

		for (File i: directoryReader.getFileList())
		{
			String searchUrl = APIControl.getSearchTitle(directoryReader.cleanMovieName(i).getTitle());
			wParser = new WebParser(searchUrl);
			Movie retrievedMovie = directoryReader.cleanMovieName(i);
			
			if(wParser.isValidFetch())
			{
				base.add(wParser.getWebMovieByTitle(retrievedMovie));
				goodCount++;
				String successfulOutput = (goodCount + badCount) + ". " + "Successfully Read: " + retrievedMovie.getTitle() + "\n"; 
				progressBox.append(successfulOutput);
			}
			
			else 
			{
				moviesNotFound.add(retrievedMovie);
				badCount++;
				String badOutput = (goodCount + badCount) + ". " + "Could not get info for " + retrievedMovie.getTitle() +"\n";
				progressBox.append(badOutput);
			}
			
			progressBar.setValue(goodCount + badCount);
			progressLabel.setText((goodCount + badCount) + " of " + length);
			
			
			
			if (stop)
			{
				JOptionPane.showMessageDialog(new JPanel(), "Import was Aborted!");
				break;
			}
			
			//limit testing runs to a low amount for debugging
			if (goodCount + badCount == -1)
			{
				break;
			}
			
		}
		
		Logger.logMessage("Import Finished: " + "Successfully Imported " + goodCount + " movies. \n" + "Failed to import badCount," + " movies");
		new ImportResults(goodCount, badCount);

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
		
		for (Movie i : base)
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
