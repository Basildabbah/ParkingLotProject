package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Admin;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class loginadmin {
    public static String str=" ";
    public static void setStr(String str) {
        il.cshaifasweng.OCSFMediatorExample.client.loginadmin.str=str;
    }
    public static String getStr() {
        return str;
    }
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
    private static Button loginadmin;

    @FXML
    private Label loginasguest;

    @FXML
    private Label loginassubscriber;

    @FXML
    private TextField pass;

    @FXML
    void loginbutton(ActionEvent event) {
        if(pass.getText()=="")pass.setStyle("-fx-border-color: red");
        if(id.getText()=="")id.setStyle("-fx-border-color: red");
        Message m=new Message("#loginadmin",id.getText(),pass.getText());
        try {
            SimpleClient.getClient().sendToServer(m);
        } catch (IOException e) {
            e.printStackTrace();
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
    @Subscribe
    public void setLabelshow(WarningEvent c)throws IOException {
        Platform.runLater(()->{
        if (c.getWarning().getObject1().equals("yes Chain Manager"))
        {
            try {
                ChainManagerBoundary.name=id.getText();

                App.setRoot("ChainManagerBoundary");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        if (c.getWarning().getObject1().equals("yes Customer Service"))
        {
            try {
                CustomerServiceBoundary.name=id.getText();
                App.setRoot("CustomerServiceBoundary");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (c.getWarning().getObject1().equals("yes parkinglotmanagers"))
        {
                try {
                    App.setRoot("ParkingLotManagerBoundary");
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
