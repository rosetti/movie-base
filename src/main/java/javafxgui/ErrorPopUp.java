package javafxgui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ErrorPopUp {

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
        stage.getIcons().add(new Image(YesNoMessageBox.class.getClassLoader().getResourceAsStream("Logo.png")));

        Label lbl = new Label();
        lbl.setTextAlignment(TextAlignment.CENTER);
        lbl.setPadding(new Insets(5));
        lbl.setText(message);

        Button okBtn = new Button();
        okBtn.setText("Ok");

        okBtn.setOnAction(e -> {
            stage.close();
        });

        VBox pane = new VBox(20);
        HBox yesNoOptionsBox = new HBox(20);
        yesNoOptionsBox.getChildren().addAll(okBtn);
        yesNoOptionsBox.setAlignment(Pos.CENTER);
        pane.getChildren().addAll(lbl, yesNoOptionsBox, new VBox());
        pane.setAlignment(Pos.CENTER);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.showAndWait();
    }

}
