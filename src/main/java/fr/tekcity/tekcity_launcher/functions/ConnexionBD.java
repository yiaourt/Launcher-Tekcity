package fr.tekcity.tekcity_launcher.functions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnexionBD {

    public static Connection getConnexion() throws SQLException {

        // Fonction qui permet de récupérer la connexion à la base de données
        String url = "jdbc:mysql://localhost:3306/tekcity";
        String user = "root";
        String password = "";

        return DriverManager.getConnection(url, user, password);
    }
}