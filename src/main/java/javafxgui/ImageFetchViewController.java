package javafxgui;

import movieControl.Movie;
import resources.ImageSaver;

public class ImageFetchViewController {

    ImageFetchView view;
    Movie currentMovie;
    String localImagePath;


    public ImageFetchViewController(ImageFetchView imageFetchView) {
        view = imageFetchView;
        setFetchImageButton();
        setCancelButton();
    }

    public void show() {
        view.clear();
        view.show();
    }

    private void setFetchImageButton() {
        view.setFetchImageButton( e-> {
            localImagePath = ImageSaver.getImage(view.getImagePathFieldText(), currentMovie.getTitle(), currentMovie.getImdbId());
            hide();
        });
    }

    private void setCancelButton() {
        view.setCancelButton(e -> {
            view.clear();
            view.hide();
        });
    }

    public void setCurrentMovie(Movie movie) {
        this.currentMovie = movie;
    }

    public void hide() {
        view.hide();
        view.clear();
    }

    public String getLocalImagePath() {
        return localImagePath;
    }
}
