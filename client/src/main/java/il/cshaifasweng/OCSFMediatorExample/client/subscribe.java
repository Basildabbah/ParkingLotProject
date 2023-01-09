package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

public class subscribe {

    @FXML
    private Button Prices;

    @FXML
    private Button Subscribe;

    @FXML
    private Button about;

    @FXML
    private AnchorPane anch1;

    @FXML
    private TextField email;

    @FXML
    private TextField firstname;

    @FXML
    private TextField id;

    @FXML
    private CheckBox full;

    @FXML
    private Button homebut;

    @FXML
    private Label invaild;

    @FXML
    private TextField lastname;

    @FXML
    private Button login;

    @FXML
    private Label loginasguest;

    @FXML
    private Label loginassubscriber;

    @FXML
    private TextField password;

    @FXML
    private CheckBox regular;

    @FXML
    private TextField visa;

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
    void loginguest(MouseEvent  event) throws IOException {
        App.setRoot("loginasguest");
    }
    @FXML
    void loginsubscriber(MouseEvent  event) throws IOException {
        App.setRoot("loginassubscriber");
    }

    @FXML
    void Subscribebuttom(ActionEvent event) throws IOException {
        String type="";
        if(id.getText().matches("\\d+"))
        {

            System.out.println("Input is all digits");

        }
        else
        {
            id.setStyle("-fx-border-color: red");
        }
        if(full.isSelected()&&regular.isSelected())
        {
           full.setSelected(false);
           regular.setSelected(false);
        }
        else
        {
            System.out.println("hi");
            if(full.isSelected())
            {
                type="full";
            }
            else
            {
                type="regular";
            }
            Message m=new Message("#newsubscribe",id.getText(),firstname.getText(),lastname.getText()
                    ,email.getText(),password.getText(),visa.getText(),type);
            try {
                SimpleClient.getClient().sendToServer(m);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
