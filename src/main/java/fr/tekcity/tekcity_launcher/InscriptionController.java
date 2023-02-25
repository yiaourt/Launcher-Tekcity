package fr.tekcity.tekcity_launcher;

import fr.tekcity.tekcity_launcher.functions.ConnexionBD;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.kordamp.ikonli.javafx.FontIcon;


import java.sql.*;

public class InscriptionController implements EventHandler<ActionEvent> {

    private static Stage stage;

    private static MFXTextField usernameField;
    private static MFXTextField mailField;
    private static MFXPasswordField passwordField_1;
    private static MFXPasswordField passwordField_2;

    private static Label errorMessage;


    public InscriptionController(Stage stage,
                                 MFXTextField usernameField,
                                 MFXTextField mailField,
                                 MFXPasswordField passwordField_1,
                                 MFXPasswordField passwordField_2,
                                 Label errorMessage) {
        InscriptionController.stage = stage;
        InscriptionController.usernameField = usernameField;
        InscriptionController.mailField = mailField;
        InscriptionController.passwordField_1 = passwordField_1;
        InscriptionController.passwordField_2 = passwordField_2;
        InscriptionController.errorMessage = errorMessage;
    }

    @Override
    public void handle(ActionEvent event) {

        // On récupère les valeurs du formulaire
        String username = usernameField.getText();
        String mail = mailField.getText();
        String password_1 = passwordField_1.getText();
        String password_2 = passwordField_2.getText();

        // Vérifier le nom d'utilisateur et le mot de passe ici
        // System.out.println(username + " " + mail + " " + password_1 + " " + password_2);

        // On vérifie que le nom d'utilisateur le mail et le mot de passe sont remplis dans le formulaire.
        if (username.isEmpty()) {

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
            errorMessage.setText("Le nom d'utilisateur est vide");
            errorMessage.setVisible(true);

        } else {

            // On vérifie que le mail n'est pas vide
            if (mail.isEmpty()) {

                // Si le nom d'utilisateur est vide, focus rouge sur les bords du formulaire
                mailField.requestFocus();
                mailField.selectAll();
                mailField.setBorder(new Border(new BorderStroke(Color.INDIANRED, BorderStrokeStyle.SOLID, null, new BorderWidths(3))));

                // erreur icone
                FontIcon icon = new FontIcon("fa-exclamation-triangle:20");
                icon.setStyle("-fx-fill: red; -fx-font-family: FontAwesome");

                // Erreur definition
                errorMessage.getStyleClass().add("warning-icon");
                errorMessage.setGraphic(icon);
                errorMessage.setText("Le champs du mail est vide");
                errorMessage.setVisible(true);

            } else {

                // On vérifie que le password 1 n'est pas vide
                if (password_1.isEmpty()) {

                    // Si le nom d'utilisateur est vide, focus rouge sur les bords du formulaire
                    passwordField_1.requestFocus();
                    passwordField_1.selectAll();
                    passwordField_1.setBorder(new Border(new BorderStroke(Color.INDIANRED, BorderStrokeStyle.SOLID, null, new BorderWidths(3))));

                    // erreur icone
                    FontIcon icon = new FontIcon("fa-exclamation-triangle:20");
                    icon.setStyle("-fx-fill: red; -fx-font-family: FontAwesome");

                    // Erreur definition
                    errorMessage.getStyleClass().add("warning-icon");
                    errorMessage.setGraphic(icon);
                    errorMessage.setText("Erreur dans le formulaire.");
                    errorMessage.setVisible(true);

                } else {

                    // On vérifie que le password 2 n'est pas vide
                    if (password_2.isEmpty()) {

                        // Si le nom d'utilisateur est vide, focus rouge sur les bords du formulaire
                        passwordField_2.requestFocus();
                        passwordField_2.selectAll();
                        passwordField_2.setBorder(new Border(new BorderStroke(Color.INDIANRED, BorderStrokeStyle.SOLID, null, new BorderWidths(3))));

                        // erreur icone
                        FontIcon icon = new FontIcon("fa-exclamation-triangle:20");
                        icon.setStyle("-fx-fill: red; -fx-font-family: FontAwesome");

                        // Erreur definition
                        errorMessage.getStyleClass().add("warning-icon");
                        errorMessage.setGraphic(icon);
                        errorMessage.setText("Erreur dans le formulaire.");
                        errorMessage.setVisible(true);

                    } else {

                        // On vérifie que les 2 mot de passe sont identiques
                        if (!password_1.equals(password_2)) {

                            // Si le mot de passe 1 et 2 sont différents, focus rouge sur les bords du formulaire
                            passwordField_2.requestFocus();
                            passwordField_2.selectAll();
                            passwordField_2.setBorder(new Border(new BorderStroke(Color.INDIANRED, BorderStrokeStyle.SOLID, null, new BorderWidths(3))));

                            // erreur icone
                            FontIcon icon = new FontIcon("fa-exclamation-triangle:20");
                            icon.setStyle("-fx-fill: red; -fx-font-family: FontAwesome");

                            // Erreur definition
                            errorMessage.getStyleClass().add("warning-icon");
                            errorMessage.setGraphic(icon);
                            errorMessage.setText("Les mots de passe ne sont pas identiques");
                            errorMessage.fontProperty().setValue(Font.font("Helvetica", 13.5));
                            errorMessage.setVisible(true);


                        } else {

                            // On vérifie que l'utilisateur n'est pas déjà inscrit
                            try {
                                Connection connexion = ConnexionBD.getConnexion();
                                PreparedStatement getUsername = connexion.prepareStatement("SELECT * FROM users WHERE username =?");
                                PreparedStatement getMail = connexion.prepareStatement("SELECT * FROM users WHERE mail =?");

                                getUsername.setString(1, username);
                                getMail.setString(1, mail);

                                ResultSet resultat_username = getUsername.executeQuery();
                                ResultSet resultat_mail = getMail.executeQuery();

                                if (resultat_username.next()) { // Si l'utilisateur est déjà inscrit

                                    usernameField.requestFocus();
                                    usernameField.selectAll();
                                    usernameField.setBorder(new Border(new BorderStroke(Color.INDIANRED, BorderStrokeStyle.SOLID, null, new BorderWidths(3))));

                                    // erreur icone
                                    FontIcon icon = new FontIcon("fa-exclamation-triangle:20");
                                    icon.setStyle("-fx-fill: red; -fx-font-family: FontAwesome");

                                    // Erreur definition
                                    errorMessage.getStyleClass().add("warning-icon");
                                    errorMessage.setGraphic(icon);
                                    errorMessage.setText("Le nom d'utilisateur est déjà utilisé");
                                    errorMessage.setVisible(true);

                                    connexion.close();

                                } else { // Si l'utilisateur n'est pas déjà inscrit

                                    // On continus les vérifications avec le mail
                                    // Donc si le mail existe déjà dans la base de données
                                    if(resultat_mail.next()) { // Si le mail existe déjà dans la base de données

                                        mailField.requestFocus();
                                        mailField.selectAll();
                                        mailField.setBorder(new Border(new BorderStroke(Color.INDIANRED, BorderStrokeStyle.SOLID, null, new BorderWidths(3))));

                                        // erreur icone
                                        FontIcon icon = new FontIcon("fa-exclamation-triangle:20");
                                        icon.setStyle("-fx-fill: red; -fx-font-family: FontAwesome");

                                        // Erreur definition
                                        errorMessage.getStyleClass().add("warning-icon");
                                        errorMessage.setGraphic(icon);
                                        errorMessage.setText("Le mail est déjà utilisé");
                                        errorMessage.setVisible(true);

                                        connexion.close();

                                    }else{

                                        // Si toutes les conditions sont remplis on demande à l'utilisateur de vérifier que c'est bien son compte minecraft
                                        UUIDValidatorView uuid_validation_view = new UUIDValidatorView(stage);

                                    }
                                }
                            } catch (SQLException e) { // Si la connexion à échoué
                                System.err.println("Erreur lors de l'inscription de l'utilisateur : " + e.getMessage());
                            }
                        }
                    }
                }
            }
        }
    }

    public static String[] getInscriptionUserInfo(){
        String[] inscription_info = {usernameField.getText(), mailField.getText(), passwordField_1.getText()};
        return inscription_info;
    }
}