package parsers;

//java imports

//local imports
import movieControl.Movie;
import java.util.ArrayList;

//local imports
import resources.ImageSaver;

public class WebParser extends XMLparser 
{
	public WebParser(String url)
	{
		super(url);
	}
	
	public Movie getWebMovieByTitle(Movie movieBlank)
	{
		String title = xmlDoc.getElementsByTagName("movie").item(0).getAttributes().getNamedItem("title").getTextContent();
		int year = cleanYear(xmlDoc.getElementsByTagName("movie").item(0).getAttributes().getNamedItem("year").getTextContent());
		String rating = xmlDoc.getElementsByTagName("movie").item(0).getAttributes().getNamedItem("rated").getTextContent();
		
		int runtime = Integer.valueOf(removeMin(xmlDoc.getElementsByTagName("movie").item(0).getAttributes().getNamedItem("runtime").getTextContent()));
		
		ArrayList<String> genre = getAttributeList(xmlDoc.getElementsByTagName("movie").item(0).getAttributes().getNamedItem("genre").getTextContent());
		ArrayList<String> director = getAttributeList(xmlDoc.getElementsByTagName("movie").item(0).getAttributes().getNamedItem("director").getTextContent());
		ArrayList<String> writer = getAttributeList(xmlDoc.getElementsByTagName("movie").item(0).getAttributes().getNamedItem("writer").getTextContent());
		ArrayList<String> actor = getAttributeList(xmlDoc.getElementsByTagName("movie").item(0).getAttributes().getNamedItem("actors").getTextContent());
		String plot = xmlDoc.getElementsByTagName("movie").item(0).getAttributes().getNamedItem("plot").getTextContent();
		ArrayList<String> country = getAttributeList(xmlDoc.getElementsByTagName("movie").item(0).getAttributes().getNamedItem("country").getTextContent());
		String imageLink = xmlDoc.getElementsByTagName("movie").item(0).getAttributes().getNamedItem("poster").getTextContent();
		
		
		int metaScore;
		if (xmlDoc.getElementsByTagName("movie").item(0).getAttributes().getNamedItem("metascore").getTextContent().equals("N/A"))
		{
			metaScore = -1;
		}
		
		else
		{
			metaScore = Integer.valueOf(xmlDoc.getElementsByTagName("movie").item(0).getAttributes().getNamedItem("metascore").getTextContent());
		}
		
		float imdbScore;
		if(xmlDoc.getElementsByTagName("movie").item(0).getAttributes().getNamedItem("imdbRating").getTextContent().equals("N/A"))
		{
			imdbScore = -1;
		}
		
		else	
		{
			imdbScore = Float.valueOf(xmlDoc.getElementsByTagName("movie").item(0).getAttributes().getNamedItem("imdbRating").getTextContent());
		}
		
		String imdbId = xmlDoc.getElementsByTagName("movie").item(0).getAttributes().getNamedItem("imdbID").getTextContent();
		String poster = ImageSaver.getImage(imageLink, title, imdbId);
		
		//create movie
		movieBlank.setTitle(title);
		movieBlank.setYear(year);
		movieBlank.setRating(rating);
		movieBlank.setRuntime(runtime);
		movieBlank.setGenre(genre);
		movieBlank.setDirector(director);
		movieBlank.setWriter(writer);
		movieBlank.setActor(actor);
		movieBlank.setPlot(plot);
		movieBlank.setCountry(country);
		movieBlank.setPoster(poster);
		movieBlank.setMetaScore(metaScore);
		movieBlank.setImdbScore(imdbScore);
		movieBlank.setImdbId(imdbId);
		
		
		return movieBlank;
	}

	public ArrayList<Movie> getSearchResults()
	{
		ArrayList<Movie> searchResults = new ArrayList<>();
		
		
		
		return searchResults;
	}
	
	
}
