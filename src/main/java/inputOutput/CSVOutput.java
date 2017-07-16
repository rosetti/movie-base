package inputOutput;

//java imports
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

//local imports
import resources.Logger;
import movieControl.MovieBase;
import movieControl.Movie;

public class CSVOutput {
	File outputFile;;
	FileWriter writer;

	public CSVOutput(String outputPath) {
		if (!outputPath.toUpperCase().endsWith(".CSV")) {
			outputPath += ".csv";
		}
		outputFile = new File(outputPath);
		try {
			writer = new FileWriter(outputFile);
		}

		catch (IOException e) {
			Logger.logMessage("Unable to create FileWriter");
			Logger.logMessage(e.getMessage());
		}
	}

	public void writeBase() {
		try {
			writer.write("Title\t" + "Year\t" + "Rating\t" + "Runtime\t" + "Genre\t" + "Director\t" + "Writer\t"
					+ "Actor," + "Plot\t" + "Country\t" + "Poster\t" + "MetaScore\t" + "IMDb Score\t" + "IMDb ID\t"
					+ "Watched\t" + "File Location\t" + "File Type\n");

			while (MovieBase.getInstance().getIterator().hasNext()) {
				Movie movie = MovieBase.getInstance().getIterator().next();

				writer.append(movie.getTitle() + "\t" + movie.getYear() + "\t" + movie.getRating() + "\t"
						+ movie.getRuntime() + "\t" + movie.getListAsString(movie.getGenre()) + "\t"
						+ movie.getListAsString(movie.getDirector()) + "\t" + movie.getListAsString(movie.getWriter())
						+ "\t" + movie.getListAsString(movie.getActor()) + "\t" + movie.getPlot() + "\t"
						+ movie.getListAsString(movie.getCountry()) + "\t" + movie.getPoster() + "\t"
						+ movie.getMetaScore() + "\t" + movie.getImdbScore() + "\t" + movie.getImdbId() + "\t"
						+ movie.isWatched() + "\t" + movie.getFileLocation() + "\t" + movie.getFileType() + "\n");
			}

			writer.flush();
			writer.close();
		}

		catch (IOException e) {
			Logger.logMessage("Unable to write to file");
			Logger.logMessage(e.getMessage());
		}

	}
}
