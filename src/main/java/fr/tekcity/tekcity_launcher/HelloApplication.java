package fr.tekcity.tekcity_launcher;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        // On paramètre la fenêtre de l'application
        Group root = new Group();
        Scene scene = new Scene(root, 1020, 500, Color.BLACK);
        stage.setTitle("TekCity Launcher");

        Stage connexionStage = new Stage();

        Rectangle co = new Rectangle(500, 505, Color.LIGHTGRAY);
        co.setX(50);
        co.setY(50);

        root.getChildren().add(co);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}