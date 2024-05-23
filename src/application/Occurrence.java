package application;

public class Occurrence {
    private final String caractere;
    private final int occurrences;
    private final double frequence;

    public Occurrence(String caractere, int occurrences, double frequence) {
	this.caractere = caractere;
	this.occurrences = occurrences;
	this.frequence = frequence;
    }

    public String getCaractere() {
	return caractere;
    }

    public int getOccurrences() {
	return occurrences;
    }

    public double getFrequence() {
	return frequence;
    }
}