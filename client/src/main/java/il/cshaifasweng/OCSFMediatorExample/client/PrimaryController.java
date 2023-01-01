//package il.cshaifasweng.OCSFMediatorExample.client;
//
//import java.io.IOException;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.ResourceBundle;
//
//import javafx.application.Platform;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.scene.control.*;
//import javafx.scene.control.cell.PropertyValueFactory;
//import org.greenrobot.eventbus.EventBus;
//import org.greenrobot.eventbus.Subscribe;
//
//public class PrimaryController {
//
//	public static String str=" ";
//
//	public static void setStr(String str) {
//		PrimaryController.str = str;
//	}
//	public static String getStr() {
//		return str;
//	}
//
//	@FXML
//	private ResourceBundle resources;
//
//	@FXML
//	private URL location;
//
//	@FXML
//	private TextField idprice;
//
//	@FXML
//	private Label labelshow;
//
//	@FXML
//	private TextField newprice;
//
//	@FXML
//	private Button ok;
//
//	@FXML
//	private Button setnewprice;
//
//	@FXML
//	private Button showpark;
//
//	@FXML
//	private Button showprice;
//
//	@FXML
//	private TableView<Parking> table;
//
//	@FXML
//	private TableColumn<Parking, Integer> tableid;
//
//	@FXML
//	private TableColumn<Parking, String> tablename;
//	@FXML
//	private TableColumn<Parking, Integer> tabledepth;
//
//	@FXML
//	private TableColumn<Parking, Integer> tablesize;
//
//	@FXML
//	private TableColumn<Parking, Integer> tablehight;
//
//	@FXML
//	private TableColumn<Parking, Integer> tablewidth;
//
//	@FXML
//	private TableView<Price> table2;
//
//	@FXML
//	private TableColumn<Price, Integer> table2PAymentMethoud;
//
//	@FXML
//	private TableColumn<Price, Integer> table2id;
//
//	@FXML
//	private TableColumn<Price, Integer> table2price;
//
//	@FXML
//	private TableColumn<Price, Integer> table2typeOfParking;
//
//
//	@FXML
//	void okfun(ActionEvent event) {
//		String tmp="#okey";
//		tmp+=idprice.getText();
//		tmp+=",";
//		tmp+=newprice.getText();
//		System.out.println(tmp);
//		newprice.clear();
//		idprice.clear();
//
//		try {
//			SimpleClient.getClient().sendToServer(tmp);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	@FXML
//	void setnewprice(ActionEvent event) {
//
//			//SimpleClient.getClient().sendToServer("setNewPrice");
//			idprice.setVisible(true);
//			newprice.setVisible(true);
//			ok.setVisible(true);
//			table.setVisible(false);
//			table2.setVisible(false);
//
//
//	}
//
//	@FXML
//	void showparkfun(ActionEvent event) {
//		try {
//			SimpleClient.getClient().sendToServer("#showparkfun");
//			} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	@FXML
//	void showpricefun(ActionEvent event) {
//		try {
//			SimpleClient.getClient().sendToServer("#showpricefun");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//	@Subscribe
//	public void setLabelshow(WarningEvent c) {
//		Platform.runLater(()->{
//				labelshow.setText(c.getWarning().getMessage());
//				tableid.setCellValueFactory(new PropertyValueFactory<Parking, Integer>("id"));
//				tablesize.setCellValueFactory(new PropertyValueFactory<Parking, Integer>("size"));
//				tabledepth.setCellValueFactory(new PropertyValueFactory<Parking, Integer>("depth"));
//				tablehight.setCellValueFactory(new PropertyValueFactory<Parking, Integer>("hight"));
//				tablename.setCellValueFactory(new PropertyValueFactory<Parking, String>("name"));
//				tablewidth.setCellValueFactory(new PropertyValueFactory<Parking, Integer>("width"));
//
//			ObservableList<Parking> list = FXCollections.observableList((ArrayList<Parking>) c.getWarning().getList());
//			idprice.setVisible(false);
//			newprice.setVisible(false);
//			ok.setVisible(false);
//				table.setVisible(true);
//				table2.setVisible(false);
//				table.setItems(list);
//
//		});
//
//	}
//
//
//	@Subscribe
//	public void setLabelshow(PriceEvent c) {
//		Platform.runLater(()->{
//			labelshow.setText(c.getWarning().getMessage());
//			table2id.setCellValueFactory(new PropertyValueFactory<Price, Integer>("id"));
//			table2price.setCellValueFactory(new PropertyValueFactory<Price, Integer>("Price"));
//			table2typeOfParking.setCellValueFactory(new PropertyValueFactory<Price, Integer>("typeOfParking"));
//			table2PAymentMethoud.setCellValueFactory(new PropertyValueFactory<Price, Integer>("PAymentMethoud"));
//
//			ObservableList<Price> list = FXCollections.observableList((ArrayList<Price>) c.getWarning().getList());
//			idprice.setVisible(false);
//			newprice.setVisible(false);
//			ok.setVisible(false);
//			table.setVisible(false);
//			table2.setVisible(true);
//			table2.setItems(list);
///*	@FXML
//	private TableColumn<Price, Integer> table2PAymentMethoud;
//
//	@FXML
//	private TableColumn<Price, Integer> table2id;
//
//	@FXML
//	private TableColumn<Price, Integer> table2price;
//
//	@FXML
//	private TableColumn<Price, Integer> table2typeOfParking;*/
//		});
//
//	}
//
//	@FXML
//	void initialize() {
//		EventBus.getDefault().register(this);
//	}
//
//}
//
////
////	@FXML
////	void okfun(ActionEvent event) {
////		try {
////			SimpleClient.getClient().sendToServer("okey");
////		} catch (IOException e) {
////			e.printStackTrace();
////		}
////	}
////
////	@FXML
////	void setnewprice(ActionEvent event) {
////		try {
////			SimpleClient.getClient().sendToServer("setNewPrice");
////		} catch (IOException e) {
////			e.printStackTrace();
////		}
////	}
////
////	@FXML
////	void showparkfun(ActionEvent event) {
////		try {
////			SimpleClient.getClient().sendToServer("#showparkfun");
////			labelshow.setVisible(true);
////		} catch (IOException e) {
////			e.printStackTrace();
////		}
////	}
////
////	@FXML
////	void showpricefun(ActionEvent event) {
////		try {
////			SimpleClient.getClient().sendToServer("showpricefun");
////			labelshow.setVisible(true);
////		} catch (IOException e) {
////			e.printStackTrace();
////		}
////
////	}
//////	@Subscribe
//////	public void setDataFromServerTF(WarningEvent event) {
//////		labelshow.setText(event.getWarning().getMessage());
//////	}
////
////	@Subscribe
////	public void setLabelshow(Object e) {
////		WarningEvent event=(WarningEvent) e;
////		labelshow.setText(event.getWarning().getMessage());
////	}
////	@FXML
////	void initialize() {
////		EventBus.getDefault().register(this);
////		EventBus.getDefault().post(new WarningEvent(new Warning("")));
////		Warning message = new Warning("#showparkfun");
////		try {
////			SimpleClient.getClient().sendToServer(message);
////		} catch (IOException e) {
////			e.printStackTrace();
////		}
////	}
////
////}
