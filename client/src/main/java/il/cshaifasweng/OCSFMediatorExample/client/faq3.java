package il.cshaifasweng.OCSFMediatorExample.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class faq3 {
    @FXML
    private Button FAQ;
    @FXML
    void FAQ(ActionEvent event) throws IOException {
        App.setRoot("faq0");
    }
    @FXML
    private Button Prices;

    @FXML
    private Button about;

    @FXML
    private Button homebut;

    @FXML
    private Button loginadmin;

    @FXML
    private Button loginadmin1;

    @FXML
    private Label quest1;

    @FXML
    private Label quest2;

    @FXML
    private Label quest3;

    @FXML
    private Label quest4;

    @FXML
    private Label quest5;

    @FXML
    private Label quest6;


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
    void quest1fun(MouseEvent event) throws IOException {
        App.setRoot("faq1");
    }

    @FXML
    void quest2fun(MouseEvent event)  throws IOException {
        App.setRoot("faq2");
    }


    @FXML
    void quest3fun(MouseEvent event) throws IOException {
        App.setRoot("faq0");
    }
    @FXML
    void quest4fun(MouseEvent event) throws IOException {
        App.setRoot("faq4");
    }
    @FXML
    void quest5fun(MouseEvent event)  throws IOException {
        App.setRoot("faq5");
    }
    @FXML
    void quest6fun(MouseEvent event)  throws IOException {
        App.setRoot("faq6");
    }
}
