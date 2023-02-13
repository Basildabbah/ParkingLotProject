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
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.EventBus;

import javax.script.Bindings;
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
    private PasswordField pass1;
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
    void loginbutton(ActionEvent event) {
        if(showpass.isSelected()==true)
        {

        }
        if(showpass.isSelected()==false)
        {
            pass.setText(pass1.getText());
        }
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
    private Button FAQ;
    @FXML
    void FAQ(ActionEvent event) throws IOException {
        App.setRoot("faq0");
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
    @FXML
    void initialize() {
        EventBus.getDefault().register(this);
    }
    @Subscribe
    public void setLabelshow1(loginadminEvent c)throws IOException {
        Platform.runLater(()->{



            if (c.getWarning().getObject1().equals("yes parkinglotemployee"))
            {

                try {
                    ParkingLotEmployeeBoundary.idd=id.getText();
                    ParkingLotEmployeeBoundary.parking_id=c.getWarning().getObject2().toString();
                   /*        emaillabel.setText(email);
        firstlabel.setText(firstname);
        lastlabel.setText(lastname);
        useridlabel.setText(idd);
        typelabel.setText(type);*/
                    ParkingLotEmployeeBoundary.email=c.getWarning().getObject5().toString();
                    ParkingLotEmployeeBoundary.firstname=c.getWarning().getObject6().toString();
                    ParkingLotEmployeeBoundary.lastname=c.getWarning().getObject7().toString();
                    ParkingLotEmployeeBoundary.type=c.getWarning().getObject8().toString();

                    App.setRoot("ParkingLotEmployeeBoundary");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }



            if (c.getWarning().getObject1().equals("yes Chain Manager"))
            {

                try {
                    ChainManagerBoundary.name=id.getText();
                    ChainManagerBoundary.idd=id.getText();
                    /*				message.setObject10(p.getId());
					message.setObject11(p.getFirstName());
					message.setObject12(p.getLastName());
					message.setObject13(p.getEmail());*/
                    ChainManagerBoundary.idd=c.getWarning().getObject10().toString();
                    ChainManagerBoundary.firstname=c.getWarning().getObject11().toString();
                    ChainManagerBoundary.lastname=c.getWarning().getObject12().toString();
                    ChainManagerBoundary.email=c.getWarning().getObject13().toString();

                    App.setRoot("ChainManagerBoundary");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            if (c.getWarning().getObject1().equals("yes Customer Service"))
            {
                try {
                    CustomerServiceBoundary.name=id.getText();
                    CustomerServiceBoundary.idd=id.getText();
                    CustomerServiceBoundary.numofcomplain=c.getWarning().getObject14().toString();
                    App.setRoot("CustomerServiceBoundary");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (c.getWarning().getObject1().equals("yes parkinglotmanagers"))
            {
                ParkingLotManagerBoundary.idd=id.getText();
                ParkingLotManagerBoundary.email=c.getWarning().getObject5().toString();
                ParkingLotManagerBoundary.firstname=c.getWarning().getObject6().toString();
                ParkingLotManagerBoundary.lastname=c.getWarning().getObject7().toString();
                ParkingLotManagerBoundary.type=c.getWarning().getObject8().toString();
                try {
                    System.out.println("aaaaaaaaaa");
                    ParkingLotManagerBoundary.idd_parking_lot=c.getWarning().getObject2().toString();
                    System.out.println(ParkingLotManagerBoundary.idd_parking_lot);
                    App.setRoot("ParkingLotManagerBoundary");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else
            {
                invaild.setText("Invalid Data");
                invaild.setVisible(true);
            }
        });
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
