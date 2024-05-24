/*
 * ArbreHuffman.java                                            02 mai 2024
 * IUT de Rodez, pas de copyright.
 */
package huffman;

import java.util.ArrayList;
import java.util.List;

/**
 * ArbreHuffman contient des méthodes pour construire un 
 * arbre de Huffman à partir des occurrences des caractères d'un 
 * fichier.
 */
public class ArbreHuffman {

    /**
     * Construit un arbre de Huffman à partir d'un tableau
     * d'occurrences de caractères.
     *
     * @param occurrences un tableau d'objets où chaque élément est
     * 			  un tableau contenant un caractère et sa 
     * 			  fréquence d'occurrence
     * @return la racine de l'arbre de Huffman construit
     */
    public static Noeud constructionArbreHuffman(Object[][] occurrences) {
	List<Noeud> listeNoeuds = new ArrayList<>();
	for (Object[] occurrence : occurrences) {
	    char caractere = (char) occurrence[0];
	    int frequence = (int) occurrence[1];
	    listeNoeuds.add(new Noeud(caractere, frequence));
	}
	while (listeNoeuds.size() > 1) {
	    Noeud droite = listeNoeuds.remove(0);
	    Noeud gauche = listeNoeuds.remove(0);
	    Noeud parent = new Noeud('\0', gauche.getFrequence() 
		    				+ droite.getFrequence());
	    parent.setGauche(gauche);
	    parent.setDroite(droite);
	    int insertionIndex = 0;
	    while (insertionIndex < listeNoeuds.size()
		    && listeNoeuds.get(insertionIndex).getFrequence() 
		    				    < parent.getFrequence()) {
		insertionIndex++;
	    }
	    listeNoeuds.add(insertionIndex, parent);
	}
	return listeNoeuds.get(0);
    }
}
