package javafxgui;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import movieControl.Movie;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 *
 */
public class MovieCheckView {

    Stage stage;
    int stageWidth = 350;
    int stageHeight = 600;
    int spacing = 5;
    VBox imagePane;
    ImageView posterImageView;
    Label titleLabel;
    Button yesButton;
    Button noButton;

    public MovieCheckView() {
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Check Retrieved Movie");

        stage.setMinWidth(stageWidth);
        stage.setMaxWidth(stageWidth);

        stage.setMinHeight(stageHeight);
        stage.setMaxHeight(stageHeight + 100);

        VBox mainPane = new VBox(spacing);
        mainPane.setAlignment(Pos.CENTER);

        imagePane = new VBox();
        imagePane.setAlignment(Pos.CENTER);
        imagePane.setMinHeight(50);
        imagePane.setMinWidth(50);
        posterImageView = new ImageView();
        imagePane.getChildren().add(posterImageView);

        titleLabel = new Label();
        titleLabel.setWrapText(true);
        titleLabel.setTextAlignment(TextAlignment.CENTER);
        titleLabel.setStyle("-fx-font-size: 16; -fx-font-weight: bold;");
        Label correctMovieLabel = new Label("Is this the correct Movie?");

        HBox buttonsPane = new HBox(spacing);
        buttonsPane.setAlignment(Pos.CENTER);
        yesButton = new Button("Yes");
        noButton = new Button("No");

        buttonsPane.getChildren().addAll(yesButton, noButton);

        mainPane.getChildren().addAll(imagePane, titleLabel, correctMovieLabel, buttonsPane);

        stage.setScene(new Scene(mainPane));

    }

    public void show(Movie movie) {
        double xAdd = (MainWindowView.width - stageWidth)/2;
        double yAdd = (MainWindowView.height - stageHeight)/2;
        stage.setX(MainWindowView.x + xAdd);
        stage.setY(MainWindowView.y + yAdd);
        loadMovie(movie);
        stage.showAndWait();
    }

    public void hide() {
        stage.hide();
    }

    public void loadMovie(Movie movie) {
        titleLabel.setText(movie.getTitle() + " - (" + movie.getYear() + ")");

        imagePane.getChildren().clear();
        imagePane.getChildren().add(getImageView(movie));
    }

    private ImageView getImageView(Movie movie) {
        FileInputStream input = null;
        Image image;
        try {
            input = new FileInputStream(movie.getPoster());
        } catch (FileNotFoundException e) {

            try {
                String file = getClass().getClassLoader().getResource("image_not_found.jpg").getFile();
                input = new FileInputStream(file);
            } catch (FileNotFoundException e1) {
                e.printStackTrace();
            }
        }

        image = new Image(input);
        double height = image.getHeight();
        double width = image.getWidth();

        double resizeFactor = width / 300;


        posterImageView = new ImageView(image);
        posterImageView.setFitWidth(300);
        posterImageView.setFitHeight(height / resizeFactor);
        return posterImageView;
    }

    public void setYesButtonAction(EventHandler handler) {
        yesButton.setOnAction(handler);
    }

    public void setNoButtonAction(EventHandler handler) {
        noButton.setOnAction(handler);
    }

    public void setStageCloseOperation(EventHandler handler) {
        stage.setOnCloseRequest(handler);
    }

}
