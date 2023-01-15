package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Complaint;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.util.ArrayList;

public class StatusComplain_SUBSCRIBER {
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
        idS.setDisable(true);
        idS.setText(idd);
        EventBus.getDefault().register(this);
        labelid.setText("User ID:"+idd);

    }




    @FXML
    private Button bring;

    @FXML
    private TableColumn<Complaint, String> complaintc;

    @FXML
    private TextField idS;

    @FXML
    private TableColumn<Complaint, Integer> idc;

    @FXML
    private TableColumn<Complaint, String> statusc;

    @FXML
    private TableView<Complaint> table;


    @FXML
    void bring(ActionEvent event) {
        try {
            Message s1=new Message("bring ^"+idS.getText(),"wajd","wajd2","wajd3","wajd4");
            SimpleClient.getClient().sendToServer(s1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Subscribe
    public void showComplaints(StatusComplaintEvent e){
        Platform.runLater(()->{
            table.setVisible(true);
            idc.setCellValueFactory(new PropertyValueFactory<Complaint, Integer>("id"));
            statusc.setCellValueFactory(new PropertyValueFactory<Complaint, String>("complaintMessage"));
            complaintc.setCellValueFactory(new PropertyValueFactory<Complaint, String>("status"));
            ObservableList<Complaint> list = FXCollections.observableList((ArrayList<Complaint>) e.getWarning().getObject1());
            table.setItems(list);
        });
    }
}
