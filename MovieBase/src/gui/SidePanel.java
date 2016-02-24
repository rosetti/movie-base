package gui;

//java imports
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JCheckBox;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.event.*;

//local imports
import movieControl.Movie;
import resources.ResizeImage;

public class SidePanel
{
  private JPanel sidePanel;
  private JPanel posterPanel = new JPanel();
  private JPanel detailContentPanel = new JPanel();
  private JScrollPane detailPanel = new JScrollPane(this.detailContentPanel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
  
  public SidePanel(Movie movie)
  {
	sidePanel = new JPanel();
    sidePanel.setBorder(Theme.standardBorder);
    sidePanel.setBackground(Theme.mainBackground);
    sidePanel.setPreferredSize(new Dimension(400, 200));
    sidePanel.setLayout(new BorderLayout());
    populateSidePanelContents();
    addButtonControl(movie);
    
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
    //posterPanel.setPreferredSize(new Dimension(400, 350));
    //posterPanel.setMaximumSize(new Dimension(400, 350));
    //posterPanel.setMinimumSize(new Dimension(400, 350));
    posterPanel.setBorder(Theme.internalBorder);
    
    detailPanel.setPreferredSize(new Dimension(400, 200));
    detailPanel.setMaximumSize(new Dimension(400, 200));
    detailPanel.setMinimumSize(new Dimension(100, 200));
    //detailPanel.setBorder(Theme.internalBorder);
    
    detailContentPanel.setLayout(new BoxLayout(detailContentPanel, 1));

    posterPanel.setBackground(Theme.mainBackground);
    
  }
  
  private void addButtonControl(final Movie movie)
  {
	  
	  posterPanel.addMouseListener(new MouseAdapter()  
	  {
		  public void mouseClicked(MouseEvent e)
		  {
			  if (SwingUtilities.isRightMouseButton(e))
			  {
				  MovieContextMenu rightClickMenu = new MovieContextMenu(movie);
				  rightClickMenu.showMenu(posterPanel, e.getX(), e.getY());
			  }
			  
		  }
	  });

  }
  
  void addMovieDetail(Movie movie)
  {  
	  detailContentPanel.add(getItemPanel("Title:", movie.getTitle()));
	  detailContentPanel.add(getItemPanel("Year: ", String.valueOf(movie.getYear())));
	  detailContentPanel.add(getItemPanel("Rating: ", movie.getRating()));
	  detailContentPanel.add(getItemPanel("Runtime: ", String.valueOf(movie.getRuntime())));
	  detailContentPanel.add(getItemPanel("Genre: ", movie.getListAsString(movie.getGenre())));
	  detailContentPanel.add(getItemPanel("Director: ", movie.getListAsString(movie.getDirector())));
	  detailContentPanel.add(getItemPanel("Writer: ", movie.getListAsString(movie.getWriter())));
	  detailContentPanel.add(getItemPanel("Genre: ", movie.getListAsString(movie.getGenre())));
	  detailContentPanel.add(getItemPanel("Cast: ", movie.getListAsString(movie.getActor())));
	  detailContentPanel.add(getItemPanel("Plot: ", movie.getPlot()));
	  detailContentPanel.add(getItemPanel("Country: ", movie.getListAsString(movie.getCountry())));
    
	  JCheckBox watchedBox = new JCheckBox();
	  watchedBox.setSelected(movie.isWatched());
	  ImageIcon poster = new ImageIcon(movie.getPoster());
	  poster = ResizeImage.resizeImage(poster, Theme.posterSizeLarge);
	  JLabel posterLabel = new JLabel(poster);
	  posterPanel.add(posterLabel);
    
	  detailContentPanel.add(getItemPanel("Meta Score: ", String.valueOf(movie.getMetaScore())));
	  detailContentPanel.add(getItemPanel("IMDb Score: ", String.valueOf(movie.getImdbScore())));
	  detailContentPanel.add(getItemPanel("Watched: ", String.valueOf(movie.isWatched())));
	  detailContentPanel.add(getItemPanel("Meta Score: ", String.valueOf(movie.getMetaScore())));
	  detailContentPanel.add(getItemPanel("File Type: ", String.valueOf(movie.getFileType())));
	  detailContentPanel.add(getItemPanel("File Path: ", String.valueOf(movie.getFileLocation())));
	  
	  javax.swing.SwingUtilities.invokeLater(new Runnable() {
	   	   public void run() { 
	   	       detailPanel.getVerticalScrollBar().setValue(0);
	   	   }
	   	});
	  
	  sidePanel.add(this.posterPanel, "North");
	  sidePanel.add(this.detailPanel, "Center");
	  
  }
  
  private JTextPane getJTextArea(String text)
  {
	  JTextPane tBox1 = new JTextPane();
	  tBox1.setEditable(false);
	  tBox1.setContentType("text/html");
	  tBox1.setText(text);
	  tBox1.setBackground(Color.LIGHT_GRAY);
	  
	  int lineHeight = 20;
	  int height = lineHeight; 
	  int lineRunOff = 38;
	  int charCount = text.length();
	  
	  if (charCount>=lineRunOff)
	  {
		  height = (charCount/lineRunOff) * lineHeight;
		  
		  if (charCount%lineRunOff>=1)
		  {
			  height += lineHeight;
		  }
	  }
	  
	  Dimension tBoxSize = new Dimension(200,height);
	  
	  tBox1.setPreferredSize(tBoxSize);
	  tBox1.setMinimumSize(tBoxSize);
	  
	  return tBox1;
  }
  
  private JPanel getItemPanel(String label, String content)
  {
	  Dimension labelDim = new Dimension(80,5);
	  JPanel itemContainer = new JPanel();
	  itemContainer.setBackground(Color.CYAN);
	  
	  JTextPane itemLabelPane = getJTextArea("<html><b>" + label + "</b></html>");
	  
	  SimpleAttributeSet attribs = new SimpleAttributeSet();
	  StyleConstants.setAlignment(attribs, StyleConstants.ALIGN_RIGHT);
	  itemLabelPane.setParagraphAttributes(attribs, true);
	  
	  itemLabelPane.setMinimumSize(labelDim);
	  itemLabelPane.setMaximumSize(new Dimension(70, 400));
	  itemLabelPane.setPreferredSize(labelDim);
	  
	  itemContainer.setLayout(new BoxLayout(itemContainer, BoxLayout.X_AXIS));
	  itemContainer.add(itemLabelPane);
	  itemContainer.add(getJTextArea(content));
	  
	  return itemContainer;
  }
}
