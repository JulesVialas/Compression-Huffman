package huffman;

public class Noeud {
    private char caractere;
    private int frequence;
    private Noeud gauche;
    private Noeud droite;

    public Noeud(char caractere, int frequence) {
	this.caractere = caractere;
	this.frequence = frequence;
    }

    public char getCaractere() {
	return caractere;
    }

    public void setCaractere(char caractere) {
	this.caractere = caractere;
    }

    public int getFrequence() {
	return frequence;
    }

    public void setFrequence(int frequence) {
	this.frequence = frequence;
    }

    public Noeud getGauche() {
	return gauche;
    }

    public void setGauche(Noeud gauche) {
	this.gauche = gauche;
    }

    public Noeud getDroite() {
	return droite;
    }

    public void setDroite(Noeud droite) {
	this.droite = droite;
    }
}
