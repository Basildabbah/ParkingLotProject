package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

public class Subscribeboundry {
    public static String idd;
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
    private Button addreviewfun;

    @FXML
    private Button sendcomplain;

    @FXML
    private Button submit_add_car;
    @FXML
    private Label labelid;
    @FXML
    void RenewSubscriptionfun(ActionEvent event) {

    }
    @FXML
    void addreviewfun(ActionEvent event) throws IOException {
        review.idd=idd;
        App.setRoot("review");
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
    void checkcomplainfun(ActionEvent event) throws IOException {
        StatusComplain_SUBSCRIBER.idd=idd;
        App.setRoot("StatusComplain_SUBSCRIBER");
    //App.setRoot("StatusComplaint");
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
    void logoutfun(ActionEvent event) throws IOException {
        Message m=new Message("#logoutsubscirber",idd);
        try {
            SimpleClient.getClient().sendToServer(m);
        } catch (IOException e) {
            e.printStackTrace();
        }
        App.setRoot("home");
    }

    @FXML
    void removecarfun(ActionEvent event) {

    }

    @FXML
    void sendcomplainfun(ActionEvent event) throws IOException {
        NewComplaint1.idd=idd;
        App.setRoot("NewComplaint1");
    }
    @FXML
    void submit_add_carfun(ActionEvent event) {
        System.out.println(idd.toString());
        Message m=new Message("#addcar_full_subscriber",idd,carnumbertextbox.getText());
        try {
            SimpleClient.getClient().sendToServer(m);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void initialize() {
        labelid.setText("User ID:"+idd);

    }
}
