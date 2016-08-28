package javafxgui;

import javafx.scene.control.TableColumn;
//java imports
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
//local imports
import movieControl.Movie;
import movieControl.MovieBase;
import main.ProgramLaunch;

/*
 * JavaFX pane to hold the main view, table of movies. 
 */

public class MoviesView extends HBox
{
	TableView<Movie> movieTable = new TableView<Movie>();
	//Table Columns
	TableColumn<Movie, String> colTitle;
	TableColumn<Movie, Integer> colYear;
	TableColumn<Movie, Float> colImdbScore;
	TableColumn<Movie, Integer> colRuntime;
	TableColumn<Movie, Integer> colMetaScore;
	TableColumn<Movie, Boolean> colWatched;
	TableColumn<Movie, String> colFileType;
	
	public MoviesView()
	{
		addMovies();
		makeColumns();
		movieTable.getColumns().addAll(colTitle, colYear, colRuntime, colWatched, colMetaScore, colImdbScore, colFileType);
		getChildren().add(movieTable);
	}
	
	//TODO: Fix this shit
	private void addMovies()
	{
		for (Movie i: ProgramLaunch.getCoreBase().getMovieBase())
		{
			movieTable.getItems().add(i);
		}
	}
	
	private void makeColumns()
	{
		colTitle = new TableColumn<Movie, String>("Title");
		colTitle.setCellValueFactory(new PropertyValueFactory<Movie, String>("title"));
		
		colYear = new TableColumn<Movie, Integer>("Year");
		colYear.setCellValueFactory(new PropertyValueFactory<Movie, Integer>("year"));
		
		colImdbScore = new TableColumn<Movie, Float>("IMDb Score");
		colImdbScore.setCellValueFactory(new PropertyValueFactory<Movie, Float>("imdbScore"));
		
		colRuntime = new TableColumn<Movie, Integer>("Runtime");
		colRuntime.setCellValueFactory(new PropertyValueFactory<Movie, Integer>("runtime"));
		
		colMetaScore = new TableColumn<Movie, Integer>("Metascore");
		colMetaScore.setCellValueFactory(new PropertyValueFactory<Movie, Integer>("metaScore"));
		
		colWatched = new TableColumn<Movie, Boolean>("Watched?");
		colWatched.setCellValueFactory(new PropertyValueFactory<Movie, Boolean>("watched"));
		
		colFileType = new TableColumn<Movie, String>("File Type");
		colFileType.setCellValueFactory(new PropertyValueFactory<Movie, String>("fileType"));
		
	}
}
