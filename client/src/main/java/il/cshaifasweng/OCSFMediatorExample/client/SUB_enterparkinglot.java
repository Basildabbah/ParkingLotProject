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
import java.time.LocalDate;
import java.time.LocalTime;

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
        if (type.equals("Regular")) {
            LocalTime Date1 = LocalTime.now();
            int Hour = Date1.getHour();

            LocalDate Date2 = LocalDate.now();
            int Day = Date2.getDayOfMonth();

            LocalDate Date3 = LocalDate.now();
            int Month = Date3.getMonthValue();

            LocalDate Date4 = LocalDate.now();
            int Year = Date4.getYear();

            boolean OnSite = true;

            Message m = new Message("#RegularSubscriberOrder_enter", Hour, Day, Month, Year, ExitHour.getText(), ExitDay.getText(), ExitMonth.getText(), ExitYear.getText(), ParkingLotId.getText(), ID.getText(), Password.getText(), idd, OnSite, CarNumber.getText(), Email.getText());

            try {
                SimpleClient.getClient().sendToServer(m);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(type.equals("Full")) {
        LocalTime Date1 = LocalTime.now();
        int Hour = Date1.getHour();

        LocalDate Date2 = LocalDate.now();
        int Day = Date2.getDayOfMonth();

        LocalDate Date3 = LocalDate.now();
        int Month = Date3.getMonthValue();

        LocalDate Date4 = LocalDate.now();
        int Year = Date4.getYear();

        boolean OnSite = true;

        Message m = new Message("#FullSubscriberOrder_enter", Hour, Day, Month, Year, ExitHour.getText(), ExitDay.getText(), ExitMonth.getText(), ExitYear.getText(), ParkingLotId.getText(), ID.getText(), Password.getText(), idd, OnSite, CarNumber.getText(), Email.getText());

        try {
            SimpleClient.getClient().sendToServer(m);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


    @FXML
    void back(ActionEvent event) throws IOException {
    App.setRoot("subscribeboundry");
    }
    @FXML
    void initialize() {
        ID.setText(idd);
        Password.setText(pass);
        Password.setDisable(true);
        ID.setDisable(true);
    }
    @Subscribe
    public void setLabelshow5(RegularSubscriberOrder_enterEvent Response) throws IOException {
        Platform.runLater(() ->
                {
                    if (Response.getWarning().getMessage().equals("RegularSubscriberOrder")) {
                        String Response1 = Response.getWarning().getObject1().toString();
                        if (Response1.equals("Your Order Confirmed")) {
                            try {
                                SimpleClient.getClient().sendToServer(new Message("#enterparkinglot", Integer.parseInt(ID.getText()) ,
                                        Integer.parseInt(CarNumber.getText())));
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
                    if (Response.getWarning().getMessage().equals("FullSubscriberOrder")) {
                        String Response1 = Response.getWarning().getObject1().toString();
                        if (Response1.equals("Your Order Confirmed")) {
                            try {
                                SimpleClient.getClient().sendToServer(new Message("#enterparkinglot", Integer.parseInt(ID.getText()) ,
                                        Integer.parseInt(CarNumber.getText())));
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
