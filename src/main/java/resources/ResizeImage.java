package resources;

import java.awt.Image;
import java.awt.Dimension;

import javax.swing.ImageIcon;

public class ResizeImage 
{
	public static ImageIcon resizeImage(ImageIcon imageIcon, Dimension dimensions)
	{
		int width = dimensions.width;
		int height = dimensions.height;
		
		Image image = imageIcon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(width, height,  java.awt.Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(newimg);  // transform it back
		
		return imageIcon;
	}
}
