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

public class SUB_cancel_order {
    public static String idd;
    static String type;
    public static String pass;
    @FXML
    private Button CancelOrder;

    @FXML
    private TextField Date1;

    @FXML
    private TextField ID_Cancel;

    @FXML
    private TextField NumberOfOrder_Cancel;

    @FXML
    private TextField Password_Cancel;

    @FXML
    private AnchorPane anchonpane_CANCEL_ORDER;

    @FXML
    private Button back;

    @FXML
    void CancelOrder(ActionEvent event) {
        Message m = new Message("#CancelOrder", ID_Cancel.getText(), Password_Cancel.getText(), NumberOfOrder_Cancel.getText());
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
        ID_Cancel.setText(idd);
        Password_Cancel.setText(pass);
        Password_Cancel.setDisable(true);
        ID_Cancel.setDisable(true);
        EventBus.getDefault().register(this);
    }
    @Subscribe
    public void setLabelshow2(CancelOrderEvent Response) throws IOException {
        Platform.runLater(() ->
                {
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
