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
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.util.ArrayList;

public class GUEST_check_complain {
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



    @FXML
    void GUEST_enter_park(ActionEvent event) {

    }

    @FXML
    void GUEST_exit_park(ActionEvent event) {

    }

    @FXML
    void initialize() {
        EventBus.getDefault().register(this);
    }
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
            System.out.println("a");
            table.setVisible(true);
            idc.setCellValueFactory(new PropertyValueFactory<Complaint, Integer>("id"));
            statusc.setCellValueFactory(new PropertyValueFactory<Complaint, String>("complaintMessage"));
            complaintc.setCellValueFactory(new PropertyValueFactory<Complaint, String>("status"));
            ObservableList<Complaint> list = FXCollections.observableList((ArrayList<Complaint>) e.getWarning().getObject1());
            table.setItems(list);
        });
    }

}
