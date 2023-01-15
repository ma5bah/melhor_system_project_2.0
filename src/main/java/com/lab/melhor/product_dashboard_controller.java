package com.lab.melhor;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;

import BackEnd.database_schema.Product;

public class product_dashboard_controller implements Initializable {
    @FXML
    private VBox scrollPnItems = null;
    @FXML
    private JFXButton add_product_button;
    @FXML
    private Pane product_category_pane;
    @FXML
    private Label product_category_label;
    TextField search_filter_field;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        search_filter_field = new TextField();

        search_filter_field.setStyle("-fx-background-color: #02030A");
        search_filter_field.setStyle("-fx-border-color:  #B7C3D7");
        search_filter_field.setStyle("-fx-border-radius:  2em");
        // search_filter_field.setOnMouseExited(evt->{
        // search_by_category_on_mouse_exited();
        // });
        ArrayList<Product> list = Product.get_all_product();
        Node[] nodes = new Node[list.size()];
        for (int i = 0; i < list.size(); i++) {
            try {

                final int j = i;
                ResourceBundle r = new ResourceBundle() {

                    @Override
                    protected Object handleGetObject(String key) {
                        if (key == "item_name") {
                            return list.get(j).getName();
                        }
                        if (key == "item_category") {
                            return list.get(j).getCategory();
                        }
                        if (key == "item_price") {
                            return list.get(j).getPrice();
                        }
                        if (key == "item_expiry_date") {
                            return new Date(list.get(j).getExpiryDate().getTime());
                        }
                        if (key == "item_quantity") {
                            return list.get(j).getQuantity();
                        }
                        throw new UnsupportedOperationException("Not enumeration supported yet.");
                    }

                    @Override
                    public Enumeration<String> getKeys() {
                        throw new UnsupportedOperationException("Not enumeration supported yet.");
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
                e.printStackTrace();
            }
        }

    }

    @FXML
    public void search_by_category_on_mouse_exited() {
        String searched_text = search_filter_field.getText();

        if (product_category_pane.getChildren().contains(search_filter_field)) {
            product_category_pane.getChildren().remove(search_filter_field);
            product_category_label.setText(searched_text);
            // product_category_pane.getChildren().remove(search_filter_field);
        }
        // product_category_pane.getChildren().add(new
        // TextField(search_filter_field.getText()));
        // product_category_pane.getChildren().remove(search_filter_field);

        // product_category_pane.getChildren().remove(search_filter_field);
    }

    @FXML
    public void search_by_category_on_mouse_entered() {
        System.out.println(product_category_pane.getChildren().contains(search_filter_field));
        if (!product_category_pane.getChildren().contains(search_filter_field)) {
            // product_category_pane.getChildren().remove(product_category_label);
            product_category_pane.getChildren().add(search_filter_field);
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
