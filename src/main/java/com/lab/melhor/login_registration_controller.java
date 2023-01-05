package com.lab.melhor;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import java.io.IOException;
import java.net.URL;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class login_registration_controller implements Initializable {
    private Boolean login_state;
    // @FXML
    // private JFXButton login_button;
    @FXML
    private AnchorPane register_pane;
    @FXML
    private AnchorPane login_pane;

    @FXML
    private void register_on_mouse_clicked() {
        if (login_state) {
            login_state = !login_state;
            final Timeline timeline = new Timeline();
            timeline.getKeyFrames().addAll(
                    new KeyFrame(Duration.millis(500),
                            new KeyValue(register_pane.translateXProperty(), -6, Interpolator.EASE_IN),
                            new KeyValue(register_pane.opacityProperty(), .5, Interpolator.EASE_IN),
                            new KeyValue(register_pane.translateZProperty(), 5, Interpolator.EASE_IN),
                            new KeyValue(login_pane.translateXProperty(), 6, Interpolator.EASE_IN),
                            new KeyValue(login_pane.opacityProperty(), .5, Interpolator.EASE_IN),
                            new KeyValue(login_pane.translateZProperty(), -5, Interpolator.EASE_IN)),
                    new KeyFrame(Duration.millis(1000),
                            new KeyValue(register_pane.translateXProperty(), 6, Interpolator.EASE_OUT),
                            new KeyValue(register_pane.opacityProperty(), 1, Interpolator.EASE_OUT),
                            new KeyValue(register_pane.translateZProperty(), 5, Interpolator.EASE_IN),
                            new KeyValue(login_pane.translateXProperty(), -6, Interpolator.EASE_OUT),
                            new KeyValue(login_pane.opacityProperty(), .2, Interpolator.EASE_OUT),
                            new KeyValue(login_pane.translateZProperty(), -5, Interpolator.EASE_OUT)));
            timeline.play();
        }

    }


    @FXML
    private void registration_button_clicked() {
        try {
            if (this.registration("null", "null")) {
                this.login_on_mouse_clicked();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private Boolean registration(String username, String password) {
        return true;
    }

    private Boolean login(String username, String password) {
        return true;
    }

    @FXML
    private void login_button_clicked() {
        try {
            if (this.login("", "")) {
                App.setRoot("dashboard");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void login_on_mouse_clicked() throws InterruptedException {

        if (!login_state) {
            login_state = !login_state;

            final Timeline timeline = new Timeline();
            timeline.getKeyFrames().addAll(
                    new KeyFrame(Duration.millis(500),
                            new KeyValue(login_pane.translateXProperty(), 6, Interpolator.EASE_IN),
                            new KeyValue(login_pane.opacityProperty(), .5, Interpolator.EASE_IN),
                            new KeyValue(login_pane.translateZProperty(), 5, Interpolator.EASE_IN),
                            new KeyValue(register_pane.translateXProperty(), -6, Interpolator.EASE_IN),
                            new KeyValue(register_pane.opacityProperty(), .5, Interpolator.EASE_IN),
                            new KeyValue(register_pane.translateZProperty(), -5, Interpolator.EASE_IN)),
                    new KeyFrame(Duration.millis(1000),
                            new KeyValue(login_pane.translateXProperty(), -6, Interpolator.EASE_OUT),
                            new KeyValue(login_pane.opacityProperty(), 1, Interpolator.EASE_OUT),
                            new KeyValue(login_pane.translateZProperty(), 5, Interpolator.EASE_IN),
                            new KeyValue(register_pane.translateXProperty(), 6, Interpolator.EASE_OUT),
                            new KeyValue(register_pane.opacityProperty(), .2, Interpolator.EASE_OUT),
                            new KeyValue(register_pane.translateZProperty(), -5, Interpolator.EASE_OUT)));
            timeline.play();

        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            login_state = resources.getObject("login_state") == Boolean.TRUE;
        } catch (NullPointerException | MissingResourceException e) {

            login_state = false;
        }
        if (login_state) {
            register_pane.setOpacity(.2);
            register_pane.setTranslateZ(-10);
        } else {
            login_pane.setOpacity(.2);
            login_pane.setTranslateZ(-10);
        }
    }

}

// TranslateTransition first_login_transition=new
// TranslateTransition(Duration.millis(1000),login_pane);
// first_login_transition.setInterpolator(Interpolator.LINEAR);
// first_login_transition.setByX(6);

// TranslateTransition first_registration_transition=new
// TranslateTransition(Duration.millis(1000),register_pane);
// first_registration_transition.setInterpolator(Interpolator.LINEAR);
// first_registration_transition.setByX(-6);

// ParallelTransition first_transtion= new ParallelTransition();
// first_transtion.getChildren().addAll(
// first_login_transition,
// first_registration_transition
// );

// TranslateTransition second_login_transition=new
// TranslateTransition(Duration.millis(1000),login_pane);
// second_login_transition.setInterpolator(Interpolator.LINEAR);
// second_login_transition.setByX(-6);

// TranslateTransition second_registration_transition=new
// TranslateTransition(Duration.millis(1000),register_pane);
// second_registration_transition.setInterpolator(Interpolator.LINEAR);
// second_registration_transition.setByX(6);

// ParallelTransition second_transtion= new ParallelTransition();
// second_transtion.getChildren().addAll(
// second_login_transition,
// second_registration_transition
// );

// SequentialTransition final_transtion = new SequentialTransition();
// final_transtion.getChildren().addAll(first_transtion,second_transtion);
// final_transtion.play();