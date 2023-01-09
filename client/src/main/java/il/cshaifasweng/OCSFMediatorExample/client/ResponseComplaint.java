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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ResponseComplaint {

    private ScheduledExecutorService executor;
    @FXML
    private TableColumn<Complaint, Integer> c1;

    @FXML
    private TableColumn<Complaint, Integer> c2;

    @FXML
    private TableColumn<Complaint, String> c3;

    @FXML
    private TableColumn<Complaint, String> c4;

    @FXML
    private TableColumn<Complaint, String> c5;

    @FXML
    private TextField idSer;

    @FXML
    private TextField respText;

    @FXML
    private Button response;

    @FXML
    private TableView<Complaint> table2;

    @FXML
    void initialize() {
        EventBus.getDefault().register(this);
        try {
            SimpleClient.getClient().sendToServer(new Message("#bringall"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void response(ActionEvent event) {
        try {
            System.out.println("send response");
            System.out.println(respText.getText());
            SimpleClient.getClient().sendToServer(new Message("#response",Integer.parseInt(idSer.getText()),respText.getText()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Subscribe
     public void onEvent(ResponseComplaintEvent e) {
//        Platform.runLater(() -> {
            c1.setCellValueFactory(new PropertyValueFactory<Complaint, Integer>("id"));
            c2.setCellValueFactory(new PropertyValueFactory<Complaint, Integer>("parkingLotId"));
            c3.setCellValueFactory(new PropertyValueFactory<Complaint, String>("complaintMessage"));
            c4.setCellValueFactory(new PropertyValueFactory<Complaint, String>("status"));
            c5.setCellValueFactory(new PropertyValueFactory<Complaint, String>("response"));
            ObservableList<Complaint> list = FXCollections.observableList((ArrayList<Complaint>) e.getWarning().getObject1());
            table2.setItems(list);

//        });
//            executor = Executors.newSingleThreadScheduledExecutor();
//          executor.scheduleAtFixedRate(this::refreshTable, 0, 5, TimeUnit.SECONDS);
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(5),
                        event -> {
                            // refresh the table here
                            refreshTable();
                        })
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();


    }
    private void refreshTable() {
            try {
                System.out.println("refresh table");
                SimpleClient.getClient().sendToServer(new Message("#2bringall"));
            } catch (IOException e) {
                e.printStackTrace();
            }
//            table2.refresh();
//            System.out.println("h");
    }
    @Subscribe
    public void onEvent(RefreshComplaintsEvent e){
        Platform.runLater(()-> {
            c1.setCellValueFactory(new PropertyValueFactory<Complaint, Integer>("id"));
            c2.setCellValueFactory(new PropertyValueFactory<Complaint, Integer>("parkingLotId"));
            c3.setCellValueFactory(new PropertyValueFactory<Complaint, String>("complaintMessage"));
            c4.setCellValueFactory(new PropertyValueFactory<Complaint, String>("status"));
            c5.setCellValueFactory(new PropertyValueFactory<Complaint, String>("response"));
            ObservableList<Complaint> list = FXCollections.observableList((ArrayList<Complaint>) e.getWarning().getObject1());
            table2.setItems(list);
        });
    }

}
