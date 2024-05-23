package huffman;

import java.io.IOException;

import gestion.GestionFichierBinaire;

public class Decompression {

    public static String decompresser(String nomFichierCompresse, Object[][] dictionnaire) {
	String texteDecompresse = "";
	String texteBinaire = null;
	String temp = "";
	try {
	    texteBinaire = GestionFichierBinaire.lecture(nomFichierCompresse);
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