package gui;

//java imports
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

//local imports
import processManagers.clearMovieBaseManager;
import main.ApplicationMain;
import main.ProgramLaunch;
import movieControl.MovieBase;
import inputOutput.CSVOutput;

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
	    
	    JMenuItem exportAsCSVItem = new JMenuItem("Export Library (.CSV)");
	    exportAsCSVItem.addActionListener(new ActionListener()
	    	{
	    		public void actionPerformed(ActionEvent e)
	    		{
	    			JFileChooser fileBrowser = new JFileChooser();
	    			fileBrowser.setFileSelectionMode(JFileChooser.FILES_ONLY);
					fileBrowser.showSaveDialog(menuBar);
					String uri = fileBrowser.getSelectedFile().getPath();
					CSVOutput csvOutput = new CSVOutput(uri);
					csvOutput.writeBase();
					
	    		}
	    	});
	    
	    fileMenu.add(addMoviesItem);
	    fileMenu.add(clearMoviesItem);
	    fileMenu.add(exportAsCSVItem);
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
		 //Refresh Library
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
		 
		 //View Library XML File
		 JMenuItem viewAsXMLItem = new JMenuItem("Open XML Library File");
		 libraryMenu.add(viewAsXMLItem);
		 viewAsXMLItem.addActionListener(new ActionListener()
		 {
			 public void actionPerformed(ActionEvent e)
			 {
				 Desktop desktop = Desktop.getDesktop();
				 File xmlFile = new File(ApplicationMain.pwd + ApplicationMain.slash + "movieData.xml");
				 if (xmlFile.exists())
				 {
					 try 
					 {
						desktop.open(new File(ApplicationMain.pwd + ApplicationMain.slash + "movieData.xml"));
					 } 
					 catch (IOException e1) 
					 {
						JOptionPane.showMessageDialog(menuBar, "Unable to open XML file");
					}	 
				 }
				 
				 else
				 {
					 JOptionPane.showMessageDialog(menuBar.getParent(), "There is currently no xml library data");
				 }
				 
			 }
		 });
	 }

	 private void addViewMenuItems()
	 {
		 JMenuItem iconViewItem = new JCheckBoxMenuItem("Icon View");
		 JMenuItem listViewItem = new JCheckBoxMenuItem("List View");
		 viewMenu.add(iconViewItem);
		 viewMenu.add(listViewItem);
	 }
}
