/*
 * Compression.java                   08/05/2024
 * IUT de Rodez, pas de copyright
 */

package huffman;

import java.io.IOException;

import gestionFichier.GestionFichierBinaire;
import gestionFichier.GestionFichierTexte;

/**
 * La classe Compression fournit des méthodes pour compresser un texte
 * en utilisant l'algorithme de codage de Huffman.
 */
public class Compression {

    /**
     * Compresse le texte donné en utilisant le dictionnaire de codage de
     * Huffman fourni.
     *
     * @param texte        Le texte à compresser.
     * @param dictionnaire Le dictionnaire de codage de Huffman.
     * @return Le texte compressé en code binaire.
     * @throws IllegalArgumentException Si le texte est vide ou null.
     */
    public static String compresserTexte(String texte,
	    Object[][] dictionnaire) {
	String tableauHuffman = "";
	if (texte == null || texte.isEmpty()) {
	    throw new IllegalArgumentException("Le texte est vide ou null");
	}
	StringBuilder codeBinaire = new StringBuilder();
	for (int rang = 0; rang < texte.length(); rang++) {
	    char caractere = texte.charAt(rang);
	    String codeHuffman = trouverCodeHuffman(caractere, dictionnaire);
	    codeBinaire.append(codeHuffman);
	}
	for (Object[] element : dictionnaire) {
	    tableauHuffman += element[0] + " : " + element[1] + "\n";
	}
	GestionFichierTexte.ecrireFichier((tableauHuffman),
		"dictionnaireHuffman.txt");
	try {
	    GestionFichierBinaire.ecriture(codeBinaire.toString(),
		    "texteCompresse.bin");
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return codeBinaire.toString();
    }

    /**
     * Trouve le code de Huffman pour un caractère donné dans le
     * dictionnaire.
     *
     * @param caractere    Le caractère pour lequel trouver le code de
     *                     Huffman.
     * @param dictionnaire Le dictionnaire de codage de Huffman.
     * @return Le code de Huffman pour le caractère, ou une chaîne vide si
     *         le caractère n'est pas trouvé.
     */
    public static String trouverCodeHuffman(char caractere,
	    Object[][] dictionnaire) {
	for (Object[] ligne : dictionnaire) {
	    if (ligne[0] != null && ligne[0].equals(caractere)) {
		return (String) ligne[1];
	    }
	}
	return "";
    }
}