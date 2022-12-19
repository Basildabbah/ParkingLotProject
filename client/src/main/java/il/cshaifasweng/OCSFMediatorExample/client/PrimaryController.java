package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;

import antlr.debug.MessageEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.greenrobot.eventbus.Subscribe;

public class PrimaryController {

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
			SimpleClient.getClient().sendToServer("showparkfun");

			labelshow.setVisible(true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void showpricefun(ActionEvent event) {
		try {
			SimpleClient.getClient().sendToServer("showpricefun");
			setlabelshow(event);
			labelshow.setVisible(true);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Subscribe
	public void setlabelshow(ActionEvent event) {
		labelshow.setText(event.getWarning());
	}


}
