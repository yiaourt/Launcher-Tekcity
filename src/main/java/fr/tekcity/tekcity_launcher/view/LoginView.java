package fr.tekcity.tekcity_launcher.view;

import fr.tekcity.tekcity_launcher.controller.LoginController;
import fr.tekcity.tekcity_launcher.Main;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.enums.FloatMode;
import javafx.animation.FadeTransition;
import javafx.collections.ObservableList;
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


public class LoginView extends Region {

    private Stage stage;
    private StackPane root;

    public LoginView(Stage stage, StackPane root){

        // On créer ci-dessous l'inteface de connexion
        //-------------------------------------------------------------------------

        // On créer un Pane pour faire une boite de couleur noir pour le formulaire de connexion
        Pane loginBox = new Pane();
        loginBox.setBackground(new Background(new BackgroundFill(Color.rgb(7, 8, 7), CornerRadii.EMPTY, Insets.EMPTY)));
        loginBox.setMaxHeight(400);
        loginBox.setMaxWidth(400);

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
        Font tektur = Font.loadFont(this.getClass().getResourceAsStream("/fonts/Tektur-Bold.ttf"), 80);
        Font obtuse = Font.loadFont(this.getClass().getResourceAsStream("/fonts/Obtuse.ttf"), 20);
        Font gomarice = Font.loadFont(this.getClass().getResourceAsStream("/fonts/gomarice_super_g_type_2.ttf"), 20);
        Font g7 = Font.loadFont(this.getClass().getResourceAsStream("/fonts/G7_Cube_5.ttf"), 20);

        // On créer un titre au Panel
        Label titre = new Label("Tekcity");
        titre.setTextFill(Color.WHITE);
        titre.setFont(tektur);
        // Ombre du titre
        titre.setEffect(shadow_text);

        // Boite pour le titre
        VBox boxTitre = new VBox(15, titre);
        boxTitre.setAlignment(Pos.TOP_CENTER);
        boxTitre.setPrefSize(400, 400);

        loginBox.getChildren().add(boxTitre);

        // Créer un formulaire de connexion
        GridPane loginGrid = new GridPane();
        loginGrid.setHgap(10);
        loginGrid.setVgap(10);
        loginGrid.setMaxHeight(400);
        loginGrid.setMaxWidth(400);

        // Username
        MFXTextField userNameField = new MFXTextField();
        userNameField.setPromptText("Nom d'utilisateur");
        userNameField.setStyle("-fx-font-size: 20;");
        userNameField.setFloatMode(FloatMode.BORDER);
        userNameField.setBorder(new Border(new BorderStroke(Color.SKYBLUE, BorderStrokeStyle.SOLID, null, new BorderWidths(3))));
        userNameField.setContextMenuDisabled(true);
        userNameField.setPadding(new Insets(5, 5, 5, 5));
        userNameField.setId("username_login");
        userNameField.setPrefWidth(250);
        userNameField.setMaxWidth(250);
        userNameField.setPrefHeight(30);
        userNameField.setMaxHeight(30);
        ///////////////////////////////////////////////////////////

        // Password
        MFXPasswordField passwordField = new MFXPasswordField();
        passwordField.setPromptText("Mot de Passe");
        passwordField.setStyle("-fx-font-size: 20;");
        passwordField.setFloatMode(FloatMode.BORDER);
        passwordField.setBorder(new Border(new BorderStroke(Color.SKYBLUE, BorderStrokeStyle.SOLID, null, new BorderWidths(3))));
        passwordField.setContextMenuDisabled(true);
        passwordField.setPadding(new Insets(5, 5, 5, 5));
        passwordField.setPrefWidth(250);
        passwordField.setMaxWidth(250);
        passwordField.setPrefHeight(10);
        passwordField.setMaxHeight(10);
        ///////////////////////////////////////////////////////////

        // Box pour les boutons de connexion
        GridPane buttonBox = new GridPane();

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

        // ajout des boutons dans la box
        buttonBox.add(loginButton, 0, 0);
        buttonBox.add(microsoftLoginButton, 1, 0);

        // Espacements des boutons
        GridPane.setMargin(loginButton, new Insets(0, 10, 0, 0));

        // Créer un évenement au bouton
        loginButton.setOnAction(
                new LoginController(userNameField, passwordField)
        );
        ///////////////////////////////////////////////////////////////////

        // On fait un lien pour s'inscrire
        Hyperlink inscription_lien = new Hyperlink("Vous n'êtes pas encore inscrit ?");
        inscription_lien.setAlignment(Pos.CENTER);
        inscription_lien.setFont(new Font("Helvetica", 18));
        inscription_lien.setOnAction(event -> {

            Main main_controller = new Main();
            main_controller.switchToScene("InscriptionView", stage, root);

        });

        // On ajoute les éléments à la grille
        loginGrid.add(userNameField, 0, 6);
        loginGrid.add(passwordField, 0, 7);
        loginGrid.add(buttonBox, 0, 10);
        loginGrid.add(inscription_lien, 0, 12);

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