package com.lab.melhor;

import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class item_dashboard_controller implements Initializable {

    @FXML
    private Label item_name;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        System.out.print("From Item : ");
        item_name.setText(resources.getString("name"));
//        System.out.println(resources.getString("name"));
    }

}
