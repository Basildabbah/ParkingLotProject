package il.cshaifasweng.OCSFMediatorExample.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javax.swing.*;
import java.io.IOException;

public class home {

    @FXML
    private Button ContactUs;

    @FXML
    private Button Prices;

    @FXML
    private Button about;

    @FXML
    private Button homebut;

    @FXML
    private Button loginadmin;

    @FXML
    private Button loginguist;

    @FXML
    private Button loginsubscriber;
    @FXML
    private Button subs;


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
    void loginadminfun(ActionEvent event) throws IOException {
        App.setRoot("loginadmin");

    }
    @FXML
    void loginguistfun(ActionEvent event) throws IOException {
        App.setRoot("loginasguest");
    }
    @FXML
    void loginsubscriberfun(ActionEvent event) throws IOException {
        App.setRoot("loginassubscriber");
    }
    @FXML
    void subsfun(ActionEvent event) throws IOException {
        App.setRoot("subscribe");

    }
}
