package gui;

//java imports
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import main.ProgramLaunch;
import movieControl.MovieBase;
//local imports
import processManagers.clearMovieBaseManager;

public class MenuBar extends Observable
{
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenu aboutMenu;
	private JMenu libraryMenu;
	private JMenu viewMenu;
	
	public MenuBar()
	{
		fileMenu = new JMenu("File");
		aboutMenu = new JMenu("About");
		libraryMenu = new JMenu("Library");
		viewMenu = new JMenu("View");
		
		menuBar = new JMenuBar();
	    menuBar.setBackground(Theme.topBackground);
	    menuBar.add(fileMenu);
	    menuBar.add(libraryMenu);
	    menuBar.add(viewMenu);
	    menuBar.add(aboutMenu);
	    
	    addFileMenuItems();
	    addAboutMenuItems();
	    addLibraryMenuItems();
	    addViewMenuItems();
	}
	
	
	public JMenuBar getMenuBar()
	{
		return menuBar;
	}
	
	 private void addFileMenuItems()
	  {
	    JMenuItem addMoviesItem = new JMenuItem("Add Movies");
	    addMoviesItem.addActionListener(new ActionListener()
	    	{
	    		public void actionPerformed(ActionEvent e)
	    		{
	    			new ImportWindow();
	    		}
	    	});
	    
	    JMenuItem clearMoviesItem = new JMenuItem("Clear MovieBase Data");
	    clearMoviesItem.addActionListener(new ActionListener()
	    {
	    	public void actionPerformed(ActionEvent e)
	    	{
	    		new clearMovieBaseManager();
	    	}
	    });
	    
	    JMenuItem exitItem = new JMenuItem("Exit");
	    exitItem.addActionListener(new ActionListener()
	    {
	    	public void actionPerformed(ActionEvent e)
	    	{
	    		//are you sure you want to exit?
	    		System.exit(0);
	    	}
	    		
	    });
	    
	    fileMenu.add(addMoviesItem);
	    fileMenu.add(clearMoviesItem);
	    fileMenu.addSeparator();
	    fileMenu.add(exitItem);
	  }

	 private void addAboutMenuItems()
	 {
		 JMenuItem aboutItem = new JMenuItem("About");
		 aboutItem.addActionListener(new ActionListener() 
		{
			 public void actionPerformed(ActionEvent e)
			 {
				 //open about window
			 }
		});
		 
		 aboutMenu.add(aboutItem);
	 }
	 
	 private void addLibraryMenuItems()
	 {
		 JMenuItem refreshLibraryItem = new JMenuItem("Refresh Library");
		 
		 libraryMenu.add(refreshLibraryItem);
		 
		 refreshLibraryItem.addActionListener(new ActionListener() 
		 {
			 public void actionPerformed(ActionEvent e)
			 {
				 setChanged();
				 notifyObservers("refresh");
			 }
		 });
	 }

	 private void addViewMenuItems()
	 {
		 JMenuItem iconViewItem = new JMenuItem("Icon View");
		 JMenuItem listViewItem = new JMenuItem("List View");
		 
		 viewMenu.add(iconViewItem);
		 viewMenu.add(listViewItem);
	 }
}
