package javafxgui;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import movieControl.Movie;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MovieDetailView extends VBox {

	TextField textField;
	TableView<Movie> movieDetails;

	public MovieDetailView() {
		//setStyle("-fx-background-color: #00af1a");
		setMinWidth(300);
		//getChildren().add(new Label("Hello World!"));
		setAlignment(Pos.TOP_CENTER);
		setVisible(true);
	}

	private void buildView() {

	}
	/**
	 * Method loads a movie into the detail view
	 * @param movie
	 */
	public void loadMovieDetail(Movie movie) {
		getChildren().clear();

		FileInputStream input = null;
		Image image;
		ImageView imageView;
		try {
			input = new FileInputStream(movie.getPoster());
		} catch (FileNotFoundException e) {
			//e.printStackTrace();
		}
		if (input == null) {

			String file = getClass().getClassLoader().getResource("image_not_found.jpg").getFile();
			System.out.println(file);

			try {
				input = new FileInputStream(file);
			} catch (FileNotFoundException e) {
				//e.printStackTrace();
			}
		}

		image = new Image(input);
		double height = image.getHeight();
		double width = image.getWidth();

		double resizeFactor = width / 290;


		imageView = new ImageView(image);
		imageView.setFitWidth(290);
		imageView.setFitHeight(height / resizeFactor);
		getChildren().add(imageView);

		movieDetails = new TableView<>();
		TableRow<Movie> titleRow = new TableRow<Movie>();

		//movieDetails.getRo

	}


}
