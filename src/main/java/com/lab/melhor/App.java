package com.lab.melhor;

import BackEnd.db;
import BackEnd.database_schema.Inventory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Enumeration;
import java.util.ResourceBundle;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    AnchorPane main_window;
    // public static login_registration state;

    @Override
    public void start(Stage stage) throws IOException {

        scene = new Scene(loadFXML("welcome"));
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    static void set_root_with_resource(String fxml, ResourceBundle resourses) throws IOException {
        scene.setRoot(load_fxml_with_resource(fxml, resourses));
    }

    static void slide_from_right_side(Parent root_panal, String fxml) throws IOException {
        // Parent slide_show_panal = load_fxml_with_resource(fxml);
        // root_panal.getChildre
        // AnchorPane container = new AnchorPane();
        // slide_show_panal.setLayoutX(960);
        // container.getChildren().addAll(scene.getRoot(), slide_show_panal);
        // scene.set_root_with_resource(container);
        // System.out.println(scene.getRoot().getScene());

    }

    static void login_registration(Boolean login_state) throws IOException {
        ResourceBundle state = new ResourceBundle() {
            @Override
            protected Object handleGetObject(String key) {
                if ("login_state".equals(key)) {

                    if (login_state == true) {
                        return Boolean.TRUE;
                    }
                }
                return Boolean.FALSE;
            }

            @Override
            public Enumeration<String> getKeys() {
                // TODO Auto-generated method stub
                return null;
            }

        };
        set_root_with_resource("login_registration", state);
    }

    private static Parent load_fxml_with_resource(String fxml, ResourceBundle resourses) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"), resourses);
        return fxmlLoader.load();
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        // db.getConnections();
        // db.set_product_data();
        // db.get_product_data();
        // state = new login_registration();
        db.init_db();

        launch();
        db.closeConnections();
    }

}
