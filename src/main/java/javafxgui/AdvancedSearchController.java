package javafxgui;

import inputOutput.DBSearchQuery;
import org.sqlite.core.DB;

public class AdvancedSearchController {

    AdvancedSearchView view;
    AdvancedSearchModel model;
    MovieTableController movieTableController;

    public AdvancedSearchController(AdvancedSearchView view, AdvancedSearchModel model, MovieTableController movieTableController) {
        this.view = view;
        this.model = model;
        this.movieTableController = movieTableController;
        setSearchAction();
    }

    public void show() {
        view.show();
    }

    private void search() {
        setSearchParameters();
        model.search();
        view.hide();
        movieTableController.loadMovies();

    }

    public void setWatchedUnwatched(boolean watched, boolean unwatched) {
        model.setWatched(watched);
        model.setUnwatched(unwatched);
    }

    public void setSearchParameters() {
        DBSearchQuery dbSearchQuery = DBSearchQuery.getInstance();

        model.titleSearchText = view.getTitleSearchText();
        dbSearchQuery.setTitleSearchText(view.getTitleSearchText());

        model.actorSearchText = view.getActorSearchText();
        dbSearchQuery.setActorSearchText(view.getActorSearchText());

        model.directorSearchText = view.getDirectorSearchText();
        dbSearchQuery.setDirectorSearchText(view.getDirectorSearchText());

        model.writerSearchText = view.getWriterSearchText();
        dbSearchQuery.setWriterSearchText(view.getWriterSearchText());

        model.setGenreSearchList(view.getGenreSearchList());
        dbSearchQuery.setGenreSearchList(view.getGenreSearchList());
    }

    private void setSearchAction() {
        view.setSearchAction(e -> {
            search();
        });
    }

}
