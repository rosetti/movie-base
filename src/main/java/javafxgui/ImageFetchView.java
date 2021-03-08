package javafxgui;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ImageFetchView {

    Stage stage;
    int stageWidth = 500;
    int stageHeight = 125;
    int spacing = 5;
    VBox mainPane;
    TextField imagePath;
    Button fetchImageButton;
    Button cancelButton;

    public ImageFetchView() {
        stage = new Stage();
        stage.setTitle("Fetch Movie Poster From Web");
        stage.setMinWidth(stageWidth);
        stage.setMaxWidth(stageWidth);
        stage.setMinHeight(stageHeight);
        stage.setMaxHeight(stageHeight);

        mainPane = new VBox(spacing);
        mainPane.setAlignment(Pos.CENTER);

        imagePath = new TextField();
        imagePath.setMaxWidth(400);
        fetchImageButton = new Button("Fetch Image");
        cancelButton = new Button("Cancel");

        HBox buttonBox = new HBox(spacing);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(fetchImageButton, cancelButton);

        mainPane.getChildren().addAll(imagePath, buttonBox);
        stage.setScene(new Scene(mainPane));
    }

    public void show() {
        stage.showAndWait();
    }

    public void setFetchImageButton(EventHandler handler) {
        fetchImageButton.setOnAction(handler);
    }

    public void setCancelButton(EventHandler handler) {
        cancelButton.setOnAction(handler);
    }

    public String getImagePathFieldText() {
        return imagePath.getText();
    }

    public void clear() {
        imagePath.setText("");
    }

    public void hide() {
        stage.hide();
    }

}
