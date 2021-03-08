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
    PosterGridController posterGridController;
    AdvancedSearchController advancedSearchController;
    DBSearchQuery dbSearchQuery;
    MovieDisplayPane movieDisplayPane;

    public SearchBarController(SearchBarModel model, SearchBarView view, MovieTableController movieTableController, AdvancedSearchController advancedSearchController, PosterGridController posterGridController, MovieDisplayPane movieDisplayPane) {
        //Initialise Variables
        this.model = model;
        this.view = view;
        this.movieTableController = movieTableController;
        this.advancedSearchController = advancedSearchController;
        this.posterGridController = posterGridController;
        this.movieDisplayPane = movieDisplayPane;

        //Initialising Methods
        setClearFilterAction();
        setAdvancedSearchButtonAction();
        setSearchBoxAction();
        setWatchedCheckBoxAction();
        setUnwatchedCheckBoxAction();
        setChangeViewButtonAction();
        dbSearchQuery = DBSearchQuery.getInstance();
    }

    private void setClearFilterAction() {
        view.setClearFiltersAction( e-> {
            advancedSearchController.clearView();
            resetTable();
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
            dbSearchQuery.setWriterSearchText(view.getSearchText());
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
        posterGridController.loadMovies();
    }

    private void searchAdvanced() {
        SQLiteDatabase db = SQLiteDatabase.getInstance();
        db.loadAdvancedFilteredMovies();
        movieTableController.loadMovies();
        posterGridController.loadMovies();
    }

    private void resetTable() {
        view.clearSearchFields();
        DBSearchQuery.getInstance().resetQuery();
        SQLiteDatabase db = SQLiteDatabase.getInstance();
        db.loadAllMovies();
        movieTableController.loadMovies();
        posterGridController.loadMovies();
    }

    private void setChangeViewButtonAction() {
        view.setChangeViewButtonAction(e-> {
            if (movieDisplayPane.currentViewType == MovieDisplayPane.viewType.POSTER) {
                view.changeViewButtonIconToPoster();
            } else {
                view.changeViewButtonIconToTable();
            }
            movieDisplayPane.switchView();
        });
    }
}
