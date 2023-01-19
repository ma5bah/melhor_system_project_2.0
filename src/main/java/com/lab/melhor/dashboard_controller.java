package com.lab.melhor;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;

import BackEnd.CommonTask;
import BackEnd.db;
import BackEnd.database_schema.Product;

public class dashboard_controller implements Initializable {
    @FXML
    private AnchorPane root_anchor_pane;
    @FXML
    private StackPane main_stack_pane;

    @FXML
    private Label employee_name;
    @FXML
    private ImageView employee_dp;

    @FXML
    private Button btnOverview;

    @FXML
    private Button btnOrders;

    @FXML
    private Button btnProducts;

    @FXML
    private Button btnPanel;

    @FXML
    private Button btnProfile;

    @FXML
    private Button btnSettings;

    @FXML
    private Button btnSignout;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            if (!db.isEmployeeAvailable()) {
                App.setRoot("login_registration");
                return;
            }
            System.out.println(Product.get_all_product().size());
            employee_name.setText(db.getEmployee().getName());
            employee_dp.setImage(new Image(new File(db.getEmployee().getDp()).getAbsolutePath()));
            // System.out.println(db.getEmployee().getDp());
            // System.out.println(new
            // File("src/main/resources/assets/picture/default.png").getAbsoluteFile());
            Pane new_panal = FXMLLoader.load(App.class.getResource("overview_dashboard.fxml"));
            // FXMLLoader.load(App.class.getResource("overview_dashboard.fxml"));
            main_stack_pane.getChildren().addAll(new_panal);

        } catch (IOException e) {
            CommonTask.log(Level.SEVERE, e, e.getLocalizedMessage());
        }

    }

    private void clear_main_stack_pane() {
        try {
            main_stack_pane.getChildren().remove(0);

        } catch (Exception error) {
            System.out.println(" ERROR : " + error);
        }

    }
    // @FXML
    // public void logout(){
    // try {

    // } catch (IOException e) {
    // CommonTask.log(Level.SEVERE, e, e.getMessage());
    // }
    // }
    public void handleClicks(ActionEvent actionEvent) throws IOException {
        // for (Node iterable_element : main_stack_pane.getChildren()) {
        // System.out.print(iterable_element + " ");
        // }
        // System.out.println();
        // pnlMenus.setStyle("-fx-background-color : #53639F");
        // pnlMenus.toFront();
        if (actionEvent.getSource() == btnSignout) {
            db.logout();
            App.setRoot("login_registration");
        }

        if (actionEvent.getSource() == btnProducts) {

            Pane new_panal = new FXMLLoader(getClass().getResource("product_dashboard.fxml")).load();
            clear_main_stack_pane();
            main_stack_pane.getChildren().add(new_panal);
        }
        if (actionEvent.getSource() == btnPanel) {

            Pane new_panal = new FXMLLoader(getClass().getResource("panel_dashboard.fxml")).load();
            clear_main_stack_pane();
            main_stack_pane.getChildren().add(new_panal);

        }
        if (actionEvent.getSource() == btnOverview) {

            Pane new_panal = new FXMLLoader(getClass().getResource("overview_dashboard.fxml")).load();
            clear_main_stack_pane();
            main_stack_pane.getChildren().add(new_panal);

        }
        if (actionEvent.getSource() == btnOrders) {

            Pane new_panal = new FXMLLoader(getClass().getResource("order_dashboard.fxml")).load();
            clear_main_stack_pane();
            main_stack_pane.getChildren().add(new_panal);

            // Scene new_pnl=new Scene(new_panal);

            // pnlOrders.setStyle("-fx-background-color : #464F67");
            // pnlOrders.toFront();
        }
        if (actionEvent.getSource() == btnProfile) {
            System.out.println("done");
            Pane new_panal = new FXMLLoader(getClass().getResource("profile_dashboard.fxml")).load();
            clear_main_stack_pane();
            main_stack_pane.getChildren().add(new_panal);

        }
    }
}
