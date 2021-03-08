package javafxgui;

public class AdvancedSearchController {

    AdvancedSearchView view;
    AdvancedSearchModel model;
    MovieTableController movieTableController;
    PosterGridController posterGridController;
    boolean searchFieldsValid;

    public AdvancedSearchController(AdvancedSearchView view, AdvancedSearchModel model, MovieTableController movieTableController, PosterGridController posterGridController) {
        this.view = view;
        this.model = model;
        this.movieTableController = movieTableController;
        this.posterGridController = posterGridController;
        setSearchAction();
        setClearFieldsButtonAction();
    }

    public void show() {
        model.resetParameters();
        view.show();
    }

    private void search() {
        setSearchParameters();
        if (searchFieldsValid) {
            model.search();
            view.hide();
            movieTableController.loadMovies();
            posterGridController.loadMovies();
        }
    }

    public void setSearchParameters() {
        searchFieldsValid = true;

        //Plain Text Fields
        model.setTitleSearchText(view.getTitleSearchText());
        model.setActorSearchText(view.getActorSearchText());
        model.setDirectorSearchText(view.getDirectorSearchText());
        model.setWriterSearchText(view.getWriterSearchText());
        model.setGenreSearchList(view.getGenreSearchList());
        model.setFileTypeSearchList(view.getFileTypeSearchList());

        //IMDb Score Parameters
        if (!view.getImdbLowerBoundText().equals("")) {
            try {
                model.setImdbScoreLowerBound(Float.parseFloat(view.getImdbLowerBoundText()));
            } catch (NumberFormatException e) {
                ErrorPopUp.show(view.getImdbLowerBoundText() + " is not a valid IMDb Score value", "Error");
                searchFieldsValid = false;
            }
        }

        if (!view.getImdbUpperBoundText().equals("")) {
            try {
                model.setImdbScoreUpperBound(Float.parseFloat(view.getImdbUpperBoundText()));
            } catch (NumberFormatException e) {
                ErrorPopUp.show(view.getImdbUpperBoundText() + " is not a valid IMDb Score value", "Error");
                searchFieldsValid = false;
            }
        }

        //Metascore Parameters
        if (!view.getMetaScoreLowerBoundText().equals("")) {
            try {
                model.setMetascoreLowerBound(Integer.parseInt(view.getMetaScoreLowerBoundText()));
            } catch (NumberFormatException e) {
                ErrorPopUp.show(view.getMetaScoreLowerBoundText() + " is not a valid Metascore value", "Error");
                searchFieldsValid = false;
            }
        }

        if (!view.getMetaScoreUpperBoundText().equals("")) {
            try {
                model.setMetascoreUpperBound(Integer.parseInt(view.getMetaScoreUpperBoundText()));
            } catch (NumberFormatException e) {
                ErrorPopUp.show(view.getMetaScoreUpperBoundText() + " is not a valid Metascore value", "Error");
                searchFieldsValid = false;
            }
        }

        //Year Parameters
        if (!view.getYearLowerBoundText().equals("")) {
            try {
                model.setYearLowerBound(Integer.parseInt(view.getYearLowerBoundText()));
            } catch (NumberFormatException e) {
                ErrorPopUp.show(view.getYearLowerBoundText() + " is not a valid year", "Error");
                searchFieldsValid = false;
            }
        }

        if (!view.getYearUpperBoundText().equals("")) {
            try {
                model.setYearUpperBound(Integer.parseInt(view.getYearUpperBoundText()));
            } catch (NumberFormatException e) {
                ErrorPopUp.show(view.getYearUpperBoundText() + " is not a valid year", "Error");
                searchFieldsValid = false;
            }
        }

        //Runtime Parameters
        if (!view.getRuntimeLowerBoundText().equals("")) {
            try {
                model.setRuntimeLowerBound(Integer.parseInt(view.getRuntimeLowerBoundText()));
            } catch (NumberFormatException e) {
                ErrorPopUp.show(view.getRuntimeLowerBoundText() + " is not a valid runtime", "Error");
                searchFieldsValid = false;
            }
        }

        if (!view.getRuntimeUpperBoundText().equals("")) {
            try {
                model.setRuntimeUpperBound(Integer.parseInt(view.getRuntimeUpperBoundText()));
            } catch (NumberFormatException e) {
                ErrorPopUp.show(view.getRuntimeUpperBoundText() + " is not a valid runtime", "Error");
                searchFieldsValid = false;
            }
        }
    }

    private void setSearchAction() {
        view.setSearchAction(e -> {
            search();
        });
    }

    private void setClearFieldsButtonAction() {
        view.setClearFieldsButtonAction(e-> {
            clearView();
        });
    }

    public void clearView() {
        view.clearFields();
    }

    public void clearModel() {
        model.resetParameters();
    }


}
