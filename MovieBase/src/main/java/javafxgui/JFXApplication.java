package javafxgui;

//Java Imports
import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.StageStyle;
import javafx.scene.layout.BorderPane;
//Local Imports
import javafxgui.MenuBarView; 

public class MainWindowView extends Application
{
	Stage mainStage;
	Scene mainScene;
	BorderPane mainPane;
	
	public void start(Stage primaryStage)
	{
		prepScene();
		prepMainStage(primaryStage);
		mainStage.show();
	}
	
	private void prepMainStage(Stage primaryStage)
	{
		mainStage = primaryStage;
		mainStage.setTitle("vklx MovieBase");
		prepDimensions();
		mainStage.setScene(mainScene);
	}
	
	private void prepDimensions()
	{
		mainStage.setHeight(500);
		mainStage.setWidth(500);
	}
	
	private void prepScene()
	{
		mainPane = new BorderPane();
		
		mainPane.setTop(new MenuBarView());
		mainPane.setLeft(new MovieDetailView());
		mainPane.setCenter(new MoviesView());
		
		mainScene = new Scene(mainPane, 500, 500);
	}
}
