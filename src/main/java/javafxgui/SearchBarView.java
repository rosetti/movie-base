package javafxgui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.awt.*;

/**
 * Created by Vin on 09/12/2017.
 */
public class SearchBarView  extends HBox{

    TextField searchField;

    public SearchBarView() {
        searchField = new TextField();

        getChildren().add(searchField);


    }

    public void setSearchBoxAction(EventHandler<ActionEvent> handler) {
        searchField.setOnAction(handler);
    }

}
