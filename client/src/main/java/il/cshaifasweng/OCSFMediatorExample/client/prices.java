package il.cshaifasweng.OCSFMediatorExample.client;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import il.cshaifasweng.OCSFMediatorExample.entities.Prices;

import java.io.IOException;
import java.util.ArrayList;


public class prices {

    @FXML
    private Button Prices;

    @FXML
    private Button about;

    @FXML
    private Button homebut;

    @FXML
    private Label invaild1;

    @FXML
    private Label label1;

    @FXML
    private Label label11;

    @FXML
    private Label label111;

    @FXML
    private Label label1111;

    @FXML
    private Label label11111;

    @FXML
    private Label label111111;

    @FXML
    private Button login;

    @FXML
    private Button loginadmin1;

    @FXML
    private Label loginassubscriber1;

    @FXML
    private MenuButton menu;

    @FXML
    private Button order;

    @FXML
    private TextField ordernumber1;

    @FXML
    private TextField ordernumber11;

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
    private TextField ordernumber11111111;

    @FXML
    private TextField ordernumber1111121;

    @FXML
    private TextField ordernumber111121;
    @FXML
    private Label hasorder;
    @FXML
    private MenuItem park1;

    @FXML
    private MenuItem park2;
    @FXML
    private MenuItem park3;

    @FXML
    private Label paymentMethod;

    @FXML
    private Button reserve;

    @FXML
    private AnchorPane reserveanch;

    @FXML
    private AnchorPane textbox;

    @FXML
    void Pricesfun(ActionEvent event) throws IOException {
    App.setRoot("prices");
    }

    @FXML
    void alreadyhasorder(MouseEvent  event) throws IOException {
        App.setRoot("loginasguest");
    }
    @FXML
    void aboutus(ActionEvent event) throws IOException {
        App.setRoot("aboutus");
    }

    @FXML
    void homebutfun(ActionEvent event)  throws IOException {
        App.setRoot("home");
    }

    @FXML
    void loginbutton(ActionEvent event) {

    }

    @FXML
    void loginfun(ActionEvent event)  throws IOException {
        App.setRoot("loginadmin");
    }

    @FXML
    void loginsubscriber(MouseEvent event) {

    }

    @FXML
    void order(ActionEvent event) {

    }

    @FXML
    void park1(ActionEvent event) {
        try {
            SimpleClient.getClient().sendToServer("prices_1");
        } catch (IOException e) {
            e.printStackTrace();
        }
        textbox.setVisible(true);
        ordernumber111121.setText("1");
        ordernumber111121.setDisable(true);
        reserveanch.setVisible(false);

    }

    @FXML
    void park2(ActionEvent event) {
        try {
            SimpleClient.getClient().sendToServer("prices_2");
        } catch (IOException e) {
            e.printStackTrace();
        }        reserveanch.setVisible(false);

        textbox.setVisible(true);
        ordernumber111121.setText("2");
        ordernumber111121.setDisable(true);
    }
    @FXML
    void park3(ActionEvent event) {
        try {
            SimpleClient.getClient().sendToServer("prices_3");
        } catch (IOException e) {
            e.printStackTrace();

        }
        reserveanch.setVisible(false);

        textbox.setVisible(true);
        ordernumber111121.setText("3");
        ordernumber111121.setDisable(true);
    }
    @FXML
    void reservefun(ActionEvent event) {
    reserveanch.setVisible(true);
    textbox.setVisible(false);
    }
    @Subscribe
    public void setLabelshow(loginadminEvent c) {
        Platform.runLater(() -> {
            String m="You Can Pay By ";
            String[] n = (String[]) c.getWarning().getObject3();
            for (int i=0;i<3;i++)
            {
                m+=n[i]+", ";
            }

            label1.setText((c.getWarning().getObject1().toString()));
            paymentMethod.setText(m);
            menu.setText(label1.getText());
            label11.setText(c.getWarning().getObject4().toString());
            label111.setText(c.getWarning().getObject2().toString());
            label1111.setText(c.getWarning().getObject5().toString());
            label11111.setText(c.getWarning().getObject6().toString());
            label111111.setText(c.getWarning().getObject7().toString());
            textbox.setVisible(true);

        });
    }
    @FXML
    void initialize() {
        EventBus.getDefault().register(this);
    }

}
