package parsers;

import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import resources.Logger;

//import java.io.File;
import java.io.IOException;

//this class will read the xml file of movies, create movie objects
public abstract class XMLparser 
{
	//Document to hold parsed xmlDocument
	Document xmlDoc = null;
	//Create a DocumentBuilderFactory
	protected DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	//user the DocumentBuilderFactory, to build a document. Initialise it first.
	protected DocumentBuilder dBuilder = null;
	//Create a blank Document to hold data document
	Document onlineDoc = null;
	
	NodeList movieList = null;
	Node movieNode = null;;
	Element movieElement = null;
	/*------------------------------------------------------------------------------------------------*/
	
	//Constructor
	public XMLparser(String url)
	{
		try
		{
			getParsedDoc(url);	
		}
		
		catch (SAXException e)
		{
			Logger.logMessage("SAXException: Unable to parse document!" + e.getMessage());
		}
		
		catch (IOException e)
		{
			Logger.logMessage("IOException: Unable to parse document" + e.getMessage());
		}
	}
	
	
	protected DocumentBuilder getBuilder(DocumentBuilderFactory dBFactory)
	{
		try 
		{
			dBuilder = dbFactory.newDocumentBuilder();
		} 
		
		catch (ParserConfigurationException e) 
		{
			Logger.logMessage("Parser Configuration Exception" + e.getMessage());
		}
		
		return dBuilder;
	}
	
	//method takes a file path of a local xml file
	//then returns a parsed document, ready for reading
	public void getParsedDoc(String filePath) throws SAXException,IOException
	{
		xmlDoc = getBuilder(dbFactory).parse(filePath);
	}
	
	//method to check if a valid movie was returned 
	public boolean isValidFetch()
	{
		Node response = xmlDoc.getElementsByTagName("root").item(0).getAttributes().getNamedItem("response");
		if (response == null) {
			response = xmlDoc.getElementsByTagName("root").item(0).getAttributes().getNamedItem("Response");
		}
		
		if (response.getTextContent().equals("False")) {
			return false;
		}
		
		else {
			return true;
		}
	}
	
	//Method takes an id, and a tag, and will retrieve an element at that position, ready for querying
	public Element getElement(int id, String tag)
	{
		//Create Element that contains all the movies. 
		movieList = xmlDoc.getElementsByTagName(tag);
		movieNode = xmlDoc.getElementsByTagName(tag).item(id);
		
		return (Element) movieNode;
	}
	
	//Method takes a Element and a tag name, and returns all values of that tag in an ArrayList
	public ArrayList<String> getTagList(Element movieElement, String tag)
	{
		int i = 0;
		int length = movieElement.getElementsByTagName(tag).getLength();
		
		ArrayList<String> tagList = new ArrayList<>();
		
		while (i<= length-1)
		{
			String subTag = movieElement.getElementsByTagName(tag).item(i).getTextContent();
			tagList.add(subTag);
			i++;
		}
		
		return tagList;
	}

	public ArrayList<String> getAttributeList(String csv)
	{
		ArrayList<String> list = new ArrayList<>();
		String temp = "";
		int commaPosition;
		
		if (csv.contains(","))
		{
			while(csv.contains(","))
			{
				commaPosition = csv.indexOf(",");
				temp = csv.substring(0, commaPosition);
				csv = csv.substring(commaPosition + 2, csv.length());
				list.add(temp);
			}
		}
		
		list.add(csv);
		
		
		return list;
	}

	public int removeMin(String input)
	{
		if (input.equals("N/A"))
		{
			return -1;
		}
		int position;
		position = input.indexOf("min");
		input = input.substring(0, position - 1);
		
		return Integer.valueOf(input);
	}

	protected int cleanYear(String yString)
	{
		
		if (yString.equals("N/A"))
		{
			return -1;
		}
		
		if (yString.length()>4)
		{
			yString = yString.substring(0, 4);
		}
		
		return Integer.valueOf(yString);
	}
	
	public Document getXMLDoc()
	{
		return xmlDoc;
	}

}
