/*
 * Noeud.java                                            02 mai 2024
 * IUT de Rodez, pas de copyright.
 */
package huffman;

import java.util.ArrayList;
import java.util.List;

/**
 * Noeud contient des méthodes pour construire un arbre de Huffman à partir des
 * occurrences des caractères d'un fichier.
 */
public class Noeud {
    private char caractere;
    private int frequence;
    private Noeud gauche;
    private Noeud droite;

    /**
     * Instancie un objet en tant que feuille.
     *
     * @param caractere le caractère représenté par ce noeud
     * @param frequence la fréquence du caractère
     */
    public Noeud(char caractere, int frequence) {
	this.caractere = caractere;
	this.frequence = frequence;
    }

    /**
     * Instancie un objet en tant que noeud interne.
     *
     * @param frequence la fréquence cumulative des noeuds enfants
     * @param gauche    le noeud enfant gauche
     * @param droite    le noeud enfant droit
     */
    public Noeud(int frequence, Noeud gauche, Noeud droite) {
	this.caractere = '\0';
	this.frequence = frequence;
	this.gauche = gauche;
	this.droite = droite;
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
     * @param gauche le noeud enfant gauche à définir
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
     * @param droite le noeud enfant droit à définir
     */
    public void setDroite(Noeud droite) {
	this.droite = droite;
    }

    /**
     * Construit un arbre de Huffman à partir d'une liste d'occurrences de
     * caractères.
     *
     * @param occurrences une liste d'objets où chaque élément est une occurrence
     *                    représentée par un objet Occurrence
     * @return la racine de l'arbre de Huffman construit
     */
    public static Noeud constructionArbreHuffman(List<Occurrence> occurrences) {
	List<Noeud> listeNoeuds = new ArrayList<>();
	for (Occurrence occurrence : occurrences) {
	    listeNoeuds.add(new Noeud(occurrence.getCaractere(), occurrence.getOccurrences()));
	}
	while (listeNoeuds.size() > 1) {
	    // Trier les noeuds par fréquence
	    listeNoeuds.sort((a, b) -> Integer.compare(a.getFrequence(), b.getFrequence()));

	    // Extraire les deux noeuds avec les fréquences les plus basses
	    Noeud gauche = listeNoeuds.remove(0);
	    Noeud droite = listeNoeuds.remove(0);

	    // Créer un nouveau noeud parent avec ces deux noeuds comme enfants
	    Noeud parent = new Noeud(gauche.getFrequence() + droite.getFrequence(), gauche, droite);

	    // Ajouter le nouveau noeud parent à la liste
	    listeNoeuds.add(parent);
	}
	return listeNoeuds.get(0);
    }
}
