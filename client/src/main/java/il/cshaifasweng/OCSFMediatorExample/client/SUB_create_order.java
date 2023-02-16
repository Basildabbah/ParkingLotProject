package il.cshaifasweng.OCSFMediatorExample.client;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.hibernate.type.SpecialOneToOneType;

import java.net.SocketTimeoutException;
import java.util.Date;
import java.io.IOException;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SUB_create_order {
    public static String idd;
    static String type;
    public static String pass;
    public static String firstname;
    static String lastname;
    public static String email;
    public static List<String> cars;
    @FXML
    private MenuItem setPriceParkID1;

    @FXML
    private MenuItem setPriceParkID2;
    @FXML
    private MenuButton ParkLotIdMenu;
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
    private TextField CarNumber;
    @FXML
    private MenuButton menu;
    @FXML
    private TextField Date;

    @FXML
    private TextField Email;

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
    private TextField EnterMin;

    @FXML
    private TextField ExitMin;
    @FXML
    private TextField ID;

    @FXML
    private CheckBox OnSite;

    @FXML
    private TextField ParkingLotId;

    @FXML
    private TextField Password;

    @FXML
    private Button SubscriberOrder;

    @FXML
    private AnchorPane ancherpane_ORDER;

    @FXML
    private Button back;

    @FXML
    private Label enterlabel;

    @FXML
    private CheckBox preorder;

    @FXML
    void SubscriberOrder(ActionEvent event) {

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, Integer.parseInt(EnterYear.getText()));
        cal.set(Calendar.MONTH, Integer.parseInt(ExitMonth.getText())-1);
        cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(EnterDay.getText()));
        cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(EnterHour.getText()));
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        java.util.Date date = cal.getTime();

        cal.set(Calendar.YEAR, Integer.parseInt(ExitYear.getText()));
        cal.set(Calendar.MONTH, Integer.parseInt(ExitMonth.getText())-1);
        cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(ExitDay.getText()));
        cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(ExitHour.getText()));
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        java.util.Date date1 = cal.getTime();


        System.out.println(date);
        System.out.println(date1);
        System.out.println(new Date());
        System.out.println(date.before(date1));
        System.out.println(date.after(new Date()));

        if (date.before(date1)) {
            if (date.after(new Date())) {

                if (type.equals("Regular") && preorder.isSelected() == true) {
                    boolean OnSite = false;
                    Message m = new Message("#RegularSubscriberOrder", EnterHour.getText(), EnterDay.getText(), EnterMonth.getText(), EnterYear.getText(), ExitHour.getText(), ExitDay.getText(), ExitMonth.getText(), ExitYear.getText(), ParkLotIdMenu.getText(), ID.getText(), Password.getText(), idd, OnSite, menu.getText(),email);
                    try {
                        SimpleClient.getClient().sendToServer(m);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if (type.equals("Regular") && OnSite.isSelected() == true) {
                    LocalTime Date1 = LocalTime.now();
                    int Hour = Date1.getHour();

                    LocalDate Date2 = LocalDate.now();
                    int Day = Date2.getDayOfMonth();

                    LocalDate Date3 = LocalDate.now();
                    int Month = Date3.getMonthValue();

                    LocalDate Date4 = LocalDate.now();
                    int Year = Date4.getYear();

                    boolean OnSite = true;
                    String type="OnSite";
                    Message m = new Message("#RegularSubscriberOrder", Hour, Day, Month, Year, ExitHour.getText(), ExitDay.getText(), ExitMonth.getText(), ExitYear.getText(), ParkingLotId.getText(), ID.getText(), Password.getText(), idd, OnSite, CarNumber.getText(), email,type);

                    try {
                        SimpleClient.getClient().sendToServer(m);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if (type.equals("Full") && preorder.isSelected() == true) {
                    boolean OnSite = false;
                    System.out.println(Subscribeboundry.email);
                    Message m = new Message("#FullSubscriberOrder", EnterHour.getText(), EnterDay.getText(), EnterMonth.getText(), EnterYear.getText(), ExitHour.getText(), ExitDay.getText(), ExitMonth.getText(), ExitYear.getText(), ParkLotIdMenu.getText(), ID.getText(), Password.getText(), idd, OnSite, menu.getText(), Subscribeboundry.email);

                    try {
                        SimpleClient.getClient().sendToServer(m);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if (type.equals("Full") && OnSite.isSelected() == true) {
                    LocalTime Date1 = LocalTime.now();
                    int Hour = Date1.getHour();

                    LocalDate Date2 = LocalDate.now();
                    int Day = Date2.getDayOfMonth();

                    LocalDate Date3 = LocalDate.now();
                    int Month = Date3.getMonthValue();

                    LocalDate Date4 = LocalDate.now();
                    int Year = Date4.getYear();

                    boolean OnSite = true;
                    String type="OnSite";
                    Message m = new Message("#FullSubscriberOrder", Hour, Day, Month, Year, ExitHour.getText(), ExitDay.getText(), ExitMonth.getText(), ExitYear.getText(), ParkingLotId.getText(), ID.getText(), Password.getText(), idd, OnSite, CarNumber.getText(), email,type);

                    try {
                        SimpleClient.getClient().sendToServer(m);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(idd + " hhh" + pass + " hhh" + type);
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "Please enter a valid entry and exit time"
            );
            alert.show();
        }

    }
    @FXML
    void back(ActionEvent event) throws IOException {
    App.setRoot("subscribeboundry");
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
    void initialize() {
        preorder.setSelected(true);
        ID.setText(idd);
        Password.setText(pass);
        Password.setDisable(true);
        ID.setDisable(true);
        System.out.println("s");
        System.out.println(Subscribeboundry.cars);
        if (Subscribeboundry.cars!=null) {
            for (String y : Subscribeboundry.cars) {
                MenuItem x = new MenuItem(y);
                menu.getItems().addAll(x);
                x.setOnAction(actionEvent -> menu.setText(y));
            }
        }
       /* MenuItem x=new MenuItem("aaaaaa");
        menu.getItems().addAll(x);
        x.setOnAction(actionEvent -> menu.setText("aaaaaa"));*/
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
    void setMenuPriceParkID7(ActionEvent event) {
    ParkLotIdMenu.setText("7");
    }
  /*  @Subscribe
    public void setLabelshow5(RegularSubscriberOrderEvent Response) throws IOException {
        Platform.runLater(() ->
                {
                    if (Response.getWarning().getMessage().equals("RegularSubscriberOrder")) {
                        String Response1 = Response.getWarning().getObject1().toString();
                        if (Response1.equals("Your Order Confirmed")) {
                            String Response2 = Response.getWarning().getObject2().toString();
                            String Response3 = Response.getWarning().getObject3().toString();

                            Alert alert = new Alert(Alert.AlertType.WARNING,
                                    String.format("%s \n Your Order Number is: %s \n The Paid Hours is: %s"
                                            , Response1, Response2, Response3)
                            );
                            alert.show();
                        }

                        if (Response1.equals("The Subscription Number is Wrong")) {
                            Alert alert = new Alert(Alert.AlertType.WARNING,
                                    String.format("%s", Response1)
                            );
                            alert.show();
                        }

                        if (Response1.equals("The ParkingLot is Full")) {
                            Alert alert = new Alert(Alert.AlertType.WARNING,
                                    String.format("%s", Response1)
                            );
                            alert.show();
                        }

                        if (Response1.equals("You Only Allowed To Enter Once a Day")) {
                            Alert alert = new Alert(Alert.AlertType.WARNING,
                                    String.format("%s", Response1)
                            );
                            alert.show();
                        }
                        if (Response1.equals("You Don't Have Enough Hours")) {
                            Alert alert = new Alert(Alert.AlertType.WARNING,
                                    String.format("%s", Response1)
                            );
                            alert.show();
                        }
                    }
                }
        );
    }
    @Subscribe
    public void setLabelshow6(FullSubscriberOrderEvent Response) throws IOException {
        Platform.runLater(() ->
                {
                    if (Response.getWarning().getMessage().equals("FullSubscriberOrder")) {
                        String Response1 = Response.getWarning().getObject1().toString();
                        if (Response1.equals("Your Order Confirmed")) {
                            String Response2 = Response.getWarning().getObject2().toString();
                            String Response3 = Response.getWarning().getObject3().toString();

                            Alert alert = new Alert(Alert.AlertType.WARNING,
                                    String.format("%s \n Your Order Number is: %s \n The Paid Hours is: %s"
                                            , Response1, Response2, Response3)
                            );
                            alert.show();
                        }

                        if (Response1.equals("The Subscription Number is Wrong")) {
                            Alert alert = new Alert(Alert.AlertType.WARNING,
                                    String.format("%s", Response1)
                            );
                            alert.show();
                        }

                        if (Response1.equals("The ParkingLot is Full")) {
                            Alert alert = new Alert(Alert.AlertType.WARNING,
                                    String.format("%s", Response1)
                            );
                            alert.show();
                        }
                        if (Response1.equals("You Don't Have Enough Hours")) {
                            Alert alert = new Alert(Alert.AlertType.WARNING,
                                    String.format("%s", Response1)
                            );
                            alert.show();
                        }

                    }
                }
        );
    }*/
}
