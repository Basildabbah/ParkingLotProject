package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

public class CurrentPictureController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

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
    private Rectangle rect040;

    @FXML
    private Rectangle rect041;

    @FXML
    private Rectangle rect042;

    @FXML
    private Rectangle rect050;

    @FXML
    private Rectangle rect051;

    @FXML
    private Rectangle rect052;

    @FXML
    private Rectangle rect060;

    @FXML
    private Rectangle rect061;

    @FXML
    private Rectangle rect062;

    @FXML
    private Rectangle rect070;

    @FXML
    private Rectangle rect071;

    @FXML
    private Rectangle rect072;

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
    private Rectangle rect140;

    @FXML
    private Rectangle rect141;

    @FXML
    private Rectangle rect142;

    @FXML
    private Rectangle rect150;

    @FXML
    private Rectangle rect151;

    @FXML
    private Rectangle rect152;

    @FXML
    private Rectangle rect160;

    @FXML
    private Rectangle rect161;

    @FXML
    private Rectangle rect162;

    @FXML
    private Rectangle rect170;

    @FXML
    private Rectangle rect171;

    @FXML
    private Rectangle rect172;

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
    private Rectangle rect240;

    @FXML
    private Rectangle rect241;

    @FXML
    private Rectangle rect242;

    @FXML
    private Rectangle rect250;

    @FXML
    private Rectangle rect251;

    @FXML
    private Rectangle rect252;

    @FXML
    private Rectangle rect260;

    @FXML
    private Rectangle rect261;

    @FXML
    private Rectangle rect262;

    @FXML
    private Rectangle rect270;

    @FXML
    private Rectangle rect271;

    @FXML
    private Rectangle rect272;

    @FXML
    void goback(ActionEvent event) throws IOException {
        App.setRoot("curpark_chainmanager");
    }


    @FXML
    void initialize() {
        assert anchorroot != null : "fx:id=\"anchorroot\" was not injected: check your FXML file 'CurrentPictureController.fxml'.";
        assert backbuttton != null : "fx:id=\"backbuttton\" was not injected: check your FXML file 'CurrentPictureController.fxml'.";
        assert rect000 != null : "fx:id=\"rect000\" was not injected: check your FXML file 'CurrentPictureController.fxml'.";
        assert rect001 != null : "fx:id=\"rect001\" was not injected: check your FXML file 'CurrentPictureController.fxml'.";
        assert rect002 != null : "fx:id=\"rect002\" was not injected: check your FXML file 'CurrentPictureController.fxml'.";
        assert rect010 != null : "fx:id=\"rect010\" was not injected: check your FXML file 'CurrentPictureController.fxml'.";
        assert rect011 != null : "fx:id=\"rect011\" was not injected: check your FXML file 'CurrentPictureController.fxml'.";
        assert rect012 != null : "fx:id=\"rect012\" was not injected: check your FXML file 'CurrentPictureController.fxml'.";
        assert rect020 != null : "fx:id=\"rect020\" was not injected: check your FXML file 'CurrentPictureController.fxml'.";
        assert rect021 != null : "fx:id=\"rect021\" was not injected: check your FXML file 'CurrentPictureController.fxml'.";
        assert rect022 != null : "fx:id=\"rect022\" was not injected: check your FXML file 'CurrentPictureController.fxml'.";
        assert rect030 != null : "fx:id=\"rect030\" was not injected: check your FXML file 'CurrentPictureController.fxml'.";
        assert rect031 != null : "fx:id=\"rect031\" was not injected: check your FXML file 'CurrentPictureController.fxml'.";
        assert rect032 != null : "fx:id=\"rect032\" was not injected: check your FXML file 'CurrentPictureController.fxml'.";
        assert rect040 != null : "fx:id=\"rect040\" was not injected: check your FXML file 'CurrentPictureController.fxml'.";
        assert rect041 != null : "fx:id=\"rect041\" was not injected: check your FXML file 'CurrentPictureController.fxml'.";
        assert rect042 != null : "fx:id=\"rect042\" was not injected: check your FXML file 'CurrentPictureController.fxml'.";
        assert rect050 != null : "fx:id=\"rect050\" was not injected: check your FXML file 'CurrentPictureController.fxml'.";
        assert rect051 != null : "fx:id=\"rect051\" was not injected: check your FXML file 'CurrentPictureController.fxml'.";
        assert rect052 != null : "fx:id=\"rect052\" was not injected: check your FXML file 'CurrentPictureController.fxml'.";
        assert rect060 != null : "fx:id=\"rect060\" was not injected: check your FXML file 'CurrentPictureController.fxml'.";
        assert rect061 != null : "fx:id=\"rect061\" was not injected: check your FXML file 'CurrentPictureController.fxml'.";
        assert rect062 != null : "fx:id=\"rect062\" was not injected: check your FXML file 'CurrentPictureController.fxml'.";
        assert rect070 != null : "fx:id=\"rect070\" was not injected: check your FXML file 'CurrentPictureController.fxml'.";
        assert rect071 != null : "fx:id=\"rect071\" was not injected: check your FXML file 'CurrentPictureController.fxml'.";
        assert rect072 != null : "fx:id=\"rect072\" was not injected: check your FXML file 'CurrentPictureController.fxml'.";
        assert rect100 != null : "fx:id=\"rect100\" was not injected: check your FXML file 'CurrentPictureController.fxml'.";
        assert rect101 != null : "fx:id=\"rect101\" was not injected: check your FXML file 'CurrentPictureController.fxml'.";
        assert rect102 != null : "fx:id=\"rect102\" was not injected: check your FXML file 'CurrentPictureController.fxml'.";
        assert rect110 != null : "fx:id=\"rect110\" was not injected: check your FXML file 'CurrentPictureController.fxml'.";
        assert rect111 != null : "fx:id=\"rect111\" was not injected: check your FXML file 'CurrentPictureController.fxml'.";
        assert rect112 != null : "fx:id=\"rect112\" was not injected: check your FXML file 'CurrentPictureController.fxml'.";
        assert rect120 != null : "fx:id=\"rect120\" was not injected: check your FXML file 'CurrentPictureController.fxml'.";
        assert rect121 != null : "fx:id=\"rect121\" was not injected: check your FXML file 'CurrentPictureController.fxml'.";
        assert rect122 != null : "fx:id=\"rect122\" was not injected: check your FXML file 'CurrentPictureController.fxml'.";
        assert rect130 != null : "fx:id=\"rect130\" was not injected: check your FXML file 'CurrentPictureController.fxml'.";
        assert rect131 != null : "fx:id=\"rect131\" was not injected: check your FXML file 'CurrentPictureController.fxml'.";
        assert rect132 != null : "fx:id=\"rect132\" was not injected: check your FXML file 'CurrentPictureController.fxml'.";
        assert rect140 != null : "fx:id=\"rect140\" was not injected: check your FXML file 'CurrentPictureController.fxml'.";
        assert rect141 != null : "fx:id=\"rect141\" was not injected: check your FXML file 'CurrentPictureController.fxml'.";
        assert rect142 != null : "fx:id=\"rect142\" was not injected: check your FXML file 'CurrentPictureController.fxml'.";
        assert rect150 != null : "fx:id=\"rect150\" was not injected: check your FXML file 'CurrentPictureController.fxml'.";
        assert rect151 != null : "fx:id=\"rect151\" was not injected: check your FXML file 'CurrentPictureController.fxml'.";
        assert rect152 != null : "fx:id=\"rect152\" was not injected: check your FXML file 'CurrentPictureController.fxml'.";
        assert rect160 != null : "fx:id=\"rect160\" was not injected: check your FXML file 'CurrentPictureController.fxml'.";
        assert rect161 != null : "fx:id=\"rect161\" was not injected: check your FXML file 'CurrentPictureController.fxml'.";
        assert rect162 != null : "fx:id=\"rect162\" was not injected: check your FXML file 'CurrentPictureController.fxml'.";
        assert rect170 != null : "fx:id=\"rect170\" was not injected: check your FXML file 'CurrentPictureController.fxml'.";
        assert rect171 != null : "fx:id=\"rect171\" was not injected: check your FXML file 'CurrentPictureController.fxml'.";
        assert rect172 != null : "fx:id=\"rect172\" was not injected: check your FXML file 'CurrentPictureController.fxml'.";
        assert rect200 != null : "fx:id=\"rect200\" was not injected: check your FXML file 'CurrentPictureController.fxml'.";
        assert rect201 != null : "fx:id=\"rect201\" was not injected: check your FXML file 'CurrentPictureController.fxml'.";
        assert rect202 != null : "fx:id=\"rect202\" was not injected: check your FXML file 'CurrentPictureController.fxml'.";
        assert rect210 != null : "fx:id=\"rect210\" was not injected: check your FXML file 'CurrentPictureController.fxml'.";
        assert rect211 != null : "fx:id=\"rect211\" was not injected: check your FXML file 'CurrentPictureController.fxml'.";
        assert rect212 != null : "fx:id=\"rect212\" was not injected: check your FXML file 'CurrentPictureController.fxml'.";
        assert rect220 != null : "fx:id=\"rect220\" was not injected: check your FXML file 'CurrentPictureController.fxml'.";
        assert rect221 != null : "fx:id=\"rect221\" was not injected: check your FXML file 'CurrentPictureController.fxml'.";
        assert rect222 != null : "fx:id=\"rect222\" was not injected: check your FXML file 'CurrentPictureController.fxml'.";
        assert rect230 != null : "fx:id=\"rect230\" was not injected: check your FXML file 'CurrentPictureController.fxml'.";
        assert rect231 != null : "fx:id=\"rect231\" was not injected: check your FXML file 'CurrentPictureController.fxml'.";
        assert rect232 != null : "fx:id=\"rect232\" was not injected: check your FXML file 'CurrentPictureController.fxml'.";
        assert rect240 != null : "fx:id=\"rect240\" was not injected: check your FXML file 'CurrentPictureController.fxml'.";
        assert rect241 != null : "fx:id=\"rect241\" was not injected: check your FXML file 'CurrentPictureController.fxml'.";
        assert rect242 != null : "fx:id=\"rect242\" was not injected: check your FXML file 'CurrentPictureController.fxml'.";
        assert rect250 != null : "fx:id=\"rect250\" was not injected: check your FXML file 'CurrentPictureController.fxml'.";
        assert rect251 != null : "fx:id=\"rect251\" was not injected: check your FXML file 'CurrentPictureController.fxml'.";
        assert rect252 != null : "fx:id=\"rect252\" was not injected: check your FXML file 'CurrentPictureController.fxml'.";
        assert rect260 != null : "fx:id=\"rect260\" was not injected: check your FXML file 'CurrentPictureController.fxml'.";
        assert rect261 != null : "fx:id=\"rect261\" was not injected: check your FXML file 'CurrentPictureController.fxml'.";
        assert rect262 != null : "fx:id=\"rect262\" was not injected: check your FXML file 'CurrentPictureController.fxml'.";
        assert rect270 != null : "fx:id=\"rect270\" was not injected: check your FXML file 'CurrentPictureController.fxml'.";
        assert rect271 != null : "fx:id=\"rect271\" was not injected: check your FXML file 'CurrentPictureController.fxml'.";
        assert rect272 != null : "fx:id=\"rect272\" was not injected: check your FXML file 'CurrentPictureController.fxml'.";



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