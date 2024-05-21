package huffman;

import java.io.IOException;

import gestionFichier.GestionDictionnaire;
import gestionFichier.GestionFichierBinaire;

/**
 * Cette classe fournit des méthodes pour décompresser un fichier
 * binaire encodé avec l'algorithme de compression de Huffman. Elle
 * utilise un dictionnaire et un tableau de tailles de caractères pour
 * reconstruire le texte original à partir du code binaire compressé.
 */
public class Decompression {

    /**
     * Décompresse un fichier binaire encodé avec l'algorithme de Huffman.
     *
     * @param nomFichier Le nom du fichier binaire à décompresser.
     * @return Le texte décompressé, ou {@code null} en cas d'erreur.
     */
    public static String decompresser(String nomFichier) {
	Object[][] dictionnaire = null;
	int[] tailleCaractere = null;
	try {
	    dictionnaire = GestionDictionnaire
		    .reconstruireDictionnaire("dictionnaireHuffman.txt");
	    tailleCaractere = GestionDictionnaire
		    .reconstruireTableauTailles("dictionnaireTaille.txt");
	} catch (IOException e) {
	    e.printStackTrace();
	}

	if (dictionnaire == null || tailleCaractere == null) {
	    System.err.println(
		    "Erreur : Impossible de reconstruire le dictionnaire ou le tableau des tailles.");
	    return null;
	}

	StringBuilder codeBinaire = new StringBuilder();
	try {
	    codeBinaire.append(GestionFichierBinaire.lecture(nomFichier));
	} catch (IOException e) {
	    e.printStackTrace();
	}

	StringBuilder texteDecomprime = new StringBuilder();
	int index = 0;
	while (index < codeBinaire.length()) {
	    boolean matchFound = false;
	    for (int taille : tailleCaractere) {
		if (index + taille <= codeBinaire.length()) {
		    String sousChaine = codeBinaire.substring(index,
			    index + taille);
		    for (Object[] ligne : dictionnaire) {
			if (ligne != null && ligne.length >= 2
				&& ligne[1] != null
				&& ligne[1].equals(sousChaine)) {
			    texteDecomprime.append(ligne[0]);
			    index += taille;
			    matchFound = true;
			    break;
			}
		    }
		    if (matchFound) {
			break;
		    }
		}
	    }
	    if (!matchFound) {
		System.err.println(
			"Erreur : Aucun match trouvé pour la séquence binaire à l'index "
				+ index);
		break;
	    }
	}

	return texteDecomprime.toString();
    }

    /**
     * Méthode principale pour tester la décompression d'un fichier.
     *
     * @param args Les arguments de la ligne de commande (non utilisés
     *             ici).
     */
    public static void main(String[] args) {
	String texteDecomprime = decompresser("coucou.bin");
	if (texteDecomprime != null) {
	    System.out.println("Texte décompressé : " + texteDecomprime);
	} else {
	    System.err.println("Erreur lors de la décompression du fichier.");
	}
    }
}
