package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class controltest {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addocarro;

    @FXML
    private TextField carnumberrr;

    @FXML
    private TextField parkkkkkiddd;

    @FXML
    private Button removecarr;

    @FXML
    private Button showparkkkk;

    @FXML
    private TextField subiiidddd;

    @FXML
    private TextField subtypeeee;

    @FXML
    void plsaddocarro(ActionEvent event) {
        try {
            SimpleClient.getClient().sendToServer(new Message("#addcar", Integer.parseInt(carnumberrr.getText()),
                    Integer.parseInt(subiiidddd.getText()) , subtypeeee.getText()));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    void plshowparkkkk(ActionEvent event) {
        try {
            SimpleClient.getClient().sendToServer(new Message("#showparkinglotpicture", Integer.parseInt(parkkkkkiddd.getText())
                    ,Integer.parseInt(subiiidddd.getText())));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    void plsremovecarr(ActionEvent event) {

        try {
            SimpleClient.getClient().sendToServer(new Message("#removecar", Integer.parseInt(carnumberrr.getText())
                    , Integer.parseInt(subiiidddd.getText()), subtypeeee.getText()));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        assert addocarro != null : "fx:id=\"addocarro\" was not injected: check your FXML file 'controltesto.fxml'.";
        assert carnumberrr != null : "fx:id=\"carnumberrr\" was not injected: check your FXML file 'controltesto.fxml'.";
        assert parkkkkkiddd != null : "fx:id=\"parkkkkkiddd\" was not injected: check your FXML file 'controltesto.fxml'.";
        assert removecarr != null : "fx:id=\"removecarr\" was not injected: check your FXML file 'controltesto.fxml'.";
        assert showparkkkk != null : "fx:id=\"showparkkkk\" was not injected: check your FXML file 'controltesto.fxml'.";
        assert subiiidddd != null : "fx:id=\"subiiidddd\" was not injected: check your FXML file 'controltesto.fxml'.";
        assert subtypeeee != null : "fx:id=\"subtypeeee\" was not injected: check your FXML file 'controltesto.fxml'.";

    }

}
