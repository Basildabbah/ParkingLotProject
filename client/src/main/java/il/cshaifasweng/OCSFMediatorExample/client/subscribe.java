package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Admin;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class subscribe {
    @FXML
    private Button FAQ;
    @FXML
    void FAQ(ActionEvent event) throws IOException {
        App.setRoot("faq0");
    }
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
        if(id.getText().equals("")||visa.getText().equals("")||password.getText().equals("")
        ||email.getText().equals("")||lastname.getText().equals("")||firstname.getText().equals(""))
        {
            invaild.setText("Fill All The Data");
            invaild.setVisible(true);
        }
       else if(!id.getText().matches("\\d+"))
        {
            id.setStyle("-fx-border-color: red");

        }
        if(!visa.getText().matches("\\d+"))
        {
            visa.setStyle("-fx-border-color: red");

        }
       else if(full.isSelected()&&regular.isSelected())
        {
            invaild.setText("Choose One Type");
            invaild.setVisible(true);
           full.setSelected(false);
           regular.setSelected(false);
        }
       else if(full.isSelected()==false&&regular.isSelected()==false)
        {
            invaild.setText("Choose Type");
            invaild.setVisible(true);
        }
        else
        {
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
    @Subscribe
    public void setLabelshow(new_subscirber_EVENT c)throws IOException {
        Platform.runLater(()->{
            System.out.println("as");
            invaild.setVisible(true);
            if(c.getWarning().getObject1().toString().equals("email is used before"))
            invaild.setText("email is used before,Change it");
            if(c.getWarning().getObject1().toString().equals("id is used before"))
                invaild.setText("id is used before,Change it");
            if(c.getWarning().getObject1().toString().equals("yes"))
            {
                if (full.isSelected()==true) {
                    Subscribeboundry.idd = id.getText().toString();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION,
                            String.format("Success: %s\n You Will Pay: %s\n",
                                    c.getWarning().getObject1(),
                                    "400 $")
                    );

                    alert.show();
                }
                if (regular.isSelected()==true) {
                    Subscribeboundry.idd = id.getText().toString();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION,
                            String.format("Success: %s\n You Will Pay: %s\n",
                                    c.getWarning().getObject1(),
                                    "300 $")
                    );

                    alert.show();
                }
                try {
                    App.setRoot("loginassubscriber");

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
