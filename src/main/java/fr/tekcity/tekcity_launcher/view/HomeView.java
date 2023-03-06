package fr.tekcity.tekcity_launcher.view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXNodesList;
import com.jfoenix.controls.JFXTextField;
import fr.tekcity.tekcity_launcher.Main;
import fr.tekcity.tekcity_launcher.functions.InitBackgroundView;
import io.github.palexdev.materialfx.controls.BoundTextField;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.base.MFXLabeled;
import io.github.palexdev.materialfx.controls.legacy.MFXLegacyComboBox;
import io.github.palexdev.materialfx.enums.ButtonType;
import io.github.palexdev.materialfx.enums.FloatMode;
import io.github.palexdev.materialfx.skins.MFXComboBoxSkin;
import io.github.palexdev.materialfx.skins.MFXTextFieldSkin;
import javafx.animation.ScaleTransition;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import javafx.util.Duration;
import org.controlsfx.control.GridView;
import org.controlsfx.control.spreadsheet.Grid;
import org.jetbrains.annotations.Nullable;
import org.kordamp.bootstrapfx.BootstrapFX;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.materialdesign.MaterialDesign;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;
import java.util.prefs.Preferences;


public class HomeView extends Region {

    private Stage stage;
    private StackPane root;

    private Border defaultBorder;
    private Border hoverBorder;


    public HomeView(Stage stage, StackPane root, String alertMessage) {

        // On créer ci-dessous l'inteface d'installation, de mises à jour et de démarrage de Minecraft
        //-------------------------------------------------------------------------

        // Récupére les préférences de l'utilisateur
        Preferences preferences = Preferences.userNodeForPackage(Main.class);

        // On récupere la session de l'utilisateur s'il existe
        String username = preferences.get("username", "");
        String mail = preferences.get("mail", "");
        String uuid = preferences.get("uuid_minecraft", "");
        Boolean is_logged = preferences.getBoolean("is_logged_in", false);

        // Des backgrounds
        BackgroundFill backgroundBlack = new BackgroundFill(Paint.valueOf("black"), CornerRadii.EMPTY, new Insets(0));
        Background background = new Background(backgroundBlack);

        BackgroundFill backgroundHardGray = new BackgroundFill(Color.rgb(7,6,7), CornerRadii.EMPTY, new Insets(0));
        Background hardgray = new Background(backgroundHardGray);

        // On créer une interface qui va gérer deux vues d'interfaces
        GridPane interface_panel = new GridPane();

        GridPane interface_panel_left = new GridPane();
        GridPane interface_panel_right = new GridPane();

        // On créer le panel de sélection de Jeu
        GridPane game_panel = new GridPane();
        game_panel.setPrefWidth(300);
        game_panel.setBackground(background);
        game_panel.setOpacity(0.85);

        // On initialise l'effet de flou
        BoxBlur blur = new BoxBlur(10, 0, 3);
        game_panel.setEffect(blur);

        // On créer une Vertical Box pour la liste des serveurs
        VBox list_serv_Box = new VBox(10);
        list_serv_Box.setPrefWidth(300);
        list_serv_Box.setPadding(new Insets(15));
        list_serv_Box.setAlignment(Pos.TOP_CENTER);
        Border rightborder = new Border(new BorderStroke(Color.SKYBLUE, BorderStrokeStyle.SOLID, null, new BorderWidths(0,3,0,0)));
        list_serv_Box.setBorder(rightborder);

        // On créer un label pour la liste de serveur
        Label titre_list_serv = new Label();
        titre_list_serv.setText("Liste des serveurs :");
        titre_list_serv.setStyle("-fx-font-family: Helvetica; -fx-font-size: 20; -fx-font-weight: bold; -fx-underline: true;");
        titre_list_serv.setTextFill(Color.WHITE);

        // Le bouton pour le serveur TekCity Officiel
        MFXButton tekcityButton = new MFXButton("TekCity - Officiel");
        tekcityButton.setPrefWidth(Double.MAX_VALUE);
        tekcityButton.setStyle("-fx-font-family: Tektur; -fx-font-size: 26px;");
        tekcityButton.setOpacity(1);

        list_serv_Box.getChildren().addAll(titre_list_serv, tekcityButton);

        // On désactive le bouton par défaut
        tekcityButton.setDisable(true);
        tekcityButton.setStyle("-fx-font-family: Tektur; -fx-font-size: 26px; -fx-background-color: darkgray;");

        AnchorPane game_box = new AnchorPane();
        game_box.getChildren().addAll(game_panel, list_serv_Box);

        AnchorPane.setTopAnchor(game_panel, 0.0);
        AnchorPane.setLeftAnchor(game_panel, 0.0);
        AnchorPane.setBottomAnchor(game_panel, 0.0);

        AnchorPane.setTopAnchor(list_serv_Box, 0.0);
        AnchorPane.setLeftAnchor(list_serv_Box, 0.0);
        AnchorPane.setBottomAnchor(list_serv_Box, 0.0);

        // On créer ensuite le panel du menu d'informations de l'utilisateur connecté
        GridPane profil_panel = new GridPane();
        profil_panel.setBackground(background);
        profil_panel.setPrefHeight(100);
        profil_panel.setMaxHeight(100);
        profil_panel.setOpacity(0.85);
        profil_panel.setBorder(new Border(new BorderStroke(Color.LIGHTSKYBLUE, BorderStrokeStyle.SOLID, null, new BorderWidths(0, 0,3,0))));
        profil_panel.toFront();

        // On créer une Horizontal Box pour le profil d'informations'
        HBox profil_menu_Box = new HBox(10);
        profil_menu_Box.setPadding(new Insets(15));
        profil_menu_Box.setPrefHeight(100);
        profil_menu_Box.setMaxHeight(100);

        Image user_avatar = new Image("https://mc-heads.net/avatar/" + uuid + "/42/nohelm.png");
        ImageView user_avatar_view = new ImageView(user_avatar);

        // On créer un bouton du profil de l'utilisateur qui permet d'ouvrir le menu de profil
        JFXButton user_button = new JFXButton(username);
        user_button.setStyle("-fx-font-family: Helvetica; -fx-font-size: 20; -fx-font-weight: bold; -fx-underline: true;");
        user_button.setTextFill(Color.WHITE);
        user_button.setGraphic(user_avatar_view);
        user_button.setPadding(new Insets(5,5,5,5));
        user_button.setGraphicTextGap(20);

        // Bouton pour ouvrir la modification de profil de l'utilisateur
        JFXButton profil_user_button = new JFXButton("Mon Profil");
        profil_user_button.setStyle("-fx-font-family: Helvetica; -fx-font-size: 20; -fx-font-weight: bold;");
        profil_user_button.setTextFill(Color.LIGHTSKYBLUE);
        profil_user_button.setPrefSize(250, 50);
        profil_user_button.setBackground(background);
        profil_user_button.setOpacity(0.85);
        profil_user_button.setAlignment(Pos.CENTER_LEFT);
        profil_user_button.toFront();

        defaultBorder = new Border(new BorderStroke(Color.LIGHTSKYBLUE, BorderStrokeStyle.SOLID, null, new BorderWidths(0, 0,1,1)));
        hoverBorder = new Border(new BorderStroke(Color.LIGHTSKYBLUE, BorderStrokeStyle.SOLID, null, new BorderWidths(0, 0,3,3)));

        profil_user_button.setBorder(defaultBorder);

        profil_user_button.setOnMouseEntered(event -> {
            profil_user_button.setOpacity(1);
            profil_user_button.setBorder(hoverBorder);
            ScaleTransition animation = new ScaleTransition(Duration.seconds(0.2), profil_user_button);
            animation.setToX(1.1);
            animation.setToY(1.1);
            animation.play();
            profil_user_button.setBorder(hoverBorder);
        });

        profil_user_button.setOnMouseExited(event -> {
            profil_user_button.setOpacity(0.85);
            ScaleTransition animation = new ScaleTransition(Duration.seconds(0.2), profil_user_button);
            animation.setToX(1.0);
            animation.setToY(1.0);
            animation.play();
            profil_user_button.setBorder(defaultBorder);
        });

        // Bouton de déconnexion de l'utilisateur
        JFXButton deconnexion_button = new JFXButton("Se déconnecter");
        deconnexion_button.setStyle("-fx-font-family: Helvetica; -fx-font-size: 20; -fx-font-weight: bold;");
        deconnexion_button.setTextFill(Color.RED);
        deconnexion_button.setPrefSize(250, 50);
        deconnexion_button.setBackground(background);
        deconnexion_button.setOpacity(0.85);
        deconnexion_button.setAlignment(Pos.CENTER_LEFT);
        deconnexion_button.toFront();

        defaultBorder = new Border(new BorderStroke(Color.LIGHTSKYBLUE, BorderStrokeStyle.SOLID, null, new BorderWidths(0, 0,1,1)));
        hoverBorder = new Border(new BorderStroke(Color.LIGHTSKYBLUE, BorderStrokeStyle.SOLID, null, new BorderWidths(0, 0,3,3)));

        deconnexion_button.setBorder(defaultBorder);

        deconnexion_button.setOnMouseEntered(event -> {
            deconnexion_button.setOpacity(1);
            deconnexion_button.setBorder(hoverBorder);
            ScaleTransition animation = new ScaleTransition(Duration.seconds(0.2), deconnexion_button);
            animation.setToX(1.1);
            animation.setToY(1.1);
            animation.play();
            deconnexion_button.setBorder(hoverBorder);
        });

        deconnexion_button.setOnMouseExited(event -> {
            deconnexion_button.setOpacity(0.85);
            ScaleTransition animation = new ScaleTransition(Duration.seconds(0.2), deconnexion_button);
            animation.setToX(1.0);
            animation.setToY(1.0);
            animation.play();
            deconnexion_button.setBorder(defaultBorder);
        });

        deconnexion_button.setOnAction(event -> {
            // Déconnexion de l'utilisateur
            preferences.remove("username");
            preferences.remove("mail");
            preferences.remove("uuid_minecraft");
            preferences.putBoolean("is_logged_in", false);

            Main main_controller = new Main(); // Redirection vers la page de connexion
            main_controller.switchToScene("LoginView", stage, root);
        });

        // On créer une nodes list pour afficher un menu de profil de l'utilisateur
        JFXNodesList nodesList = new JFXNodesList();
        nodesList.addAnimatedNode(user_button);
        nodesList.addAnimatedNode(profil_user_button);
        nodesList.addAnimatedNode(deconnexion_button);
        nodesList.setAlignment(Pos.CENTER);
        nodesList.setScaleShape(false);

        // évenement pour quitter la nodeList
        stage.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            // Vérifier si le clic a été effectué en dehors de la JFXNodesList
            if (!nodesList.getBoundsInParent().contains(event.getX(), event.getY())) {
                nodesList.animateList(false); // Fermer la liste
            }
        });

        profil_menu_Box.getChildren().add(nodesList);

        AnchorPane profil_box = new AnchorPane();
        profil_box.getChildren().addAll(profil_panel, profil_menu_Box);

        AnchorPane.setTopAnchor(profil_panel, 0.0);
        AnchorPane.setRightAnchor(profil_panel, 0.0);

        AnchorPane.setTopAnchor(profil_menu_Box, 0.0);
        AnchorPane.setRightAnchor(profil_menu_Box, 0.0);

        // On créer ci-dessous le panel d'affichage du serveur
        GridPane server_panel = new GridPane();
        server_panel.setBackground(background);
        server_panel.setOpacity(1);
        server_panel.setBorder(new Border(new BorderStroke(Color.LIGHTSKYBLUE, BorderStrokeStyle.SOLID, null, new BorderWidths(0,3,3,0))));
        server_panel.setPadding(new Insets(20));
        server_panel.setVgap(50);
        server_panel.setHgap(100);

        // On créer l'affichage du serveur
        Label titre_server = new Label("TekCity - Serveur Officiel");
        titre_server.setTextFill(Color.WHITE);
        titre_server.setStyle("-fx-font-family: Tektur; -fx-font-size: 30px; -fx-font-weight: bold;");
        titre_server.setTextAlignment(TextAlignment.CENTER);

        BorderPane titre_server_pane = new BorderPane();
        titre_server_pane.setCenter(titre_server);

        String short_description = "TekCity est un monde ouvert en Survie remplis de magies, de technologies et bien plus encore !";

        String description = "Nous sommes heureux de vous accueillir sur notre communauté de jeu passionnante, où nous avons créé un monde entièrement personnalisé pour que vous puissiez explorer, construire et survivre !\n" +
                "\n" +
                "Notre serveur est constamment mis à jour avec les dernières versions du Modpack ATM8, ce qui signifie que vous aurez accès à toutes les dernières fonctionnalités et améliorations.";

        String features = "- Version : 1.19.2 Forge \n" +
                            "- ModPack : ATM8 Custom\n";



        Pane short_description_pane = new Pane();

        Pane description_pane = new Pane();

        VBox features_pane = new VBox();

        Text short_description_server = new Text(short_description);
        short_description_server.wrappingWidthProperty().bind(titre_server_pane.widthProperty());
        short_description_server.setFill(Color.WHITE);
        short_description_server.setStyle("-fx-font-family: Helvetica; -fx-font-size: 18px;");
        short_description_server.setTextAlignment(TextAlignment.CENTER);

        Text description_server = new Text(description);
        description_server.setFill(Color.WHITE);
        description_server.setStyle("-fx-font-family: Helvetica; -fx-font-size: 18px;");
        description_server.setTextAlignment(TextAlignment.CENTER);

        Text features_server = new Text(features);
        features_server.setFill(Color.WHITE);
        features_server.setStyle("-fx-font-family: Helvetica; -fx-font-size: 18px;");
        features_server.setTextAlignment(TextAlignment.JUSTIFY);

        WebView webview_gallery = new WebView();

        WebEngine webengine_gallery = webview_gallery.getEngine();
        webengine_gallery.load(String.valueOf(Objects.requireNonNull(getClass().getResource("/gallery/gallery.html"))));

        // Courte description et longue description
        short_description_pane.getChildren().add(short_description_server);
        description_pane.getChildren().add(description_server);

        // Features et Gallerie d'images
        features_pane.getChildren().add(features_server);
        features_pane.getChildren().add(webview_gallery);

        // On créer les boutons et barre de progression d'installation
        BorderPane server_btn_interface = new BorderPane();
        server_btn_interface.prefWidthProperty().bind(server_panel.widthProperty());
        server_btn_interface.prefHeightProperty().bind(server_panel.heightProperty());

        JFXButton play_install_btn = new JFXButton("Installer");
        play_install_btn.setStyle("-fx-background-color: skyblue; -fx-font-size: 24px;");

        // Icone pour le bouton chois de dossier
        FontIcon icon_folder = new FontIcon(MaterialDesign.MDI_FOLDER);
        icon_folder.setIconSize(35);

        JFXButton directory_chooser_btn = new JFXButton();
        directory_chooser_btn.setStyle("-fx-background-color: skyblue;");
        directory_chooser_btn.setGraphic(icon_folder);

        MFXTextField directory_textfield = new MFXTextField();
        directory_textfield.setStyle("-fx-font-size: 17;");
        directory_textfield.setFloatMode(FloatMode.BORDER);
        directory_textfield.setBorder(new Border(new BorderStroke(Color.SKYBLUE, BorderStrokeStyle.SOLID, null, new BorderWidths(3))));
        directory_textfield.setContextMenuDisabled(true);
        directory_textfield.setPadding(new Insets(0, 0, 0, 0));
        directory_textfield.setAlignment(Pos.CENTER_RIGHT);
        directory_textfield.prefWidthProperty().bind(server_btn_interface.widthProperty().divide(2));

        // Dossier par défaut pour informer l'utilisateur du dossier d'installation
        directory_textfield.setText("{{dossier de l'éxecutable}}/TekCity_Officiel/");

        HBox directory_chooser_box = new HBox();
        directory_chooser_box.setAlignment(Pos.BOTTOM_LEFT);
        directory_chooser_box.setSpacing(-3);

        directory_chooser_box.getChildren().addAll(directory_chooser_btn, directory_textfield);

        server_btn_interface.setLeft(directory_chooser_box);
        server_btn_interface.setRight(play_install_btn);

        // On ajoute l'affichage
        server_panel.add(titre_server_pane, 0,0, GridPane.REMAINING, 1);
        server_panel.add(short_description_pane, 0, 1, GridPane.REMAINING, 1);
        server_panel.add(description_pane, 0, 2, 1, 1);
        server_panel.add(features_pane, 1, 2, 1, 1);
        server_panel.add(server_btn_interface, 0, 3, 2, 1);

        GridPane.setHalignment(titre_server_pane, HPos.CENTER);
        GridPane.setHalignment(short_description_pane, HPos.CENTER);

        GridPane.setMargin(short_description_pane, new Insets(0,0,20,0));

        GridPane.setFillWidth(titre_server, true);
        GridPane.setFillWidth(short_description_pane, true);

        GridPane.setHgrow(titre_server, Priority.ALWAYS);
        GridPane.setHgrow(short_description_pane, Priority.ALWAYS);

        description_server.wrappingWidthProperty().bind(server_panel.widthProperty().divide(2));
        features_server.wrappingWidthProperty().bind(server_panel.widthProperty().divide(2.5));
        webview_gallery.prefWidthProperty().bind(features_server.wrappingWidthProperty());
        webview_gallery.prefHeightProperty().bind(server_panel.heightProperty().multiply(1.6));

        // On ajoute à l'interface à gauche le panel de liste de serveur
        interface_panel_left.add(game_panel, 0, 0);
        interface_panel_left.add(game_box, 0,0);
        GridPane.setVgrow(game_panel, Priority.ALWAYS);
        GridPane.setVgrow(game_box, Priority.ALWAYS);

        // On ajoute centrer à droite en dessous du panel de profil l'affichage du serveur
        interface_panel_right.add(server_panel, 0, 1, GridPane.REMAINING, GridPane.REMAINING);
        GridPane.setVgrow(server_panel, Priority.ALWAYS);
        GridPane.setFillWidth(server_panel, true);

        // On ajoute à droite en haut le panel de profil
        interface_panel_right.add(profil_panel, 0, 0);
        interface_panel_right.add(profil_box, 0,0);
        GridPane.setHgrow(profil_panel, Priority.ALWAYS);
        GridPane.setHgrow(profil_box, Priority.ALWAYS);
        GridPane.setVgrow(profil_panel, Priority.NEVER);
        GridPane.setVgrow(profil_box, Priority.NEVER);
        GridPane.setFillHeight(profil_panel, false);
        GridPane.setFillHeight(profil_box, false);

        // On ajoute les deux interfaces droite et gauche à l'interface
        interface_panel.add(interface_panel_left, 0,0);
        interface_panel.add(interface_panel_right, 1, 0);
        GridPane.setVgrow(interface_panel_left, Priority.ALWAYS);
        GridPane.setVgrow(interface_panel_right, Priority.ALWAYS);
        GridPane.setHgrow(interface_panel_right, Priority.ALWAYS);

        // On clear la root en conservant le webpane de particules en arrière plan
        ObservableList<Node> children = root.getChildren();
        for (int i = children.size() - 1; i >= 0; i--) {
            Node node = children.get(i);
            if (!(node.getId().equals("webPane"))) {
                children.remove(i); // On enlève tous les éléments sauf le webPane
            }
        }

        // On créer les identifiants pour pouvoir les supprimmer dans une prochaine scène
        interface_panel.setId("interface_panel");

        // On groupe les éléments dans la racine
        root.getChildren().addAll(interface_panel);
    }
}
