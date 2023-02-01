package il.cshaifasweng.OCSFMediatorExample.client;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

public class ChainManagerBoundary {
    public static String name;
    public static String idd;
    @FXML
    private Button CurrentParkingLotStatus;
    @FXML
    private Label userid;
    @FXML
    private Button confirmnewprice;

    @FXML
    private Button displayreports;

    @FXML
    private Button logout;

    @FXML
    void CurrentParkingLotStatusfun(ActionEvent event) {
        System.out.println(name);

    }

    @FXML
    void confirmnewpricefun(ActionEvent event) throws IOException {
        App.setRoot("ConfirmNewPrice");
    }

    @FXML
    void displayreportsfun(ActionEvent event) throws IOException {
    App.setRoot("displayreportofchain");
    }

    @FXML
    void logoutfun(ActionEvent event) throws IOException {
        Message m=new Message("#logoutchain",idd);
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