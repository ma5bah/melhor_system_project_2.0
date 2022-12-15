package com.lab.melhor;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class dashboard_controller implements Initializable {
    @FXML
    private AnchorPane root_anchor_pane;
    @FXML
    private StackPane main_stack_pane;
    @FXML
    private VBox pnItems = null;
    @FXML
    private Button btnOverview;

    @FXML
    private Button btnOrders;

    @FXML
    private Button btnCustomers;

    @FXML
    private Button btnMenus;

    @FXML
    private Button btnPackages;

    @FXML
    private Button btnSettings;

    @FXML
    private Button btnSignout;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Pane new_panal = new FXMLLoader(getClass().getResource("overview_dashboard.fxml")).load();
            main_stack_pane.getChildren().addAll(new_panal);

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Node[] nodes = new Node[10];
        // for (int i = 0; i < nodes.length; i++) {
        // try {
        //
        // final int j = i;
        // nodes[i] = FXMLLoader.load(getClass().getResource("Item.fxml"));
        //
        // // give the items some effect
        //
        // nodes[i].setOnMouseEntered(event -> {
        // nodes[j].setStyle("-fx-background-color : #0A0E3F");
        // });
        // nodes[i].setOnMouseExited(event -> {
        // nodes[j].setStyle("-fx-background-color : #02030A");
        // });
        // pnItems.getChildren().add(nodes[i]);
        // } catch (IOException e) {
        // e.printStackTrace();
        // }
        // }

    }

    public void handleClicks(ActionEvent actionEvent) throws IOException {
        if (actionEvent.getSource() == btnCustomers) {
            // pnlCustomer.setStyle("-fx-background-color : #1620A1");
            // pnlCustomer.toFront();
        }
        if (actionEvent.getSource() == btnMenus) {
            // pnlMenus.setStyle("-fx-background-color : #53639F");
            // pnlMenus.toFront();
        }
        if (actionEvent.getSource() == btnOverview) {
            // pnlOverview.setStyle("-fx-background-color : #02030A");
            // pnlOverview.toFront();
        }
        if (actionEvent.getSource() == btnOrders) {
            // root_anchor_pane.getChildren().removeAll();
            // root_anchor_pane.getChildren().remove(main_stack_pane.getChildren());
            // main_stack_pane.getChildren().remove(pnlOverview);
            Pane new_panal = new FXMLLoader(getClass().getResource("detail.fxml")).load();
            // Scene new_pnl=new Scene(new_panal);
            main_stack_pane.getChildren().addAll(new_panal);
            System.out.println("null");
            // pnlOrders.setStyle("-fx-background-color : #464F67");
            // pnlOrders.toFront();
        }
    }
}
