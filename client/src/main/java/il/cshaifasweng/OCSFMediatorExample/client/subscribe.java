package il.cshaifasweng.OCSFMediatorExample.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class subscribe {

    @FXML
    private Button Prices;

    @FXML
    private Button Subscribe;

    @FXML
    private Button Subscribe1;

    @FXML
    private Button about;

    @FXML
    private AnchorPane anch1;

    @FXML
    private AnchorPane anch2;

    @FXML
    private TextField email;

    @FXML
    private TextField email1;

    @FXML
    private TextField enterhour;

    @FXML
    private TextField exithour;

    @FXML
    private TextField firstname;

    @FXML
    private TextField firstname1;

    @FXML
    private CheckBox full;

    @FXML
    private CheckBox full1;

    @FXML
    private Button homebut;

    @FXML
    private Label invaild;

    @FXML
    private Label invaild1;

    @FXML
    private TextField lastname;

    @FXML
    private TextField lastname1;

    @FXML
    private Button login;

    @FXML
    private Label loginasguest;

    @FXML
    private Label loginasguest1;

    @FXML
    private Label loginassubscriber;

    @FXML
    private Label loginassubscriber1;

    @FXML
    private TextField parkingid;

    @FXML
    private TextField password;

    @FXML
    private TextField password1;

    @FXML
    private CheckBox regular;

    @FXML
    private CheckBox regular1;

    @FXML
    private Button typeguest;

    @FXML
    private Button typesubscribe;

    @FXML
    private TextField visa;

    @FXML
    private TextField visa1;






    @FXML
    void Pricesfun(ActionEvent event) throws IOException {
        App.setRoot("prices");
    }

    @FXML
    void Subscribebuttom(ActionEvent event) {

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
    void loginfun(ActionEvent event)throws IOException {
        App.setRoot("loginadmin");

    }

    @FXML
    void loginguest(MouseEvent event)throws IOException {
        App.setRoot("loginasguest");
    }

    @FXML
    void loginsubscriber(MouseEvent event) throws IOException {
    App.setRoot("loginassubscriber");
    }

    @FXML
    void typeguest(ActionEvent event) {
     anch1.setVisible(false);
     anch2.setVisible(true);

    }

    @FXML
    void typesubscribe(ActionEvent event) {
        anch1.setVisible(true);
        anch2.setVisible(false);
    }
}
