package gui;

//java imports
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


//local imports
import movieControl.Movie;
import resources.ResizeImage;
import resources.StaticTestObjects;

public class SidePanel
{
  private JPanel sidePanel;
  private JPanel posterPanel = new JPanel();
  private JPanel detailContentPanel = new JPanel();
  private JScrollPane detailPanel = new JScrollPane(this.detailContentPanel);
  private JLabel imageLabel;
  
  public SidePanel(Movie movie)
  {
    sidePanel = new JPanel();
    sidePanel.setBackground(Theme.mainBackground);
    sidePanel.setPreferredSize(new Dimension(300, 200));
    sidePanel.setLayout(new BorderLayout());
    //Movie film = StaticTestObjects.getTestMovie1();
    populateSidePanelContents();
    addMovieDetail(movie);
  }
  
  public SidePanel(){}
  
  public JPanel getPanel()
  {
    return this.sidePanel;
  }
  
  public void populateSidePanelContents()
  { 
    posterPanel.setPreferredSize(new Dimension(400, 320));
    posterPanel.setMaximumSize(new Dimension(400, 320));
    posterPanel.setMinimumSize(new Dimension(400, 320));
    
    detailPanel.setPreferredSize(new Dimension(400, 200));
    detailPanel.setMaximumSize(new Dimension(400, 200));
    detailPanel.setMinimumSize(new Dimension(400, 200));
    
    detailContentPanel.setLayout(new BoxLayout(detailContentPanel, 1));
    
    //addMovieDetail(movie);
    posterPanel.setBackground(Theme.mainBackground);
    
  }
  
  void addMovieDetail(Movie movie)
  {
    detailContentPanel.add(new JLabel("Title: " + movie.getTitle()));
    detailContentPanel.add(new JLabel("Year: " + movie.getYear()));
    detailContentPanel.add(new JLabel("Rating: " + movie.getTitle()));
    detailContentPanel.add(new JLabel("Runtime: " + movie.getTitle()));
    detailContentPanel.add(new JLabel("Genre: " + movie.getListAsString(movie.getGenre())));
    detailContentPanel.add(new JLabel("Director: " + movie.getListAsString(movie.getDirector())));
    detailContentPanel.add(new JLabel("Writer: " + movie.getListAsString(movie.getWriter())));
    detailContentPanel.add(new JLabel("Genre: " + movie.getListAsString(movie.getGenre())));
    detailContentPanel.add(new JLabel("Cast: " + movie.getListAsString(movie.getActor())));
    detailContentPanel.add(new JLabel("Plot: " + movie.getPlot()));
    detailContentPanel.add(new JLabel("Country: " + movie.getListAsString(movie.getCountry())));
    
    ImageIcon poster = new ImageIcon(movie.getPoster());
    poster = ResizeImage.resizeImage(poster);
    JLabel posterLabel = new JLabel(poster);
    posterPanel.add(posterLabel);
    
    detailContentPanel.add(new JLabel("Meta Score: " + movie.getMetaScore()));
    detailContentPanel.add(new JLabel("IMDb Score: " + movie.getImdbScore()));
    detailContentPanel.add(new JLabel("Watched: " + movie.isWatched()));
    detailContentPanel.add(new JLabel("Meta Score: " + movie.getMetaScore()));
    detailContentPanel.add(new JLabel("File Type: " + movie.getFileType()));
    detailContentPanel.add(new JLabel("File Path: " + movie.getFileLocation()));
    sidePanel.add(this.posterPanel, "North");
    sidePanel.add(this.detailPanel, "Center");
  }
  
  public JLabel getImageLabel()
  {
    this.imageLabel = new JLabel();
    
    return this.imageLabel;
  }
}
