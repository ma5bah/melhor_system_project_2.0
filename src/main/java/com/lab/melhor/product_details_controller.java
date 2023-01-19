package com.lab.melhor;

import java.net.URL;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import BackEnd.db;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class product_details_controller implements Initializable {
    @FXML
    private AnchorPane root_product_panal;
    @FXML
    private Text name;
    @FXML
    private Text category;
    @FXML
    private Text price;
    @FXML
    private Text expiry_date;
    @FXML
    private Text available_quantity;
    @FXML
    private Spinner<Integer> product_quantity;

    @FXML
    private ImageView product_image;
    ResourceBundle state;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        state = resources;
        product_quantity.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10000000));
        // System.out.println(resources);
        try {
            System.out.println("q " + resources.getString("item_expiry_date"));
            if (resources.getString("item_name") != null) {
                name.setText("Name: " + resources.getString("item_name"));
            }
            if (resources.getString("item_price") != null) {
                price.setText("Price: " + resources.getString("item_price"));
            }
            if (resources.getString("item_category") != null) {
                category.setText("Category: " + resources.getString("item_category"));
            }
            if (resources.getString("item_quantity") != null) {
                available_quantity.setText("Available: " + resources.getString("item_quantity") + " piece");
            }
            if (resources.getString("item_expiry_date") != null) {
                expiry_date.setText("Wil be expired: " + resources.getString("item_expiry_date"));
            }

        } catch (NullPointerException | MissingResourceException e) {
            e.getStackTrace();

        }
        final Timeline timeline = new Timeline();
        timeline.getKeyFrames().addAll(
                new KeyFrame(Duration.millis(500),
                        new KeyValue(root_product_panal.translateXProperty(), -300,
                                Interpolator.EASE_IN)));
        timeline.play();

        // for (Node iterable_element : root_panal.getChildren()) {
        // if (iterable_element.getId() == null)
        // continue;
        // if (iterable_element.getId().compareTo("root_product_panal") == 0) {
        // iterable_element.onMouseClickedProperty();
        // }
        // }
        // System.out.println(root_product_panal.getScene());
    }

    @FXML
    private void add_to_cart() {
        // System.out.println( +"  "+ String.valueOf( product_quantity.getValue().intValue()));
        db.tmp_order.add_product_in_order(Long.parseLong(state.getString("item_id")), product_quantity.getValue().longValue());
    }

    @FXML
    private void close_me() {
        AnchorPane root_panal = (AnchorPane) root_product_panal.getScene().getRoot();
        // for (Node iterable_element : root_panal.getChildren()) {
        // if (iterable_element.getId() == null)
        // continue;
        // if (iterable_element.getId().compareTo("root_product_panal") == 0) {

        // }
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
