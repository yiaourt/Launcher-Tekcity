package fr.tekcity.tekcity_launcher.functions;

import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import java.io.InputStream;


// Cette classe permet de charger un svg pour l'afficher. (voir dossier de ressources "svg")
public class SVGIconLoader {

    public static SVGPath loadSVGIcon(String iconName, String style, Color color) {

        String path = "/svg/" + style + "/" + iconName + ".svg";

        InputStream stream = SVGIconLoader.class.getResourceAsStream(path);

        SVGPath svgPath = new SVGPath();

        // On charge le svg
        svgPath.setContent(readStream(stream));

        // On modifie la couleur
        svgPath.setFill(color);

        // On retourne le svg
        return svgPath;
    }

    // Fonction pour lire un svg
    private static String readStream(InputStream stream) {
        java.util.Scanner s = new java.util.Scanner(stream).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
}