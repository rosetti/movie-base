package movieControl;

//java imports
import java.util.ArrayList;
import java.util.Iterator;

//local imports
import errorHandling.MovieNotFoundException;

//this class is effectively the database of movie objects
//this will be populated by either the XML parser or the APIControl class
public class MovieBase
{
	private static MovieBase coreBase;
	private static Iterator<Movie> iterator;
	private ArrayList<Movie> movieBase = new ArrayList<>();
	private int length;
	
	public static MovieBase getInstance()
	{
		if (coreBase == null)
		{
			coreBase = new MovieBase();
		}
		
		return coreBase;
	}
	
	public void addMovie(Movie movie)
	{
		movieBase.add(movie);
		length = movieBase.size();
	}
	
	public void removeMovie(Movie movie)
	{
		movieBase.remove(movie);
	}
	
	public void addMovies(ArrayList<Movie> movieList)
	{
		for (Movie i: movieList)
		{
			movieBase.add(i);
		}
	}
	
	public void printMovies()
	{
		for (Movie i: movieBase)
		{
			i.printMovie();
			System.out.println("-------------------------------------------");
		}
	}
	
	public ArrayList<Movie> searchBase(String searchTerm)
	{
		ArrayList<Movie> searchResults = new ArrayList<>();
		searchTerm = searchTerm.toUpperCase();
		
		for (Movie i: movieBase)
		{
			if
			(
				i.getTitle().toUpperCase().contains(searchTerm) ||
				String.valueOf(i.getYear()).contains(searchTerm) ||
				Movie.getListAsString(i.getActor()).toUpperCase().contains(searchTerm) ||
				Movie.getListAsString(i.getDirector()).toUpperCase().contains(searchTerm) ||
				Movie.getListAsString(i.getWriter()).toUpperCase().contains(searchTerm) ||
				Movie.getListAsString(i.getGenre()).toUpperCase().contains(searchTerm) ||
				i.getPlot().toUpperCase().contains(searchTerm) ||
				Movie.getListAsString(i.getCountry()).toUpperCase().contains(searchTerm) ||
				i.getImdbId().toUpperCase().contains(searchTerm)
			)
			{
				searchResults.add(i);
			}
		}
		
		return searchResults;
	}

	public Movie searchByTitle(String searchTitle)
	{
		boolean movieFound = false;
		int baseSearchLength = length;
		
		
		return null;
	}
	
	public Iterator<Movie> getIterator()
	{
		if (iterator == null)
		{
			iterator = movieBase.iterator();
		}
		
		return iterator;
	}
}
