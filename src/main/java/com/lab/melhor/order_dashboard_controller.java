package com.lab.melhor;


import BackEnd.database_schema.Order;
import BackEnd.database_schema.Product;
import javafx.fxml.Initializable;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

public class order_dashboard_controller implements Initializable {

    @FXML
    private Label item_name;
    @FXML
    private VBox pnItems;
@FXML
private JFXButton generated_order;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        show_order(Order.get_all_order());
    }
    void show_order(ArrayList<Order> list){
         if(list==null){
             return;
         }
        Node[] nodes = new Node[list.size()];
        for (int i = 0; i < list.size(); i++) {
            try {
                final int j = i;
                ResourceBundle r = new ResourceBundle() {
                    @Override
                    protected Object handleGetObject(String key) {
                        if (key == "order_id") {
                            return String.valueOf(list.get(j).getId());
                        }
                        if (key == "order_status") {
                            return list.get(j).getStatus();
                        }
                        if (key == "order_type") {
                            return list.get(j).getType();
                        }

                        throw new UnsupportedOperationException("Not enumeration supported yet.");
                    }

                    @Override
                    public Enumeration<String> getKeys() {
                        throw new UnsupportedOperationException("Not enumeration supported yet.");
                    }
                };
                nodes[i] = FXMLLoader.load(getClass().getResource("order_list_row.fxml"), r);
                // give the items some effect
                nodes[i].setOnMouseEntered(event -> {
                    nodes[j].setStyle("-fx-background-color : #0A0E3F");
                });
                nodes[i].setOnMouseExited(event -> {
                    nodes[j].setStyle("-fx-background-color : #02030A");
                });
                pnItems.getChildren().add(nodes[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void get_all_order(){

    }
    @FXML
    private void get_generated_order() {
        try {
            Parent slide_show_panal = FXMLLoader.load(App.class.getResource("order_generated_order.fxml"));
            AnchorPane root_panal = (AnchorPane) generated_order.getScene().getRoot();

            slide_show_panal.setLayoutX(960);
            ArrayList<Node> item = new ArrayList<>();
            for (Node iterable_element : root_panal.getChildren()) {
                if (iterable_element.getId() == null)
                    continue;
                if (iterable_element.getId().compareTo("root_product_panal") == 0
                        || iterable_element.getId().compareTo("invisible_panal_for_slide_show") == 0) {
                    item.add(iterable_element);
                }
            }
            AnchorPane invisible_panal = new AnchorPane();
            invisible_panal.setPrefHeight(600);
            invisible_panal.setPrefHeight(660);

            invisible_panal.setId("invisible_panal_for_slide_show");
            invisible_panal.setStyle("-fx-background-color:#ffffff;");
            invisible_panal.setOnMouseClicked((ev) -> {
                System.out.println(ev);
            });

            root_panal.getChildren().removeAll(item);

            root_panal.getChildren().addAll(invisible_panal, slide_show_panal);
            System.out.println(root_panal.getChildren().get(2).getLayoutX());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
