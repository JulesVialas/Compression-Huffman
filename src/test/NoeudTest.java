/*
 * NoeudTest.java                                            02 mai 2024
 * IUT de Rodez, pas de copyright.
 */
package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import huffman.Noeud;
import huffman.Occurrence;

/**
 * La classe NoeudTest contient les tests unitaires pour la classe
 * Noeud. Elle vérifie la construction de l'arbre de Huffman à partir
 * des occurrences des caractères.
 *
 * @author Jules Vialas, Robin Montes, Aurélien Valat, Landry
 *         Loubière, Amjed Sehil
 */
class NoeudTest {

    /** Les occurrences de caractères à utiliser pour les tests. */
    private static final List<Occurrence> OCCURRENCES = Occurrence
            .compter("Bonjour a tous");
    private static final List<Occurrence> UNE_OCCURRENCE = Occurrence
            .compter("aaaaa");
    private static final List<Occurrence> OCCURRENCES_NON_REPETEES = Occurrence
            .compter("abcdefghij");
    private static final List<Occurrence> OCCURRENCES_EGALES = Occurrence
            .compter("aabbccddee");

    /**
     * Test unitaire pour la construction de l'arbre de Huffman avec des
     * occurrences répétées. Vérifie que l'arbre de Huffman construit est
     * valide.
     */
    @Test
    void testConstructionArbreHuffmanAvecOccurrencesRepetees() {
        Noeud root1 = Noeud.constructionArbreHuffman(OCCURRENCES);
        assertNotNull(root1);
        assertTrue(estArbreHuffmanValide(root1));
    }

    /**
     * Test unitaire pour la construction de l'arbre de Huffman avec une
     * seule occurrence. Vérifie que l'arbre de Huffman construit est
     * valide et qu'il contient uniquement un nœud avec la bonne
     * occurrence.
     */
    @Test
    void testConstructionArbreHuffmanAvecUneOccurrence() {
        Noeud root2 = Noeud.constructionArbreHuffman(UNE_OCCURRENCE);
        assertNotNull(root2);
        assertTrue(estArbreHuffmanValide(root2));
        assertEquals(root2.getCaractere(), 'a');
        assertEquals(root2.getFrequence(), 5);
        assertEquals(root2.getGauche(), null);
        assertEquals(root2.getDroite(), null);
    }

    /**
     * Test unitaire pour la construction de l'arbre de Huffman avec des
     * occurrences non répétées. Vérifie que l'arbre de Huffman construit
     * est valide.
     */
    @Test
    void testConstructionArbreHuffmanAvecOccurrencesNonRepetees() {
        Noeud root4 = Noeud.constructionArbreHuffman(OCCURRENCES_NON_REPETEES);
        assertNotNull(root4);
        assertTrue(estArbreHuffmanValide(root4));
    }

    /**
     * Test unitaire pour la construction de l'arbre de Huffman avec des
     * occurrences égales. Vérifie que l'arbre de Huffman construit est
     * valide.
     */
    @Test
    void testArbreHuffmanAvecOccurencesEgales() {
        Noeud root = Noeud.constructionArbreHuffman(OCCURRENCES_EGALES);
        assertNotNull(root);
        assertTrue(estArbreHuffmanValide(root));
    }

    /**
     * Vérifie si l'arbre de Huffman est valide.
     *
     * @param noeud le nœud racine de l'arbre à vérifier
     * @return true si l'arbre est valide, false sinon
     */
    private boolean estArbreHuffmanValide(Noeud noeud) {
        if ((noeud == null)
                || (noeud.getGauche() == null && noeud.getDroite() == null)) {
            return true;
        }
        if (noeud.getGauche() != null && noeud.getDroite() != null) {
            return noeud
                    .getFrequence() == (noeud.getGauche().getFrequence()
                            + noeud.getDroite().getFrequence())
                    && estArbreHuffmanValide(noeud.getGauche())
                    && estArbreHuffmanValide(noeud.getDroite());
        }
        return false;
    }
}
