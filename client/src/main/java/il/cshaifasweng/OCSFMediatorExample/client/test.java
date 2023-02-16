package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class test {
    /*********************************************************************/
    public static int p11,p12,p13,p21,p22,p23,p31,p32,p33,p41,p42,p43,p51,p52,p53
            ,p61,p62,p63,p71,p72,p73;


    @FXML
    private Label label_total_active_order;
    public static String String_total_active_order;
    @FXML
    private Label label_total_cancel_order;
    public static String String_total_cancel_order;
    @FXML
    private Label label_total_complains;
    public static String String_label_total_complains;
    @FXML
    private Label label_total_earn;
    public static String String_label_total_earn;
    @FXML
    private Label label_total_late_arrival;
    public static String String_total_late_arrival;
    /***********************************************************************/
    @FXML
    void back(ActionEvent event) throws IOException {
        App.setRoot("Displayreportofchain");
    }
    @FXML
    void Complains(ActionEvent event) throws IOException {
        try {
            SimpleClient.getClient().sendToServer(new Message("#total_complains_number"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void Orders(ActionEvent event) throws IOException {
        try {
            SimpleClient.getClient().sendToServer(new Message("#total_order_number"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void Cancelorder(ActionEvent event) throws IOException {
        try {
            SimpleClient.getClient().sendToServer(new Message("#total_cancelorder_number"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /***********************************************************************/



    final static String austria = "Austria";
    final static String brazil = "Brazil";
    final static String france = "France";
    final static String italy = "Italy";
    final static String usa = "USA";

    @FXML
    private BarChart<String, Number> bc;

    @FXML
    private Button but;

    @FXML
    private PieChart pie;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;


    @FXML
    public void initialize() {

        try {
            SimpleClient.getClient().sendToServer(new Message("#total_complains_number_init"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        bc.setVisible(false);

    /*  ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Park 1 ", 10),
                        new PieChart.Data("Park 2 ", 20),
                        new PieChart.Data("Park 3 ", 30),
                        new PieChart.Data("Park 4 ", 0),
                        new PieChart.Data("Park 5 ", 1),
                        new PieChart.Data("Park 6 ", 5),
                        new PieChart.Data("Park 7 ", 7));


        pieChartData.forEach(data ->
                data.nameProperty().bind(Bindings.concat(data.getName(), " Arrival: ", data.pieValueProperty())));
        pie.setVisible(true);
        pie.getData().addAll(pieChartData);
        bc.setVisible(false);
*/



        EventBus.getDefault().register(this);

    }
    @Subscribe
    public void setLabelshow1(total_complain_number_initEVENT Response) throws IOException {
        Platform.runLater(() ->
                {
                    pie.setVisible(false);
                    test.String_label_total_complains=Response.getWarning().getObject1().toString();
                    test.String_total_cancel_order=Response.getWarning().getObject3().toString();
                    test.String_total_active_order=Response.getWarning().getObject2().toString();
                    label_total_complains.setText(String_label_total_complains);
                    label_total_cancel_order.setText(String_total_cancel_order);
                    label_total_active_order.setText(String_total_active_order);
                    System.out.println("String_total_active_order");
                    System.out.println(String_label_total_complains);
                    System.out.println(String_total_cancel_order);
                    System.out.println(String_total_active_order);
                }
        );
    }
    @Subscribe
    public void setLabelshow1(total_order_numberEVENT Response) throws IOException {
        Platform.runLater(() ->
                {
                    pie.setVisible(false);
                    test.String_label_total_complains=Response.getWarning().getObject2().toString();
                    test.String_total_cancel_order=Response.getWarning().getObject3().toString();
                    test.String_total_active_order=Response.getWarning().getObject4().toString();
                    List<Integer> x= (ArrayList) Response.getWarning().getObject9();
                    test.p11=x.get(0);test.p12=x.get(1);test.p13=x.get(2);
                    x= (ArrayList) Response.getWarning().getObject10();
                    test.p21=x.get(0);test.p22=x.get(1);test.p23=x.get(2);
                    x= (ArrayList) Response.getWarning().getObject11();
                    test.p31=x.get(0);test.p32=x.get(1);test.p33=x.get(2);
                    x= (ArrayList) Response.getWarning().getObject12();
                    test.p41=x.get(0);test.p42=x.get(1);test.p43=x.get(2);
                    x= (ArrayList) Response.getWarning().getObject13();
                    test.p51=x.get(0);test.p52=x.get(1);test.p53=x.get(2);
                    x= (ArrayList) Response.getWarning().getObject14();
                    test.p61=x.get(0);test.p62=x.get(1);test.p63=x.get(2);
                    x= (ArrayList) Response.getWarning().getObject15();
                    test.p71=x.get(0);test.p72=x.get(1);test.p73=x.get(2);

                    xAxis.setCategories(FXCollections.<String>observableArrayList(Arrays.asList("Full","Guest","Regular")));
                    xAxis.setLabel("category");
                    yAxis.setLabel("score");
                    bc.setVisible(true);
                    bc.setTitle("Report Parking Lot");
                    XYChart.Series<String, Number> series1 = new XYChart.Series<>();
                    series1.setName("Park 1");
                    series1.getData().add(new XYChart.Data<>("Full", p11));
                    series1.getData().add(new XYChart.Data<>("Guest", p12));
                    series1.getData().add(new XYChart.Data<>("Regular", p13));

                    XYChart.Series<String, Number> series2 = new XYChart.Series<>();
                    series2.setName("Park 2");
                    series2.getData().add(new XYChart.Data<>("Full", p21));
                    series2.getData().add(new XYChart.Data<>("Guest", p22));
                    series2.getData().add(new XYChart.Data<>("Regular", p23));
                    XYChart.Series<String, Number> series3 = new XYChart.Series<>();
                    series3.setName("Park 3");
                    series3.getData().add(new XYChart.Data<>("Full", p31));
                    series3.getData().add(new XYChart.Data<>("Guest", p32));
                    series3.getData().add(new XYChart.Data<>("Regular", p33));
                    XYChart.Series<String, Number> series4 = new XYChart.Series<>();
                    series4.setName("Park 4");
                    series4.getData().add(new XYChart.Data<>("Full", p41));
                    series4.getData().add(new XYChart.Data<>("Guest", p42));
                    series4.getData().add(new XYChart.Data<>("Regular", p43));
                    XYChart.Series<String, Number> series5 = new XYChart.Series<>();
                    series5.setName("Park 5");
                    series5.getData().add(new XYChart.Data<>("Full", p51));
                    series5.getData().add(new XYChart.Data<>("Guest", p52));
                    series5.getData().add(new XYChart.Data<>("Regular", p53));
                    XYChart.Series<String, Number> series6 = new XYChart.Series<>();
                    series6.setName("Park 6");
                    series6.getData().add(new XYChart.Data<>("Full", p61));
                    series6.getData().add(new XYChart.Data<>("Guest", p62));
                    series6.getData().add(new XYChart.Data<>("Regular", p63));
                    XYChart.Series<String, Number> series7= new XYChart.Series<>();
                    series7.setName("Park 7");
                    series7.getData().add(new XYChart.Data<>("Full", p71));
                    series7.getData().add(new XYChart.Data<>("Guest", p72));
                    series7.getData().add(new XYChart.Data<>("Regular", p73));
                    bc.getData().clear();
                    bc.getData().addAll(series1,series2,series3,series4,series5,series6,series7);

                }
        );
    }
    @Subscribe
    public void setLabelshow1(total_cancelorder_numberEVENT Response) throws IOException {
        Platform.runLater(() ->
                {


                    bc.setVisible(false);
                    ObservableList<PieChart.Data> pieChartData =
                            FXCollections.observableArrayList(

                                    new PieChart.Data("Cancel Orders  ", Integer.parseInt(Response.getWarning().getObject11().toString())),
                                    new PieChart.Data("Total Orders ", Integer.parseInt(Response.getWarning().getObject12().toString())));

                    pieChartData.forEach(data ->
                            data.nameProperty().bind(Bindings.concat(data.getName(), " : ", data.pieValueProperty())));
                    pie.setVisible(true);
                    pie.getData().clear();
                    pie.getData().addAll(pieChartData);
                }
        );
    }
    @Subscribe
    public void setLabelshow1(total_complain_numberEVENT Response) throws IOException {
        Platform.runLater(() ->
                {
                    int c=Integer.parseInt(Response.getWarning().getObject2().toString());
                    bc.setVisible(false);
                    ObservableList<PieChart.Data> pieChartData =
                            FXCollections.observableArrayList(
                                    new PieChart.Data("Park 1 ", Integer.parseInt(Response.getWarning().getObject15().toString())),
                                    new PieChart.Data("Park 2 ", Integer.parseInt(Response.getWarning().getObject14().toString())),
                                    new PieChart.Data("Park 3 ", Integer.parseInt(Response.getWarning().getObject13().toString())),
                                    new PieChart.Data("Park 4 ", Integer.parseInt(Response.getWarning().getObject12().toString())),
                                    new PieChart.Data("Park 5 ", Integer.parseInt(Response.getWarning().getObject11().toString())),
                                    new PieChart.Data("Park 6 ", Integer.parseInt(Response.getWarning().getObject10().toString())),
                                    new PieChart.Data("Park 7 ", Integer.parseInt(Response.getWarning().getObject9().toString())));

                    pieChartData.forEach(data ->
                            data.nameProperty().bind(Bindings.concat(data.getName(), " Complain: ", data.pieValueProperty())));
                    pie.setVisible(true);
                    pie.getData().clear();
                    pie.getData().addAll(pieChartData);
                }
        );
    }
}
