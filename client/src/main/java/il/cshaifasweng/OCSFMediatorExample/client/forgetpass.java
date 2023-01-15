package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;

public class forgetpass {

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
    private TextField id;

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
        Message m = new Message("#admin_forgetpass", id.getText(), firstname.getText(), lastname.getText(), email.getText());
        try {
            SimpleClient.getClient().sendToServer(m);
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
    @Subscribe
    public void setLabelshow(admin_forgetpass_EVENT c)throws IOException {
        Platform.runLater(()->{
            if (c.getWarning().getObject1().equals("yes"))
            {
            String x= (String) c.getWarning().getObject2();
            newpass.idd= Integer.parseInt(x);
                try {
                    System.out.println(    newpass.idd);
                    App.setRoot("newpass");
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
}
