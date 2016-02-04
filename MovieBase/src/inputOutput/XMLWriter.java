package inputOutput;

import movieControl.Movie;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class XMLWriter
{
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = null;
	TransformerFactory tFactory = TransformerFactory.newInstance();
	Transformer xmlTransformer = null;
	
	public XMLWriter()
	{
		makeDocumentBuilder();
		makeTransformers();
	}
	
	private void makeDocumentBuilder()
	{
		try 
		{
			dBuilder = dbFactory.newDocumentBuilder();
		}
		
		catch (ParserConfigurationException e1) 
		{
			System.out.println("Unable to create Document Builder");
			e1.printStackTrace();
		}
	}
	
	//method creates an XML document (does not check if one exists)
	public void makeBlankXMLFile(String output)
	{
	
		Document outDoc = dBuilder.newDocument();
		
		Element rootElement = outDoc.createElement("library");
		outDoc.appendChild(rootElement);
		
		outputXML(outDoc, output);

	}
	
	private void makeTransformers()
	{
		try 
		{
			xmlTransformer = tFactory.newTransformer();
		} 
		
		catch (TransformerConfigurationException e) 
		{
			System.out.println("Unable to create Transformer");
			e.printStackTrace();
		}
	}
	
	public void outputXML(Document doc, String filePath)
	{
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(filePath));
		
		try 
		{
			xmlTransformer.transform(source, result);
		} 
		
		catch (TransformerException e) 
		{
			System.out.println("Unable to output XML document to file");
			e.printStackTrace();
		}
	}
	
	public void addMovie(Document doc, Movie movie)
	{
		Node library = doc.getElementsByTagName("library").item(0);
		
		Element movieElement = doc.createElement("movie");
		
		Element titleElement = doc.createElement("title");
		movieElement.appendChild(titleElement);
		titleElement.setTextContent(movie.getTitle());
		
		Element yearElement = doc.createElement("year");
		movieElement.appendChild(yearElement);
		yearElement.setTextContent(String.valueOf(movie.getYear()));
		
		Element ratingElement = doc.createElement("rating");
		movieElement.appendChild(ratingElement);
		ratingElement.setTextContent(movie.getRating());
		
		Element runtimeElement = doc.createElement("runtime");
		movieElement.appendChild(runtimeElement);
		runtimeElement.setTextContent(String.valueOf(movie.getRuntime()));
		
		Element genreElement = doc.createElement("genre");
		movieElement.appendChild(genreElement);
		
		genreElement = getMultiTags(genreElement,movie.getGenre(), "subGenre", doc);
		
		Element directorElement = doc.createElement("director");
		movieElement.appendChild(directorElement);
		
		directorElement = getMultiTags(directorElement,movie.getDirector(), "subDirector", doc);
		
		Element writerElement = doc.createElement("writer");
		movieElement.appendChild(writerElement);
		
		writerElement = getMultiTags(writerElement,movie.getWriter(), "subWriter", doc);
		
		Element actorElement = doc.createElement("actor");
		movieElement.appendChild(actorElement);
		
		actorElement = getMultiTags(actorElement,movie.getActor(), "subActor", doc);
		
		Element plotElement = doc.createElement("plot");
		movieElement.appendChild(plotElement);
		plotElement.setTextContent(movie.getPlot());
		
		Element countryElement = doc.createElement("country");
		movieElement.appendChild(countryElement);
		
		countryElement = getMultiTags(countryElement,movie.getCountry(), "subCountry", doc);
		
		Element posterElement = doc.createElement("poster");
		movieElement.appendChild(posterElement);
		posterElement.setTextContent(movie.getPoster());
		
		Element metaScoreElement = doc.createElement("metaScore");
		movieElement.appendChild(metaScoreElement);
		metaScoreElement.setTextContent(String.valueOf(movie.getMetaScore()));
		
		Element imdbScoreElement = doc.createElement("imdbScore");
		movieElement.appendChild(imdbScoreElement);
		imdbScoreElement.setTextContent(String.valueOf(movie.getImdbScore()));
		
		Element imdbIdElement = doc.createElement("imdbId");
		movieElement.appendChild(imdbIdElement);
		imdbIdElement.setTextContent(movie.getImdbId());
		
		Element watchedElement = doc.createElement("watched");
		movieElement.appendChild(watchedElement);
		watchedElement.setTextContent(String.valueOf(movie.isWatched()));
		
		Element fileTypeElement = doc.createElement("filetype");
		movieElement.appendChild(fileTypeElement);
		fileTypeElement.setTextContent(movie.getFileType());
		
		Element fileLocationElement = doc.createElement("fileLocation");
		movieElement.appendChild(fileLocationElement);
		fileLocationElement.setTextContent(movie.getFileLocation());
		
		library.appendChild(movieElement);
	}
	
	
	public Element getMultiTags(Element element, ArrayList<String> tagList, String tag, Document doc)
	{
		for (String i: tagList)
		{
			Element tempSubGenreElement = doc.createElement(tag);
			tempSubGenreElement.setTextContent(i);
			element.appendChild(tempSubGenreElement);
		}
		
		return element;
	}
}
