package gui;

//java imports
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


//local imports
import resources.ModifiedFlowLayout;
import resources.StaticTestObjects;
import movieControl.Movie;
import resources.ResizeImage;

public class MoviePanel
{
  JScrollPane moviePane;
  JPanel moviePanel = new JPanel();
  JLabel image = new JLabel(new ImageIcon("H:\\Movie Posters\\Avengers.jpg"));
  SidePanel sPanel;
  MainWindow window;
  
  public MoviePanel(MainWindow mainWindow)
  {
	  //mainWindow.paramTest();
	  this.window = mainWindow;
	  this.moviePanel.setBackground(Color.DARK_GRAY);
	  this.moviePanel.setLayout(new ModifiedFlowLayout());
	  this.moviePane = new JScrollPane(this.moviePanel);
	  makeMoviePanel();
  }
  
  public void addMovies(ArrayList<Movie> movieList)
  {
	  for (Movie i: movieList)
	  {
		  moviePanel.add(getMovieIcon(i.getPoster(), i.getTitle(), i));
	  }
	  
  }
  
  public JPanel getMovieIcon(String image, String title, final Movie movie)
  {
    JPanel movieIcon = new JPanel();
    
    ImageIcon imageIcon = new ImageIcon(movie.getPoster());
    imageIcon = ResizeImage.resizeImage(imageIcon);
    
    JLabel imageLabel = new JLabel(imageIcon);
    JLabel titleLabel = new JLabel(movie.getTitle(), 0);
    titleLabel.setOpaque(true);
    titleLabel.setBackground(Color.lightGray);
    titleLabel.setMinimumSize(new Dimension(20, 298));
    
    movieIcon.setLayout(new BorderLayout());
    movieIcon.add(imageLabel, "Center");
    movieIcon.add(titleLabel, "South");
    
    movieIcon.addMouseListener(new MouseAdapter()
    {
    	@Override
    	public void mouseClicked(MouseEvent e) 
    	{
    		window.setSidePanel(movie);
    	}
    });
    
    Dimension size = new Dimension(200, 298);
    
    movieIcon.setSize(size);
    movieIcon.setPreferredSize(size);
    movieIcon.setMaximumSize(size);
    
    return movieIcon;
  }
  
  private void makeMoviePanel()
  {
    this.moviePane.setBackground(Color.blue);
  }
  
  private void addImage(ArrayList<JLabel> imageList) {}
  
  public JScrollPane getPanel()
  {
    return this.moviePane;
  }
}
