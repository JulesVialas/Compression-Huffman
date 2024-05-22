/*
 * GestionFichierTexte.java                   23/04/2024
 * IUT de Rodez, pas de copyrights
 */

package gestionFichier;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Cette classe gère la lecture et l'écriture de fichiers texte.
 */
public class GestionFichierTexte {

    /**
     * Méthode pour lire le contenu d'un fichier texte.
     *
     * @param cheminFichier Le chemin du fichier à lire.
     * @return Le contenu du fichier sous forme d'une chaîne de
     *         caractères.
     * @throws IOException Si une erreur d'entrée/sortie se produit lors
     *                     de la lecture du fichier.
     */
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

    /**
     * Méthode pour écrire du contenu dans un fichier texte.
     *
     * @param contenu      Le contenu à écrire dans le fichier.
     * @param nomDeFichier Le nom du fichier dans lequel écrire le
     *                     contenu.
     */
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
