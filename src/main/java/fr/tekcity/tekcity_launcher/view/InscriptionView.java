package fr.tekcity.tekcity_launcher.view;

import fr.tekcity.tekcity_launcher.controller.InscriptionController;
import fr.tekcity.tekcity_launcher.Main;
import fr.tekcity.tekcity_launcher.functions.InitBackgroundView;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.enums.FloatMode;
import javafx.animation.FadeTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;



public class InscriptionView {

    private Stage stage;
    private StackPane root;

    public InscriptionView(Stage stage, StackPane root){

        // On créer ci-dessous l'inteface d"inscription
        //-------------------------------------------------------------------------

        // On créer un Pane pour faire une boite de couleur noir pour le formulaire
        Pane inscriptionBox = new Pane();
        inscriptionBox.setBackground(new Background(new BackgroundFill(Color.rgb(7, 8, 7), CornerRadii.EMPTY, Insets.EMPTY)));
        inscriptionBox.setMaxHeight(500);
        inscriptionBox.setMaxWidth(400);

        // Ombre de loginBox
        InnerShadow shadow_box = new InnerShadow();
        shadow_box.setColor(Color.LIGHTBLUE);
        shadow_box.setRadius(20);
        shadow_box.setChoke(0.5);

        // On l'applique
        inscriptionBox.setEffect(shadow_box);

        // Ombre pour du texte
        InnerShadow shadow_text = new InnerShadow();
        shadow_text.setColor(Color.LIGHTBLUE);
        shadow_text.setRadius(5);
        shadow_text.setChoke(0.5);

        // On créer une animation d'apparition
        FadeTransition fade_inscription = new FadeTransition(Duration.millis(500), inscriptionBox);
        fade_inscription.setFromValue(0.8);
        fade_inscription.setToValue(1.0);
        fade_inscription.play();

        // On créer une bordure au Pane
        Border border = new Border(new BorderStroke(Color.DARKGRAY, BorderStrokeStyle.SOLID, null, new BorderWidths(3)));
        inscriptionBox.setBorder(border);

        // On charge la/les polices
        Font tektur = Font.loadFont(this.getClass().getResourceAsStream("/fonts/Tektur-Bold.ttf"), 60);

        // On créer un titre au Panel
        Label titre = new Label("Inscription");
        titre.setTextFill(Color.WHITE);
        titre.setFont(tektur);
        // Ombre du titre
        titre.setEffect(shadow_text);

        // Boite pour le titre
        VBox boxTitre = new VBox(15, titre);
        boxTitre.setAlignment(Pos.TOP_CENTER);
        boxTitre.setPrefSize(400, 400);

        inscriptionBox.getChildren().add(boxTitre);

        // Créer un formulaire d"inscription
        GridPane inscriptionGrid = new GridPane();
        inscriptionGrid.setHgap(10);
        inscriptionGrid.setVgap(10);
        inscriptionGrid.setMaxHeight(400);
        inscriptionGrid.setMaxWidth(400);

        // On créer un message d'erreur pour le formulaire d'inscription
        final Label errorMessage = new Label();
        errorMessage.setTextFill(Color.RED);
        errorMessage.setFont(new Font("Helvetica", 18));

        // Username
        MFXTextField userNameField = new MFXTextField();
        userNameField.setPromptText("Nom d'utilisateur");
        userNameField.setStyle("-fx-font-size: 20;");
        userNameField.setFloatMode(FloatMode.BORDER);
        userNameField.setBorder(new Border(new BorderStroke(Color.SKYBLUE, BorderStrokeStyle.SOLID, null, new BorderWidths(3))));
        userNameField.setContextMenuDisabled(true);
        userNameField.setPadding(new Insets(5, 5, 5, 5));
        userNameField.setId("username_inscription");
        userNameField.setPrefWidth(250);
        userNameField.setMaxWidth(250);
        userNameField.setPrefHeight(30);
        userNameField.setMaxHeight(30);

        // On réinitialise la bordure du champs de texte si l'utilisateur écrit dedans
        userNameField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                userNameField.setBorder(new Border(new BorderStroke(Color.SKYBLUE, BorderStrokeStyle.SOLID, null, new BorderWidths(3))));
                errorMessage.setVisible(false);
            }
        });
        ///////////////////////////////////////////////////////////

        // E-Mail
        MFXTextField mailField = new MFXTextField();
        mailField.setPromptText("Adresse e-mail");
        mailField.setStyle("-fx-font-size: 20;");
        mailField.setFloatMode(FloatMode.BORDER);
        mailField.setBorder(new Border(new BorderStroke(Color.SKYBLUE, BorderStrokeStyle.SOLID, null, new BorderWidths(3))));
        mailField.setContextMenuDisabled(true);
        mailField.setPadding(new Insets(5, 5, 5, 5));
        mailField.setId("username_inscription");
        mailField.setPrefWidth(250);
        mailField.setMaxWidth(250);
        mailField.setPrefHeight(30);
        mailField.setMaxHeight(30);

        // On réinitialise la bordure du champs de texte si l'utilisateur écrit dedans
        mailField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                mailField.setBorder(new Border(new BorderStroke(Color.SKYBLUE, BorderStrokeStyle.SOLID, null, new BorderWidths(3))));
                errorMessage.setVisible(false);
            }
        });
        ///////////////////////////////////////////////////////////

        // Password n°1
        MFXPasswordField passwordField_1 = new MFXPasswordField();
        passwordField_1.setPromptText("Mot de Passe");
        passwordField_1.setStyle("-fx-font-size: 16;");
        passwordField_1.setFloatMode(FloatMode.BORDER);
        passwordField_1.setBorder(new Border(new BorderStroke(Color.SKYBLUE, BorderStrokeStyle.SOLID, null, new BorderWidths(3))));
        passwordField_1.setContextMenuDisabled(true);
        passwordField_1.setPadding(new Insets(5, 5, 5, 5));
        passwordField_1.setId("password_inscription");
        passwordField_1.setPrefWidth(250);
        passwordField_1.setMaxWidth(250);
        passwordField_1.setPrefHeight(10);
        passwordField_1.setMaxHeight(10);

        // On réinitialise la bordure du champs de texte si l'utilisateur écrit dedans
        passwordField_1.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                passwordField_1.setBorder(new Border(new BorderStroke(Color.SKYBLUE, BorderStrokeStyle.SOLID, null, new BorderWidths(3))));
                errorMessage.setVisible(false);
            }
        });
        ///////////////////////////////////////////////////////////

        // Password n°2
        MFXPasswordField passwordField_2 = new MFXPasswordField();
        passwordField_2.setPromptText("Répétez le mot de passe");
        passwordField_2.setStyle("-fx-font-size: 16;");
        passwordField_2.setFloatMode(FloatMode.BORDER);
        passwordField_2.setBorder(new Border(new BorderStroke(Color.SKYBLUE, BorderStrokeStyle.SOLID, null, new BorderWidths(3))));
        passwordField_2.setContextMenuDisabled(true);
        passwordField_2.setPadding(new Insets(5, 5, 5, 5));
        passwordField_2.setId("password_inscription");
        passwordField_2.setPrefWidth(250);
        passwordField_2.setMaxWidth(250);
        passwordField_2.setPrefHeight(10);
        passwordField_2.setMaxHeight(10);

        // On réinitialise la bordure du champs de texte si l'utilisateur écrit dedans
        passwordField_2.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                passwordField_2.setBorder(new Border(new BorderStroke(Color.SKYBLUE, BorderStrokeStyle.SOLID, null, new BorderWidths(3))));
                errorMessage.setVisible(false);
            }
        });
        ///////////////////////////////////////////////////////////

        // Boutton
        BorderPane buttonBox = new BorderPane();
        MFXButton inscriptionButton = new MFXButton("S'inscrire");
        inscriptionButton.setStyle("-fx-background-color: skyblue; -fx-font-size: 24px;");

        // Centre le bouton
        BorderPane.setAlignment(inscriptionButton, Pos.CENTER);
        buttonBox.setCenter(inscriptionButton);

        // On fait un lien pour revenir sur la page de connexion
        Hyperlink connexion_lien = new Hyperlink("<- Retour");
        connexion_lien.setAlignment(Pos.CENTER);
        connexion_lien.setFont(new Font("Helvetica", 18));
        connexion_lien.setOnAction(event -> {

            Main main_controller = new Main();
            main_controller.switchToScene("LoginView", stage, root);

        });

        // Créer un évenement au bouton
        inscriptionButton.setOnAction(
                new InscriptionController(stage, root, userNameField, mailField, passwordField_1, passwordField_2, errorMessage)
        );
        ///////////////////////////////////////////////////////////////////

        // On ajoute les éléments à la grille
        inscriptionGrid.add(userNameField, 0, 6);
        inscriptionGrid.add(mailField, 0, 7);
        inscriptionGrid.add(passwordField_1, 0, 8);
        inscriptionGrid.add(passwordField_2, 0, 9);
        inscriptionGrid.add(buttonBox, 0, 10);
        inscriptionGrid.add(errorMessage, 0, 11);
        inscriptionGrid.add(connexion_lien, 0, 12);

        GridPane.setHalignment(errorMessage, HPos.CENTER);

        // On aligne les élements
        inscriptionGrid.setStyle("-fx-alignment: center;");

        // On clear la root en conservant le webpane de particules en arrière plan
        ObservableList<Node> children = root.getChildren();
        for (int i = children.size() - 1; i >= 0; i--) {
            Node node = children.get(i);
            if (!(node.getId().equals("webPane"))) {
                children.remove(i); // On enlève tous les éléments sauf le webPane
            }
        }

        // On créer les identifiants pour pouvoir les supprimmer dans une prochaine scène
        inscriptionBox.setId("inscriptionBox");
        inscriptionGrid.setId("inscriptionGrid");

        // On groupe les éléments dans la root
        root.getChildren().addAll(inscriptionBox, inscriptionGrid);

    }
}