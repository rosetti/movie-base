package resources;

//java imports
import java.io.IOException;
import java.net.*;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;
import java.io.*;

//local imports
import main.ApplicationMain;

public class ImageSaver 
{
	
	public static String getImage(String imageLink, String title, String imdbId)
	{
		title = title.replace(":",  "-");
		
		if (imageLink.equals("N/A"))
		{
			return imageLink;
		}
		
		String extension = imageLink.substring(imageLink.lastIndexOf("."), imageLink.length());
		String outputPath = (ApplicationMain.pwd + ApplicationMain.slash + "images" + ApplicationMain.slash + title + " " + imdbId + extension);
		
		
		BufferedImage downloadedImage = downloadBufferedImage(imageLink);
		
		OutputStream out = null;
		
		try
		{
			out = new BufferedOutputStream(new FileOutputStream(outputPath));
		}
		
		catch(Exception e)
		{
			System.out.println("Unable to connect to external image location");
			outputPath = imageLink;
		}
		
		try 
		{
			ImageIO.write(downloadedImage, "jpeg", new File(outputPath));
		} 
		catch (IOException e1) 
		{
			System.out.println("Unable to output image to file");
		}
		
		try 
		{
			out.close();
		} 
		
		catch (IOException e) 
		{
			System.out.println("Unable to close input/output streams.");
			System.out.println("God only knows why...");
		}
		
		return outputPath;
	}
	
	private static BufferedImage downloadBufferedImage(String imageLink)
	{
		URL url = null;
		BufferedImage bufferedImage = null;
		try 
		{
			url = new URL(imageLink);
		} 
		
		catch (MalformedURLException e) 
		{
			System.out.println("Incorrectly Formed URL");
		}
		
		try 
		{
			bufferedImage = ImageIO.read(url.openStream());
		}
		
		catch (IOException e)
		{
			System.out.println("unable to write to img");
		}
		
		return bufferedImage;
	}

	public static ImageIcon getImageIcon(String imageLink)
	{
		return new ImageIcon(downloadBufferedImage(imageLink));
	}
}
