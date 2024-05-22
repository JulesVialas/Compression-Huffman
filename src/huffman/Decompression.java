/*
 * Decompression.java				22/05/2024
 * IUT de Rodez, pas de copyright
 */

package huffman;

import java.io.IOException;

import gestionFichier.GestionDictionnaire;
import gestionFichier.GestionFichierBinaire;

/**
 * La décompression permet de proposer à l'utilisateur de pouvoir
 * décompresser un fichier compressé
 * 
 * @author Jules Vialas
 */

public class Decompression {

    /**
     * Le fichier compressé est lu et analysé pour reconstruire les
     * différents caractères à partir d'un dictionnaire contenant dans une
     * colonne les caractères et dans l'autre le code huffman qui leur est
     * propre
     * 
     * @param nomFichierCompresse
     * @param nomFichierDictionnaire
     * @return une String contenant le contenu du fichier décompressé .
     */
    public static String decompresser(String nomFichierCompresse,
	    String nomFichierDictionnaire) {
	String texteDecompresse = "";
	String texteBinaire = null;
	String temp = "";
	try {
	    texteBinaire = GestionFichierBinaire.lecture(nomFichierCompresse);
	} catch (IOException e) {
	    e.printStackTrace();
	}
	Object[][] dictionnaire = null;
	try {
	    dictionnaire = GestionDictionnaire
		    .reconstruireDictionnaire(nomFichierDictionnaire);
	} catch (IOException e) {
	    e.printStackTrace();
	}
	for (int rang = 0; rang < texteBinaire.length(); rang++) {
	    temp += texteBinaire.charAt(rang);
	    for (int rang2 = 0; rang2 < dictionnaire.length; rang2++) {
		if (temp.equals(dictionnaire[rang2][1])) {
		    texteDecompresse += dictionnaire[rang2][0];
		    temp = "";
		}
	    }
	}
	return texteDecompresse;
    }
}
