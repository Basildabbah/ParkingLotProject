package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Complaint;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.util.ArrayList;

public class StatusComplain_SUBSCRIBER {
    public static String idd;
    static String type;

    @FXML
    private Label labelid;
    @FXML
    private TableColumn<Complaint, String> complaintc;

    @FXML
    private TextField idS;

    @FXML
    private TableColumn<Complaint, Integer> idc;

    @FXML
    private TableColumn<Complaint, String> statusc;

    @FXML
    private TableView<Complaint> table;

    @FXML
    void back(ActionEvent event) throws IOException {
    App.setRoot("subscribeboundry");
    }


    @FXML
    void initialize() {
        idS.setDisable(true);
        idS.setText(idd);
        EventBus.getDefault().register(this);
        labelid.setText("User ID:"+idd);

    }

    @FXML
    void bring(ActionEvent event) {
        try {
            Message s1=new Message("bring ^"+idS.getText(),"wajd","wajd2","wajd3","wajd4");
            SimpleClient.getClient().sendToServer(s1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Subscribe
    public void showComplaints(StatusComplaintEvent e){
        Platform.runLater(()->{
            table.setVisible(true);
            idc.setCellValueFactory(new PropertyValueFactory<Complaint, Integer>("id"));
            statusc.setCellValueFactory(new PropertyValueFactory<Complaint, String>("complaintMessage"));
            complaintc.setCellValueFactory(new PropertyValueFactory<Complaint, String>("status"));
            ObservableList<Complaint> list = FXCollections.observableList((ArrayList<Complaint>) e.getWarning().getObject1());
            table.setItems(list);
        });
    }
}
