package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;

public class newpass {
    static int idd=0;

    @FXML
    private Button Confirm;

    @FXML
    private TextField Password1;

    @FXML
    private TextField Password2;

    @FXML
    private Button Prices;

    @FXML
    private Button about;

    @FXML
    private Button homebut;

    @FXML
    private Label invaild;
    @FXML
    private ImageView im;
    @FXML
    private Button login;
    @FXML
    private Label s;
    @FXML
    private Label ss;
    @FXML
    private Label sss;
    @FXML
    void Confirm(ActionEvent event) throws IOException {


        if (!Password1.getText().equals(Password2.getText()))
        {
            invaild.setVisible(true);
        }
        else
        {
            Message m=new Message("#newpassadmin",idd,Password1.getText());
            try {
                SimpleClient.getClient().sendToServer(m);
            } catch (IOException e) {
                e.printStackTrace();
            }
            s.setVisible(true);
            Password1.setVisible(false);
            Password2.setVisible(false);
            ss.setVisible(false);
            sss.setVisible(false);
            invaild.setVisible(false);
            im.setVisible(true);

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
    @Subscribe
    public void setLabelshow(loginadminEvent c)throws IOException {
        Platform.runLater(()->{
            if (c.getWarning().getObject1().equals("yes"))
            {
                try {
                    App.setRoot("loginadmin");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        });
    }
    @FXML
    void initialize() {
        EventBus.getDefault().register(this);
    }

}
