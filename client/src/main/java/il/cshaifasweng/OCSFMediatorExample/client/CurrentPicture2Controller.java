package il.cshaifasweng.OCSFMediatorExample.client;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.imageio.ImageIO;

public class CurrentPicture2Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button pdfbut;


    @FXML
    private AnchorPane anchorroot;

    @FXML
    private Button backbuttton;

    @FXML
    private Rectangle rect000;

    @FXML
    private Rectangle rect001;

    @FXML
    private Rectangle rect002;

    @FXML
    private Rectangle rect010;

    @FXML
    private Rectangle rect011;

    @FXML
    private Rectangle rect012;

    @FXML
    private Rectangle rect020;

    @FXML
    private Rectangle rect021;

    @FXML
    private Rectangle rect022;

    @FXML
    private Rectangle rect030;

    @FXML
    private Rectangle rect031;

    @FXML
    private Rectangle rect032;

    @FXML
    private Rectangle rect100;

    @FXML
    private Rectangle rect101;

    @FXML
    private Rectangle rect102;

    @FXML
    private Rectangle rect110;

    @FXML
    private Rectangle rect111;

    @FXML
    private Rectangle rect112;

    @FXML
    private Rectangle rect120;

    @FXML
    private Rectangle rect121;

    @FXML
    private Rectangle rect122;

    @FXML
    private Rectangle rect130;

    @FXML
    private Rectangle rect131;

    @FXML
    private Rectangle rect132;

    @FXML
    private Rectangle rect200;

    @FXML
    private Rectangle rect201;

    @FXML
    private Rectangle rect202;

    @FXML
    private Rectangle rect210;

    @FXML
    private Rectangle rect211;

    @FXML
    private Rectangle rect212;

    @FXML
    private Rectangle rect220;

    @FXML
    private Rectangle rect221;

    @FXML
    private Rectangle rect222;

    @FXML
    private Rectangle rect230;

    @FXML
    private Rectangle rect231;

    @FXML
    private Rectangle rect232;

    @FXML
    void exportToPDF(ActionEvent event) {
        WritableImage wi = anchorroot.snapshot(new SnapshotParameters(), null);
        anchorroot.snapshot(null, wi);
        BufferedImage bImage = SwingFXUtils.fromFXImage(wi, null);
        try {
            Calendar cal = Calendar.getInstance();
            String fileName = "Status_" + + CurparkChainmanager.plid + "/" +
                    cal.get(Calendar.YEAR) + "-" +
                    cal.get(Calendar.MONTH) + "-" +
                    cal.get(Calendar.DAY_OF_MONTH)+ "-" +
                    cal.get(Calendar.HOUR_OF_DAY) + "-" +
                    cal.get(Calendar.MINUTE) + "-" +
                    cal.get(Calendar.SECOND) + ".pdf";
            File currentDirectory = new File(System.getProperty("user.dir"));
            File parentDirectory = currentDirectory.getParentFile();
            System.out.println(currentDirectory);
            FileOutputStream fos = new FileOutputStream(parentDirectory + "\\\\PLstatus\\\\" + fileName);
            Document document = new Document();
            PdfWriter.getInstance(document, fos);
            document.open();

            ByteArrayOutputStream png = new ByteArrayOutputStream();
            ImageIO.write(bImage, "png", png);
            Image image = Image.getInstance(png.toByteArray());
            image.scaleToFit(PageSize.A4.getWidth(), PageSize.A4.getHeight());
            image.setScaleToFitLineWhenOverflow(true);
            document.add(image);
            document.close();

            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION,
                        "PDF exported\n");
                alert.show();
            });
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void goback(ActionEvent event) throws IOException {
        App.setRoot("curpark_chainmanager");
    }
    private void refreshTable() {
        try {
            System.out.println("refresh table");
            SimpleClient.getClient().sendToServer(new Message("#updatematrix" , CurparkChainmanager.plid));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Subscribe
    public void onCP2Crefresh(CP2Crefresh event) {

        int[][][] matrix3d = event.getCurrentParkingLot();

        for (int i = 0; i < matrix3d.length; i++) {
            for (int j = 0; j < matrix3d[i].length; j++) {
                for (int k = 0; k < matrix3d[i][j].length; k++) {
                    String rectId = "rect" + i + j + k;
                    Rectangle rect = (Rectangle) anchorroot.lookup("#" + rectId);
                    if (matrix3d[i][j][k] == 2) {
                        rect.setFill(Color.RED);
                    } else if (matrix3d[i][j][k] == 0) {
                        rect.setFill(Color.WHITE);
                    } else {
                        rect.setFill(new Color(0.0, 0.0, 1.0, 1.0));
                    }
                }
            }
        }
    }


    @FXML
    void initialize() {
        EventBus.getDefault().register(this);
        assert anchorroot != null : "fx:id=\"anchorroot\" was not injected: check your FXML file 'CurrentPicture2Controller.fxml'.";
        assert backbuttton != null : "fx:id=\"backbuttton\" was not injected: check your FXML file 'CurrentPicture2Controller.fxml'.";
        assert rect000 != null : "fx:id=\"rect000\" was not injected: check your FXML file 'CurrentPicture2Controller.fxml'.";
        assert rect001 != null : "fx:id=\"rect001\" was not injected: check your FXML file 'CurrentPicture2Controller.fxml'.";
        assert rect002 != null : "fx:id=\"rect002\" was not injected: check your FXML file 'CurrentPicture2Controller.fxml'.";
        assert rect010 != null : "fx:id=\"rect010\" was not injected: check your FXML file 'CurrentPicture2Controller.fxml'.";
        assert rect011 != null : "fx:id=\"rect011\" was not injected: check your FXML file 'CurrentPicture2Controller.fxml'.";
        assert rect012 != null : "fx:id=\"rect012\" was not injected: check your FXML file 'CurrentPicture2Controller.fxml'.";
        assert rect020 != null : "fx:id=\"rect020\" was not injected: check your FXML file 'CurrentPicture2Controller.fxml'.";
        assert rect021 != null : "fx:id=\"rect021\" was not injected: check your FXML file 'CurrentPicture2Controller.fxml'.";
        assert rect022 != null : "fx:id=\"rect022\" was not injected: check your FXML file 'CurrentPicture2Controller.fxml'.";
        assert rect030 != null : "fx:id=\"rect030\" was not injected: check your FXML file 'CurrentPicture2Controller.fxml'.";
        assert rect031 != null : "fx:id=\"rect031\" was not injected: check your FXML file 'CurrentPicture2Controller.fxml'.";
        assert rect032 != null : "fx:id=\"rect032\" was not injected: check your FXML file 'CurrentPicture2Controller.fxml'.";
        assert rect100 != null : "fx:id=\"rect100\" was not injected: check your FXML file 'CurrentPicture2Controller.fxml'.";
        assert rect101 != null : "fx:id=\"rect101\" was not injected: check your FXML file 'CurrentPicture2Controller.fxml'.";
        assert rect102 != null : "fx:id=\"rect102\" was not injected: check your FXML file 'CurrentPicture2Controller.fxml'.";
        assert rect110 != null : "fx:id=\"rect110\" was not injected: check your FXML file 'CurrentPicture2Controller.fxml'.";
        assert rect111 != null : "fx:id=\"rect111\" was not injected: check your FXML file 'CurrentPicture2Controller.fxml'.";
        assert rect112 != null : "fx:id=\"rect112\" was not injected: check your FXML file 'CurrentPicture2Controller.fxml'.";
        assert rect120 != null : "fx:id=\"rect120\" was not injected: check your FXML file 'CurrentPicture2Controller.fxml'.";
        assert rect121 != null : "fx:id=\"rect121\" was not injected: check your FXML file 'CurrentPicture2Controller.fxml'.";
        assert rect122 != null : "fx:id=\"rect122\" was not injected: check your FXML file 'CurrentPicture2Controller.fxml'.";
        assert rect130 != null : "fx:id=\"rect130\" was not injected: check your FXML file 'CurrentPicture2Controller.fxml'.";
        assert rect131 != null : "fx:id=\"rect131\" was not injected: check your FXML file 'CurrentPicture2Controller.fxml'.";
        assert rect132 != null : "fx:id=\"rect132\" was not injected: check your FXML file 'CurrentPicture2Controller.fxml'.";
        assert rect200 != null : "fx:id=\"rect200\" was not injected: check your FXML file 'CurrentPicture2Controller.fxml'.";
        assert rect201 != null : "fx:id=\"rect201\" was not injected: check your FXML file 'CurrentPicture2Controller.fxml'.";
        assert rect202 != null : "fx:id=\"rect202\" was not injected: check your FXML file 'CurrentPicture2Controller.fxml'.";
        assert rect210 != null : "fx:id=\"rect210\" was not injected: check your FXML file 'CurrentPicture2Controller.fxml'.";
        assert rect211 != null : "fx:id=\"rect211\" was not injected: check your FXML file 'CurrentPicture2Controller.fxml'.";
        assert rect212 != null : "fx:id=\"rect212\" was not injected: check your FXML file 'CurrentPicture2Controller.fxml'.";
        assert rect220 != null : "fx:id=\"rect220\" was not injected: check your FXML file 'CurrentPicture2Controller.fxml'.";
        assert rect221 != null : "fx:id=\"rect221\" was not injected: check your FXML file 'CurrentPicture2Controller.fxml'.";
        assert rect222 != null : "fx:id=\"rect222\" was not injected: check your FXML file 'CurrentPicture2Controller.fxml'.";
        assert rect230 != null : "fx:id=\"rect230\" was not injected: check your FXML file 'CurrentPicture2Controller.fxml'.";
        assert rect231 != null : "fx:id=\"rect231\" was not injected: check your FXML file 'CurrentPicture2Controller.fxml'.";
        assert rect232 != null : "fx:id=\"rect232\" was not injected: check your FXML file 'CurrentPicture2Controller.fxml'.";


        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1),
                        event -> {
                            // refresh the table here
                            refreshTable();
                        })
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();


        int[][][] matrix3d = App.getCurrentParkingLot();

        for (int i = 0; i < matrix3d.length; i++) {
            for (int j = 0; j < matrix3d[i].length; j++) {
                for (int k = 0; k < matrix3d[i][j].length; k++) {
                    String rectId = "rect" + i + j + k;
                    Rectangle rect = (Rectangle) anchorroot.lookup("#" + rectId);
                    if (matrix3d[i][j][k] == 2) {
                        rect.setFill(Color.RED);
                    } else if (matrix3d[i][j][k] == 0) {
                        rect.setFill(Color.WHITE);
                    } else {
                        rect.setFill(new Color(0.0, 0.0, 1.0, 1.0));
                    }
                }
            }
        }


    }

}