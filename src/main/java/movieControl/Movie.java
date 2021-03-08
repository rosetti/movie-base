package movieControl;

import java.util.ArrayList;


/**
 * This class defines a movie object.
 * Movie objects are only created to hold movies during runtime.
 *
 * @author vklx
 */
public class Movie {
    String title = "";
    int year = -1;
    String rating = "";
    int runtime = -1;
    ArrayList<String> genre = new ArrayList<>();
    ArrayList<String> director = new ArrayList<>();
    ArrayList<String> writer = new ArrayList<>();
    ArrayList<String> actor = new ArrayList<>();

    String plot = "";
    ArrayList<String> country = new ArrayList<>();
    String poster = "";
    int metaScore = -1;
    float imdbScore = -1;
    String imdbId = "";

    boolean watched = false;
    String watchedString;
    String fileLocation = "";
    String fileType = "";

    //constructor

    public Movie(String title, int year, String rating, int runtime, ArrayList<String> genre, ArrayList<String> director,
                 ArrayList<String> writer, ArrayList<String> actor, String plot, ArrayList<String> country, String poster,
                 int metaScore, float imdbScore, String imdbId, boolean watched, String fileLocation, String fileType) {
        this.title = title;
        this.year = year;
        this.rating = rating;
        this.runtime = runtime;
        this.genre = genre;
        this.director = director;
        this.writer = writer;
        this.actor = actor;
        this.plot = plot;
        this.country = country;
        this.poster = poster;
        this.metaScore = metaScore;
        this.imdbScore = imdbScore;
        this.imdbId = imdbId;
        this.watched = watched;
        this.fileLocation = fileLocation;
        this.fileType = fileType;
    }

    //Initialise all fields to something, to prevent any issues with displaying blank-ish Movie objects later.
    public Movie() {
        this.title = "";
        this.year = -1;
        this.rating = "";
        this.runtime = -1;
        this.genre = new ArrayList<String>();
        this.director = new ArrayList<String>();
        this.writer = new ArrayList<String>();
        this.actor = new ArrayList<String>();
        this.plot = "";
        this.country = new ArrayList<String>();
        this.poster = "";
        this.metaScore = -1;
        this.imdbScore = -1;
        this.imdbId = "";
        this.watched = false;
        this.fileLocation = "";
        this.fileType = "";
    }

    //print to console
    public void printMovie() {
        System.out.println("Title: " + title);
        System.out.println("Year: " + year);
        System.out.println("Rating: " + rating);
        System.out.println("Runtime: " + runtime);
        printArrayList(genre, "Genre");
        printArrayList(director, "Director");
        printArrayList(writer, "Writer");
        printArrayList(actor, "Actor");
        System.out.println("Plot: " + plot);
        printArrayList(country, "Country");
        System.out.println("Poster: " + poster);
        System.out.println("Metascore: " + metaScore);
        System.out.println("IMDb: " + imdbScore);
        System.out.println("IMDb ID: " + imdbId);
        System.out.println("File Path: " + fileLocation);
        System.out.println("File Type: " + fileType);
    }

    public void printArrayList(ArrayList<String> list, String titleString) {
        for (String i : list) {
            System.out.println(titleString + ": " + i);
        }
    }

    public static String getListAsString(ArrayList<String> list) {
        if (list.size() == 0) {
            return "";
        }
        String returnString = "";

        for (String i : list) {
            returnString += i + ", ";
        }

        return returnString.substring(0, returnString.length() - 2);
    }


    /* getters and setters */

    public String getFileLocation() {
        return fileLocation;
    }

    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public boolean isWatched() {
        return watched;
    }

    public void setWatched(boolean watched) {
        this.watched = watched;
        if (watched) {
            watchedString = "Watched";//"✔";
        } else {
            watchedString = "✘";
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public ArrayList<String> getGenre() {
        return genre;
    }

    public void setGenre(ArrayList<String> genre) {
        this.genre = genre;
    }

    public ArrayList<String> getDirector() {
        return director;
    }

    public void setDirector(ArrayList<String> director) {
        this.director = director;
    }

    public ArrayList<String> getWriter() {
        return writer;
    }

    public void setWriter(ArrayList<String> writer) {
        this.writer = writer;
    }

    public ArrayList<String> getActor() {
        return actor;
    }

    public void setActor(ArrayList<String> actor) {
        this.actor = actor;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public ArrayList<String> getCountry() {
        return country;
    }

    public void setCountry(ArrayList<String> country) {
        this.country = country;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public int getMetaScore() {
        return metaScore;
    }

    public void setMetaScore(int metaScore) {
        this.metaScore = metaScore;
    }

    public float getImdbScore() {
        return imdbScore;
    }

    public void setImdbScore(float imdbScore) {
        this.imdbScore = imdbScore;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

}
