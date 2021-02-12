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
        //sql += " AND IMDB_SCORE > ?"; //par 7
        //sql += " AND IMDB_SCORE < ?"; //par 8

        for (String i: genreSearchList) {
            sql += " AND LOWER(GENRE) LIKE ?";
        }

        for (String i: fileTypeSearchList) {
            sql += " AND LOWER(FILE_TYPE) LIKE ?";
        }

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setBoolean(parNum++, watched);
        statement.setBoolean(parNum++, unwatched);
        statement.setString(parNum++, "%" + titleSearchText + "%");
        statement.setString(parNum++, "%" + actorSearchText + "%");
        statement.setString(parNum++, "%" + directorSearchText + "%");
        statement.setString(parNum++, "%" + writerSearchText + "%");
        //statement.setFloat(parNum++, imdbLowerBound);
        //statement.setFloat(parNum++, imdbUpperBound);

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
}
