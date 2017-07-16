package gui;

//java imports
import javax.swing.*;
import java.awt.Dimension;

public abstract class DialogTemplate 
{
	protected JFrame window;
	protected JPanel mainPanel;
	protected JPanel buttonPanel;
	protected JButton okBtn;
	protected JButton cancelBtn;
	
	public DialogTemplate(String title, int hSize, int vSize)
	{
		makeFrame(title, hSize, vSize);
		makeButtons();
	}
	
	private void makeFrame(String title, int hSize, int vSize)
	{
		Dimension wSize = new Dimension(hSize, vSize);
		window = new JFrame(title);
		window.setMaximumSize(wSize);
		window.setMinimumSize(wSize);
		window.setPreferredSize(wSize);
		window.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
		mainPanel = new JPanel();
		window.add(mainPanel);
	}
	
	private void makeButtons()
	{
		buttonPanel = new JPanel();
		
		okBtn = new JButton("Ok");
		okBtn.setMaximumSize(Theme.buttonSize);
		okBtn.setMinimumSize(Theme.buttonSize);
		okBtn.setPreferredSize(Theme.buttonSize);
		
		cancelBtn = new JButton("Cancel");
		cancelBtn.setMaximumSize(Theme.buttonSize);
		cancelBtn.setMinimumSize(Theme.buttonSize);
		cancelBtn.setPreferredSize(Theme.buttonSize);
	}
	
}
