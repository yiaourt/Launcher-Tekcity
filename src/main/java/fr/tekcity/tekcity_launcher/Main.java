package fr.tekcity.tekcity_launcher;

import fr.tekcity.tekcity_launcher.view.InscriptionView;
import fr.tekcity.tekcity_launcher.view.LoginView;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;


import java.util.Objects;
import java.util.prefs.Preferences;

public class Main extends Application {

    private Stage stage;

    @Override
    public void start(Stage stage) {

        this.stage = stage;

        // Icone de la fenêtre
        stage.getIcons().add(new Image("file:src/images/logo.png"));

        // Récupére les préférences de l'utilisateur
        Preferences preferences = Preferences.userNodeForPackage(Main.class);

        // On récupere la session de l'utilisateur s'il existe
        String username = preferences.get("username", "");
        Boolean is_logged = preferences.getBoolean("is_logged_in", false);

        System.out.println(username);
        System.out.println(is_logged);

        // On vérifie si l'utilisateur est connecté
        if(is_logged){

            // Initialisation de la scène d'accueil
            switchToScene("HomeView", getStage());

        }else{ // Si l'utilisateur n'est pas connecté

            // Initialisation de la scène pour connecter l'utilisateur
            switchToScene("LoginView", getStage());
        }

        // Taille de la fenêtre
        stage.setMinWidth(966);
        stage.setMinHeight(666);

        // Calculer la position de la fenêtre au centre de l'écran
        double centerX = (java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth() - stage.getWidth()) / 2.0;
        double centerY = (java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight() - stage.getHeight()) / 2.0;

        // Définir la position de la fenêtre
        stage.setX(centerX);
        stage.setY(centerY);

    }

    // Fonction qui créer une scène selon la vue
    public void switchToScene(String viewName, Stage stage) {

        // La page de connexion
        if(Objects.equals(viewName, "HomeView")) {

            LoginView login_view = new LoginView(stage);
            login_view.getStyleClass().add("root");
        }

        // La page de connexion
        if(Objects.equals(viewName, "LoginView")) {

            LoginView login_view = new LoginView(stage);
            login_view.getStyleClass().add("root");
        }

        // La page d'inscription
        if(Objects.equals(viewName, "InscriptionView")) {

            InscriptionView inscription_view = new InscriptionView(stage);

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