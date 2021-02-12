package unitTests;

import inputOutput.SQLiteDatabase;
import main.ApplicationMain;
import movieControl.Movie;
import movieControl.MovieBase;
//import org.junit.Assert;
import org.junit.*;
import org.junit.Test;
import parsers.LocalParser;
import resources.StaticTestObjects;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class TestSQLiteDatabase {

    //@Test
    public void movieExistsInDBTest3() {
        String[] args = {};
        ApplicationMain.setUserDirectory(args);
        SQLiteDatabase db = SQLiteDatabase.getInstance();
        Movie movie = mock(Movie.class, RETURNS_DEEP_STUBS);

        when(movie.getImdbId()).thenReturn("1234");
        Assert.assertEquals(true, db.movieExistsInDB(movie));

        when(movie.getImdbId()).thenReturn("1234fgdfg");
        Assert.assertEquals(false, db.movieExistsInDB(movie));
    }

    //@Test
    public void movieExistsInDBTest() {
        String[] args = {};
        ApplicationMain.setUserDirectory(args);
        SQLiteDatabase db = SQLiteDatabase.getInstance();
        Movie movie = mock(Movie.class, RETURNS_DEEP_STUBS);

        when(movie.getImdbId()).thenReturn("1234");
        Assert.assertEquals(true, db.movieExistsInDB(movie));

        when(movie.getImdbId()).thenReturn("1234fgdfg");
        Assert.assertEquals(false, db.movieExistsInDB(movie));
    }

    //@Test
    public void addMovieTest() {
        String[] args = {};
        ApplicationMain.setUserDirectory(args);
        SQLiteDatabase db = SQLiteDatabase.getInstance();
        Movie movie = StaticTestObjects.getTestMovie1();
        //db.addMovie(movie);

        Assert.assertEquals(true, db.movieExistsInDB(movie));
    }

}

