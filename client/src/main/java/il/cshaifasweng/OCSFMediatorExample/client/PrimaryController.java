package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.Parking;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class PrimaryController {

	public static String str=" ";

	public static void setStr(String str) {
		PrimaryController.str = str;
	}
	public static String getStr() {
		return str;
	}

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TextField idprice;

	@FXML
	private Label labelshow;

	@FXML
	private TextField newprice;

	@FXML
	private Button ok;

	@FXML
	private Button setnewprice;

	@FXML
	private Button showpark;

	@FXML
	private Button showprice;

	@FXML
	private TableView<Parking> table;

	@FXML
	private TableColumn<Parking, Integer> tableid;

	@FXML
	private TableColumn<?, ?> tablesize;

	@FXML
	void okfun(ActionEvent event) {
		try {
			SimpleClient.getClient().sendToServer("okey");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void setnewprice(ActionEvent event) {
		try {
			SimpleClient.getClient().sendToServer("setNewPrice");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void showparkfun(ActionEvent event) {
		try {
			SimpleClient.getClient().sendToServer("#showparkfun");
			labelshow.setVisible(true);
//			System.out.println(str);
//			labelshow.setText(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void showpricefun(ActionEvent event) {
		try {
			SimpleClient.getClient().sendToServer("showpricefun");
			labelshow.setVisible(true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Subscribe
	public void setLabelshow(WarningEvent c) {
		Platform.runLater(()->{
			System.out.println("in setLabelshow");
			labelshow.setText(c.getWarning().getMessage());
			tableid.setCellValueFactory(new PropertyValueFactory<Parking,Integer>("id"));
			ObservableList<Parking> list= FXCollections.observableList((ArrayList<Parking>)c.getWarning().getList());
			table.setItems(list);
		});
	}

	@FXML
	void initialize() {
		EventBus.getDefault().register(this);
	}

}

//
//	@FXML
//	void okfun(ActionEvent event) {
//		try {
//			SimpleClient.getClient().sendToServer("okey");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	@FXML
//	void setnewprice(ActionEvent event) {
//		try {
//			SimpleClient.getClient().sendToServer("setNewPrice");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	@FXML
//	void showparkfun(ActionEvent event) {
//		try {
//			SimpleClient.getClient().sendToServer("#showparkfun");
//			labelshow.setVisible(true);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	@FXML
//	void showpricefun(ActionEvent event) {
//		try {
//			SimpleClient.getClient().sendToServer("showpricefun");
//			labelshow.setVisible(true);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//	}
////	@Subscribe
////	public void setDataFromServerTF(WarningEvent event) {
////		labelshow.setText(event.getWarning().getMessage());
////	}
//
//	@Subscribe
//	public void setLabelshow(Object e) {
//		WarningEvent event=(WarningEvent) e;
//		labelshow.setText(event.getWarning().getMessage());
//	}
//	@FXML
//	void initialize() {
//		EventBus.getDefault().register(this);
//		EventBus.getDefault().post(new WarningEvent(new Warning("")));
//		Warning message = new Warning("#showparkfun");
//		try {
//			SimpleClient.getClient().sendToServer(message);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//}
