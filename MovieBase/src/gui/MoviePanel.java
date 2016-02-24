package gui;

//java imports
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

//local imports
import resources.ModifiedFlowLayout;
import movieControl.Movie;
import resources.ResizeImage;

public class MoviePanel
{
  JScrollPane moviePane;
  JPanel moviePanel;
  JLabel image = new JLabel(new ImageIcon("H:\\Movie Posters\\Avengers.jpg"));
  SidePanel sPanel;
  MainWindow window;
  
  public MoviePanel(MainWindow mainWindow)
  {
	  makeMoviePanel();
	  this.window = mainWindow;
	  this.moviePane = new JScrollPane(this.moviePanel);
	  moviePane.setBorder(Theme.standardBorder);
	  
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
    final JPanel movieIcon = new JPanel();
    
    final MovieContextMenu rightClickMenu = new MovieContextMenu(movie);
    
    ImageIcon imageIcon = new ImageIcon(movie.getPoster());
    imageIcon = ResizeImage.resizeImage(imageIcon, Theme.posterSizeMedium);
    
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
    		if (SwingUtilities.isLeftMouseButton(e))
    		{
    			window.setSidePanel(movie);
    		}
    		
    		if (SwingUtilities.isRightMouseButton(e))
    		{
    			rightClickMenu.showMenu(movieIcon, e.getX(), e.getY());
    		}
    		
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
	  moviePanel = new JPanel();
	  moviePanel.setBackground(Theme.mainBackground);
	  moviePanel.setLayout(new ModifiedFlowLayout());
	  moviePanel.addMouseListener(new MouseAdapter() 
	  {
		  @Override
		  public void mouseClicked(MouseEvent e)
		  {
			  window.clearSidePanel();
		  }
	  });
  }
  
  public JScrollPane getPanel()
  {
    return this.moviePane;
  }
}
