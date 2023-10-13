package com.example.laboratoire5;

import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class pageInscription extends Group {

    public pageInscription(){
        CSVFile csvFile = new CSVFile();
        this.setTranslateX(150);
        this.setTranslateY(50);

        VBox vbox = new VBox(7);

        Label labelPrenom = new Label("Prénom");

        TextField fieldPrenom = new TextField();
        fieldPrenom.setPromptText("Prénom");

        Label labelNomDeFamille = new Label("Nom de famille");

        TextField fieldNomDeFamille = new TextField();
        fieldNomDeFamille.setPromptText("Nom de famille");

        Label labelNomUtilisateur = new Label("Nom d'utilisateur");

        TextField fieldNomUtilisateur = new TextField();
        fieldNomUtilisateur.setPromptText("Nom d'utilisateur");

        Label labelMotdePasse = new Label("Mot de passe");

        PasswordField fieldMotDePasse = new PasswordField();
        fieldMotDePasse.setPromptText("Mot de passe");

        Label labelConfirmerMotDePasse = new Label("Confirmer mot de passe");

        PasswordField fieldConfirmerMotDePasse = new PasswordField();
        fieldConfirmerMotDePasse.setPromptText("Mot de passe");

        Label labelGenre = new Label("Genre");

        HBox hboxButton = new HBox(5);
        RadioButton hommeGenre = new RadioButton("Homme");
        RadioButton femmeGenre = new RadioButton("Femme");
        RadioButton autreGenre = new RadioButton("Autre");

        ToggleGroup toggleGroup = new ToggleGroup();
        hommeGenre.setToggleGroup(toggleGroup);
        femmeGenre.setToggleGroup(toggleGroup);
        autreGenre.setToggleGroup(toggleGroup);

        hboxButton.getChildren().addAll(hommeGenre, femmeGenre, autreGenre);

        Label labelAge = new Label("Âge");

        Spinner spinnerAge = new Spinner<>(0, 100, 0);

        CheckBox checkBoxConditions = new CheckBox("J'accepte les conditions d'utilisation");

        HBox hBox = new HBox(25);

        Button buttonInscription = new Button("S'incrire");
        Button buttonEffacer = new Button("Effacer");

        hBox.getChildren().addAll(buttonInscription, buttonEffacer);

        Label labelErreur = new Label("Formulaire d'inscription incomplet");
        labelErreur.setTextFill(Color.RED);
        labelErreur.setOpacity(0);

        vbox.getChildren().addAll(labelPrenom, fieldPrenom, labelNomDeFamille, fieldNomDeFamille, labelNomUtilisateur,
                fieldNomUtilisateur, labelMotdePasse, fieldMotDePasse, labelConfirmerMotDePasse,
                fieldConfirmerMotDePasse,labelGenre, hboxButton, labelAge,
                spinnerAge, checkBoxConditions, hBox, labelErreur);

        this.getChildren().addAll(vbox);

        buttonInscription.setOnAction( (e) -> {
            try {
                if (Objects.equals(fieldPrenom.getText(), "")){
                    labelErreur.setText("Veuillez entrer un prénom");
                    labelErreur.setOpacity(1);
                }
                else if (Objects.equals(fieldNomDeFamille.getText(), "")){
                    labelErreur.setText("Veuillez entrer un nom");
                    labelErreur.setOpacity(1);
                }
                else if (Objects.equals(fieldNomUtilisateur.getText(), "")){
                    labelErreur.setText("Veuillez entrer un nom d'utilisateur");
                    labelErreur.setOpacity(1);
                }
                else if (csvFile.verifierUsername(fieldNomUtilisateur.getText())){
                    labelErreur.setText("Veuillez entrer un autre nom d'utilisateur");
                    labelErreur.setOpacity(1);
                }
                else if (Objects.equals(fieldMotDePasse.getText(), "")){
                    labelErreur.setText("Veuillez entrer un mot de passe");
                    labelErreur.setOpacity(1);
                }
                else if (Objects.equals(fieldConfirmerMotDePasse.getText(), "")){
                    labelErreur.setText("Veuillez confirmer votre mot de passe");
                    labelErreur.setOpacity(1);
                }
                else if (!Objects.equals(fieldMotDePasse.getText(), fieldConfirmerMotDePasse.getText())) {
                    labelErreur.setText("Votre confirmation de mot passe n'est pas identique");
                    labelErreur.setOpacity(1);
                }
                else if (!checkBoxConditions.isSelected()){
                    labelErreur.setText("Veuillez acceptez les termes et conditions");
                    labelErreur.setOpacity(1);
                }
                else if (toggleGroup.getSelectedToggle() == null){
                    labelErreur.setText("Veuillez spécifiez votre genre");
                    labelErreur.setOpacity(1);
                }
                else if ((int) spinnerAge.getValue() == 0){
                    labelErreur.setText("Veuillez spécifiez votre âge");
                    labelErreur.setOpacity(1);
                }
                 else {
                    labelErreur.setOpacity(0);

                    String[] data = {fieldPrenom.getText(),fieldNomDeFamille.getText(),fieldNomUtilisateur.getText(),
                            fieldMotDePasse.getText(), spinnerAge.getValue().toString()};

                    List<String> dataList = new ArrayList<>(Arrays.asList(data));

                    Toggle selectedToggle = toggleGroup.getSelectedToggle();
                    if (selectedToggle.equals(hommeGenre)) {
                        dataList.add("H");
                    } else if (selectedToggle.equals(femmeGenre)) {
                        dataList.add("F");
                    } else if (selectedToggle.equals(autreGenre)) {
                        dataList.add("A");
                    }
                    csvFile.ajouter(dataList);

                    }

            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        });
        buttonEffacer.setOnAction( (e) -> {
            fieldPrenom.setText("");
            fieldNomDeFamille.setText("");
            fieldNomUtilisateur.setText("");
            fieldMotDePasse.setText("");
            fieldConfirmerMotDePasse.setText("");

            if (toggleGroup.getSelectedToggle() != null)
                toggleGroup.getSelectedToggle().setSelected(false);

            checkBoxConditions.setSelected(false);
            spinnerAge.getValueFactory().setValue(0);
        });

    }
}
