package javafxgui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;

/**
 * Created by Vin on 07/10/2017.
 */
public class ImportWindow {

    DirectoryChooser chooser;
    TextField pathField;
    Stage stage;

    public ImportWindow() {
        initialiseStage();
    }

    private void initialiseStage() {
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Import Movies");

        stage.setMinWidth(400);
        stage.setMinHeight(250);

        stage.setMaxWidth(400);
        stage.setMaxHeight(250);
    }

    public void show() {

        VBox vPane = new VBox();


        Label label = new Label("Path to Scan:");

        HBox hPane = new HBox();
        Button btnOpenFileChooser = new Button("...");
        btnOpenFileChooser.setOnAction(e -> btnFileChooser());
        pathField = new TextField();


        hPane.getChildren().addAll(btnOpenFileChooser, pathField);





        vPane.getChildren().addAll(label, hPane);

        Scene scene = new Scene(vPane);
        stage.setScene(scene);
        stage.show();
    }

    private void btnFileChooser() {
        String pathText = getFilePathFromChooser();
        pathField.setText(pathText);
    }

    private String getFilePathFromChooser() {
        if (chooser == null) {
            chooser = new DirectoryChooser();

        }

        File file = chooser.showDialog(stage);
        if (file != null) {
            return file.getAbsolutePath();
        } else {
            return "";
        }
    }

}
