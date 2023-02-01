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
    private TextField NumberOfOrder_Check;

    @FXML
    private TextField Password_Check;
    @FXML
    private TextField ID_Check;

    @FXML
    private TextField ID_Cancel;
    @FXML
    private TextField Password_Cancel;
    @FXML
    private TextField NumberOfOrder_Cancel;
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
    private Label enterlabel;

    @FXML
    void FAQ(ActionEvent event) throws IOException {
        App.setRoot("faq0");
    }

    @FXML
    void CheckOrderStatus(ActionEvent event) {
        Message m = new Message("#CheckOrderStatus", ID_Check.getText(), Password_Check.getText(), NumberOfOrder_Check.getText());
        try {
            SimpleClient.getClient().sendToServer(m);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void onsitecheck(ActionEvent event) {
        if (OnSite.isSelected() == true) {
            EnterDay.setVisible(false);
            EnterHour.setVisible(false);
            EnterMonth.setVisible(false);
            EnterYear.setVisible(false);
            enterlabel.setVisible(false);

        } else {
            EnterDay.setVisible(true);
            EnterHour.setVisible(true);
            EnterMonth.setVisible(true);
            EnterYear.setVisible(true);
            enterlabel.setVisible(true);
        }
    }

    @FXML
    void GuestOrder(ActionEvent event) throws IOException {

        if (preorder.isSelected() == true) {

            boolean OnSite = false;

            Message m = new Message("#GuestPreOrder", EnterHour.getText(), EnterDay.getText(), EnterMonth.getText(), EnterYear.getText(), ExitHour.getText(), ExitDay.getText(), ExitMonth.getText(), ExitYear.getText(), ParkingLotId.getText(), ID.getText(), Password.getText(), OnSite, CarNumber.getText(), Email.getText());

            try {
                SimpleClient.getClient().sendToServer(m);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (OnSite.isSelected() == true) {

            LocalTime Date1 = LocalTime.now();
            int Hour = Date1.getHour();

            LocalDate Date2 = LocalDate.now();
            int Day = Date2.getDayOfMonth();

            LocalDate Date3 = LocalDate.now();
            int Month = Date3.getMonthValue();

            LocalDate Date4 = LocalDate.now();
            int Year = Date4.getYear();

            boolean OnSite = true;

            Message m = new Message("#GuestOnSiteOrder", Hour, Day, Month, Year, ExitHour.getText(), ExitDay.getText(), ExitMonth.getText(), ExitYear.getText(), ParkingLotId.getText(), ID.getText(), Password.getText(), OnSite, CarNumber.getText(), Email.getText());

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


    @FXML
    void CancelOrder(ActionEvent event)throws IOException  {

        Message m = new Message("#CancelOrder", ID_Cancel.getText(), Password_Cancel.getText(), NumberOfOrder_Cancel.getText());
        try {
            SimpleClient.getClient().sendToServer(m);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Subscribe
    public void setLabelshow1(CheckOrderStatusEvent Response) throws IOException {
        Platform.runLater(() ->
                {
                    if (Response.getWarning().getMessage().equals("CheckOrderStatus")) {
                        String Response1 = Response.getWarning().getObject1().toString();
                        if (Response1.equals("One Of The Details is Not Right")) {
                            Alert alert = new Alert(Alert.AlertType.WARNING,
                                    String.format("%s", Response1)
                            );
                            alert.show();
                        }
                        if (Response1.equals("Check Order Status Accepted")) {
                            String Response2 = Response.getWarning().getObject2().toString();
                            String Response3 = Response.getWarning().getObject3().toString();
                            String Response4 = Response.getWarning().getObject4().toString();
                            String Response5 = Response.getWarning().getObject5().toString();
                            String Response6 = Response.getWarning().getObject6().toString();
                            String Response7 = Response.getWarning().getObject7().toString();
                            String Response8 = Response.getWarning().getObject8().toString();
                            String Response9 = Response.getWarning().getObject9().toString();
                            String Response10 = Response.getWarning().getObject10().toString();
                            String Response11 = Response.getWarning().getObject11().toString();
                            String Response12 = Response.getWarning().getObject12().toString();
                            String Response13 = Response.getWarning().getObject13().toString();
                            String Response14 = Response.getWarning().getObject14().toString();


                            Alert alert = new Alert(Alert.AlertType.WARNING, String.format(
                                    "%s \n %s %s \n OrderID is: %s \n PersonID is: %s \n ParkingLotID is: %s \n Enter Hour is: %s \n Enter Day is: %s \n Enter Month is: %s \n Enter Year is %s \n Exit Hour is: %s \n Exit Day is: %s \n Exit Month is: %s \n Exit Year is %s \n",
                                    Response1, Response2, Response3, Response4, Response5, Response6, Response7,
                                    Response8, Response9, Response10, Response11, Response12, Response13, Response14)
                            );
                            alert.show();
                        }
                    }
                }

        );
    }

    @Subscribe
    public void setLabelshow2(CancelOrderEvent Response) throws IOException {
        Platform.runLater(() ->
                {
                    System.out.println("bbb");
                    if (Response.getWarning().getMessage().equals("CancelOrder")) {
                        String Response1 = Response.getWarning().getObject1().toString();
                        if (Response1.equals("One Of The Details is Not Right")) {
                            Alert alert = new Alert(Alert.AlertType.WARNING,
                                    String.format("One Of The Details is Not Right")
                            );
                            alert.show();
                        }
                        if (Response1.equals("Your Order Has Been Canceled")) {
                            String Response2 = Response.getWarning().getObject2().toString();
                            String Response3 = Response.getWarning().getObject3().toString();
                            if (Response2.equals("The Refund in Money is: ")) {
                                Alert alert = new Alert(Alert.AlertType.WARNING,
                                        String.format("%s \n  %s %s"
                                                , Response1, Response2, Response3)
                                );
                                alert.show();
                            }
                            if (Response2.equals("The Refund in Hours is: ")) {
                                Alert alert = new Alert(Alert.AlertType.WARNING,
                                        String.format("%s \n  %s %s"
                                                , Response1, Response2, Response3)
                                );
                                alert.show();
                            }
                        }
                    }
                }
        );
    }

}
