package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


public class MenuBar 
{
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenu aboutMenu;
	
	public MenuBar()
	{
		fileMenu = new JMenu("File");
		aboutMenu = new JMenu("About");
		
		menuBar = new JMenuBar();
	    menuBar.setBackground(Theme.topBackground);
	    menuBar.add(fileMenu);
	    menuBar.add(aboutMenu);
	    
	    addFileMenuItems();
	    addAboutMenuItems();
	    
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
	    		//message box, sure you wanna clear?
	    		//if yes, delete all images, and XML file
	    		//reload mainwindow
	    	}
	    });
	    
	    JMenuItem exitItem = new JMenuItem("Exit");
	    exitItem.addActionListener(new ActionListener()
	    {
	    	public void actionPerformed(ActionEvent e)
	    	{
	    		//exit method
	    	}
	    		
	    });
	    
	    fileMenu.add(clearMoviesItem);
	    fileMenu.add(addMoviesItem);
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
}
