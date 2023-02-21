package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

public class SUB_enterparkinglot {
    public static String idd;
    static String type;
    public static String pass;
    @FXML
    private TextField CarNumber;

    @FXML
    private TextField Date;

    @FXML
    private TextField Email;

    @FXML
    private TextField ExitDay;

    @FXML
    private TextField ExitHour;

    @FXML
    private TextField ExitMin;
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
    private AnchorPane addcaranchorpane;

    @FXML
    private AnchorPane ancherpane_enter;

    @FXML
    private Button back;

    @FXML
    private TextField carnumbertextbox;

    @FXML
    private TextField idtextbox;

    @FXML
    private Button submit_add_car;

    @FXML
    void AlreadyhaveAnOrder(MouseEvent event) throws IOException {
        SUB_enterparkingwithorder.idd = idd;
        SUB_enterparkingwithorder.pass = pass;
        SUB_enterparkingwithorder.type = type;
        App.setRoot("SUB_enterparkingwithorder");


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
            if (type.equals("Regular")) {
                LocalTime Date1 = LocalTime.now();
                int Hour = Date1.getHour();

                LocalDate Date2 = LocalDate.now();
                int Day = Date2.getDayOfMonth();

                LocalDate Date3 = LocalDate.now();
                int Month = Date3.getMonthValue();

                LocalDate Date4 = LocalDate.now();
                int Year = Date4.getYear();

                LocalTime  Date5= LocalTime .now();
                int EnterMin = Date5.getMinute();
                boolean OnSite = true;

                Message m = new Message("#RegularSubscriberOrder_enter", Hour, Day, Month, Year, ExitHour.getText(), ExitDay.getText(), ExitMonth.getText(), ExitYear.getText(), ParkingLotId.getText(), ID.getText(), Password.getText(), idd, OnSite, CarNumber.getText(), Email.getText(),"",EnterMin,ExitMin.getText());

                try {
                    SimpleClient.getClient().sendToServer(m);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (type.equals("Full")) {
                LocalTime Date1 = LocalTime.now();
                int Hour = Date1.getHour();

                LocalDate Date2 = LocalDate.now();
                int Day = Date2.getDayOfMonth();

                LocalDate Date3 = LocalDate.now();
                int Month = Date3.getMonthValue();

                LocalDate Date4 = LocalDate.now();
                int Year = Date4.getYear();
                LocalTime  Date5= LocalTime .now();
                int EnterMin = Date5.getMinute();
                boolean OnSite = true;
                System.out.println(ParkingLotId.getText()+"    "+ CarNumber.getText()+"    "+ ID.getText());
                Message m = new Message("#FullSubscriberOrder_enter", Hour, Day, Month, Year, ExitHour.getText(), ExitDay.getText(), ExitMonth.getText(), ExitYear.getText(), ParkingLotId.getText(), ID.getText(), Password.getText(), idd, OnSite, CarNumber.getText(), Email.getText(),"",EnterMin,ExitMin.getText());

                try {
                    SimpleClient.getClient().sendToServer(m);
                } catch (IOException e) {
                    e.printStackTrace();
                }
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
        App.setRoot("subscribeboundry");
    }
    @FXML
    void initialize() {
        EventBus.getDefault().register(this);
        ID.setText(idd);
        Password.setText(pass);
        Password.setDisable(true);
        ID.setDisable(true);
    }

    @Subscribe
    public void setLabelshow5(RegularSubscriberOrder_enterEvent Response) throws IOException {
        Platform.runLater(() ->
                {
                    if (Response.getWarning().getMessage().equals("RegularSubscriberOrder_enter")) {
                        String Response1 = Response.getWarning().getObject1().toString();
                        if (Response1.equals("Your Order Confirmed")) {
                            System.out.println("welwelwlel");
                            try {

                                SimpleClient.getClient().sendToServer(new Message("#enterparkinglot", Integer.parseInt(ParkingLotId.getText()) ,
                                        Integer.parseInt(CarNumber.getText()) , Integer.parseInt(ID.getText())));
                            } catch (IOException e){
                                e.printStackTrace();
                            }

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
    public void setLabelshow6(FullSubscriberOrder_enterEvent Response) throws IOException {
        Platform.runLater(() ->
                {
                    if (Response.getWarning().getMessage().equals("FullSubscriberOrder_enter")) {
                        String Response1 = Response.getWarning().getObject1().toString();
                        if (Response1.equals("Your Order Confirmed")) {
                            System.out.println("lewlewlwel");
                            try {
                                SimpleClient.getClient().sendToServer(new Message("#enterparkinglot", Integer.parseInt(ParkingLotId.getText()) ,
                                        Integer.parseInt(CarNumber.getText()) , Integer.parseInt(ID.getText())));
                            } catch (IOException e){
                                e.printStackTrace();
                            }



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
    }
}