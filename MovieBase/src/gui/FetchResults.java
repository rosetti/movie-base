package gui;

//java imports
import javax.swing.*;
import java.awt.Dimension;

public class FetchResults extends DialogTemplate
{
	private JTextArea message;
	
	public FetchResults()
	{
		super("Fetch Results", 500, 400);
		setMessage();
		
		mainPanel.add(message);
		
		window.setVisible(true);
	}
	
	private void setMessage()
	{
		Dimension messageSize = new Dimension(450, 100);
		message = new JTextArea();
		message.setText("The following results were returned when searching for:");
		message.setMinimumSize(messageSize);
		message.setMaximumSize(messageSize);
		message.setPreferredSize(messageSize);
		message.setEditable(false);
		message.setOpaque(false);
	}
	
	private void setButtons()
	{
		
	}

}
