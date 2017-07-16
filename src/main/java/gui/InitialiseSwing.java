package gui;

import javax.swing.UIManager;
import resources.Logger;

public class InitialiseSwing 
{
	
	public static void setSwingConstants()
	{
		try 
		{
			// Set System L&F
			UIManager.setLookAndFeel(
			UIManager.getSystemLookAndFeelClassName());
		} 

		catch (Exception e) 
		{
			Logger.logMessage("Error trying to set the Look and Feel + \n" + e.getMessage());
		}	
	}

}
