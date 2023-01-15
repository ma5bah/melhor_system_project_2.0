package com.lab.melhor;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import java.io.IOException;
import java.net.URL;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.logging.Level;

import BackEnd.CommonTask;
import BackEnd.db;
import BackEnd.database_schema.Employee;

public class login_registration_controller implements Initializable {
    private Boolean login_state;
    // @FXML
    // private JFXButton login_button;
    @FXML
    private TextField registration_contact;
    @FXML
    private TextField registration_email;
    @FXML
    private PasswordField registration_password;
    @FXML
    private TextField registration_name;
    @FXML
    private TextField login_email;
    @FXML
    private PasswordField login_password;
    @FXML
    private CheckBox login_remember_check_box;
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
            // System.out.println(registration_contact.getText()+"
            // "+registration_email.getText()+" "+registration_password.getText());
            if (check_registration_info()) {
                CommonTask.showAlert(AlertType.ERROR, "Incomplete information",
                        "Necessary infornmation must be provided");
                return;
            }
            if (this.registration(registration_email.getText(), registration_password.getText(),
                    registration_contact.getText(), registration_name.getText())) {
                App.setRoot("dashboard");
            } else {

                CommonTask.showAlert(AlertType.ERROR, "Incomplete information",
                        "Necessary infornmation must be provided");
            }
        } catch (IOException e) {
            CommonTask.log(Level.SEVERE, e, e.getMessage());

        }
    }

    private Boolean registration(String email, String password, String contact, String name) {
        return db.signup(email, password, contact, name);
    }

    private Boolean login(String email, String password) {
        return db.login(email, password);
    }

    @FXML
    private void login_button_clicked() {
       
        try {
            
            if (check_login_info()) {
                CommonTask.showAlert(AlertType.ERROR, "Incomplete information",
                        "Necessary infornmation must be provided");
                return;
            }
            if (this.login(login_email.getText(), login_password.getText())) {
                App.setRoot("dashboard");
            } else {
                CommonTask.showAlert(AlertType.ERROR, "Wrong information", "Necessary infornmation must be provided");
                return;
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

    private boolean check_login_info() {
        return login_email.getText() == "" || login_email.getText() == null
                || login_password.getText() == "" || login_password.getText() == null;
    }

    private boolean check_registration_info() {
        return registration_contact.getText() == "" || registration_contact.getText() == null
                || registration_email.getText() == "" || registration_email.getText() == null
                || registration_password.getText() == "" || registration_password.getText() == null
                || registration_name.getText() == "" || registration_name.getText() == null;
    }

    @FXML
    private void handle_key_press_event(KeyEvent evt) {
        // if(evt.getEventType().equals(KeyEvent.KEY_PRESSED)){
        // evt.code
        // }
        if (evt.getSource() == registration_name) {
            System.out.println("from name");
            System.out.println(evt.getCharacter());
            System.out.println(registration_name.getText());
        }
        if (evt.getSource() == login_password) {
            if (evt.getCode().equals(KeyCode.ENTER)) {
                login_button_clicked();
            }
        }
        if (evt.getSource() == registration_password) {
            if (evt.getCode().equals(KeyCode.ENTER)) {
                registration_button_clicked();
            }
        }
        // System.out.println(evt.getCharacter()==Character);
        // System.out.println(evt.getText());

        // System.out.println(evt.getTarget());
        // System.out.println(evt);
    }

    // public void handleClicks(Event actionEvent) throws IOException {
    // // for (Node iterable_element : main_stack_pane.getChildren()) {
    // // System.out.print(iterable_element + " ");
    // // }
    // // System.out.println();
    // // pnlMenus.setStyle("-fx-background-color : #53639F");
    // // pnlMenus.toFront();

    // System.out.println("null");

    // if (actionEvent.getSource() == registration_name) {

    // System.out.println("null 2.0");
    // }

    // }

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