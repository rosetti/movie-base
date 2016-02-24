package gui;

//java imports
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

//local imports
import processManagers.clearMovieBaseManager;

public class MenuBar 
{
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenu aboutMenu;
	private JMenu libraryMenu;
	
	public MenuBar()
	{
		fileMenu = new JMenu("File");
		aboutMenu = new JMenu("About");
		libraryMenu = new JMenu("Library");
		
		menuBar = new JMenuBar();
	    menuBar.setBackground(Theme.topBackground);
	    menuBar.add(fileMenu);
	    menuBar.add(aboutMenu);
	    menuBar.add(libraryMenu);
	    
	    addFileMenuItems();
	    addAboutMenuItems();
	    addLibraryMenuItems();
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
	 }
}
