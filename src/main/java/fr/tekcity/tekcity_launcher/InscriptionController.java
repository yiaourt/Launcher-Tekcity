package fr.tekcity.tekcity_launcher;

import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import org.kordamp.ikonli.Ikon;
import org.kordamp.ikonli.Ikon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;

public class InscriptionController implements EventHandler<ActionEvent> {

    private MFXTextField usernameField;
    private MFXTextField mailField;
    private MFXPasswordField passwordField;

    private Text errorMessage;

    public InscriptionController(MFXTextField usernameField,
                                 MFXTextField mailField,
                                 MFXPasswordField passwordField,
                                 Text errorMessage) {
        this.usernameField = usernameField;
        this.mailField = mailField;
        this.passwordField = passwordField;
        this.errorMessage = errorMessage;
    }

    @Override
    public void handle(ActionEvent event) {

        // On récupère les valeurs du formulaire
        String username = usernameField.getText();
        String mail = mailField.getText();
        String password = passwordField.getText();

        // Vérifier le nom d'utilisateur et le mot de passe ici
        System.out.println(username + " " + mail + " "+ password);

        // On vérifie que le nom d'utilisateur le mail et le mot de passe sont remplis dans le formulaire.
        if (username.isEmpty()) {
            // Si le nom d'utilisateur est vide, focus rouge sur les bords du formulaire
           usernameField.requestFocus();
           usernameField.selectAll();
           usernameField.setBorder(new Border(new BorderStroke(Color.INDIANRED, BorderStrokeStyle.SOLID, null, new BorderWidths(3))));
            // erreur icon
            Ikon warningIcon = (Ikon) IkonResolver.getInstance().resolve("fa-warning-circle");

            // Erreur definition
            errorMessage.setText("Le nom d'utilisateur est vide");
            errorMessage.setVisible(true);


        }
    }
}