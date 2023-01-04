package com.lab.melhor;

import java.net.URL;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class product_add_product_controller implements Initializable {
    @FXML
    private AnchorPane root_product_panal;
    @FXML
    private Text name_text;
    @FXML
    private Label description_text;
    @FXML
    private ImageView product_image;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            if (resources.getString("name_of_product") != null) {
                name_text.setText(resources.getString("name_of_product"));
            } else {
                name_text.setText("Product Name is not available");
            }
            if (resources.getString("description_of_product") != null) {
                description_text.setText(resources.getString("description_of_product"));
            } else {
                name_text.setText("Product Name is not available");
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
