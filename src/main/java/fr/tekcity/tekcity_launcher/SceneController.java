package fr.tekcity.tekcity_launcher;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.HashMap;
import java.util.Map;

public class SceneController {

    private final Stage stage;
    private final Map<String, Scene> scenes = new HashMap<>();

    public SceneController(Stage stage) {
        this.stage = stage;
    }

    public void addScene(String name, Parent view) {

        // Nouvelle scene
        Scene scene = new Scene(view);

        // Fichiers de style
        scene.getStylesheets().add("ikonli.css");

        scenes.put(name, scene);
    }

    public void activate(String name) {
        Scene scene = scenes.get(name);
        if (scene != null) {
            stage.setScene(scene);
            stage.show();
        }
    }
}