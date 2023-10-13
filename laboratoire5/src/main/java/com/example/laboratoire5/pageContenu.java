package com.example.laboratoire5;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.VBox;

public class pageContenu extends Group {

    public pageContenu() {
        this.setTranslateX(100);
        ProgressIndicator progressIndicator = new ProgressIndicator();
        progressIndicator.setTranslateY(50);
        progressIndicator.setTranslateX(-30);
        Label labelChargement = new Label("Chargement du contenu");
        labelChargement.setTranslateY(150);
        labelChargement.setTranslateX(-65);

        this.getChildren().addAll(progressIndicator, labelChargement);
    }
}
