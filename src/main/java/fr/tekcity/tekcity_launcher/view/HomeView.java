package fr.tekcity.tekcity_launcher.view;

import fr.tekcity.tekcity_launcher.functions.InitBackgroundView;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import org.jetbrains.annotations.Nullable;


public class HomeView extends Region {

    private Stage stage;
    private StackPane root;


    public HomeView(Stage stage, StackPane root, String alertMessage) {

        // On créer ci-dessous l'inteface d'installation, de mises à jour et de démarrage de Minecraft
        //-------------------------------------------------------------------------

        // On créer le panel de sélection de Jeu
        Pane game_panel = new Pane();
        game_panel.setPrefWidth(200);
        game_panel.setStyle("-fx-background-color: gray; -fx-opacity: 0.5;");

        AnchorPane game_box = new AnchorPane();
        game_box.getChildren().add(game_panel);
        AnchorPane.setTopAnchor(game_panel, 0.0);
        AnchorPane.setLeftAnchor(game_panel, 0.0);
        AnchorPane.setBottomAnchor(game_panel, 0.0);

        // On clear la root en conservant le webpane de particules en arrière plan
        ObservableList<Node> children = root.getChildren();
        for (int i = children.size() - 1; i >= 0; i--) {
            Node node = children.get(i);
            if (!(node.getId().equals("webPane"))) {
                children.remove(i); // On enlève tous les éléments sauf le webPane
            }
        }

        // On créer les identifiants pour pouvoir les supprimmer dans une prochaine scène
        game_box.setId("game_box");

        // On groupe les éléments dans la racine
        root.getChildren().add(game_box);


    }
}
