package resources;

//Java Imports
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.io.FileWriter;
import java.util.Date;

//Local Imports
import main.ApplicationMain;

//Class to handle error logging
//Errors will log to a txt file created in the starting directory of the application
public class Logger 
{

	static File logFile = new File(ApplicationMain.pwd + ApplicationMain.slash + " log.txt");
	static FileWriter writer;
	static String newLine = System.getProperty("line.separator");
	static String dashLine = "------------------------------------------------------------------------";
	
	//Log a standard message
	public static void logMessage(String logMessage)
	{

		if (makeWriter())
		{
			try 
			{
				writer.write(getDate() + ": ");
				writer.write(logMessage);
				writer.write(newLine);
				writer.close();
			} 
			
			catch (IOException e) 
			{
				System.out.println("Unable to write to log");
				e.printStackTrace();
			}
		}
		
	}

	//Write a a title message to the log e.g. for a new section
	public static void logBreak(String breakTitle)
	{
		assert breakTitle.length() <= 68;
		
		if (makeWriter())
		{
			String halfDashLine = getHalfDashes(breakTitle);
			String message = halfDashLine + " " + breakTitle + " " + halfDashLine;
			if (message.length() > dashLine.length())
			{
				message = message.substring(0, dashLine.length());
			}
			try 
			{
				writer.write(dashLine);
				writer.write(newLine);
				writer.write(message);
				writer.write(newLine);
				writer.write(dashLine);
				writer.write(newLine);
				writer.close();
			}
			
			catch (IOException e) 
			{
				System.out.println("Unable to write to log");
				e.printStackTrace();
			}
		}
	}
	
	//Create FileWriter
 	private static boolean makeWriter()
	{
		if (writer == null)
		{
			try 
			{
				writer = new FileWriter(logFile,true);
				return true;
			} 
			
			catch (IOException e) 
			{
				System.out.println("Unable to create writer");
				e.printStackTrace();
				return false;
			}
		}
		
		return true;
 		
	}
	
	//Get date as a String
	private static String getDate()
	{
		Date date = new Date();
		date.getTime();
		DateFormat format = new SimpleDateFormat("dd/MM/YYYY HH:mm:ss");
		
		return String.valueOf(format.format(date));
	}

	//Get dashes for header message
	private static String getHalfDashes(String i_string)
	{
		int length = i_string.length();
		length = dashLine.length() - length;
		length = (length/2);
		
		String dashes = "";
		
		for (int i = 0; i < length; i++)
		{
			dashes = dashes + "-";
		}
		
		return dashes;
	}
}
