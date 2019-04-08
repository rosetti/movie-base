package javafxgui;

//Java Imports
import javafx.application.*;
import javafx.scene.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.*;
import javafx.scene.layout.BorderPane;
//Local Imports


public class MainWindowView
{
	Stage mainStage;
	Scene mainScene;
	BorderPane mainPane;
	MovieTableView tableView;
	MovieDetailView detailView;
	MenuBarView menuBar;

	public MainWindowView(MovieTableView tableView, MovieDetailView detailView, MenuBarView menuBar) {
		this.tableView = tableView;
		this.detailView = detailView;
		this.menuBar = menuBar;
	}

	public void start(Stage primaryStage)
	{
		prepScene();
		prepMainStage(primaryStage);
		mainStage.show();
	}
	
	private void prepMainStage(Stage primaryStage)
	{
		mainStage = primaryStage;
		mainStage.setTitle("VKLX MovieBase");
		prepDimensions();
		mainStage.setScene(mainScene);
	}
	
	private void prepDimensions()
	{
		mainStage.setHeight(700);
		mainStage.setWidth(1100);
	}
	
	private void prepScene()
	{
		mainPane = new BorderPane();

		HBox topBar = new HBox();



		topBar.getChildren().addAll(menuBar, new SearchBarView());



		mainPane.setTop(topBar);
		mainPane.setLeft(detailView);
		mainPane.setCenter(tableView);
		detailView.setVisible(true);
		mainScene = new Scene(mainPane);
	}

}
