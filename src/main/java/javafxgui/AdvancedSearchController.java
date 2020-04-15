package javafxgui;

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
        model.titleSearchText = view.getTitleSearchText();
        model.actorSearchText = view.getActorSearchText();
        model.directorSearchText = view.getDirectorSearchText();
        model.writerSearchText = view.getWriterSearchText();
        model.setGenreSearchList(view.getGenreSearchList());
    }

    private void setSearchAction() {
        view.setSearchAction(e -> {
            search();
        });
    }

}
