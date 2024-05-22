package gestionFichier;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GestionFichierTexte {

    public static String lireFichier(String cheminFichier) throws IOException {
	StringBuilder contenuBuilder = new StringBuilder();
	try (BufferedReader reader = new BufferedReader(
		new FileReader(cheminFichier))) {
	    String ligne;
	    while ((ligne = reader.readLine()) != null) {
		contenuBuilder.append(ligne).append('\n');
	    }
	}
	return contenuBuilder.toString();
    }

    public static void ecrireFichier(String contenu, String nomDeFichier) {
	BufferedWriter writer = null;
	try {
	    writer = new BufferedWriter(new FileWriter(nomDeFichier));
	    writer.write(contenu);
	} catch (IOException e) {
	    e.printStackTrace();
	} finally {
	    try {
		if (writer != null) {
		    writer.close();
		}
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}
    }
}
