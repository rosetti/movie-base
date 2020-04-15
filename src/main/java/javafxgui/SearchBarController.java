package javafxgui;

import inputOutput.SQLiteDatabase;

/**
 * Created by Vin on 10/12/2017.
 */
public class SearchBarController {

    SearchBarView view;
    SearchBarModel model;
    MovieTableController movieTableController;

    public SearchBarController(SearchBarModel model, SearchBarView view, MovieTableController movieTableController) {
        //Initialise Variables
        this.model = model;
        this.view = view;
        this.movieTableController = movieTableController;

        //Initialising Methods
        setClearFilterAction();
    }

    private void setClearFilterAction() {
        view.setClearFiltersAction( e-> {
            view.clearSearchFields();
            SQLiteDatabase db = SQLiteDatabase.getInstance();
            db.loadAllMovies();
            movieTableController.addMovies();
        });
    }

    private void setWatchedCheckBoxAction() {

    }

    private void setAdvancedSearchButtionAction() {

    }


}
