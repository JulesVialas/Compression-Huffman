/*
 * CompterOccurrences.java                   30/04/2024
 * IUT de Rodez, pas de copyright
 */

package huffman;

/**
 * Cette classe fournit des méthodes pour compter les occurrences de
 * chaque caractère dans une chaîne de texte.
 */
public class CompterOccurrences {

    /**
     * Compte les occurrences de chaque caractère dans une chaîne de
     * texte.
     *
     * @param texte la chaîne de texte dans laquelle compter les
     *              occurrences
     * @return un tableau d'objets contenant les caractères uniques de la
     *         chaîne et leurs occurrences correspondantes
     * @throws IllegalArgumentException si le texte est null, vide ou
     *                                  contient des caractères non pris
     *                                  en charge
     */
    public static Object[][] compter(String texte) {
	testValide(texte);
	int[] tableauOccurrences = new int[127];
	Object[][] resultat = remplirTableau(texte, tableauOccurrences);
	return triInsertionCroissant(resultat);
    }

    /**
     * Vérifie si le texte est valide.
     *
     * @param texte le texte à vérifier
     * @throws IllegalArgumentException si le texte est null ou vide
     * @throws IllegalArgumentException si le texte contient des
     *                                  caractères non pris en charge
     *                                  (hors de la plage ASCII)
     */
    private static void testValide(String texte) {
	if (texte == null || texte.isEmpty()) {
	    throw new IllegalArgumentException("Le texte est vide ou null");
	}
	for (char caractere : texte.toCharArray()) {
	    if (caractere > '\u007F' || caractere < '\u0000') {
		throw new IllegalArgumentException(
			"Le caractère n'est pas pris en charge");
	    }
	}
    }

    /**
     * Remplit le tableau d'occurrences avec les caractères uniques du
     * texte.
     *
     * @param texte       le texte à analyser
     * @param Occurrences le tableau d'occurrences à remplir
     * @return le tableau d'occurrences rempli
     */
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

    /**
     * Compte le nombre de caractères qui sont présents dans la chaine de
     * caractères.
     *
     * @param texte la chaîne de texte dans laquelle compter les
     *              caractères uniques
     * @return le nombre de caractères uniques dans la chaîne de texte
     */
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

    /**
     * Implémente l'algorithme de tri par insertion pour trier le tableau
     * d'occurrences par ordre croissant d'occurrences.
     *
     * @param tableau le tableau d'occurrences à trier
     * @return le tableau d'occurrences trié par ordre croissant
     */
    private static Object[][] triInsertionCroissant(Object[][] tableau) {
	for (int rang = 1; rang < tableau.length; rang++) {
	    Object[] temp = tableau[rang];
	    int secondRang = rang - 1;
	    while (secondRang >= 0
		    && (int) tableau[secondRang][1] >= (int) temp[1]) {
		tableau[secondRang + 1] = tableau[secondRang];
		secondRang--;
	    }
	    tableau[secondRang + 1] = temp;
	}
	return tableau;
    }
}