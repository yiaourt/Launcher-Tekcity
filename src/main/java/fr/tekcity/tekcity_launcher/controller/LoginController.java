package fr.tekcity.tekcity_launcher.controller;

import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController implements EventHandler<ActionEvent> {

    private MFXTextField usernameField;
    private MFXPasswordField passwordField;

    public LoginController(MFXTextField usernameField, MFXPasswordField passwordField) {
        this.usernameField = usernameField;
        this.passwordField = passwordField;
    }

    @Override
    public void handle(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Vérifier le nom d'utilisateur et le mot de passe ici
        System.out.println(username + " " + password);
    }
}