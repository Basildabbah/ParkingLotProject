package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class GUEST_exitparkinglot {

    @FXML
    private TextField CarNumber;



    @FXML
    private TextField ID;

    @FXML
    private TextField ParkingLotId;



    @FXML
    void CreateOrder(MouseEvent event) throws IOException {
        App.setRoot("home");

    }

    @FXML
    void Exit(ActionEvent event) {
        if(ID.getText().equals("")||CarNumber.getText().equals(""))
        {
            if(ID.getText().equals("")){
                ID.setStyle("-fx-border-color: red");
              }
            if(CarNumber.getText().equals("")){
                CarNumber.setStyle("-fx-border-color: red");
            }
        }
        else {
            try {
                SimpleClient.getClient().sendToServer(new Message("#exitrparkinglot", Integer.parseInt(ParkingLotId.getText()),
                        Integer.parseInt(CarNumber.getText()), Integer.parseInt(ID.getText())));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void back(ActionEvent event) throws IOException {
        App.setRoot("GUEST_create_order");
    }
    @FXML
    void initialize() {
    }
}