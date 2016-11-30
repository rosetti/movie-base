package tests;

import static org.junit.Assert.*;

import org.junit.Assert;

import movieControl.Movie;
import movieControl.MovieBase;

import org.junit.Test;

import parsers.WebParser;

public class PopulateMovieBaseFromWeb {

	@Test
	public void test() 
	{	
		
		MovieBase base = new MovieBase();
		Movie webMovie = new WebParser("http://www.omdbapi.com/?t=italian+job&y=&plot=short&r=xml").getWebMovieByTitle(new Movie());
		base.addMovie(webMovie);
		base.printMovies();
		
		String webMovieTitleOne = base.getMovie(0).getTitle();
		Assert.assertEquals(webMovieTitleOne,"The Italian Job");
		
		webMovie = new WebParser("http://www.omdbapi.com/?t=the+avengers&y=&plot=short&r=xml").getWebMovieByTitle(new Movie());
		base.addMovie(webMovie);
		String webMovieTitleTwo = base.getMovie(1).getTitle();
		
		Assert.assertEquals(webMovieTitleTwo,"The Avengers");
	}

}
