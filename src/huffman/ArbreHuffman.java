/*
 * ArbreHuffman.java                   14/05/2024
 * IUT de Rodez, pas de copyright
 */

package huffman;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe ArbreHuffman permettant de construire et d'afficher un arbre
 * de Huffman à partir des occurrences des caractères.
 */
public class ArbreHuffman {

    /**
     * Classe interne représentant un nœud de l'arbre de Huffman.
     */
    public static class Noeud {
	public char caractere;
	public int frequence;
	public Noeud gauche;
	public Noeud droite;

	/**
	 * Constructeur de la classe Noeud.
	 *
	 * @param caractere Le caractère associé au nœud.
	 * @param frequence La fréquence d'apparition du caractère.
	 */
	Noeud(char caractere, int frequence) {
	    this.caractere = caractere;
	    this.frequence = frequence;
	}
    }

    /**
     * Méthode pour construire l'arbre de Huffman à partir des
     * occurrences.
     *
     * @param occurrences Les occurrences des caractères sous forme de
     *                    tableau d'objets.
     * @return Le nœud racine de l'arbre de Huffman.
     */
    public static Noeud constructionArbreHuffman(Object[][] occurrences) {
	List<Noeud> listeNoeuds = new ArrayList<>();

	for (Object[] occurrence : occurrences) {
	    char caractere = (char) occurrence[0];
	    int frequence = (int) occurrence[1];
	    listeNoeuds.add(new Noeud(caractere, frequence));
	}

	while (listeNoeuds.size() > 1) {
	    Noeud droite = listeNoeuds.remove(0); // élément le moins fréquent
	    Noeud gauche = listeNoeuds.remove(0);

	    Noeud parent = new Noeud('\0', gauche.frequence + droite.frequence); // somme
	    // des
	    // fréquences
	    // pour
	    // le
	    // noeud
	    // parent
	    parent.gauche = gauche;
	    parent.droite = droite;

	    int insertionIndex = 0;
	    while (insertionIndex < listeNoeuds.size() && listeNoeuds
		    .get(insertionIndex).frequence < parent.frequence) {
		insertionIndex++;
	    }
	    listeNoeuds.add(insertionIndex, parent);
	}

	return listeNoeuds.get(0);
    }
}
