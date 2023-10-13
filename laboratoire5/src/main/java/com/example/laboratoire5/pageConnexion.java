package com.example.laboratoire5;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Collection;


public class pageConnexion extends Group {
    public pageConnexion(){
        CSVFile csvFile = new CSVFile();
        this.setTranslateX(155);
        this.setTranslateY(180);

        VBox vbox = new VBox(5);

        Label labelUtilisateur = new Label("Nom d'utilisateur");
        Label labelMotDePasse = new Label("Mot de passe");

        TextField fieldUtilisateur = new TextField();
        fieldUtilisateur.setPromptText("Nom d'utilisateur");

        PasswordField fieldMotDePasse = new PasswordField();
        fieldMotDePasse.setPromptText("Mot de passe");


        Button buttonConnexion = new Button("Se connecter");
        Button buttonInscription = new Button("S'inscrire");
        HBox hbox = new HBox(20);
        hbox.getChildren().addAll(buttonConnexion, buttonInscription);

        vbox.getChildren().addAll(labelUtilisateur, fieldUtilisateur, labelMotDePasse, fieldMotDePasse, hbox);
        vbox.setMinHeight(320);
        vbox.setPrefWidth(200);
        vbox.setFillWidth(true);

        buttonConnexion.setOnAction( (e) -> {
            String[] data = {fieldUtilisateur.getText(), fieldMotDePasse.getText()};
            try {
                if (csvFile.verifierUtilisateur(data)) {
                    this.getChildren().removeAll(vbox);
                    this.getChildren().addAll(new pageContenu());
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        buttonInscription.setOnAction( (e) -> {
            this.getChildren().removeAll(vbox);
            this.getChildren().addAll(new pageInscription());
            this.setTranslateY(-25);
            this.setTranslateX(0);

            Button buttonRetour = new Button("Retour");
            buttonRetour.setTranslateY(465);
            buttonRetour.setTranslateX(320);

            this.getChildren().addAll(buttonRetour);
            buttonRetour.setOnAction( (u) -> {
                this.getChildren().clear();
                this.getChildren().addAll(vbox);
                this.setTranslateX(155);
                this.setTranslateY(180);
            });
        });

        this.getChildren().addAll(vbox);
    }



}
