package il.cshaifasweng.OCSFMediatorExample.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class ParkingLotManagerBoundary {


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
    void Logoutfun(ActionEvent event) throws IOException {
    App.setRoot("home");
    }

    @FXML
    void Pricesfun(ActionEvent event) {

    }

    @FXML
    void Setpricesfun(ActionEvent event) {

    }

    @FXML
    void displayreportsfun(ActionEvent event) {

    }

    @FXML
    void makereportfun(ActionEvent event) {

    }

    @FXML
    void setpricefun(ActionEvent event) {

    }
}