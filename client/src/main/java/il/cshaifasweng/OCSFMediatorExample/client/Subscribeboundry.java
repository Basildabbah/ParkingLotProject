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
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Subscribeboundry {
    public static String idd;
    static String type;
    public static String pass;
    public static String firstname;
    static String lastname;
    public static String email;
    @FXML
    private Label firstlabel;

    @FXML
    private Label lastlabel;
    @FXML
    private Label emaillabel;
    @FXML
    private Label typelabel;

    @FXML
    private Label useridlabel;

    @FXML
    private Label numofcomplain_label;

    @FXML
    private Button CancelOrder;

    @FXML
    private TextField CarNumber;

    @FXML
    private Button CheckOrderStatus;

    @FXML
    private TextField Date;

    @FXML
    private TextField Date1;

    @FXML
    private TextField Date11;

    @FXML
    private TextField Email;

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
    private Button SubscriberOrder;

    @FXML
    private TextField ID;

    @FXML
    private TextField ID_Cancel;

    @FXML
    private TextField ID_Check;

    @FXML
    private TextField NumberOfOrder_Cancel;

    @FXML
    private TextField NumberOfOrder_Check;

    @FXML
    private CheckBox OnSite;

    @FXML
    private TextField ParkingLotId;

    @FXML
    private TextField Password;

    @FXML
    private TextField Password_Cancel;

    @FXML
    private TextField Password_Check;

    @FXML
    private Button RenewSubscription;

    @FXML
    private Button addcar;

    @FXML
    private AnchorPane addcaranchorpane;

    @FXML
    private Button addreview;

    @FXML
    private AnchorPane ancherpane_ORDER;

    @FXML
    private AnchorPane anchonpane_CANCEL_ORDER;

    @FXML
    private AnchorPane anchorpane_CHECK_ORDER;

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
    private Label enterlabel;

    @FXML
    private Button enterpark;

    @FXML
    private Button exitpark;

    @FXML
    private TextField idtextbox;

    @FXML
    private Label labelid;

    @FXML
    private Label labelid1;

    @FXML
    private Button logout;

    @FXML
    private CheckBox preorder;

    @FXML
    private Button removecar;

    @FXML
    private Button sendcomplain;

    @FXML
    private Button submit_add_car;



    @FXML
    void RenewSubscriptionfun(ActionEvent event) {
        Alert x = new Alert(Alert.AlertType.CONFIRMATION,
                String.format("Are You Sure?")

        );
        Optional<ButtonType> result = x.showAndWait();
        if (result.get() == ButtonType.OK){
            try {
                SimpleClient.getClient().sendToServer(new Message("#renewsub", Integer.parseInt(idd)));
            } catch (IOException e){
                e.printStackTrace();
            }

        }
       // x.show();
    }
    @FXML
    void addreviewfun(ActionEvent event) throws IOException {
        review.idd=idd;
        App.setRoot("review");
    }

    @FXML
    void addcarfun(ActionEvent event) throws IOException {
       /* addcaranchorpane.setVisible(true);
        idtextbox.setText(idd);
        idtextbox.setDisable(true);*/
        addcar_subs.idd=idd;
        SUB_add_car.idd=idd;
        SUB_add_car.pass=pass;
        SUB_add_car.type=type;
        App.setRoot("SUB_add_car");
    }

    @FXML
    void cancelcomplainfun(ActionEvent event) {

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
    void cancelorderfun(ActionEvent event) throws IOException {
       /* anchonpane_CANCEL_ORDER.setVisible(true);
        anchorpane_CHECK_ORDER.setVisible(false);
        ancherpane_ORDER.setVisible(false);
        ID_Cancel.setText(idd);
        ID_Cancel.setDisable(true);
        Password_Cancel.setText(pass);
        Password_Cancel.setDisable(true);*/
        SUB_cancel_order.idd=idd;
        SUB_cancel_order.pass=pass;
        SUB_cancel_order.type=type;
        App.setRoot("SUB_cancel_order");

    }
    @FXML
    void SubscriberOrder(ActionEvent event) {

        if (type.equals("Regular") && preorder.isSelected()==true) {
            boolean OnSite = false;
            Message m = new Message("#RegularSubscriberOrder", EnterHour.getText(), EnterDay.getText(), EnterMonth.getText(), EnterYear.getText(), ExitHour.getText(), ExitDay.getText(), ExitMonth.getText(), ExitYear.getText(), ParkingLotId.getText(), ID.getText(), Password.getText(), idd,OnSite,CarNumber.getText(),Email.getText());
            try {
                SimpleClient.getClient().sendToServer(m);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (type.equals("Regular") && OnSite.isSelected()==true) {
            LocalTime Date1 = LocalTime.now();
            int Hour = Date1.getHour();

            LocalDate Date2 = LocalDate.now();
            int Day = Date2.getDayOfMonth();

            LocalDate Date3 = LocalDate.now();
            int Month = Date3.getMonthValue();

            LocalDate Date4 = LocalDate.now();
            int Year = Date4.getYear();

            boolean OnSite = true;

            Message m = new Message("#RegularSubscriberOrder", Hour, Day, Month, Year, ExitHour.getText(), ExitDay.getText(), ExitMonth.getText(), ExitYear.getText(), ParkingLotId.getText(), ID.getText(), Password.getText(),idd,OnSite,CarNumber.getText(),Email.getText());

            try {
                SimpleClient.getClient().sendToServer(m);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (type.equals("Full") && preorder.isSelected()==true) {
            boolean OnSite = false;

            Message m = new Message("#FullSubscriberOrder", EnterHour.getText(), EnterDay.getText(), EnterMonth.getText(), EnterYear.getText(), ExitHour.getText(), ExitDay.getText(), ExitMonth.getText(), ExitYear.getText(), ParkingLotId.getText(), ID.getText(), Password.getText(),idd,OnSite,CarNumber.getText(),Email.getText());

            try {
                SimpleClient.getClient().sendToServer(m);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (type.equals("Full") && OnSite.isSelected()==true) {
            LocalTime Date1 = LocalTime.now();
            int Hour = Date1.getHour();

            LocalDate Date2 = LocalDate.now();
            int Day = Date2.getDayOfMonth();

            LocalDate Date3 = LocalDate.now();
            int Month = Date3.getMonthValue();

            LocalDate Date4 = LocalDate.now();
            int Year = Date4.getYear();

            boolean OnSite = true;

            Message m = new Message("#FullSubscriberOrder", Hour, Day, Month, Year, ExitHour.getText(), ExitDay.getText(), ExitMonth.getText(), ExitYear.getText(), ParkingLotId.getText(), ID.getText(), Password.getText(), idd,OnSite,CarNumber.getText(),Email.getText());

            try {
                SimpleClient.getClient().sendToServer(m);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void checkcomplainfun(ActionEvent event) throws IOException {
        StatusComplain_SUBSCRIBER.idd=idd;
        StatusComplain_SUBSCRIBER.type=type;
        App.setRoot("StatusComplain_SUBSCRIBER");
        //App.setRoot("StatusComplaint");
    }

    @FXML
    void checkorderfun(ActionEvent event) throws IOException {
     /*   anchorpane_CHECK_ORDER.setVisible(true);
        anchonpane_CANCEL_ORDER.setVisible(false);
        ancherpane_ORDER.setVisible(false);
        ID_Check.setText(idd);
        ID_Check.setDisable(true);
        Password_Check.setText(pass);
        Password_Check.setDisable(true);*/
        SUB_check_order.idd=idd;
        SUB_check_order.type=type;
        SUB_check_order.pass=pass;
        App.setRoot("SUB_check_order");
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
    void createorderfun(ActionEvent event) throws IOException {
        /*anchonpane_CANCEL_ORDER.setVisible(false);
        anchorpane_CHECK_ORDER.setVisible(false);
        ancherpane_ORDER.setVisible(true);
        ID.setText(idd);
        ID.setDisable(true );
        Password.setText(pass);
        Password.setDisable(true);*/
        SUB_create_order.idd=idd;
        SUB_create_order.type=type;
        SUB_create_order.pass=pass;
        try {
            System.out.println("type is "+type);
            SimpleClient.getClient().sendToServer(new Message("#needmycars",idd,type));
        } catch (IOException e){
            e.printStackTrace();
        }
    App.setRoot("SUB_create_order");

    }

    @FXML
    void enterpark(ActionEvent event) throws IOException {
        SUB_enterparkinglot.idd=idd;
        SUB_enterparkinglot.pass=pass;
        SUB_enterparkinglot.type=type;
    App.setRoot("SUB_enterparkinglot");
    }

    @FXML
    void exitparkfun(ActionEvent event) throws IOException {
        SUB_exitparkinglot.idd=idd;
        SUB_exitparkinglot.pass=pass;
        SUB_exitparkinglot.type=type;
        App.setRoot("SUB_exitparkinglot");

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
    void removecarfun(ActionEvent event) throws IOException {
        removecer_subs.idd=idd;

    App.setRoot("removecer_subs");

    }

    @FXML
    void sendcomplainfun(ActionEvent event) throws IOException {
        NewComplaint1.idd=idd;
        App.setRoot("NewComplaint1");
    }
    @FXML
    void submit_add_carfun(ActionEvent event) {
        try {
            SimpleClient.getClient().sendToServer(new Message("#addcar", Integer.parseInt(carnumbertextbox.getText()),
                    Integer.parseInt(idd)));
        } catch (IOException e){
            e.printStackTrace();
        }

    }
    @FXML
    void CancelOrder(ActionEvent event) {
        Message m = new Message("#CancelOrder", ID_Cancel.getText(), Password_Cancel.getText(), NumberOfOrder_Cancel.getText());
        try {
            SimpleClient.getClient().sendToServer(m);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void initialize() {
        addcar_subs.idd=idd;
        useridlabel.setText(idd);
        typelabel.setText(type);
        SUB_create_order.email=email;
        emaillabel.setText(email);
        firstlabel.setText(firstname);
        lastlabel.setText(lastname);
        EventBus.getDefault().register(this);
    }
    @Subscribe
    public void setLabelshow2(CancelOrderEvent Response) throws IOException {
        Platform.runLater(() ->
                {
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
    public void setLabelshow5(RegularSubscriberOrderEvent Response) throws IOException {
        Platform.runLater(() ->
                {
                    if (Response.getWarning().getMessage().equals("RegularSubscriberOrder")) {
                        String Response1 = Response.getWarning().getObject1().toString();
                        if (Response1.equals("Your Order Confirmed")) {
                            String Response2 = Response.getWarning().getObject2().toString();
                            String Response3 = Response.getWarning().getObject3().toString();

                            Alert alert = new Alert(Alert.AlertType.WARNING,
                                    String.format("%s \n Your Order Number is: %s \n The Paid Hours is: %s"
                                            , Response1, Response2, Response3)
                            );
                            alert.show();

                        }

                        if (Response1.equals("The Subscription Number is Wrong")) {
                            Alert alert = new Alert(Alert.AlertType.WARNING,
                                    String.format("%s", Response1)
                            );
                            alert.show();
                        }

                        if (Response1.equals("The ParkingLot is Full")) {
                            Alert alert = new Alert(Alert.AlertType.WARNING,
                                    String.format("%s", Response1)
                            );
                            alert.show();
                        }

                        if (Response1.equals("You Only Allowed To Enter Once a Day")) {
                            Alert alert = new Alert(Alert.AlertType.WARNING,
                                    String.format("%s", Response1)
                            );
                            alert.show();
                        }
                        if (Response1.equals("You Don't Have Enough Hours")) {
                            Alert alert = new Alert(Alert.AlertType.WARNING,
                                    String.format("%s", Response1)
                            );
                            alert.show();
                        }
                    }
                }
        );
    }
    @Subscribe
    public void setLabelshow6(FullSubscriberOrderEvent Response) throws IOException {
        Platform.runLater(() ->
                {
                    if (Response.getWarning().getMessage().equals("FullSubscriberOrder")) {
                        String Response1 = Response.getWarning().getObject1().toString();
                        if (Response1.equals("Your Order Confirmed")) {
                            String Response2 = Response.getWarning().getObject2().toString();
                            String Response3 = Response.getWarning().getObject3().toString();

                            Alert alert = new Alert(Alert.AlertType.WARNING,
                                    String.format("%s \n Your Order Number is: %s \n The Paid Hours is: %s"
                                            , Response1, Response2, Response3)
                            );
                            alert.show();
                        }

                        if (Response1.equals("The Subscription Number is Wrong")) {
                            Alert alert = new Alert(Alert.AlertType.WARNING,
                                    String.format("%s", Response1)
                            );
                            alert.show();
                        }

                        if (Response1.equals("The ParkingLot is Full")) {
                            Alert alert = new Alert(Alert.AlertType.WARNING,
                                    String.format("%s", Response1)
                            );
                            alert.show();
                        }
                        if (Response1.equals("You Don't Have Enough Hours")) {
                            Alert alert = new Alert(Alert.AlertType.WARNING,
                                    String.format("%s", Response1)
                            );
                            alert.show();
                        }

                    }
                }
        );
    }
    @Subscribe
    public void setLabelshow5(needmycarsEVENT Response) throws IOException {

                    System.out.println("aaaa");
                    List<String> x= (ArrayList) Response.getWarning().getObject1();
                    cars=new ArrayList<>();
                    cars.clear();
                    for (String y:x) {
                       cars.add(y);
                      //  System.out.println(y);
                    }

    }
    public static List<String> cars=null;
}
