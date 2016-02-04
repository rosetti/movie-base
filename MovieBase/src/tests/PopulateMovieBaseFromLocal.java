package tests;

import static org.junit.Assert.*;

import java.io.IOException;

import movieControl.MovieBase;

import org.junit.Test;
import org.xml.sax.SAXException;

import parsers.LocalParser;

public class PopulateMovieBaseFromLocal {

	@Test
	public void test() 
	{
		MovieBase base = new MovieBase();
		LocalParser parser = new LocalParser();
		try {
			parser.getParsedDoc("C:\\Program Files\\Sinnerman Software\\Movie Base"+  "\\movieData.xml");
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		base.addMovies(parser.getMovies());
		
		String movieOne = base.getMovie(0).getTitle();
		String movieTwo = base.getMovie(1).getTitle();
		String movieThree = base.getMovie(2).getTitle();
		
		base.printMovies();
		
		assertEquals(movieOne,"Code 11-14");
		assertEquals(movieTwo,"12 Angry Men");
		assertEquals(movieThree,"12 Monkeys");
	}

}
