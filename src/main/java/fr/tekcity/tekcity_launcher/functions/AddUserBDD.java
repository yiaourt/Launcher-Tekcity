package fr.tekcity.tekcity_launcher.functions;

import fr.tekcity.tekcity_launcher.Main;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddUserBDD {

    private Stage stage;
    private String username;
    private String password;
    private String email;
    private String UUID;

    public static void AddUserBDD(Stage stage, String username, String password, String email, String UUID) throws SQLException {

        System.out.println(username + " " + password + " " + email + " " + UUID);

        // On créer ci dessous l'utilisateur sur la base de données
        // ----------------------------------------------------------
        // On Hash le mot de passe avec BCrypt
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        Connection connexion = ConnexionBD.getConnexion();

        // Requête SQL pour insérer l'utilisateur
        String sql = "INSERT INTO users (username, mail, passpass, uuid_minecraft) VALUES (?, ?, ?, ?)";

        PreparedStatement insertUser = connexion.prepareStatement(sql);

        insertUser.setString(1, username);
        insertUser.setString(2, email);
        insertUser.setString(3, hashedPassword);
        insertUser.setString(4, UUID);

        // Exécution de la requête
        int rowsInserted = insertUser.executeUpdate();

        if (rowsInserted > 0) { // Si l'utilisateur est correctement enregistré dans la base de données

            // On le redirige vers la page d'accueil avec une alerte
            Main main_controller = new Main();
            main_controller.switchToScene("HomeView", stage);


        }

        // Fermeture de la connexion
        connexion.close();
    }

}
