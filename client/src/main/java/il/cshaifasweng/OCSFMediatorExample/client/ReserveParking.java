package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;

public class ReserveParking {
static int  ret_ = -1;

    public static int getRet_() {
        return ret_;
    }

    public static void setRet_(int ret_) {
        ReserveParking.ret_ = ret_;
    }

    @FXML
    private TextField ID;

    @FXML
    private TextField ID_Cancel;

    @FXML
    private TextField NumberOfOrder_Cancel;

    @FXML
    private TextField ParkingLotId;

    @FXML
    private TextField SubscriptionNumber;

    @FXML
    private TextField Password;

    @FXML
    private TextField Password_Cancel;

    @FXML
    private TextField EnterDay;

    @FXML
    private TextField EnterHour;

    @FXML
    private TextField EnterMonth;

    @FXML
    private TextField EnterYear;

    @FXML
    private Label label_cancel_reservation;

    @FXML
    private Label label_reservation;
    @FXML
    private TextField ExitDay;

    @FXML
    private TextField ExitHour;

    @FXML
    private TextField ExitMonth;

    @FXML
    private TextField ExitYear;

    @FXML
    private TextField GuestType;

    @FXML
    private Button Return;


    @FXML
    private TextField SubscriberType1;

    @FXML
    private TextField SubscriberType2;

    @FXML
    private Button GuestOrderButton;

    @FXML
    private Button SubscriberOrderButton;

    @FXML
    private TextField ID_Check;

    @FXML
    private TextField NumberOfOrder_Check;

    @FXML
    private TextField Password_Check;

    @FXML
    private TextField CarNumber;

    @FXML
    private TextField Email;

    @FXML
    void ReturnParkingLotEmployee(ActionEvent event) throws IOException {
        if (getRet_() == 1){
            App.setRoot("ParkingLotEmployeeBoundary");
        }
        if (getRet_() == 0){
            App.setRoot("CustomerServiceBoundary");
        }
    }

    @FXML
    void CheckOrderStatus(ActionEvent event) {
        Message m = new Message("#CheckReservationStatus", ID_Check.getText(), Password_Check.getText(), NumberOfOrder_Check.getText());
        try {
            SimpleClient.getClient().sendToServer(m);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void CancelOrder(ActionEvent event) {
        int cancelcheck=0;
        NumberOfOrder_Cancel.setStyle("-fx-border-color: white");
        Password_Cancel.setStyle("-fx-border-color: white");
        ID_Cancel.setStyle("-fx-border-color: white");
        if(!ID_Cancel.getText().matches("\\d+")) {
            ID_Cancel.setStyle("-fx-border-color: red");
            label_cancel_reservation.setText("Wrong Data: ID contains letters");
            cancelcheck++;
        }
        if(!NumberOfOrder_Cancel.getText().matches("\\d+")) {
            NumberOfOrder_Cancel.setStyle("-fx-border-color: red");
            label_cancel_reservation.setText("Wrong Data: NumberOfOrder contains letters");
            cancelcheck++;
        }
        if (ID_Cancel.getText().equals("")) {
            ID_Cancel.setStyle("-fx-border-color: red");
            label_cancel_reservation.setText("Fill All Input");
            cancelcheck++;
        }
        if (Password_Cancel.getText().equals("")) {
            Password_Cancel.setStyle("-fx-border-color: red");
            label_cancel_reservation.setText("Fill All Input");
            cancelcheck++;
        }
        if (NumberOfOrder_Cancel.getText().equals("")) {
            NumberOfOrder_Cancel.setStyle("-fx-border-color: red");
            label_cancel_reservation.setText("Fill All Input");
            cancelcheck++;
        }
        if (cancelcheck>0) {
            label_cancel_reservation.setVisible(true);
        }
        if (cancelcheck==0) {
            label_cancel_reservation.setVisible(false);
            Message m = new Message("#CancelReservation", ID_Cancel.getText(), Password_Cancel.getText(), NumberOfOrder_Cancel.getText());
            try {
                SimpleClient.getClient().sendToServer(m);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void GuestOrder(ActionEvent event) {
        ID.setStyle("-fx-border-color: white");
        ExitYear.setStyle("-fx-border-color: white");
        EnterYear.setStyle("-fx-border-color: white");
        CarNumber.setStyle("-fx-border-color: white");
        Password.setStyle("-fx-border-color: white");
        Email.setStyle("-fx-border-color: white");

        int check=0;
        if(!ExitYear.getText().matches("\\d+"))
        {
            ExitYear.setStyle("-fx-border-color: red");
            label_reservation.setText("Wrong Data: ExitYear contains letters");
            check++;
        }
        if(!EnterYear.getText().matches("\\d+"))
        {
            EnterYear.setStyle("-fx-border-color: red");
            label_reservation.setText("Wrong Data: EnterYear contains letters");
            check++;
        }
        if(!CarNumber.getText().matches("\\d+"))
        {
            CarNumber.setStyle("-fx-border-color: red");
            label_reservation.setText("Wrong Data: CarNumber contains letters");
            check++;
        }
        if(!ID.getText().matches("\\d+"))
        {
            ID.setStyle("-fx-border-color: red");
            label_reservation.setText("Wrong Data: ID contains letters");
            check++;
        }
        if (ID.getText().equals(""))
        {
            ID.setStyle("-fx-border-color: red");
            label_reservation.setText("Fill All Input");
            check++;
        }
        if (CarNumber.getText().equals(""))
        {
            CarNumber.setStyle("-fx-border-color: red");
            label_reservation.setText("Fill All Input");
            check++;
        }
        if (Email.getText().equals(""))
        {
            Email.setStyle("-fx-border-color: red");
            label_reservation.setText("Fill All Input");
            check++;
        }
        if (Password.getText().equals(""))
        {
            Password.setStyle("-fx-border-color: red");
            label_reservation.setText("Fill All Input");
            check++;
        }
        if (EnterYear.getText().equals(""))
        {
            EnterYear.setStyle("-fx-border-color: red");
            label_reservation.setText("Fill All Input");
            check++;
        }
        if (ExitYear.getText().equals(""))
        {
            ExitYear.setStyle("-fx-border-color: red");
            label_reservation.setText("Fill All Input");
            check++;
        }

        if (check>0)
        {
            label_reservation.setVisible(true);
        }
        if (check==0 &&GuestType.getText().equals("PreOrder")) {

            boolean OnSite = false;

            Message m = new Message("#Reservation", ENTER_hoursmenu.getText(), ENTER_daymenu.getText(), Enter_Monthmenu.getText(), EnterYear.getText(), Exit_hoursmenu.getText(), Exit_daymenu.getText(), Exit_Monthmenu.getText(), ExitYear.getText(), ParkLotIdMenu.getText(), ID.getText(), Password.getText(),OnSite,CarNumber.getText(),Email.getText());

            try {
                SimpleClient.getClient().sendToServer(m);
            } catch (IOException e) {
                e.printStackTrace();
            }
            label_reservation.setVisible(false);
        }
/*
        if (GuestType.getText().equals("OnSite")) {
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
        }*/
    }

    @FXML
    void SubscriberOrder(ActionEvent event) {
/*
        if (SubscriberType1.getText().equals("Regular") && SubscriberType2.getText().equals("PreOrder")) {
            boolean OnSite = false;
            Message m = new Message("#RegularSubscriberOrder", EnterHour.getText(), EnterDay.getText(), EnterMonth.getText(), EnterYear.getText(), ExitHour.getText(), ExitDay.getText(), ExitMonth.getText(), ExitYear.getText(), ParkingLotId.getText(), ID.getText(), Password.getText(), SubscriptionNumber.getText(),OnSite,CarNumber.getText(),Email.getText());
            try {
                SimpleClient.getClient().sendToServer(m);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (SubscriberType1.getText().equals("Regular") && SubscriberType2.getText().equals("OnSite")) {
            LocalTime Date1 = LocalTime.now();
            int Hour = Date1.getHour();

            LocalDate Date2 = LocalDate.now();
            int Day = Date2.getDayOfMonth();

            LocalDate Date3 = LocalDate.now();
            int Month = Date3.getMonthValue();

            LocalDate Date4 = LocalDate.now();
            int Year = Date4.getYear();

            boolean OnSite = true;

            Message m = new Message("#RegularSubscriberOrder", Hour, Day, Month, Year, ExitHour.getText(), ExitDay.getText(), ExitMonth.getText(), ExitYear.getText(), ParkingLotId.getText(), ID.getText(), Password.getText(), SubscriptionNumber.getText(),OnSite,CarNumber.getText(),Email.getText());

            try {
                SimpleClient.getClient().sendToServer(m);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (SubscriberType1.getText().equals("Full") && SubscriberType2.getText().equals("PreOrder")) {
            boolean OnSite = false;

            Message m = new Message("#FullSubscriberOrder", EnterHour.getText(), EnterDay.getText(), EnterMonth.getText(), EnterYear.getText(), ExitHour.getText(), ExitDay.getText(), ExitMonth.getText(), ExitYear.getText(), ParkingLotId.getText(), ID.getText(), Password.getText(), SubscriptionNumber.getText(),OnSite,CarNumber.getText(),Email.getText());

            try {
                SimpleClient.getClient().sendToServer(m);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (SubscriberType1.getText().equals("Full") && SubscriberType2.getText().equals("OnSite")) {
            LocalTime Date1 = LocalTime.now();
            int Hour = Date1.getHour();

            LocalDate Date2 = LocalDate.now();
            int Day = Date2.getDayOfMonth();

            LocalDate Date3 = LocalDate.now();
            int Month = Date3.getMonthValue();

            LocalDate Date4 = LocalDate.now();
            int Year = Date4.getYear();

            boolean OnSite = true;

            Message m = new Message("#FullSubscriberOrder", Hour, Day, Month, Year, ExitHour.getText(), ExitDay.getText(), ExitMonth.getText(), ExitYear.getText(), ParkingLotId.getText(), ID.getText(), Password.getText(), SubscriptionNumber.getText(),OnSite,CarNumber.getText(),Email.getText());

            try {
                SimpleClient.getClient().sendToServer(m);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
    }

//		*************************************************************************************
//		*************************************************************************************



//		*************************************************************************************
//      1 CheckReserveStatusEvent
//		*************************************************************************************

    @Subscribe
    public void setLabelshow1(CheckReservationStatus Response) throws IOException {
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

//		*************************************************************************************
//      2
//		*************************************************************************************

    @Subscribe
    public void setLabelshow2(CancelReserveEvent Response) throws IOException {
        Platform.runLater(() ->
                {
                    System.out.println("11");
                    if (Response.getWarning().getMessage().equals("CancelReservation")) {
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

            //App.setRoot("ReserveParking");

    }

//		*************************************************************************************
//      3
//		*************************************************************************************

    @Subscribe
    public void setLabelshow3(ReserveParkingEvent Response) throws IOException {
        Platform.runLater(() ->
                {
                    System.out.println("22");
                    if (Response.getWarning().getMessage().equals("Reservation")) {
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


            //App.setRoot("ReserveParking");

    }

//		*************************************************************************************
//      4
//		*************************************************************************************


//		*************************************************************************************
//      5
//		*************************************************************************************


//		*************************************************************************************
//      6
//		*************************************************************************************


    @FXML
    private MenuButton ENTER_hoursmenu=new MenuButton();
    @FXML
    private MenuButton Exit_hoursmenu=new MenuButton();
    @FXML
    private MenuButton ENTER_daymenu=new MenuButton();
    @FXML
    private MenuButton Exit_daymenu=new MenuButton();
    @FXML
    private MenuButton Exit_Monthmenu=new MenuButton();
    @FXML
    private MenuButton Enter_Monthmenu=new MenuButton();
    @FXML
    private MenuButton ParkLotIdMenu;
    @FXML
    void initialize() {
        for (int i=0;i<25;i++)
        {
            String tmp="";
            tmp+=i;
            MenuItem x = new MenuItem(tmp);
            ENTER_hoursmenu.getItems().addAll(x);
            String tmp2=tmp;
            x.setOnAction(actionEvent -> ENTER_hoursmenu.setText(tmp2));
        }
        for (int i=0;i<25;i++)
        {
            String tmp="";
            tmp+=i;
            MenuItem x = new MenuItem(tmp);
            Exit_hoursmenu.getItems().addAll(x);
            String tmp2=tmp;
            x.setOnAction(actionEvent -> Exit_hoursmenu.setText(tmp2));
        }
        for (int i=1;i<29;i++)
        {
            String tmp="";
            tmp+=i;
            MenuItem x = new MenuItem(tmp);
            ENTER_daymenu.getItems().addAll(x);
            String tmp2=tmp;
            x.setOnAction(actionEvent -> ENTER_daymenu.setText(tmp2));
        }
        for (int i=1;i<29;i++)
        {
            String tmp="";
            tmp+=i;
            MenuItem x = new MenuItem(tmp);
            Exit_daymenu.getItems().addAll(x);
            String tmp2=tmp;
            x.setOnAction(actionEvent -> Exit_daymenu.setText(tmp2));
        }
        for (int i=1;i<13;i++)
        {
            String tmp="";
            tmp+=i;
            MenuItem x = new MenuItem(tmp);
            Enter_Monthmenu.getItems().addAll(x);
            String tmp2=tmp;
            x.setOnAction(actionEvent -> Enter_Monthmenu.setText(tmp2));
        }
        for (int i=1;i<13;i++)
        {
            String tmp="";
            tmp+=i;
            MenuItem x = new MenuItem(tmp);
            Exit_Monthmenu.getItems().addAll(x);
            String tmp2=tmp;
            x.setOnAction(actionEvent -> Exit_Monthmenu.setText(tmp2));
        }
        label_cancel_reservation.setVisible(false);
        EventBus.getDefault().register(this);
    }
    @FXML
    void setMenuPriceParkID1(ActionEvent event) {
        ParkLotIdMenu.setText("1");

    }

    @FXML
    void setMenuPriceParkID2(ActionEvent event) {
        ParkLotIdMenu.setText("2");

    }

    @FXML
    void setMenuPriceParkID3(ActionEvent event) {
        ParkLotIdMenu.setText("3");

    }

    @FXML
    void setMenuPriceParkID4(ActionEvent event) {
        ParkLotIdMenu.setText("4");

    }

    @FXML
    void setMenuPriceParkID5(ActionEvent event) {
        ParkLotIdMenu.setText("5");

    }

    @FXML
    void setMenuPriceParkID6(ActionEvent event) {
        ParkLotIdMenu.setText("6");

    }

    @FXML
    void setMenuPriceParkID7(ActionEvent event) {
        ParkLotIdMenu.setText("7");
    }
}

//		*************************************************************************************
//		*************************************************************************************
