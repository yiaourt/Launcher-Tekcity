package fr.tekcity.tekcity_launcher;

import fr.tekcity.tekcity_launcher.UUIDValidatorView;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


import java.io.InputStream;
import java.util.Objects;

public class Main extends Application {

    private Stage stage;

    @Override
    public void start(Stage stage) {

        this.stage = stage;

        stage.getIcons().add(new Image("file:src/images/logo.png"));

        // Initialisation de la scène selon la connexion de l'utilisateur
        switchToScene("LoginView", getStage());

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