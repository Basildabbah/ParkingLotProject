package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;

public class home_review {
    @FXML
    private ImageView star5;
    @FXML
    private ImageView star4;
    @FXML
    private ImageView star3;
    @FXML
    private ImageView star2;
    @FXML
    private ImageView star1;
    @FXML
    private ImageView gold1;

    @FXML
    private ImageView gold2;

    @FXML
    private ImageView gold3;

    @FXML
    private ImageView gold4;

    @FXML
    private ImageView gold5;


    @FXML
    private Button FAQ;

    @FXML
    void FAQ(ActionEvent event) throws IOException {
        App.setRoot("faq0");
    }

    @FXML
    private Button Prices;

    @FXML
    private Button about;

    @FXML
    private AnchorPane anchreview;

    @FXML
    private Button homebut;

    @FXML
    private Button login;

    @FXML
    private Button next;

    @FXML
    private Label nickname;

    @FXML
    private Button prev;

    @FXML
    private Label reviewnum;

    @FXML
    private Label text;

    @FXML
    void Pricesfun(ActionEvent event) throws IOException {
        App.setRoot("prices");

    }

    @FXML
    void aboutus(ActionEvent event) throws IOException {
        App.setRoot("aboutus");
    }

    @FXML
    void homebutfun(ActionEvent event) throws IOException {
        App.setRoot("home");
    }

    @FXML
    void loginfun(ActionEvent event) throws IOException {
        App.setRoot("loginadmin");
    }

    @FXML
    void prev(ActionEvent event) throws IOException {
        String tmp = "";
        reviewnum.setText(tmp += Integer.parseInt(reviewnum.getText()) - 1);
        if (!reviewnum.getText().equals("0")) {
            try {
                System.out.println(reviewnum.getText());

                SimpleClient.getClient().sendToServer(new Message("#review_-1", reviewnum.getText()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void next(ActionEvent event) throws IOException {
        String tmp = "";
        prev.setDisable(false);
        reviewnum.setText(tmp += Integer.parseInt(reviewnum.getText()) + 1);
        if (!reviewnum.getText().equals("0")) {
            prev.setVisible(true);
            try {
                SimpleClient.getClient().sendToServer(new Message("#review_0", reviewnum.getText()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Subscribe
    public void setLabelshow(reviewEvent c) throws IOException {
        Platform.runLater(() -> {
            if (c.getWarning().getObject7().toString().equals("no")) {
                String tmp = "";
                reviewnum.setText(tmp += Integer.parseInt(reviewnum.getText()) - 1);
            } else {
                nickname.setText(c.getWarning().getObject1().toString());
                text.setText(c.getWarning().getObject3().toString());
                System.out.println("aaaa"+c.getWarning().getObject2().toString());

                if(Integer.parseInt(c.getWarning().getObject2().toString())==1)
                {
                    star1.setVisible(false);
                    star2.setVisible(true);
                    star3.setVisible(true);
                    star4.setVisible(true);
                    star5.setVisible(true);
                    gold1.setVisible(true);
                    gold2.setVisible(false);
                    gold3.setVisible(false);
                    gold4.setVisible(false);
                    gold5.setVisible(false);

                }
                if(Integer.parseInt(c.getWarning().getObject2().toString())==2)
                {
                    star1.setVisible(false);
                    star2.setVisible(false);
                    star3.setVisible(true);
                    star4.setVisible(true);
                    star5.setVisible(true);
                    gold1.setVisible(true);
                    gold2.setVisible(true);
                    gold3.setVisible(false);
                    gold4.setVisible(false);
                    gold5.setVisible(false);

                }
                if(Integer.parseInt(c.getWarning().getObject2().toString())==3)
                {
                    star1.setVisible(false);
                    star2.setVisible(false);
                    star3.setVisible(false);
                    star4.setVisible(true);
                    star5.setVisible(true);
                    gold1.setVisible(true);
                    gold2.setVisible(true);
                    gold3.setVisible(true);
                    gold4.setVisible(false);
                    gold5.setVisible(false);

                }
                if(Integer.parseInt(c.getWarning().getObject2().toString())==4)
                {
                    star1.setVisible(false);
                    star2.setVisible(false);
                    star3.setVisible(false);
                    star4.setVisible(false);
                    star5.setVisible(true);
                    gold1.setVisible(true);
                    gold2.setVisible(true);
                    gold3.setVisible(true);
                    gold4.setVisible(true);
                    gold5.setVisible(false);

                }
                if(Integer.parseInt(c.getWarning().getObject2().toString())==5)
                {
                    star1.setVisible(false);
                    star2.setVisible(false);
                    star3.setVisible(false);
                    star4.setVisible(false);
                    star5.setVisible(false);
                    gold1.setVisible(true);
                    gold2.setVisible(true);
                    gold3.setVisible(true);
                    gold4.setVisible(true);
                    gold5.setVisible(true);

                }
            }

        });
    }


    @Subscribe
    public void setLabelshow(home_review_Event c) throws IOException {
        Platform.runLater(() -> {
            if (c.getWarning().getObject7().toString().equals("no")) {

                String tmp = "";
                reviewnum.setText(tmp += Integer.parseInt(reviewnum.getText()) + 1);
                if (Integer.parseInt(reviewnum.getText().toString()) == 0) {
                    reviewnum.setText("0");
                    nickname.setText("HI");
                    text.setText("Hi, You can click 'next' to check if there is any reviews from the subscriber");
                    //star1.setVisible(false);star2.setVisible(true);star3.setVisible(true);star4.setVisible(true);star5.setVisible(true);
                    //gold1.setVisible(true);gold2.setVisible(false);gold3.setVisible(false);gold4.setVisible(false);gold5.setVisible(false);
                }
            } else {
                nickname.setText(c.getWarning().getObject1().toString());
                text.setText(c.getWarning().getObject3().toString());
                if(Integer.parseInt(c.getWarning().getObject2().toString())==1)
                {
                    star1.setVisible(false);
                    star2.setVisible(true);
                    star3.setVisible(true);
                    star4.setVisible(true);
                    star5.setVisible(true);
                    gold1.setVisible(true);
                    gold2.setVisible(false);
                    gold3.setVisible(false);
                    gold4.setVisible(false);
                    gold5.setVisible(false);

                }
                if(Integer.parseInt(c.getWarning().getObject2().toString())==2)
                {
                    star1.setVisible(false);
                    star2.setVisible(false);
                    star3.setVisible(true);
                    star4.setVisible(true);
                    star5.setVisible(true);
                    gold1.setVisible(true);
                    gold2.setVisible(true);
                    gold3.setVisible(false);
                    gold4.setVisible(false);
                    gold5.setVisible(false);

                }
                if(Integer.parseInt(c.getWarning().getObject2().toString())==3)
                {
                    star1.setVisible(false);
                    star2.setVisible(false);
                    star3.setVisible(false);
                    star4.setVisible(true);
                    star5.setVisible(true);
                    gold1.setVisible(true);
                    gold2.setVisible(true);
                    gold3.setVisible(true);
                    gold4.setVisible(false);
                    gold5.setVisible(false);

                }
                if(Integer.parseInt(c.getWarning().getObject2().toString())==4)
                {
                    star1.setVisible(false);
                    star2.setVisible(false);
                    star3.setVisible(false);
                    star4.setVisible(false);
                    star5.setVisible(true);
                    gold1.setVisible(true);
                    gold2.setVisible(true);
                    gold3.setVisible(true);
                    gold4.setVisible(true);
                    gold5.setVisible(false);

                }

            }

        });
    }


    @FXML
    void initialize() {
        if (reviewnum.getText().equals("0")) {
            prev.setDisable(true);
            reviewnum.setText("0");
            nickname.setText("HI");
            text.setText("click 'next' to check if there is any reviews.");
        }

        EventBus.getDefault().register(this);
    }


}
