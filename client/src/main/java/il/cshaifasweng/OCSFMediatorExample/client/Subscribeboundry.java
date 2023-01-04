package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class Subscribeboundry {
    static String idd;
    static String type;

    @FXML
    private Button RenewSubscription;

    @FXML
    private AnchorPane addcaranchorpane;

    @FXML
    private Button cancelcomplain;

    @FXML
    private Button cancelorder;

    @FXML
    private TextField carnumbertextbox;

    @FXML
    private Button checkcomplain;

    @FXML
    private Button checkorder;

    @FXML
    private Button createorder;

    @FXML
    private Button enterpark;

    @FXML
    private Button exitpark;

    @FXML
    private TextField idtextbox;

    @FXML
    private Button logout;

    @FXML
    private Button removecar;

    @FXML
    private Button sendcomplain;

    @FXML
    private Button submit_add_car;

    @FXML
    void RenewSubscriptionfun(ActionEvent event) {

    }

    @FXML
    void addcarfun(ActionEvent event) {
        addcaranchorpane.setVisible(true);
        idtextbox.setText(idd);
        idtextbox.setDisable(true);

    }

    @FXML
    void cancelcomplainfun(ActionEvent event) {

    }

    @FXML
    void cancelorderfun(ActionEvent event) {

    }

    @FXML
    void checkcomplainfun(ActionEvent event) {

    }

    @FXML
    void checkorderfun(ActionEvent event) {

    }

    @FXML
    void createorderfun(ActionEvent event) {

    }

    @FXML
    void enterpark(ActionEvent event) {

    }

    @FXML
    void exitparkfun(ActionEvent event) {

    }

    @FXML
    void logoutfun(ActionEvent event) {

    }

    @FXML
    void removecarfun(ActionEvent event) {

    }

    @FXML
    void sendcomplainfun(ActionEvent event) {

    }
    @FXML
    void submit_add_carfun(ActionEvent event) {
        Message m=new Message("#addcar_full_subscriber",idd,carnumbertextbox.getText());
        try {
            SimpleClient.getClient().sendToServer(m);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
