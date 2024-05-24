/*
 * Decompression.java                                            02 mai 2024
 * IUT de Rodez, pas de copyright.
 */
package huffman;

import java.io.IOException;

import gestion.GestionFichierBinaire;
/**
 * Decompression decompresse des fichiers de type text (.txt)
 */
public class Decompression {
    /**
     * Décompresse des fichier à partir de l'encodage Huffman
     *
     * @param FichierCompresse fichier à décompresser
     * @param dictionnaire les caractres avec leurs encodage Huffman
     * 
     * @return le texte décompressé
     */
    public static String decompresser(String FichierCompresse, Object[][] dictionnaire) {
	String texteDecompresse = "";
	String texteBinaire = null;
	String temp = "";
	try {
	    texteBinaire = GestionFichierBinaire.lecture(FichierCompresse);
	} catch (IOException e) {
	    e.printStackTrace();
	}
	for (int rang = 0; rang < texteBinaire.length(); rang++) {
	    temp += texteBinaire.charAt(rang);
	    for (Object[] element : dictionnaire) {
		if (temp.equals(element[1])) {
		    texteDecompresse += element[0];
		    temp = "";
		}
	    }
	}
	return texteDecompresse;
    }
}