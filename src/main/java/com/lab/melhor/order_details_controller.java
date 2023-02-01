package com.lab.melhor;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import BackEnd.database_schema.ProductAndOrder;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class order_details_controller implements Initializable {
    @FXML
    private AnchorPane root_product_panal;
    @FXML
    private Text name_text;
    @FXML
    private Label description_text;
    @FXML
    private ImageView product_image;
    @FXML
    private VBox pnItems;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<ProductAndOrder>list=ProductAndOrder.get_ordered_product(Long.parseLong(resources.getString("order_id")));
        Node[] nodes = new Node[list.size()];
        for (int i = 0; i < nodes.length; i++) {
            try {

                final int j = i;
                ResourceBundle r=new ResourceBundle() {
                    
                    @Override
                    protected Object handleGetObject(String key) {
                        if(key=="order_id"){
                            return String.valueOf( list.get(j).getOrderId());
                        }
                        if(key=="product_id"){
                            return String.valueOf(list.get(j).getProductId());
                        }
                        if(key=="product_quantity"){
                            return String.valueOf(list.get(j).getProductQuantity());
                        }
                        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                    }

                    @Override
                    public Enumeration<String> getKeys() {
                        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                    }
                };
                nodes[i] = FXMLLoader.load(getClass().getResource("order_cart_list_row.fxml"),r);


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

        final Timeline timeline = new Timeline();
        timeline.getKeyFrames().addAll(
                new KeyFrame(Duration.millis(500),
                        new KeyValue(root_product_panal.translateXProperty(), -300,
                                Interpolator.EASE_IN)));
        timeline.play();
        
        // for (Node iterable_element : root_panal.getChildren()) {
        //     if (iterable_element.getId() == null)
        //         continue;
        //     if (iterable_element.getId().compareTo("root_product_panal") == 0) {
        //         iterable_element.onMouseClickedProperty();
        //     }
        // }
        // System.out.println(root_product_panal.getScene());
    }

    @FXML
    private void close_me() {
        AnchorPane root_panal = (AnchorPane) root_product_panal.getScene().getRoot();
        // for (Node iterable_element : root_panal.getChildren()) {
        //     if (iterable_element.getId() == null)
        //         continue;
        //     if (iterable_element.getId().compareTo("root_product_panal") == 0) {
                
        //     }
        // }
        final Timeline timeline = new Timeline();
        timeline.getKeyFrames().addAll(
                new KeyFrame(Duration.millis(750),
                        new KeyValue(root_product_panal.translateXProperty(), 300,
                                Interpolator.EASE_OUT)));
        timeline.play();
        timeline.setOnFinished((ev) -> {
            root_panal.getChildren().remove(root_product_panal);
        });
    }
}
