package il.cshaifasweng.OCSFMediatorExample.client;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

import static il.cshaifasweng.OCSFMediatorExample.client.ReserveParking.setRet_;

public class ParkingLotEmployeeBoundary {
    public static String idd;
    public static String parking_id;

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
    void ReserveParkingfun(ActionEvent event) throws IOException {
        setRet_(1);
        App.setRoot("ReserveParking");
    }


    @FXML
    void SendToAlternativeParkingLotfun(ActionEvent event) {

    }

    @FXML
    void SetupParkingLotfun(ActionEvent event) {

    }

    @FXML
    void logoutfun(ActionEvent event) throws IOException  {
        Message m=new Message("#logoutlotemployee",idd);
        try {
            SimpleClient.getClient().sendToServer(m);
        } catch (IOException e) {
            e.printStackTrace();
        }
        App.setRoot("home");
    }
    @FXML
    void parkingnotactivefun(ActionEvent event) throws IOException {

        App.setRoot("InactiveParkings");
    }
}