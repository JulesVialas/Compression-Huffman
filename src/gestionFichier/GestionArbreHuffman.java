/*
 * GestionArbreHuffman.java                   01/05/2024
 * IUT de Rodez, pas de copyright
 */

package gestionFichier;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import huffman.ArbreHuffman.Noeud;

/**
 * Cette classe gère la création et la sauvegarde de l'arbre de
 * Huffman ainsi que la génération du dictionnaire Huffman. Elle
 * fournit également une méthode pour sauvegarder l'arbre de Huffman
 * dans un fichier texte.
 */
public class GestionArbreHuffman {

    private static int rang = 0;

    /**
     * Crée un dictionnaire associant chaque caractère de l'arbre de
     * Huffman à son code Huffman correspondant.
     *
     * @param racine Le nœud racine de l'arbre de Huffman.
     * @return Un tableau contenant le dictionnaire avec les caractères et
     *         leurs codes Huffman.
     */
    public static Object[][] creerDictionnaire(Noeud racine) {
	int nombreDeCaracteres = compterCaracteres(racine);
	Object[][] dictionnaire = new Object[nombreDeCaracteres][2];
	rang = 0;
	remplirDictionnaire(racine, dictionnaire, "");
	return dictionnaire;
    }

    private static void remplirDictionnaire(Noeud racine,
	    Object[][] dictionnaire, String codeHuffman) {
	if (racine == null) {
	    return;
	}
	if (racine.gauche == null && racine.droite == null) {
	    dictionnaire[rang][0] = (char) racine.caractere;
	    dictionnaire[rang][1] = codeHuffman;
	    rang++;
	}
	remplirDictionnaire(racine.gauche, dictionnaire, codeHuffman + "0");
	remplirDictionnaire(racine.droite, dictionnaire, codeHuffman + "1");
    }

    private static int compterCaracteres(Noeud racine) {
	if (racine == null) {
	    return 0;
	}
	if (racine.gauche == null && racine.droite == null) {
	    return 1;
	}
	return compterCaracteres(racine.gauche)
		+ compterCaracteres(racine.droite);
    }

    /**
     * Sauvegarde l'arbre de Huffman dans un fichier texte.
     *
     * @param racine     Le nœud racine de l'arbre de Huffman.
     * @param nomFichier Le nom du fichier dans lequel sauvegarder
     *                   l'arbre.
     */
    public static void sauvegardeArbreHuffman(Noeud racine, String nomFichier) {
	try (BufferedWriter writer = new BufferedWriter(
		new FileWriter(nomFichier))) {
	    parcourirArbre(racine, writer, "");
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    private static void parcourirArbre(Noeud noeud, BufferedWriter writer,
	    String codeHuffman) throws IOException {
	if (noeud == null) {
	    return;
	}

	if (noeud.gauche == null && noeud.droite == null) {
	    writer.write("codeHuffman = " + codeHuffman + " ; encode = "
		    + Integer.toBinaryString(noeud.caractere) + " ; symbole = "
		    + noeud.caractere + "\n");
	}

	parcourirArbre(noeud.gauche, writer, codeHuffman + "0");
	parcourirArbre(noeud.droite, writer, codeHuffman + "1");
    }

    /**
     *
     * @param nomFichier
     */

    public Noeud restaurerArbreHuffman(String nomFichier) {
	Noeud racine = null;

	return racine;

    }
}
