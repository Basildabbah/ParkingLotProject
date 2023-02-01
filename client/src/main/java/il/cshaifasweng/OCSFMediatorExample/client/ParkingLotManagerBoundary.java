package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class ParkingLotManagerBoundary {
    public static String idd;
    public static String idd_parking_lot;
    @FXML
    private Label userid;
    @FXML
    private Button Logout;

    @FXML
    private Button Prices;

    @FXML
    private Button Setprices;

    @FXML
    private Button displayreports;

    @FXML
    private Button makereport;

    @FXML
    private Button showprices;

    @FXML
    void Logoutfun(ActionEvent event) throws IOException  {
        Message m=new Message("#logoutlotmanager",idd);
        try {
            SimpleClient.getClient().sendToServer(m);
        } catch (IOException e) {
            e.printStackTrace();
        }
        App.setRoot("home");
    }

    @FXML
    void Pricesfun(ActionEvent event) {

    }

    @FXML
    void Setpricesfun(ActionEvent event) throws IOException {
        setNewPrice.iddd_park=idd_parking_lot;
        App.setRoot("setNewPrice");
    }

    @FXML
    void displayreportsfun(ActionEvent event) throws IOException {
        App.setRoot("displayreportofmanager");

    }

    @FXML
    void makereportfun(ActionEvent event) {

    }

    @FXML
    void setpricefun(ActionEvent event) throws IOException {
        App.setRoot("showPriceManager");
    }
    @FXML
    void initialize() {
        userid.setText(userid.getText()+idd);
        Displayreportofmanager.idd_parking_lot=idd_parking_lot;
    }
}