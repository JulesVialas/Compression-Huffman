/*
 * CompterOccurrences.java                                        22 avril 2024
 * IUT de Rodez, pas de copyright.
 */

package huffman;

/**
 * CompterOccurrences contient des méthodes pour compter
 * les occurrences des caractères dans un texte et retourner un
 * tableau d'objets trié par fréquence d'occurrences.
 */	
public class CompterOccurrences {

    /**
     * Compte les occurrences de chaque caractère dans un texte 
     * donné.
     *
     * @param texte	le texte dont on veut compter les occurrences
     * 			des caractères
     * @return un tableau d'objets contenant les caractères et leurs 
     * 	       occurrences triées par ordre croissant des fréquences
     */
    public static Object[][] compter(String texte) {
	testValide(texte);
	int[] tableauOccurrences = new int[127];
	Object[][] resultat = remplirTableau(texte, tableauOccurrences);
	return triInsertionCroissant(resultat);
    }

    /**
     * Vérifie si le texte est valide.
     * Le texte est invalide s'il est null, vide ou s'il contient des 
     * caractères non pris en charge.
     *
     * @param texte	le texte à vérifier
     * @throws IllegalArgumentException si le texte est null, vide ou
     *	       contient des caractères non pris en charge
     */
    private static void testValide(String texte) {
	if (texte == null || texte.isEmpty()) {
	    throw new IllegalArgumentException("Le texte est vide ou null");
	}
	for (char caractere : texte.toCharArray()) {
	    if (caractere > '\u007F' || caractere < '\u0000') {
		throw new IllegalArgumentException("Le caractère n'est pas pris"
			+ " en charge");
	    }
	}
    }

    /**
     * Remplit un tableau avec les caractères et leurs occurrences.
     *
     * @param texte	le texte dont on veut compter les occurrences
     * @param occurrences   le tableau des occurrences des caractères
     * @return un tableau d'objets contenant les caractères et leurs
     * 	       occurrences
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
     * Recherche la taille nécessaire pour le tableau d'occurrences.
     *
     * @param texte 	le texte dont on veut compter les occurrences
     * @return la taille du tableau d'occurrences
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
     * Trie le tableau d'occurrences en ordre croissant de fréquence
     * des caractères.
     *
     * @param tableau 	le tableau d'objets à trier
     * @return le tableau trié d'objets
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