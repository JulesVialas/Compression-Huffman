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
	return this.caractere;
    }

    public int getFrequence() {
	return this.frequence;
    }

    public Noeud getGauche() {
	return this.gauche;
    }

    public void setGauche(Noeud gauche) {
	this.gauche = gauche;
    }

    public Noeud getDroite() {
	return this.droite;
    }

    public void setDroite(Noeud droite) {
	this.droite = droite;
    }
}
