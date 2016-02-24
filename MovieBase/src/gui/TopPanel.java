package gui;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TopPanel
{
  private JPanel topBar;
  private JLabel searchLabel;
  private JTextField searchField;
  private JLabel watchedLabel;
  private JCheckBox watchedBox;
  private JComboBox<String> genre1;
  private JComboBox<String> genre2;
  
  public TopPanel()
  {
    makeTopBar();
    makeGenre1();
    makeGenre2();
    addComponents();
  }
  
  private void makeTopBar()
  {
    this.topBar = new JPanel();
    this.topBar.setBackground(Theme.topBackground);
    this.topBar.setPreferredSize(new Dimension(100, 30));
    this.topBar.setLayout(new BoxLayout(this.topBar, 0));
  }
  
  private void addComponents()
  {
    this.topBar.add(getSearchLabel());
    this.topBar.add(getSearchField());
    this.topBar.add(getWatchedLabel());
    this.topBar.add(getWatchedBox());
    this.topBar.add(genre1);
    this.topBar.add(genre2);
  }
  
  private JLabel getSearchLabel()
  {
    this.searchLabel = new JLabel("Search: ");
    
    Dimension size = new Dimension(100, 40);
    this.searchLabel.setSize(size);
    this.searchLabel.setMaximumSize(size);
    this.searchLabel.setMinimumSize(size);
    this.searchLabel.setPreferredSize(size);
    return this.searchLabel;
  }
  
  private JTextField getSearchField()
  {
    this.searchField = new JTextField();
    
    Dimension size = new Dimension(200, 30);
    this.searchField.setSize(size);
    this.searchField.setMaximumSize(size);
    this.searchField.setMinimumSize(size);
    this.searchField.setPreferredSize(size);
    
    return this.searchField;
  }
  
  private JLabel getWatchedLabel()
  {
    this.watchedLabel = new JLabel("Watched: ");
    return this.watchedLabel;
  }
  
  private JCheckBox getWatchedBox()
  {
    this.watchedBox = new JCheckBox();
    this.watchedBox.setBackground(Theme.topBackground);
    return this.watchedBox;
  }
  
  private void makeGenre1()
  {
    genre1 = new JComboBox<>();
    
    Dimension size = new Dimension(150, 30);
    genre1.setSize(size);
    genre1.setMaximumSize(size);
    genre1.setMinimumSize(size);
    genre1.setPreferredSize(size);
  }
  
  private void makeGenre2()
  {
	Dimension size = new Dimension(150, 30);
	genre2 = new JComboBox<>();
    
    genre2.setSize(size);
    genre2.setMaximumSize(size);
    genre2.setMinimumSize(size);
    genre2.setPreferredSize(size);
    
  }
  
  public JPanel getPanel()
  {
    return this.topBar;
  }
  
}
