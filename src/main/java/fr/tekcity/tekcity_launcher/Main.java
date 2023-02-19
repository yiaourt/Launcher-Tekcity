package fr.tekcity.tekcity_launcher;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Création du contrôleur de scène
        SceneController sceneController = new SceneController(primaryStage);

        // Création des scenes
        LoginView loginView = new LoginView(sceneController);
        InscriptionView inscriptionView = new InscriptionView(sceneController);

        // Ajout des scènes au contrôleur de scène
        sceneController.addScene("login", loginView.getView());
        sceneController.addScene("inscription", inscriptionView.getView());

        // Activation de la vue de connexion
        sceneController.activate("login");

        primaryStage.setMinWidth(966);
        primaryStage.setMinHeight(666);
    }

    public static void main(String[] args) {
        launch(args);
    }
}