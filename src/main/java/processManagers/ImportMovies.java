package processManagers;

//java imports
import java.io.File;
import java.util.Iterator;
import java.util.ArrayList;

import inputOutput.SQLiteDatabase;
import interfaces.ImportProgressInterface;
import javafx.concurrent.Task;
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


public class ImportMovies extends Task implements Runnable {

	private String inputPath, outputPath, outputSQLPath;
	private int goodCount, badCount;
	ArrayList<Movie> moviesNotFound;
	ArrayList<Movie> base;
	boolean rescan;
	//JTextArea progressBox;
	//JProgressBar progressBar;
	//JLabel progressLabel;
	ImportProgressInterface progressView;
	public volatile boolean stop = false;
	
	//constructor
	public ImportMovies(String inputPath, JTextArea progressBox, JProgressBar progressBar, JLabel progressLabel) {
		this.inputPath = inputPath;
		outputPath = ApplicationMain.pwd + ApplicationMain.slash + "movieData.xml";
		outputSQLPath = ApplicationMain.pwd + ApplicationMain.slash + "movieData.db";
		base = new ArrayList<Movie>();
		goodCount = 0;
		badCount = 0;
		moviesNotFound = new ArrayList<>();
	}

	public ImportMovies(ImportProgressInterface view, String inputPath) {
		progressView = view;
		this.inputPath = inputPath;
		outputPath = ApplicationMain.pwd + ApplicationMain.slash + "movieData.xml";
		base = new ArrayList<Movie>();
		goodCount = 0;
		badCount = 0;
		moviesNotFound = new ArrayList<>();
	}

	public void run() {
		System.out.println("Run Import");
		readMoviesFromOmdb();
		//writeResultsToXml();
		writeResultsToDB();
	}

	public void readMoviesFromOmdb() {
		DirectoryReader directoryReader = new DirectoryReader(inputPath);
		directoryReader.readDirectory(); //TODO: What if the directory is empty?

		WebParser wParser = null;
		int length = 0;
		
		
		if (new File(inputPath).isFile()) {
			length = 1;
		}
		else {
			length = directoryReader.getFileList().length;
		}

		for (File i: directoryReader.getFileList()) {

			if (!rescan) {
				if (i.getAbsolutePath().toLowerCase().contains("hot tub")) {
					int debug = 0;
				}
				if (SQLiteDatabase.getInstance().movieExistsInDB(i.getAbsolutePath())) {

					continue;
				}
			}

			String searchUrl = APIControl.getSearchTitle(directoryReader.cleanMovieName(i).getTitle());
			wParser = new WebParser(searchUrl);
			wParser.getParsedDoc(searchUrl);
			Movie retrievedMovie = directoryReader.cleanMovieName(i);

			if(wParser.isValidFetch()) {
				base.add(wParser.getWebMovieByTitle(retrievedMovie));
				goodCount++;
				String successfulOutput = (goodCount + badCount) + ". " + "Successfully Read: " + retrievedMovie.getTitle() + "\n";
				progressView.setProgressText(successfulOutput);
			} else {
				moviesNotFound.add(retrievedMovie);
				badCount++;
				String badOutput = (goodCount + badCount) + ". " + "Could not get info for " + retrievedMovie.getTitle() +"\n";
				progressView.setProgressText(badOutput);
			}
			progressView.setProgressValues(goodCount + badCount, length);


			if (stop) {
				JOptionPane.showMessageDialog(new JPanel(), "Import was Aborted!");
				break;
			}
			
			//limit testing runs to a low amount for debugging
//			if (goodCount + badCount == 200) {
////				break;
////			}
			
		}
		
		Logger.logMessage("Import Finished: " + "Successfully Imported " + goodCount + " movies. \n" + "Failed to import " + badCount + " movies");
		//new ImportResults(goodCount, badCount);

	}
	
	public void writeResultsToXml() {
		XMLWriter writer = new XMLWriter();
		
		File output = new File(outputPath);
		
		if (!output.exists()) {
			writer.makeBlankXMLFile(outputPath);
		}
		
		LocalParser lParser = new LocalParser();
		Document xmlImportDoc = lParser.getXMLDoc();
		
		for (Movie i : base) {
			if (!lParser.isAlreadyInFileByTitle(i.getTitle())) {
				writer.addMovie(xmlImportDoc, i);
			}
		}
		
		writer.outputXML(xmlImportDoc, outputPath);
	}

	public void writeResultsToDB() {
		SQLiteDatabase db = SQLiteDatabase.getInstance();
		for (Movie i : base) {
			db.addMovie(i);
		}
	}

	public ArrayList<Movie> getMoviesNotFound() {
		return moviesNotFound;
	}
	
	public void stop() {
		stop = true;
	}

	@Override
	protected Object call() throws Exception {
		System.out.println("Run Import");
		readMoviesFromOmdb();
		//writeResultsToXml();
		writeResultsToDB();
		return null;
	}
}
