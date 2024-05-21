/*
 * Compression.java                   08/05/2024
 * IUT de Rodez, pas de copyright
 */

package huffman;

import java.io.IOException;

import gestionFichier.GestionArbreHuffman;
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
	String tableauTailles = "";
	String tableauHuffman = "";
	if (texte == null || texte.isEmpty()) {
	    throw new IllegalArgumentException("Le texte est vide ou null");
	}
	StringBuilder codeBinaire = new StringBuilder();
	for (int rang = 0; rang < texte.length(); rang++) {
	    char caractere = texte.charAt(rang);
	    String codeHuffman = trouverCodeHuffman(caractere, dictionnaire);
	    tableauTailles += codeHuffman.length();
	    codeBinaire.append(codeHuffman);
	}
	for (Object[] element : dictionnaire) {
	    tableauHuffman += element[0] + " : " + element[1] + "\n";
	}
	GestionFichierTexte.ecrireFichier((tableauHuffman),
		"dictionnaireHuffman.txt");
	GestionFichierTexte.ecrireFichier((tableauTailles),
		"dictionnaireTaille.txt");
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

    public static void main(String[] args) {
	String texte = "coucou";
	try {
	    // Compter les occurrences des caractères dans le texte
	    Object[][] occurrences = CompterOccurrences.compter(texte);

	    // Construire l'arbre de Huffman à partir des occurrences
	    ArbreHuffman.Noeud racine = ArbreHuffman
		    .constructionArbreHuffman(occurrences);

	    // Créer un dictionnaire de codage de Huffman à partir de l'arbre
	    Object[][] dictionnaire = GestionArbreHuffman
		    .creerDictionnaire(racine);

	    // Compresser le texte en utilisant le dictionnaire
	    String texteComprime = Compression.compresserTexte(texte,
		    dictionnaire);

	    // Afficher le texte compressé
	    System.out.println("Texte compressé : " + texteComprime);
	} catch (IllegalArgumentException e) {
	    System.err.println("Erreur: " + e.getMessage());
	}
    }
}