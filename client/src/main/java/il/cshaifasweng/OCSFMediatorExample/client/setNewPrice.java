package il.cshaifasweng.OCSFMediatorExample.client;
import java.io.IOException;
/**
 * Sample Skeleton for 'ParkingLotManagerBoundary.fxml' Controller Class
 */

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.EventBus;

import static il.cshaifasweng.OCSFMediatorExample.client.ParkingLotEmployeeBoundary.parking_id;

public class setNewPrice {

    public static String name;
    public static String iddd_park;

    @FXML // fx:id="APsetPrice"
    private AnchorPane APsetPrice; // Value injected by FXMLLoader

    @FXML // fx:id="Logout"
    private Button Logout; // Value injected by FXMLLoader

    @FXML // fx:id="ParkIdLabel"
    private Label ParkIdLabel; // Value injected by FXMLLoader

    @FXML // fx:id="ParkLotIdMenu"
    private MenuButton ParkLotIdMenu; // Value injected by FXMLLoader

    @FXML // fx:id="returnToMain"
    private Button returnToMain; // Value injected by FXMLLoader

    @FXML // fx:id="ParkingLotsLabel"
    private Label ParkingLotsLabel; // Value injected by FXMLLoader

    @FXML // fx:id="ReturnPriceEmptyBtn"
    private Button ReturnPriceEmptyBtn; // Value injected by FXMLLoader

    @FXML // fx:id="ReturnPriceUpdatedBtn"
    private Button ReturnPriceUpdatedBtn; // Value injected by FXMLLoader

    @FXML // fx:id="Setprices"
    private Button Setprices; // Value injected by FXMLLoader

    @FXML // fx:id="TOCbusiness"
    private MenuItem TOCbusiness; // Value injected by FXMLLoader

    @FXML // fx:id="TOCfullSub"
    private MenuItem TOCfullSub; // Value injected by FXMLLoader

    @FXML // fx:id="TOConSite"
    private MenuItem TOConSite; // Value injected by FXMLLoader

    @FXML // fx:id="TOConeCar"
    private MenuItem TOConeCar; // Value injected by FXMLLoader

    @FXML // fx:id="TOConeTime"
    private MenuItem TOConeTime; // Value injected by FXMLLoader

    @FXML // fx:id="displayreports"
    private Button displayreports; // Value injected by FXMLLoader

    @FXML // fx:id="makereport"
    private Button makereport; // Value injected by FXMLLoader

    @FXML // fx:id="newPayLabel"
    private Label newPayLabel; // Value injected by FXMLLoader

    @FXML // fx:id="newPayTx"
    private TextField newPayTx; // Value injected by FXMLLoader

    @FXML // fx:id="newPriceEmpty"
    private AnchorPane newPriceEmpty; // Value injected by FXMLLoader

    @FXML // fx:id="newPriceUpdated"
    private AnchorPane newPriceUpdated; // Value injected by FXMLLoader

    @FXML // fx:id="setNewPriceLabel"
    private Label setNewPriceLabel; // Value injected by FXMLLoader

    @FXML // fx:id="setPriceParkID1"
    private MenuItem setPriceParkID1; // Value injected by FXMLLoader

    @FXML // fx:id="setPriceParkID2"
    private MenuItem setPriceParkID2; // Value injected by FXMLLoader

    @FXML // fx:id="setPriceParkID3"
    private MenuItem setPriceParkID3; // Value injected by FXMLLoader

    @FXML // fx:id="setPriceParkID4"
    private MenuItem setPriceParkID4; // Value injected by FXMLLoader

    @FXML // fx:id="setPriceParkID5"
    private MenuItem setPriceParkID5; // Value injected by FXMLLoader

    @FXML // fx:id="setPriceParkID6"
    private MenuItem setPriceParkID6; // Value injected by FXMLLoader

    @FXML // fx:id="setPriceParkID7"
    private MenuItem setPriceParkID7; // Value injected by FXMLLoader

    @FXML // fx:id="showprices"
    private Button showprices; // Value injected by FXMLLoader

    @FXML // fx:id="typeClientMenu"
    private MenuButton typeClientMenu; // Value injected by FXMLLoader

    @FXML // fx:id="typeOfParkLabel"
    private Label typeOfParkLabel; // Value injected by FXMLLoader

    @FXML // fx:id="updatePriceBt"
    private Button updatePriceBt; // Value injected by FXMLLoader

    @FXML
    void Logoutfun(ActionEvent event) {

    }

    @FXML
    void ReturnPriceEmpty(ActionEvent event) {
        newPriceEmpty.setVisible(false);
    }

    @FXML
    void ReturnPriceUpdated(ActionEvent event) {
        newPriceUpdated.setVisible(false);
        ParkLotIdMenu.setText(iddd_park);
        ParkLotIdMenu.setDisable(true);
        typeClientMenu.setDisable(false);
        typeClientMenu.setText("Select Type of Client");
        newPayTx.setDisable(true);
        newPayTx.setText("");
        updatePriceBt.setDisable(true);

    }

    @FXML
    void returnToMainbtn(ActionEvent event) throws IOException{
        App.setRoot("ParkingLotManagerBoundary");
    }

    @FXML
    void Setpricesfun(ActionEvent event) {

        APsetPrice.setVisible(true);
        newPriceEmpty.setVisible(false);
        newPriceUpdated.setVisible(false);
        ParkingLotsLabel.setVisible(false);
        ParkLotIdMenu.setText("Select Parking Lot ID");
        typeClientMenu.setDisable(true);
        typeClientMenu.setText("Select Type of Client");
        newPayTx.setDisable(true);
        newPayTx.setText("");
        updatePriceBt.setDisable(true);
    }

    @FXML
    void displayreportsfun(ActionEvent event) {

    }

    @FXML
    void makereportfun(ActionEvent event) {

    }

    @FXML
    void sendNewPrice(ActionEvent event) throws IOException {
        if (newPayTx.getText().equals("")) {
            newPriceEmpty.setVisible(true);
        }
        else {
            Message m=new Message("#sendNewPrice",ParkLotIdMenu.getText(),typeClientMenu.getText(),newPayTx.getText());
            try {
                SimpleClient.getClient().sendToServer(m);
            } catch (IOException e) {
                e.printStackTrace();
            }
            newPriceUpdated.setVisible(true);
        }

    }

    @FXML
    void setMenuPriceParkID1(ActionEvent event) {
        ParkLotIdMenu.setText(setPriceParkID1.getText());
        typeClientMenu.setDisable(false);
        typeClientMenu.setText("Select Type of Client");
        newPayTx.setDisable(true);
        updatePriceBt.setDisable(true);
    }

    @FXML
    void setMenuPriceParkID2(ActionEvent event) {
        ParkLotIdMenu.setText(setPriceParkID2.getText());
        typeClientMenu.setDisable(false);
        typeClientMenu.setText("Select Type of Client");
        newPayTx.setDisable(true);
        updatePriceBt.setDisable(true);
    }

    @FXML
    void setMenuPriceParkID3(ActionEvent event) {
        ParkLotIdMenu.setText(setPriceParkID3.getText());
        typeClientMenu.setDisable(false);
        typeClientMenu.setText("Select Type of Client");
        newPayTx.setDisable(true);
        updatePriceBt.setDisable(true);
    }

    @FXML
    void setMenuPriceParkID4(ActionEvent event) {
        ParkLotIdMenu.setText(setPriceParkID4.getText());
        typeClientMenu.setDisable(false);
        typeClientMenu.setText("Select Type of Client");
        newPayTx.setDisable(true);
        updatePriceBt.setDisable(true);
    }

    @FXML
    void setMenuPriceParkID5(ActionEvent event) {
        ParkLotIdMenu.setText(setPriceParkID5.getText());
        typeClientMenu.setDisable(false);
        typeClientMenu.setText("Select Type of Client");
        newPayTx.setDisable(true);
        updatePriceBt.setDisable(true);
    }

    @FXML
    void setMenuPriceParkID6(ActionEvent event) {
        ParkLotIdMenu.setText(setPriceParkID6.getText());
        typeClientMenu.setDisable(false);
        typeClientMenu.setText("Select Type of Client");
        newPayTx.setDisable(true);
        updatePriceBt.setDisable(true);
    }

    @FXML
    void setMenuPriceParkID7(ActionEvent event) {
        ParkLotIdMenu.setText(setPriceParkID7.getText());
        typeClientMenu.setDisable(false);
        typeClientMenu.setText("Select Type of Client");
        newPayTx.setDisable(true);
        updatePriceBt.setDisable(true);
    }

    @FXML
    void setTOCbusiness(ActionEvent event) {
        typeClientMenu.setText(TOCbusiness.getText());
        newPayTx.setDisable(false);
        updatePriceBt.setDisable(false);
    }

    @FXML
    void setTOCfullSub(ActionEvent event) {
        typeClientMenu.setText(TOCfullSub.getText());
        newPayTx.setDisable(false);
        updatePriceBt.setDisable(false);
    }

    @FXML
    void setTOConSite(ActionEvent event) {
        typeClientMenu.setText(TOConSite.getText());
        newPayTx.setDisable(false);
        updatePriceBt.setDisable(false);
    }

    @FXML
    void setTOConeCar(ActionEvent event) {
        typeClientMenu.setText(TOConeCar.getText());
        newPayTx.setDisable(false);
        updatePriceBt.setDisable(false);
    }

    @FXML
    void setTOConeTime(ActionEvent event) {
        typeClientMenu.setText(TOConeTime.getText());
        newPayTx.setDisable(false);
        updatePriceBt.setDisable(false);
    }

    @FXML
    void showPricefun(ActionEvent event) {
    }
    @FXML
    void initialize() {
        //EventBus.getDefault().register(this);
        ParkLotIdMenu.setText(iddd_park);
        ParkLotIdMenu.setDisable(true);
    }
}
