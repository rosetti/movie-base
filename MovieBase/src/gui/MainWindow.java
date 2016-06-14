package gui;

//java imports
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import main.ProgramLaunch;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

//local imports
import movieControl.Movie;

public class MainWindow implements Observer
{ 
	private JFrame frame;
	private Container contentPane;
	private SidePanel sidePanel;
	private JPanel jSidePanel;
	private ImageIcon logo;
	private TopPanel topPanel = new TopPanel();
	private MoviePanel moviePanel;
	private JScrollPane movieScrollPane;
	private MenuBar menuBar;
	
	public MainWindow()
	{
		menuBar = new MenuBar();
		makeFrame();
		frame.setJMenuBar(menuBar.getMenuBar());
		frame.add(topPanel.getPanel(), "North");
		
		moviePanel = new MoviePanel(this);
		
		movieScrollPane = moviePanel.getPanel();
		
		frame.add(movieScrollPane, "Center");
		
		frame.setSize(900, 650);
		addObservees();
	}
  
	public void showMainWindow()
	{
		frame.setVisible(true);
	}
	
	public void setSidePanel(Movie movie)
	{
		if (!(jSidePanel == null))
		{
			frame.remove(jSidePanel);
		}
		
		sidePanel = null;
		sidePanel = new SidePanel(movie);
		jSidePanel= null;
		jSidePanel = sidePanel.getPanel();
		frame.add(jSidePanel, "West");
		frame.revalidate();
		System.gc();
	}
	
	private void addObservees()
	{
		menuBar.addObserver(this);
		topPanel.addObserver(this);
	}
	
	public void clearSidePanel()
	{
		if (!(jSidePanel == null))
		{
			frame.remove(jSidePanel);
		}
		
		sidePanel = null;
		jSidePanel = null;
		frame.revalidate();
		System.gc();
	}
  
	private void makeFrame()
	{
		frame = new JFrame("Vin's Movie Base");
		frame.setDefaultCloseOperation(3);
    
		contentPane = frame.getContentPane();
		contentPane.setLayout(new BorderLayout());
	}
  
	public void addMoviesToPanel(ArrayList<Movie> movieList)
	{
		moviePanel.clearMovies();
		moviePanel.addMovies(movieList);
		frame.revalidate();
	}
	
	public void refreshByResize()
	{
		frame.revalidate();
	}

	@Override
	public void update(Observable arg0, Object arg1) 
	{
		if (arg1.equals("refresh"))
		{
			System.out.println("Time to refresh!");
			ProgramLaunch.loadLibrary();
			moviePanel.clearMovies();
			addMoviesToPanel(ProgramLaunch.getCoreBase().getMovieBase());
			frame.revalidate();
			System.out.println("Library Refreshed!");
		}
		
		if (arg1.toString().startsWith("search:"));
		{
			String searchTerm = arg1.toString().replace("search:", "");
			moviePanel.clearMovies();
			addMoviesToPanel(ProgramLaunch.getCoreBase().searchBase(searchTerm));
		}
			
	}

}
