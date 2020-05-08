package javafxgui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MessageBox {
    public static void show(String message, String title)
    {
        Stage stage = new Stage();
        double xAdd = (MainWindowView.width - 250)/2;
        double yAdd = (MainWindowView.height - 400)/2;
        stage.setX(MainWindowView.x + xAdd);
        stage.setY(MainWindowView.y + yAdd);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(title);
        stage.setMinWidth(450);
        stage.setMinHeight(80);

        Label lbl = new Label();
        lbl.setTextAlignment(TextAlignment.CENTER);
        lbl.setPadding(new Insets(5));
        lbl.setText(message);

        VBox pane = new VBox(20);
        pane.getChildren().addAll(lbl, new VBox());
        pane.setAlignment(Pos.CENTER);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.showAndWait();
    }
}
