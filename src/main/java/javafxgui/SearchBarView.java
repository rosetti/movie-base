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
        makeAdvancedSearchButton();
        makeClearFiltersButton();
        setSpacing(20);
        setAlignment(Pos.CENTER);
        getChildren().addAll(searchField, watchedCheckBox, unwatchedCheckBox, advancedSearchButton, clearFiltersButton);

    }

    private void makeAdvancedSearchButton() {
        advancedSearchButton = new Button("Advanced Search");
    }

    public void setAdvancedSearchButtonAction(EventHandler handler) {
        advancedSearchButton.setOnAction(handler);
    }

    public void setSearchFieldText(String str) {
        searchField.setText(str);
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

        unwatchedCheckBox = new CheckBox("Unwatched");
        unwatchedCheckBox.setAlignment(Pos.CENTER_RIGHT);
    }

    public void setWatchedCheckBoxAction(EventHandler handler) {
        watchedCheckBox.setOnAction(handler);
    }

    public void setUnwatchedCheckBoxAction(EventHandler handler) {
        unwatchedCheckBox.setOnAction(handler);
    }
/*
    private void search(){
        if (advSearchController != null) {
            advSearchController.setWatchedUnwatched(watchedCheckBox.isSelected(), unwatchedCheckBox.isSelected());
        }
        SQLiteDatabase db = SQLiteDatabase.getInstance();
        db.loadAdvancedFilteredMovies(searchField.getText(), watchedCheckBox.isSelected(), unwatchedCheckBox.isSelected(), "", "", "", null);
        tableView.loadMoviesFromMovieBase();
    }
*/
    public void setSearchBoxAction(EventHandler<ActionEvent> handler) {
        searchField.setOnAction(handler);
    }

    public String getSearchText() {
        return searchField.getText();
    }

    public boolean isWatchedCheckBoxSelected() {
        return watchedCheckBox.isSelected();
    }

    public boolean isUnwatchedCheckBoxSelected() {
        return unwatchedCheckBox.isSelected();
    }
}
