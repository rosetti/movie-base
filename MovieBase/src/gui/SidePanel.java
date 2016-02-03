package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import movieControl.Movie;
import resources.ResizeImage;

public class SidePanel
{
  private JPanel sidePanel;
  private JPanel posterPanel = new JPanel();
  private JPanel detailContentPanel = new JPanel();
  private JScrollPane detailPanel = new JScrollPane(this.detailContentPanel);
  private JLabel imageLabel;
  
  public SidePanel()
  {
    this.sidePanel = new JPanel();
    this.sidePanel.setBackground(Theme.mainBackground);
    this.sidePanel.setPreferredSize(new Dimension(300, 200));
    this.sidePanel.setLayout(new BorderLayout());
    Movie film = new Movie();
    film.setTitle("12 Monkeys");
    film.setYear(2015);
    film.setPoster("C:\\Program Files\\Sinnerman Software\\Movie Base\\images\\12 Monkeys 2015.jpg");
    populateSidePanelContents(film);
  }
  
  public JPanel getPanel()
  {
    return this.sidePanel;
  }
  
  public void populateSidePanelContents(Movie movie)
  {
    ImageIcon poster = new ImageIcon(movie.getPoster());
    poster = ResizeImage.resizeImage(poster);
    
    JLabel posterLabel = new JLabel(poster);
    
    this.posterPanel.setPreferredSize(new Dimension(400, 320));
    this.posterPanel.setMaximumSize(new Dimension(400, 320));
    this.posterPanel.setMinimumSize(new Dimension(400, 320));
    
    this.detailPanel.setPreferredSize(new Dimension(400, 200));
    this.detailPanel.setMaximumSize(new Dimension(400, 200));
    this.detailPanel.setMinimumSize(new Dimension(400, 200));
    
    this.detailContentPanel.setLayout(new BoxLayout(this.detailContentPanel, 1));
    
    addMovieDetail(movie);
    
    this.posterPanel.add(posterLabel);
    this.posterPanel.setBackground(Theme.mainBackground);
    this.sidePanel.add(this.posterPanel, "North");
    this.sidePanel.add(this.detailPanel, "Center");
  }
  
  private void addMovieDetail(Movie movie)
  {
    this.detailContentPanel.add(new JLabel("Title: " + movie.getTitle(), 0));
    this.detailContentPanel.add(new JLabel("Year: " + movie.getYear()));
    this.detailContentPanel.add(new JLabel("Title: " + movie.getTitle()));
    this.detailContentPanel.add(new JLabel("Title: " + movie.getTitle()));
  }
  
  public JLabel getImageLabel()
  {
    this.imageLabel = new JLabel();
    
    return this.imageLabel;
  }
}
