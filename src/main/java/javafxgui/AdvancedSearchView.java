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
    Button searchButton;

    TextField titleField;
    TextField actorField;
    TextField directorField;
    TextField writerField;
    ListView<String> genreList;

    public AdvancedSearchView(double x, double y) {
        initialiseStage();
        addFields();
        System.out.println(x + ", " + y);
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

    public void clearFields() {
        System.out.println("Clear fields on Advanced Search View");
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
        //V Spacers
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
        ListView<String> fileTypeList = new ListView<>();
        fileTypeSearch.getChildren().addAll(fileTypeLabel, fileTypeList);

        rowThree.getChildren().addAll(genreSearch, spacer1, fileTypeSearch);

        //Row Four - IMDB Score, Meta Critic Score
        HBox rowFour = new HBox();
        rowFour.setAlignment(Pos.CENTER);

        VBox imdbScoreBox = new VBox();
        imdbScoreBox.setAlignment(Pos.CENTER);
        Label imdbScoreLabel = new Label("IMDB Score");

        HBox imdbScoreDropDowns = new HBox();
        ComboBox<Integer> imdbLowerBoundBox = new ComboBox<>();
        ComboBox<Integer> imdbUpperBoundBox = new ComboBox<>();
        imdbScoreDropDowns.getChildren().addAll(imdbLowerBoundBox, imdbUpperBoundBox);

        imdbScoreBox.getChildren().addAll(imdbScoreLabel, imdbScoreDropDowns);

        VBox metaCriticScoreBox = new VBox();
        metaCriticScoreBox.setAlignment(Pos.CENTER_RIGHT);
        Label metaCriticScoreLabel = new Label("Metacritic Score");

        HBox metaCriticScoreDropDowns = new HBox();
        ComboBox<Integer> metaCriticLowerBoundBox = new ComboBox<>();
        ComboBox<Integer> metaCriticUpperBoundBox = new ComboBox<>();
        metaCriticLowerBoundBox.setItems(getMetacriticOptions());
        metaCriticUpperBoundBox.setItems(getMetacriticOptions());
        metaCriticScoreDropDowns.getChildren().addAll(metaCriticLowerBoundBox, metaCriticUpperBoundBox);

        metaCriticScoreBox.getChildren().addAll(metaCriticScoreLabel, metaCriticScoreDropDowns);

        rowFour.getChildren().addAll(imdbScoreBox, spacer2, metaCriticScoreBox);

        //Row Five - Year, Runtime
        HBox rowFive = new HBox();
        rowFive.setAlignment(Pos.CENTER);

        VBox yearBox = new VBox();
        yearBox.setAlignment(Pos.CENTER);
        Label yearLabel = new Label("Year");

        HBox yearDropDowns = new HBox();
        ComboBox<Integer> yearLowerBoundBox = new ComboBox<>();
        ComboBox<Integer> yearUpperBoundBox = new ComboBox<>();
        yearDropDowns.getChildren().addAll(yearLowerBoundBox, yearUpperBoundBox);

        yearBox.getChildren().addAll(yearLabel, yearDropDowns);

        VBox runtimeBox = new VBox();
        runtimeBox.setAlignment(Pos.CENTER);
        Label runtimeLabel = new Label("Runtime");

        HBox runtimeDropDowns = new HBox();
        ComboBox<Integer> runtimeLowerBoundBox = new ComboBox<>();
        ComboBox<Integer> runtimeUpperBoundBox = new ComboBox<>();
        runtimeDropDowns.getChildren().addAll(runtimeLowerBoundBox, runtimeUpperBoundBox);

        runtimeBox.getChildren().addAll(runtimeLabel, runtimeDropDowns);

        rowFive.getChildren().addAll(yearBox, spacer3, runtimeBox);


        searchButton = new Button("Search");

        mainPane.getChildren().addAll(new VBox(), rowOne, rowThree, rowFour, rowFive, searchButton, new VBox());

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
}
