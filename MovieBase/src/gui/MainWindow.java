package gui;

//java imports
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.util.ArrayList;

//local imports
import movieControl.Movie;
import resources.StaticTestObjects;

public class MainWindow
{ 
	private JFrame frame;
	private Container contentPane;
	private SidePanel sidePanel;
	private ImageIcon logo;
	private TopPanel topPanel = new TopPanel();
	private MoviePanel moviePanel; // = new MoviePanel();
	
	public MainWindow()
	{

		makeFrame();
		frame.setJMenuBar(new MenuBar().getMenuBar());
		frame.add(topPanel.getPanel(), "North");
		sidePanel = new SidePanel(new Movie());
		moviePanel = new MoviePanel(this);
		frame.add(sidePanel.getPanel(), "West");
		frame.add(moviePanel.getPanel(), "Center");
		frame.setVisible(true);
		frame.setSize(900, 650);
	}
  
	public void setSidePanel(Movie movie)
	{
		sidePanel = new SidePanel(movie);
		//I need to remove the old panel.
		//frame.remove(sidePanel.getPanel());
		//System.out.println("sleeping now");
		Component[] components = frame.getRootPane().getComponents();
		int index = 0;
		Component gotComponent=null;
		for (Component i: components)
		{
			if (index==1)
			{
				gotComponent = i;
				System.out.println(i.getName());
			}
			//System.out.println(i.toString());
			//System.out.println(i.getName());
			index++;
		}
		
		//System.out.println("and we're back");
		frame.remove(gotComponent);
		sidePanel = new SidePanel(movie);
		frame.add(sidePanel.getPanel(), "West");
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
		moviePanel.addMovies(movieList);
	}
	
	public void refreshByResize()
	{
		frame.setSize(900, 650);
	}

}
