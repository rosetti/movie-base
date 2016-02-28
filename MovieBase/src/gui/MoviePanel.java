package gui;

//java imports
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
	  int count = 0;
	  for (Movie i: movieList)
	  {
		  moviePanel.add(getMovieIcon(i));
		  count++;
		  
		  if (count==300)
		  {
			  break;
		  }
	  }
	  
  }
  
  public void clearMovies()
  {
	  moviePanel.removeAll();
	  System.out.println("break point");
  }
  
  public JLabel getMovieIcon(final Movie movie)
  { 
    final MovieContextMenu rightClickMenu = new MovieContextMenu(movie);
    
    ImageIcon imageIcon = new ImageIcon(movie.getPoster());
    imageIcon = ResizeImage.resizeImage(imageIcon, Theme.posterSizeMedium);
    imageIcon.getImage().flush();
    final JLabel imageLabel = new JLabel(imageIcon);
    imageLabel.setText(movie.getTitle());
    imageLabel.setVerticalTextPosition(JLabel.BOTTOM);
    imageLabel.setHorizontalTextPosition(JLabel.CENTER);
    
    imageLabel.addMouseListener(new MouseAdapter()
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
    			rightClickMenu.showMenu(imageLabel, e.getX(), e.getY());
    		}
    		
    	}
    });
    
    Dimension size = new Dimension(200, 320);
    
    imageLabel.setSize(size);
    imageLabel.setPreferredSize(size);
    imageLabel.setMaximumSize(size);
    
    return imageLabel;
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
