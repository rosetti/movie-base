package gui;

//java imports
import javax.swing.*;
import javax.swing.text.DefaultCaret;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//local imports
import processManagers.ImportMovies;

public class ImportProgress implements Runnable
{
	private JFrame window;
	private JLabel titleLabel;
	private JScrollPane progressPane;
	private JTextArea progressBox;
	private JLabel progressLabel;
	private JProgressBar progressBar;
	private JButton cancelBtn;
	private JPanel mainPanel;
	private JPanel buttonPanel;
	private ImportMovies importMovieProcess;
	private String importPath;
	private volatile boolean runLoop;
	private volatile boolean execute;
	
	
	public ImportProgress()
	{	
		runLoop = true;
		execute = false;
		progressLabel = new JLabel();
		titleLabel = new JLabel();
		
		makeFrame();
		makeProgressPane();
		makeProgressBar();
		makeButtons();
		
		mainPanel.add(titleLabel);
		mainPanel.add(progressPane);
		mainPanel.add(progressLabel);
		mainPanel.add(progressBar);
		mainPanel.add(buttonPanel);
		
	}
	
	public void run()
	{
		while(runLoop)
		{
			if (execute)
			{
				runImportProcess(importPath);
				runLoop = false;
			}
		}
	}
	
	public void setRunLoop(boolean value)
	{
		runLoop = value;
	}
	
	public void setExecute(boolean value, String importPath)
	{
		this.execute = value;
		this.importPath = importPath;
	}
	
	public void runImportProcess(String importPath)
	{
		titleLabel.setText("Currently Scanning: " + importPath);
		window.setVisible(true);
		window.revalidate();
		importMovieProcess = new ImportMovies(importPath, progressBox, progressBar, progressLabel);
		importMovieProcess.readMoviesFromOmdb();
		importMovieProcess.writeResultsToXml();
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
		DefaultCaret caret =  (DefaultCaret) progressBox.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
			
		progressPane = new JScrollPane(progressBox, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		progressPane.setMaximumSize(pBoxSize);
		progressPane.setMinimumSize(pBoxSize);
		progressPane.setPreferredSize(pBoxSize);
	}
	
	private void makeProgressBar()
	{
		Dimension pBarSize = new Dimension(450, 30);
		progressBar = new JProgressBar();
		progressBar.setMaximumSize(pBarSize);
		progressBar.setMinimumSize(pBarSize);
		progressBar.setPreferredSize(pBarSize);
	}
	
	private void makeButtons()
	{
		buttonPanel = new JPanel();
		
		cancelBtn = new JButton("Abort");
		cancelBtn.setPreferredSize(Theme.buttonSize);
		cancelBtn.setMaximumSize(Theme.buttonSize);
		cancelBtn.setMinimumSize(Theme.buttonSize);
	
		buttonPanel.add(cancelBtn);
		
		cancelBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				importMovieProcess.stop();
			}
		});
	}
}
