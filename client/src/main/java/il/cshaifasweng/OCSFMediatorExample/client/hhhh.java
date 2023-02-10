package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;

import java.io.IOException;

public class hhhh {

    @FXML
    private MenuButton menu;
    @FXML
    void initialize() {
        try {

            SimpleClient.getClient().sendToServer(new Message("#needmycars",149));
        } catch (IOException e){
            e.printStackTrace();
        }
        System.out.println("010101+        ");
          /*    MenuItem x=new MenuItem("aaaaaa");
        menu.getItems().addAll(x);
        x.setOnAction(actionEvent -> menu.setText("aaaaaa"));*/
    }
}
