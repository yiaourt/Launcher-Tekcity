package fr.tekcity.tekcity_launcher.view;

import fr.tekcity.tekcity_launcher.functions.InitBackgroundView;
import javafx.geometry.Pos;
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

        // On groupe les éléments dans la racine
        root.getChildren().add(game_box);

        stage.setScene(new Scene(root));

        stage.show();

    }
}
