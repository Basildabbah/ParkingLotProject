package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.swing.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

public class GUESTT {

    @FXML
    private TextField CarNumber;

    @FXML
    private TextField Date;

    @FXML
    private TextField EnterDay;

    @FXML
    private TextField EnterHour;

    @FXML
    private TextField EnterMonth;

    @FXML
    private TextField EnterYear;

    @FXML
    private TextField ExitDay;

    @FXML
    private TextField ExitHour;

    @FXML
    private TextField ExitMonth;

    @FXML
    private TextField ExitYear;

    @FXML
    private Button FAQ;

    @FXML
    private Button GuestOrder;

    @FXML
    private TextField GuestType;

    @FXML
    private TextField ID;

    @FXML
    private TextField ParkingLotId;

    @FXML
    private TextField Password;

    @FXML
    private Button Prices;

    @FXML
    private Button about;

    @FXML
    private TextField carnum;

    @FXML
    private TextField email;

    @FXML
    private Button homebut;

    @FXML
    private Button login;

    @FXML
    private Button loginadmin;

    @FXML
    private TextField Email;
    @FXML
    private CheckBox preorder;
    @FXML
    private CheckBox OnSite;
    @FXML
    void FAQ(ActionEvent event) throws IOException {
        App.setRoot("faq0");
    }

    @FXML
    void GuestOrder(ActionEvent event) {

        if (preorder.isSelected()==true) {

            boolean OnSite = false;

            Message m = new Message("#GuestPreOrder", EnterHour.getText(), EnterDay.getText(), EnterMonth.getText(), EnterYear.getText(), ExitHour.getText(), ExitDay.getText(), ExitMonth.getText(), ExitYear.getText(), ParkingLotId.getText(), ID.getText(), Password.getText(),OnSite,CarNumber.getText(),Email.getText());

            try {
                SimpleClient.getClient().sendToServer(m);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (OnSite.isSelected()==true) {
            LocalTime Date1 = LocalTime.now();
            int Hour = Date1.getHour();

            LocalDate Date2 = LocalDate.now();
            int Day = Date2.getDayOfMonth();

            LocalDate Date3 = LocalDate.now();
            int Month = Date3.getMonthValue();

            LocalDate Date4 = LocalDate.now();
            int Year = Date4.getYear();

            boolean OnSite = true;

            Message m = new Message("#GuestOnSiteOrder", Hour, Day, Month, Year, ExitHour.getText(), ExitDay.getText(), ExitMonth.getText(), ExitYear.getText(), ParkingLotId.getText(), ID.getText(), Password.getText(),OnSite,CarNumber.getText(),Email.getText());

            try {
                SimpleClient.getClient().sendToServer(m);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void Pricesfun(ActionEvent event) throws IOException {
        App.setRoot("prices");

    }
    @FXML
    void aboutus(ActionEvent event) throws IOException {
        App.setRoot("aboutus");
    }
    @FXML
    void homebutfun(ActionEvent event) throws IOException {
        App.setRoot("home");
    }

    @FXML
    void loginadminfun(ActionEvent event) throws IOException {
        App.setRoot("loginadmin");
    }
    @FXML
    void loginfun(ActionEvent event) {

    }
    @FXML
    void initialize() {
        EventBus.getDefault().register(this);
    }
    @Subscribe
    public void setLabelshow3(GuestPreOrderEvent Response) throws IOException {
        Platform.runLater(() ->
                {
                    if (Response.getWarning().getMessage().equals("GuestPreOrder")) {
                        String Response1 = Response.getWarning().getObject1().toString();
                        if (Response1.equals("The Parking Lot is Full, Please Choose Another Parking Lot")) {
                            Alert alert = new Alert(Alert.AlertType.WARNING,
                                    String.format("The Parking Lot is Full, Please Choose Another Parking Lot")
                            );
                            alert.show();
                        }
                        if (Response1.equals("Your Order Confirmed")) {
                            String Response2 = Response.getWarning().getObject2().toString();
                            String Response3 = Response.getWarning().getObject3().toString();

                            Alert alert = new Alert(Alert.AlertType.WARNING,
                                    String.format("Your Order Confirmed \n Your Order Number is: %s \n You Will Be Charged: %s "
                                            , Response2, Response3)
                            );
                            alert.show();
                        }
                    }
                }
        );
    }

//		*************************************************************************************
//      4
//		*************************************************************************************

    @Subscribe
    public void setLabelshow4(GuestOnSiteOrderEvent Response) throws IOException {
        Platform.runLater(() ->
                {
                    if (Response.getWarning().getMessage().equals("GuestOnSiteOrder")) {
                        String Response1 = Response.getWarning().getObject1().toString();
                        if (Response1.equals("The Parking Lot is Full, Please Choose Another Parking Lot")) {
                            Alert alert = new Alert(Alert.AlertType.WARNING,
                                    String.format("%s", Response1)
                            );
                            alert.show();
                        }
                        if (Response1.equals("Your Order Confirmed")) {
                            String Response2 = Response.getWarning().getObject2().toString();
                            String Response3 = Response.getWarning().getObject3().toString();

                            Alert alert = new Alert(Alert.AlertType.WARNING,
                                    String.format("%s \n Your Order Number is: %s \n You Will Be Charged: %s "
                                            , Response1, Response2, Response3)
                            );
                            alert.show();
                        }
                    }
                }
        );
    }

}
