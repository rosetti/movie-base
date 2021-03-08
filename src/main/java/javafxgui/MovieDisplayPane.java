package javafxgui;


import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class MovieDisplayPane extends VBox {
    PosterGridView posterGridView;
    MovieTableView movieTableView;
    enum viewType {POSTER, TABLE};
    viewType currentViewType;


    public MovieDisplayPane(PosterGridView posterGridView, MovieTableView movieTableView) {
        this.posterGridView = posterGridView;
        this.movieTableView = movieTableView;
        setStyle("-fx-focus-color: transparent;");
        //setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        //setFitToWidth(true);
        currentViewType = viewType.TABLE;
        getChildren().add(movieTableView);
    }

    public void switchView() {
        getChildren().clear();

        if (currentViewType == viewType.TABLE) {
            getChildren().add(posterGridView);
            currentViewType = viewType.POSTER;
        } else {
            getChildren().add(movieTableView);
            currentViewType = viewType.TABLE;
        }
    }

}
