package parsers;

import java.util.ArrayList;
import java.util.Iterator;

import main.ApplicationMain;
import movieControl.Movie;
import movieControl.MovieBase;

import org.w3c.dom.Document;

public class LocalParser extends XMLparser {
    public LocalParser() {
        super(ApplicationMain.pwd + ApplicationMain.slash + "movieData.xml");
    }

    public LocalParser(String uri) {
        super(uri);
    }

    private Movie getLocalMovie(Document xmlDoc, int id) {

        movieElement = getElement(id, "movie");

        //get content for
        String title = movieElement.getElementsByTagName("title").item(0).getTextContent();
        int year = Integer.valueOf(movieElement.getElementsByTagName("year").item(0).getTextContent());
        String rating = movieElement.getElementsByTagName("rating").item(0).getTextContent();
        int runTime = Integer.valueOf(movieElement.getElementsByTagName("runtime").item(0).getTextContent());

        ArrayList<String> subGenreList = getTagList(movieElement, "subGenre");
        ArrayList<String> subDirectorList = getTagList(movieElement, "subDirector");
        ArrayList<String> subWriterList = getTagList(movieElement, "subWriter");
        ArrayList<String> subActorList = getTagList(movieElement, "subActor");

        String plot = movieElement.getElementsByTagName("plot").item(0).getTextContent();
        ArrayList<String> subCountryList = getTagList(movieElement, "subCountry");
        ;
        String poster = movieElement.getElementsByTagName("poster").item(0).getTextContent();
        int metaScore = Integer.valueOf(movieElement.getElementsByTagName("metaScore").item(0).getTextContent());
        float imdbScore = Float.valueOf(movieElement.getElementsByTagName("imdbScore").item(0).getTextContent());
        String imdbId = movieElement.getElementsByTagName("imdbId").item(0).getTextContent();
        boolean watched = Boolean.valueOf(movieElement.getElementsByTagName("watched").item(0).getTextContent());
        String fileType = movieElement.getElementsByTagName("filetype").item(0).getTextContent();
        String fileLocation = movieElement.getElementsByTagName("fileLocation").item(0).getTextContent();

        Movie returnMovie = new Movie(title, year, rating, runTime, subGenreList, subDirectorList, subWriterList, subActorList, plot, subCountryList, poster, metaScore, imdbScore, imdbId, watched, "", "");

        returnMovie.setFileType(fileType);
        returnMovie.setFileLocation(fileLocation);

        return returnMovie;
    }

    public ArrayList<Movie> getMovies() {
        ArrayList<Movie> movies = new ArrayList<>();
        int length = xmlDoc.getElementsByTagName("movie").getLength();
        int i = 0;
        while (i <= length - 1) {
            movies.add(getLocalMovie(xmlDoc, i));
            i++;
        }

        return movies;

    }

    public boolean isAlreadyInFileByTitle(String title) {
        ArrayList<Movie> xmlMovies = getMovies();
        Iterator<Movie> iter = xmlMovies.iterator();

        while (iter.hasNext()) {
            if (iter.next().getTitle().equals(title)) {
                return true;
            }

        }

        return false;
    }

    public void readMovies() {
        //MovieBase.getInstance().addMovies(getMovies());
    }

}
