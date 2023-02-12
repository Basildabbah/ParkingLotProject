package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Complaint;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;

public class GUEST_check_order {

    @FXML
    private SplitMenuButton parking_id;
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
    private Label complainnum;
    @FXML
    private TextArea textC;

    @FXML
    private TextField id;

    @FXML
    private TextField parkingid;
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
    void CheckOrderStatus(ActionEvent event) {
        Message m = new Message("#CheckOrderStatus", ID_Check.getText(), Password_Check.getText(), NumberOfOrder_Check.getText());
        try {
            SimpleClient.getClient().sendToServer(m);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void GUEST_enter_park(ActionEvent event) throws IOException {
        App.setRoot("GUEST_enterparkinglot");
    }
    @FXML
    void GUEST_exit_park(ActionEvent event) throws IOException {
        App.setRoot("GUEST_exitparkinglot");
    }

    @FXML
    void GUEST_cancel_order(ActionEvent event) throws IOException {
        App.setRoot("GUEST_cancel_order");
    }
    @FXML
    void GUEST_check_complain(ActionEvent event) throws IOException {
        App.setRoot("GUEST_check_complain");
    }
    @FXML
    void GUEST_check_order(ActionEvent event)throws IOException {
        App.setRoot("GUEST_check_order");
    }
    @FXML
    void GUEST_create_order(ActionEvent event) throws IOException {
        App.setRoot("GUEST_create_order");
    }
    @FXML
    void GUEST_send_complain(ActionEvent event) throws IOException {
        App.setRoot("GUEST_send_complain");
    }
    @FXML
    void FAQ(ActionEvent event) throws IOException {
        App.setRoot("faq0");
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
    void homebutfun(ActionEvent event)  throws IOException {
        App.setRoot("home");
    }
    @FXML
    void loginfun(ActionEvent event) throws IOException {
        App.setRoot("loginadmin");
    }


    @FXML
    void initialize() {
        EventBus.getDefault().register(this);
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
}
