package javafxgui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import movieControl.Movie;
import resources.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class MovieDetailView extends VBox {

    TextField textField;
    TableView<Movie> movieDetails;
    VBox vSpacer;
    Color bgColor = Color.rgb(222, 208, 227);
    Color darkBgColor = Color.rgb(42, 42, 53);
    Color textColor = Color.rgb(21, 41, 36);

    Insets insetPadding = new Insets(4, 20,4,10);
    int paneWidth = 300;
    int imageWidth = 250;

    public MovieDetailView() {
        setMinWidth(paneWidth);
        setMaxWidth(paneWidth);
        setAlignment(Pos.TOP_CENTER);
        setVisible(true);
        vSpacer = new VBox();
        vSpacer.setMinHeight(15);
        vSpacer.setMaxHeight(15);
    }

    private void buildView() {

    }

    /**
     * Method loads a movie into the detail view
     *
     * @param movie
     */
    public void loadMovieDetail(Movie movie) {
        getChildren().clear();
        Image image;
        ImageView imageView;
        InputStream input = null;

        try {
            input = new FileInputStream(movie.getPoster());
        } catch (FileNotFoundException e) {
            input = getClass().getClassLoader().getResourceAsStream("image_not_found.jpg");
        }

        image = new Image(input);
        double height = image.getHeight();
        double width = image.getWidth();

        double resizeFactor = width / imageWidth;


        imageView = new ImageView(image);
        imageView.setFitWidth(imageWidth);
        imageView.setFitHeight(height / resizeFactor);
        setBackground(new Background(new BackgroundFill(bgColor, CornerRadii.EMPTY, Insets.EMPTY)));
        getChildren().add(imageView);

        Label title = new Label(movie.getTitle());
        title.setStyle("-fx-font-weight: bold; -fx-font-size: 18");
        title.setWrapText(true);
        title.setMaxWidth(paneWidth);
        title.setTextFill(textColor);
        title.setPadding(insetPadding);
        title.setBackground(new Background(new BackgroundFill(bgColor, CornerRadii.EMPTY, Insets.EMPTY)));

        Label plot = new Label(movie.getPlot());

        plot.setWrapText(true);
        plot.setTextAlignment(TextAlignment.JUSTIFY);
        plot.setTextFill(textColor);
        plot.setMaxWidth(paneWidth);
        plot.setBackground(new Background(new BackgroundFill(bgColor, CornerRadii.EMPTY, Insets.EMPTY)));
        plot.setPadding(insetPadding);


        VBox fourBox = new VBox();
        fourBox.setBackground(new Background(new BackgroundFill(bgColor, CornerRadii.EMPTY, Insets.EMPTY)));

        HBox rowOne = new HBox();
        Label directorLabel = new Label(movie.getListAsString(movie.getDirector()));
        directorLabel.setWrapText(true);
        directorLabel.setTextFill(textColor);
        directorLabel.setMinWidth(150);
        directorLabel.setMaxWidth(150);
        directorLabel.setPadding(insetPadding);

        Label writerLabel = new Label(movie.getListAsString(movie.getWriter()));
        writerLabel.setWrapText(true);
        writerLabel.setTextFill(textColor);
        writerLabel.setMinWidth(150);
        writerLabel.setMaxWidth(150);
        writerLabel.setPadding(insetPadding);

        rowOne.getChildren().addAll(directorLabel, writerLabel);

        HBox rowTwo = new HBox();
        Label actorLabel = new Label(movie.getListAsString(movie.getActor()));
        //actorLabel.setBackground(new Background(new BackgroundFill(Color.rgb(10,10,200), CornerRadii.EMPTY, Insets.EMPTY)));
        actorLabel.setWrapText(true);
        actorLabel.setTextFill(textColor);
        actorLabel.setMinWidth(150);
        actorLabel.setMaxWidth(150);
        actorLabel.setPadding(insetPadding);

        Label genreLabel = new Label(movie.getListAsString(movie.getGenre()));
        genreLabel.setWrapText(true);
        genreLabel.setTextFill(textColor);
        genreLabel.setMinWidth(150);
        genreLabel.setMaxWidth(150);
        genreLabel.setPadding(insetPadding);

        rowTwo.getChildren().addAll(actorLabel, genreLabel);

        fourBox.getChildren().addAll(rowOne, rowTwo);

        VBox box = new VBox();
        box.setBackground(new Background(new BackgroundFill(Color.rgb(10, 10, 20), CornerRadii.EMPTY, Insets.EMPTY)));
        box.setMaxWidth(paneWidth);
        box.getChildren().addAll(title, fourBox, plot);

        ScrollPane textDetails = new ScrollPane();
        textDetails.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        textDetails.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        textDetails.setMaxWidth(paneWidth);
        textDetails.setContent(box);
        textDetails.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));

        getChildren().addAll(getScoreBox(movie), textDetails);
    }

    private HBox getScoreBox(Movie movie) {

        //Metacritic square
        StackPane metaCriticPane = new StackPane();
        metaCriticPane.setAlignment(Pos.CENTER);
        metaCriticPane.setMinWidth(150);
        //String file = getClass().getClassLoader().getResource("metascore-square-small.png").getFile();
        //System.out.println("Filepath: " + file);
        FileInputStream input = null;
        Image image;
        ImageView imageView;
        Label metaScoreLabel = new Label(movie.getMetaScore() > 0 ? String.valueOf(movie.getMetaScore()) : "N/A");
        metaScoreLabel.setFont(new Font(18));
        metaScoreLabel.setStyle("-fx-font-weight: bold;");

        image = new Image(getClass().getClassLoader().getResourceAsStream("metascore-square-small.png"));
        imageView = new ImageView(image);
        metaCriticPane.getChildren().addAll(imageView, metaScoreLabel);


        //IMDB Star
        HBox imdbBox = new HBox();
        imdbBox.setAlignment(Pos.CENTER);
        imdbBox.setMinWidth(150);
        image = new Image(getClass().getClassLoader().getResourceAsStream("imdb-star-small.png"));
        imageView = new ImageView(image);
        imdbBox.getChildren().add(imageView);


        Label imdbScore = new Label(String.valueOf(movie.getImdbScore()));
        imdbScore.setMinWidth(40);
        imdbScore.setTextAlignment(TextAlignment.RIGHT);
        imdbScore.setAlignment(Pos.CENTER);
        imdbScore.setFont(new Font(18));
        imdbScore.setStyle("-fx-font-weight: bold;");
        imdbScore.setTextFill(Color.WHITE);
        imdbBox.getChildren().add(imdbScore);

        //Score Box
        HBox scoreBox = new HBox();
        scoreBox.setBackground(new Background(new BackgroundFill(darkBgColor, CornerRadii.EMPTY, Insets.EMPTY)));
        scoreBox.setMinHeight(50);
        scoreBox.setMaxHeight(50);
        scoreBox.setMaxWidth(300);
        scoreBox.setAlignment(Pos.CENTER);

        scoreBox.getChildren().addAll(metaCriticPane, imdbBox);
        return scoreBox;
    }

}
