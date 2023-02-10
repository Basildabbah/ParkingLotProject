package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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
    private PasswordField pass1;
    @FXML
    private Button loginadmin;

    @FXML
    private Label loginasguest;

    @FXML
    private TextField pass;
    @FXML
    private CheckBox showpass;
    @FXML
    void showpass(ActionEvent event) {
        if(showpass.isSelected()==true)
        {
            pass.setText(pass1.getText());
            pass1.setVisible(false);
            pass.setVisible(true);
            System.out.println("yes selected"+pass.getText());

        }
        else
        {
            System.out.println("not selected"+pass.getText());
            pass1.setText(pass.getText());
            pass.setVisible(false);
            pass1.setVisible(true);

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
    void loginguest(MouseEvent event)throws IOException {
        App.setRoot("loginasguest");

    }
    @FXML
    void loginbutton(ActionEvent event) throws IOException {
        if(showpass.isSelected()==true)
        {

        }
        if(showpass.isSelected()==false)
        {
            pass.setText(pass1.getText());
        }
        if(pass.getText()=="")pass.setStyle("-fx-border-color: red");
        if(accountid.getText()=="")accountid.setStyle("-fx-border-color: red");

        Message m=new Message("#loginsubscriber",accountid.getText(),pass.getText());
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
                Subscribeboundry.pass=pass.getText();
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
                Subscribeboundry.pass=pass.getText();
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
