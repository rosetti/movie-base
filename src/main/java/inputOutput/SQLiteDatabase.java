package inputOutput;

//Local Imports

import main.ApplicationMain;
import movieControl.Movie;
import movieControl.MovieBase;

//Java Imports
import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SQLiteDatabase {

    private static SQLiteDatabase coreBase;
    String url = "jdbc:sqlite:" + ApplicationMain.pwd + "\\" + ApplicationMain.dbName;
    Statement stmt;
    PreparedStatement pStmt;
    ResultSet results;

    public static SQLiteDatabase getInstance() {
        if (coreBase == null) {
            coreBase = new SQLiteDatabase();
        }
        return coreBase;
    }

    /**
     * Create SQLite movieData.db Database
     */
    public void createNewDatabase() {
        if (dbExists()) {
            System.out.println("DB Exists, not creating!");
            return;
        }

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
                Statement stmt = conn.createStatement();

                String sql = "CREATE TABLE MOVIES (\n" +
                        "    ID            INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                        "    TITLE         STRING,\n" +
                        "    YEAR          INT,\n" +
                        "    RATING        STRING,\n" +
                        "    RUNTIME       INT,\n" +
                        "    PLOT          STRING,\n" +
                        "    POSTER        STRING,\n" +
                        "    META_SCORE    INT,\n" +
                        "    IMDB_SCORE    DECIMAL,\n" +
                        "    IMDB_ID       STRING,\n" +
                        "    WATCHED       BOOLEAN,\n" +
                        "    FILE_LOCATION STRING,\n" +
                        "    FILE_TYPE     STRING,\n" +
                        "    ACTOR         STRING,\n" +
                        "    DIRECTOR      STRING,\n" +
                        "    GENRE         STRING,\n" +
                        "    WRITER        STRING\n" +
                        ");\n";

                stmt.execute(sql);
                conn.close();
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Check is DB already Exists
     *
     * @return
     */
    private boolean dbExists() {
        File db = new File(ApplicationMain.pwd + ApplicationMain.slash + ApplicationMain.dbName);
        return db.exists();
    }

    /**
     * Add a movie to the database
     * Method first checks if the movie already exists
     * TODO: If the movie already exists - perhaps it should change to an update query?
     *
     * @param movie
     */
    public void addMovie(Movie movie) {

        if (movieExistsInDB(movie)) {
            updateMovie(movie);
            return;
        }

        String sql = "INSERT INTO MOVIES (\n" +
                "                       TITLE,\n" +
                "                       YEAR,\n" +
                "                       RATING,\n" +
                "                       RUNTIME,\n" +
                "                       PLOT,\n" +
                "                       POSTER,\n" +
                "                       META_SCORE,\n" +
                "                       IMDB_SCORE,\n" +
                "                       IMDB_ID,\n" +
                "                       WATCHED,\n" +
                "                       FILE_LOCATION,\n" +
                "                       FILE_TYPE,\n" +
                "                       ACTOR,\n" +
                "                       DIRECTOR,\n" +
                "                       GENRE,\n" +
                "                       WRITER\n" +
                "                   )\n" +
                "                   VALUES (\n" +
                "                       ?,\n" +
                "                       ?,\n" +
                "                       ?,\n" +
                "                       ?,\n" +
                "                       ?,\n" +
                "                       ?,\n" +
                "                       ?,\n" +
                "                       ?,\n" +
                "                       ?,\n" +
                "                       ?,\n" +
                "                       ?,\n" +
                "                       ?,\n" +
                "                       ?,\n" +
                "                       ?,\n" +
                "                       ?,\n" +
                "                       ?\n" +
                "                   );\n";
        try {
            Connection conn = DriverManager.getConnection(url);
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, movie.getTitle());
            pStmt.setInt(2, movie.getYear());
            pStmt.setString(3, movie.getRating());
            pStmt.setInt(4, movie.getRuntime());
            pStmt.setString(5, movie.getPlot());
            pStmt.setString(6, movie.getPoster());
            pStmt.setInt(7, movie.getMetaScore());
            pStmt.setFloat(8, movie.getImdbScore());
            pStmt.setString(9, movie.getImdbId());
            pStmt.setBoolean(10, movie.isWatched());
            pStmt.setString(11, movie.getFileLocation());
            pStmt.setString(12, movie.getFileType());
            pStmt.setString(13, movie.getListAsString(movie.getActor()));
            pStmt.setString(14, movie.getListAsString(movie.getDirector()));
            pStmt.setString(15, movie.getListAsString(movie.getGenre()));
            pStmt.setString(16, movie.getListAsString(movie.getWriter()));
            pStmt.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }

    public void deleteMovie(Movie movie) {
        String sql = "DELETE FROM MOVIES WHERE IMDB_ID = ?";
        try {
            Connection conn = DriverManager.getConnection(url);
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, movie.getImdbId());
            pStmt.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }

    public void updateMovie(Movie movie) {
        String sql = "UPDATE MOVIES SET \n" +
                "                       TITLE = ?,\n" +
                "                       YEAR = ?,\n" +
                "                       RATING = ?,\n" +
                "                       RUNTIME = ?,\n" +
                "                       PLOT = ?,\n" +
                "                       POSTER = ?,\n" +
                "                       META_SCORE = ?,\n" +
                "                       IMDB_SCORE = ?,\n" +
                "                       WATCHED = ?,\n" +
                "                       FILE_LOCATION = ?,\n" +
                "                       FILE_TYPE = ?,\n" +
                "                       ACTOR = ?,\n" +
                "                       DIRECTOR = ?,\n" +
                "                       GENRE = ?,\n" +
                "                       WRITER = ?" +
                "WHERE IMDB_ID = ?";

        try {
            Connection conn = DriverManager.getConnection(url);
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, movie.getTitle());
            pStmt.setInt(2, movie.getYear());
            pStmt.setString(3, movie.getRating());
            pStmt.setInt(4, movie.getRuntime());
            pStmt.setString(5, movie.getPlot());
            pStmt.setString(6, movie.getPoster());
            pStmt.setInt(7, movie.getMetaScore());
            pStmt.setFloat(8, movie.getImdbScore());
            pStmt.setBoolean(9, movie.isWatched());
            pStmt.setString(10, movie.getFileLocation());
            pStmt.setString(11, movie.getFileType());
            pStmt.setString(12, movie.getListAsString(movie.getActor()));
            pStmt.setString(13, movie.getListAsString(movie.getDirector()));
            pStmt.setString(14, movie.getListAsString(movie.getGenre()));
            pStmt.setString(15, movie.getListAsString(movie.getWriter()));
            pStmt.setString(16, movie.getImdbId());
            pStmt.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Checks for the database to see if the movie already exists - based on the IMDB ID
     *
     * @param movie
     * @return
     */
    public boolean movieExistsInDB(Movie movie) {
        int count = 0;
        String sql = "SELECT COUNT(*) COUNT FROM MOVIES WHERE IMDB_ID LIKE ?";
        //get a connection
        try {
            Connection conn = DriverManager.getConnection(url);
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, movie.getImdbId());
            results = pStmt.executeQuery();
            while (results.next()) {
                count = results.getInt("COUNT");
            }

            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return count == 1;
    }

    public boolean movieExistsInDB(String filePath) {
        int count = 0;
        String sql = "SELECT COUNT(*) COUNT FROM MOVIES WHERE LOWER(FILE_LOCATION) LIKE ?";
        try {
            Connection conn = DriverManager.getConnection(url);
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, filePath.toLowerCase());
            results = pStmt.executeQuery();
            while (results.next()) {
                count = results.getInt("COUNT");
            }

            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return count > 0;
    }

    public void loadAllMovies() {
        MovieBase movieBase = MovieBase.getInstance();
        movieBase.clearMovieBase();
        Movie movie = new Movie();
        String sql = "SELECT * FROM MOVIES";
        try {
            Connection conn = DriverManager.getConnection(url);
            pStmt = conn.prepareStatement(sql);
            results = pStmt.executeQuery();
            loadMovieBase(results);
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }

    private void loadMovieBase(ResultSet results) throws SQLException{
        MovieBase movieBase = MovieBase.getInstance();
        movieBase.clearMovieBase();

        while (results.next()) {
            Movie movie = new Movie();
            movie.setTitle(results.getString("TITLE"));
            movie.setYear(results.getInt("YEAR"));
            movie.setRating(results.getString("RATING"));
            movie.setRuntime(results.getInt("RUNTIME"));
            movie.setPlot(results.getString("PLOT"));
            movie.setPoster(results.getString("POSTER"));
            movie.setMetaScore(results.getInt("META_SCORE"));
            movie.setImdbScore(results.getFloat("IMDB_SCORE"));
            movie.setImdbId(results.getString("IMDB_ID"));
            movie.setWatched(results.getBoolean("WATCHED"));
            movie.setFileLocation(results.getString("FILE_LOCATION"));
            movie.setFileType(results.getString("FILE_TYPE"));

            movie.setActor(new ArrayList<String>(Arrays.asList(results.getString("ACTOR").split("\\s*,\\s*"))));
            movie.setDirector(new ArrayList<String>(Arrays.asList(results.getString("DIRECTOR").split("\\s*,\\s*"))));
            movie.setGenre(new ArrayList<String>(Arrays.asList(results.getString("GENRE").split("\\s*,\\s*"))));
            movie.setWriter(new ArrayList<String>(Arrays.asList(results.getString("WRITER").split("\\s*,\\s*"))));

            movieBase.addMovie(movie);
        }
    }

    public void loadAdvancedFilteredMovies() {

        try {
            Connection conn = DriverManager.getConnection(url);

            //PreparedStatement pStatement = getPreparedStatement(conn, watched, unwatched, titleSearchText, actorSearchText, directorSearchText, writerSearchText, genreSearchList);
            PreparedStatement pStatement = DBSearchQuery.getInstance().getPreparedStatementAdvanced(conn);
            results = pStatement.executeQuery();
            loadMovieBase(results);
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void loadGeneralFilteredMovies() {
        try {
            Connection conn = DriverManager.getConnection(url);

            PreparedStatement pStatement = DBSearchQuery.getInstance().getPreparedStatementGeneral(conn);
            results = pStatement.executeQuery();
            loadMovieBase(results);
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Empty out all tables
     */
    public void clearDatabase() {
        String sql = "DELETE FROM MOVIES;";

        try {
            Connection conn = DriverManager.getConnection(url);
            pStmt = conn.prepareStatement(sql);
            pStmt.execute();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    private PreparedStatement getPreparedStatement(Connection connection, boolean watched, boolean unwatched,
                                                   String titleSearchText, String actorSearchText, String directorSearchText,
                                                   String writerSearchText, List<String> genreSearchList
                                                   //, ArrayList<String> genreList
    ) throws SQLException {

        int parNum = 1;
        if (genreSearchList == null) {
            genreSearchList = new ArrayList<>();
        }
        System.out.println("watched: " + watched + " unwatched: " + unwatched);
        System.out.println("title: " + titleSearchText);
        String sql = "SELECT * FROM MOVIES WHERE";
        sql += " (WATCHED = ? or WATCHED != ?)";
        sql += " AND LOWER(TITLE) LIKE ?";
        sql += " AND LOWER(ACTOR) LIKE ?";
        sql += " AND LOWER(DIRECTOR) LIKE ?";
        sql += " AND LOWER(WRITER) LIKE ?";

        for (String i: genreSearchList) {
            sql += " AND LOWER(GENRE) LIKE ?";
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

        System.out.println(sql);
        return statement;
    }

    public void toggleWatchedUnwatched(Movie movie) {
        String sql = "SELECT WATCHED FROM MOVIES WHERE LOWER(IMDB_ID) LIKE ?";
        try {
            Connection conn = DriverManager.getConnection(url);
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, movie.getImdbId() .toLowerCase());
            results = pStmt.executeQuery();
            boolean watched = results.getBoolean("WATCHED");

            sql = "UPDATE MOVIES SET WATCHED = ? WHERE LOWER(IMDB_ID) LIKE ? ";
            pStmt = conn.prepareStatement(sql);
            if (watched) {
                pStmt.setBoolean(1, false);
            } else {
                pStmt.setBoolean(1, true);
            }
            pStmt.setString(2, movie.getImdbId() .toLowerCase());
            pStmt.execute();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
