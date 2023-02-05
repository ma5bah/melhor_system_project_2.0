package com.lab.melhor;

import BackEnd.database_schema.ProductAndOrder;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;

import BackEnd.db;
import BackEnd.database_schema.Order;
import BackEnd.database_schema.Product;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.css.Style;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import javafx.util.Pair;

public class order_generated_order_controller implements Initializable {
    @FXML
    private AnchorPane root_generated_order_panal;
    @FXML
    private Label item_name;
    @FXML
    HBox item_box;
    @FXML
    private VBox pnItems;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ArrayList<Pair<Long, Long>> product_list=db.tmp_order.get_product_in_order();
        Node[] nodes = new Node[product_list.size()];
        for (int i = 0; i < product_list.size(); i++) {
            try {
                Product tmp_product=Product.get_product_by_id(product_list.get(i).getKey().longValue());
                System.out.println(product_list.get(i).getKey().longValue());
                final int j = i;
                ResourceBundle r=new ResourceBundle() {
                    
                    @Override
                    protected Object handleGetObject(String key) {
                        if(key=="product_id"){
                            return tmp_product.getName();
                        }
                        if(key=="product_quantity"){
                            return product_list.get(j).getValue().toString();
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
                        new KeyValue(root_generated_order_panal.translateXProperty(), -300,
                                Interpolator.EASE_IN)));
        timeline.play();
    }

@FXML
public void add_to_cart(){
    long order_id = Order.create_order("buy", "none");
    if(order_id!=-1){

    db.tmp_order.get_product_in_order().forEach(data->{
        ProductAndOrder.create_ordered_product(order_id,data.getKey(),data.getValue());
    });
    }

}


@FXML
    private void close_me() {
        AnchorPane root_panal = (AnchorPane) root_generated_order_panal.getScene().getRoot();
        // for (Node iterable_element : root_panal.getChildren()) {
        //     if (iterable_element.getId() == null)
        //         continue;
        //     if (iterable_element.getId().compareTo("root_generated_order_panal") == 0) {
                
        //     }
        // }
        final Timeline timeline = new Timeline();
        timeline.getKeyFrames().addAll(
                new KeyFrame(Duration.millis(750),
                        new KeyValue(root_generated_order_panal.translateXProperty(), 300,
                                Interpolator.EASE_OUT)));
        timeline.play();
        timeline.setOnFinished((ev) -> {
            root_panal.getChildren().remove(root_generated_order_panal);
        });
    }
}
