package gestionFichier;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GestionDictionnaire {

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
}