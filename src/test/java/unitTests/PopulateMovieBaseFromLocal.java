package unitTests;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;
import static org.mockito.Mockito.*;

import movieControl.MovieBase;
import parsers.LocalParser;

public class PopulateMovieBaseFromLocal {

	@Test
	public void test() {
		//MovieBase base = MovieBase.getInstance();
		MovieBase base = mock(MovieBase.class);
		
		LocalParser parser = new LocalParser();
		try {
			parser.getParsedDoc("C:\\Program Files\\Sinnerman Software\\Movie Base" + "\\movieData.xml");
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

		Assert.assertEquals(movieOne, "Code 11-14");
		Assert.assertEquals(movieTwo, "12 Angry Men");
		Assert.assertEquals(movieThree, "12 Monkeys");
	}

}