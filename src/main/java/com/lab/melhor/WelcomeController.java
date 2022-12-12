package com.lab.melhor;

import com.lab.melhor.App;
import java.io.IOException;
import javafx.fxml.FXML;
import BackEnd.CommonTask;
import javafx.scene.control.Alert;
public class WelcomeController {

    @FXML
    private void start_here() throws IOException {
        App.setRoot("login");
    }
    @FXML
    private void show_about()throws IOException{
        CommonTask.showAlert(Alert.AlertType.INFORMATION, "About us", "This app is developed for managing inventory smoothly.\nSamin and masbah made this application.");
    }
}
