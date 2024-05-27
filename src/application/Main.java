/*
 * Main.java                                        20 mai 2024
 * IUT de Rodez, pas de copyright.
 */
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

	Button compteroccurrencesBouton 
	= new Button("Compter les occurrences d'un fichier texte");
	Button creerArbreHuffmanBouton 
	= new Button("Créer un arbre Huffman à partir d'un fichier texte");
	Button compresserFichierBouton 
	= new Button("Compresser un fichier texte");
	Button decompresserFichierBouton 
	= new Button("Décompresser un fichier binaire");

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

	VBox vbox = new VBox(25, compteroccurrencesBouton,
		creerArbreHuffmanBouton, compresserFichierBouton,
		decompresserFichierBouton);
	vbox.setAlignment(javafx.geometry.Pos.CENTER);
	Scene scene = new Scene(vbox, 600, 400);
	primaryStage.setScene(scene);
	primaryStage.show();
    }

    private void gérerCompteroccurrences() {
	FileChooser fileChooser = new FileChooser();
	fileChooser.setTitle("Sélectionner un fichier texte (.txt)");
	FileChooser.ExtensionFilter extFilter 
	= new FileChooser.ExtensionFilter("Fichiers texte (*.txt)", "*.txt");
	fileChooser.getExtensionFilters().add(extFilter);
	File fichier = fileChooser.showOpenDialog(new Stage());
	if (fichier != null) {
	    try {
		String contenuFichier 
		= GestionFichierTexte.lireFichier(fichier.getAbsolutePath());
		if (contenuFichier == null || contenuFichier.isEmpty()) {
		    afficherPopUpErreur("Le fichier sélectionné est vide. "
		    	+ "Veuillez sélectionner un fichier valide.");
		    return;
		}
		Object[][] resultat 
		= CompterOccurrences.compter(contenuFichier);
		afficheroccurrencesDansTableau(resultat, 
			contenuFichier.length());
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

    private void afficheroccurrencesDansTableau(Object[][] occurrences,
	    int totalCaracteres) {
	Stage stage = new Stage();
	stage.setTitle("Occurrences des caractères");

	TableView<Occurrence> tableView = new TableView<>();
	ObservableList<Occurrence> data = FXCollections.observableArrayList();

	for (Object[] occurrence : occurrences) {
	    String caractere = occurrence[0].toString();
	    int count = (int) occurrence[1];
	    double frequence = (double) count / totalCaracteres;
	    data.add(new Occurrence(caractere, count, frequence));
	}

	TableColumn<Occurrence, String> caractereCol 
		= new TableColumn<>("Caractère");
	TableColumn<Occurrence, Integer> occurrencesCol 
		= new TableColumn<>("Occurrences");
	TableColumn<Occurrence, Double> frequenceCol 
		= new TableColumn<>("Fréquence");

	caractereCol.setCellValueFactory(
		new PropertyValueFactory<>("caractere"));
	occurrencesCol.setCellValueFactory(
		new PropertyValueFactory<>("occurrences"));
	frequenceCol.setCellValueFactory(
		new PropertyValueFactory<>("frequence"));

	tableView.setItems(data);
	tableView.getColumns().addAll(caractereCol, occurrencesCol,
		frequenceCol);

	tableView.widthProperty().addListener((obs, oldWidth, newWidth) -> {
	    double tableWidth = newWidth.doubleValue();
	    caractereCol.setPrefWidth(tableWidth / 3);
	    occurrencesCol.setPrefWidth(tableWidth / 3);
	    frequenceCol.setPrefWidth(tableWidth / 3);
	});

	VBox vbox = new VBox(tableView);
	Scene scene = new Scene(vbox);

	stage.setScene(scene);
	stage.show();
    }

    private void gérerCreerArbreHuffman() {
	FileChooser fileChooser = new FileChooser();
	fileChooser.setTitle("Sélectionner un fichier texte (.txt)");
	FileChooser.ExtensionFilter extFilter 
	= new FileChooser.ExtensionFilter("Fichiers texte (*.txt)", "*.txt");
	fileChooser.getExtensionFilters().add(extFilter);
	File fichier = fileChooser.showOpenDialog(new Stage());
	if (fichier != null) {
	    try {
		String contenuFichier 
		= GestionFichierTexte.lireFichier(fichier.getAbsolutePath());
		if (contenuFichier == null || contenuFichier.isEmpty()) {
		    afficherPopUpErreur("Le fichier sélectionné est vide. "
		    	+ "Veuillez sélectionner un fichier valide.");
		    return;
		}
		FileChooser saveFileChooser = new FileChooser();
		saveFileChooser.setTitle("Enregistrer l'arbre Huffman");
		saveFileChooser.setInitialFileName("dictionnaire_huffman.txt");
		File fichierEnregistrement 
		= saveFileChooser.showSaveDialog(new Stage());
		if (fichierEnregistrement != null) {
		    Object[][] occurrences 
		    = CompterOccurrences.compter(contenuFichier);
		    Noeud racine 
		    = ArbreHuffman.constructionArbreHuffman(occurrences);
		    GestionArbreHuffman.sauvegardeArbreHuffman(racine, 
			    fichierEnregistrement.getAbsolutePath());
		}
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}
    }

    private void gérerCompresserFichier() {
	FileChooser fileChooser = new FileChooser();
	fileChooser.setTitle("Sélectionner un fichier texte (.txt)");
	fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(
		"Fichiers texte (*.txt)", "*.txt"));
	File fichier = fileChooser.showOpenDialog(new Stage());
	if (fichier != null) {
	    try {
		String contenuFichier 
		= GestionFichierTexte.lireFichier(fichier.getAbsolutePath());
		if (contenuFichier == null || contenuFichier.isEmpty()) {
		    afficherPopUpErreur("Le fichier sélectionné est vide. "
		    	+ "Veuillez sélectionner un fichier valide.");
		    return;
		}
		FileChooser dictionaryChooser = new FileChooser();
		dictionaryChooser.setTitle("Sélectionner un dictionnaire "
			+ "Huffman (.txt)");
		dictionaryChooser.getExtensionFilters().add(
			new FileChooser.ExtensionFilter("Fichiers texte (*.txt)"
				, "*.txt"));
		File fichierDictionnaire 
		= dictionaryChooser.showOpenDialog(new Stage());
		if (fichierDictionnaire != null) {
		    FileChooser saveFileChooser = new FileChooser();
		    saveFileChooser.setTitle("Enregistrer le fichier compressé "
		    	+ "(.bin)");
		    saveFileChooser.setInitialFileName("fichier_compressé.bin");
		    saveFileChooser.getExtensionFilters().add(
			    new FileChooser.ExtensionFilter("Fichiers binaires "
			    	+ "(*.bin)", "*.bin"));
		    File fichierEnregistrement 
		    = saveFileChooser.showSaveDialog(new Stage());
		    if (fichierEnregistrement != null) {
			long startTime = System.currentTimeMillis();
			String lectureCompresse 
			= Compression.compresserTexte(contenuFichier, 
				GestionArbreHuffman.restaurerArbreHuffman(
					fichierDictionnaire.getAbsolutePath()));
			GestionFichierBinaire.ecriture(lectureCompresse, 
				fichierEnregistrement.getAbsolutePath());
			long compressionTime 
			= System.currentTimeMillis() - startTime;
			double tauxCompression 
			= TailleFichiers.tauxCompression(
				fichier.getAbsolutePath(), 
				fichierEnregistrement.getAbsolutePath());
			afficherPopUpInformations("Taille du fichier d'origine"
				+ " : " + fichier.length() + " octets" + 
				"\nTaille du fichier décompréssé : " 
				+ " octets" + fichierEnregistrement.length() 
				+ "\nTaux de compression : " + tauxCompression 
				+ "\nDurée de la compression : " 
				+ compressionTime + " ms");
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
	fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(
		"Fichiers binaires (*.bin)", "*.bin"));
	File fichier = fileChooser.showOpenDialog(new Stage());
	if (fichier != null) {
	    FileChooser dictionaryChooser = new FileChooser();
	    dictionaryChooser.setTitle("Sélectionner un dictionnaire Huffman"
	    	+ " (.txt)");
	    dictionaryChooser.getExtensionFilters().add(
		    new FileChooser.ExtensionFilter("Fichiers texte (*.txt)",
			    "*.txt"));
	    File fichierDictionnaire 
	    = dictionaryChooser.showOpenDialog(new Stage());
	    if (fichierDictionnaire != null) {
		FileChooser saveFileChooser = new FileChooser();
		saveFileChooser.setTitle("Enregistrer le fichier décompressé");
		saveFileChooser.setInitialFileName("fichier_decompressé.txt");
		saveFileChooser.getExtensionFilters().add(
			new FileChooser.ExtensionFilter("Fichiers texte (*.txt)"
				, "*.txt"));
		File fichierEnregistrement 
		= saveFileChooser.showSaveDialog(new Stage());
		if (fichierEnregistrement != null) {
		    try {
			long startTime = System.currentTimeMillis();
			String texteDecompresse 
			= Decompression.decompresser(fichier.getAbsolutePath(),
				GestionArbreHuffman.restaurerArbreHuffman(
					fichierDictionnaire.getAbsolutePath()));
			long decompressionTime 
			= System.currentTimeMillis() - startTime;
			GestionFichierTexte.ecrireFichier(texteDecompresse,
				fichierEnregistrement.getAbsolutePath());
			double tauxDecompression 
			= TailleFichiers.tauxCompression(
				fichier.getAbsolutePath(), 
				fichierEnregistrement.getAbsolutePath());
			afficherPopUpInformations(
				"Taille du fichier d'origine : " 
			+ fichier.length() + " octets" + "\nTaille du fichier "
				+ "décompréssé : " 
			+ fichierEnregistrement.length() + " octets" 
				+ "\nTaux de décompression : " 
			+ tauxDecompression + "\nDurée de la décompression : " 
				+ decompressionTime + " ms");
		    } catch (IOException e) {
			e.printStackTrace();
		    }
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

