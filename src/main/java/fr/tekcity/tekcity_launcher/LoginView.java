package fr.tekcity.tekcity_launcher;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.enums.FloatMode;
import javafx.animation.FadeTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import static javafx.scene.layout.BackgroundPosition.CENTER;
import static javafx.scene.layout.BackgroundRepeat.REPEAT;

public class LoginView extends Parent {

    private Stage stage;

    public LoginView(Stage stage) {

        // On créer ci-dessous l'inteface de connexion
        //-------------------------------------------------------------------------
        // On initialise l'effet de flou
        BoxBlur blur = new BoxBlur(20, 0, 3);

        // On créer un Pane pour faire une boite de couleur noir pour le formulaire de connexion
        Pane loginBox = new Pane();
        loginBox.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
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
        fade_login.setFromValue(0.0);
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

        // Boutton
        BorderPane buttonBox = new BorderPane();
        MFXButton loginButton = new MFXButton("Se connecter");
        loginButton.setStyle("-fx-background-color: skyblue; -fx-font-size: 24px;");

        // Centre le bouton
        BorderPane.setAlignment(loginButton, Pos.CENTER);
        buttonBox.setCenter(loginButton);

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
            main_controller.switchToScene("InscriptionView", stage);
        });

        // On ajoute les éléments à la grille
        loginGrid.add(userNameField, 0, 6);
        loginGrid.add(passwordField, 0, 7);
        loginGrid.add(buttonBox, 0, 10);
        loginGrid.add(inscription_lien, 0, 12);

        // On aligne les élements
        loginGrid.setStyle("-fx-alignment: center;");

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
        root.getChildren().addAll(black_bg, img_bg, loginBox, loginGrid);

        stage.setScene(new Scene(root));
        stage.show();
    }
}