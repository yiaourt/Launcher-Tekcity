package fr.tekcity.tekcity_launcher;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.enums.FloatMode;
import javafx.animation.FadeTransition;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

import static javafx.scene.layout.BackgroundPosition.CENTER;
import static javafx.scene.layout.BackgroundRepeat.REPEAT;

public class InscriptionView {

    private final SceneController sceneController;
    private Parent view;

    public InscriptionView(SceneController sceneController) {
        this.sceneController = sceneController;
        initView();
    }

    public Parent getView() {
        return view;
    }

    private void initView() {
        // On créer ci-dessous l'inteface d"inscription
        //-------------------------------------------------------------------------
        // On initialise l'effet de flou
        BoxBlur blur = new BoxBlur(20, 0, 3);

        // On créer un Pane pour faire une boite de couleur noir pour le formulaire de connexion
        Pane inscriptionBox = new Pane();
        inscriptionBox.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        inscriptionBox.setMaxHeight(400);
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
        fade_inscription.setFromValue(0.0);
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
        ///////////////////////////////////////////////////////////

        // Password
        MFXPasswordField passwordField = new MFXPasswordField();
        passwordField.setPromptText("Mot de Passe");
        passwordField.setStyle("-fx-font-size: 20;");
        passwordField.setFloatMode(FloatMode.BORDER);
        passwordField.setBorder(new Border(new BorderStroke(Color.SKYBLUE, BorderStrokeStyle.SOLID, null, new BorderWidths(3))));
        passwordField.setContextMenuDisabled(true);
        passwordField.setPadding(new Insets(5, 5, 5, 5));
        passwordField.setId("password_inscription");
        passwordField.setPrefWidth(250);
        passwordField.setMaxWidth(250);
        passwordField.setPrefHeight(10);
        passwordField.setMaxHeight(10);
        ///////////////////////////////////////////////////////////

        // Boutton
        BorderPane buttonBox = new BorderPane();
        MFXButton loginButton = new MFXButton("S'inscrire");
        loginButton.setStyle("-fx-background-color: skyblue; -fx-font-size: 24px;");

        // Centre le bouton
        BorderPane.setAlignment(loginButton, Pos.CENTER);
        buttonBox.setCenter(loginButton);
        ///////////////////////////////////////////////////////////////////

        // On fait un lien pour revenir sur la page de connexion
        Hyperlink connexion_lien = new Hyperlink("<- Retour");
        connexion_lien.setAlignment(Pos.CENTER);
        connexion_lien.setFont(new Font("Helvetica", 18));
        connexion_lien.setOnAction(event -> {

            sceneController.activate("login");
        });

        // On ajoute les éléments à la grille
        inscriptionGrid.add(userNameField, 0, 6);
        inscriptionGrid.add(mailField, 0, 7);
        inscriptionGrid.add(passwordField, 0, 8);
        inscriptionGrid.add(buttonBox, 0, 10);
        inscriptionGrid.add(connexion_lien, 0, 11);

        // On aligne les élements
        inscriptionGrid.setStyle("-fx-alignment: center;");

        // Crée une StackPane pour superposer l'image de fond floue et la zone de connexion
        StackPane root = new StackPane();
        root.setStyle("-fx-background-size: cover;");

        // On change la couleur de fond d'écran
        Pane black_bg = new Pane();
        BackgroundFill black_bg_fill = new BackgroundFill(Color.BLACK, null, null);
        Background background = new Background(black_bg_fill);
        black_bg.setBackground(background);

        //Créer une image de fond
        Pane img_bg = new HBox();
        img_bg.setBackground(new Background(new BackgroundImage(new Image("file:src/images/BG_tekcity_launcher.jpg"), REPEAT, REPEAT, CENTER,
                new BackgroundSize(100, 100, true, true, true, true))));

        // Ajoute un effet de flou sur l'image de fond
        img_bg.setEffect(blur);

        // On créer une animation d'apparition
        FadeTransition fade_background = new FadeTransition(Duration.millis(4500), img_bg);
        fade_background.setFromValue(0.0);
        fade_background.setToValue(1.0);
        fade_background.play();

        // On groupe les éléments dans la root
        root.getChildren().addAll(black_bg, img_bg, inscriptionBox, inscriptionGrid);

        view = root;
    }
}