package javafxgui;

import movieControl.Movie;
import parsers.WebParser;
import parsers.XMLparser;
import resources.APIControl;

public class MovieEditModel {

    Movie currentMovie;
    WebParser webParser;
    Movie retrievedMovie;

    public MovieEditModel() {

    }

    public void setCurrentMovie(Movie movie) {
        currentMovie = movie;
    }

    public Movie getCurrentMovie() {
        return currentMovie;
    }

    public void searchImdbByTitle(String searchText) {
        retrievedMovie = null;
        webParser = new WebParser(APIControl.getSearchTitle(searchText));
        webParser.getParsedDoc(APIControl.getSearchTitle(searchText));
        if (webParser.isValidFetch()) {
            retrievedMovie = new Movie();
            retrievedMovie = webParser.getWebMovieByTitle(retrievedMovie);
        };
    }

    public void searchImdbByImdbId(String searchText) {
        retrievedMovie = null;
        webParser = new WebParser(APIControl.getSearchImdbId(searchText));
        webParser.getParsedDoc(APIControl.getSearchImdbId(searchText));
        if (webParser.isValidFetch()) {
            retrievedMovie = new Movie();
            retrievedMovie = webParser.getWebMovieByTitle(retrievedMovie);
        };
    }

    public Movie getRetrievedMovie() {
        return retrievedMovie;
    }


}
