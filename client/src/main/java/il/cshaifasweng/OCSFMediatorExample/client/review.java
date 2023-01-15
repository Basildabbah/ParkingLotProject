package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.print.DocFlavor;
import javax.swing.*;
import java.io.IOException;

public class review<Static> {
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
    private TextField review_id;

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
    private Button Submit;
    @FXML
    private TextField idtextbox;
    @FXML
    private TextField textboxreview;
    @FXML
    private TextField nickname;

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
    private ImageView star5;
    @FXML
    private ImageView star4;
    @FXML
    private ImageView star3;
    @FXML
    private ImageView star2;
    @FXML
    private ImageView star1;
    @FXML
    private ImageView gold1;

    @FXML
    private ImageView gold2;

    @FXML
    private ImageView gold3;

    @FXML
    private ImageView gold4;

    @FXML
    private ImageView gold5;

    int numberofstars=0;
    @FXML
    void star1(MouseEvent event) {
    gold1.setVisible(true);
    gold2.setVisible(false);
    gold3.setVisible(false);
    gold4.setVisible(false);
    gold5.setVisible(false);
    numberofstars=1;
    }

    @FXML
    void star2(MouseEvent event) {
        gold1.setVisible(true);
        gold2.setVisible(true);
        gold3.setVisible(false);
        gold4.setVisible(false);
        gold5.setVisible(false);
        numberofstars=2;

    }

    @FXML
    void star3(MouseEvent event) {
        gold1.setVisible(true);
        gold2.setVisible(true);
        gold3.setVisible(true);
        gold4.setVisible(false);
        gold5.setVisible(false);
        numberofstars=3;

    }

    @FXML
    void star4(MouseEvent event) {
        gold1.setVisible(true);
        gold2.setVisible(true);
        gold3.setVisible(true);
        gold4.setVisible(true);
        gold5.setVisible(false);
        numberofstars=4;

    }
    @FXML
    void star5(MouseEvent event) {
        gold1.setVisible(true);
        gold2.setVisible(true);
        gold3.setVisible(true);
        gold4.setVisible(true);
        gold5.setVisible(true);
        numberofstars=5;

    }

    @FXML
    void RenewSubscriptionfun(ActionEvent event) {

    }
    @FXML
    void addreviewfun(ActionEvent event) {


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
    void Submit(ActionEvent event) throws IOException {
        if (nickname.getText().equals(""))
        {
            nickname.setText("UnKnown");
        }
        Message m=new Message("#writeareview",nickname.getText(),textboxreview.getText(),numberofstars);
        try {
            SimpleClient.getClient().sendToServer(m);
        } catch (IOException e) {
            e.printStackTrace();
        }
        App.setRoot("subscribeboundry");
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
    void logoutfun(ActionEvent event) throws IOException {
        Message m=new Message("#logoutsubscirber",idd);
        try {
            SimpleClient.getClient().sendToServer(m);
        } catch (IOException e) {
            e.printStackTrace();
        }
        App.setRoot("loginassubscriber");
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
    @Subscribe
    public void setLabelshow(reviewEvent c)throws IOException {
        Platform.runLater(()->{

        });
    }
    @FXML
    void initialize() {
        review_id.setText(idd);
        review_id.setDisable(true);
    }
}
