package javafxgui;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import movieControl.Movie;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class MovieEditView {

    Stage stage;
    HBox moviePane;
    VBox detailsPane;
    VBox imagePane;
    TextField titleField;
    TextField yearField;
    TextField runtimeField;
    ImageView posterImageView;
    TextArea plotField;
    TextField metaScoreField;
    TextField imdbScoreField;
    TextField fileLocationField;
    Button fileLocationChooserButton;
    TextField actorField;
    TextField directorField;
    TextField writerField;
    TextField genreField;
    TextField imdbSearchField;
    Button saveButton;
    Button cancelButton;
    Button imdbSearchByTitleButton;
    Button imdbSearchByIdButton;
    Button posterChooserButton;
    Button fetchPosterButton;
    TextField posterField;
    int height = 595;
    int width = 700;

    Insets insetPadding = new Insets(4, 20, 4, 10);

    int spacing = 5;
    int labelMinWidth = 70;

    public MovieEditView() {
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Edit View");
        stage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream("Logo.png")));
        stage.setOnCloseRequest(e -> clearForm());

        stage.setMinWidth(width);
        stage.setMaxWidth(width);

        stage.setMinHeight(height);
        stage.setMaxHeight(height);

        //Row One
        titleField = new TextField();
        titleField.setMinWidth(200);
        yearField = new TextField();
        yearField.setMaxWidth(50);
        HBox rowOne = new HBox(5);
        rowOne.setAlignment(Pos.CENTER_LEFT);
        Label titleLabel = new Label("Title");
        titleLabel.setMinWidth(labelMinWidth);
        Label yearLabel = new Label("Year");
        rowOne.getChildren().addAll(new HBox(), titleLabel, titleField, yearLabel, yearField);

        //Row Two
        runtimeField = new TextField();
        HBox rowTwo = new HBox(5);
        rowTwo.setAlignment(Pos.CENTER_LEFT);
        Label runtimeLabel = new Label("Runtime");
        runtimeLabel.setMinWidth(labelMinWidth);
        rowTwo.getChildren().addAll(new HBox(), runtimeLabel, runtimeField);

        //Row Three
        plotField = new TextArea();
        plotField.setWrapText(true);
        plotField.setMaxWidth(350);
        plotField.setMaxHeight(100);
        HBox rowThree = new HBox(spacing);
        rowThree.setAlignment(Pos.CENTER_LEFT);
        rowThree.getChildren().addAll(new HBox(), plotField);

        //Row Four
        metaScoreField = new TextField();
        metaScoreField.setMaxWidth(40);
        imdbScoreField = new TextField();
        imdbScoreField.setMaxWidth(40);
        HBox rowFour = new HBox(spacing);
        rowFour.setAlignment(Pos.CENTER_LEFT);
        rowFour.getChildren().addAll(new HBox(), new Label("Metacritic Score"), metaScoreField, new Label("IMDb Score"), imdbScoreField);

        //Row Five
        fileLocationField = new TextField();
        fileLocationField.setMinWidth(248);
        fileLocationChooserButton = new Button("...");
        HBox rowFive = new HBox(5);
        rowFive.setAlignment(Pos.CENTER_LEFT);
        Label fileLocationLabel = new Label("File Location");
        fileLocationLabel.setMinWidth(labelMinWidth);
        rowFive.getChildren().addAll(new HBox(), fileLocationLabel, fileLocationChooserButton, fileLocationField);

        //Row Six
        actorField = new TextField();
        actorField.setMinWidth(275);
        HBox rowSix = new HBox(5);
        rowSix.setAlignment(Pos.CENTER_LEFT);
        Label actorLabel = new Label("Cast");
        actorLabel.setMinWidth(labelMinWidth);
        rowSix.getChildren().addAll(new HBox(), actorLabel, actorField);

        //Row Seven
        directorField = new TextField();
        directorField.setMinWidth(275);
        HBox rowSeven = new HBox(5);
        rowSeven.setAlignment(Pos.CENTER_LEFT);
        Label directorLabel = new Label("Director(s)");
        directorLabel.setMinWidth(labelMinWidth);
        rowSeven.getChildren().addAll(new HBox(), directorLabel, directorField);

        //Row Eight
        writerField = new TextField();
        writerField.setMinWidth(275);
        HBox rowEight = new HBox(5);
        rowEight.setAlignment(Pos.CENTER_LEFT);
        Label writerLabel = new Label("Writer(s)");
        writerLabel.setMinWidth(labelMinWidth);
        rowEight.getChildren().addAll(new HBox(), writerLabel, writerField);

        //Row Nine
        genreField = new TextField();
        genreField.setMinWidth(275);
        HBox rowNine = new HBox(5);
        rowNine.setAlignment(Pos.CENTER_LEFT);
        Label genreLabel = new Label("Genre(s)");
        genreLabel.setMinWidth(labelMinWidth);
        rowNine.getChildren().addAll(new HBox(), genreLabel, genreField);

        //Row Ten
        posterField = new TextField();
        posterField.setMinWidth(215);
        posterChooserButton = new Button("...");
        fetchPosterButton = new Button("\uD83C\uDF10");
        fetchPosterButton.setTooltip(new Tooltip("Download image from URL"));
        Label posterLabel = new Label("Poster");
        HBox rowTen = new HBox(5);
        rowTen.setAlignment(Pos.CENTER_LEFT);
        posterLabel.setMinWidth(labelMinWidth);
        rowTen.getChildren().addAll(new HBox(), posterLabel, fetchPosterButton, posterChooserButton, posterField);

        //Row Eleven
        saveButton = new Button("Save");
        cancelButton = new Button("Cancel");
        HBox rowEleven = new HBox(5);
        rowEleven.setAlignment(Pos.CENTER);
        rowEleven.getChildren().addAll(new HBox(), saveButton, cancelButton);

        //ImagePane
        imagePane = new VBox();
        imagePane.setMinHeight(50);
        imagePane.setMinWidth(50);
        posterImageView = new ImageView();
        imagePane.getChildren().add(posterImageView);

        detailsPane = new VBox(10);
        detailsPane.getChildren().addAll(new HBox(), rowOne, rowTwo, rowThree, rowFour, rowFive, rowSix, rowSeven, rowEight, rowNine, rowTen, rowEleven);


        moviePane = new HBox(spacing);
        moviePane.setAlignment(Pos.CENTER);
        moviePane.getChildren().addAll(detailsPane, imagePane);

        VBox searchPane = new VBox(spacing);
        searchPane.setAlignment(Pos.CENTER);
        searchPane.setStyle("-fx-border-color: grey; -fx-border-insets: 5; -fx-border-width: 1;");
        searchPane.setPadding(insetPadding);
        Label imdbSearchLabel = new Label("Fetch Movie Data from IMDb");
        imdbSearchField = new TextField();
        imdbSearchField.setMinWidth(300);
        imdbSearchField.setMaxWidth(300);

        imdbSearchByIdButton = new Button("Search By IMDb ID");
        imdbSearchByTitleButton = new Button("Search By Title");
        HBox searchButtonsPane = new HBox(spacing);
        searchButtonsPane.setAlignment(Pos.CENTER);
        searchButtonsPane.getChildren().addAll(imdbSearchByTitleButton, imdbSearchByIdButton);
        searchPane.getChildren().addAll(imdbSearchLabel, imdbSearchField, searchButtonsPane);

        VBox mainPane = new VBox();
        mainPane.getChildren().addAll(moviePane, searchPane);

        Scene scene = new Scene(mainPane);
        stage.setScene(scene);
    }

    public void show(Movie movie) {
        loadMovie(movie);
        double xAdd = (MainWindowView.width - width) / 2;
        double yAdd = (MainWindowView.height - height) / 2;
        stage.setX(MainWindowView.x + xAdd);
        stage.setY(MainWindowView.y + yAdd);
        stage.show();
    }

    public void showBlank() {
        double xAdd = (MainWindowView.width - 700) / 2;
        double yAdd = (MainWindowView.height - 400) / 2;
        stage.setX(MainWindowView.x + xAdd);
        stage.setY(MainWindowView.y + yAdd);
        imagePane.getChildren().add(getImageView(""));
        stage.show();
    }

    public void setPosterChooserButton(EventHandler eventHandler) {
        posterChooserButton.setOnAction(eventHandler);
    }

    public void setPosterFieldText(String text) {
        posterField.setText(text);
    }

    public void setFetchPosterButton(EventHandler eventHandler) {
        fetchPosterButton.setOnAction(eventHandler);
    }

    public void setFileLocationChooserButton(EventHandler eventHandler) {
        fileLocationChooserButton.setOnAction(eventHandler);
    }

    public void setFileLocationText(String text) {
        fileLocationField.setText(text);
    }

    public Stage getStage() {
        return stage;
    }
    public void setSaveButtonAction(EventHandler handler) {
        saveButton.setOnAction(handler);
    }

    public void setCancelButtonAction(EventHandler handler) {
        cancelButton.setOnAction(handler);
    }

    public void setSearchByTitleButtonAction(EventHandler handler) {
        imdbSearchByTitleButton.setOnAction(handler);
    }

    public void setSearchByImdbIdButtonAction(EventHandler handler) {
        imdbSearchByIdButton.setOnAction(handler);
    }

    public void hide() {
        stage.hide();
    }

    public String getPosterFieldText() {
        return posterField.getText();
    }

    public void clearForm() {
        titleField.setText("");
        yearField.setText("");
        runtimeField.setText("");
        plotField.setText("");
        metaScoreField.setText("");
        imdbScoreField.setText("");
        fileLocationField.setText("");
        actorField.setText("");
        directorField.setText("");
        writerField.setText("");
        genreField.setText("");
        imdbSearchField.setText("");
        posterField.setText("");
        imagePane.getChildren().clear();
    }

    private void loadMovie(Movie movie) {
        titleField.setText(movie.getTitle());
        yearField.setText(String.valueOf(movie.getYear()));
        runtimeField.setText(String.valueOf(movie.getRuntime()));
        plotField.setText(movie.getPlot());
        metaScoreField.setText(String.valueOf(movie.getMetaScore()));
        imdbScoreField.setText(String.valueOf(movie.getImdbScore()));
        fileLocationField.setText(movie.getFileLocation());
        actorField.setText(movie.getListAsString(movie.getActor()));
        directorField.setText(movie.getListAsString(movie.getDirector()));
        writerField.setText(movie.getListAsString(movie.getWriter()));
        genreField.setText(movie.getListAsString(movie.getGenre()));
        posterField.setText(movie.getPoster());
        imdbSearchField.setText("");

        imagePane.getChildren().clear();
        imagePane.getChildren().add(getImageView(movie.getPoster()));
    }

    private ImageView getImageView(String moviePosterPath) {
        InputStream input = null;
        Image image;

        try {
            input = new FileInputStream(moviePosterPath);
        } catch (FileNotFoundException e) {
            input = getClass().getClassLoader().getResourceAsStream("image_not_found.jpg");
        }

        image = new Image(input);
        double height = image.getHeight();
        double width = image.getWidth();

        double resizeFactor = width / 300;


        posterImageView = new ImageView(image);
        posterImageView.setFitWidth(300);
        posterImageView.setFitHeight(height / resizeFactor);
        return posterImageView;
    }

    public void setOnCloseAction(EventHandler handler) {
        stage.setOnCloseRequest(handler);
    }

    public String getTitleText() {
        return titleField.getText();
    }

    public String getYearText() {
        return yearField.getText();
    }

    public String getRuntimeText() {
        return runtimeField.getText();
    }

    public String getPlotText() {
        return plotField.getText();
    }

    public String getMetaScoreText() {
        return metaScoreField.getText();
    }

    public String getImdbScoreText() {
        return imdbScoreField.getText();
    }

    public String getFileLocationText() {
        return fileLocationField.getText();
    }

    public String getActorText() {
        return actorField.getText();
    }

    public String getDirectorText() {
        return directorField.getText();
    }

    public String getWriterText() {
        return writerField.getText();
    }

    public String getGenreText() {
        return genreField.getText();
    }

    public String getImdbSearchText() {
        return imdbSearchField.getText();
    }

    public void refreshPosterImage() {
        imagePane.getChildren().clear();
        imagePane.getChildren().add(getImageView(posterField.getText()));
    }
}
