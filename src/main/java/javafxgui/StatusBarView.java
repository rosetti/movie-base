package javafxgui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

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
