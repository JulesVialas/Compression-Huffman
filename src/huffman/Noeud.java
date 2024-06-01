/*
 * Noeud.java                                            02 mai 2024
 * IUT de Rodez, pas de copyright.
 */
package huffman;

import java.util.ArrayList;
import java.util.List;

/**
 * Permet de créer un arbre de Huffman à partir des fréquences des
 *  caractères d'un fichier.
 * <p>
 * L'arbre de Huffman est un arbre binaire utilisé en compression de
 * données. Il associe les caractères les plus fréquents à des codes
 * binaires courts et les caractères moins fréquents à 
 * des codes binaires plus longs.
 * </p>
 * <p>
 * Pour construire l'arbre, cette classe prend en entrée une liste
 * des occurrences de chaque caractère dans le fichier, représentées
 * par des paires de caractères et de fréquences.
 * </p>
 * <p>
 * Les nœuds de l'arbre représentent soit des caractères, soit des
 * combinaisons de caractères, tandis que les feuilles de l'arbre
 * représentent les caractères eux-mêmes.
 * </p>
 * <p>
 * Les méthodes fournies permettent d'accéder aux caractères,
 * aux fréquences et aux nœuds enfants de chaque nœud de l'arbre.
 * </p>
 * @author LOUBIERE Landry, MONTES Robin, SEHIL Amjed, VALAT Aurélien
 * et VIALAS Jules 
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
     * @return le noeud enfant droit
     */
    public Noeud getDroite() {
	return this.droite;
    }

    /**
<<<<<<< HEAD
     * Définit le noeud enfant droit.
     *
     * @param droite le noeud enfant droit à définir
     */
    public void setDroite(Noeud droite) {
	this.droite = droite;
    }

    /**
     * Construit un arbre de Huffman à partir d'une liste 
     * d'occurrences de caractères.
=======
     * Construit un arbre de Huffman à partir d'une liste d'occurrences de
     * caractères.
>>>>>>> branch 'master' of https://github.com/JulesVialas/SAE-S2.02.git
     *
     * @param occurrences une liste d'objets où chaque élément est 
     *                    une occurrence représentée par un objet 
     *                    Occurrence
     * @return la racine de l'arbre de Huffman construit
     */
    public static Noeud constructionArbreHuffman(List<Occurrence> occurrences) {
	List<Noeud> listeNoeuds = new ArrayList<>();
	for (Occurrence occurrence : occurrences) {
	    listeNoeuds.add(new Noeud(occurrence.getCaractere(),
		    occurrence.getOccurrences()));
	}
	while (listeNoeuds.size() > 1) {
	    // Trier les noeuds par fréquence
	    listeNoeuds.sort((a, b) -> Integer.compare(a.getFrequence(), 
		    b.getFrequence()));

	    /* Extraire les deux noeuds avec les fréquences les plus
	       basses*/
	    Noeud gauche = listeNoeuds.remove(0);
	    Noeud droite = listeNoeuds.remove(0);

	    /* Créer un nouveau noeud parent avec ces deux noeuds 
	       comme enfants */
	    Noeud parent = new Noeud(gauche.getFrequence() 
		    + droite.getFrequence(), gauche, droite);

	    // Ajouter le nouveau noeud parent à la liste
	    listeNoeuds.add(parent);
	}
	return listeNoeuds.get(0);
    }
}
