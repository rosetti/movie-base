package movieControl;

import java.util.ArrayList;

//this class is effectively the database of movie objects
//this will be populated by either the XML parsser or the APIControl class
public class MovieBase 
{
	ArrayList<Movie> movieBase = new ArrayList<>();
	
	public ArrayList<Movie> getMovieBase() 
	{
		return movieBase;
	}

	public MovieBase()
	{
		
	}
	
	public void addMovie(Movie movie)
	{
		movieBase.add(movie);
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
	
	public Movie getMovie(int i)
	{
		return movieBase.get(i);
	}
	
	public ArrayList<Movie> searchBase(String searchTerm)
	{
		ArrayList<Movie> searchResults = new ArrayList<>();
		searchTerm = searchTerm.toUpperCase();
		
		for (Movie i: movieBase)
		{
			if (i.getTitle().toUpperCase().contains(searchTerm))
			{
				searchResults.add(i);
			}
		}
		
		return searchResults;
	}
	
}
