package fr.tekcity.tekcity_launcher.functions;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javafx.geometry.Pos;

public class AlertUser {

    public static BorderPane alert(String message) {

        // Fonction qui affiche une boîte de dialogue pour alerter un utilisateur avec un message
        BorderPane pane_alert = new BorderPane();
        pane_alert.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
        pane_alert.setMaxHeight(200);
        pane_alert.setMaxWidth(300);

        // Créer la boîte flottante
        HBox box = new HBox();
        box.setAlignment(Pos.CENTER_RIGHT);
        box.setPadding(new Insets(10));
        box.getChildren().add(new Label(message));

        // Ajouter la boîte à la région supérieure droite
        pane_alert.setTop(box);
        BorderPane.setAlignment(box, Pos.TOP_RIGHT);

        return pane_alert;
    }
}
