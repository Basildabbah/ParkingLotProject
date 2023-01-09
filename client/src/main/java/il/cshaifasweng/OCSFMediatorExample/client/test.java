package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Prices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.greenrobot.eventbus.Subscribe;

public class test {
    @FXML
    private Button but;

    @FXML
    private TableColumn<Prices, String> id;

    @FXML
    private TableView<Prices> table;
    @FXML
    void but(ActionEvent event) {
        System.out.println("1");

        try {
        SimpleClient.getClient().sendToServer("test");
    } catch (IOException e) {
        e.printStackTrace();
    }
    }
    @Subscribe
    public void setLabelshow(loginadminEvent c)throws IOException {
        Platform.runLater(()->{
          System.out.println("m");
            id.setCellValueFactory(new PropertyValueFactory<Prices, String>("id"));

            ObservableList<Prices> list = FXCollections.observableList((ArrayList<Prices>) c.getWarning().getList());

            table.setItems(list);
        });
    }
}

