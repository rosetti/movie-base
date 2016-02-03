package resources;

//java imports
import java.util.ArrayList;


//local imports
import movieControl.Movie;

//This class contains a number of static test objects to be used in other areas of the system
public class StaticTestObjects 
{
	public static Movie getTestMovie1()
	{
		Movie movie = new Movie();
		
		movie.setTitle("12 Angry Men");
		movie.setYear(1957);
		movie.setRuntime(96);
		
		ArrayList<String> genre = new ArrayList<>();
		genre.add("Crime");
		genre.add("Drama");
		movie.setGenre(genre);
		
		ArrayList<String> director = new ArrayList<>();
		director.add("Sidney Lumet");
		movie.setDirector(director);
		
		ArrayList<String> writer = new ArrayList<>();
		writer.add("Reginald Rose (story)");
		movie.setWriter(writer);
		
		ArrayList<String> actor = new ArrayList<>();
		actor.add("Martin Balsam");
		actor.add("John Fiedler");
		actor.add("Lee J. Cobb");
		actor.add("E.G. Marshall");
		movie.setActor(actor);
		
		movie.setPlot("A dissenting juror in a murder trial slowly manages to convince the others that the case is not as obviously clear as it seemed in court.");
		
		ArrayList<String> country = new ArrayList<>();
		country.add("USA");
		movie.setCountry(country);
		
		movie.setPoster("C:\\Users\\Vin\\Documents\\temp\\12 Angry Men tt0050083.jpg");
		movie.setMetaScore(-1);
		movie.setImdbScore((float) 8.9);
		movie.setImdbId("tt0050083");
		movie.setFileType(".avi");
		movie.setFileLocation("K:\\films\\12 Angry Men.avi");
		
		return movie;
	}

	public static Movie getTestMovie2()
	{
		Movie movie = new Movie();
		
		movie.setTitle("12 Monkeys");
		movie.setYear(2015);
		movie.setRating("TV-14");
		movie.setRuntime(42);
		
		ArrayList<String> genre = new ArrayList<>();
		genre.add("Adventure");
		genre.add("Drama");
		genre.add("Mystery");
		movie.setGenre(genre);
		
		ArrayList<String> director = new ArrayList<>();
		director.add("N/A");
		movie.setDirector(director);
		
		ArrayList<String> writer = new ArrayList<>();
		writer.add("Terry Matalas");
		writer.add("Travis Fickett");
		movie.setWriter(writer);
		
		ArrayList<String> actor = new ArrayList<>();
		actor.add("Demore Barnes");
		actor.add("Aaron Stanford");
		actor.add("Amanda Schull");
		actor.add("Barbara Sukowa");
		movie.setActor(actor);
		
		movie.setPlot("Follows the journey of a time traveler from the post-apocalyptic future who appears in present day on a mission to locate and eradicate the source of a deadly plague that will nearly destroy the human race.");
		
		ArrayList<String> country = new ArrayList<>();
		country.add("USA");
		movie.setCountry(country);
		
		movie.setPoster("C:\\Users\\Vin\\Documents\\temp\\12 Monkeys tt3148266.jpg");
		movie.setMetaScore(-1);
		movie.setImdbScore((float) 7.6);
		movie.setImdbId("tt3148266");
		movie.setWatched(true);
		movie.setFileType(".avi");
		movie.setFileLocation("K:\\films\\12 Monkeys.avi");
		
		return movie;
	}
	
}
