package javafxgui;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class MovieDetailView extends VBox
{
	public MovieDetailView()
	{
		setStyle("-fx-background-color: #52CAF2;");
		setMinWidth(100);
		getChildren().add(new Label("Hello World!"));
	}
}
