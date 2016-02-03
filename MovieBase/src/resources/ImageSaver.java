package resources;

//java imports
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.io.*;

//local imports
import main.SinSoftMovieAppMain;

public class ImageSaver 
{
	
	public static String getImage(String imageLink, String title, String imdbId)
	{
		String extension = imageLink.substring(imageLink.lastIndexOf("."), imageLink.length());
		String outputPath = SinSoftMovieAppMain.pwd + "\\images\\" + title + " " + imdbId + extension;
		
		
		
		URL url = null;
		try 
		{
			url = new URL(imageLink);
		} 
		
		catch (MalformedURLException e) 
		{
			System.out.println("Incorrectly Formed URL");
			return imageLink;
		}
		
		
		InputStream in = null;
		try 
		{
			in = new BufferedInputStream(url.openStream());
		} 
		
		catch (IOException e) 
		{
			System.out.println("Unable to connect to Online Image Source");
			return imageLink;
		}
		
		OutputStream out = null;
		
		try
		{
			out = new BufferedOutputStream(new FileOutputStream(outputPath));
		}
		
		catch(Exception e)
		{
			System.out.println("Unable to connect to external image location");
			return imageLink;
		}
		
		try 
		{
			for(int i; (i = in.read()) != -1;)
			{
				out.write(i);
			}
		} 
		
		catch (IOException e) 
		{
			System.out.println("Unable to output image to folder");
			return imageLink;
		}
		
		try 
		{
			in.close();
			out.close();
		} 
		
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return outputPath;
	}
}
