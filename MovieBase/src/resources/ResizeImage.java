package resources;

import java.awt.Image;

import javax.swing.ImageIcon;

public class ResizeImage 
{
	public static ImageIcon resizeImage(ImageIcon imageIcon)
	{
		Image image = imageIcon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(200, 298,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		imageIcon = new ImageIcon(newimg);  // transform it back
		
		return imageIcon;
	}
}
