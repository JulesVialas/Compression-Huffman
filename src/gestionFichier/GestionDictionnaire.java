package gestionFichier;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GestionDictionnaire {

    /**
     * Reconstruit le dictionnaire de Huffman à partir du fichier donné.
     *
     * @param cheminFichier Le chemin du fichier contenant le dictionnaire
     *                      de Huffman.
     * @return Le dictionnaire de Huffman sous forme de tableau
     *         bidimensionnel d'objets.
     * @throws IOException Si une erreur d'entrée/sortie se produit lors
     *                     de la lecture du fichier.
     */
    public static Object[][] reconstruireDictionnaire(String cheminFichier)
	    throws IOException {
	BufferedReader reader = null;
	Object[][] dictionnaire = null;
	int nombreLignes = compterLignes(cheminFichier);

	try {
	    reader = new BufferedReader(new FileReader(cheminFichier));
	    dictionnaire = new Object[nombreLignes][2];
	    String ligne;
	    int index = 0;
	    while ((ligne = reader.readLine()) != null) {
		String[] elements = ligne.split(" : ");
		if (elements.length == 2) {
		    dictionnaire[index][0] = elements[0];
		    dictionnaire[index][1] = elements[1];
		    index++;
		} else {
		    // Gérer une erreur si une ligne du fichier n'est pas au
		    // format attendu
		    System.err.println(
			    "Erreur : Ligne du fichier au format incorrect");
		}
	    }
	} finally {
	    if (reader != null) {
		reader.close();
	    }
	}
	return dictionnaire;
    }

    /**
     * Reconstruit le tableau de tailles à partir du fichier donné.
     *
     * @param cheminFichier Le chemin du fichier contenant le tableau de
     *                      tailles.
     * @return Le tableau de tailles sous forme de tableau d'entiers.
     * @throws IOException Si une erreur d'entrée/sortie se produit lors
     *                     de la lecture du fichier.
     */
    public static int[] reconstruireTableauTailles(String cheminFichier)
	    throws IOException {
	BufferedReader reader = null;
	String contenuFichier = "";

	// Lecture du contenu du fichier dans une chaîne de caractères
	try {
	    reader = new BufferedReader(new FileReader(cheminFichier));
	    String ligne;
	    while ((ligne = reader.readLine()) != null) {
		contenuFichier += ligne.trim(); // Supprimer les espaces
		// inutiles
	    }
	} finally {
	    if (reader != null) {
		reader.close();
	    }
	}

	// Création du tableau de tailles avec une case pour chaque chiffre
	// dans la chaîne
	int[] tableauTailles = new int[contenuFichier.length()];
	for (int i = 0; i < contenuFichier.length(); i++) {
	    tableauTailles[i] = Character
		    .getNumericValue(contenuFichier.charAt(i));
	}

	return tableauTailles;
    }

    /**
     * Compte le nombre de lignes dans un fichier.
     *
     * @param cheminFichier Le chemin du fichier.
     * @return Le nombre de lignes dans le fichier.
     * @throws IOException Si une erreur d'entrée/sortie se produit lors
     *                     de la lecture du fichier.
     */
    private static int compterLignes(String cheminFichier) throws IOException {
	BufferedReader reader = null;
	int nombreLignes = 0;
	try {
	    reader = new BufferedReader(new FileReader(cheminFichier));
	    while (reader.readLine() != null) {
		nombreLignes++;
	    }
	} finally {
	    if (reader != null) {
		reader.close();
	    }
	}
	return nombreLignes;
    }

    public static void main(String[] args) {
	try {
	    Object[][] dictionnaire = reconstruireDictionnaire(
		    "dictionnaireHuffman.txt");
	    int[] tableauTailles = reconstruireTableauTailles(
		    "dictionnaireTaille.txt");

	    // Afficher le dictionnaire de Huffman reconstruit
	    System.out.println("Dictionnaire de Huffman : ");
	    for (Object[] entree : dictionnaire) {
		System.out.println(entree[0] + " : " + entree[1]);
	    }

	    // Afficher le tableau de tailles reconstruit
	    System.out.println("Tableau de tailles : ");
	    for (int taille : tableauTailles) {
		System.out.println(taille);
	    }
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
}
