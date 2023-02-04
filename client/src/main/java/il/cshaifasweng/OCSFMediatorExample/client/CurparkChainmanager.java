package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class CurparkChainmanager {
    @FXML
    private TextField textbox;

    @FXML
    void back(ActionEvent event) throws IOException {
    App.setRoot("ChainManagerBoundary");
    }

    @FXML
    void show(ActionEvent event) {
        System.out.println("aaaaaaaaaaaaaaaaaaaaaa");
     /*   try {
            SimpleClient.getClient().sendToServer(new Message("#showparkinglotpicture", Integer.parseInt(textbox.getText())));
        } catch (IOException e){
            e.printStackTrace();
        }*/

    }

}
