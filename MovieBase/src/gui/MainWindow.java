package gui;

//java imports
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.io.PrintStream;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

//local imports
import movieControl.Movie;
import resources.StaticTestObjects;

public class MainWindow
{ 
	private JFrame frame;
	private Container contentPane;
	private SidePanel sidePanel;
	private ImageIcon logo;
  
	public MainWindow()
	{
		makeFrame();
		frame.setJMenuBar(new MenuBar().getMenuBar());
		frame.add(new TopPanel().getPanel(), "North");
		sidePanel = new SidePanel(StaticTestObjects.getTestMovie2());
		frame.add(sidePanel.getPanel(), "West");
		frame.add(new MoviePanel().getPanel(), "Center");
		frame.setVisible(true);
	}
  
	public void setSidePanel(Movie movie)
	{
		sidePanel = new SidePanel(movie);
		frame.add(sidePanel.getPanel(), "West");
		frame.revalidate();
		System.out.println("Side Panel Method finished");
	}
  
	private void makeFrame()
	{
		frame = new JFrame("Vin's Movie Base");
		frame.setDefaultCloseOperation(3);
		frame.setBackground(Color.black);
		logo = new ImageIcon("Y:\\Development\\SinSoftMovieApplication\\src\\resources\\MovieBase Logo.png");
		frame.setIconImage(logo.getImage());
		frame.setSize(900, 650);
    
		contentPane = frame.getContentPane();
		contentPane.setLayout(new BorderLayout());
	}
  
}
