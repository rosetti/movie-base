package javafxgui;

//java imports

import java.util.Iterator;


import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.control.TableColumn;

//local imports
import movieControl.Movie;
import movieControl.MovieBase;

/*
 * JavaFX pane to hold the main view, table of movies. 
 */

public class MovieTableView extends HBox {
    TableView<Movie> movieTable = new TableView<Movie>();
    //Table Columns
    TableColumn<Movie, String> colTitle;
    TableColumn<Movie, Integer> colYear;
    TableColumn<Movie, Float> colImdbScore;
    TableColumn<Movie, Integer> colRuntime;
    TableColumn<Movie, Integer> colMetaScore;
    TableColumn<Movie, String> colWatched;
    TableColumn<Movie, String> colFileType;

    //Context Menu
    ContextMenu contextMenu;
    MenuItem menuItemYoutube;
    MenuItem menuItemPlay;
    MenuItem menuItemMarkWatched;
    MenuItem menuItemOpenExplorer;
    MenuItem menuItemOpenEdit;

    public MovieTableView() {
        loadMoviesFromMovieBase();
        makeColumns();
        createContextMenu();
        movieTable.getColumns().addAll(colTitle, colYear, colRuntime, colWatched, colMetaScore, colImdbScore, colFileType);
        movieTable.setStyle("-fx-focus-color: transparent;");
        movieTable.setPrefWidth(2000);
        movieTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        getChildren().add(movieTable);
    }

    public void loadMoviesFromMovieBase() {
        boolean removed = getChildren().removeAll(movieTable);
        movieTable.getItems().clear();
        Iterator<Movie> i = MovieBase.getInstance().getIterator();
        while (i.hasNext()) {
            movieTable.getItems().add(i.next());
        }
        if (removed) {
            movieTable.getColumns().clear();
            makeColumns();
            movieTable.getColumns().addAll(colTitle, colYear, colRuntime, colWatched, colMetaScore, colImdbScore, colFileType);
            movieTable.setPrefWidth(2000);
            movieTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            getChildren().add(movieTable);
        }
    }

    private void createContextMenu() {
        contextMenu = new ContextMenu();
        menuItemPlay = new MenuItem("Play");
        menuItemYoutube = new MenuItem("Watch Trailer on Youtube");

        menuItemMarkWatched = new MenuItem("Mark as Watched/Unwatched");
        menuItemOpenExplorer = new MenuItem("Open In Explorer");
        menuItemOpenEdit = new MenuItem("Edit Entry");

        contextMenu.getItems().addAll(menuItemPlay, menuItemYoutube, menuItemMarkWatched, menuItemOpenExplorer, menuItemOpenEdit);

    }

    public void showContextMenu(double x, double y) {
        contextMenu.show(this, x, y);
    }

    public void hideContextMenu() {
        contextMenu.hide();
    }

    public void setYoutubeAction(EventHandler handler) {
        menuItemYoutube.setOnAction(handler);
    }

    public void setPlayAction(EventHandler handler) {
        menuItemPlay.setOnAction(handler);
    }

    public void setExplorerAction(EventHandler handler) {
        menuItemOpenExplorer.setOnAction(handler);
    }

    public void setRowClickedAction(ChangeListener action) {
        movieTable.getSelectionModel().selectedItemProperty().addListener(action);
    }

    public void setContextMenuAction(EventHandler handler) {
        movieTable.addEventHandler(MouseEvent.MOUSE_CLICKED, handler);
    }

    private void makeColumns() {
        colTitle = new TableColumn<Movie, String>("Title");
        colTitle.setPrefWidth(200);
        colTitle.setMinWidth(200);

        colTitle.setCellValueFactory(new PropertyValueFactory<Movie, String>("title"));

        colYear = new TableColumn<Movie, Integer>("Year");
        colYear.setStyle("-fx-alignment: CENTER;");
        colYear.setMaxWidth(60);
        colYear.setMinWidth(60);
        colYear.setCellValueFactory(new PropertyValueFactory<Movie, Integer>("year"));

        colImdbScore = new TableColumn<Movie, Float>("IMDb Score");
        colImdbScore.setStyle("-fx-alignment: CENTER;");
        colImdbScore.setMaxWidth(70);
        colImdbScore.setMinWidth(70);
        colImdbScore.setCellValueFactory(new PropertyValueFactory<Movie, Float>("imdbScore"));

        colRuntime = new TableColumn<Movie, Integer>("Runtime");
        colRuntime.setStyle("-fx-alignment: CENTER;");
        colRuntime.setMaxWidth(60);
        colRuntime.setMinWidth(60);
        colRuntime.setCellValueFactory(new PropertyValueFactory<Movie, Integer>("runtime"));

        colMetaScore = new TableColumn<Movie, Integer>("Metascore");
        colMetaScore.setStyle("-fx-alignment: CENTER;");
        colMetaScore.setMaxWidth(70);
        colMetaScore.setMinWidth(70);
        colMetaScore.setCellValueFactory(new PropertyValueFactory<Movie, Integer>("metaScore"));

        colWatched = new TableColumn<Movie, String>("Watched?");
        colWatched.setStyle("-fx-alignment: CENTER;");
        colWatched.setMaxWidth(60);
        colWatched.setMinWidth(60);

        colWatched.setCellValueFactory(cellData -> {
            String tickCross;
            if (cellData.getValue().isWatched()) {
                tickCross = "✔";
            } else {
                tickCross = "✘";
            }

            return new ReadOnlyStringWrapper(tickCross);
        });

        colFileType = new TableColumn<Movie, String>("File Type");
        colFileType.setCellValueFactory(new PropertyValueFactory<Movie, String>("fileType"));
        colFileType.setStyle("-fx-alignment: CENTER;");
        colFileType.setMaxWidth(60);
        colFileType.setMinWidth(60);

    }
}
