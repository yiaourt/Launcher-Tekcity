package fr.tekcity.tekcity_launcher.functions;

import javafx.animation.FadeTransition;
import javafx.concurrent.Worker;
import javafx.event.Event;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Objects;

public class InitBackgroundView {


    public StackPane InitBackgroundView(Stage stage){


        // Fonction qui initialise l'arrière plan de fond de l'application
        // ----------------------------------------------------------------


        // Crée une StackPane pour superposer l'image de fond floue et la zone d'inteface par la suite
        StackPane root = new StackPane();
        root.setPrefSize(stage.getWidth(), stage.getHeight());

        // On met le fond de l'application en noir
        BackgroundFill backgroundFill = new BackgroundFill(Color.BLACK, null, null);
        Background background = new Background(backgroundFill);
        root.setBackground(background);





        return root;
    }
}
