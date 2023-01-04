package com.lab.melhor;

import com.lab.melhor.App;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import BackEnd.CommonTask;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;

public class WelcomeController implements Initializable {
    @FXML
    private AnchorPane main_pane;

    @FXML
    private void login() throws IOException {
        App.login_registration(true);

    }
    @FXML
    private void signup() throws IOException {
        App.login_registration(false);
    }

    @FXML
    private void show_about() throws IOException {
        CommonTask.showAlert(Alert.AlertType.INFORMATION, "About us",
                "This app is developed for managing inventory smoothly.\nSamin and masbah made this application.");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void show_terms() throws IOException {
        CommonTask.showAlert(AlertType.INFORMATION, "terms and policies",
                "What is Lorem Ipsum?\n Lorem Ipsum is simply dummy text of the printing and typesetting industry.\n  Lorem Ipsum has been the industry's standard dummy \n text ever since the 1500s, when an unknown printer took a galley\n  of type and scrambled it to make a type specimen book.\n  It has survived not only five centuries, but also the leap into electronic typesetting,\n  remaining essentially unchanged. It was popularised in the 1960s with the release\n  of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop\n  publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
    }
}
