package processManagers;

//java imports
import java.io.File;
import java.util.ArrayList;
import org.w3c.dom.Document;

import gui.ImportResults;

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
	public volatile boolean stop = false;
	
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

		int DEBUGCOUNT = 0;
		for (File i: directoryReader.getFileList())
		{
			
			//DEBUGGING
			
			//if (DEBUGCOUNT < 225)
			//{
			//	DEBUGCOUNT++;
			//	continue;
			//}
			
			//i = directoryReader.getFileList()[225];
			//System.out.println("Adding: " + i);
			//DEBUGGING
			String searchUrl = APIControl.getSearchTitle(directoryReader.cleanMovieName(i).getTitle());
			wParser = new WebParser(searchUrl);
			Movie retrievedMovie = directoryReader.cleanMovieName(i);
			
			if(wParser.isValidFetch())
			{
				base.addMovie(wParser.getWebMovieByTitle(retrievedMovie));
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
			
			//limit testing runs to a low amount
			//For debugging
			if ((goodCount + badCount == -1))
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
