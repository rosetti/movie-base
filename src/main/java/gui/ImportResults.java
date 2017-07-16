package gui;

//java imports
import javax.swing.*;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ImportResults 
{
	private JFrame window;
	private JPanel mainPanel;
	private JLabel successLabel;
	private JLabel failLabel;
	private JTextArea questionLabel;
	private JPanel buttonPanel;
	private JPanel resultPanel;
	private JButton yesBtn;
	private JButton noBtn;
	
	public ImportResults(int successes, int fails)
	{
		makeFrame();
		makeLabels(successes,fails);
		makeButtons();
		makeQuestionLabel();
		
		mainPanel.add(resultPanel);
		mainPanel.add(questionLabel);
		mainPanel.add(buttonPanel);
		
		window.setVisible(true);
	}
	
	private void makeFrame()
	{
		Dimension windowSize = new Dimension(300, 200);
		window = new JFrame("Import New Movies - Scanning Completed!");
		window.setMaximumSize(windowSize);
		window.setMinimumSize(windowSize);
		window.setPreferredSize(windowSize);
		window.setResizable(false);
		
		mainPanel = new JPanel();
		window.add(mainPanel);
	}
	
	private void makeLabels(int successes, int fails)
	{
		resultPanel = new JPanel();
		resultPanel.setPreferredSize(new Dimension(270,60));
		
		successLabel = new JLabel(successes + " " + getMovieWord(successes) + " Successfully Imported");
		failLabel = new JLabel(fails + " " + getMovieWord(fails) + " failed to import");
		
		resultPanel.add(successLabel);
		resultPanel.add(failLabel);
	}
	
	private void makeQuestionLabel()
	{
		questionLabel = new JTextArea("Would you like to view and/or manually add failed imports?");
		questionLabel.setLineWrap(true);
		questionLabel.setWrapStyleWord(true);
		questionLabel.setEditable(false);
		questionLabel.setOpaque(false);
		questionLabel.setPreferredSize(new Dimension(230, 40));
	}
	
	private String getMovieWord(int count)
	{
		if (count ==1)
		{
			return "Movie";
		}
		
		else
		{
			return "Movies";
		}
	}
	
	private void makeButtons()
	{
		buttonPanel = new JPanel();
		
		yesBtn = new JButton("Yes");
		yesBtn.setMaximumSize(Theme.buttonSize);
		yesBtn.setMinimumSize(Theme.buttonSize);
		yesBtn.setPreferredSize(Theme.buttonSize);
		
		noBtn = new JButton("No");
		noBtn.setMaximumSize(Theme.buttonSize);
		noBtn.setMinimumSize(Theme.buttonSize);
		noBtn.setPreferredSize(Theme.buttonSize);
		
		buttonPanel.add(yesBtn);
		buttonPanel.add(noBtn);
		
		yesBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				
			}
			
		});
		
	}
}

