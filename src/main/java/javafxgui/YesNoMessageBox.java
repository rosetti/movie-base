package javafxgui;

import javafx.application.*;
import javafx.scene.image.Image;
import javafx.scene.text.TextAlignment;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;


public class YesNoMessageBox {

    private static boolean response;

    /*
    public static void show(String message, String title, Confirmable confirmableObject)
    {
        boolean hmm;
        Stage stage = new Stage();
        double xAdd = (MainWindowView.width - 250)/2;
        double yAdd = (MainWindowView.height - 400)/2;
        stage.setX(MainWindowView.x + xAdd);
        stage.setY(MainWindowView.y + yAdd);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(title);
        stage.setMinWidth(250);
        stage.setMinHeight(80);
        Label lbl = new Label();
        lbl.setTextAlignment(TextAlignment.CENTER);
        lbl.setPadding(new Insets(5));
        lbl.setText(message);
        Button btnYes = new Button();
        btnYes.setText("Yes");

        Button btnNo = new Button();
        btnNo.setText("No");

        btnYes.setOnAction(e -> {
            stage.close();
            confirmableObject.setContinue(true);
        });

        btnNo.setOnAction(e -> {
            stage.close();
            confirmableObject.setContinue(false);
        });

        stage.setOnCloseRequest(e -> {
            confirmableObject.setContinue(false);
        });
        VBox pane = new VBox(20);
        HBox yesNoOptionsBox = new HBox(20);
        yesNoOptionsBox.getChildren().addAll(btnYes, btnNo);
        yesNoOptionsBox.setAlignment(Pos.CENTER);
        pane.getChildren().addAll(lbl, yesNoOptionsBox, new VBox());
        pane.setAlignment(Pos.CENTER);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.showAndWait();
    }
*/
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

        Button btnYes = new Button();
        btnYes.setText("Yes");

        Button btnNo = new Button();
        btnNo.setText("No");

        btnYes.setOnAction(e -> {
            response = true;
            stage.close();
        });

        btnNo.setOnAction(e -> {
            response = false;
            stage.close();
        });

        stage.setOnCloseRequest(e -> {
            response = false;
            stage.close();
        });

        VBox pane = new VBox(20);
        HBox yesNoOptionsBox = new HBox(20);
        yesNoOptionsBox.getChildren().addAll(btnYes, btnNo);
        yesNoOptionsBox.setAlignment(Pos.CENTER);
        pane.getChildren().addAll(lbl, yesNoOptionsBox, new VBox());
        pane.setAlignment(Pos.CENTER);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.showAndWait();
    }

    public static boolean getResponse() {
        return response;
    }
}
