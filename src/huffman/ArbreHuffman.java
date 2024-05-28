/*
 * ArbreHuffman.java                                            02 mai 2024
 * IUT de Rodez, pas de copyright.
 */
package huffman;

import java.util.ArrayList;
import java.util.List;

/**
 * ArbreHuffman contient des méthodes pour construire un arbre de Huffman à
 * partir des occurrences des caractères d'un fichier.
 */
public class ArbreHuffman {

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
	    Noeud droite = listeNoeuds.remove(0);
	    Noeud gauche = listeNoeuds.remove(0);
	    Noeud parent = new Noeud('\0', gauche.getFrequence() + droite.getFrequence());
	    parent.setGauche(gauche);
	    parent.setDroite(droite);
	    int insertionIndex = 0;
	    while (insertionIndex < listeNoeuds.size()
		    && listeNoeuds.get(insertionIndex).getFrequence() < parent.getFrequence()) {
		insertionIndex++;
	    }
	    listeNoeuds.add(insertionIndex, parent);
	}
	return listeNoeuds.get(0);
    }
}
