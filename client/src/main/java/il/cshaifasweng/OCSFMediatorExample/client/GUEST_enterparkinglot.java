package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

public class GUEST_enterparkinglot {

    @FXML
    private TextField CarNumber;

    @FXML
    private TextField Email;

    @FXML
    private TextField ExitDay;

    @FXML
    private TextField ExitHour;

    @FXML
    private TextField ExitMonth;

    @FXML
    private TextField ExitYear;

    @FXML
    private Button GuestOrder;

    @FXML
    private TextField ID;

    @FXML
    private TextField ParkingLotId;

    @FXML
    private TextField Password;
    @FXML
    void AlreadyhaveAnOrder(MouseEvent event) throws IOException {

        App.setRoot("GUEST_enterparkingwithorder");
    }

    @FXML
    void OrderandEnter(ActionEvent event) {

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, Integer.parseInt(ExitYear.getText()));
        cal.set(Calendar.MONTH, Integer.parseInt(ExitMonth.getText())-1);
        cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(ExitDay.getText()));
        cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(ExitHour.getText()));
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        java.util.Date date1 = cal.getTime();
        System.out.println(date1);
        System.out.println(new Date());
        System.out.println(date1.after(new Date()));
        if (date1.after(new Date())) {
                LocalTime Date1 = LocalTime.now();
                int Hour = Date1.getHour();

                LocalDate Date2 = LocalDate.now();
                int Day = Date2.getDayOfMonth();

                LocalDate Date3 = LocalDate.now();
                int Month = Date3.getMonthValue();

                LocalDate Date4 = LocalDate.now();
                int Year = Date4.getYear();
            System.out.println("date1.after(new Date()");
                boolean OnSite = true;
                Message m = new Message("#GuestOnSiteOrder_enter", Hour, Day, Month, Year, ExitHour.getText(), ExitDay.getText(), ExitMonth.getText(), ExitYear.getText(), ParkLotIdMenu.getText(), ID.getText(), Password.getText(), ID.getText(), OnSite, CarNumber.getText(), Email.getText());

                try {
                    SimpleClient.getClient().sendToServer(m);
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "Please enter a valid exit time in the future"
            );
            alert.show();
        }
    }


    @FXML
    void back(ActionEvent event) throws IOException {
        App.setRoot("GUEST_create_order");
    }
    @FXML
    void initialize() {
        EventBus.getDefault().register(this);
    }

    @Subscribe
    public void setLabelshow4(GuestOnSiteOrderEvent Response) throws IOException {
        Platform.runLater(() ->
                {
                    if (Response.getWarning().getMessage().equals("GuestOnSiteOrder_enter")) {
                        String Response1 = Response.getWarning().getObject1().toString();
                        if (Response1.equals("The Parking Lot is Full, Please Choose Another Parking Lot")) {
                            Alert alert = new Alert(Alert.AlertType.WARNING,
                                    String.format("%s", Response1)
                            );
                            alert.show();
                        }
                        if (Response1.equals("Your Order Confirmed")) {
                            try {
                                SimpleClient.getClient().sendToServer(new Message("#enterparkinglot", Integer.parseInt(ParkLotIdMenu.getText()) ,
                                        Integer.parseInt(CarNumber.getText()) , Integer.parseInt(ID.getText())));
                            } catch (IOException e){
                                e.printStackTrace();
                            }
                        }
                    }
                }
        );
    }
    @FXML
    void setMenuPriceParkID1(ActionEvent event) {
        ParkLotIdMenu.setText("1");

    }

    @FXML
    void setMenuPriceParkID2(ActionEvent event) {
        ParkLotIdMenu.setText("2");

    }

    @FXML
    void setMenuPriceParkID3(ActionEvent event) {
        ParkLotIdMenu.setText("3");

    }

    @FXML
    void setMenuPriceParkID4(ActionEvent event) {
        ParkLotIdMenu.setText("4");

    }

    @FXML
    void setMenuPriceParkID5(ActionEvent event) {
        ParkLotIdMenu.setText("5");

    }

    @FXML
    void setMenuPriceParkID6(ActionEvent event) {
        ParkLotIdMenu.setText("6");

    }
    @FXML
    private MenuButton ParkLotIdMenu;
    @FXML
    void setMenuPriceParkID7(ActionEvent event) {
        ParkLotIdMenu.setText("7");
    }
}