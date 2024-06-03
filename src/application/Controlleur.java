/*
 * Controlleur.java                                          3 juin 2024
 * IUT de Rodez, pas de copyright.
 */
package application;

import java.io.File;
import java.util.List;

import huffman.Occurrence;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;

/**
 * La classe Controlleur gère l'interface utilisateur pour
 * l'application de compression et décompression Huffman. Elle permet
 * de charger des fichiers, de calculer les occurrences de caractères,
 * de créer des arbres de Huffman, de compresser et de décompresser
 * des fichiers. Les informations sur les occurrences de caractères
 * sont affichées dans un tableau.
 * <p>
 * Cette classe utilise le modèle de l'application pour effectuer les
 * opérations sur les fichiers et afficher les résultats dans
 * l'interface utilisateur.
 * </p>
 * <ul>
 * <li>Compter les occurrences de caractères dans un fichier
 * texte</li>
 * <li>Créer un arbre de Huffman à partir d'un fichier texte</li>
 * <li>Compresser un fichier texte en utilisant un dictionnaire
 * Huffman</li>
 * <li>Décompresser un fichier binaire en utilisant un dictionnaire
 * Huffman</li>
 * </ul>
 *
 * @author Jules Vialas
 */
public class Controlleur {

    /** Titre du dialogue pour sélectionner un fichier texte (.txt). */
    private static final String SELECTIONNER_FICHIER_TEXTE = "Sélectionner un fichier texte (.txt)";

    /** Titre du dialogue pour enregistrer l'arbre de Huffman. */
    private static final String ENREGISTRER_ARBRE_HUFFMAN = "Enregistrer l'arbre Huffman";

    /**
     * Titre du dialogue pour sélectionner un dictionnaire Huffman (.txt).
     */
    private static final String SELECTIONNER_DICTIONNAIRE_HUFFMAN = "Sélectionner un dictionnaire Huffman (.txt)";

    /** Titre du dialogue pour enregistrer le fichier compressé (.bin). */
    private static final String ENREGISTRER_FICHIER_COMPRESSE = "Enregistrer le fichier compressé (.bin)";

    /** Titre du dialogue pour enregistrer le fichier décompressé. */
    private static final String ENREGISTRER_FICHIER_DECOMPRESSE = "Enregistrer le fichier décompressé";

    /** Filtre pour les fichiers texte (.txt). */
    private static final String FICHIERS_TEXTE = "*.txt";

    /** Filtre pour les fichiers binaires (.bin). */
    private static final String FICHIERS_BINAIRES = "*.bin";

    /** Message d'erreur pour les dialogues d'alerte. */
    private static final String ERREUR = "Erreur";

    /**
     * Message de succès pour les dialogues d'alerte de compression
     * réussie.
     */
    private static final String COMPRESSION_REUSSIE = "Compression réussie";

    /**
     * Message de succès pour les dialogues d'alerte de décompression
     * réussie.
     */
    private static final String DECOMPRESSION_REUSSIE = "Décompression réussie";

    /** Format du message affichant les détails de la compression. */
    private static final String MESSAGE_TAUX_COMPRESSION = "Taille du fichier initial : %d bytes\nTaille du fichier"
            + " compressé : %d bytes\nTemps de compression : %d ms\n"
            + "Taux de compression : %.2f";

    /** Format du message affichant les détails de la décompression. */
    private static final String MESSAGE_TAUX_DECOMPRESSION = "Taille du fichier compressé : %d bytes\nTaille du fichier"
            + " décompressé : %d bytes\nTemps de décompression : %d ms"
            + "\nTaux de décompression : %.2f";

    @FXML
    private TableView<Occurrence> tableView;

    @FXML
    private TableColumn<Occurrence, String> charColumn;

    @FXML
    private TableColumn<Occurrence, Integer> occurrenceColumn;

    private Modele modele;

    /**
     * Constructeur de la classe Controlleur. Initialise le modèle de
     * l'application.
     */
    public Controlleur() {
        this.modele = new Modele();
    }

    /**
     * Initialise les colonnes de la TableView pour afficher les
     * occurrences de caractères.
     */
    @FXML
    public void initialize() {
        charColumn.setCellValueFactory(new PropertyValueFactory<>("caractere"));
        occurrenceColumn
                .setCellValueFactory(new PropertyValueFactory<>("occurrences"));
    }

    /**
     * Ouvre un dialogue pour sélectionner un fichier texte (.txt) et
     * compte les occurrences de chaque caractère dans le fichier
     * sélectionné. Les résultats sont affichés dans le tableau.
     */
    @FXML
    private void compterOccurrences() {
        File fichier = choisirFichier(SELECTIONNER_FICHIER_TEXTE,
                FICHIERS_TEXTE, false);
        if (fichier != null) {
            try {
                List<Occurrence> occurrences = Modele
                        .compterOccurrences(fichier.getAbsolutePath());
                afficherOccurrencesDansTableau(occurrences);
            } catch (Exception e) {
                afficherAlerteErreur(e.getMessage());
            }
        }
    }

    /**
     * Ouvre un dialogue pour sélectionner un fichier texte (.txt), puis
     * un autre dialogue pour enregistrer l'arbre de Huffman créé à partir
     * du fichier texte sélectionné.
     */
    @FXML
    private void créerArbreHuffman() {
        File fichier = choisirFichier(SELECTIONNER_FICHIER_TEXTE,
                FICHIERS_TEXTE, false);
        if (fichier != null) {
            try {
                File fichierEnregistrement = choisirFichier(
                        ENREGISTRER_ARBRE_HUFFMAN, FICHIERS_TEXTE, true);
                if (fichierEnregistrement != null) {
                    modele.créerArbreHuffman(fichier.getAbsolutePath(),
                            fichierEnregistrement.getAbsolutePath());
                }
            } catch (Exception e) {
                afficherAlerteErreur(e.getMessage());
            }
        }
    }

    /**
     * Ouvre un dialogue pour sélectionner un fichier texte (.txt) et un
     * dictionnaire Huffman (.txt), puis un autre dialogue pour
     * enregistrer le fichier compressé en format binaire (.bin). Affiche
     * les informations sur la compression après l'opération.
     */
    @FXML
    private void compresserFichier() {
        File fichier = choisirFichier(SELECTIONNER_FICHIER_TEXTE,
                FICHIERS_TEXTE, false);
        if (fichier != null) {
            try {
                File dictionnaire = choisirFichier(
                        SELECTIONNER_DICTIONNAIRE_HUFFMAN, FICHIERS_TEXTE,
                        false);
                if (dictionnaire != null) {
                    File fichierEnregistrement = choisirFichier(
                            ENREGISTRER_FICHIER_COMPRESSE, FICHIERS_BINAIRES,
                            true);
                    if (fichierEnregistrement != null) {
                        long startTime = System.nanoTime();
                        modele.compresserFichier(fichier.getAbsolutePath(),
                                dictionnaire.getAbsolutePath(),
                                fichierEnregistrement.getAbsolutePath());
                        long endTime = System.nanoTime();
                        long duration = (endTime - startTime) / 1_000_000;

                        double tauxCompression = modele.calculerTauxCompression(
                                fichier.getAbsolutePath(),
                                fichierEnregistrement.getAbsolutePath());
                        afficherInfosCompression(fichier, fichierEnregistrement,
                                duration, tauxCompression);
                    }
                }
            } catch (Exception e) {
                afficherAlerteErreur(e.getMessage());
            }
        }
    }

    /**
     * Ouvre un dialogue pour sélectionner un fichier binaire (.bin) et un
     * dictionnaire Huffman (.txt), puis un autre dialogue pour
     * enregistrer le fichier décompressé en format texte (.txt). Affiche
     * les informations sur la décompression après l'opération.
     */
    @FXML
    private void decompresserFichier() {
        File fichier = choisirFichier("Sélectionner un fichier binaire (.bin)",
                FICHIERS_BINAIRES, false);
        if (fichier != null) {
            try {
                File dictionnaire = choisirFichier(
                        SELECTIONNER_DICTIONNAIRE_HUFFMAN, FICHIERS_TEXTE,
                        false);
                if (dictionnaire != null) {
                    File fichierEnregistrement = choisirFichier(
                            ENREGISTRER_FICHIER_DECOMPRESSE, FICHIERS_TEXTE,
                            true);
                    if (fichierEnregistrement != null) {
                        long startTime = System.nanoTime();
                        modele.decompresserFichier(fichier.getAbsolutePath(),
                                dictionnaire.getAbsolutePath(),
                                fichierEnregistrement.getAbsolutePath());
                        long endTime = System.nanoTime();
                        long duration = (endTime - startTime) / 1_000_000;

                        double tauxDecompression = modele
                                .calculerTauxCompression(
                                        fichierEnregistrement.getAbsolutePath(),
                                        fichier.getAbsolutePath());
                        afficherInfosDecompression(fichier,
                                fichierEnregistrement, duration,
                                tauxDecompression);
                    }
                }
            } catch (Exception e) {
                afficherAlerteErreur(e.getMessage());
            }
        }
    }

    /**
     * Ouvre un dialogue de sélection de fichier.
     *
     * @param title     le titre du dialogue de sélection
     * @param extension l'extension de fichier autorisée
     * @param save      true pour un dialogue de sauvegarde, false pour un
     *                  dialogue d'ouverture
     * @return le fichier sélectionné, ou null si aucun fichier n'a été
     *         sélectionné
     */
    private File choisirFichier(String title, String extension, boolean save) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(title);
        fileChooser.getExtensionFilters()
                .add(new FileChooser.ExtensionFilter("Fichiers", extension));
        return save ? fileChooser.showSaveDialog(null)
                : fileChooser.showOpenDialog(null);
    }

    /**
     * Affiche une alerte d'erreur avec un message spécifié.
     *
     * @param message le message à afficher dans l'alerte
     */
    private void afficherAlerteErreur(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(ERREUR);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Affiche les informations sur la compression dans une alerte.
     *
     * @param originalFile    le fichier original
     * @param compressedFile  le fichier compressé
     * @param duration        la durée de la compression en millisecondes
     * @param tauxCompression le taux de compression
     */
    private void afficherInfosCompression(File originalFile,
            File compressedFile, long duration, double tauxCompression) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(COMPRESSION_REUSSIE);
        alert.setHeaderText(null);
        alert.setContentText(
                String.format(MESSAGE_TAUX_COMPRESSION, originalFile.length(),
                        compressedFile.length(), duration, tauxCompression));
        alert.showAndWait();
    }

    /**
     * Affiche les informations sur la décompression dans une alerte.
     *
     * @param originalFile      le fichier compressé
     * @param decompressedFile  le fichier décompressé
     * @param duration          la durée de la décompression en
     *                          millisecondes
     * @param tauxDecompression le taux de décompression
     */
    private void afficherInfosDecompression(File originalFile,
            File decompressedFile, long duration, double tauxDecompression) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(DECOMPRESSION_REUSSIE);
        alert.setHeaderText(null);
        alert.setContentText(String.format(MESSAGE_TAUX_DECOMPRESSION,
                originalFile.length(), decompressedFile.length(), duration,
                tauxDecompression));
        alert.showAndWait();
    }

    /**
     * Affiche les occurrences de caractères dans le tableau.
     *
     * @param occurrences la liste des occurrences de caractères à
     *                    afficher
     */
    private void afficherOccurrencesDansTableau(List<Occurrence> occurrences) {
        tableView.getItems().setAll(occurrences);
    }
}
