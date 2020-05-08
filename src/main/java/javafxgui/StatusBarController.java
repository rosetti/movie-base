package javafxgui;

public class StatusBarController {

    StatusBarModel model;
    StatusBarView view;

    public StatusBarController(StatusBarModel model, StatusBarView view) {
        this.model = model;
        this.view = view;
    }

    public void setStatusBarText(String text) {
        view.setStatusText(text);
    }

}
