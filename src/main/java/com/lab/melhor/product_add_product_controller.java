package com.lab.melhor;

import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import BackEnd.database_schema.Product;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
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
    private Spinner<Double> product_price;
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


            ObservableList<String> category = FXCollections.observableArrayList();
            category.addAll(Product.get_category());
            product_category.setItems(category);
            product_quantity.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,10000000));
            product_price.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(1, 100000));
            product_needed_space.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,10000000));
            product_storage_id.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,10000000));
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

    private boolean check_product_details(){
        return product_category.getValue()!=""||product_price.getValue().doubleValue()>0||product_quantity.getValue().intValue()>0||product_needed_space.getValue().intValue()>0||product_name.getText()!=""||product_name.getText()!=null;
    }
    @FXML
    private void add_product(){
        if(check_product_details()){
            Product.create_product(product_name.getText(), product_category.getValue(), product_price.getValue(), product_needed_space.getValue().intValue(), product_quantity.getValue().intValue(),product_expiry_date.getValue().atStartOfDay() , product_storage_id.getValue().intValue());
        }
    }
}
