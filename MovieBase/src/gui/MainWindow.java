package gui;

//java imports
import java.awt.BorderLayout;
import java.awt.Color;
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
		
		sidePanel = new SidePanel(new Movie());
		jSidePanel = sidePanel.getPanel(); 
		
		moviePanel = new MoviePanel(this);
		
		
		
		movieScrollPane = moviePanel.getPanel();
		
		frame.add(jSidePanel, "West");
		frame.add(movieScrollPane, "Center");
		
		frame.setVisible(true);
		frame.setSize(900, 650);
		addObservees();
	}
  
	public void setSidePanel(Movie movie)
	{
		frame.remove(jSidePanel);
		sidePanel = new SidePanel(movie);
		jSidePanel = sidePanel.getPanel();
		frame.add(jSidePanel, "West");
		frame.revalidate();
	}
	
	private void addObservees()
	{
		menuBar.addObserver(this);
		topPanel.addObserver(this);
	}
	
	public void clearSidePanel()
	{
		frame.remove(jSidePanel);
		sidePanel = null;
		frame.revalidate();
	}
  
	private void makeFrame()
	{
		frame = new JFrame("Vin's Movie Base");
		frame.setDefaultCloseOperation(3);
		frame.setBackground(Color.black);
		logo = new ImageIcon("Y:\\Development\\SinSoftMovieApplication\\src\\resources\\MovieBase Logo.png");
		frame.setIconImage(logo.getImage());

    
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
		//frame.setSize(900, 650);
		frame.revalidate();
	}

	@Override
	public void update(Observable arg0, Object arg1) 
	{
		if (arg1.equals("refresh"))
		{
			ProgramLaunch.loadLibrary();
			addMoviesToPanel(ProgramLaunch.getCoreBase().getMovieBase());
			frame.revalidate();
		}
		
		if (arg1.toString().startsWith("search:"));
		{
			String searchTerm = arg1.toString().replace("search:", "");
			addMoviesToPanel(ProgramLaunch.getCoreBase().searchBase(searchTerm));
		}
			
	}

}
