package fr.tekcity.tekcity_launcher.view;

import fr.litarvan.openauth.microsoft.MicrosoftAuthenticationException;
import fr.litarvan.openauth.microsoft.MicrosoftAuthenticator;
import fr.tekcity.tekcity_launcher.controller.LoginController;
import fr.tekcity.tekcity_launcher.Main;
//import fr.tekcity.tekcity_launcher.functions.ConnectMicrosoftUser;
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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.materialdesign.MaterialDesign;


import java.util.Objects;
import java.util.prefs.Preferences;


public class LoginView extends Region {

    private Stage stage;
    private StackPane root;

    public LoginView(Stage stage, StackPane root){

        // On créer ci-dessous l'inteface de connexion
        //-------------------------------------------------------------------------

        // On créer un Pane pour faire une boite de couleur noir pour le formulaire de connexion
        Pane loginBox = new Pane();
        loginBox.setBackground(new Background(new BackgroundFill(Color.rgb(7, 8, 7), CornerRadii.EMPTY, Insets.EMPTY)));
        loginBox.setMaxHeight(500);
        loginBox.setMaxWidth(450);

        // Ombre de loginBox
        InnerShadow shadow_box = new InnerShadow();
        shadow_box.setColor(Color.LIGHTBLUE);
        shadow_box.setRadius(20);
        shadow_box.setChoke(0.5);

        // On l'applique
        loginBox.setEffect(shadow_box);

        // Ombre pour du texte
        InnerShadow shadow_text = new InnerShadow();
        shadow_text.setColor(Color.LIGHTBLUE);
        shadow_text.setRadius(5);
        shadow_text.setChoke(0.5);

        // On créer une animation d'apparition
        FadeTransition fade_login = new FadeTransition(Duration.millis(500), loginBox);
        fade_login.setFromValue(0.8);
        fade_login.setToValue(1.0);
        fade_login.play();

        // On créer une bordure au Pane
        Border border = new Border(new BorderStroke(Color.DARKGRAY, BorderStrokeStyle.SOLID, null, new BorderWidths(3)));
        loginBox.setBorder(border);

        // On charge la/les polices
        Font tektur = Font.loadFont(LoginView.class.getResourceAsStream("/fonts/Tektur-Bold.ttf"), 80);

        // On créer un titre au Panel
        Label titre = new Label("Tekcity");
        titre.setTextFill(Color.WHITE);
        titre.setFont(tektur);
        // Ombre du titre
        titre.setEffect(shadow_text);

        // Boite pour le titre
        VBox boxTitre = new VBox(25, titre);
        boxTitre.setAlignment(Pos.TOP_CENTER);
        boxTitre.setPrefSize(450, 500);

        loginBox.getChildren().add(boxTitre);

        // Créer un formulaire de connexion
        GridPane loginGrid = new GridPane();
        loginGrid.setVgap(10);
        loginGrid.setMaxHeight(400);
        loginGrid.setMaxWidth(400);

        // Label pour des erreures de formulaire de connexion
        final Label errorMessage = new Label();
        errorMessage.setTextFill(Color.RED);
        errorMessage.setFont(new Font("Helvetica", 18));

        // Username
        MFXTextField userNameField = new MFXTextField();
        userNameField.setPromptText("Nom d'utilisateur ou e-mail");
        userNameField.setStyle("-fx-font-size: 20;");
        userNameField.setFloatMode(FloatMode.BORDER);
        userNameField.setBorder(new Border(new BorderStroke(Color.SKYBLUE, BorderStrokeStyle.SOLID, null, new BorderWidths(3))));
        userNameField.setContextMenuDisabled(true);
        userNameField.setPadding(new Insets(5, 5, 5, 5));
        userNameField.setId("username_login");
        userNameField.setPrefWidth(310);
        userNameField.setMaxWidth(310);
        userNameField.setPrefHeight(50);
        userNameField.setMaxHeight(50);

        // On réinitialise la bordure du champs de texte si l'utilisateur écrit dedans
        userNameField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                userNameField.setBorder(new Border(new BorderStroke(Color.SKYBLUE, BorderStrokeStyle.SOLID, null, new BorderWidths(3))));
                errorMessage.setVisible(false);
            }
        });
        ///////////////////////////////////////////////////////////

        // Password
        MFXPasswordField passwordField = new MFXPasswordField();
        passwordField.setPromptText("Mot de Passe");
        passwordField.setStyle("-fx-font-size: 20;");
        passwordField.setFloatMode(FloatMode.BORDER);
        passwordField.setBorder(new Border(new BorderStroke(Color.SKYBLUE, BorderStrokeStyle.SOLID, null, new BorderWidths(3))));
        passwordField.setContextMenuDisabled(true);
        passwordField.setPadding(new Insets(5, 5, 5, 5));
        passwordField.setPrefWidth(310);
        passwordField.setMaxWidth(310);
        passwordField.setPrefHeight(50);
        passwordField.setMaxHeight(50);

        // On réinitialise la bordure du champs de texte si l'utilisateur écrit dedans
        passwordField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                passwordField.setBorder(new Border(new BorderStroke(Color.SKYBLUE, BorderStrokeStyle.SOLID, null, new BorderWidths(3))));
                errorMessage.setVisible(false);
            }
        });
        ///////////////////////////////////////////////////////////

        // Récupére les préférences de l'utilisateur
        Preferences preferences = Preferences.userNodeForPackage(Main.class);

        String error = preferences.get("error", ""); // Récupère l'erreur MS pour la traiter, si elle existe

        // Condition pour l'erreur microsoft authentification
        if(!Objects.equals(error, "")) {
            // erreur icone
            FontIcon errorIcon = new FontIcon("fa-exclamation-triangle:20");
            errorIcon.setStyle("-fx-fill: red; -fx-font-family: FontAwesome");
            // erreur message
            errorMessage.getStyleClass().add("warning-icon");
            errorMessage.setGraphic(errorIcon);
            errorMessage.setText(error);
            errorMessage.setVisible(true);

            // Puis on réinitialise l'erreur ms
            preferences.remove("error");

        }else{
            errorMessage.setVisible(false);
        }


        // Box pour les boutons de connexion
        GridPane buttonBox = new GridPane();
        buttonBox.setAlignment(Pos.CENTER);

        // Boutton de login avec TekCity
        MFXButton loginButton = new MFXButton("Se connecter");
        loginButton.setStyle("-fx-background-color: skyblue; -fx-font-size: 24px;");

        // Icone pour le bouton connexion microsoft
        FontIcon icon = new FontIcon(MaterialDesign.MDI_MINECRAFT);
        icon.setIconSize(40);

        // Bouton de login avec microsoft
        MFXButton microsoftLoginButton = new MFXButton("");
        microsoftLoginButton.setGraphic(icon);
        microsoftLoginButton.setStyle("-fx-background-color: skyblue;");

        // évènement du bouton de login avec microsoft
        microsoftLoginButton.setOnMouseClicked(event -> {

            try {
                LoadingMicrosoftView loading_ms_view = new LoadingMicrosoftView(stage, root);
            } catch (MicrosoftAuthenticationException e) {
                throw new RuntimeException(e);
            }
        });

        // ajout des boutons dans la box
        buttonBox.add(loginButton, 0, 0);
        buttonBox.add(microsoftLoginButton, 1, 0);

        // Espacements des boutons
        GridPane.setMargin(loginButton, new Insets(0, 10, 0, 0));

        // Créer un évenement au bouton
        loginButton.setOnAction(
                new LoginController(stage, root, userNameField, passwordField, errorMessage)
        );
        ///////////////////////////////////////////////////////////////////

        // On fait un lien pour s'inscrire
        Hyperlink inscription_lien = new Hyperlink("Vous n'êtes pas encore inscrit ?");
        inscription_lien.setAlignment(Pos.CENTER);
        inscription_lien.setFont(new Font("Helvetica", 18));
        inscription_lien.setOnAction(event -> {

            // On cache l'erreur du formulaire de connexion
            errorMessage.setVisible(false);

            Main main_controller = new Main();
            main_controller.switchToScene("InscriptionView", stage, root);

        });

        // On ajoute les éléments à la grille
        loginGrid.add(userNameField, 0, 5);
        loginGrid.add(passwordField, 0, 8);
        loginGrid.add(errorMessage,0,9);
        loginGrid.add(buttonBox, 0, 10);
        loginGrid.add(inscription_lien, 0, 12);

        GridPane.setHalignment(userNameField, HPos.CENTER);
        GridPane.setHalignment(passwordField, HPos.CENTER);
        GridPane.setHalignment(errorMessage, HPos.CENTER);
        GridPane.setHgrow(buttonBox, Priority.ALWAYS);
        GridPane.setHalignment(buttonBox, HPos.CENTER);
        GridPane.setHalignment(inscription_lien, HPos.CENTER);

        // On aligne les élements
        loginGrid.setStyle("-fx-alignment: center;");

        // On clear la root en conservant le webpane de particules en arrière plan
        ObservableList<Node> children = root.getChildren();
        for (int i = children.size() - 1; i >= 0; i--) {
            Node node = children.get(i);
            if (!(node.getId().equals("webPane"))) {
                children.remove(i); // On enlève tous les éléments sauf le webPane
            }
        }

        // On créer les identifiants pour pouvoir les supprimmer dans une prochaine scène
        loginBox.setId("loginBox");
        loginGrid.setId("loginGrid");

        // On groupe les éléments dans la racine
        root.getChildren().addAll(loginBox, loginGrid);

    }
}