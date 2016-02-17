package gui;

//java imports
import javax.swing.*;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ImportProgress 
{
	private JFrame window;
	private JLabel title;
	private JScrollPane progressPane;
	private JTextArea progressBox;
	private JLabel progressLabel;
	private JProgressBar progressBar;
	private JButton cancelBtn;
	private JButton okBtn;
	private JPanel mainPanel;
	private JPanel buttonPanel;

	
	public ImportProgress(String importPath)
	{
		makeFrame();
		makeProgressPane();
		makeProgressLabel(1, 100);
		makeProgressBar(100);
		makeButtons();
		
		mainPanel.add(new JLabel("Currently Scanning: " + importPath));
		mainPanel.add(progressPane);
		mainPanel.add(progressLabel);
		mainPanel.add(progressBar);
		mainPanel.add(buttonPanel);
		window.setVisible(true);
		
	}
	
	private void runImportProcess()
	{
		
	}
	
	private void makeFrame()
	{
		Dimension windowSize = new Dimension(500, 315);
		window = new JFrame("Importing Movies");
		window.setMaximumSize(windowSize);
		window.setMinimumSize(windowSize);
		window.setPreferredSize(windowSize);
		window.setResizable(false);
		
		window.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
		mainPanel = new JPanel();
		window.add(mainPanel);
	}
	
	private void makeProgressPane()
	{
		Dimension pBoxSize = new Dimension(450, 150);
		progressBox = new JTextArea();
		progressBox.setEditable(false);
			
		progressPane = new JScrollPane(progressBox, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		progressPane.setMaximumSize(pBoxSize);
		progressPane.setMinimumSize(pBoxSize);
		progressPane.setPreferredSize(pBoxSize);
		
		progressBox.setText("Helloooooooo world");
		progressBox.setText("Added String");
	}
	
	private void makeProgressLabel(int position, int total)
	{
		progressLabel = new JLabel();
		progressLabel.setText(position + " of " + total);	
	}
	
	private void makeProgressBar(int max)
	{
		Dimension pBarSize = new Dimension(450, 30);
		progressBar = new JProgressBar();
		progressBar.setMaximumSize(pBarSize);
		progressBar.setMinimumSize(pBarSize);
		progressBar.setPreferredSize(pBarSize);
		progressBar.setMaximum(max);
	}
	
	private void makeButtons()
	{
		buttonPanel = new JPanel();
	
		
		
		okBtn = new JButton("Ok");
		okBtn.setPreferredSize(Theme.buttonSize);
		okBtn.setMaximumSize(Theme.buttonSize);
		okBtn.setMinimumSize(Theme.buttonSize);
		
		cancelBtn = new JButton("Cancel");
		cancelBtn.setPreferredSize(Theme.buttonSize);
		cancelBtn.setMaximumSize(Theme.buttonSize);
		cancelBtn.setMinimumSize(Theme.buttonSize);
		
		buttonPanel.add(okBtn);
		buttonPanel.add(cancelBtn);
		
		cancelBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				//TODO: Write some sort of cancel method
			}
		});
	}
}
