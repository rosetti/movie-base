package javafxgui;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class StatusBarView extends HBox {

    Label statsLabel;

    public StatusBarView() {
        statsLabel = new Label("X movies added");
        getChildren().addAll(statsLabel);
        setAlignment(Pos.CENTER_RIGHT);
    }

    public void setStatsLabel(String text) {
        statsLabel.setText(text);
    }
}
