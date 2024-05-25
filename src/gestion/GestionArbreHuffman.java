/*
 * GestonArbreHuffman.java                                            [METTRE DATE COHERENTE]
 * IUT de Rodez, pas de copyright.
 */
package gestion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import huffman.Noeud;

/**
 * GestionArbreHuffman contient des méthodes pour sauvegarder et restaurer
 * des arbres de Huffman à partir de fichiers.
 */
public class GestionArbreHuffman {

    /**
     * Sauvegarde l'arbre Huffman dans un fichier.
     *
     * @param racine     La racine de l'arbre de Huffman.
     * @param nomFichier Le nom du fichier où sauvegarder l'arbre.
     */
    public static void sauvegardeArbreHuffman(Noeud racine, String nomFichier) {
	try (BufferedWriter contenuFichier = new BufferedWriter(new FileWriter(nomFichier))) {
	    parcourirArbreHuffman(racine, contenuFichier, "");
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    /**
     * Parcourt l'arbre de Huffman et écrit chaque noeud dans le fichier.
     *
     * @param noeud Le noeud actuel de l'arbre.
     * @param contenuFichier L'écrivain pour le fichier de sortie.
     * @param codeHuffman Le code Huffman pour le noeud actuel.
     * @throws IOException Si une erreur d'entrée ou de sortie survient.
     */
    private static void parcourirArbreHuffman(Noeud noeud, BufferedWriter contenuFichier, String codeHuffman)
	    throws IOException {
	if (noeud == null) {
	    return;
	}
	if (noeud.getGauche() == null && noeud.getDroite() == null) {
	    if (noeud.getCaractere() == '\n') {
		contenuFichier.write(
			"codeHuffman = " + codeHuffman + " ; encode = " + Integer.toBinaryString('\n') + " ; symbole = ↲\n");
	    } else if (noeud.getCaractere() == ' ') {
		contenuFichier.write(
			"codeHuffman = " + codeHuffman + " ; encode = " + Integer.toBinaryString(' ') + " ; symbole = ␣\n");
	    } else {
		contenuFichier.write("codeHuffman = " + codeHuffman + " ; encode = " + Integer.toBinaryString(noeud.getCaractere())
			+ " ; symbole = " + noeud.getCaractere() + "\n");
	    }
	}
	parcourirArbreHuffman(noeud.getGauche(), contenuFichier, codeHuffman + "0");
	parcourirArbreHuffman(noeud.getDroite(), contenuFichier, codeHuffman + "1");

    }
    /**
     * Restaure l'arbre de Huffman à partir d'un fichier.
     *
     * @param nomFichier Le nom du fichier contenant l'arbre de Huffman.
     * @return Un tableau de dictionnaire Huffman.
     * @throws IOException Si une erreur d'entrée ou de sortie survient.
     */
    public static Object[][] restaurerArbreHuffman(String nomFichier) throws IOException {
	List<Object[]> dictionnaire = new ArrayList<>();

	try (BufferedReader lecteur = new BufferedReader(new FileReader(nomFichier))) {
	    String ligne;
	    while ((ligne = lecteur.readLine()) != null) {

		String[] parties = ligne.split(";");

		String[] partieCodeHuffman = parties[0].split("=");
		String[] partieCaractere = parties[2].split("=");

		String codeHuffman = partieCodeHuffman[1].trim();
		String caractere = partieCaractere[1].trim();
		char symbole = decoderSymbole(caractere);

		dictionnaire.add(new Object[] { symbole, codeHuffman });
	    }
	}

	return dictionnaire.toArray(new Object[0][0]);
    }
    /**
     * Décode le symbole en fonction du caractère représenté dans le fichier.
     *
     * @param caractere Le caractère sous forme de chaîne.
     * @return Le caractère décodé.
     */
    private static char decoderSymbole(String caractere) {
	switch (caractere) {
	case "↲":
	    return '\n';
	case "␣":
	    return ' ';
	default:
	    return caractere.length() == 1 ? caractere.charAt(0) : '\0';
	}
    }
}