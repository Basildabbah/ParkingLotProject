package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;

public class loginassubscriber {

    @FXML
    private Button Prices;

    @FXML
    private Label ForgetPassword;
    @FXML
    private Button about;

    @FXML
    private TextField accountid;

    @FXML
    private Button homebut;

    @FXML
    private Label invaild;

    @FXML
    private Button login;

    @FXML
    private Button loginadmin;

    @FXML
    private Label loginasguest;

    @FXML
    private TextField password;

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
    void loginguest(MouseEvent event)throws IOException {
        App.setRoot("loginasguest");

    }
    @FXML
    void loginbutton(ActionEvent event) throws IOException {


        Message m=new Message("#loginsubscriber",accountid.getText(),password.getText());
        try {
            SimpleClient.getClient().sendToServer(m);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @FXML
    void ForgetPassword(MouseEvent event)  throws IOException {
        App.setRoot("forgetpasssubscriber");
    }






    @Subscribe
    public void setLabelshow(loginadminEvent c)throws IOException {
        Platform.runLater(()->{
            if (c.getWarning().getObject1().equals("yes full_subscriber"))
            {
                Subscribeboundry.idd=accountid.getText();
                Subscribeboundry.type="Full";
                Subscribeboundry.pass=password.getText();
                Subscribeboundry.firstname=c.getWarning().getObject7().toString();
                Subscribeboundry.lastname=c.getWarning().getObject8().toString();
                Subscribeboundry.email=c.getWarning().getObject9().toString();
                System.out.println("full_subscriber");
                try {
                    App.setRoot("subscribeboundry");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            if (c.getWarning().getObject1().equals("yes regular_subscriber"))
            {
                Subscribeboundry.idd=accountid.getText();
                Subscribeboundry.type="Regular";
                Subscribeboundry.pass=password.getText();
                Subscribeboundry.firstname=c.getWarning().getObject7().toString();
                Subscribeboundry.lastname=c.getWarning().getObject8().toString();
                Subscribeboundry.email=c.getWarning().getObject9().toString();

                System.out.println("regular_subscriber");
                try {
                    App.setRoot("subscribeboundry");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else
            {
                invaild.setVisible(true);
            }
        });
    }
    @FXML
    void initialize() {
        EventBus.getDefault().register(this);
    }
    @Subscribe
    public void setLabelshow(twoclientEVENT c)throws IOException {
        Platform.runLater(()->{
            Alert alert = new Alert(Alert.AlertType.WARNING,
                    String.format("Message: %s\nTimestamp: %s\n",
                            "You Cant join With Same User From 2 Clients",
                            c.getWarning().getTime().toString())
            );
            alert.show();
        });
    }
}
