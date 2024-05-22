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
	return toString(this, 0);
    }

    private String toString(Noeud noeud, int niveau) {
	if (noeud == null) {
	    return "";
	}
	StringBuilder sb = new StringBuilder();
	for (int i = 0; i < niveau; i++) {
	    sb.append("   ");
	}
	if (noeud.getCaractere() == '\n') {
	    sb.append("↲ : ").append(noeud.getFrequence()).append("\n");
	} else if (noeud.getCaractere() == ' ') {
	    sb.append("␣ : ").append(noeud.getFrequence()).append("\n");
	} else {
	    sb.append(noeud.getCaractere()).append(" : ")
		    .append(noeud.getFrequence()).append("\n");
	}
	sb.append(toString(noeud.getGauche(), niveau + 1));
	sb.append(toString(noeud.getDroite(), niveau + 1));
	return sb.toString();
    }
}
