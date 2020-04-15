package javafxgui;

import inputOutput.SQLiteDatabase;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

/**
 * Created by Vin on 09/12/2017.
 */
public class SearchBarView  extends HBox{

    TextField searchField;
    CheckBox watchedCheckBox;
    CheckBox unwatchedCheckBox;
    MovieTableView tableView;
    MovieTableController movieTableController;
    Button advancedSearchButton;
    AdvancedSearchView advSearchView;
    AdvancedSearchModel advSearchModel;
    AdvancedSearchController advSearchController;
    Button clearFiltersButton;

    public SearchBarView(MovieTableView tableView, MovieTableController movieTableController) {
        this.tableView = tableView;
        this.movieTableController = movieTableController;
        searchField = new TextField();
        searchField.setMinHeight(5);
        addCheckboxes();
        addAdvancedSearchButton();
        makeClearFiltersButton();
        setSpacing(20);
        setAlignment(Pos.CENTER);
        getChildren().addAll(searchField, watchedCheckBox, unwatchedCheckBox, advancedSearchButton, clearFiltersButton);

        searchField.setOnAction(e -> {
            search();
        });
    }

    private void getParenthmm() {
        //getParent()
    }

    private void addAdvancedSearchButton() {
        advancedSearchButton = new Button("Advanced Search");
        advancedSearchButton.setOnAction(e -> {
            System.out.println("Advanced Search!");
            searchField.setText("");
            if (advSearchView == null || advSearchView == null || advSearchController == null) {
                advSearchView = new AdvancedSearchView(getLayoutX(), getLayoutY());
                advSearchModel = new AdvancedSearchModel();
                advSearchController = new AdvancedSearchController(advSearchView, advSearchModel, movieTableController);
            }
            advSearchController.show();
        });
    }

    private void makeClearFiltersButton() {
        clearFiltersButton = new Button("Clear Filters");
    }

    public void clearSearchFields() {
        searchField.clear();
        watchedCheckBox.setSelected(false);
        unwatchedCheckBox.setSelected(false);
    }

    public void setClearFiltersAction(EventHandler handler) {
        clearFiltersButton.setOnAction(handler);
    }

    private void addCheckboxes() {
        watchedCheckBox = new CheckBox("Watched");
        watchedCheckBox.setAlignment(Pos.CENTER_RIGHT);
        watchedCheckBox.setOnAction(e -> {
            search();
        });

        unwatchedCheckBox = new CheckBox("Unwatched");
        unwatchedCheckBox.setAlignment(Pos.CENTER_RIGHT);
        unwatchedCheckBox.setOnAction(e -> {
            //
            search();
        });
    }

    public void setWatchedCheckBoxAction(EventHandler handler) {

    }

    private void search(){
        if (advSearchController != null) {
            advSearchController.setWatchedUnwatched(watchedCheckBox.isSelected(), unwatchedCheckBox.isSelected());
        }
        SQLiteDatabase db = SQLiteDatabase.getInstance();
        db.loadFilteredMovies(searchField.getText(), watchedCheckBox.isSelected(), unwatchedCheckBox.isSelected(), "", "", "", null);
        tableView.loadMoviesFromMovieBase();
    }

    public void setSearchBoxAction(EventHandler<ActionEvent> handler) {
        searchField.setOnAction(handler);
    }

}
