package fr.tekcity.tekcity_launcher.controller;

import fr.tekcity.tekcity_launcher.functions.ConnectUserBDD;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.kordamp.ikonli.javafx.FontIcon;

import java.sql.SQLException;

public class LoginController implements EventHandler<ActionEvent> {

    private Stage stage;
    private StackPane root;
    private MFXTextField usernameField;
    private MFXPasswordField passwordField;
    private Label errorMessage;

    public LoginController(Stage stage, StackPane root, MFXTextField usernameField, MFXPasswordField passwordField, Label errorMessage) {
        this.stage = stage;
        this.root = root;
        this.usernameField = usernameField;
        this.passwordField = passwordField;
        this.errorMessage = errorMessage;
    }

    @Override
    public void handle(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // On vérifie si le nom d'utilisateur est vide
        if(username.isEmpty()){
            // Si le nom d'utilisateur est vide, focus rouge sur les bords du formulaire
            usernameField.requestFocus();
            usernameField.selectAll();
            usernameField.setBorder(new Border(new BorderStroke(Color.INDIANRED, BorderStrokeStyle.SOLID, null, new BorderWidths(3))));

            // erreur icone
            FontIcon icon = new FontIcon("fa-exclamation-triangle:20");
            icon.setStyle("-fx-fill: red; -fx-font-family: FontAwesome");

            // Erreur definition
            errorMessage.getStyleClass().add("warning-icon");
            errorMessage.setGraphic(icon);
            errorMessage.setText("Erreur dans le formulaire.");
            errorMessage.setVisible(true);

        }else{

            // On vérifie si le mot de passe est vide
            if(password.isEmpty()) {
                // Si le mot de passe est vide, focus rouge sur les bords du formulaire
                passwordField.requestFocus();
                passwordField.selectAll();
                passwordField.setBorder(new Border(new BorderStroke(Color.INDIANRED, BorderStrokeStyle.SOLID, null, new BorderWidths(3))));

                // erreur icone
                FontIcon icon = new FontIcon("fa-exclamation-triangle:20");
                icon.setStyle("-fx-fill: red; -fx-font-family: FontAwesome");

                // Erreur definition
                errorMessage.getStyleClass().add("warning-icon");
                errorMessage.setGraphic(icon);
                errorMessage.setText("Erreur dans le formulaire.");
                errorMessage.setVisible(true);

            }else{

                // Sinon, si les deux formulaires sont remplis on vérifie la connexion de l'utilisateur sur la BDD
                try {
                    ConnectUserBDD.ConnectUser(stage, root, username, password);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}