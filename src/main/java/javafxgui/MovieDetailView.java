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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Logger;

public class MovieDetailView extends VBox {

    TextField textField;
    TableView<Movie> movieDetails;
    VBox vSpacer;
    int padding;

    public MovieDetailView() {
        //setStyle("-fx-background-color: #00af1a");
        setMinWidth(300);
        setMaxWidth(300);
        padding = 20;
        //getChildren().add(new Label("Hello World!"));
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

            try {
                input = new FileInputStream(file);
            } catch (FileNotFoundException e) {
                //e.printStackTrace();
            }
        }

        image = new Image(input);
        double height = image.getHeight();
        double width = image.getWidth();

        double resizeFactor = width / 300;


        imageView = new ImageView(image);
        imageView.setFitWidth(300);
        imageView.setFitHeight(height / resizeFactor);
        setBackground(new Background(new BackgroundFill(Color.rgb(20, 20, 20), CornerRadii.EMPTY, Insets.EMPTY)));
        getChildren().add(imageView);

        movieDetails = new TableView<>();
        TableRow<Movie> titleRow = new TableRow<Movie>();


        Label title = new Label(movie.getTitle());
        //title.setFont(new Font(18));
        title.setStyle("-fx-font-weight: bold; -fx-font-size: 18");
        title.setWrapText(true);
        title.setMaxWidth(300);
        title.setTextFill(Color.WHITE);
        title.setPadding(new Insets(padding));

        //Label plot = new Label(movie.getPlot());
        Label plot = new Label(movie.getPlot());

        plot.setWrapText(true);
        plot.setTextAlignment(TextAlignment.JUSTIFY);
        plot.setTextFill(Color.WHITE);
        //plot.setMinHeight(1000);
        plot.setMaxWidth(300);
        //plot.setBackground(new Background(new BackgroundFill(Color.rgb(100,10,200), CornerRadii.EMPTY, Insets.EMPTY)));
        plot.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        plot.setPadding(new Insets(padding));


        VBox fourBox = new VBox();

        HBox rowOne = new HBox();
        Label directorLabel = new Label(movie.getListAsString(movie.getDirector()));
        directorLabel.setWrapText(true);
        directorLabel.setTextFill(Color.WHITE);
        directorLabel.setMinWidth(150);
        directorLabel.setMaxWidth(150);
        directorLabel.setPadding(new Insets(padding));

        Label writerLabel = new Label(movie.getListAsString(movie.getWriter()));
        writerLabel.setWrapText(true);
        writerLabel.setTextFill(Color.WHITE);
        writerLabel.setMinWidth(150);
        writerLabel.setMaxWidth(150);
        writerLabel.setPadding(new Insets(padding));

        rowOne.getChildren().addAll(directorLabel, writerLabel);

        HBox rowTwo = new HBox();
        Label actorLabel = new Label(movie.getListAsString(movie.getActor()));
        //actorLabel.setBackground(new Background(new BackgroundFill(Color.rgb(10,10,200), CornerRadii.EMPTY, Insets.EMPTY)));
        actorLabel.setWrapText(true);
        actorLabel.setTextFill(Color.WHITE);
        actorLabel.setMinWidth(150);
        actorLabel.setMaxWidth(150);
        actorLabel.setPadding(new Insets(padding));

        Label genreLabel = new Label(movie.getListAsString(movie.getGenre()));
        genreLabel.setWrapText(true);
        genreLabel.setTextFill(Color.WHITE);
        genreLabel.setMinWidth(150);
        genreLabel.setMaxWidth(150);
        genreLabel.setPadding(new Insets(padding));

        rowTwo.getChildren().addAll(actorLabel, genreLabel);

        fourBox.getChildren().addAll(rowOne, rowTwo);

        //imdbScore.setFont(new Font(18 ));
        //imdbScore.setStyle("-fx-font-weight: bold;");
        VBox box = new VBox();
        //box.setBackground(new Background (new BackgroundFill(Color.rgb(0,200,0), CornerRadii.EMPTY, Insets.EMPTY)));
        box.setBackground(new Background(new BackgroundFill(Color.rgb(10, 10, 20), CornerRadii.EMPTY, Insets.EMPTY)));
        box.setMaxWidth(300);
        box.getChildren().addAll(title, fourBox, plot);

        ScrollPane textDetails = new ScrollPane();
        textDetails.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        textDetails.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        //textDetails.setMinHeight(300);
        //textDetails.setMaxHeight(200);
        //textDetails.setFitToHeight(true);
        textDetails.setMaxWidth(300);
        textDetails.setContent(box);
        textDetails.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));

        getChildren().addAll(getScoreBox(movie), textDetails);
    }

    private HBox getScoreBox(Movie movie) {

        //Metacritic square
        StackPane metaCriticPane = new StackPane();
        metaCriticPane.setAlignment(Pos.CENTER);
        metaCriticPane.setMinWidth(150);
        String file = getClass().getClassLoader().getResource("metascore-square-small.png").getFile();
        FileInputStream input = null;
        Image image;
        ImageView imageView;
        Label metaScoreLabel = new Label(String.valueOf(movie.getMetaScore()));
        metaScoreLabel.setFont(new Font(18));
        metaScoreLabel.setStyle("-fx-font-weight: bold;");

        try {
            input = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            //Logger.getLogger().lo
        }
        if (input != null) {
            image = new Image(input);
            imageView = new ImageView(image);
            metaCriticPane.getChildren().addAll(imageView, metaScoreLabel);
        }

        //IMDB Star
        HBox imdbBox = new HBox();
        imdbBox.setAlignment(Pos.CENTER);
        imdbBox.setMinWidth(150);
        file = getClass().getClassLoader().getResource("imdb-star-small.png").getFile();
        try {
            input = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (input != null) {
            image = new Image(input);
            imageView = new ImageView(image);
            imdbBox.getChildren().add(imageView);
        }

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
        scoreBox.setBackground(new Background(new BackgroundFill(Color.rgb(42, 42, 53), CornerRadii.EMPTY, Insets.EMPTY)));
        scoreBox.setMinHeight(50);
        scoreBox.setMaxHeight(50);
        scoreBox.setMaxWidth(300);
        scoreBox.setAlignment(Pos.CENTER);

        scoreBox.getChildren().addAll(metaCriticPane, imdbBox);
        return scoreBox;
    }

}
