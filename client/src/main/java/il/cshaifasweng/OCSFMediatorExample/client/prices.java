package il.cshaifasweng.OCSFMediatorExample.client;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;

import java.awt.*;
import java.io.IOException;
import java.util.Collection;

public class prices {
    @FXML
    private    ChoiceBox<String> box;

    @FXML
    private TableView<?> table;

    @FXML
    private Button ContactUs;

    @FXML
    private Button Prices;

    @FXML
    private Button about;

    @FXML
    private Button homebut;

    @FXML
    private Button login;
    @FXML
    private Label choosepark;
    @FXML
    void boxfun(MouseEvent  event) {
        if (box.getValue()!="")
        {
            choosepark.setVisible(false);
        }
        else
        {
            choosepark.setVisible(true);
        }
        ObservableList<String>tmp=FXCollections.observableArrayList("a","b","c");
        box.setItems(tmp);
    }
    @FXML
    void Pricesfun(ActionEvent event) throws IOException {
        App.setRoot("prices");
    }
    @FXML
    void aboutus(ActionEvent event) throws IOException {
        App.setRoot("aboutus");
    }
    @FXML
    void homebutfun(ActionEvent event) throws IOException {
        App.setRoot("home");
    }

    @FXML
    void loginfun(ActionEvent event) throws IOException {
        App.setRoot("loginadmin");

    }

}
