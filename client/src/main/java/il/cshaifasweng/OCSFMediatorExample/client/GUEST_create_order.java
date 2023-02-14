package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Complaint;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

public class GUEST_create_order {
    @FXML
    private SplitMenuButton parking_id;
    @FXML
    private TextField NumberOfOrder_Check;

    @FXML
    private TextField Password_Check;
    @FXML
    private TextField ID_Check;

    @FXML
    private TextField ID_Cancel;
    @FXML
    private TextField Password_Cancel;
    @FXML
    private TextField NumberOfOrder_Cancel;
    @FXML
    private TextField CarNumber;

    @FXML
    private TextField Date;

    @FXML
    private TextField EnterDay;

    @FXML
    private TextField EnterHour;

    @FXML
    private TextField EnterMonth;

    @FXML
    private TextField EnterYear;

    @FXML
    private TextField ExitDay;

    @FXML
    private TextField ExitHour;

    @FXML
    private TextField ExitMonth;

    @FXML
    private TextField ExitYear;


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
    private Button FAQ;

    @FXML
    private Button GuestOrder;

    @FXML
    private TextField GuestType;

    @FXML
    private TextField ID;

    @FXML
    private TextField ParkingLotId;

    @FXML
    private TextField Password;

    @FXML
    private Button Prices;

    @FXML
    private Button about;

    @FXML
    private TextField carnum;

    @FXML
    private TextField email;

    @FXML
    private Button homebut;

    @FXML
    private Button login;

    @FXML
    private Button loginadmin;
    @FXML
    private Label complainnum;
    @FXML
    private TextArea textC;

    @FXML
    private TextField id;

    @FXML
    private TextField parkingid;
    @FXML
    private TextField Email;
    @FXML
    private CheckBox preorder;
    @FXML
    private CheckBox OnSite;
    @FXML
    private Label enterlabel;


    @FXML
    void GuestOrder(ActionEvent event) throws IOException {

        if (preorder.isSelected() == true) {

            boolean OnSite = false;

            Message m = new Message("#GuestPreOrder", EnterHour.getText(), EnterDay.getText(), EnterMonth.getText(), EnterYear.getText(), ExitHour.getText(), ExitDay.getText(), ExitMonth.getText(), ExitYear.getText(), ParkLotIdMenu.getText(), ID.getText(), Password.getText(), OnSite, CarNumber.getText(), Email.getText());

            try {
                SimpleClient.getClient().sendToServer(m);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (OnSite.isSelected() == true) {

            LocalTime Date1 = LocalTime.now();
            int Hour = Date1.getHour();

            LocalDate Date2 = LocalDate.now();
            int Day = Date2.getDayOfMonth();

            LocalDate Date3 = LocalDate.now();
            int Month = Date3.getMonthValue();

            LocalDate Date4 = LocalDate.now();
            int Year = Date4.getYear();

            boolean OnSite = true;

            Message m = new Message("#GuestOnSiteOrder", Hour, Day, Month, Year, ExitHour.getText(), ExitDay.getText(), ExitMonth.getText(), ExitYear.getText(), ParkLotIdMenu.getText(), ID.getText(), Password.getText(), OnSite, CarNumber.getText(), Email.getText());

            try {
                SimpleClient.getClient().sendToServer(m);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @FXML
    void onsitecheck(ActionEvent event) {
        if (OnSite.isSelected() == true) {
            EnterDay.setVisible(false);
            EnterHour.setVisible(false);
            EnterMonth.setVisible(false);
            EnterYear.setVisible(false);
            enterlabel.setVisible(false);

        } else {
            EnterDay.setVisible(true);
            EnterHour.setVisible(true);
            EnterMonth.setVisible(true);
            EnterYear.setVisible(true);
            enterlabel.setVisible(true);
        }
    }

    @FXML
    void GUEST_enter_park(ActionEvent event) throws IOException {
        App.setRoot("GUEST_enterparkinglot");
    }
    @FXML
    void GUEST_exit_park(ActionEvent event) throws IOException {
        App.setRoot("GUEST_exitparkinglot");
    }

    @FXML
    void GUEST_cancel_order(ActionEvent event) throws IOException {
        App.setRoot("GUEST_cancel_order");
    }
    @FXML
    void GUEST_check_complain(ActionEvent event) throws IOException {
        App.setRoot("GUEST_check_complain");
    }
    @FXML
    void GUEST_check_order(ActionEvent event)throws IOException {
        App.setRoot("GUEST_check_order");
    }
    @FXML
    void GUEST_create_order(ActionEvent event) throws IOException {
        App.setRoot("GUEST_create_order");
    }
    @FXML
    void GUEST_send_complain(ActionEvent event) throws IOException {
        App.setRoot("GUEST_send_complain");
    }
    @FXML
    void FAQ(ActionEvent event) throws IOException {
        App.setRoot("faq0");
    }
    @FXML
    void Pricesfun(ActionEvent event) throws IOException {
        App.setRoot("prices");
    }
    @FXML
    void aboutus(ActionEvent event) throws IOException {
        App.setRoot("aboutus");
    }
    @FXML
    void homebutfun(ActionEvent event)  throws IOException {
        App.setRoot("home");
    }
    @FXML
    void loginfun(ActionEvent event) throws IOException {
        App.setRoot("loginadmin");
    }
    @FXML
    void initialize() {
        preorder.setSelected( true);
        EventBus.getDefault().register(this);
    }
    @Subscribe
    public void setLabelshow3(GuestPreOrderEvent Response) throws IOException {
        Platform.runLater(() ->
                {
                    if (Response.getWarning().getMessage().equals("GuestPreOrder")) {
                        String Response1 = Response.getWarning().getObject1().toString();
                        if (Response1.equals("The Parking Lot is Full, Please Choose Another Parking Lot")) {
                            Alert alert = new Alert(Alert.AlertType.WARNING,
                                    String.format("The Parking Lot is Full, Please Choose Another Parking Lot")
                            );
                            alert.show();
                        }
                        if (Response1.equals("Your Order Confirmed")) {
                            String Response2 = Response.getWarning().getObject2().toString();
                            String Response3 = Response.getWarning().getObject3().toString();

                            Alert alert = new Alert(Alert.AlertType.WARNING,
                                    String.format("Your Order Confirmed \n Your Order Number is: %s \n You Will Be Charged: %s "
                                            , Response2, Response3)
                            );
                            alert.show();
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
    /*@Subscribe
    public void setLabelshow4(GuestOnSiteOrderEvent Response) throws IOException {
        Platform.runLater(() ->
                {
                    if (Response.getWarning().getMessage().equals("GuestOnSiteOrder")) {
                        String Response1 = Response.getWarning().getObject1().toString();
                        if (Response1.equals("The Parking Lot is Full, Please Choose Another Parking Lot")) {
                            Alert alert = new Alert(Alert.AlertType.WARNING,
                                    String.format("%s", Response1)
                            );
                            alert.show();
                        }
                        if (Response1.equals("Your Order Confirmed")) {
                            String Response2 = Response.getWarning().getObject2().toString();
                            String Response3 = Response.getWarning().getObject3().toString();

                            Alert alert = new Alert(Alert.AlertType.WARNING,
                                    String.format("%s \n Your Order Number is: %s \n You Will Be Charged: %s "
                                            , Response1, Response2, Response3)
                            );
                            alert.show();
                        }
                    }
                }
        );
    }*/

}
