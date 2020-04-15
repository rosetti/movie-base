package javafxgui;

import inputOutput.SQLiteDatabase;

import java.util.List;

public class AdvancedSearchModel {

    boolean watched;
    boolean unwatched;
    String titleSearchText;
    String actorSearchText;
    String directorSearchText;
    String writerSearchText;
    List<String> genreSearchList;

    public AdvancedSearchModel() {

    }

    public void search() {
        System.out.println("Advanced Search Baby!");
        SQLiteDatabase db = SQLiteDatabase.getInstance();
        db.loadFilteredMovies(titleSearchText, watched, unwatched, actorSearchText, directorSearchText, writerSearchText, genreSearchList);
    }

    public void setWatched(boolean watched) {
        this.watched = watched;
    }

    public void setUnwatched(boolean unwatched) {
        this.unwatched = unwatched;
    }

    public boolean isUnwatched() {
        return unwatched;
    }

    public boolean isWatched() {
        return watched;
    }

    public void setGenreSearchList(List<String> genreSearchList) {
        this.genreSearchList = genreSearchList;
    }
}
