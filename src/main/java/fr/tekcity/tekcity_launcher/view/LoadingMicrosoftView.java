package fr.tekcity.tekcity_launcher.view;

import fr.litarvan.openauth.microsoft.MicrosoftAuthResult;
import fr.litarvan.openauth.microsoft.MicrosoftAuthenticationException;
import fr.litarvan.openauth.microsoft.MicrosoftAuthenticator;
import fr.tekcity.tekcity_launcher.Main;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.concurrent.CompletableFuture;
import java.util.prefs.Preferences;

public class LoadingMicrosoftView {

    private Stage stage;
    private StackPane root;

    private Timeline timeline;
    public LoadingMicrosoftView(Stage stage, StackPane root) throws MicrosoftAuthenticationException {

        Pane loadBox = new Pane();
        loadBox.setBackground(new Background(new BackgroundFill(Color.rgb(7, 8, 7), CornerRadii.EMPTY, Insets.EMPTY)));
        loadBox.setMaxHeight(400);
        loadBox.setMaxWidth(400);

        // Ombre de loginBox
        InnerShadow shadow_box = new InnerShadow();
        shadow_box.setColor(Color.LIGHTBLUE);
        shadow_box.setRadius(20);
        shadow_box.setChoke(0.5);

        // On l'applique
        loadBox.setEffect(shadow_box);

        // Ombre pour du texte
        InnerShadow shadow_text = new InnerShadow();
        shadow_text.setColor(Color.LIGHTBLUE);
        shadow_text.setRadius(5);
        shadow_text.setChoke(0.5);

        // On créer une animation d'apparition
        FadeTransition fade_login = new FadeTransition(Duration.millis(500), loadBox);
        fade_login.setFromValue(0.8);
        fade_login.setToValue(1.0);
        fade_login.play();

        // On créer une bordure au Pane
        Border border = new Border(new BorderStroke(Color.DARKGRAY, BorderStrokeStyle.SOLID, null, new BorderWidths(3)));
        loadBox.setBorder(border);

        // Loading Grid
        GridPane loadGrid = new GridPane();
        loadGrid.setHgap(10);
        loadGrid.setVgap(10);
        loadGrid.setMaxHeight(400);
        loadGrid.setMaxWidth(400);
        loadGrid.setAlignment(Pos.CENTER);

        // Demande de connexion Microsoft dans une nouvelle fenêtre
        MicrosoftAuthenticator authenticator = new MicrosoftAuthenticator();

        // Récupére les préférences de l'utilisateur
        Preferences preferences = Preferences.userNodeForPackage(Main.class);

        CompletableFuture<MicrosoftAuthResult> actual_ms_auth = authenticator.loginWithAsyncWebview().whenCompleteAsync((response, error) -> {
            if(error != null) {

                String errorMessage = error.getMessage().substring(error.getMessage().lastIndexOf(":") + 1);
                preferences.put("error", errorMessage);

            }
            if (response.getAccessToken() != null) {

                preferences.put("MS_token", response.getAccessToken());
                preferences.put("username", response.getProfile().getName());
                preferences.put("uuid_minecraft", response.getProfile().getId());
                preferences.putBoolean("is_logged_in", true);

            }
        });

        // Timeline ou boucle de chargement et d'écoute de l'authentification microsoft
        this.timeline = new Timeline(new KeyFrame(Duration.millis(1000), event -> {

            boolean isLoggedIn = preferences.getBoolean("is_logged_in", false);

            if (isLoggedIn) {
                // Récupère la class Main pour la redirection de scène
                Main main_controller = new Main();
                main_controller.switchToScene("HomeView", stage, root);
                stopLoading();

            }else{
                if(actual_ms_auth.isDone()){ // On empêche le code de s'éxecuter si la fenêtre d'authentification n'est pas quitter
                    // Récupère la class Main pour la redirection de scène
                    Main main_controller = new Main();
                    main_controller.switchToScene("LoginView", stage, root);
                    stopLoading();
                }
            }

        }));
        this.timeline.setCycleCount(Timeline.INDEFINITE); // Boucle indéfiniment
        this.timeline.play(); // Démarrer la boucle

        Label titre_load = new Label("En attente d'authentification avec Microsoft ...");
        titre_load.setFont(new Font("Helvetica", 18));
        titre_load.setTextFill(Color.WHITE);

        ProgressIndicator spinner = new ProgressIndicator();
        spinner.setId("spinner");
        loadGrid.add(titre_load, 0, 0);
        loadGrid.add(spinner, 0, 1);

        GridPane.setHalignment(spinner, HPos.CENTER);

        // On clear la root en conservant le webpane de particules en arrière plan
        ObservableList<Node> children = root.getChildren();
        for (int i = children.size() - 1; i >= 0; i--) {
            Node node = children.get(i);
            if (!(node.getId().equals("webPane"))) {
                children.remove(i); // On enlève tous les éléments sauf le webPane
            }
        }

        // On créer les identifiants pour pouvoir les supprimer dans une prochaine scène
        loadBox.setId("loadBox");
        loadGrid.setId("loadGrid");

        // On groupe les éléments dans la racine
        root.getChildren().addAll(loadBox, loadGrid);
    }

    public void stopLoading() {
        this.timeline.stop();
    }
}
