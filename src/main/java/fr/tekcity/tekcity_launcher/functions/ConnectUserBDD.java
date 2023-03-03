package fr.tekcity.tekcity_launcher.functions;

import fr.tekcity.tekcity_launcher.Main;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.prefs.Preferences;

public class ConnectUserBDD {

    private Stage stage;
    private String username;
    private String password;
    private String email;
    private String UUID;

    public static void ConnectUser(Stage stage, StackPane root, String username, String password) throws SQLException {

        // On créer ci dessous la connexion d'un utilisateur sur la base de données
        // ----------------------------------------------------------

        // Connexion avec la base de données
        Connection connexion = ConnexionBD.getConnexion();

        try {
            // Préparation de la requête SQL pour récupérer l'utilisateur correspondant aux identifiants entrés
            String sql = "SELECT * FROM users WHERE username = ?";

            PreparedStatement statement = connexion.prepareStatement(sql);

            statement.setString(1, username);

            // Exécution de la requête SQL
            ResultSet result = statement.executeQuery();

            // Si un utilisateur est trouvé
            if (result.next()) {
                // Récupération du mot de passe hashé dans la base de données
                String hashedPassword_BDD = result.getString("passpass");

                // Comparaison des deux hash
                if(BCrypt.checkpw(password, hashedPassword_BDD)) {

                    // Récupère les préférences de l'utilisateur
                    Preferences preferences = Preferences.userNodeForPackage(Main.class);

                    // L'utilisateur est connecté !
                    // (-   -   - - - - - - - - -
                    // Sauvegarde les préférences de la session
                    preferences.put("username", result.getString("username"));
                    preferences.put("mail", result.getString("mail"));
                    preferences.put("uuid_minecraft", result.getString("uuid_minecraft"));
                    preferences.putBoolean("is_logged_in", true);

                    // On le redirige vers la page d'accueil
                    Main main_controller = new Main();
                    main_controller.switchToScene("HomeView", stage, root);

                }else{

                    // Récupère les préférences de l'utilisateur
                    Preferences preferences = Preferences.userNodeForPackage(Main.class);

                    // L'utilisateur n'est pas connecté
                    // Erreur ... Puis redirection
                    String errorMessage = "Erreur : Identifiant ou mot de passe.";
                    preferences.put("error", errorMessage);

                    // On le redirige vers la page de connexion
                    Main main_controller = new Main();
                    main_controller.switchToScene("LoginView", stage, root);

                    connexion.close(); // Ferme la connexion bdd
                }
            }else{

                // Récupère les préférences de l'utilisateur
                Preferences preferences = Preferences.userNodeForPackage(Main.class);

                // L'utilisateur n'est pas connecté
                // Erreur ... Puis redirection
                String errorMessage = "Erreur : Identifiant ou mot de passe.";
                preferences.put("error", errorMessage);

                // On le redirige vers la page de connexion
                Main main_controller = new Main();
                main_controller.switchToScene("LoginView", stage, root);

                connexion.close(); // Ferme la connexion bdd
            }

        } catch (SQLException e) {

            // Récupère les préférences de l'utilisateur
            Preferences preferences = Preferences.userNodeForPackage(Main.class);
            // L'utilisateur n'est pas connecté
            // Erreur ... Puis redirection
            String errorMessage = "Erreur lors de la connexion BDD.";
            preferences.put("error", errorMessage);

            // On le redirige vers la page de connexion
            Main main_controller = new Main();
            main_controller.switchToScene("LoginView", stage, root);

            connexion.close(); // Ferme la connexion bdd
        }

        // Si aucun utilisateur n'est trouvé, ou si les hash ne correspondent pas
        // Fermeture de la connexion
        connexion.close();
    }
}
