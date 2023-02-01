package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;

public class NewComplaint1 {
    static String idd;
    static String type;
    @FXML
    void park1(ActionEvent event) {
        parking_id.setText("1");
    }

    @FXML
    void park2(ActionEvent event) {
        parking_id.setText("2");

    }

    @FXML
    void park3(ActionEvent event) {
        parking_id.setText("3");

    }

    @FXML
    void park4(ActionEvent event) {
        parking_id.setText("4");

    }

    @FXML
    void park5(ActionEvent event) {
        parking_id.setText("5");

    }
    @FXML
    private SplitMenuButton parking_id;
    @FXML
    void park6(ActionEvent event) {
        parking_id.setText("6");

    }

    @FXML
    void park7(ActionEvent event) {
        parking_id.setText("7");

    }
    ///////////////////////
    @FXML
    private TextField textC;
    @FXML
    private TextField id;
    @FXML
    private Button send;
    ///////////////////////
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
    private Label complainnum;

    @FXML
    private TextField parkingId;
    @FXML
    private Button addreviewfun;

    @FXML
    private Button sendcomplain;

    @FXML
    private Button submit_add_car;

    @FXML
    void RenewSubscriptionfun(ActionEvent event) {

    }
    @FXML
    void addreviewfun(ActionEvent event) throws IOException {
        review.idd=idd;
        App.setRoot("review");
    }
    @FXML
    void send(ActionEvent event) {
        try {

            SimpleClient.getClient().sendToServer(new Message("newCompliain^"+textC.getText()+"^" +idd,parking_id.getText()));
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        App.setRoot("home");
    }

    @FXML
    void removecarfun(ActionEvent event) {

    }

    @FXML
    void sendcomplainfun(ActionEvent event) throws IOException {
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
    void Back(ActionEvent event) throws IOException {
    App.setRoot("subscribeboundry");
    }


    @FXML
    void initialize() {
        id.setText(idd);
        id.setDisable(true);
        EventBus.getDefault().register(this);
    }
    @Subscribe
    public void onEvent(NewComplaintEvent e){
        Platform.runLater(()-> {
            complainnum.setText(complainnum.getText()+" "+e.getWarning().getObject1().toString());
             complainnum.setVisible(true);


            Alert alert = new Alert(Alert.AlertType.INFORMATION,
                    String.format("Your Complain Number is: %s\nTimestamp: %s\n",
                            e.getWarning().getObject1(),
                            e.getWarning().getTime().toString())
            );
            alert.show();
            try {
                App.setRoot("subscribeboundry");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

}
