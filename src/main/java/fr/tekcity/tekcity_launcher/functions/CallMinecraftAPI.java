package fr.tekcity.tekcity_launcher.functions;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class CallMinecraftAPI {

    // Fonction qui va récuperer l'UUID de l'utilisateur selon son nom d'utilisateur
    public static String getUUIDfromName(String name) throws Exception{

        // Créer une URL
        String url = "https://api.mojang.com/users/profiles/minecraft/" + name;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(url).openStream()))) {
            // Créer une instance Gson
            Gson gson = new Gson();

            JsonObject jsonAPIObject = gson.fromJson(reader, JsonObject.class);

            // On vérifie si jsonAPIObject est null
            if(jsonAPIObject == null){

                // On déclare l'uuid de "player" pour juste afficher un skin qui existe
                String UUID_value_API = "bd346dd5ac1c427d87e873bdd4bf3e13";

                return UUID_value_API;

            }else{ // Si l'uuid existe

                String UUID_value_API = jsonAPIObject.get("id").getAsString();

                return UUID_value_API;
            }

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
