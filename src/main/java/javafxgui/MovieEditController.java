package javafxgui;

import inputOutput.SQLiteDatabase;
import javafx.scene.control.ContextMenu;
import movieControl.Movie;

import java.util.ArrayList;
import java.util.Arrays;

public class MovieEditController implements Confirmable {

    MovieEditModel model;
    MovieEditView view;
    MovieCheckController movieCheckController;
    boolean continueAction;

    @Override
    public void setContinue(boolean continueAction) {
        this.continueAction = continueAction;
    }

    public MovieEditController(MovieEditModel model, MovieEditView view, MovieCheckController movieCheckController) {
        this.model = model;
        this.view = view;
        this.movieCheckController = movieCheckController;
        setSaveButtonAction();
        setCancelButtonAction();
        setSearchByTitleButtonAction();
        setSearchByImdbIdButtonAction();
    }

    public void show(Movie movie) {
        view.show(movie);
        model.setCurrentMovie(movie);
    }

    public void setSaveButtonAction() {
        view.setSaveButtonAction(e-> {
            if (model.getRetrievedMovie() != null) {
                YesNoMessageBox.show("Are you sure you wish to overwrite movie:\n" + model.getCurrentMovie().getTitle()
                + " with:\n" +
                        model.getRetrievedMovie().getTitle(), "Are you sure?");
                if (YesNoMessageBox.getResponse()) {
                    SQLiteDatabase db = SQLiteDatabase.getInstance();
                    db.deleteMovie(model.getCurrentMovie());
                    model.setCurrentMovie(model.getRetrievedMovie());
                }
            }

            //TODO: Field validation needs to be added
            model.getCurrentMovie().setTitle(view.getTitleText());
            model.getCurrentMovie().setYear(Integer.valueOf(view.getYearText()));
            model.getCurrentMovie().setPlot(view.getPlotText());
            model.getCurrentMovie().setMetaScore(Integer.valueOf(view.getMetaScoreText()));
            model.getCurrentMovie().setImdbScore(Float.valueOf(view.getMetaScoreText()));
            model.getCurrentMovie().setFileLocation(view.getFileLocationText());
            model.getCurrentMovie().setActor(new ArrayList<String>(Arrays.asList(view.getActorText().split("\\s*,\\s*"))));
            model.getCurrentMovie().setDirector(new ArrayList<String>(Arrays.asList(view.getDirectorText().split("\\s*,\\s*"))));
            model.getCurrentMovie().setWriter(new ArrayList<String>(Arrays.asList(view.getWriterText().split("\\s*,\\s*"))));
            model.getCurrentMovie().setGenre(new ArrayList<String>(Arrays.asList(view.getGenreText().split("\\s*,\\s*"))));

            SQLiteDatabase db = SQLiteDatabase.getInstance();
            db.addMovie(model.getCurrentMovie());
        });
    }

    public void setCancelButtonAction() {
        view.setCancelButtonAction(e-> {
            view.hide();
        });
    }

    public void setSearchByTitleButtonAction() {
        view.setSearchByTitleButtonAction(e-> {
            model.searchImdbByTitle(view.getImdbSearchText());
            if (model.getRetrievedMovie() != null) {
                movieCheckController.show(model.getRetrievedMovie());
                if (movieCheckController.getMovieToPersist() != null) {
                    SQLiteDatabase db = SQLiteDatabase.getInstance();
                    show(model.getRetrievedMovie());
                }
            } else {
                MessageBox.show("No movies found in IMDb!", "No movies found!");
            }
        });
    }

    public void setSearchByImdbIdButtonAction() {
        view.setSearchByImdbIdButtonAction(e-> {
            model.searchImdbByImdbId(view.getImdbSearchText());
            if (model.getRetrievedMovie() != null) {
                movieCheckController.show(model.getRetrievedMovie());
                if (movieCheckController.getMovieToPersist() != null) {
                    SQLiteDatabase db = SQLiteDatabase.getInstance();
                    show(model.getRetrievedMovie());
                }
            } else {
                MessageBox.show("No movies found in IMDb!", "No movies found!");
            }
        });
    }

}
