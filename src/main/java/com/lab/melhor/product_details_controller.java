package com.lab.melhor;

import com.lab.melhor.App;
import java.io.IOException;
import java.net.URL;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
public class product_details_controller implements Initializable{
    @FXML
    private Text name_text;
    @FXML
    private Label description_text;

    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            if (resources.getString("name_of_product") != null) {
                name_text.setText(resources.getString("name_of_product"));
            }else{
                name_text.setText("Product Name is not available");
            }
            if (resources.getString("description_of_product") != null) {
                description_text.setText(resources.getString("description_of_product"));
            }else{
                name_text.setText("Product Name is not available");
            }

        } catch (NullPointerException | MissingResourceException e) {

        }
       
        
    }
}
