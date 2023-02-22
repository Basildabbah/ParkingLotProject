package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Complaint;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class displayreportofchain_COMPLAIN {
    @FXML
    private MenuItem ComplaintsReport;

    @FXML
    private MenuItem InvalidSpotsReport;

    @FXML
    private MenuItem OrdersReport;

    @FXML
    private Button ShowReportfun;
    @FXML
    private DatePicker fromdate;

    @FXML
    private MenuItem park1;

    @FXML
    private MenuItem park2;

    @FXML
    private MenuItem park3;

    @FXML
    private MenuItem park4;

    @FXML
    private MenuItem park5;

    @FXML
    private MenuItem park6;

    @FXML
    private MenuItem park7;

    @FXML
    private SplitMenuButton parking_id;

    @FXML
    private Label parknumber;

    @FXML
    private Label parknumber1;

    @FXML
    private Label parknumber11;

    @FXML
    private Label parknumber111;
    @FXML
    private ScrollPane  ancher;

    @FXML
    private Button back;
    @FXML
    private DatePicker todate;

    @FXML
    private SplitMenuButton type;
    @FXML
    private TableColumn<Complaint, Integer> c1;

    @FXML
    private TableColumn<Complaint, Integer> c2;

    @FXML
    private TableColumn<Complaint, String> c3;

    @FXML
    private TableColumn<Complaint, String> c4;

    @FXML
    private TableColumn<Complaint, String> c5;
    
    @FXML
    private TableView<Complaint> table2;

    @FXML
    void initialize() {
        EventBus.getDefault().register(this);
    }
    @FXML
    void back(ActionEvent event) throws IOException {
    App.setRoot("displayreportofchain");
    }
    @Subscribe
     public void onEvent(displayreportofchain_COMPLAIN_EVENT e) {
        Platform.runLater(() ->
        {
            System.out.println("5555");
        c1.setCellValueFactory(new PropertyValueFactory<Complaint, Integer>("id"));
        c2.setCellValueFactory(new PropertyValueFactory<Complaint, Integer>("parkingLotId"));
        c3.setCellValueFactory(new PropertyValueFactory<Complaint, String>("complaintMessage"));
        c4.setCellValueFactory(new PropertyValueFactory<Complaint, String>("status"));
        c5.setCellValueFactory(new PropertyValueFactory<Complaint, String>("response"));
        ObservableList<Complaint> list = FXCollections.observableList((ArrayList<Complaint>) e.getWarning().getObject1());
        table2.setItems(list);

        parknumber.setText(e.getWarning().getObject5().toString());
        parknumber1.setText(e.getWarning().getObject2().toString());
        parknumber11.setText(e.getWarning().getObject3().toString());
        parknumber111.setText(e.getWarning().getObject4().toString());
        ancher.setVisible(true);
        }
        );
    }

    @FXML
    void ShowReportfun(ActionEvent event) {
        Calendar cal1 = new GregorianCalendar(fromdate.getValue().getYear(), fromdate.getValue().getMonthValue(), fromdate.getValue().getDayOfMonth()); // note that months are 0-based, i.e. January is 0
        Calendar cal2  = new GregorianCalendar(todate.getValue().getYear(), todate.getValue().getMonthValue(), todate.getValue().getDayOfMonth());
        long diffMillis = Math.abs(cal2.getTimeInMillis() - cal1.getTimeInMillis());
        long diffDays = TimeUnit.MILLISECONDS.toDays(diffMillis);

/*            Message m = new Message("#ComplaintsReport", parking_id.getText(),fromdate.getValue().getYear(),fromdate.getValue().getMonthValue()
                   , fromdate.getValue().getDayOfMonth(),todate.getValue().getYear(),todate.getValue().getMonthValue(),todate.getValue().getDayOfMonth()  );*/
        Message m = new Message("#displayreportofchain_COMPLAIN", parking_id.getText(),fromdate.getValue().getYear(),fromdate.getValue().getMonthValue()
                , fromdate.getValue().getDayOfMonth(),todate.getValue().getYear(),todate.getValue().getMonthValue(),todate.getValue().getDayOfMonth(),diffDays );
        try {
            SimpleClient.getClient().sendToServer(m);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
}
