package com.lab.melhor;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

public class product_dashboard_controller implements Initializable {
    @FXML
    private VBox scrollPnItems = null;
    @FXML
    private JFXButton add_product_button;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Node[] nodes = new Node[10];
        for (int i = 0; i < nodes.length; i++) {
            try {

                final int j = i;
                ResourceBundle r = new ResourceBundle() {

                    @Override
                    protected Object handleGetObject(String key) {
                        if (key == "name") {
                            return "masbah" + Integer.toString(j);
                        }
                        throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                                       // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                    }

                    @Override
                    public Enumeration<String> getKeys() {
                        throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                                       // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                    }
                };
                nodes[i] = FXMLLoader.load(getClass().getResource("product_list_row.fxml"), r);
                // nodes[i].getController();
                // FXMLLoader.
                // nodes[i]
                // give the items some effect

                nodes[i].setOnMouseEntered(event -> {
                    nodes[j].setStyle("-fx-background-color : #0A0E3F");
                });
                nodes[i].setOnMouseExited(event -> {
                    nodes[j].setStyle("-fx-background-color : #02030A");
                });
                scrollPnItems.getChildren().add(nodes[i]);
            } catch (IOException e) {
                System.out.println("GONE TO");
                e.printStackTrace();
            }
        }

    }

    @FXML
    private void open_add_product_window() {
        // App.slide_from_right_side("product_details");
        try {
            Parent slide_show_panal = FXMLLoader.load(App.class.getResource("product_add_product.fxml"));
            AnchorPane root_panal = (AnchorPane) add_product_button.getScene().getRoot();
            
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
