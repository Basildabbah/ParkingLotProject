package il.cshaifasweng.OCSFMediatorExample.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class forgetpasssubscriber {
    @FXML
    private Button Prices;

    @FXML
    private Button about;

    @FXML
    private TextField email;

    @FXML
    private TextField firstname;

    @FXML
    private Button forgetpass;

    @FXML
    private Button homebut;

    @FXML
    private Label invaild;

    @FXML
    private TextField lastname;

    @FXML
    private Button login;
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
    void forgetpass(ActionEvent event) throws IOException {
        if(firstname.getText()==""||lastname.getText()==""||email.getText()=="")
        {
            invaild.setVisible(true);
        }
        else
        {
            App.setRoot("newpass");
        }
    }
}
