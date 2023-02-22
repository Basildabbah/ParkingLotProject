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
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

public class displayreportofchain_invalidspot {


    @FXML
    private Button ShowReportfun;

    @FXML
    private Button back;

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
    private DatePicker todate;


    @FXML
    void initialize() {
        EventBus.getDefault().register(this);
    }
    @FXML
    void back(ActionEvent event) throws IOException {
    App.setRoot("displayreportofchain");
    }

    @FXML
    void ShowReportfun(ActionEvent event) {
        Calendar cal1 = new GregorianCalendar(fromdate.getValue().getYear(), fromdate.getValue().getMonthValue(), fromdate.getValue().getDayOfMonth()); // note that months are 0-based, i.e. January is 0
        Calendar cal2  = new GregorianCalendar(todate.getValue().getYear(), todate.getValue().getMonthValue(), todate.getValue().getDayOfMonth());
        long diffMillis = Math.abs(cal2.getTimeInMillis() - cal1.getTimeInMillis());
        long diffDays = TimeUnit.MILLISECONDS.toDays(diffMillis);

        Message m = new Message("#InvalidSpotsReport", parking_id.getText(),fromdate.getValue().getYear(),fromdate.getValue().getMonthValue()
                , fromdate.getValue().getDayOfMonth(),todate.getValue().getYear(),todate.getValue().getMonthValue(),todate.getValue().getDayOfMonth());
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
    @Subscribe
    public void setLabelshow1(InvalidSpotsReportEvent Response) throws IOException {
        Platform.runLater(() ->
                {

                    if (Response.getWarning().getMessage().equals("InvalidSpotsReport")) {
                        String Response1 = Response.getWarning().getObject1().toString();
                        String Response2 = Response.getWarning().getObject2().toString();

                        Alert alert = new Alert(Alert.AlertType.WARNING,
                                String.format("The Number of Invalid Spots in ParkingLot %s is: %s ", Response2, Response1));
                        alert.show();
                    }

                }
        );
    }
}
