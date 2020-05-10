package resources;
//Test Comment to check Synchonising.
//This class will create a string URL that will retrieve the necessary information
//The String URL will be read in by the XMLparser class

import main.ApplicationMain;

import java.util.Properties;

public class APIControl {
	static final String baseLink = "http://www.omdbapi.com/?" + "apikey=" +
			ApplicationMain.properties.getProperty("api-key") + "&" +
			"plot=full" + "&";

	/**
	 * Method will replace spaces in a string with "%20" to conform to
	 * @param string
	 * @return
	 */
	private static String replaceSpaces(String text) {
		if (text.contains(" ")) {
			text = text.replace(" ", "%20");
		}

		if (text.contains("(") || text.contains(")")) {
			text = text.replace("(", "%20");
			text = text.replace(")", "%20");
		}
		return text;
	}

	/**
	 * Method will return a single result from a title. This is best used when there is little no possibility
	 * of a duplicate movie name
	 * @param title The title of the film
	 * @return
	 */
	public static String getSearchTitle(String title) {
		title = replaceSpaces(title);
		return baseLink + "t=" + title + "&r=xml";
	}
	
	public static String getSearchImdbId(String id) {
		id = replaceSpaces(id);
		return baseLink + "i=" + id + "&r=xml";
	}
	
	public static String getSearchResultsFromTitle(String title) {
		title = replaceSpaces(title);
		return baseLink + "s=" + title + "&r=xml";
	}


}
