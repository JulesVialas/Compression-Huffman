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

public class Controlleur {

    @FXML
    private TableView<Occurrence> tableView;

    @FXML
    private TableColumn<Occurrence, String> charColumn;

    @FXML
    private TableColumn<Occurrence, Integer> occurrenceColumn;

    private Modele modele;

    public Controlleur() {
	this.modele = new Modele();
    }

    @FXML
    public void initialize() {
	charColumn.setCellValueFactory(new PropertyValueFactory<>("caractere"));
	occurrenceColumn.setCellValueFactory(new PropertyValueFactory<>("occurrences"));
    }

    @FXML
    private void compterOccurrences() {
	File fichier = choisirFichier("Sélectionner un fichier texte (.txt)", "*.txt", false);
	if (fichier != null) {
	    try {
		List<Occurrence> occurrences = Modele.compterOccurrences(fichier.getAbsolutePath());
		afficherOccurrencesDansTableau(occurrences);
	    } catch (Exception e) {
		afficherAlerteErreur(e.getMessage());
	    }
	}
    }

    @FXML
    private void créerArbreHuffman() {
	File fichier = choisirFichier("Sélectionner un fichier texte (.txt)", "*.txt", false);
	if (fichier != null) {
	    try {
		File fichierEnregistrement = choisirFichier("Enregistrer l'arbre Huffman", "*.txt", true);
		if (fichierEnregistrement != null) {
		    modele.créerArbreHuffman(fichier.getAbsolutePath(), fichierEnregistrement.getAbsolutePath());
		}
	    } catch (Exception e) {
		afficherAlerteErreur(e.getMessage());
	    }
	}
    }

    @FXML
    private void compresserFichier() {
	File fichier = choisirFichier("Sélectionner un fichier texte (.txt)", "*.txt", false);
	if (fichier != null) {
	    try {
		File dictionnaire = choisirFichier("Sélectionner un dictionnaire Huffman (.txt)", "*.txt", false);
		if (dictionnaire != null) {
		    File fichierEnregistrement = choisirFichier("Enregistrer le fichier compressé (.bin)", "*.bin",
			    true);
		    if (fichierEnregistrement != null) {
			long startTime = System.nanoTime();
			modele.compresserFichier(fichier.getAbsolutePath(), dictionnaire.getAbsolutePath(),
				fichierEnregistrement.getAbsolutePath());
			long endTime = System.nanoTime();
			long duration = (endTime - startTime) / 1_000_000; // Convert to milliseconds

			double tauxCompression = modele.calculerTauxCompression(fichier.getAbsolutePath(),
				fichierEnregistrement.getAbsolutePath());
			afficherInfosCompression(fichier, fichierEnregistrement, duration, tauxCompression);
		    }
		}
	    } catch (Exception e) {
		afficherAlerteErreur(e.getMessage());
	    }
	}
    }

    @FXML
    private void decompresserFichier() {
	File fichier = choisirFichier("Sélectionner un fichier binaire (.bin)", "*.bin", false);
	if (fichier != null) {
	    try {
		File dictionnaire = choisirFichier("Sélectionner un dictionnaire Huffman (.txt)", "*.txt", false);
		if (dictionnaire != null) {
		    File fichierEnregistrement = choisirFichier("Enregistrer le fichier décompressé", "*.txt", true);
		    if (fichierEnregistrement != null) {
			long startTime = System.nanoTime();
			modele.decompresserFichier(fichier.getAbsolutePath(), dictionnaire.getAbsolutePath(),
				fichierEnregistrement.getAbsolutePath());
			long endTime = System.nanoTime();
			long duration = (endTime - startTime) / 1_000_000; // Convert to milliseconds

			double tauxDecompression = modele.calculerTauxCompression(fichier.getAbsolutePath(),
				fichierEnregistrement.getAbsolutePath());
			afficherInfosDecompression(fichier, fichierEnregistrement, duration, tauxDecompression);
		    }
		}
	    } catch (Exception e) {
		afficherAlerteErreur(e.getMessage());
	    }
	}
    }

    private File choisirFichier(String title, String extension, boolean save) {
	FileChooser fileChooser = new FileChooser();
	fileChooser.setTitle(title);
	fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichiers", extension));
	return save ? fileChooser.showSaveDialog(null) : fileChooser.showOpenDialog(null);
    }

    private void afficherAlerteErreur(String message) {
	Alert alert = new Alert(Alert.AlertType.ERROR);
	alert.setTitle("Erreur");
	alert.setHeaderText(null);
	alert.setContentText(message);
	alert.showAndWait();
    }

    private void afficherInfosCompression(File originalFile, File compressedFile, long duration,
	    double tauxCompression) {
	Alert alert = new Alert(Alert.AlertType.INFORMATION);
	alert.setTitle("Compression réussie");
	alert.setHeaderText(null);
	alert.setContentText(String.format(
		"Taille du fichier initial : %d bytes\n" + "Taille du fichier compressé : %d bytes\n"
			+ "Temps de compression : %d ms\n" + "Taux de compression : %.2f",
		originalFile.length(), compressedFile.length(), duration, tauxCompression));
	alert.showAndWait();
    }

    private void afficherInfosDecompression(File originalFile, File decompressedFile, long duration,
	    double tauxDecompression) {
	Alert alert = new Alert(Alert.AlertType.INFORMATION);
	alert.setTitle("Décompression réussie");
	alert.setHeaderText(null);
	alert.setContentText(String.format(
		"Taille du fichier compressé : %d bytes\n" + "Taille du fichier décompressé : %d bytes\n"
			+ "Temps de décompression : %d ms\n" + "Taux de décompression : %.2f",
		originalFile.length(), decompressedFile.length(), duration, tauxDecompression));
	alert.showAndWait();
    }

    private void afficherOccurrencesDansTableau(List<Occurrence> occurrences) {
	tableView.getItems().clear();
	tableView.getItems().addAll(occurrences);
    }
}
