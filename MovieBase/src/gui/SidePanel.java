package gui;

//java imports
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


//local imports
import movieControl.Movie;
import resources.ResizeImage;
import resources.StaticTestObjects;

public class SidePanel
{
  private JPanel sidePanel;
  private JPanel posterPanel = new JPanel();
  private JPanel detailContentPanel = new JPanel();
  private JScrollPane detailPanel = new JScrollPane(this.detailContentPanel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
  private JLabel imageLabel;
  
  public SidePanel(Movie movie)
  {
    sidePanel = new JPanel();
    sidePanel.setBackground(Theme.mainBackground);
    sidePanel.setPreferredSize(new Dimension(300, 200));
    sidePanel.setLayout(new BorderLayout());
    populateSidePanelContents();
    
    if (movie.getTitle().isEmpty())
    {
    	sidePanel.setVisible(false);
    }
    
    else
    {
    	addMovieDetail(movie);	
    }
    
  }
  
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
	  detailContentPanel.add(getJTextArea("Title: " + movie.getTitle()));
	  detailContentPanel.add(getJTextArea("Year: " + movie.getYear()));
	  detailContentPanel.add(getJTextArea("Rating: " + movie.getTitle()));
	  detailContentPanel.add(getJTextArea("Runtime: " + movie.getTitle()));
	  detailContentPanel.add(getJTextArea("Genre: " + movie.getListAsString(movie.getGenre())));
	  detailContentPanel.add(getJTextArea("Director: " + movie.getListAsString(movie.getDirector())));
	  detailContentPanel.add(getJTextArea("Writer: " + movie.getListAsString(movie.getWriter())));
	  detailContentPanel.add(getJTextArea("Genre: " + movie.getListAsString(movie.getGenre())));
	  detailContentPanel.add(getJTextArea("Cast: " + movie.getListAsString(movie.getActor())));
	  detailContentPanel.add(getJTextArea("Plot: " + movie.getPlot()));
	  detailContentPanel.add(getJTextArea("Country: " + movie.getListAsString(movie.getCountry())));
    
	  ImageIcon poster = new ImageIcon(movie.getPoster());
	  poster = ResizeImage.resizeImage(poster);
	  JLabel posterLabel = new JLabel(poster);
	  posterPanel.add(posterLabel);
    
	  detailContentPanel.add(getJTextArea("Meta Score: " + movie.getMetaScore()));
	  detailContentPanel.add(getJTextArea("IMDb Score: " + movie.getImdbScore()));
	  detailContentPanel.add(getJTextArea("Watched: " + movie.isWatched()));
	  detailContentPanel.add(getJTextArea("Meta Score: " + movie.getMetaScore()));
	  detailContentPanel.add(getJTextArea("File Type: " + movie.getFileType()));
	  detailContentPanel.add(getJTextArea("File Path: " + movie.getFileLocation()));
	  sidePanel.add(this.posterPanel, "North");
	  sidePanel.add(this.detailPanel, "Center");
  }
  
  private JTextArea getJTextArea(String text)
  {
	  JTextArea tBox1 = new JTextArea();
	  tBox1.setEditable(false);
	  tBox1.setText(text);
	  tBox1.setLineWrap(true);
	  tBox1.setWrapStyleWord(true);
	  tBox1.setBackground(Color.LIGHT_GRAY);
	  
	  int height = 17; 
	  int charCount = text.length();
	  
	  if (charCount>=45)
	  {
		  height = (charCount/45) * 17;
		  
		  if (charCount%45>=1)
		  {
			  height += 17;
		  }
	  }
	  
	  tBox1.setPreferredSize(new Dimension(200, height));

	  
	  return tBox1;
  }
  
  public JLabel getImageLabel()
  {
    this.imageLabel = new JLabel();
    
    return this.imageLabel;
  }
}
