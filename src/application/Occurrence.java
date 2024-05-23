package application;

public class Occurrence {
    private final String caractere;
    private final int frequence;
    private final double pourcentage;

    public Occurrence(char caractere, int frequence, double pourcentage) {
        this.caractere = caractere == '\n' ? "↲" : (caractere == ' ' ? "␣" : Character.toString(caractere));
        this.frequence = frequence;
        this.pourcentage = pourcentage;
    }

    public String getCaractere() {
        return caractere;
    }

    public int getFrequence() {
        return frequence;
    }

    public double getPourcentage() {
        return pourcentage;
    }
}
