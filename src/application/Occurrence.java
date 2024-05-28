/*
 * Occurence.java                                            TODO DATE 
 * IUT de Rodez, pas de copyright.
 */
package application;

/**
 * TODO commenter la responsabilité de cette classe (SRP)
 */
public class Occurrence {
    private final String caractere;
    private final int occurrences;
    private final double frequence;

    /**
     * TODO commenter l'état initial obtenu
     * @param caractere
     * @param occurrences
     * @param frequence
     */
    public Occurrence(String caractere, int occurrences, double frequence) {
	this.caractere = caractere;
	this.occurrences = occurrences;
	this.frequence = frequence;
    }

    /**
     * TODO commenter le rôle de cette méthode
     * @return caractere
     */
    public String getCaractere() {
	return caractere;
    }

    /**
     * TODO commenter le rôle de cette méthode
     * @return occurrences
     */
    public int getOccurrences() {
	return occurrences;
    }

    /**
     * TODO commenter le rôle de cette méthode
     * @return frequence
     */
    public double getFrequence() {
	return frequence;
    }
}