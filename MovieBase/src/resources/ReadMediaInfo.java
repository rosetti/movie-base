package resources;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import main.SinSoftMovieAppMain;

public class ReadMediaInfo 
{
	static String exePath = SinSoftMovieAppMain.pwd + "\\MediaInfo.exe";
	
	public static int getRuntimeFromFile(String mediaPath)
	{
		ProcessBuilder builder = new ProcessBuilder(exePath,mediaPath);
		
		builder.redirectErrorStream(true);
		Process process = null;
		try 
		{
			process = builder.start();
		} 
		
		catch (IOException e) 
		{
			System.out.println("Error when starting Builder");
			e.printStackTrace();
		}
		
		StringBuilder buffer = new StringBuilder();
		Reader reader = new InputStreamReader(process.getInputStream());
		
		try 
		{
			for (int i; (i = reader.read()) != -1;) 
			{
			    buffer.append((char) i);
			}
		} 
		
		catch (IOException e) 
		{
			System.out.println("Error loading input into buffer");
			e.printStackTrace();
		}
		
		String output = buffer.toString();
		
		if (!output.contains("Duration"))
		{
			return -1;
		}
		
		int dIndex = output.indexOf("Duration");
		
		String duration = output.substring(dIndex, output.length());
		
		
		int mnIndex = duration.indexOf("mn") + 2;
		duration = duration.substring(0, mnIndex);
		duration = duration.substring(duration.indexOf(":") + 2, duration.length());
		
		int hoursIndex = duration.indexOf("h");
		mnIndex = duration.indexOf("mn");
		int hours = Integer.valueOf(duration.substring(0, hoursIndex));
		int minutes = Integer.valueOf(duration.substring(hoursIndex + 2, mnIndex));
		
		//System.out.println(duration);
		
		//System.out.println("Hours: " + hours);
		//System.out.println("Minutes: " + minutes);
		//System.out.println((hours*60) + minutes);
		
		return (hours*60) + minutes;
	}
}
