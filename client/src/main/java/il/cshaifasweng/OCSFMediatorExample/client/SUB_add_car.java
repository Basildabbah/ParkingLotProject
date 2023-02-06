package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

public class SUB_add_car {
    public static String idd;
    static String type;
    public static String pass;
    @FXML
    private AnchorPane addcaranchorpane;

    @FXML
    private Button back;

    @FXML
    private TextField carnumbertextbox;

    @FXML
    private TextField idtextbox;

    @FXML
    private Button submit_add_car;
    @FXML
    void back(ActionEvent event) throws IOException {
        App.setRoot("subscribeboundry");
    }
    @FXML
    void submit_add_carfun(ActionEvent event) {
        try {
            SimpleClient.getClient().sendToServer(new Message("#addcar", Integer.parseInt(carnumbertextbox.getText()),
                    Integer.parseInt(idd)));
        } catch (IOException e){
            e.printStackTrace();
        }

    }
    @FXML
    void initialize() {
        idtextbox.setText(idd);
        idtextbox.setDisable(true);
    }

}
