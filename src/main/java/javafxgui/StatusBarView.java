package javafxgui;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class StatusBarView extends HBox {

    Label statusLabel;

    public StatusBarView() {
        statusLabel = new Label("X movies added");
        getChildren().addAll(statusLabel);
        setAlignment(Pos.CENTER_RIGHT);
    }

    public void setStatusText(String text) {
        statusLabel.setText(text);
    }
}
