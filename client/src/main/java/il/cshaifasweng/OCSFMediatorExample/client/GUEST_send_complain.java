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

public class GUEST_send_complain {
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
    private Label complainnum;
    @FXML
    private TextArea textC;

    @FXML
    private TextField id;

    @FXML
    private TextField parkingid;
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
    private TextField password_Complain;

    @FXML
    void GUEST_enter_park(ActionEvent event) {

    }

    @FXML
    void GUEST_exit_park(ActionEvent event) {

    }
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
    void park6(ActionEvent event) {
        parking_id.setText("6");

    }

    @FXML
    void park7(ActionEvent event) {
        parking_id.setText("7");
    }

    @FXML
    void send(ActionEvent event) {
        if (id.getText().isEmpty()  || password_Complain.getText().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.WARNING,
                    String.format("you have to fill all the fileds")
            );
            alert.show();
        }
        try {
            SimpleClient.getClient().sendToServer(new Message("newCompliain^"+textC.getText()+"^" +id.getText(),parking_id.getText(),1,password_Complain.getText()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void initialize() {
        EventBus.getDefault().register(this);
        complainnum.setVisible(false);
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








    @Subscribe
    public void onEvent(NewComplaintEvent e){
        Platform.runLater(()-> {
            if (Integer.parseInt(e.getWarning().getObject1().toString()) == -1) {
                Alert alert = new Alert(Alert.AlertType.ERROR,
                        String.format("the password not exist")
                );
                alert.show();
            } else{
                complainnum.setText(complainnum.getText() + " " + e.getWarning().getObject1().toString());
            complainnum.setVisible(true);


            Alert alert = new Alert(Alert.AlertType.INFORMATION,
                    String.format("Your Complain Number is: %s\nTimestamp: %s\n",
                            e.getWarning().getObject1(),
                            e.getWarning().getTime().toString())
            );
            alert.show();
            try {
                App.setRoot("home");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        });

    }
}
