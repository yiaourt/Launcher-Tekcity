package fr.tekcity.tekcity_launcher;

import fr.tekcity.tekcity_launcher.view.HomeView;
import fr.tekcity.tekcity_launcher.view.InscriptionView;
import fr.tekcity.tekcity_launcher.view.LoginView;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Duration;


import java.util.Objects;
import java.util.prefs.Preferences;

public class Main extends Application {

    private Stage stage;

    @Override
    public void start(Stage stage){

        this.stage = stage;

        // Taille de la fenêtre
        stage.setMinWidth(966);
        stage.setMinHeight(666);
        stage.setWidth(966);
        stage.setHeight(666);

        // Icone de la fenêtre
        stage.getIcons().add(new Image("file:src/images/logo.png"));

        // Récupére les préférences de l'utilisateur
        Preferences preferences = Preferences.userNodeForPackage(Main.class);

        // On récupere la session de l'utilisateur s'il existe
        String username = preferences.get("username", "");
        String MS_token = preferences.get("MS_token", "");
        Boolean is_logged = preferences.getBoolean("is_logged_in", false);

        System.out.println(username);
        System.out.println(MS_token);
        System.out.println(is_logged);

        // On créer une root
        StackPane root = new StackPane();
        root.setPrefSize(stage.getWidth(), stage.getHeight());
        // On charge l'arrière plan de particules dans une webview avec du html/js en utilisant particles.js
        BorderPane webPane = new BorderPane();
        webPane.setPrefSize(stage.getWidth(), stage.getHeight());
        webPane.setId("webPane");
        // Créer une WebView sur la taille du stage
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();

        // Charger le fichier HTML
        webEngine.load(String.valueOf(Objects.requireNonNull(getClass().getResource("/particles/particles.html"))));

        webPane.setCenter(webView);
        webView.addEventFilter(ScrollEvent.ANY, Event::consume);
        webView.addEventFilter(MouseEvent.MOUSE_PRESSED, Event::consume);
        webView.addEventFilter(MouseEvent.MOUSE_DRAGGED, Event::consume);
        webView.addEventFilter(MouseEvent.MOUSE_RELEASED, Event::consume);

        // On créer une animation d'apparition
        FadeTransition fadeIN_background = new FadeTransition(Duration.millis(1000), webPane);
        fadeIN_background.setFromValue(0.7);
        fadeIN_background.setToValue(1.0);
        fadeIN_background.play();

        root.getChildren().add(webPane);

        // On vérifie si l'utilisateur est connecté
        if(is_logged){

            // Initialisation de la scène d'accueil
            switchToScene("HomeView", getStage(), root);

        }else{ // Si l'utilisateur n'est pas connecté

            // Initialisation de la scène pour connecter l'utilisateur
            switchToScene("LoginView", getStage(), root);
        }

        // Calculer la position de la fenêtre au centre de l'écran
        double centerX = (java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth() - stage.getWidth()) / 2.0;
        double centerY = (java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight() - stage.getHeight()) / 2.0;

        // Définir la position de la fenêtre
        stage.setX(centerX);
        stage.setY(centerY);

        stage.setScene(new Scene(root));

        stage.show();

    }

    // Fonction qui créer une scène selon la vue
    public void switchToScene(String viewName, Stage stage, StackPane root){

        // La page d'accueil
        if(Objects.equals(viewName, "HomeView")) {

            HomeView home_view = new HomeView(stage, root, null);
        }

        // La page de connexion
        if(Objects.equals(viewName, "LoginView")) {

            LoginView login_view = new LoginView(stage, root);
        }

        // La page d'inscription
        if(Objects.equals(viewName, "InscriptionView")) {

            InscriptionView inscription_view = new InscriptionView(stage, root);

        }
    }

    // On récupere le stage avec une fonction getStage()
    public Stage getStage() {
        return stage;
    }

    public static void main(String[] args) {

        launch(args);

    }
}