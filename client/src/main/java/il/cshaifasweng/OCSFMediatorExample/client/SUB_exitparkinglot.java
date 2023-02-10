package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

public class SUB_exitparkinglot {
    public static String idd;
    static String type;
    public static String pass;
    @FXML
    private TextField CarNumber;

    @FXML
    private TextField Date;

    @FXML
    private Button GuestOrder;

    @FXML
    private TextField ID;

    @FXML
    private TextField ParkingLotId;

    @FXML
    private AnchorPane ancherpane_enter;

    @FXML
    private Button back;

    @FXML
    void CreateOrder(MouseEvent event) throws IOException {
        App.setRoot("SUB_enterparkinglot");
        SUB_enterparkinglot.type=type;
        SUB_enterparkinglot.pass=pass;
        SUB_enterparkinglot.idd=idd;
    }

    @FXML
    void Exit(ActionEvent event) {
        try {
            SimpleClient.getClient().sendToServer(new Message("#exitrparkinglot", Integer.parseInt(ParkingLotId.getText()) ,
                    Integer.parseInt(CarNumber.getText()) , Integer.parseInt(ID.getText())));
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    @FXML
    void back(ActionEvent event) throws IOException {
        App.setRoot("subscribeboundry");
    }
    @FXML
    void initialize() {
        ID.setText(idd);
        ID.setDisable(true);

    }
}