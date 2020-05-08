package javafxgui;

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
    private String titleSearchText;
    private boolean watched;
    private boolean unwatched;
    private String actorSearchText;
    private String directorSearchText;
    private String writerSearchText;
    private List<String> genreSearchList;
    private List<String> fileTypeSearchList;

    public static DBSearchQuery getInstance() {
        if (dbSearchQuery == null) {
            dbSearchQuery = new DBSearchQuery();
        }
        return dbSearchQuery;
    }

    public void resetQuery() {
        titleSearchText = "";
        boolean watched = false;
        boolean unwatched = false;
        String actorSearchText = "";
        String directorSearchText = "";
        String writerSearchText = "";
        List<String> genreSearchList = new ArrayList<String>();
        List<String> fileTypeSearchList = new ArrayList<String>();
    }

    public PreparedStatement getPreparedStatement(Connection connection) throws SQLException {

        int parNum = 1;

        if (genreSearchList == null) {
            genreSearchList = new ArrayList<>();
        }

        if (fileTypeSearchList == null) {
            fileTypeSearchList = new ArrayList<>();
        }

        String sql = "SELECT * FROM MOVIES WHERE";
        sql += " (WATCHED = ? or WATCHED != ?)";
        sql += " AND LOWER(TITLE) LIKE ?";
        sql += " AND LOWER(ACTOR) LIKE ?";
        sql += " AND LOWER(DIRECTOR) LIKE ?";
        sql += " AND LOWER(WRITER) LIKE ?";

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

        for (String i: genreSearchList) {
            statement.setString(parNum++, "%" + i + "%");
        }

        for (String i: fileTypeSearchList) {
            statement.setString(parNum++, "%" + i + "%");
        }

        System.out.println(sql);
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
}
