package javafxgui;

import inputOutput.SQLiteDatabase;
import org.sqlite.core.DB;

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
            dbSearchQuery.setActorSearchText(view.getSearchText());
            dbSearchQuery.setDirectorSearchText(view.getSearchText());
            dbSearchQuery.setTitleSearchText(view.getSearchText());
            search();
        });
    }

    private void setWatchedCheckBoxAction() {
        view.setWatchedCheckBoxAction(e -> {
            dbSearchQuery.setWatched(view.isWatchedCheckBoxSelected());
            search();
        });
    }

    private void setUnwatchedCheckBoxAction() {
        view.setUnwatchedCheckBoxAction(e -> {
            dbSearchQuery.setUnwatched(view.isUnwatchedCheckBoxSelected());
            search();
        });
    }

    private void search() {
        SQLiteDatabase db = SQLiteDatabase.getInstance();

        //db.loadFilteredMovies(view.getSearchText(), view.isWatchedCheckBoxSelected(), view.isUnwatchedCheckBoxSelected(), "", "", "", null);
        db.loadFilteredMovies();
        movieTableController.loadMovies();
    }

}
