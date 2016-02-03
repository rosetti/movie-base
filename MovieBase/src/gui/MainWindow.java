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
  private JMenuBar menuBar;
  private JMenu fileMenu;
  private Container contentPane;
  private JPanel mainBox;
  private JMenuItem addMovies;
  
  private SidePanel sidePanel;
  
  public MainWindow()
  {
    makeFrame();
    addMenuBar();
    frame.setJMenuBar(this.menuBar);
    frame.add(new TopPanel().getPanel(), "North");
    sidePanel = new SidePanel(StaticTestObjects.getTestMovie2());
    frame.add(sidePanel.getPanel(), "West");
    frame.add(new MoviePanel().getPanel(), "Center");
    addMenuItem();
    frame.setVisible(true);
  }
  
  public void setSidePanel(Movie movie)
  {
	  sidePanel = new SidePanel(movie);
	  frame.add(sidePanel.getPanel(), "West");
	  frame.revalidate();
	  System.out.println("Side Panel Method finished");
  }
  //
  private void makeFrame()
  {
    this.frame = new JFrame("Vin's Movie Base");
    this.frame.setDefaultCloseOperation(3);
    this.frame.setBackground(Color.black);
    ImageIcon logo = new ImageIcon("Y:\\Development\\SinSoftMovieApplication\\src\\resources\\MovieBase Logo.png");
    this.frame.setIconImage(logo.getImage());
    this.frame.setSize(900, 650);
    
    this.contentPane = this.frame.getContentPane();
    this.contentPane.setLayout(new BorderLayout());
  }
  
  private void testMethod()
  {
    System.out.println("Hello Search!");
  }
  
  private void addMenuItem()
  {
    this.addMovies = new JMenuItem("Add Movies");
    
    //this.addMovies.addActionListener(new MainWindow.1(this));
    
    this.fileMenu.add(this.addMovies);
  }
  
  private void addMenuBar()
  {
    this.menuBar = new JMenuBar();
    this.menuBar.setBackground(Theme.topBackground);
    this.menuBar.add(getMenu());
  }
  
  private void makeMainBox()
  {
    this.mainBox = new JPanel();
    this.mainBox.setBackground(Color.ORANGE);
  }
  
  private JMenu getMenu()
  {
    this.fileMenu = new JMenu("File");
    return this.fileMenu;
  }
}
