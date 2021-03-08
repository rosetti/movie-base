package javafxgui;

public class SearchBarModel {

    boolean watched;
    boolean unwatched;


    public SearchBarModel() {

    }

    public boolean isWatched() {
        return watched;
    }

    public void setWatched(boolean watched) {
        this.watched = watched;
    }

    public boolean isUnwatched() {
        return unwatched;
    }

    public void setUnwatched(boolean unwatched) {
        this.unwatched = unwatched;
    }
}
