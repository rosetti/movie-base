package main;

//java imports
import java.io.*;

import gui.InitialiseSwing;

//import main.java.gui.InitialiseSwing;

public class ApplicationMain {

	public static String pwd;
	public static String slash;
	public static boolean mediaInfoAvailable;
	
	public static void main(String[] args) 
	{	
		if (args.length==0)
		{
			pwd = System.getProperty("user.dir");
		}
		
		else
		{
			if (args.length > 1)
			{
				getOpts(args);
			}
			
			else
			{
				pwd = args[0];
			}
		}
		setSlash();
		initialise();
		setMediaInfoAvailable();
		
		//new ProgramLaunch();
		new MovieTest();
	}

	private static void initialise()
	{
		File imagesDirectory = new File(pwd + slash + "images");
		
		if (!imagesDirectory.exists())
		{
			imagesDirectory.mkdir();
		}
		InitialiseSwing.setSwingConstants();
	}
	
	private static void setSlash()
	{
		if (pwd.contains("/"))
		{
			slash = "/";
		}
		
		else
		{
			slash = "\\";
		}
	}
	
	private static void setMediaInfoAvailable()
	{
		if(new File(pwd + slash + "MediaInfo.exe").exists())
		{
			mediaInfoAvailable = true;
		}
		
		else
		{
			mediaInfoAvailable = false;
		}
	}
	
	private static void getOpts(String[] args)
	{
		System.out.println("Getting options...");
	}
}
