package gui;

//java imports
import javax.swing.*;
import java.awt.Dimension;

public class ImportProgress 
{
	private JFrame window;
	private JLabel title;
	private JTextArea progressBox;
	private JLabel progressLabel;
	private JProgressBar progressBar;
	private JButton cancelbtn;
	private JButton okBtn;
	
	public ImportProgress()
	{
		
	}
	
	public void makeFrame()
	{
		window = new JFrame("Importing Movies");
	}
	
	public JProgressBar getProgressBar(int max)
	{
		progressBar.setMaximum(max);
		return progressBar;
	}
}
