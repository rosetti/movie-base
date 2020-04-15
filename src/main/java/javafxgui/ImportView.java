package javafxgui;

import interfaces.ImportProgressInterface;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javafx.event.ActionEvent;

import java.io.File;

/**
 * Created by Vin on 07/10/2017.
 */
public class ImportView implements ImportProgressInterface{

    DirectoryChooser chooser;
    TextField pathField;
    Stage stage;
    VBox mainPane;
    Button btnStartImport;
    ProgressBar progressBar;
    Label progressLabel;
    TextArea progressBox;
    String pathText;

    public ImportView() {
        initialiseStage();
        mainPane.getChildren().addAll(getPathToScanLabel(), getLookupPane(), getOptionsPane(), getImportGoButton(), getProgressBox(), getProgressLabel(), getProgressBar());

    }

    private void initialiseStage() {
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Import Movies");

        stage.setMinWidth(500);
        stage.setMaxWidth(500);

        stage.setMinHeight(400);
        stage.setMaxHeight(400);

        mainPane = new VBox(10);

        Scene scene = new Scene(mainPane);
        stage.setScene(scene);
    }

    public void show() {
        stage.show();
    }

   private Pane getPathToScanLabel() {
        Label lblPathToScan = new Label("Path to Scan:");

        VBox labelPane = new VBox();
        //labelPane.setStyle("-fx-background-color: #00ddcf");
        labelPane.setMaxHeight(100);
        labelPane.setAlignment(Pos.CENTER);
        labelPane.getChildren().add(lblPathToScan);

        return labelPane;
   }

    private HBox getLookupPane() {
        Button btnOpenFileChooser = new Button("...");
        btnOpenFileChooser.setOnAction(e -> btnFileChooser());

        pathField = new TextField();
        pathField.setMinWidth(350);

        HBox lookupPane = new HBox(5);
        //lookupPane.setStyle("-fx-background-color: #0063af");
        lookupPane.setMaxHeight(20);
        lookupPane.setAlignment(Pos.CENTER);
        lookupPane.getChildren().addAll(btnOpenFileChooser, pathField);

        return lookupPane;
    }

    private FlowPane getOptionsPane() {
        CheckBox scanAllBox = new CheckBox("Scan All Movies");
        CheckBox reviewAfterImportBox = new CheckBox("Review Movies after Import");

        FlowPane optionsPane = new FlowPane();
        optionsPane.setAlignment(Pos.CENTER);
        optionsPane.setHgap(5);
        optionsPane.getChildren().addAll(scanAllBox, reviewAfterImportBox);

        return optionsPane;
    }

    private VBox getImportGoButton() {
        btnStartImport = new Button("Start Import!");

        VBox importGoPane = new VBox();
        //importGoPane.setStyle("-fx-background-color: #8e00af");
        importGoPane.setMaxHeight(15);
        importGoPane.setAlignment(Pos.CENTER);
        importGoPane.getChildren().add(btnStartImport);
        return importGoPane;
    }

    public void setImportAction(EventHandler<ActionEvent> action) {
        btnStartImport.setOnAction(action);
    }

    private VBox getProgressBox() {
        progressBox = new TextArea();
        progressBox.setMinHeight(150);
        progressBox.setMinWidth(400);
        progressBox.setMaxWidth(400);

        VBox progressBoxPane = new VBox();
        //progressBoxPane.setStyle("-fx-background-color: #af2500");
        progressBoxPane.setMaxHeight(160);
        progressBoxPane.setAlignment(Pos.CENTER);
        progressBoxPane.getChildren().add(progressBox);

        return progressBoxPane;
    }

    private VBox getProgressBar() {
        progressBar = new ProgressBar();
        progressBar.setMinWidth(200);
        progressBar.setMinHeight(50);

        VBox progressBarPane = new VBox();
        //progressBarPane.setStyle("-fx-background-color: #00af1a");
        progressBarPane.setMaxHeight(15);
        progressBarPane.setAlignment(Pos.CENTER);
        progressBarPane.getChildren().add(progressBar);
        return progressBarPane;
    }

    private VBox getProgressLabel() {
        progressLabel = new Label("0 of 0 Movies Imported");

        VBox currentlyScanningPane = new VBox();
        currentlyScanningPane.setAlignment(Pos.CENTER);
        currentlyScanningPane.getChildren().add(progressLabel);
        return currentlyScanningPane;
    }

    private void btnFileChooser() {
        pathText = getFilePathFromChooser();
        pathField.setText(pathText);
    }

    @Override
    public void setProgressValues(int count, int total) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                //Set progress bar
                progressBar.setProgress(count/total);

                //Set x of y
                progressLabel.setText(count + " of " + total);
            }
        });

    }

    @Override
    public void setProgressText(String text) {
        progressBox.appendText(text);
    }

    @Override
    public void setProgressBar(int count, int total) {

    }
    public String getInputPath() {
        return pathField.getText();
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
