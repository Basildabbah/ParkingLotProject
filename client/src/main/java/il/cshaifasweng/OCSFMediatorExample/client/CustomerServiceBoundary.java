package il.cshaifasweng.OCSFMediatorExample.client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class CustomerServiceBoundary {

    @FXML
    private Button HandleComplains;

    @FXML
    private Button ReserveParking;

    @FXML
    private Button logout;

    @FXML
    void HandleComplainsfun(ActionEvent event) {

    }

    @FXML
    void ReserveParkingfun(ActionEvent event) {

    }

    @FXML
    void logoutfun(ActionEvent event) throws IOException {
    App.setRoot("home");
    }

}
