package huffman;

public class Compression {

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

    public static String trouverCodeHuffman(char caractere, Object[][] dictionnaire) {
	for (Object[] element : dictionnaire) {
	    if (element[0] != null && caractere == (char) element[0]) {
		return element[1].toString();
	    }
	}
	return "";
    }

}