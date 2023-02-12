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

public class GUEST_cancel_order {
    @FXML
    private SplitMenuButton parking_id;
    @FXML
    private TextField NumberOfOrder_Check;
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
    void CancelOrder(ActionEvent event)throws IOException  {

        Message m = new Message("#CancelOrder", ID_Cancel.getText(), Password_Cancel.getText(), NumberOfOrder_Cancel.getText());
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
    void initialize() {
        EventBus.getDefault().register(this);
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
