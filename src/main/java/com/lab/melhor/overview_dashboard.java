package com.lab.melhor;

import BackEnd.database_schema.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import java.net.URL;
import java.util.ResourceBundle;

import BackEnd.db;

public class overview_dashboard implements Initializable {

    @FXML
    private Label inventory_name;
    @FXML
    private Label total_order_count;
    @FXML
    private Label pending_order_count;
    @FXML
    private Label total_supplier_count;
    @FXML
    private Label total_product_count;
    @FXML
    private Label total_storage_space;
    @FXML
    private Label total_employee_count;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        inventory_name.setText(db.getInventory().getName() + " Overview");
        total_order_count.setText(String.valueOf(Order.count_all_order()));
        pending_order_count.setText(String.valueOf(Order.count_pending_order()));
        total_product_count.setText(String.valueOf(Product.count_product()));
        total_employee_count.setText(String.valueOf(Employee.count_employee()));
        total_supplier_count.setText(String.valueOf(Supplier.count_supplier()));
        total_storage_space.setText(String.valueOf(Storage.count_total_space()));
    }

}
