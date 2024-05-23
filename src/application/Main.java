package application;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

import gestion.GestionArbreHuffman;
import gestion.GestionFichierBinaire;
import gestion.GestionFichierTexte;
import gestion.TailleFichiers;
import huffman.ArbreHuffman;
import huffman.Compression;
import huffman.CompterOccurrences;
import huffman.Decompression;
import huffman.Noeud;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
	primaryStage.setTitle("Application Huffman");

	Button compteroccurrencesBouton = new Button("Compter les occurrences d'un fichier texte");
	Button creerArbreHuffmanBouton = new Button("Créer un arbre Huffman à partir d'un fichier texte");
	Button compresserFichierBouton = new Button("Compresser un fichier texte");
	Button decompresserFichierBouton = new Button("Décompresser un fichier binaire");

	compteroccurrencesBouton.setOnAction(new EventHandler<ActionEvent>() {
	    @Override
	    public void handle(ActionEvent event) {
		gérerCompteroccurrences();
	    }
	});

	creerArbreHuffmanBouton.setOnAction(new EventHandler<ActionEvent>() {
	    @Override
	    public void handle(ActionEvent event) {
		gérerCreerArbreHuffman();
	    }
	});

	compresserFichierBouton.setOnAction(new EventHandler<ActionEvent>() {
	    @Override
	    public void handle(ActionEvent event) {
		gérerCompresserFichier();
	    }
	});

	decompresserFichierBouton.setOnAction(new EventHandler<ActionEvent>() {
	    @Override
	    public void handle(ActionEvent event) {
		gérerDecompresserFichier();
	    }
	});

	VBox vbox = new VBox(25, compteroccurrencesBouton, creerArbreHuffmanBouton, compresserFichierBouton, decompresserFichierBouton);
	vbox.setAlignment(javafx.geometry.Pos.CENTER);
	Scene scene = new Scene(vbox, 600, 400);
	primaryStage.setScene(scene);
	primaryStage.show();
    }

    private void gérerCompteroccurrences() {
	FileChooser fileChooser = new FileChooser();
	fileChooser.setTitle("Sélectionner un fichier texte (.txt)");
	FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Fichiers texte (*.txt)", "*.txt");
	fileChooser.getExtensionFilters().add(extFilter);
	File fichier = fileChooser.showOpenDialog(new Stage());
	if (fichier != null) {
	    try {
		String contenuFichier = GestionFichierTexte.lireFichier(fichier.getAbsolutePath());
		if (contenuFichier == null || contenuFichier.isEmpty()) {
		    afficherPopUpErreur("Le fichier sélectionné est vide. Veuillez sélectionner un fichier valide.");
		    return;
		}
		Object[][] resultat = CompterOccurrences.compter(contenuFichier);
		afficheroccurrencesDansTableau(resultat);
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}
    }

    private void afficherPopUpErreur(String message) {
	Alert alert = new Alert(Alert.AlertType.ERROR);
	alert.setTitle("Informations");
	alert.setHeaderText(null);
	alert.setContentText(message);
	alert.showAndWait();
    }

    private void afficheroccurrencesDansTableau(Object[][] occurrences) {
	Stage stage = new Stage();
	stage.setTitle("occurrences");

	TableView<Occurrence> tableView = new TableView<>();
	TableColumn<Occurrence, String> colonneCaractere = new TableColumn<>("Caractère");
	colonneCaractere.setCellValueFactory(new PropertyValueFactory<>("caractere"));

	TableColumn<Occurrence, Integer> colonneOccurrences = new TableColumn<>("occurrences");
	colonneOccurrences.setCellValueFactory(new PropertyValueFactory<>("occurrences"));

	TableColumn<Occurrence, Double> colonneFrequence = new TableColumn<>("frequence");
	colonneFrequence.setCellValueFactory(new PropertyValueFactory<>("frequence"));

	colonneCaractere.prefWidthProperty().bind(tableView.widthProperty().multiply(0.3));
	colonneOccurrences.prefWidthProperty().bind(tableView.widthProperty().multiply(0.35));
	colonneFrequence.prefWidthProperty().bind(tableView.widthProperty().multiply(0.35));

	tableView.getColumns().addAll(colonneCaractere, colonneOccurrences, colonneFrequence);

	ObservableList<Occurrence> data = FXCollections.observableArrayList();
	int totaloccurrences = 0;
	for (Object[] element : occurrences) {
	    totaloccurrences += (int) element[1];
	}

	for (Object[] element : occurrences) {
	    char caractere = (char) element[0];
	    int occurrences1 = (int) element[1];
	    double frequence = (double) occurrences1 / totaloccurrences * 100;
	    data.add(new Occurrence(caractere, occurrences1, frequence));
	}

	tableView.setItems(data);

	VBox vbox = new VBox(tableView);
	Scene scene = new Scene(vbox);
	stage.setScene(scene);
	stage.show();
    }

    private void gérerCreerArbreHuffman() {
	FileChooser fileChooser = new FileChooser();
	fileChooser.setTitle("Sélectionner un fichier texte (.txt)");
	FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Fichiers texte (*.txt)", "*.txt");
	fileChooser.getExtensionFilters().add(extFilter);
	File fichier = fileChooser.showOpenDialog(new Stage());
	if (fichier != null) {
	    try {
		String contenuFichier = GestionFichierTexte.lireFichier(fichier.getAbsolutePath());
		if (contenuFichier == null || contenuFichier.isEmpty()) {
		    afficherPopUpErreur("Le fichier sélectionné est vide. Veuillez sélectionner un fichier valide.");
		    return;
		}
		FileChooser saveFileChooser = new FileChooser();
		saveFileChooser.setTitle("Enregistrer l'arbre Huffman");
		saveFileChooser.setInitialFileName("arbre_huffman.txt");
		File fichierEnregistrement = saveFileChooser.showSaveDialog(new Stage());
		if (fichierEnregistrement != null) {
		    Object[][] occurrences = CompterOccurrences.compter(contenuFichier);
		    Noeud racine = ArbreHuffman.constructionArbreHuffman(occurrences);
		    GestionArbreHuffman.sauvegardeArbreHuffman(racine, fichierEnregistrement.getAbsolutePath());
		}
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}
    }

    private void gérerCompresserFichier() {
	FileChooser fileChooser = new FileChooser();
	fileChooser.setTitle("Sélectionner un fichier texte (.txt)");
	FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Fichiers texte (*.txt)", "*.txt");
	fileChooser.getExtensionFilters().add(extFilter);
	File fichier = fileChooser.showOpenDialog(new Stage());
	if (fichier != null) {
	    try {
		String contenuFichier = GestionFichierTexte.lireFichier(fichier.getAbsolutePath());
		if (contenuFichier == null || contenuFichier.isEmpty()) {
		    afficherPopUpErreur("Le fichier sélectionné est vide. Veuillez sélectionner un fichier valide.");
		    return;
		}
		FileChooser dictionaryChooser = new FileChooser();
		dictionaryChooser.setTitle("Sélectionner un dictionnaire Huffman (.txt)");
		dictionaryChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichiers texte (*.txt)", "*.txt"));
		File fichierDictionnaire = dictionaryChooser.showOpenDialog(new Stage());
		if (fichierDictionnaire != null) {
		    FileChooser saveFileChooser = new FileChooser();
		    saveFileChooser.setTitle("Enregistrer le fichier compressé (.bin)");
		    saveFileChooser.setInitialFileName("fichier_compressé.bin");
		    File fichierEnregistrement = saveFileChooser.showSaveDialog(new Stage());
		    if (fichierEnregistrement != null) {
			long startTime = System.currentTimeMillis();
			String lectureCompresse = Compression.compresserTexte(contenuFichier, GestionArbreHuffman.restaurerArbreHuffman(fichierDictionnaire.getAbsolutePath()));
			GestionFichierBinaire.ecriture(lectureCompresse, fichierEnregistrement.getAbsolutePath());
			long compressionTime = System.currentTimeMillis() - startTime;
			double tauxCompression = TailleFichiers.tauxCompression(fichier.getName(), fichierEnregistrement.getName());
			afficherPopUpInformations("Taux de compression : " + tauxCompression + "\nDurée de la compression : " + compressionTime + " ms");
		    }
		}
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}
    }

    private void gérerDecompresserFichier() {
	FileChooser fileChooser = new FileChooser();
	fileChooser.setTitle("Sélectionner un fichier binaire (.bin)");
	FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Fichiers binaires (*.bin)", "*.bin");
	fileChooser.getExtensionFilters().add(extFilter);
	File fichier = fileChooser.showOpenDialog(new Stage());
	if (fichier != null) {
	    FileChooser dictionaryChooser = new FileChooser();
	    dictionaryChooser.setTitle("Sélectionner un dictionnaire Huffman (.txt)");
	    dictionaryChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichiers texte (*.txt)", "*.txt"));
	    File fichierDictionnaire = dictionaryChooser.showOpenDialog(new Stage());
	    if (fichierDictionnaire != null) {
		String texteDecompresse = null;
		try {
		    long startTime = System.currentTimeMillis();
		    texteDecompresse = Decompression.decompresser(fichier.getAbsolutePath(), GestionArbreHuffman.restaurerArbreHuffman(fichierDictionnaire.getAbsolutePath()));
		    long decompressionTime = System.currentTimeMillis() - startTime;
		    double tauxDecompression = TailleFichiers.tauxCompression(fichier.getName(), fichier.getName() + "Decompresse.txt");
		    afficherPopUpInformations("Taux de décompression : " + tauxDecompression + "\nDurée de la décompression : " + decompressionTime + " ms");
		    GestionFichierTexte.ecrireFichier(texteDecompresse, fichier.getName() + "Decompresse.txt");
		} catch (IOException e) {
		    e.printStackTrace();
		}
	    }
	}
    }
    private void afficherPopUpInformations(String message) {
	Alert alert = new Alert(Alert.AlertType.INFORMATION);
	alert.setTitle("Résultat de la compression");
	alert.setHeaderText(null);
	alert.setContentText(message);
	alert.showAndWait();
    }

    public static void main(String[] args) {
	launch(args);
    }
}

