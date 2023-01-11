package com.lab.melhor;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.ResourceBundle;

import BackEnd.db;

public class overview_dashboard implements Initializable {
    @FXML
    private VBox pnItems = null;
    @FXML 
    private Label inventory_name;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        inventory_name.setText(db.getInventory().getName()+ " Overview");
        Node[] nodes = new Node[10];
        for (int i = 0; i < nodes.length; i++) {
            try {

                final int j = i;
                ResourceBundle r=new ResourceBundle() {
                    
                    @Override
                    protected Object handleGetObject(String key) {
                        if(key=="name"){
                            return "masbah"+Integer.toString(j);
                        }
                        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                    }

                    @Override
                    public Enumeration<String> getKeys() {
                        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                    }
                };
                nodes[i] = FXMLLoader.load(getClass().getResource("product_list_row.fxml"),r);
//                nodes[i].getController();
//                FXMLLoader.
//                nodes[i]
                // give the items some effect

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

    }

}
