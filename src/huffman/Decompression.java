package huffman;

import java.io.IOException;

import gestionFichier.GestionDictionnaire;
import gestionFichier.GestionFichierBinaire;

public class Decompression {

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
