package com.lab.melhor;

import BackEnd.db;
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

    static Scene get_login_window() throws IOException {
        ResourceBundle state = new ResourceBundle() {

            @Override
            protected Object handleGetObject(String key) {
                if (key == "login_state")
                    return Boolean.TRUE;
                return Boolean.FALSE;
            }

            @Override
            public Enumeration<String> getKeys() {
                // TODO Auto-generated method stub
                return null;
            }

        };
        Scene login_window = new Scene(FXMLLoader.load(App.class.getResource("login.fxml"), state), 960, 600);
        return login_window;
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        // db.getConnections();
        // db.set_product_data();
//        db.get_product_data();
        // state = new login_registration();
         launch();
    }

}
