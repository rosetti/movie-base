package javafxgui;

import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;

public class MenuBarView extends MenuBar
{

	MenuBarView mainMenuBar;
	
	Menu fileMenu;
	Menu libraryMenu;
	Menu viewMenu;
	Menu aboutMenu;
	
	public MenuBarView getMenuBar()
	{
		if (this.mainMenuBar == null)
		{
			mainMenuBar = new MenuBarView();
		}
		return mainMenuBar;
	}
	
	public MenuBarView()
	{
		makeFileMenu();
		makeLibraryMenu();
		makeViewMenu();
		makeAboutMenu();
		
		addMenus();
	}
	
	private void addMenus()
	{
		getMenus().addAll(fileMenu, libraryMenu, viewMenu, aboutMenu);
	}
	
	private void makeFileMenu()
	{
		fileMenu = new Menu("_File");
		
		MenuItem addMovies = new MenuItem("Add Movies");
		MenuItem clearMovieBaseData = new MenuItem("Clear MovieBase Data");
		MenuItem exit = new MenuItem("Exit");
		
		fileMenu.getItems().addAll(addMovies, clearMovieBaseData, new SeparatorMenuItem(), exit);
	}
	
	private void makeLibraryMenu()
	{
		libraryMenu = new Menu("_Library");
	}
	
	private void makeViewMenu()
	{
		viewMenu = new Menu("_View");
	}
	
	private void makeAboutMenu()
	{
		aboutMenu = new Menu("_About");
	}
	
}
