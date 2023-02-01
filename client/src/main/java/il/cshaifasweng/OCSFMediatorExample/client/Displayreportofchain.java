package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;

public class Displayreportofchain {

    @FXML
    private MenuItem ComplaintsReport;

    @FXML
    private MenuItem InvalidSpotsReport;

    @FXML
    private MenuItem OrdersReport;

    @FXML
    private Button ShowReportfun;

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
    private SplitMenuButton parking_id1;
    @FXML
    private SplitMenuButton type;

    @FXML
    void ShowReportfun(ActionEvent event) {
        if (type.getText().equals("InvalidSpotsReport")) {
            Message m = new Message("#InvalidSpotsReport", parking_id.getText());
            try {
                SimpleClient.getClient().sendToServer(m);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (type.getText().equals("ComplaintsReport")) {
            Message m = new Message("#ComplaintsReport", parking_id.getText());
           try {
                SimpleClient.getClient().sendToServer(m);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (type.getText().equals("OrdersReport")) {
            Message m = new Message("#OrdersReport", parking_id.getText());
           try {
                SimpleClient.getClient().sendToServer(m);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println(parking_id.getText());
        System.out.println(type.getText());
    }
    @FXML
    void ComplaintsReport(ActionEvent event) {
    type.setText("ComplaintsReport");
    }

    @FXML
    void InvalidSpotsReport(ActionEvent event) {
        type.setText("InvalidSpotsReport");

    }

    @FXML
    void OrdersReport(ActionEvent event) {
        type.setText("OrdersReport");

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
    @FXML
    void ShowStatisticsfun(ActionEvent event) {
        Message m = new Message("#Stats", parking_id1.getText());
        System.out.println(parking_id1.getText());
        try {
            SimpleClient.getClient().sendToServer(m);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        EventBus.getDefault().register(this);
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

    @Subscribe
    public void setLabelshow2(ComplaintsReportEvent Response) throws IOException {
        Platform.runLater(() ->
                {

                    if (Response.getWarning().getMessage().equals("ComplaintsReport")) {
                        String Response1 = Response.getWarning().getObject1().toString();
                        String Response2 = Response.getWarning().getObject2().toString();

                        Alert alert = new Alert(Alert.AlertType.WARNING,
                                String.format("The Number of Complaints in ParkingLot %s is: %s", Response2, Response1));
                        alert.show();
                    }

                }
        );
    }
    @Subscribe
    public void setLabelshow3(OrdersReportEvent Response) throws IOException {
        Platform.runLater(() ->
                {

                    if (Response.getWarning().getMessage().equals("OrdersReport")) {
                        /*String Response1 = Response.getWarning().getObject1().toString();*/
                        String Response2 = Response.getWarning().getObject2().toString();
                        String Response3 = Response.getWarning().getObject3().toString();
                        String Response4 = Response.getWarning().getObject4().toString();
                        String Response5 = Response.getWarning().getObject5().toString();
                        String Response6 = Response.getWarning().getObject6().toString();

/*
                        Alert alert = new Alert(Alert.AlertType.WARNING,
                                String.format("The Number of Orders in ParkingLot %s is: %s",Response2,Response1));
                        alert.show();*/
                        Alert alert = new Alert(Alert.AlertType.WARNING,
                                String.format("The Number of Orders in ParkingLot %s: \n" + "For FullSubscriber is: %s \n" + "For GuestPreOrder is: %s \n" + "For GuestOnSiteOrder is: %s \n" + "For RegularSubscriber is: %s", Response2, Response3, Response4, Response5, Response6));

                        alert.show();
                    }

                }
        );
    }
    @Subscribe
    public void setLabelshow4(StatsEvent Response) throws IOException {
        Platform.runLater(() ->
                {

                    if (Response.getWarning().getMessage().equals("Stats")) {
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
}
