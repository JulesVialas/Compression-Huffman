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

    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder();
	toStringHelper(this, sb, 0);
	return sb.toString();
    }

    private void toStringHelper(Noeud noeud, StringBuilder sb, int level) {
	if (noeud == null) {
	    return;
	}
	for (int i = 0; i < level; i++) {
	    sb.append("  ");
	}
	sb.append(noeud.getCaractere() == '\n' ? "↲" : (noeud.getCaractere() == ' ' ? "␣" : noeud.getCaractere()));
	sb.append(" : ").append(noeud.getFrequence()).append("\n");
	toStringHelper(noeud.getGauche(), sb, level + 1);
	toStringHelper(noeud.getDroite(), sb, level + 1);
    }
}
