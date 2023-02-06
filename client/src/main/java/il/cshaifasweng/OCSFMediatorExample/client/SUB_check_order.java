package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;

public class SUB_check_order {
    public static String idd;
    static String type;
    public static String pass;
    @FXML
    private Button CheckOrderStatus;

    @FXML
    private TextField Date11;

    @FXML
    private TextField ID_Check;

    @FXML
    private TextField NumberOfOrder_Check;

    @FXML
    private TextField Password_Check;

    @FXML
    private AnchorPane anchorpane_CHECK_ORDER;

    @FXML
    private Button back;



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
    void back(ActionEvent event) throws IOException {
        App.setRoot("subscribeboundry");
    }
    @FXML
    void initialize() {
        ID_Check.setText(idd);
        Password_Check.setText(pass);
        Password_Check.setDisable(true);
        ID_Check.setDisable(true);
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
