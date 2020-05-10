package javafxgui;

import inputOutput.DBSearchQuery;
import inputOutput.SQLiteDatabase;

/**
 * Created by Vin on 10/12/2017.
 */
public class SearchBarController {

    SearchBarView view;
    SearchBarModel model;
    MovieTableController movieTableController;
    AdvancedSearchController advancedSearchController;
    DBSearchQuery dbSearchQuery;

    public SearchBarController(SearchBarModel model, SearchBarView view, MovieTableController movieTableController, AdvancedSearchController advancedSearchController) {
        //Initialise Variables
        this.model = model;
        this.view = view;
        this.movieTableController = movieTableController;
        this.advancedSearchController = advancedSearchController;

        //Initialising Methods
        setClearFilterAction();
        setAdvancedSearchButtonAction();
        setSearchBoxAction();
        setWatchedCheckBoxAction();
        setUnwatchedCheckBoxAction();
        dbSearchQuery = DBSearchQuery.getInstance();
        String x = "";
    }

    private void setClearFilterAction() {
        view.setClearFiltersAction( e-> {
            view.clearSearchFields();
            DBSearchQuery.getInstance().resetQuery();
            SQLiteDatabase db = SQLiteDatabase.getInstance();
            db.loadAllMovies();
            movieTableController.loadMovies();
        });
    }

    private void setAdvancedSearchButtonAction() {
        view.setAdvancedSearchButtonAction(e -> {
            view.setSearchFieldText("");
            advancedSearchController.show();
        });
    }

    private void setSearchBoxAction() {
        view.setSearchBoxAction(e -> {
            DBSearchQuery.getInstance().resetQuery();
            dbSearchQuery.setActorSearchText(view.getSearchText());
            dbSearchQuery.setDirectorSearchText(view.getSearchText());
            dbSearchQuery.setTitleSearchText(view.getSearchText());
            searchGeneric();
        });
    }

    private void setWatchedCheckBoxAction() {
        view.setWatchedCheckBoxAction(e -> {
            dbSearchQuery.setWatched(view.isWatchedCheckBoxSelected());
            if (view.getSearchText().equals("")) {
                searchAdvanced();
            } else {
                searchGeneric();
            }
        });
    }

    private void setUnwatchedCheckBoxAction() {
        view.setUnwatchedCheckBoxAction(e -> {
            dbSearchQuery.setUnwatched(view.isUnwatchedCheckBoxSelected());
            if (view.getSearchText().equals("")) {
                searchAdvanced();
            } else {
                searchGeneric();
            }
        });
    }

    private void searchGeneric() {
        SQLiteDatabase db = SQLiteDatabase.getInstance();
        db.loadGeneralFilteredMovies();
        movieTableController.loadMovies();
    }

    private void searchAdvanced() {
        SQLiteDatabase db = SQLiteDatabase.getInstance();
        db.loadAdvancedFilteredMovies();
        movieTableController.loadMovies();
    }
}
