package javafxgui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;



public class AdvancedSearchView {

    Stage stage;
    VBox mainPane;
    Button searchButton, clearFieldsButton;

    TextField titleField, actorField, directorField, writerField, imdbLowerBoundField, imdbUpperBoundField;
    TextField metaCriticLowerBoundField, metaCriticUpperBoundField, yearLowerBoundField, yearUpperBoundField, runtimeLowerBoundField, runtimeUpperBoundField;
    ListView<String> genreList, fileTypeList;

    public AdvancedSearchView() {
        initialiseStage();
        addFields();
        stage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream("Logo.png")));
    }

    private void initialiseStage() {
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Advanced Search");

        stage.setMinWidth(700);
        stage.setMaxWidth(700);

        stage.setMinHeight(400);
        stage.setMaxHeight(400);

        mainPane = new VBox(10);
        mainPane.setAlignment(Pos.CENTER);

        Scene scene = new Scene(mainPane);
        stage.setScene(scene);

    }

    public void setSearchAction(EventHandler handler) {
        searchButton.setOnAction(handler);
    }

    public void show() {
        double xAdd = (MainWindowView.width - 700)/2;
        double yAdd = (MainWindowView.height - 400)/2;
        stage.setX(MainWindowView.x + xAdd);
        stage.setY(MainWindowView.y + yAdd);
        stage.show();
    }

    public void hide() {
        stage.hide();
    }

    public String getTitleSearchText() {
        return titleField.getText();
    }

    public String getActorSearchText() {return actorField.getText(); }

    public String getDirectorSearchText() {return directorField.getText(); }

    public String getWriterSearchText() {return writerField.getText(); }

    public ObservableList<String> getGenreSearchList() { return genreList.getSelectionModel().getSelectedItems(); }

    public ObservableList<String> getFileTypeSearchList() { return fileTypeList.getSelectionModel().getSelectedItems(); }

    public void clearFields() {
        titleField.setText("");
        actorField.setText("");
        directorField.setText("");
        writerField.setText("");
        imdbLowerBoundField.setText("");
        imdbUpperBoundField.setText("");
        runtimeLowerBoundField.setText("");
        runtimeUpperBoundField.setText("");
        genreList.getSelectionModel().clearSelection();
        fileTypeList.getSelectionModel().clearSelection();
    }

    private void addFields() {
        //H Spacers
        VBox spacer1 = new VBox();
        spacer1.setMinWidth(15);
        VBox spacer2 = new VBox();
        spacer2.setMinWidth(15);
        VBox spacer3 = new VBox();
        spacer3.setMinWidth(15);
        VBox spacer4 = new VBox();
        spacer4.setMinWidth(15);
        VBox spacer5 = new VBox();
        spacer5.setMinHeight(5);
        VBox spacer6 = new VBox();
        spacer6.setMinHeight(5);

        //Row One Side A - Title, Director
        HBox rowOne = new HBox();
        rowOne.setAlignment(Pos.CENTER);

        HBox titleBox = new HBox();
        titleBox.setAlignment(Pos.CENTER_RIGHT);
        Label titleLabel = new Label("Title: ");
        titleField = new TextField();
        titleBox.getChildren().addAll(titleLabel, titleField);

        HBox directorBox = new HBox();
        directorBox.setAlignment(Pos.CENTER_RIGHT);
        Label directorLabel = new Label("Director(s): ");
        directorField = new TextField();
        directorBox.getChildren().addAll(directorLabel, directorField);

        //Row One Side B - Actor, Writer

        HBox actorBox = new HBox();
        actorBox.setAlignment(Pos.CENTER_RIGHT);
        Label actorLabel = new Label("Actor: ");
        actorField = new TextField();
        actorBox.getChildren().addAll(actorLabel, actorField);

        HBox writerBox = new HBox();
        writerBox.setAlignment(Pos.CENTER_RIGHT);
        Label writerLabel = new Label("Writer(s): ");
        writerField = new TextField();
        writerBox.getChildren().addAll(writerLabel, writerField);

        VBox actorWriterBox = new VBox();
        actorWriterBox.getChildren().addAll(actorBox,spacer5, writerBox);

        VBox titleDirectorBox = new VBox();
        titleDirectorBox.getChildren().addAll(titleBox,spacer6, directorBox);

        rowOne.getChildren().addAll(titleDirectorBox, spacer4, actorWriterBox);

        //Row Three - Genre, File Type
        HBox rowThree = new HBox();
        rowThree.setAlignment(Pos.CENTER);

        VBox genreSearch = new VBox();
        genreSearch.setMaxWidth(300);
        genreSearch.setAlignment(Pos.CENTER);
        Label genreLabel = new Label("Genre");
        genreList = new ListView<>();
        genreList.getItems().addAll(getGenreOptions());
        genreList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        genreSearch.getChildren().addAll(genreLabel, genreList);

        VBox fileTypeSearch = new VBox();
        fileTypeSearch.setMaxWidth(300);
        fileTypeSearch.setAlignment(Pos.CENTER);
        Label fileTypeLabel = new Label("File Type");
        fileTypeList = new ListView<>();
        fileTypeList.getItems().addAll(getFileTypeOptions());
        fileTypeSearch.getChildren().addAll(fileTypeLabel, fileTypeList);

        rowThree.getChildren().addAll(genreSearch, spacer1, fileTypeSearch);

        //Row Four - IMDB Score, Meta Critic Score
        HBox rowFour = new HBox();
        rowFour.setAlignment(Pos.CENTER);

        VBox imdbScoreBox = new VBox();
        imdbScoreBox.setAlignment(Pos.CENTER);
        Label imdbScoreLabel = new Label("IMDB Score");

        HBox imdbScoreDropDowns = new HBox();
        imdbLowerBoundField = new TextField();
        int scoreWidth = 45;
        imdbLowerBoundField.setMinWidth(scoreWidth);
        imdbLowerBoundField.setMaxWidth(scoreWidth);
        imdbUpperBoundField = new TextField();
        imdbUpperBoundField.setMinWidth(scoreWidth);
        imdbUpperBoundField.setMaxWidth(scoreWidth);
        imdbScoreDropDowns.getChildren().addAll(imdbLowerBoundField, new Label(" - "), imdbUpperBoundField);

        imdbScoreBox.getChildren().addAll(imdbScoreLabel, imdbScoreDropDowns);

        VBox metaCriticScoreBox = new VBox();
        metaCriticScoreBox.setAlignment(Pos.CENTER_RIGHT);
        Label metaCriticScoreLabel = new Label("Metacritic Score");

        HBox metaCriticScoreDropDowns = new HBox();
        metaCriticLowerBoundField = new TextField();
        metaCriticLowerBoundField.setMinWidth(scoreWidth);
        metaCriticLowerBoundField.setMaxWidth(scoreWidth);

        metaCriticUpperBoundField = new TextField();
        metaCriticUpperBoundField.setMinWidth(scoreWidth);
        metaCriticUpperBoundField.setMaxWidth(scoreWidth);

        metaCriticScoreDropDowns.getChildren().addAll(metaCriticLowerBoundField, new Label(" - "), metaCriticUpperBoundField);

        metaCriticScoreBox.getChildren().addAll(metaCriticScoreLabel, metaCriticScoreDropDowns);

        rowFour.getChildren().addAll(imdbScoreBox, spacer2, metaCriticScoreBox);

        //Row Five - Year, Runtime
        HBox rowFive = new HBox();
        rowFive.setAlignment(Pos.CENTER);

        VBox yearBox = new VBox();
        yearBox.setAlignment(Pos.CENTER);
        Label yearLabel = new Label("Year");

        HBox yearDropDowns = new HBox();
        yearLowerBoundField = new TextField();
        yearLowerBoundField.setMinWidth(scoreWidth);
        yearLowerBoundField.setMaxWidth(scoreWidth);

        yearUpperBoundField = new TextField();
        yearUpperBoundField.setMinWidth(scoreWidth);
        yearUpperBoundField.setMaxWidth(scoreWidth);

        yearDropDowns.getChildren().addAll(yearLowerBoundField, new Label(" - "),  yearUpperBoundField);

        yearBox.getChildren().addAll(yearLabel, yearDropDowns);

        VBox runtimeBox = new VBox();
        runtimeBox.setAlignment(Pos.CENTER);
        Label runtimeLabel = new Label("Runtime");

        HBox runtimeDropDowns = new HBox();

        runtimeLowerBoundField = new TextField();
        runtimeLowerBoundField.setMinWidth(scoreWidth);
        runtimeLowerBoundField.setMaxWidth(scoreWidth);

        runtimeUpperBoundField = new TextField();
        runtimeUpperBoundField.setMinWidth(scoreWidth);
        runtimeUpperBoundField.setMaxWidth(scoreWidth);

        runtimeDropDowns.getChildren().addAll(runtimeLowerBoundField, new Label(" - "), runtimeUpperBoundField);

        runtimeBox.getChildren().addAll(runtimeLabel, runtimeDropDowns);

        rowFive.getChildren().addAll(yearBox, spacer3, runtimeBox);

        searchButton = new Button("Search");
        clearFieldsButton = new Button("Clear");

        HBox buttonBox = new HBox(5);
        buttonBox.getChildren().addAll(searchButton, clearFieldsButton);
        buttonBox.setAlignment(Pos.CENTER);
        mainPane.getChildren().addAll(new VBox(), rowOne, rowThree, rowFour, rowFive, buttonBox, new VBox());

    }

    private ObservableList getMetacriticOptions() {
        Integer[] numbers = new Integer[101];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = i;
        }

        return FXCollections.observableArrayList(numbers);
    }

    private ObservableList getGenreOptions() {
        String[] genres = {"Action", "Crime", "Drama", "Mystery", "Thriller", "Sci-Fi", "Biography", "History", "Adventure", "Comedy", "Romance", "Horror", "Documentary", "Music", "Animation", "Family", "Fantasy", "Short", "War", "Sport", "Musical", "News", "Western", "Reality-TV", "Film-Noir"};
        return FXCollections.observableArrayList(genres);
    }

    private ObservableList getFileTypeOptions() {
        String[] fileTypes = {".avi", ".mp4", "folder", ".mkv",".divx", ".AVI",""};
        return FXCollections.observableArrayList(fileTypes);
    }

    public String getImdbLowerBoundText() {
        return imdbLowerBoundField.getText();
    }

    public String getImdbUpperBoundText() {
        return imdbUpperBoundField.getText();
    }

    public String getMetaScoreLowerBoundText() {
        return metaCriticLowerBoundField.getText();
    }

    public String getMetaScoreUpperBoundText() {
        return metaCriticUpperBoundField.getText();
    }

    public void setClearFieldsButtonAction(EventHandler handler) {
        clearFieldsButton.setOnAction(handler);
    }

    public String getYearLowerBoundText() {
        return yearLowerBoundField.getText();
    }

    public String getYearUpperBoundText() {
        return yearUpperBoundField.getText();
    }

    public String getRuntimeLowerBoundText() {
        return runtimeLowerBoundField.getText();
    }

    public String getRuntimeUpperBoundText() {
        return runtimeUpperBoundField.getText();
    }
}
