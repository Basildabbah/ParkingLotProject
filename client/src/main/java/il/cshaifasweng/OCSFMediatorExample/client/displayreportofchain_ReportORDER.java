package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Complaint;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Order;
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

public class displayreportofchain_ReportORDER {
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
    private TableColumn<Order, Integer> c1;

    @FXML
    private TableColumn<Order, String> c2;

    @FXML
    private TableColumn<Order, Integer> c3;

    @FXML
    private TableColumn<Order, Integer> c4;

    @FXML
    private TableColumn<Order, String> c5;
    
    @FXML
    private TableView<Order> table2;

    @FXML
    void initialize() {
        EventBus.getDefault().register(this);
    }
    @FXML
    void back(ActionEvent event) throws IOException {
    App.setRoot("displayreportofchain");
    }
    @Subscribe
    public void setLabelshow3(OrdersReportEvent Response) throws IOException {
        Platform.runLater(() ->
                {
                    c1.setCellValueFactory(new PropertyValueFactory<Order, Integer>("OrderId"));
                    c2.setCellValueFactory(new PropertyValueFactory<Order, String>("TypeOfOrder"));
                    c3.setCellValueFactory(new PropertyValueFactory<Order, Integer>("ParkingLotId"));
                    c4.setCellValueFactory(new PropertyValueFactory<Order, Integer>("PersonId"));

                    ObservableList<Order> list = FXCollections.observableList((ArrayList<Order>) Response.getWarning().getObject7());
                    table2.setItems(list);
                    System.out.println( Response.getWarning().getObject8());
                    parknumber1.setText((String) Response.getWarning().getObject8());
                    double x1= Double.parseDouble(parknumber1.getText());
                    double x2=Double.parseDouble(parknumber11.getText());
                    //System.out.println("x is "+x);
                    //System.out.println("xx is "+xx);
                    double x3=x1/x2;
                    x3=x3*100;
                    String x4="";
                    x4+=x3;
                    parknumber111.setText(x4);

                }
        );
    }


    @FXML
    void ShowReportfun(ActionEvent event) {
        Calendar cal1 = new GregorianCalendar(fromdate.getValue().getYear(), fromdate.getValue().getMonthValue(), fromdate.getValue().getDayOfMonth()); // note that months are 0-based, i.e. January is 0
        Calendar cal2  = new GregorianCalendar(todate.getValue().getYear(), todate.getValue().getMonthValue(), todate.getValue().getDayOfMonth());
        long diffMillis = Math.abs(cal2.getTimeInMillis() - cal1.getTimeInMillis());
        long diffDays = TimeUnit.MILLISECONDS.toDays(diffMillis);

            Message m = new Message("#OrdersReport", parking_id.getText(),fromdate.getValue().getYear(),fromdate.getValue().getMonthValue()
                    , fromdate.getValue().getDayOfMonth(),todate.getValue().getYear(),todate.getValue().getMonthValue(),todate.getValue().getDayOfMonth());
            try {
                SimpleClient.getClient().sendToServer(m);
            } catch (IOException e) {
                e.printStackTrace();
            }
            parknumber.setText(parking_id.getText());
            parknumber11.setText(String.valueOf(diffDays));

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
