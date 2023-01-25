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


public class showPriceManager {
    @FXML
    private Button FAQ;
    @FXML
    void FAQ(ActionEvent event) throws IOException {
        App.setRoot("faq0");
    }
    @FXML // fx:id="label1"
    private Label label1; // Value injected by FXMLLoader

    @FXML // fx:id="label11"
    private Label label11; // Value injected by FXMLLoader

    @FXML // fx:id="label111"
    private Label label111; // Value injected by FXMLLoader

    @FXML // fx:id="label1111"
    private Label label1111; // Value injected by FXMLLoader

    @FXML // fx:id="label11111"
    private Label label11111; // Value injected by FXMLLoader

    @FXML // fx:id="label111111"
    private Label label111111; // Value injected by FXMLLoader

    @FXML // fx:id="menu"
    private MenuButton menu; // Value injected by FXMLLoader

    @FXML // fx:id="park1"
    private MenuItem park1; // Value injected by FXMLLoader

    @FXML // fx:id="park2"
    private MenuItem park2; // Value injected by FXMLLoader

    @FXML // fx:id="park3"
    private MenuItem park3; // Value injected by FXMLLoader

    @FXML // fx:id="returnToMain"
    private Button returnToMain; // Value injected by FXMLLoader

    @FXML // fx:id="textbox"
    private SplitPane textbox; // Value injected by FXMLLoader

    @FXML
    void returnToMainbtn(ActionEvent event) throws IOException{
        App.setRoot("ParkingLotManagerBoundary");
    }

    @FXML
    void park1(ActionEvent event) {
        try {
            SimpleClient.getClient().sendToServer("#Managerprices_1");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //textbox.setVisible(true);
    }

    @FXML
    void park2(ActionEvent event) {
        try {
            SimpleClient.getClient().sendToServer("#Managerprices_2");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //textbox.setVisible(true);
    }
    @FXML
    void park3(ActionEvent event) {
        try {
            SimpleClient.getClient().sendToServer("#Managerprices_3");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //textbox.setVisible(true);
    }

    @Subscribe
    public void setLabelshow(showPriceManagerEvent c) {
        Platform.runLater(() -> {

            label1.setText((c.getWarning().getObject1().toString()));
            //paymentMethod.setText(m);
            //menu.setText(label1.getText());
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
