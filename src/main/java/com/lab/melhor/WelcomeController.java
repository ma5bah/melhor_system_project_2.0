package com.lab.melhor;

import com.lab.melhor.App;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import BackEnd.CommonTask;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
public class WelcomeController implements Initializable{
    @FXML
    private AnchorPane main_pane;


    @FXML
    private void start_here() throws IOException {
        App.setRoot("login");
    }
    @FXML
    private void show_about()throws IOException{
        CommonTask.showAlert(Alert.AlertType.INFORMATION, "About us", "This app is developed for managing inventory smoothly.\nSamin and masbah made this application.");
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        
    }
}
