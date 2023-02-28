package fr.tekcity.tekcity_launcher.view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXNodesList;
import fr.tekcity.tekcity_launcher.Main;
import fr.tekcity.tekcity_launcher.functions.InitBackgroundView;
import io.github.palexdev.materialfx.controls.BoundTextField;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.base.MFXLabeled;
import io.github.palexdev.materialfx.controls.legacy.MFXLegacyComboBox;
import io.github.palexdev.materialfx.skins.MFXComboBoxSkin;
import io.github.palexdev.materialfx.skins.MFXTextFieldSkin;
import javafx.collections.ObservableList;
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
import javafx.stage.Stage;

import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.prefs.Preferences;


public class HomeView extends Region {

    private Stage stage;
    private StackPane root;


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

        // On créer un GridPane qui va nous servir à placer les éléments de l'interface
        GridPane interface_panel = new GridPane();

        // On créer le panel de sélection de Jeu
        GridPane game_panel = new GridPane();
        game_panel.setPrefWidth(300);
        BackgroundFill backgroundFill = new BackgroundFill(Paint.valueOf("black"), CornerRadii.EMPTY, new Insets(0));
        Background background = new Background(backgroundFill);
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
        profil_panel.setOpacity(0.85);

        // On créer une Horizontal Box pour le profil d'informations'
        HBox profil_menu_Box = new HBox(10);
        profil_menu_Box.setPadding(new Insets(15));
        profil_menu_Box.setPrefHeight(100);

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
        profil_user_button.setStyle("-fx-font-family: Helvetica; -fx-font-size: 20; -fx-font-weight: bold; -fx-underline: true;");
        profil_user_button.setTextFill(Color.LIGHTSKYBLUE);
        profil_user_button.setPrefSize(250, 50);
        profil_user_button.setBackground(background);
        profil_user_button.setOpacity(0.85);

        // Bouton de déconnexion de l'utilisateur
        JFXButton deconnexion_button = new JFXButton("Se déconnecter");
        deconnexion_button.setStyle("-fx-font-family: Helvetica; -fx-font-size: 20; -fx-font-weight: bold; -fx-underline: true;");
        deconnexion_button.setTextFill(Color.RED);
        deconnexion_button.setPrefSize(250, 50);
        deconnexion_button.setBackground(background);
        deconnexion_button.setOpacity(0.85);

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


        // On ajoute à l'interface à gauche le panel de liste de serveur
        interface_panel.add(game_panel, 0, 0);
        interface_panel.add(game_box, 0,0);
        GridPane.setVgrow(game_panel, Priority.ALWAYS);
        GridPane.setVgrow(game_box, Priority.ALWAYS);

        // Puis on ajoute à droite en haut le panel de profil
        interface_panel.add(profil_panel, 1, 0);
        interface_panel.add(profil_box, 1,0);
        GridPane.setHgrow(profil_panel, Priority.ALWAYS);
        GridPane.setHgrow(profil_box, Priority.ALWAYS);
        GridPane.setVgrow(profil_panel, Priority.ALWAYS);
        GridPane.setVgrow(profil_box, Priority.ALWAYS);
        GridPane.setFillHeight(profil_panel, false);
        GridPane.setFillHeight(profil_box, false);
        GridPane.setValignment(profil_panel, VPos.TOP);
        GridPane.setValignment(profil_box, VPos.TOP);

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
