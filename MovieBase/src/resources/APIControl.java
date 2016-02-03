package resources;

//This class will create a string URL that will retrieve the necessary information
//The String URL will be read in by the XMLparser class

public class APIControl 
{
	static final String baseLink = "http://www.omdbapi.com/?" ;
	
	private static String replaceSpaces(String string)
	{
		if (string.contains(" "))
		{
			string = string.replace(" ", "%20");
		}
		
		return string;
	}
	
	public static String getSearchTitle(String title)
	{
		title = replaceSpaces(title);
		return baseLink + "t=" + title + "&r=xml";
	}
	
	public static String getSearchImdbId(String id)
	{
		id = replaceSpaces(id);
		return baseLink + "i=" + id + "&r=xml";
	}
	
	public static String getSearchResultsFromTitle(String title)
	{
		title = replaceSpaces(title);
		return baseLink + "s=" + title + "&r=xml";
	}
}
