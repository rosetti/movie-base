package gui;

import java.awt.Color;
import javax.swing.border.*;
import java.awt.Dimension;

public class Theme
{
  
	//Colors
	static Color topBackground = Color.LIGHT_GRAY;
	static Color mainBackground = Color.LIGHT_GRAY;
  
	//Borders
	static Border standardBorder = new LineBorder(Color.DARK_GRAY,4);
	static Border frameBorder = new LineBorder(Color.BLUE, 3);
	static Border internalBorder = new LineBorder(Color.darkGray,1);
	static Border singleBlackBorder = new LineBorder(Color.black, 1);
  
	//Dimensions
	static Dimension buttonSize = new Dimension(100,30);
}
