package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Complaint;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.util.ArrayList;

public class StatusComplain_SUBSCRIBER {
    public static String idd;
    static String type;
    Timeline timeline=new Timeline();

    @FXML
    private Label labelid;
    @FXML
    private TableColumn<Complaint, String> complaintc;

    @FXML
    private TableColumn<Complaint,Integer> ParkingLotId1;

    @FXML
    private TableColumn<Complaint, String> response1;

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
        timeline.stop();
    App.setRoot("subscribeboundry");
    }


    @FXML
    void initialize() throws IOException {
        idS.setDisable(true);
        idS.setText(idd);
        EventBus.getDefault().register(this);
        labelid.setText("User ID:"+idd);
            timeline = new Timeline(
                new KeyFrame(Duration.seconds(1),
                        event1 -> {
                            try {
                                Message s1=new Message("bring ^"+idS.getText(),"wajd","wajd2","wajd3","wajd4");
                                SimpleClient.getClient().sendToServer(s1);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        })
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

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
            ParkingLotId1.setCellValueFactory(new PropertyValueFactory<Complaint, Integer>("parkingLotId"));
            statusc.setCellValueFactory(new PropertyValueFactory<Complaint, String>("complaintMessage"));
            complaintc.setCellValueFactory(new PropertyValueFactory<Complaint, String>("status"));
            response1.setCellValueFactory(new PropertyValueFactory<Complaint, String>("response"));
            ObservableList<Complaint> list = FXCollections.observableList((ArrayList<Complaint>) e.getWarning().getObject1());
            table.setItems(list);
        });
    }
}
