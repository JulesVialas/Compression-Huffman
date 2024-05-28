/*
 * Occurrence.java                                            TODO DATE
 * IUT de Rodez, pas de copyright.
 */
package huffman;

/**
 * Cette classe représente une occurrence d'un caractère dans un contexte donné.
 * Elle encapsule les informations relatives à ce caractère, à savoir le
 * caractère lui-même, le nombre d'occurrences de ce caractère et sa fréquence
 * dans le contexte.
 */
public class Occurrence {
    private final char caractere;
    private final int occurrences;
    private final double frequence;

    /**
     * Initialise une occurrence avec les paramètres spécifiés.
     *
     * @param caractere   Le caractère concerné par cette occurrence.
     * @param occurrences Le nombre d'occurrences du caractère dans le contexte.
     * @param frequence   La fréquence du caractère dans le contexte.
     */
    public Occurrence(char caractere, int occurrences, double frequence) {
	this.caractere = caractere;
	this.occurrences = occurrences;
	this.frequence = frequence;
    }

    /**
     * Récupère le caractère associé à cette occurrence.
     *
     * @return Le caractère associé à cette occurrence.
     */
    public char getCaractere() {
	return caractere;
    }

    /**
     * Récupère le nombre d'occurrences du caractère dans le contexte.
     *
     * @return Le nombre d'occurrences du caractère dans le contexte.
     */
    public int getOccurrences() {
	return occurrences;
    }

    /**
     * Récupère la fréquence du caractère dans le contexte.
     *
     * @return La fréquence du caractère dans le contexte.
     */
    public double getFrequence() {
	return frequence;
    }
}
