package gui;

import java.awt.Color;
import javax.swing.border.*;
import java.awt.Dimension;
import java.awt.Font;

public class Theme
{
  
	//Colors
	static Color topBackground = Color.decode("#A2A4A5");
	static Color mainBackground = Color.decode("#86AFB1");
  
	//Borders
	static Border standardBorder = new LineBorder(Color.WHITE,0);
	static Border frameBorder = new LineBorder(Color.WHITE, 0);
	static Border internalBorder = new LineBorder(Color.WHITE,0);
	static Border singleBlackBorder = new LineBorder(Color.WHITE, 0);
  
	//Dimensions
	static Dimension buttonSize = new Dimension(100,30);
	static Dimension posterSizeSmall = new Dimension (100, 149);
	static Dimension posterSizeMedium = new Dimension (200, 298);
	static Dimension posterSizeLarge = new Dimension (250, 373);
	
	//Fonts
	static Font largeJTextAreaFont = new Font(Font.SANS_SERIF, Font.PLAIN, 21);
}
