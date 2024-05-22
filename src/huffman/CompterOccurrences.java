package huffman;

public class CompterOccurrences {

    public static Object[][] compter(String texte) {
	testValide(texte);
	int[] tableauOccurrences = new int[127];
	Object[][] resultat = remplirTableau(texte, tableauOccurrences);
	return triInsertionCroissant(resultat);
    }

    private static void testValide(String texte) {
	if (texte == null || texte.isEmpty()) {
	    throw new IllegalArgumentException("Le texte est vide ou null");
	}
	for (char caractere : texte.toCharArray()) {
	    if (caractere > '\u007F' || caractere < '\u0000') {
		throw new IllegalArgumentException("Le caractère n'est pas pris en charge");
	    }
	}
    }

    private static Object[][] remplirTableau(String texte, int[] Occurrences) {
	int index = 0;
	int tailleTableau = rechercheTailleTableau(texte);
	Object[][] tableau = new Object[tailleTableau][2];
	for (int rang = 0; rang < texte.length(); rang++) {
	    char caractere = texte.charAt(rang);
	    Occurrences[caractere]++;
	}

	for (int rang = 0; rang < Occurrences.length; rang++) {
	    if (Occurrences[rang] > 0) {
		tableau[index][0] = (char) rang;
		tableau[index][1] = Occurrences[rang];
		index++;
	    }
	}
	return tableau;
    }

    private static int rechercheTailleTableau(String texte) {
	int[] OccurrencesTemporaires = new int[127];
	int compteurCaracteresUniques = 0;
	for (int rang = 0; rang < texte.length(); rang++) {
	    char caractere = texte.charAt(rang);
	    OccurrencesTemporaires[caractere]++;
	}
	for (int rang = 0; rang < 127; rang++) {
	    if (OccurrencesTemporaires[rang] > 0) {
		compteurCaracteresUniques++;
	    }
	}
	return compteurCaracteresUniques;
    }

    private static Object[][] triInsertionCroissant(Object[][] tableau) {
	for (int rang = 1; rang < tableau.length; rang++) {
	    Object[] temp = tableau[rang];
	    int secondRang = rang - 1;
	    while (secondRang >= 0 && (int) tableau[secondRang][1] >= (int) temp[1]) {
		tableau[secondRang + 1] = tableau[secondRang];
		secondRang--;
	    }
	    tableau[secondRang + 1] = temp;
	}
	return tableau;
    }
}