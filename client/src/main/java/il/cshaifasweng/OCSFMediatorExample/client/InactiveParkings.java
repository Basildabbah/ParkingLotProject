package il.cshaifasweng.OCSFMediatorExample.client; /**
 * Sample Skeleton for 'InactiveParkings.fxml' Controller Class
 */

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import static il.cshaifasweng.OCSFMediatorExample.client.ParkingLotEmployeeBoundary.parking_id;

public class InactiveParkings{

    @FXML // fx:id="Col"
    private TextField Col; // Value injected by FXMLLoader

    @FXML // fx:id="Depth"
    private TextField Depth; // Value injected by FXMLLoader

    @FXML // fx:id="Row"
    private TextField Row; // Value injected by FXMLLoader

    @FXML // fx:id="SetActive"
    private Button SetActive; // Value injected by FXMLLoader

    @FXML // fx:id="SetInactive"
    private Button SetInactive; // Value injected by FXMLLoader

    @FXML // fx:id="enterPlId"
    private TextField enterPlId; // Value injected by FXMLLoader

    @FXML // fx:id="returnSuccessFull"
    private Button returnSuccessFull; // Value injected by FXMLLoader

    @FXML // fx:id="returnToInactive1"
    private Button returnToInactive1; // Value injected by FXMLLoader

    @FXML // fx:id="returnToInactive2"
    private Button returnToInactive2; // Value injected by FXMLLoader

    @FXML // fx:id="returnToMain"
    private Button returnToMain; // Value injected by FXMLLoader

    @FXML // fx:id="updateSuccessFullAP"
    private AnchorPane updateSuccessFullAP; // Value injected by FXMLLoader

    @FXML // fx:id="wrongParkDataAP1"
    private AnchorPane wrongParkDataAP1; // Value injected by FXMLLoader

    @FXML // fx:id="wrongParkDataAP2"
    private AnchorPane wrongParkDataAP2; // Value injected by FXMLLoader

    @FXML
    void Coltxt(ActionEvent event) {

    }

    @FXML
    void Depthtxt(ActionEvent event) {

    }

    @FXML
    void Rowtxt(ActionEvent event) {

    }

    @FXML
    void SetActivebtn(ActionEvent event) {

        if (enterPlId.getText().equals("") || Row.getText().equals("") || Col.getText().equals("") || Depth.getText().equals("")){
            wrongParkDataAP2.setVisible(true);
        }
      /*  else if (!enterPlId.getText().matches("^[1-9]\\d*$") || !Row.getText().matches("^[1-9]\\d*$") || !Col.getText().matches("^[1-9]\\d*$") || !Depth.getText().matches("^[1-9]\\d*$")) {
            wrongParkDataAP1.setVisible(true);
        }*/

      /*  else if (Integer.parseInt(Row.getText())>2 || Integer.parseInt(Row.getText())<0 || Integer.parseInt(Depth.getText())>2 || Integer.parseInt(Depth.getText())<0){
            wrongParkDataAP1.setVisible(true);

        }*/
        else{
            Message m=new Message("#active",Integer.parseInt(enterPlId.getText()),Integer.parseInt(Row.getText()),Integer.parseInt(Col.getText()),Integer.parseInt(Depth.getText()));

            try {
                SimpleClient.getClient().sendToServer(m);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void SetInactivebtn(ActionEvent event) {
        if (enterPlId.getText().equals("") || Row.getText().equals("") || Col.getText().equals("") || Depth.getText().equals("")){
            wrongParkDataAP2.setVisible(true);
        }
       /* else if (!enterPlId.getText().matches("^[1-9]\\d*$") || !Row.getText().matches("^[1-9]\\d*$") || !Col.getText().matches("^[1-9]\\d*$") || !Depth.getText().matches("^[1-9]\\d*$")) {
            wrongParkDataAP1.setVisible(true);
        }
        else if (Integer.parseInt(Row.getText())>2 || Integer.parseInt(Row.getText())<0 || Integer.parseInt(Depth.getText())>2 || Integer.parseInt(Depth.getText())<0){
            wrongParkDataAP1.setVisible(true);

        }*/
        else{
            Message m=new Message("#inactive",Integer.parseInt(enterPlId.getText()),Integer.parseInt(Row.getText()),Integer.parseInt(Col.getText()),Integer.parseInt(Depth.getText()));
            try {
                SimpleClient.getClient().sendToServer(m);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void enterPlIdtxt(ActionEvent event) {

    }

    @FXML
    void returnSuccessFullbtn(ActionEvent event) {

    }

    @FXML
    void returnToInactive1btn(ActionEvent event) {
wrongParkDataAP1.setVisible(false);
    }

    @FXML
    void returnToInactive2btn(ActionEvent event) {
wrongParkDataAP2.setVisible(false);
    }

    @FXML
    void returnToMainbtn(ActionEvent event) throws IOException {
        App.setRoot("ParkingLotEmployeeBoundary");
    }
    @FXML
    void initialize() {
        //EventBus.getDefault().register(this);
        enterPlId.setText(parking_id);
        enterPlId.setDisable(true);
    }
}
