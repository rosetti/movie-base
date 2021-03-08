package javafxgui;

import inputOutput.DBSearchQuery;
import inputOutput.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class AdvancedSearchModel {

    private String titleSearchText;
    private String actorSearchText;
    private String directorSearchText;
    private String writerSearchText;
    private List<String> genreSearchList, fileTypeSearchList;
    private float imdbScoreLowerBound, imdbScoreUpperBound;
    private int metascoreLowerBound, metascoreUpperBound, yearLowerBound, yearUpperBound, runtimeLowerBound, runtimeUpperBound;

    public void search() {
        DBSearchQuery dbSearchQuery = DBSearchQuery.getInstance();
        dbSearchQuery.resetQuery();
        dbSearchQuery.setTitleSearchText(titleSearchText);
        dbSearchQuery.setActorSearchText(actorSearchText);
        dbSearchQuery.setDirectorSearchText(directorSearchText);
        dbSearchQuery.setWriterSearchText(writerSearchText);
        dbSearchQuery.setGenreSearchList(genreSearchList);
        dbSearchQuery.setFileTypeSearchList(fileTypeSearchList);
        dbSearchQuery.setImdbLowerBound(imdbScoreLowerBound);
        dbSearchQuery.setImdbUpperBound(imdbScoreUpperBound);
        dbSearchQuery.setMetaScoreLowerBound(metascoreLowerBound);
        dbSearchQuery.setMetaScoreUpperBound(metascoreUpperBound);
        dbSearchQuery.setYearLowerBound(yearLowerBound);
        dbSearchQuery.setYearUpperBound(yearUpperBound);
        dbSearchQuery.setRuntimeLowerBound(runtimeLowerBound);
        dbSearchQuery.setRuntimeUpperBound(runtimeUpperBound);

        SQLiteDatabase db = SQLiteDatabase.getInstance();
        db.loadAdvancedFilteredMovies();
    }

    public void resetParameters() {
        titleSearchText = "%";
        actorSearchText = "%";
        directorSearchText = "%";
        writerSearchText = "%";
        genreSearchList = new ArrayList<String>();
        fileTypeSearchList = new ArrayList<String>();
        imdbScoreLowerBound = 0;
        imdbScoreUpperBound = 10;
        metascoreLowerBound = 0;
        metascoreUpperBound = 100;
        yearLowerBound = 0;
        yearUpperBound = 3000;
        runtimeLowerBound = 0;
        runtimeUpperBound = 3000;
    }

    /* Getters and Setters */
    public void setGenreSearchList(List<String> genreSearchList) {
        this.genreSearchList = genreSearchList;
    }

    public String getTitleSearchText() {
        return titleSearchText;
    }

    public void setTitleSearchText(String titleSearchText) {
        this.titleSearchText = titleSearchText;
    }

    public String getActorSearchText() {
        return actorSearchText;
    }

    public void setActorSearchText(String actorSearchText) {
        this.actorSearchText = actorSearchText;
    }

    public String getDirectorSearchText() {
        return directorSearchText;
    }

    public void setDirectorSearchText(String directorSearchText) {
        this.directorSearchText = directorSearchText;
    }

    public String getWriterSearchText() {
        return writerSearchText;
    }

    public void setWriterSearchText(String writerSearchText) {
        this.writerSearchText = writerSearchText;
    }

    public List<String> getGenreSearchList() {
        return genreSearchList;
    }

    public List<String> getFileTypeSearchList() {
        return fileTypeSearchList;
    }

    public void setFileTypeSearchList(List<String> fileTypeSearchList) {
        this.fileTypeSearchList = fileTypeSearchList;
    }

    public float getImdbScoreLowerBound() {
        return imdbScoreLowerBound;
    }

    public void setImdbScoreLowerBound(float imdbScoreLowerBound) {
        this.imdbScoreLowerBound = imdbScoreLowerBound;
    }

    public float getImdbScoreUpperBound() {
        return imdbScoreUpperBound;
    }

    public void setImdbScoreUpperBound(float imdbScoreUpperBound) {
        this.imdbScoreUpperBound = imdbScoreUpperBound;
    }

    public int getMetascoreLowerBound() {
        return metascoreLowerBound;
    }

    public void setMetascoreLowerBound(int metascoreLowerBound) {
        this.metascoreLowerBound = metascoreLowerBound;
    }

    public int getMetascoreUpperBound() {
        return metascoreUpperBound;
    }

    public void setMetascoreUpperBound(int metascoreUpperBound) {
        this.metascoreUpperBound = metascoreUpperBound;
    }

    public int getYearLowerBound() {
        return yearLowerBound;
    }

    public void setYearLowerBound(int yearLowerBound) {
        this.yearLowerBound = yearLowerBound;
    }

    public int getYearUpperBound() {
        return yearUpperBound;
    }

    public void setYearUpperBound(int yearUpperBound) {
        this.yearUpperBound = yearUpperBound;
    }

    public int getRuntimeLowerBound() {
        return runtimeLowerBound;
    }

    public void setRuntimeLowerBound(int runtimeLowerBound) {
        this.runtimeLowerBound = runtimeLowerBound;
    }

    public int getRuntimeUpperBound() {
        return runtimeUpperBound;
    }

    public void setRuntimeUpperBound(int runtimeUpperBound) {
        this.runtimeUpperBound = runtimeUpperBound;
    }
}
