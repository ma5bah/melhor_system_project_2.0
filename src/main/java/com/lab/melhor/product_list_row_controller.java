package com.lab.melhor;

import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;

import javafx.css.Style;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class product_list_row_controller implements Initializable {
    private int item_id;
    @FXML
    private Label item_name;
    @FXML
    private Label item_expiry_date;
    @FXML
    private Label item_category;
    @FXML
    private Label item_price;
    @FXML
    private Button item_quantity;

    @FXML
    HBox item_box;
    ResourceBundle saved_resources;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // System.out.print("From Item : ");
        item_name.setText(resources.getString("item_name"));
        // item_name.setText(resources.getString("price"));
        // item_name.setText(resources.getString("category"));
        // item_name.setText(resources.getString("available_quantity"));
        // this.item_id=Integer.parseInt( resources.getString("item_id"));
        this.saved_resources=resources;

    }

    @FXML
    private void show_product() {
        try {
            // ResourceBundle state = new ResourceBundle() {
            //     @Override
            //     protected Object handleGetObject(String key) {
            //         if (key == "item_name") {
            //             return saved_resources.getString("item_name");
            //         }
            //         if (key == "item_id") {
            //             return saved_resources.getString("item_id");
            //         }
            //         if (key == "item_category") {
            //             return saved_resources.getString("item_category");
            //         }
            //         if (key == "item_price") {
            //             return saved_resources.getString("item_price");
            //         }
            //         if (key == "item_expiry_date") {
            //             return saved_resources.getString("item_expiry_date");
            //         }
            //         if (key == "item_quantity") {
            //             return saved_resources.getString("item_quantity");
            //         }
                    
            //         throw new UnsupportedOperationException("Not enumeration supported yet.");
            //     }
    
            //     @Override
            //     public Enumeration<String> getKeys() {
            //         return null;
            //     }
    
            // };
            
            Parent slide_show_panal = FXMLLoader.load(App.class.getResource("product_details.fxml"),this.saved_resources);
            AnchorPane root_panal = (AnchorPane) item_box.getScene().getRoot();
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
            // invisible_panal.setLayoutX(0);
            // invisible_panal.setLayoutY(0);
            invisible_panal.setId("invisible_panal_for_slide_show");
            invisible_panal.setStyle("-fx-background-color:#ffffff;");
            invisible_panal.setOnMouseClicked((ev) -> {
                System.out.println(ev);
            });

            // new EventHandler<Event>() {

            // @Override
            // public void handle(Event event) {

            // AnchorPane temp_root_panal = event.getSource() .getScene().getRoot();
            // System.out.println("from mouse event");;
            // ArrayList<Node> temp_item = new ArrayList<>();
            // for (Node iterable_element : root_panal.getChildren()) {
            // if (iterable_element.getId() == null)
            // continue;
            // if (iterable_element.getId().compareTo("root_product_panal") == 0
            // || iterable_element.getId().compareTo("invisible_panal_for_slide_show") == 0)
            // {
            // temp_item.add(iterable_element);
            // }
            // }
            // temp_root_panal.getChildren().removeAll(temp_item);
            // }
            // });
            root_panal.getChildren().removeAll(item);

            root_panal.getChildren().addAll(invisible_panal, slide_show_panal);
            // System.out.println(root_panal.getChildren().get(2).getLayoutX());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
