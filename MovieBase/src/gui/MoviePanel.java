package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import resources.ModifiedFlowLayout;
import resources.ResizeImage;

public class MoviePanel
{
  JScrollPane moviePane;
  JPanel moviePanel = new JPanel();
  JLabel image = new JLabel(new ImageIcon("H:\\Movie Posters\\Avengers.jpg"));
  
  public MoviePanel()
  {
    this.moviePanel.setBackground(Color.DARK_GRAY);
    this.moviePanel.setLayout(new ModifiedFlowLayout());
    this.moviePane = new JScrollPane(this.moviePanel);
    makeMoviePanel();
    addMovies();
  }
  
  public void addMovies()
  {
    this.moviePanel.add(getMovieIcon("C:\\Users\\Vin\\Documents\\temp\\Ali.jpg", "Ali"));
    this.moviePanel.add(getMovieIcon("C:\\Users\\Vin\\Documents\\temp\\Dredd.jpg", "Dredd"));
    this.moviePanel.add(getMovieIcon("C:\\Users\\Vin\\Documents\\temp\\Inside Out.jpg", "Inside Out"));
    this.moviePanel.add(getMovieIcon("C:\\Users\\Vin\\Documents\\temp\\Leon.jpg", "Leon"));
    this.moviePanel.add(getMovieIcon("C:\\Users\\Vin\\Documents\\temp\\Michael Jackson - MoonWalker.jpg", "MJ - MoonWalker"));
    this.moviePanel.add(getMovieIcon("C:\\Users\\Vin\\Documents\\temp\\The Dark Knight.jpg", "The Dark Knight"));
    this.moviePanel.add(getMovieIcon("C:\\Users\\Vin\\Documents\\temp\\The Martian.jpg", "The Martian"));
    this.moviePanel.add(getMovieIcon("C:\\Users\\Vin\\Documents\\temp\\Tron Legacy.jpg", "Tron Legacy"));
  }
  
  public JPanel getMovieIcon(String image, String title)
  {
    JPanel movieIcon = new JPanel();
    
    ImageIcon imageIcon = new ImageIcon(image);
    imageIcon = ResizeImage.resizeImage(imageIcon);
    
    JLabel imageLabel = new JLabel(imageIcon);
    JLabel titleLabel = new JLabel(title, 0);
    titleLabel.setOpaque(true);
    titleLabel.setBackground(Color.lightGray);
    titleLabel.setMinimumSize(new Dimension(20, 298));
    
    movieIcon.setLayout(new BorderLayout());
    movieIcon.add(imageLabel, "Center");
    movieIcon.add(titleLabel, "South");
    
    //imageLabel.addMouseListener(new MoviePanel.1());
    
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
