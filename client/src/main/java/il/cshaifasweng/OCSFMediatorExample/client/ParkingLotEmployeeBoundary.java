package il.cshaifasweng.OCSFMediatorExample.client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class ParkingLotEmployeeBoundary {
    @FXML
    private Button ReserveParking;

    @FXML
    private Button SendToAlternativeParkingLot;

    @FXML
    private Button SetupParkingLot;

    @FXML
    private Label label;

    @FXML
    private Button logout;

    @FXML
    private Button parkingnotactive;

    @FXML
    void ReserveParkingfun(ActionEvent event) {

    }

    @FXML
    void SendToAlternativeParkingLotfun(ActionEvent event) {

    }

    @FXML
    void SetupParkingLotfun(ActionEvent event) {

    }

    @FXML
    void logoutfun(ActionEvent event) throws IOException {
        App.setRoot("home");
    }

    @FXML
    void parkingnotactivefun(ActionEvent event) {

    }
}
