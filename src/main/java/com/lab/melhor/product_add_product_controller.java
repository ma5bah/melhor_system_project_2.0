package com.lab.melhor;

import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class product_add_product_controller implements Initializable {
    @FXML
    private AnchorPane root_product_panal;
    @FXML
    private TextField product_name;
    @FXML
    private TextField product_price;
    @FXML
    private ComboBox<String> product_category;
    @FXML
    private Spinner<Integer> product_quantity;
    @FXML
    private Spinner<Integer> product_needed_space;
    @FXML
    private Spinner<Integer> product_storage_id;
    @FXML
    private DatePicker product_expiry_date;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            final int initialValue = 1;

            // Value factory.
            SpinnerValueFactory<Integer> valueFactory = //
                    new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100000, initialValue);
            product_quantity.setValueFactory(valueFactory);
            product_quantity.setPromptText("Quantity");
            // if (resources.getString("product_name") != null) {
            //     product_name.setText(resources.getString("product_name"));
            // }
            // if (resources.getString("product_name") != null) {
            //     product_name.setText(resources.getString("product_name"));
            // } 
            

        } catch (NullPointerException | MissingResourceException e) {
            e.getStackTrace();

        }
        final Timeline timeline = new Timeline();
        timeline.getKeyFrames().addAll(
                new KeyFrame(Duration.millis(500),
                        new KeyValue(root_product_panal.translateXProperty(), -300,
                                Interpolator.EASE_IN)));
        timeline.play();
        

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
    @FXML
    private void add_product(){
        if(product_expiry_date.getValue()!=null){
            
            System.out.println(Timestamp.valueOf(product_expiry_date.getValue().atTime(LocalTime.MIDNIGHT)));
        }
    }
}
