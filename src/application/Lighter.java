/*
 * Lighter.java                                          3 juin 2024
 * IUT de Rodez, pas de copyright.
 */
package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * La classe Main est le point d'entrée de l'application JavaFX. Elle
 * charge et affiche l'interface utilisateur définie dans le fichier
 * FXML.
 *
 * @author Jules Vialas
 */
public class Lighter extends Application {

    /**
     * La méthode start est appelée au lancement de l'application. Elle
     * charge le fichier FXML et configure la fenêtre principale de
     * l'application.
     *
     * @param primaryStage la fenêtre principale de l'application
     */
    @Override
    public void start(Stage primaryStage) {
        try {
            // Chargement du fichier FXML et initialisation de la scène
            Parent root = FXMLLoader.load(getClass().getResource("Vue.fxml"));
            primaryStage.setTitle("Lighter");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (Exception e) {
            // Affichage des erreurs potentielles lors du chargement du fichier
            // FXML
            e.printStackTrace();
        }
    }

    /**
     * La méthode main est le point d'entrée de l'application Java. Elle
     * lance l'application JavaFX.
     *
     * @param args les arguments de la ligne de commande
     */
    public static void main(String[] args) {
        launch(args);
    }
}
