package fr.tekcity.tekcity_launcher.view;

import fr.tekcity.tekcity_launcher.controller.InscriptionController;
import fr.tekcity.tekcity_launcher.functions.AddUserBDD;
import fr.tekcity.tekcity_launcher.functions.CallMinecraftAPI;
import fr.tekcity.tekcity_launcher.functions.InitBackgroundView;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.enums.FloatMode;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicBoolean;

public class UUIDValidatorView {

    private MFXTextField usernameField;
    private MFXTextField mailField;
    private MFXPasswordField passwordField_1;
    private MFXPasswordField passwordField_2;

    private String uuid;

    public UUIDValidatorView(Stage stage, StackPane root) {

        // On commence par récuperer les données d'inscription
        String[] user_info_inscription = InscriptionController.getInscriptionUserInfo();

        // On map les variables
        String username_info = user_info_inscription[0];
        String mail_info = user_info_inscription[1];
        String password_info = user_info_inscription[2];

        // On récupère les l'UUID du nom d'utilisateur
        try {
            uuid = CallMinecraftAPI.getUUIDfromName(username_info);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // On demande ci-dessous de confirmer l'UUID de l'utilisateur avec une API
        //-------------------------------------------------------------------------

        // Bordure
        Border border = new Border(new BorderStroke(Color.DARKGRAY, BorderStrokeStyle.SOLID, null, new BorderWidths(3)));

        // Premier conteneur
        Pane container = new Pane();
        container.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        container.setMaxHeight(500);
        container.setMaxWidth(700);
        container.setBorder(border);

        // Ombre de container
        InnerShadow shadow_box = new InnerShadow();
        shadow_box.setColor(Color.LIGHTBLUE);
        shadow_box.setRadius(20);
        shadow_box.setChoke(0.5);

        // On l'applique
        container.setEffect(shadow_box);

        // On créer une animation d'apparition
        FadeTransition fade_container = new FadeTransition(Duration.millis(500), container);
        fade_container.setFromValue(0.0);
        fade_container.setToValue(1.0);
        fade_container.play();

        // On créer un Grid de pour la validation de l'UUID de l'utilisateur
        GridPane UUIDvalidation_grid = new GridPane();
        UUIDvalidation_grid.setPadding(new Insets(20, 20, 20, 20));
        UUIDvalidation_grid.setVgap(10);
        UUIDvalidation_grid.setHgap(10);
        UUIDvalidation_grid.setAlignment(Pos.CENTER);

        // On créer une contraintes pour que le minecraft_user_container se redimensionne
        ColumnConstraints constraints_container = new ColumnConstraints();
        constraints_container.setPrefWidth(100);

        UUIDvalidation_grid.getColumnConstraints().addAll(constraints_container, new ColumnConstraints());

        // On créer un Grid des informations Minecraft du joueur
        GridPane minecraft_user_container = new GridPane();
        minecraft_user_container.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        minecraft_user_container.setPadding(new Insets(20, 20, 20, 20));
        minecraft_user_container.setVgap(10);
        minecraft_user_container.setHgap(20);
        // On créer une bordure au container
        minecraft_user_container.setBorder(border);

        Image head_image = new Image("https://mc-heads.net/player/"+username_info+"/70");
        ImageView avatar_image_view = new ImageView(head_image);

        Label minecraft_username = new Label(username_info);
        minecraft_username.setFont(Font.font("Helvetica", FontWeight.BOLD, 20));
        minecraft_username.setTextFill(Color.WHITE);

        minecraft_user_container.add(avatar_image_view, 0, 0);
        minecraft_user_container.add(minecraft_username, 0, 1);

        GridPane.setHalignment(avatar_image_view, HPos.CENTER);

        // On créer un conteneur pour le texte de la validation de l'UUID de l'utilisateur
        GridPane text_container = new GridPane();

        // On créer le conteneur de la fonction de recherche de profil minecraft
        GridPane search_container = new GridPane();

        Label uuid_infos = new Label("UUID : 2fd7963e-c102-4700-a0ef-afd05094f3fb");
        uuid_infos.setFont(Font.font("Helvetica", FontWeight.BOLD, 14));
        uuid_infos.setTextFill(Color.GRAY);
        uuid_infos.setPadding(new Insets(0, 0, 0, 20));

        Label question_validation = new Label("Est ce que c'est bien votre profil Minecraft ?");
        question_validation.setFont(Font.font("Helvetica", FontWeight.BOLD, 18));
        question_validation.setTextFill(Color.WHITE);
        question_validation.setPadding(new Insets(0, 0, 0, 20));

        // On créer un conteneur pour des boutons de validation
        GridPane radio_container = new GridPane();
        radio_container.setTranslateX(20);

        RadioButton UUID_valid = new RadioButton("Oui");
        UUID_valid.setFont(Font.font("Helvetica", FontWeight.BOLD, 18));
        UUID_valid.setTextFill(Color.WHITE);

        RadioButton UUID_incorrect = new RadioButton("Non");
        UUID_incorrect.setFont(Font.font("Helvetica", FontWeight.BOLD, 18));
        UUID_incorrect.setTextFill(Color.WHITE);

        // Créez un groupe de basculement et ajoutez les boutons radiobox
        ToggleGroup toggleGroup = new ToggleGroup();
        UUID_valid.setToggleGroup(toggleGroup);
        UUID_incorrect.setToggleGroup(toggleGroup);

        // On ajoute les boutons au container de radiobox
        radio_container.add(UUID_valid, 0, 0);
        radio_container.add(UUID_incorrect, 1, 0);
        GridPane.setMargin(UUID_incorrect, new Insets(0, 0, 0, 60));

        // On ajoute le texte au container de texte
        text_container.add(uuid_infos, 0, 0);
        text_container.add(question_validation, 0, 1);
        text_container.add(radio_container, 0, 2);
        GridPane.setMargin(radio_container, new Insets(50, 0, 0, 0));

        // On agrandis le conteneur des boutons
        GridPane.setHgrow(radio_container, Priority.ALWAYS);

        // On demande à l'utilisateur de rechercher son profil sur Minecraft
        Label label_search = new Label("Veuillez ajouter votre profil manuellement :");
        label_search.setFont(Font.font("Helvetica", FontWeight.BOLD, 18));
        label_search.setTextFill(Color.WHITE);
        label_search.setPadding(new Insets(20, 0, 0, 20));

        // Formulaire de recherche de profil minecraft
        MFXTextField search_field = new MFXTextField();
        search_field.setPromptText("Nom d'utilisateur ou UUID");
        search_field.setStyle("-fx-font-size: 18;");
        search_field.setFloatMode(FloatMode.BORDER);
        search_field.setBorder(new Border(new BorderStroke(Color.SKYBLUE, BorderStrokeStyle.SOLID, null, new BorderWidths(3))));
        search_field.setContextMenuDisabled(true);
        search_field.setTranslateX(50);
        search_field.setTranslateY(30);
        search_field.setPrefWidth(300);

        // On créer un bouton de validation dans son conteneur pour passer à la scène suivante
        MFXButton validation_button = new MFXButton("Valider");
        validation_button.setFont(Font.font("Helvetica", FontWeight.BOLD, 18));
        validation_button.setPadding(new Insets(2, 20, 2, 20));
        validation_button.setStyle("-fx-background-color: gray; -fx-font-size: 24px;");
        // On le désactive par défaut
        validation_button.setDisable(true);

        // évènement de recherche lorsque l'utilisateur termine d'écrire
        PauseTransition pause = new PauseTransition(Duration.millis(333));
        search_field.textProperty().addListener((observable, oldValue, newValue) -> {

            pause.setOnFinished(event -> {
                // Lorsque le texte change on modifie l'avatar du profil minecraft
                avatar_image_view.setVisible(false);

                // On recréer l'image du profil minecraft
                Image new_head_image = new Image("https://mc-heads.net/player/" + newValue + "/70");
                ImageView new_avatar_image_view = new ImageView(new_head_image);

                minecraft_user_container.add(new_avatar_image_view, 0, 0);
                GridPane.setHalignment(new_avatar_image_view, HPos.CENTER);

            });
            pause.playFromStart();
            // On active le bouton de validation
            validation_button.setDisable(false);
            validation_button.setStyle("-fx-background-color: skyblue; -fx-font-size: 24px;");

        });


        // On instancie par défaut la variable de condition
        AtomicBoolean is_searching_uuid = new AtomicBoolean(false);

        // Ajoute un écouteur pour surveiller les changements de sélection
        toggleGroup.selectedToggleProperty().addListener((observable, oldToggle, newToggle) -> {

            if (!newToggle.equals(oldToggle)) {

                if (newToggle.equals(UUID_incorrect)) { // Si "Non" est sélectionné

                    UUID_valid.setSelected(false); // On déselectionne le bouton "Oui"

                } else { // Si "Oui" est sélectionné

                    UUID_incorrect.setSelected(false); // On déselectionne le bouton "Non"

                }
            }
        });

        // On créer l'évènement du bouton radio UUID_valid "Oui"
        UUID_valid.setOnAction(event -> {

            // On active le bouton de validation
            validation_button.setDisable(false);
            validation_button.setStyle("-fx-background-color: skyblue; -fx-font-size: 24px;");

            // On enlève la sélection manuelle du profil minecraft
            search_container.setVisible(false);

            // On réinitialise au cas ou l'image de l'avatar du profil minecraft
            avatar_image_view.setVisible(false);

            // On recréer l'image du profil minecraft
            Image new_head_image = new Image("https://mc-heads.net/player/" + username_info + "/70");
            ImageView new_avatar_image_view = new ImageView(new_head_image);

            minecraft_user_container.add(new_avatar_image_view, 0, 0);
            GridPane.setHalignment(new_avatar_image_view, HPos.CENTER);

            // On instancie la variable disant que l'utilisateur
            // souhaite garder se profil minecraft pour l'inscription
            is_searching_uuid.set(false);

        });

        // On créer l'évènement du bouton radio UUID_incorrect "Non"
        UUID_incorrect.setOnAction(event -> {

            // On rend visible le formulaire de recherche de profil minecraft
            search_container.setVisible(true);

            // On réinitialise le formulaire de recherche
            search_field.setText("");

            // On réinitialise au cas ou l'image de l'avatar du profil minecraft
            avatar_image_view.setVisible(false);

            // On recréer l'image du profil minecraft
            Image new_head_image = new Image("https://mc-heads.net/player/player/70");
            ImageView new_avatar_image_view = new ImageView(new_head_image);

            minecraft_user_container.add(new_avatar_image_view, 0, 0);
            GridPane.setHalignment(new_avatar_image_view, HPos.CENTER);

            // On désactive le bouton
            validation_button.setStyle("-fx-background-color: gray; -fx-font-size: 24px;");
            validation_button.setDisable(true);

            // On instancie la variable disant que l'utilisateur veut rechercher son profil
            is_searching_uuid.set(true);

        });

        // On execute l'évenement pour inscrire l'utilisateur sur la base de données
        validation_button.setOnAction(event -> {

            if(!is_searching_uuid.get()){
                // Si l'utilisateur ne cherche pas son profil minecraft on utilise alors son nom d'utilisateur
                // renseigner lors de l'inscription
                // -----------------------------------------------------------------------------------------------
                // On inscrit l'utilisateur sur la base de données
                try {
                    AddUserBDD.AddUserBDD(stage,
                            root,
                            username_info,
                            password_info,
                            mail_info,
                            uuid
                    );
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

            }else{
                // Sinon si l'utilisateur veut rechercher son profil minecraft et la déjà trouver dans l'API de recherche
                // -----------------------------------------------------------------------------------------------
                // On réucupére le formulaire pour récupérer l'uuid de l'utilisateur
                try {
                    uuid = CallMinecraftAPI.getUUIDfromName(search_field.getText());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

                // On inscrit l'utilisateur sur la base de données
                try {
                    AddUserBDD.AddUserBDD(stage,
                            root,
                            username_info,
                            password_info,
                            mail_info,
                            uuid
                    );
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        // On ajoute le label et la recherche dans le conteneur
        search_container.add(label_search, 0, 0);
        search_container.add(search_field, 0, 1);
        GridPane.setColumnSpan(search_container, 2);

        // On désactive le search_container par défaut
        search_container.setVisible(false);

        // On ajoute le conteneur de la recherche dans le conteneur de texte de la validation
        text_container.add(search_container, 0, 3);

        // On ajoute les conteneurs à la validation grid
        UUIDvalidation_grid.add(minecraft_user_container, 0, 0);
        UUIDvalidation_grid.add(text_container, 2, 0);
        UUIDvalidation_grid.add(validation_button, 0, 1);

        GridPane.setConstraints(minecraft_user_container, 0, 0, 2, 1);
        GridPane.setFillWidth(minecraft_user_container, true);
        GridPane.setColumnSpan(validation_button, 2);
        GridPane.setHalignment(validation_button, HPos.CENTER);

        GridPane.setHgrow(UUIDvalidation_grid, Priority.ALWAYS);

        root.getChildren().addAll(container, UUIDvalidation_grid);

        stage.setScene(new Scene(root));
        stage.show();

    }
}
