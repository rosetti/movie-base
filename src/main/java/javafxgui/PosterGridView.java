package javafxgui;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import movieControl.Movie;
import movieControl.MovieBase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Iterator;

public class PosterGridView extends ScrollPane {

    int loadedMovieCount;
    int imageWidth = 135;
    FlowPane movieGridPane;
    MovieContextMenu movieContextMenu;
    MovieDetailController movieDetailController;
    Runnable runnable;
    Thread thread;

    public PosterGridView(MovieContextMenu movieContextMenu, MovieDetailController movieDetailController) {
        this.movieContextMenu = movieContextMenu;
        this.movieDetailController = movieDetailController;
        movieGridPane = new FlowPane();
        movieGridPane.setAlignment(Pos.TOP_CENTER);
        movieGridPane.setHgap(30);
        movieGridPane.setVgap(10);
        setFitToHeight(true);
        setFitToWidth(true);
        movieGridPane.setVisible(true);

        setContent(movieGridPane);
        setStyle("-fx-focus-color: transparent;");
        movieGridPane.setBackground(new Background(new BackgroundFill(Color.rgb(240, 230, 240), CornerRadii.EMPTY, Insets.EMPTY)));
        setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    /**
     * Uses Platform.runLater as loading all the images can take some time
     * and slows the loading of the MovieTableView.
     * Doesn't seem to speed anything up anyway though...
     */
    public void loadMoviesFromMovieBase() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                movieGridPane.getChildren().clear();
                Iterator<Movie> i = MovieBase.getInstance().getIterator();
                loadedMovieCount = 0;
                while (i.hasNext()) {
                    Image image;
                    ImageView imageView;
                    InputStream input = null;
                    Movie movie = i.next();

                    try {
                        input = new FileInputStream(movie.getPoster());
                    } catch (FileNotFoundException e) {
                        input = getClass().getClassLoader().getResourceAsStream("image_not_found.jpg");
                    }

                    image = new Image(input);
                    double height = image.getHeight();
                    double width = image.getWidth();

                    double resizeFactor = width / imageWidth;

                    HBox box = new HBox();
                    box.setBackground(new Background(new BackgroundFill(Color.rgb(132, 89, 148), CornerRadii.EMPTY, Insets.EMPTY)));
                    box.setBorder(new Border(new BorderStroke(Color.rgb(132, 89, 148), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));

                    imageView = new ImageView(image);
                    imageView.setFitWidth(imageWidth);
                    imageView.setFitHeight(height / resizeFactor);
                    box.setMaxWidth(imageWidth);
                    box.setMaxHeight(height / resizeFactor);

                    imageView.setOnMouseClicked(t -> {
                        movieContextMenu.hideContextMenu();
                        if (t.getButton() == MouseButton.PRIMARY) {
                            movieDetailController.loadMovieDetail(movie);
                        }
                        if (t.getButton() == MouseButton.SECONDARY) {
                            movieContextMenu.showContextMenu(imageView, t.getScreenX(), t.getScreenY(), movie);
                        }
                    });
                    box.getChildren().add(imageView);
                    movieGridPane.getChildren().add(box);
                }

            }
        });

    }

    public void show() {
        loadMoviesFromMovieBase();
        setVisible(true);
    }


}
