package il.cshaifasweng.OCSFMediatorExample.client;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Prices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class CustomerServiceBoundary {
    public static String name;
    public static String idd;

    @FXML
    private Button HandleComplains;

    @FXML
    private Button ReserveParking;

    @FXML
    private Button logout;
    @FXML
    private Label userid;
    @FXML
    void HandleComplainsfun(ActionEvent event) throws IOException {
        handlecomplain.idd=idd;
        App.setRoot("handlecomplain");
    }

    @FXML
    void ReserveParkingfun(ActionEvent event) {

    }

    @FXML
    void logoutfun(ActionEvent event) throws IOException {
        Message m=new Message("#logoutcusservices",idd);
        try {
            SimpleClient.getClient().sendToServer(m);
        } catch (IOException e) {
            e.printStackTrace();
        }
        App.setRoot("home");
    }
    @FXML
    void initialize() {
        userid.setText(userid.getText()+idd);
    }
}
