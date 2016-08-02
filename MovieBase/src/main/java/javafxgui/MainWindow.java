package javafxgui;

import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.control.*;

public class MainWindow extends Application
{
	Stage mainStage;
	
	public MainWindow()
	{
		
	}
	
	public void start(Stage primaryStage)
	{
		setUpMainStage();
		mainStage.show();
	}
	
	private void setUpMainStage()
	{
		mainStage = new Stage();
		mainStage.setTitle("vklx MovieBase");
		mainStage.setHeight(500);
		mainStage.setWidth(500);
	}
	
}
