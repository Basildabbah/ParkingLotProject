package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class removecer_subs {
    public static String idd;
    @FXML
    private AnchorPane addcaranchorpane;

    @FXML
    private Button back;

    @FXML
    private TextField carnumbertextbox;

    @FXML
    private TextField id;

    @FXML
    private TextField id_Car;

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
            System.out.println(idd);
            SimpleClient.getClient().sendToServer(new Message("#removecar", Integer.parseInt(id_Car.getText()),
                    Integer.parseInt(idd)));
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    @FXML
    void initialize() {
        id.setText(idd);
        id.setDisable(true);
    }
}
