package fr.tekcity.tekcity_launcher.functions;

import javafx.animation.FadeTransition;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class InitBackgroundView {


    public StackPane InitBackgroundView(Stage stage){


        // Fonction qui initialise l'arrière plan de fond de l'application
        // ----------------------------------------------------------------
        // On initialise l'effet de flou
        BoxBlur blur = new BoxBlur(20, 0, 3);

        // Crée une StackPane pour superposer l'image de fond floue et la zone d'inteface par la suite
        StackPane root = new StackPane();
        root.setPrefSize(stage.getWidth(), stage.getHeight());

        // On met le fond de l'application en noir
        BackgroundFill backgroundFill = new BackgroundFill(Color.BLACK, null, null);
        Background background = new Background(backgroundFill);
        root.setBackground(background);

        // Créer une image de fond
        Image img_bg = new Image("file:src/images/BG_tekcity_launcher.jpg");
        ImageView img_bg_view = new ImageView(img_bg);
        // Ajoute un effet de flou sur l'image de fond
        img_bg_view.setEffect(blur);
        // Ajuste la taille de l'ImageView pour qu'il remplisse la scène
        img_bg_view.fitWidthProperty().bind(stage.widthProperty());
        img_bg_view.fitHeightProperty().bind(stage.heightProperty());

        // On créer une animation d'apparition
        FadeTransition fadeIN_background = new FadeTransition(Duration.millis(4500), img_bg_view);
        fadeIN_background.setFromValue(0.0);
        fadeIN_background.setToValue(1.0);
        fadeIN_background.play();

        root.getChildren().add(img_bg_view);

        return root;
    }
}
