/**
 * Sample Skeleton for 'ConfirmNewPrice.fxml' Controller Class
 */

package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.EventBus;


import java.io.IOException;
import java.util.List;

public class ConfirmNewPrice {

                @FXML // fx:id="C1"
                private MenuItem C1; // Value injected by FXMLLoader

                @FXML // fx:id="C2"
                private MenuItem C2; // Value injected by FXMLLoader

                @FXML // fx:id="C3"
                private MenuItem C3; // Value injected by FXMLLoader

                @FXML // fx:id="C4"
                private MenuItem C4; // Value injected by FXMLLoader

                @FXML // fx:id="C5"
                private MenuItem C5; // Value injected by FXMLLoader

                @FXML // fx:id="CD1"
                private MenuButton CD1; // Value injected by FXMLLoader

                @FXML // fx:id="CD2"
                private MenuButton CD2; // Value injected by FXMLLoader

                @FXML // fx:id="CD3"
                private MenuButton CD3; // Value injected by FXMLLoader

                @FXML // fx:id="CD4"
                private MenuButton CD4; // Value injected by FXMLLoader

                @FXML // fx:id="CD5"
                private MenuButton CD5; // Value injected by FXMLLoader

                @FXML // fx:id="CDVB"
                private VBox CDVB; // Value injected by FXMLLoader

                @FXML // fx:id="D1"
                private MenuItem D1; // Value injected by FXMLLoader

                @FXML // fx:id="D2"
                private MenuItem D2; // Value injected by FXMLLoader

                @FXML // fx:id="D3"
                private MenuItem D3; // Value injected by FXMLLoader

                @FXML // fx:id="D4"
                private MenuItem D4; // Value injected by FXMLLoader

                @FXML // fx:id="D5"
                private MenuItem D5; // Value injected by FXMLLoader

                @FXML // fx:id="ParkingLot1"
                private MenuItem ParkingLot1; // Value injected by FXMLLoader

                @FXML // fx:id="ParkingLot2"
                private MenuItem ParkingLot2; // Value injected by FXMLLoader

                @FXML // fx:id="ParkingLot3"
                private MenuItem ParkingLot3; // Value injected by FXMLLoader

                @FXML // fx:id="ParkingLot4"
                private MenuItem ParkingLot4; // Value injected by FXMLLoader

                @FXML // fx:id="ParkingLot5"
                private MenuItem ParkingLot5; // Value injected by FXMLLoader

                @FXML // fx:id="enterPlId"
                private TextField enterPlId; // Value injected by FXMLLoader

                @FXML // fx:id="errorAP"
                private AnchorPane errorAP; // Value injected by FXMLLoader

                @FXML // fx:id="getprice"
                private Button getprice; // Value injected by FXMLLoader

                @FXML // fx:id="new1LB"
                private Label new1LB; // Value injected by FXMLLoader

                @FXML // fx:id="new2LB"
                private Label new2LB; // Value injected by FXMLLoader

                @FXML // fx:id="new3LB"
                private Label new3LB; // Value injected by FXMLLoader

                @FXML // fx:id="new4LB"
                private Label new4LB; // Value injected by FXMLLoader

                @FXML // fx:id="new5LB"
                private Label new5LB; // Value injected by FXMLLoader

                @FXML // fx:id="newPricesVB"
                private VBox newPricesVB; // Value injected by FXMLLoader

                @FXML // fx:id="old1LB"
                private Label old1LB; // Value injected by FXMLLoader

                @FXML // fx:id="old2LB"
                private Label old2LB; // Value injected by FXMLLoader

                @FXML // fx:id="old3LB"
                private Label old3LB; // Value injected by FXMLLoader

                @FXML // fx:id="old4LB"
                private Label old4LB; // Value injected by FXMLLoader

                @FXML // fx:id="old5LB"
                private Label old5LB; // Value injected by FXMLLoader

                @FXML // fx:id="oldPricesVB"
                private VBox oldPricesVB; // Value injected by FXMLLoader

                @FXML // fx:id="parkingLotId"
                private Label parkingLotId; // Value injected by FXMLLoader

                @FXML // fx:id="returnSuccessFull"
                private Button returnSuccessFull; // Value injected by FXMLLoader

                @FXML // fx:id="returnToMain"
                private Button returnToMain; // Value injected by FXMLLoader

                @FXML // fx:id="returnToNewPrices1"
                private Button returnToNewPrices1; // Value injected by FXMLLoader

                @FXML // fx:id="returnToNewPrices2"
                private Button returnToNewPrices2; // Value injected by FXMLLoader

                @FXML // fx:id="updatePrices"
                private Button updatePrices; // Value injected by FXMLLoader

                @FXML // fx:id="updateSuccessFullAP"
                private AnchorPane updateSuccessFullAP; // Value injected by FXMLLoader

                @FXML // fx:id="wrongParkIdAP1"
                private AnchorPane wrongParkIdAP1; // Value injected by FXMLLoader

                @FXML
                void C1btn(ActionEvent event) {
                        CD1.setText(C1.getText());
                }

                @FXML
                void C2btn(ActionEvent event) {
                        CD2.setText(C2.getText());
                }

                @FXML
                void C3btn(ActionEvent event) {
                        CD3.setText(C3.getText());
                }

                @FXML
                void C4btn(ActionEvent event) {
                        CD4.setText(C4.getText());
                }

                @FXML
                void C5btn(ActionEvent event) {
                        CD5.setText(C5.getText());
                }

                @FXML
                void CD1btn(ActionEvent event) {

                }

                @FXML
                void CD2btn(ActionEvent event) {

                }

                @FXML
                void CD3btn(ActionEvent event) {

                }

                @FXML
                void CD4btn(ActionEvent event) {

                }

                @FXML
                void CD5btn(ActionEvent event) {

                }

                @FXML
                void D1btn(ActionEvent event) {
                        CD1.setText(D1.getText());
                }

                @FXML
                void D2btn(ActionEvent event) {
                        CD2.setText(D2.getText());
                }

                @FXML
                void D3btn(ActionEvent event) {
                        CD3.setText(D3.getText());
                }

                @FXML
                void D4btn(ActionEvent event) {
                        CD4.setText(D4.getText());
                }

                @FXML
                void D5btn(ActionEvent event) {
                        CD5.setText(D5.getText());
                }

                @FXML
                void ParkingLot1btn(ActionEvent event) {

                }

                @FXML
                void ParkingLot2btn(ActionEvent event) {

                }

                @FXML
                void ParkingLot3btn(ActionEvent event) {

                }

                @FXML
                void ParkingLot4btn(ActionEvent event) {

                }

                @FXML
                void ParkingLot5btn(ActionEvent event) {

                }

                @FXML
                void enterPlIdtxt(ActionEvent event) {

                }

                @FXML
                void getpricebtn(ActionEvent event) throws IOException {
                        String ParkID = enterPlId.getText();
                        if (ParkID.equals("")){
                                wrongParkIdAP1.setVisible(true);
                                updatePrices.setDisable(true);
                                returnToMain.setDisable(true);
                                CDVB.setDisable(true);
                                getprice.setDisable(true);
                                enterPlId.setDisable(true);
                        }
                        else if (ParkID.matches("^[1-9]\\d*$")){
                                Message m=new Message("#getNewPrice");
                                m.setObject1(ParkID);
                                try {
                                        SimpleClient.getClient().sendToServer(m);
                                } catch (IOException e) {
                                        e.printStackTrace();
                                }
                                m.setMessage("#getOldPrice");
                                 m.setObject1(ParkID);
                                try {
                                     SimpleClient.getClient().sendToServer(m);
                                } catch (IOException e) {
                                e.printStackTrace();
                                }
                        }
                        else{
                                wrongParkIdAP1.setVisible(true);
                                updatePrices.setDisable(true);
                                returnToMain.setDisable(true);
                                CDVB.setDisable(true);
                                getprice.setDisable(true);
                                enterPlId.setDisable(true);
                        }
                }

                @FXML
                void returnSuccessFullbtn(ActionEvent event) throws IOException{
                        App.setRoot("ConfirmNewPrice");
                }

                @FXML
                void returnToMainbtn(ActionEvent event) throws IOException{
                        App.setRoot("ChainManagerBoundary");
                }

                @FXML
                void returnToNewPrices2btn(ActionEvent event) {
                    try {
                        App.setRoot("ConfirmNewPrice");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }/*
                        wrongParkIdAP1.setVisible(false);
                        updatePrices.setDisable(true);
                        returnToMain.setDisable(false);
                        CDVB.setDisable(true);
                        getprice.setDisable(false);
                        enterPlId.setDisable(false);*/
                }

                @FXML
                void returnToNewPrices1btn(ActionEvent event) {
                        errorAP.setVisible(false);
                        updatePrices.setDisable(false);
                        returnToMain.setDisable(false);
                        CDVB.setDisable(false);
                        getprice.setDisable(false);
                        enterPlId.setDisable(false);
                }

                @FXML
                void updatePricesbtn(ActionEvent event) throws IOException{
                        boolean i1 = !CD1.isDisabled() &&  CD1.getText().equals("On Site") ;
                        boolean i2 = !CD2.isDisabled() && CD2.getText().equals("Preorder");
                        boolean i3 = !CD3.isDisabled() && CD3.getText().equals("Client for One Car");
                        boolean i4 = !CD4.isDisabled() && CD4.getText().equals("Client for a Number of Cars");
                        boolean i5 = !CD5.isDisabled() && CD5.getText().equals("Full Subscription");
                        System.out.println(i1);
                        if (i1 || i2 || i3 || i4 || i5){
                                errorAP.setVisible(true);
                                updatePrices.setDisable(true);
                                returnToMain.setDisable(true);
                                CDVB.setDisable(true);
                                getprice.setDisable(true);
                                enterPlId.setDisable(true);
                        }
                        else{
                                Message m=new Message("#UpdateNewPrices");
                                if (CD1.getText().equals("Confirm"))
                                    m.setObject1("1");
                                else
                                    m.setObject1("0");
                            if (CD2.getText().equals("Confirm"))
                                m.setObject2("1");
                            else
                                m.setObject2("0");
                            if (CD3.getText().equals("Confirm"))
                                m.setObject3("1");
                            else
                                m.setObject3("0");
                            if (CD4.getText().equals("Confirm"))
                                m.setObject4("1");
                            else
                                m.setObject4("0");
                            if (CD5.getText().equals("Confirm"))
                                m.setObject5("1");
                            else
                                m.setObject5("0");
                            m.setObject6(parkingLotId.getText().charAt(12));
                            System.out.println(parkingLotId.getText().charAt(12));

                                try {
                                        SimpleClient.getClient().sendToServer(m);
                                } catch (IOException e) {
                                        e.printStackTrace();
                                }
                                updateSuccessFullAP.setVisible(true);
                            updatePrices.setDisable(true);
                            returnToMain.setDisable(false);
                            CDVB.setDisable(true);
                            getprice.setDisable(true);
                            enterPlId.setDisable(true);
                        }


                }
        @Subscribe
        public void setLabelshow(ConfirmNewPriceEvent c)throws IOException {
            System.out.println("Hello");
                Platform.runLater(()->{
                        if (c.getWarning().getObject1().equals("error"))
                        {

                            wrongParkIdAP1.setVisible(true);
                                updatePrices.setDisable(true);
                                returnToMain.setDisable(true);
                                CDVB.setDisable(true);
                                getprice.setDisable(true);
                                enterPlId.setDisable(true);

                        }
                    if (c.getWarning().getObject1().equals("error1"))
                    {


                    }
                        if (c.getWarning().getObject1().equals("old")) {
                            String PLID = enterPlId.getText();
                            CD1.setText("On Site") ;
                            CD2.setText("Preorder");
                            CD3.setText("Client for One Car");
                            CD4.setText("Client for a Number of Cars");
                            CD5.setText("Full Subscription");
                            parkingLotId.setText("Parking Lot "+ PLID);
                            parkingLotId.setVisible(true);
                            oldPricesVB.setVisible(true);
                            old1LB.setText((String) c.getWarning().getObject2());
                            old2LB.setText((String) c.getWarning().getObject3());
                            old3LB.setText((String) c.getWarning().getObject4());
                            old4LB.setText((String) c.getWarning().getObject5());
                            old5LB.setText((String) c.getWarning().getObject6());
                        }
                        if (c.getWarning().getObject1().equals("new")) {
                                newPricesVB.setVisible(true);
                                CDVB.setDisable(false);
                                if (c.getWarning().getObject2().equals("-1")){
                                        new1LB.setText("-");
                                        CD1.setDisable(true);
                                }
                                else {
                                        new1LB.setText((String) c.getWarning().getObject2());
                                        CD1.setDisable(false);
                                        updatePrices.setDisable(false);
                                }
                                if (c.getWarning().getObject3().equals("-1")){
                                        new2LB.setText("-");
                                        CD2.setDisable(true);
                                }
                                else {
                                        new2LB.setText((String) c.getWarning().getObject3());
                                        CD2.setDisable(false);
                                        updatePrices.setDisable(false);
                                }
                                if (c.getWarning().getObject4().equals("-1")){
                                        new3LB.setText("-");
                                        CD3.setDisable(true);
                                }
                                else {
                                        new3LB.setText((String) c.getWarning().getObject4());
                                        CD3.setDisable(false);
                                    updatePrices.setDisable(false);
                                }
                                if (c.getWarning().getObject5().equals("-1")){
                                        new4LB.setText("-");
                                        CD4.setDisable(true);
                                }
                                else {
                                        new4LB.setText((String) c.getWarning().getObject5());
                                        CD4.setDisable(false);
                                    updatePrices.setDisable(false);
                                }
                                if (c.getWarning().getObject6().equals("-1")){
                                        new5LB.setText("-");
                                        CD5.setDisable(true);
                                }
                                else {
                                        new5LB.setText((String) c.getWarning().getObject6());
                                        CD5.setDisable(false);
                                    updatePrices.setDisable(false);
                                }

                        }

                });
        }
    @FXML
    void initialize() {
        EventBus.getDefault().register(this);
    }
        }

