package javafxgui;

import inputOutput.SQLiteDatabase;
import movieControl.Movie;

public class MovieCheckController implements Confirmable{

    MovieCheckModel model;
    MovieCheckView view;
    boolean continueAction;

    public MovieCheckController (MovieCheckModel model, MovieCheckView view) {
        this.model = model;
        this.view = view;

        setYesButtonAction();
        setStageCloseOperation();
        setNoButtonAction();
    }

    public void show(Movie movie) {
        model.setCurrentMovie(movie);
        view.show(movie);
    }

    private void setYesButtonAction() {
        view.setYesButtonAction(e-> {
            System.out.println("Yes Button action");
            YesNoMessageBox.show("Are you sure you wish to overwrite the current movie with this one?", "Are you sure?");
            if (YesNoMessageBox.getResponse()) {
                model.setMovieToPersist(model.currentMovie);
            }
            view.hide();
        });
    }

    private void setNoButtonAction() {
        view.setNoButtonAction(e-> {
            System.out.println("No Button action");
            view.hide();
        });
    }

    private void setStageCloseOperation() {
        view.setStageCloseOperation(e-> {
            model.setMovieToPersist(null);
        });
    }

    @Override
    public void setContinue(boolean continueAction) {
        this.continueAction = continueAction;
    }

    public Movie getMovieToPersist() {
        return model.getMovieToPersist();
    }

}
