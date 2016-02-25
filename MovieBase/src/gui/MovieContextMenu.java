package gui;

//java imports
import javax.swing.JPopupMenu;
import javax.swing.JPanel;
import javax.swing.JMenuItem;
import java.awt.Desktop;
import java.awt.event.*;
import java.io.File;
import javax.swing.JOptionPane;
import java.awt.Component;

//local imports
import movieControl.Movie;

public class MovieContextMenu 
{
	JPopupMenu rightClickMenu = new JPopupMenu();
	JMenuItem playMovie;
	JMenuItem editMovie;
	JMenuItem deleteMovie;
	Movie movie;
	Desktop desktop;
	
	public MovieContextMenu(Movie movie)
	{
		this.movie = movie;
		makeMenuItems();
		rightClickMenu.add(playMovie);
		rightClickMenu.add(editMovie);
		rightClickMenu.add(deleteMovie);
		desktop = Desktop.getDesktop();
	}
	
	public void showMenu(Component component, int x, int y)
	{

		rightClickMenu.show(component, x, y);
	}
	
	private void makeMenuItems()
	{
		playMovie = new JMenuItem("Play Movie");
		
		playMovie.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					desktop.open(new File(movie.getFileLocation()));
				}
				
				catch (Exception exception)
				{
					JOptionPane.showMessageDialog(new JPanel(), "Darn! Unable to play movie!");
				}
			}
		});
		
		editMovie = new JMenuItem("Edit Movie");
		
		editMovie.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//TODO: add edit movie method
			}
		});
		
		deleteMovie = new JMenuItem("Delete Movie From MovieBase");
		
		deleteMovie.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//TODO: add delete method
			}
		});
		
	}
	
}
