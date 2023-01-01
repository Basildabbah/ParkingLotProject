package il.cshaifasweng.OCSFMediatorExample.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class newpasssubscirber {
    @FXML
    private Button Confirm;

    @FXML
    private TextField Password1;

    @FXML
    private TextField Password2;

    @FXML
    private Button Prices;

    @FXML
    private Button about;

    @FXML
    private Button homebut;

    @FXML
    private Label invaild;

    @FXML
    private Button login;

    @FXML
    void Confirm(ActionEvent event) throws IOException {


        if (!Password1.getText().equals(Password2.getText()))
        {
            invaild.setVisible(true);
        }
        else
        {
            App.setRoot("loginadmin");

        }
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
