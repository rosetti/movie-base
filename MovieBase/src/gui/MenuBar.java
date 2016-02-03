package gui;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBar 
{
	private JMenuBar menuBar;
	private JMenu fileMenu;
	
	public MenuBar()
	{
		menuBar = new JMenuBar();
	    menuBar.setBackground(Theme.topBackground);
	    menuBar.add(getMenu());
	    
	    addMenuItem();
	    
	}
	
	public JMenu getMenu()
	{
		this.fileMenu = new JMenu("File");
	    return this.fileMenu;
	}
	
	public JMenuBar getMenuBar()
	{
		return menuBar;
	}
	
	 private void addMenuItem()
	  {
	    JMenuItem addMoviesItem = new JMenuItem("Add Movies");
	    fileMenu.add(addMoviesItem);
	  }
}
