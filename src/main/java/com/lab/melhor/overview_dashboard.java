package com.lab.melhor;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import java.net.URL;
import java.util.ResourceBundle;

import BackEnd.db;
import BackEnd.database_schema.Order;

public class overview_dashboard implements Initializable {

    @FXML
    private Label inventory_name;
    @FXML
    private Label total_order_count;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        inventory_name.setText(db.getInventory().getName() + " Overview");
        total_order_count.setText(String.valueOf(Order.count_all_order()));
    }

}
