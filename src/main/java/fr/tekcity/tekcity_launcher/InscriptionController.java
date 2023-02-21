package fr.tekcity.tekcity_launcher;

import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.web.WebView;
import org.json.JSONArray;
import org.json.JSONObject;
import org.kordamp.ikonli.javafx.FontIcon;

import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

public class InscriptionController implements EventHandler<ActionEvent> {

    private final MFXTextField usernameField;
    private final MFXTextField mailField;
    private final MFXPasswordField passwordField_1;
    private final MFXPasswordField passwordField_2;

    private final Label errorMessage;


    public InscriptionController(MFXTextField usernameField,
                                 MFXTextField mailField,
                                 MFXPasswordField passwordField_1,
                                 MFXPasswordField passwordField_2,
                                 Label errorMessage) {

        this.usernameField = usernameField;
        this.mailField = mailField;
        this.passwordField_1 = passwordField_1;
        this.passwordField_2 = passwordField_2;
        this.errorMessage = errorMessage;
    }

    @Override
    public void handle(ActionEvent event) {

        // On récupère les valeurs du formulaire
        String username = usernameField.getText();
        String mail = mailField.getText();
        String password_1 = passwordField_1.getText();
        String password_2 = passwordField_2.getText();

        // Vérifier le nom d'utilisateur et le mot de passe ici
        System.out.println(username + " " + mail + " "+ password_1 + " " + password_2);

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
        }

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
        }

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
        }

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
        }

        // On créer une Url qui va vérifier l'utilisateur sur l'API officiel de Minecraft
        long date = new Date().getTime();
        URL url = null;
        try {
            url = new URL("https://api.mojang.com/users/profiles/minecraft/"+ username + "?at=" + date);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        // On ouvre la connexion avec l'API
        HttpURLConnection con = null;
        try {
            con = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // On paramètre la requête sur un "GET"
        try {
            con.setRequestMethod("GET");
        } catch (ProtocolException e) {
            throw new RuntimeException(e);
        }

        // On li la réponse sur la connexion
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // On récupère la réponse
        String inputLine;
        while (true) {
            try {
                // Si la réponse est pas vide'
                if ((inputLine = in.readLine()) == null) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            JSONObject response = new JSONObject(inputLine);
            System.out.println(response.getString("id"));

        }
        try {
            in.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }



        /* Configure la connexion à la base de données
        String url = "jdbc:mysql://localhost:3306/nom_de_la_base_de_donnees";
        String utilisateur = "nom_utilisateur";
        String mdp = "mot_de_passe";
        String requete = "INSERT INTO users (nom_utilisateur, mot_de_passe) VALUES (?, ?)";

        try {
            // Établit la connexion à la base de données
            Connection connexion = DriverManager.getConnection(url, utilisateur, mdp);

            // Prépare la requête SQL
            PreparedStatement statement = connexion.prepareStatement(requete);
            statement.setString(1, nomUtilisateur);
            statement.setString(2, motDePasse);

            // Exécute la requête SQL
            int lignesModifiees = statement.executeUpdate();

            // Vérifie si l'insertion a réussi
            if (lignesModifiees > 0) {
                System.out.println("L'utilisateur a été inscrit avec succès.");
            } else {
                System.out.println("L'inscription a échoué.");
            }

        } catch (SQLException e) {
            System.err.println("Erreur lors de l'inscription de l'utilisateur : " + e.getMessage());
        }*/
    }
}