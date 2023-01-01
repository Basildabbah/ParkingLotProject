package il.cshaifasweng.OCSFMediatorExample.client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class ChainManagerBoundary {

    @FXML
    private Button CurrentParkingLotStatus;

    @FXML
    private Button confirmnewprice;

    @FXML
    private Button displayreports;

    @FXML
    private Button logout;

    @FXML
    void CurrentParkingLotStatusfun(ActionEvent event) {

    }

    @FXML
    void confirmnewpricefun(ActionEvent event) {

    }

    @FXML
    void displayreportsfun(ActionEvent event) {

    }

    @FXML
    void logoutfun(ActionEvent event) throws IOException {
    App.setRoot("home");
    }
}
