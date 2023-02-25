package fr.tekcity.tekcity_launcher.functions;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class CallMinecraftAPI {

    // Fonction qui va récuperer l'UUID de l'utilisateur selon son nom d'utilisateur
    public static String getUUIDfromName(String name) throws Exception{

        // Créer une URL
        String url = "https://api.mojang.com/users/profiles/minecraft/" + name;

        // Créer une instance Gson
        Gson gson = new Gson();

        // 1. JSON file to Java object
        Object object = gson.fromJson(new FileReader(url), Object.class);

        Object object = gson.fromJson('uuid');

        return object;
    }

}
