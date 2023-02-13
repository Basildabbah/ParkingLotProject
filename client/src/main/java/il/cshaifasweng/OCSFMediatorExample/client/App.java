package il.cshaifasweng.OCSFMediatorExample.client;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.io.IOException;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private SimpleClient client;

    private static int[][][] currentParkingLot;

    public static int[][][] getCurrentParkingLot() {
        return currentParkingLot;
    }

    public static void setCurrentParkingLot(int[][][] currentParkingLot) {
        App.currentParkingLot = currentParkingLot;
    }

    @Override
    public void start(Stage stage) throws IOException {
    	EventBus.getDefault().register(this);
    	client = SimpleClient.getClient();
    	client.openConnection();
        scene = new Scene(loadFXML("home"), 1000, 750);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    
    

    @Override
	public void stop() throws Exception {
		// TODO Auto-generated method stub
    	EventBus.getDefault().unregister(this);
		super.stop();
	}
    
    @Subscribe
    public void onWarningEvent(WarningEvent event) {
        System.out.println("aaa");
    	Platform.runLater(() -> {
    		Alert alert = new Alert(AlertType.WARNING,
        			String.format("Message: %s\nTimestamp: %s\n",
        					event.getWarning().getMessage(),
        					event.getWarning().getTime().toString())
        	);
        	alert.show();

    	});

    }


    @Subscribe
    public void onMessageWaEvent(MessageWaEvent event) {
        System.out.println("lkaffafa");
        Platform.runLater(() -> {
            Alert alert = new Alert(AlertType.CONFIRMATION,
                    String.format("Message: %s\n",
                            event.getWarning().getMessage()));
            alert.show();

        });

    }

    @Subscribe
    public void onMessageWaInfEvent(MessageWaInfEvent event) {
        System.out.println("lkaffafa");
        Platform.runLater(() -> {
            Alert alert = new Alert(AlertType.INFORMATION,
                    String.format("Message: %s\n",
                            event.getWarning().getMessage()));
            alert.show();

        });

    }


    @Subscribe
    public void onMessageWaErEvent(MessageWaErEvent event) {
        System.out.println("lkaffafa");
        Platform.runLater(() -> {
            Alert alert = new Alert(AlertType.ERROR,
                    String.format("Message: %s\n",
                            event.getWarning().getMessage()));
            alert.show();

        });

    }

    @Subscribe
    public void onCurrentPictureEvent(CurrentPictureEvent event) {
        setCurrentParkingLot(event.getCurrentParkingLot());
        if (event.getCurrentParkingLotCols() == 4) {
            try {
                App.setRoot("CurrentPicture2Controller");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                App.setRoot("CurrentPictureController");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

	public static void main(String[] args) {
        launch();
    }

}