package il.cshaifasweng.OCSFMediatorExample.client;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Prices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

import static il.cshaifasweng.OCSFMediatorExample.client.ReserveParking.setRet_;

public class CustomerServiceBoundary {
    public static String name;
    public static String idd;
    public static String numofcomplain;
    @FXML
    private Button HandleComplains;

    @FXML
    private Button ReserveParking;

    @FXML
    private Button logout;
    @FXML
    private Label userid;
    @FXML
    private Label numofcomplain_label;

    @FXML
    private AnchorPane numofcomplain_textbox;

    @FXML
    void HandleComplainsfun(ActionEvent event) throws IOException {
        handlecomplain.idd=idd;
        App.setRoot("handlecomplain");
    }


    @FXML
    void ReserveParkingfun(ActionEvent event) throws IOException {
        setRet_(0);
        App.setRoot("ReserveParking");
    }

    @FXML
    void logoutfun(ActionEvent event) throws IOException {
        Message m=new Message("#logoutcusservices",idd);
        try {
            SimpleClient.getClient().sendToServer(m);
        } catch (IOException e) {
            e.printStackTrace();
        }
        App.setRoot("home");
    }
    @FXML
    void initialize() {
        userid.setText(userid.getText()+idd);
        if (Integer.parseInt(numofcomplain)>0)
        {
            numofcomplain_label.setText("There are "+ numofcomplain +" reported instances of \n non-response  to complaints.");
        }
        else
        {
            numofcomplain_label.setText("There are no reported instances of  \n non-response to complaints.");
        }
    }
}
