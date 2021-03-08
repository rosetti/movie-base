package javafxgui;

import inputOutput.SQLiteDatabase;
import javafx.stage.FileChooser;
import movieControl.Movie;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class MovieEditController implements Confirmable {

    MovieEditModel model;
    MovieEditView view;
    MovieCheckController movieCheckController;
    ImageFetchViewController imageFetchViewController;
    boolean continueAction;

    @Override
    public void setContinue(boolean continueAction) {
        this.continueAction = continueAction;
    }

    public MovieEditController(MovieEditModel model, MovieEditView view, MovieCheckController movieCheckController, ImageFetchViewController imageFetchViewController) {
        this.model = model;
        this.view = view;
        this.movieCheckController = movieCheckController;
        this.imageFetchViewController = imageFetchViewController;
        setSaveButtonAction();
        setCancelButtonAction();
        setSearchByTitleButtonAction();
        setSearchByImdbIdButtonAction();
        setFileLocationChooserButton();
        setPosterChooserButton();
        setFetchPosterButton();
    }

    public void show(Movie movie) {
        view.show(movie);
        model.setCurrentMovie(movie);
    }

    public void showBlank() {
        view.showBlank();
    }

    public void setSaveButtonAction() {
        view.setSaveButtonAction(e-> {
            boolean deleteOriginal = false;
            if (model.getRetrievedMovie() != null && model.getOriginalMovie() != null) {
                YesNoMessageBox.show("Are you sure you wish to overwrite movie:\n" + model.getOriginalMovie().getTitle()
                + " with:\n" +
                        model.getRetrievedMovie().getTitle(), "Are you sure?");
                if (YesNoMessageBox.getResponse()) {
                    SQLiteDatabase db = SQLiteDatabase.getInstance();
                    deleteOriginal = true;
                    model.setCurrentMovie(model.getRetrievedMovie());
                } else {
                    return;
                }
            }

            //TODO: Field validation needs to be added
            model.getCurrentMovie().setTitle(view.getTitleText());
            try {
                model.getCurrentMovie().setYear(Integer.parseInt(view.getYearText()));
            } catch (NumberFormatException ex) {
                ErrorPopUp.show("\"" + view.getYearText() + "\"" +" is not a valid value for year.", "Error");
                return;
            }

            try {
                model.getCurrentMovie().setMetaScore(Integer.parseInt(view.getMetaScoreText()));
            } catch (NumberFormatException ex) {
                ErrorPopUp.show("\"" + view.getMetaScoreText() + "\"" +" is not a valid value for Metascore.", "Error");
                return;
            }

            try {
                model.getCurrentMovie().setImdbScore(Float.parseFloat(view.getImdbScoreText()));
            } catch (NumberFormatException ex) {
                ErrorPopUp.show("\"" + view.getImdbScoreText() + "\"" +" is not a valid value for IMDb score.", "Error");
                return;
            }

            model.getCurrentMovie().setPlot(view.getPlotText());
            model.getCurrentMovie().setFileLocation(view.getFileLocationText());
            model.getCurrentMovie().setActor(new ArrayList<String>(Arrays.asList(view.getActorText().split("\\s*,\\s*"))));
            model.getCurrentMovie().setDirector(new ArrayList<String>(Arrays.asList(view.getDirectorText().split("\\s*,\\s*"))));
            model.getCurrentMovie().setWriter(new ArrayList<String>(Arrays.asList(view.getWriterText().split("\\s*,\\s*"))));
            model.getCurrentMovie().setGenre(new ArrayList<String>(Arrays.asList(view.getGenreText().split("\\s*,\\s*"))));
            model.getCurrentMovie().setPoster(view.getPosterFieldText());

            SQLiteDatabase db = SQLiteDatabase.getInstance();
            if (deleteOriginal) {
                db.deleteMovie(model.getOriginalMovie());
            }
            db.addMovie(model.getCurrentMovie());
            view.clearForm();
            view.hide();
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
                    model.setOriginalMovie(model.getCurrentMovie());
                    if (model.getCurrentMovie() != null) {
                        model.getRetrievedMovie().setFileLocation(model.getCurrentMovie().getFileLocation());
                    }
                    show(model.getRetrievedMovie());
                }
            } else {
                MessageBox.show("No movies found in IMDb!", "No movies found!");
            }
        });
    }

    public void setFileLocationChooserButton() {
        view.setFileLocationChooserButton(e -> {
            FileChooser chooser = new FileChooser();
            File file = chooser.showOpenDialog(view.getStage());
            if (file != null) {
                view.setFileLocationText(file.getAbsolutePath());
            }
        });
    }

    public void setPosterChooserButton() {
        view.setPosterChooserButton(e -> {
            FileChooser chooser = new FileChooser();
            File file = chooser.showOpenDialog(view.getStage());
            if (file != null) {
                view.setPosterFieldText(file.getAbsolutePath());
            }
        });
    }

    public void setFetchPosterButton() {
        view.setFetchPosterButton(e -> {
            imageFetchViewController.setCurrentMovie(model.getCurrentMovie());
            imageFetchViewController.show();
            System.out.println("");
            if (imageFetchViewController.getLocalImagePath() != null) {
                view.setPosterFieldText(imageFetchViewController.getLocalImagePath());
                view.refreshPosterImage();
            }
        });
    }

}
