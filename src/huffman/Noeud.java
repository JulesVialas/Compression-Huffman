/*
 * Noeud.java                                            02 mai 2024
 * IUT de Rodez, pas de copyright.
 */
package huffman;

/**
 * La classe Noeud représente un noeud dans un arbre de Huffman.
 * Chaque noeud contient un caractère, une fréquence, ainsi que des 
 * références vers les noeuds enfants gauche et droit.
 */
public class Noeud {
    private char caractere;
    private int frequence;
    private Noeud gauche;
    private Noeud droite;

    /**
     * Instancie un objet de type Noeud.
     *
     * @param caractere le caractère représenté par ce noeud
     * @param frequence la fréquence du caractère
     */
    public Noeud(char caractere, int frequence) {
	this.caractere = caractere;
	this.frequence = frequence;
    }

    /**
     * @return le caractère représenté par ce noeud
     */
    public char getCaractere() {
	return this.caractere;
    }

    /**
     * @return la fréquence du caractère représenté par ce noeud
     */
    public int getFrequence() {
	return this.frequence;
    }

    /**
     * @return le noeud enfant gauche
     */
    public Noeud getGauche() {
	return this.gauche;
    }

    /**
     * Définit le noeud enfant gauche.
     *
     * @param gauche	le noeud enfant gauche à définir
     */
    public void setGauche(Noeud gauche) {
	this.gauche = gauche;
    }

    /**
     * @return le noeud enfant droit
     */
    public Noeud getDroite() {
	return this.droite;
    }

    /**
     * Définit le noeud enfant droit.
     *
     * @param droite	le noeud enfant droit à définir
     */
    public void setDroite(Noeud droite) {
	this.droite = droite;
    }
}
