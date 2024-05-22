package huffman;

import java.io.IOException;

import gestionFichier.GestionFichierBinaire;
import gestionFichier.GestionFichierTexte;

public class Compression {

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