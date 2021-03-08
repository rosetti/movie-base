package inputOutput;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class generates Search Query used to populate MovieTable
 */
public class DBSearchQuery {

    private static DBSearchQuery dbSearchQuery;
    private String titleSearchText = "%";
    private boolean watched = false;
    private boolean unwatched = false;
    private String actorSearchText = "%";
    private String directorSearchText = "%";
    private String writerSearchText = "%";
    private List<String> genreSearchList = new ArrayList<String>();
    private List<String> fileTypeSearchList = new ArrayList<String>();
    private float imdbLowerBound = 0;
    private float imdbUpperBound = 10;
    private int metaScoreLowerBound, metaScoreUpperBound, yearLowerBound, yearUpperBound, runtimeLowerBound, runtimeUpperBound;

    public static DBSearchQuery getInstance() {
        if (dbSearchQuery == null) {
            dbSearchQuery = new DBSearchQuery();
        }
        return dbSearchQuery;
    }

    public void resetQuery() {
        titleSearchText = "%";
        watched = false;
        unwatched = false;
        actorSearchText = "%";
        directorSearchText = "%";
        writerSearchText = "%";
        genreSearchList = new ArrayList<String>();
        fileTypeSearchList = new ArrayList<String>();
        imdbLowerBound = 0;
        imdbUpperBound = 10;
        metaScoreLowerBound = 0;
        metaScoreUpperBound = 100;
        yearLowerBound = 0;
        yearUpperBound = 3000;
        runtimeLowerBound = 0;
        runtimeUpperBound = 3000;
    }

    private void printParameters() {
        System.out.println("titleSearchText: " + titleSearchText);
        System.out.println("watched: " + watched);
        System.out.println("unwatched: " + unwatched);
    }

    /**
     * Returns PreparedStatement for advanced search
     * @param connection
     * @return
     * @throws SQLException
     */
    public PreparedStatement getPreparedStatementAdvanced(Connection connection) throws SQLException {
        printParameters();
        int parNum = 1;

        if (genreSearchList == null) {
            genreSearchList = new ArrayList<>();
        }

        if (fileTypeSearchList == null) {
            fileTypeSearchList = new ArrayList<>();
        }

        String sql = "SELECT * FROM MOVIES WHERE";
        sql += " (WATCHED = ? or WATCHED != ?)"; //par 1, par 2
        sql += " AND LOWER(TITLE) LIKE ?"; //par 3
        sql += " AND LOWER(ACTOR) LIKE ?"; //par 4
        sql += " AND LOWER(DIRECTOR) LIKE ?"; //par 5
        sql += " AND LOWER(WRITER) LIKE ?"; //par 6
        sql += " AND IMDB_SCORE > ?"; //par 7
        sql += " AND IMDB_SCORE < ?"; //par 8
        sql += " AND META_SCORE > ?"; //par 9
        sql += " AND META_SCORE < ?"; //par 10
        sql += " AND YEAR > ?"; //par 11
        sql += " AND YEAR < ?"; //par 12
        sql += " AND RUNTIME > ?"; //par 13
        sql += " AND RUNTIME < ?"; //par 14

        for (String i: genreSearchList) {
            sql += " AND LOWER(GENRE) LIKE ?";
        }

        for (String i: fileTypeSearchList) {
            sql += " AND LOWER(FILE_TYPE) LIKE ?";
        }

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setBoolean(parNum++, watched); //par 1
        statement.setBoolean(parNum++, unwatched); //par 2
        statement.setString(parNum++, "%" + titleSearchText + "%"); //par 3
        statement.setString(parNum++, "%" + actorSearchText + "%"); //par 4
        statement.setString(parNum++, "%" + directorSearchText + "%"); //par 5
        statement.setString(parNum++, "%" + writerSearchText + "%"); //par 6
        statement.setFloat(parNum++, imdbLowerBound); //par 7
        statement.setFloat(parNum++, imdbUpperBound); //par 8
        statement.setInt(parNum++, metaScoreLowerBound); //par 9
        statement.setInt(parNum++, metaScoreUpperBound); //par 10
        statement.setInt(parNum++, yearLowerBound); //par 11
        statement.setInt(parNum++, yearUpperBound); //par 12
        statement.setInt(parNum++, runtimeLowerBound); //par 13
        statement.setInt(parNum++, runtimeUpperBound); //par 14

        for (String i: genreSearchList) {
            statement.setString(parNum++, "%" + i + "%");
        }

        for (String i: fileTypeSearchList) {
            statement.setString(parNum++, "%" + i + "%");
        }

        System.out.println("Advanced SQL: " + sql);
        return statement;
    }

    /**
     * Returns PreparedStatement for use in in search bar
     * @param connection
     * @return
     * @throws SQLException
     */

    public PreparedStatement getPreparedStatementGeneral(Connection connection) throws SQLException {
        int parNum = 1;

        if (genreSearchList == null) {
            genreSearchList = new ArrayList<>();
        }

        if (fileTypeSearchList == null) {
            fileTypeSearchList = new ArrayList<>();
        }

        String sql = "SELECT * FROM MOVIES WHERE";
        sql += " (WATCHED = ? or WATCHED != ?) AND (";
        sql += "LOWER(TITLE) LIKE ?";
        sql += " OR LOWER(ACTOR) LIKE ?";
        sql += " OR LOWER(DIRECTOR) LIKE ?";
        sql += " OR LOWER(WRITER) LIKE ?";
        sql += ")";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setBoolean(parNum++, watched);
        statement.setBoolean(parNum++, unwatched);
        statement.setString(parNum++, "%" + titleSearchText + "%");
        statement.setString(parNum++, "%" + actorSearchText + "%");
        statement.setString(parNum++, "%" + directorSearchText + "%");
        statement.setString(parNum++, "%" + writerSearchText + "%");

        return statement;
    }

    public String getTitleSearchText() {
        return titleSearchText;
    }

    public void setTitleSearchText(String titleSearchText) {
        this.titleSearchText = titleSearchText;
    }

    public boolean isWatched() {
        return watched;
    }

    public void setWatched(boolean watched) {
        this.watched = watched;
    }

    public boolean isUnwatched() {
        return unwatched;
    }

    public void setUnwatched(boolean unwatched) {
        this.unwatched = unwatched;
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

    public List getGenreSearchList() {
        return genreSearchList;
    }

    public void setGenreSearchList(List genreSearchList) {
        this.genreSearchList = genreSearchList;
    }

    public float getImdbLowerBound() {
        return imdbLowerBound;
    }

    public void setImdbLowerBound(float imdbLowerBound) {
        this.imdbLowerBound = imdbLowerBound;
    }

    public float getImdbUpperBound() {
        return imdbUpperBound;
    }

    public void setImdbUpperBound(float imdbUpperBound) {
        this.imdbUpperBound = imdbUpperBound;
    }

    public void setMetaScoreLowerBound(int metaScoreLowerBound) {
        this.metaScoreLowerBound = metaScoreLowerBound;
    }

    public void setMetaScoreUpperBound(int metaScoreLowerBound) {
        this.metaScoreUpperBound = metaScoreLowerBound;
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

    public void setFileTypeSearchList (List fileTypeSearchList) {
        this.fileTypeSearchList = fileTypeSearchList;
    }

    public void setRuntimeLowerBound(int runtimeLowerBound) {
        this.runtimeLowerBound = runtimeLowerBound;
    }

    public void setRuntimeUpperBound(int runtimeUpperBound) {
        this.runtimeUpperBound = runtimeUpperBound;
    }
}
