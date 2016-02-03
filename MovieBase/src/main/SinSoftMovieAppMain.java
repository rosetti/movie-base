package main;

//java imports
import java.io.*;

public class SinSoftMovieAppMain {

	public static String pwd;
	
	public static void main(String[] args) 
	{
		if (args.length==0)
		{
			pwd = System.getProperty("user.dir");
		}
		
		else
			
		{
			pwd = args[0];
		}
		initialise();
		new MovieTest();
	}

	private static void initialise()
	{
		File imagesDirectory = new File(pwd + "\\images");
		
		if (!imagesDirectory.exists())
		{
			imagesDirectory.mkdir();
		}
	}
}
