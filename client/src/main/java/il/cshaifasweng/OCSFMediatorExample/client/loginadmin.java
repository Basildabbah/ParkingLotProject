package il.cshaifasweng.OCSFMediatorExample.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class loginadmin {
    @FXML
    private Button Prices;

    @FXML
    private Button about;

    @FXML
    private Label forgetpass;

    @FXML
    private Button homebut;

    @FXML
    private TextField id;

    @FXML
    private Label invaild;

    @FXML
    private Button login;

    @FXML
    private Button loginadmin;

    @FXML
    private Label loginasguest;

    @FXML
    private Label loginassubscriber;

    @FXML
    private TextField pass;
    @FXML
    void loginbutton(ActionEvent event) {
        if(id.getText()==""||pass.getText()=="")
        {
            invaild.setVisible(true);
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
    @FXML
    void forgetpass(MouseEvent event) throws IOException {
        App.setRoot("forgetpass");

    }

    @FXML
    void loginguest(MouseEvent event) throws IOException {
        App.setRoot("loginasguest");

    }

    @FXML
    void loginsubscriber(MouseEvent event) throws IOException {
        App.setRoot("loginassubscriber");

    }
}
