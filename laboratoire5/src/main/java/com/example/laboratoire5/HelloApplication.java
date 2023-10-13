package com.example.laboratoire5;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.FileNotFoundException;



public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws FileNotFoundException {
        stage.setMinWidth(500);
        stage.setMinHeight(500);

        
        stage.setScene(new Scene(new pageConnexion()));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}