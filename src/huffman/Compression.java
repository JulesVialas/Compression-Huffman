/*
 * Compression.java                                            02 mai 2024
 * IUT de Rodez, pas de copyright.
 */
package huffman;

/**
 * Compression contient des méthodes pour compresser des
 * chaines de caractère via le code Huffman obtenu lors de la 
 * construction de L'arbre Huffman
 */
public class Compression {

    /**
     * Compresse un fichier grâce à son code Huffman
     *
     * @param text chaine de caractère à compresser
     * @param dictionnaire arbre de Huffman du fichier donnée
     * 
     * @return texte compressé
     */
    public static String compresserTexte(String texte, Object[][] dictionnaire) {
	if (texte == null || texte.isEmpty()) {
	    throw new IllegalArgumentException("Le texte est vide ou null");
	}
	StringBuilder codeBinaire = new StringBuilder();
	for (int rang = 0; rang < texte.length(); rang++) {
	    char caractere = texte.charAt(rang);
	    String codeHuffman = trouverCodeHuffman(caractere, dictionnaire);
	    codeBinaire.append(codeHuffman);
	}
	return codeBinaire.toString();
    }
    /**
     * Refonte de l'arbre Huffman pour effectuer la compression
     *
     * @param caractere caractere dont on veut le code huffman
     * @param dictionnaire 
     * 
     * @return Si le caractère est trouvé dans le dictionnaire le code huffman
     *         Sinon rien n'est retourné
     */
    public static String trouverCodeHuffman(char caractere, Object[][] dictionnaire) {
	for (Object[] element : dictionnaire) {
	    if (element[0] != null && caractere == (char) element[0]) {
		return element[1].toString();
	    }
	}
	return "";
    }

}