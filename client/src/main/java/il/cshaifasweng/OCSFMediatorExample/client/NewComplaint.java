package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;

public class NewComplaint {

    @FXML
    private TextField id;

    @FXML
    private Label newid;

    @FXML
    private Button send;

    @FXML
    private TextField textC;

    @FXML
    void send(ActionEvent event) {
        try {
            System.out.println("send new complaint");
            SimpleClient.getClient().sendToServer(new Message("newCompliain^"+textC.getText()+"^" +id.getText()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void initialize() {
        EventBus.getDefault().register(this);
    }
    @Subscribe
    public void onEvent(NewComplaintEvent e){
        Platform.runLater(()-> {
            newid.setText("your complaint id is: "+e.getWarning().getObject1().toString()+" remember it to see the answer");
        });
    }

}
