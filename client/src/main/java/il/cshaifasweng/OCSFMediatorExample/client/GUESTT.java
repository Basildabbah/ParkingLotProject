package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import org.greenrobot.eventbus.Subscribe;

import javax.swing.*;
import java.io.IOException;

public class GUESTT {

    @FXML
    private TextField Date;

    @FXML
    private Button FAQ;

    @FXML
    private MenuItem Park2;

    @FXML
    private MenuItem Park3;

    @FXML
    private MenuItem Park4;

    @FXML
    private MenuItem Park5;

    @FXML
    private Button Prices;

    @FXML
    private Button about;

    @FXML
    private TextField carnum;

    @FXML
    private TextField email;

    @FXML
    private Button homebut;

    @FXML
    private Label invaild;

    @FXML
    private Button login;

    @FXML
    private Button loginadmin;

    @FXML
    private Button loginadmin1;

    @FXML
    private Label loginassubscriber;

    @FXML
    private TextField ordernumber;

    @FXML
    private TextField ordernumber1;

    @FXML
    private TextField ordernumber11;
    @FXML
    private CheckBox preordercheckbox;
    @FXML
    private TextField ordernumber111;

    @FXML
    private TextField ordernumber1111;

    @FXML
    private TextField ordernumber11111;

    @FXML
    private TextField ordernumber111111;

    @FXML
    private TextField ordernumber1111111;

    @FXML
    private TextField ordernumber111112;

    @FXML
    private MenuItem park1;

    @FXML
    void FAQ(ActionEvent event) {

    }

    @FXML
    void Pricesfun(ActionEvent event) {

    }

    @FXML
    void aboutus(ActionEvent event) {

    }

    @FXML
    void homebutfun(ActionEvent event) throws IOException {
        App.setRoot("home");
    }

    @FXML
    void loginadminfun(ActionEvent event) {

    }

    @FXML
    void loginbutton(ActionEvent event) {

    }

    @FXML
    void loginfun(ActionEvent event) {
        try {
            SimpleClient.getClient().sendToServer( new Message("#loginguest",email.getText()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Subscribe
    public void onEvent(logingusetsuccEvent e){

    }

    @FXML
    void loginsubscriber(MouseEvent event) {

    }
    @FXML
    void preordercheckbox(ActionEvent event) {
        if(preordercheckbox.isSelected()==true)
        {
            Date.setVisible(true);
        }
        else {
            Date.setVisible(false);

        }
    }
}
