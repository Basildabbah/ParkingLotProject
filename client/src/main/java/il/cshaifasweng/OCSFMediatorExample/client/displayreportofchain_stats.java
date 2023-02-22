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

public class displayreportofchain_stats {
    @FXML
    private Label parknumber;

    @FXML
    private Label parknumber1;

    @FXML
    private Label parknumber11;
    @FXML
    private Label parknumber22;
    @FXML
    private Label parknumber2;
    @FXML
    private Label parknumber222;
    @FXML
    private Label parknumber2222;
    @FXML
    private Label parknumber111;

    @FXML
    private Button ShowReportfun1;

    @FXML
    private Button back;
    @FXML
    private Label parknumber3;

    @FXML
    private Label parknumber33;

    @FXML
    private Label parknumber333;

    @FXML
    private Label parknumber3333;

    @FXML
    private DatePicker fromdate;

    @FXML
    private MenuItem park11;

    @FXML
    private MenuItem park22;

    @FXML
    private MenuItem park33;

    @FXML
    private MenuItem park44;

    @FXML
    private MenuItem park55;

    @FXML
    private MenuItem park66;

    @FXML
    private MenuItem park77;

    @FXML
    private SplitMenuButton parking_id1;

    @FXML
    private DatePicker todate;

    @FXML
    void ShowStatisticsfun(ActionEvent event) {


        Message m = new Message("#Stats", parking_id1.getText(),fromdate.getValue().getYear(),fromdate.getValue().getMonthValue()
                , fromdate.getValue().getDayOfMonth(),todate.getValue().getYear(),todate.getValue().getMonthValue(),todate.getValue().getDayOfMonth());
        try {
            SimpleClient.getClient().sendToServer(m);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void Back(ActionEvent event) throws IOException {
        App.setRoot("ChainManagerBoundary");
    }
    @FXML
    void park11(ActionEvent event) {
        parking_id1.setText("1");
    }

    @FXML
    void park22(ActionEvent event) {
        parking_id1.setText("2");

    }

    @FXML
    void park33(ActionEvent event) {
        parking_id1.setText("3");

    }

    @FXML
    void park44(ActionEvent event) {
        parking_id1.setText("4");

    }

    @FXML
    void park55(ActionEvent event) {
        parking_id1.setText("5");

    }

    @FXML
    void park66(ActionEvent event) {
        parking_id1.setText("6");

    }

    @FXML
    void park77(ActionEvent event) {
        parking_id1.setText("7");

    }
    @Subscribe
    public void setLabelshow4(StatsEvent Response) throws IOException {
        Platform.runLater(() ->
                {
                    Calendar cal1 = new GregorianCalendar(fromdate.getValue().getYear(), fromdate.getValue().getMonthValue(), fromdate.getValue().getDayOfMonth()); // note that months are 0-based, i.e. January is 0
                    Calendar cal2  = new GregorianCalendar(todate.getValue().getYear(), todate.getValue().getMonthValue(), todate.getValue().getDayOfMonth());
                    long diffMillis = Math.abs(cal2.getTimeInMillis() - cal1.getTimeInMillis());
                    double diffDays = TimeUnit.MILLISECONDS.toDays(diffMillis);
                    double x= Double.parseDouble((Response.getWarning().getObject3().toString()));
                    double xx= Double.parseDouble((Response.getWarning().getObject2().toString()));
                    double xxx= Double.parseDouble((Response.getWarning().getObject4().toString()));
                    double x1=x/diffDays;
                    double x2=xx/diffDays;
                    double x3=xxx/diffDays;
                    if (Response.getWarning().getMessage().equals("Stats")) {
                        parknumber.setText(parking_id1.getText());
                        parknumber1.setText(Response.getWarning().getObject3().toString());
                        parknumber11.setText(String.valueOf(diffDays));
                        parknumber111.setText(String.valueOf(x1*100));
                        parknumber2.setText(parknumber.getText());
                        parknumber222.setText(String.valueOf(diffDays));
                        parknumber2222.setText(String.valueOf(x2*100));
                        parknumber22.setText(Response.getWarning().getObject2().toString());
                        parknumber3.setText(parknumber.getText());
                        parknumber333.setText(String.valueOf(diffDays));
                        parknumber3333.setText(String.valueOf(x3*100));
                        parknumber33.setText(Response.getWarning().getObject4().toString());
                        String Response1 = Response.getWarning().getObject1().toString();
                        String Response2 = Response.getWarning().getObject2().toString();
                        String Response3 = Response.getWarning().getObject3().toString();
                        String Response4 = Response.getWarning().getObject4().toString();

                        Alert alert = new Alert(Alert.AlertType.WARNING,
                                String.format("The Statistics in ParkingLot %s are: \n"+"For activeOrders: %s \n" + "For CanceledOrders: %s \n" +"For LateArrivals: %s", Response1, Response2, Response3, Response4));
                        alert.show();
                    }

                }
        );
    }
    @FXML
    void initialize() {
        EventBus.getDefault().register(this);
    }

}
