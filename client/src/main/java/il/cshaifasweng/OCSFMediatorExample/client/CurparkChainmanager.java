package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

import java.io.IOException;

public class CurparkChainmanager {
    public static int plid;

    @FXML
    private MenuButton ParkLotIdMenu;

    @FXML
    private MenuItem setPriceParkID1;

    @FXML
    private MenuItem setPriceParkID2;

    @FXML
    private MenuItem setPriceParkID3;

    @FXML
    private MenuItem setPriceParkID4;

    @FXML
    private MenuItem setPriceParkID5;

    @FXML
    private MenuItem setPriceParkID6;

    @FXML
    private MenuItem setPriceParkID7;

    @FXML
    private TextField textbox;

    @FXML
    void back(ActionEvent event) throws IOException {
        App.setRoot("ChainManagerBoundary");
    }

    @FXML
    void setMenuPriceParkID1(ActionEvent event) {
        ParkLotIdMenu.setText("Park 1");
    }

    @FXML
    void setMenuPriceParkID2(ActionEvent event) {
        ParkLotIdMenu.setText("Park 2");

    }

    @FXML
    void setMenuPriceParkID3(ActionEvent event) {
        ParkLotIdMenu.setText("Park 3");

    }

    @FXML
    void setMenuPriceParkID4(ActionEvent event) {
        ParkLotIdMenu.setText("Park 4");

    }

    @FXML
    void setMenuPriceParkID5(ActionEvent event) {
        ParkLotIdMenu.setText("Park 5");

    }

    @FXML
    void setMenuPriceParkID6(ActionEvent event) {
        ParkLotIdMenu.setText("Park 6");
    }
    @FXML
    void setMenuPriceParkID7(ActionEvent event) {
        ParkLotIdMenu.setText("Park 7");
    }
    @FXML
    void show(ActionEvent event) {
        if (ParkLotIdMenu.getText().equals("Select Parking Lot ID"))
        {
            System.out.println("av");
        }
        else {
            char tmp = ParkLotIdMenu.getText().charAt(5);

            try {

                plid = Integer.parseInt(String.valueOf(tmp));
                System.out.println("zzzzzzzzzzz"+ plid);
                SimpleClient.getClient().sendToServer(new Message("#showparkinglotpicture", Integer.parseInt(String.valueOf(tmp))));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
