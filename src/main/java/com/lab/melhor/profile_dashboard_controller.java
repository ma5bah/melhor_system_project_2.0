package com.lab.melhor;

import BackEnd.db;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.util.ResourceBundle;

public class profile_dashboard_controller implements Initializable {

    @FXML
    private Text name;
    @FXML
    private Text age;
    @FXML
    private Label bio;
    @FXML
    private Text role;
@FXML
private ImageView image;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    name.setText(db.getEmployee().getName());
    age.setText(String.valueOf( db.getEmployee().getId()));
    role.setText(db.getEmployee().getRole());
    bio.setText(db.getEmployee().getName()+db.getEmployee().getEmail()+db.getEmployee().getPassword());
//System.out.println(Path.of(new File(db.getEmployee().getDp()).getAbsolutePath()).toUri());
        image.setImage(new Image(Path.of(new File(db.getEmployee().getDp()).getAbsolutePath()).toUri().toString()));
    }

}
