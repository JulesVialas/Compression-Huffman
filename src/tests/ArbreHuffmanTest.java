/*
 * ArbreHuffmanTest.java                                            02 mai 2024
 * IUT de Rodez, pas de copyright.
 */
package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import huffman.ArbreHuffman;
import huffman.CompterOccurrences;
import huffman.Noeud;

class ArbreHuffmanTest {

    private static final Object[][] OCCURRENCES = CompterOccurrences.compter("Bonjour a tous");

    private static final Object[][] UNE_OCCURRENCE = CompterOccurrences.compter("aaaaa");

    private static final Object[][] OCCURRENCES_NON_REPETEES = CompterOccurrences.compter("abcdefghij");

    private static final Object[][] OCCURRENCES_EGALES = CompterOccurrences.compter("aabbccddee");

    @Test
    public void testConstructionArbreHuffmanAvecOccurrencesRepetees() {
	Noeud root1 = ArbreHuffman.constructionArbreHuffman(OCCURRENCES);
	assertNotNull(root1);
	assertTrue(estArbreHuffmanValide(root1));
    }

    @Test
    public void testConstructionArbreHuffmanAvecUneOccurrence() {
	Noeud root2 = ArbreHuffman.constructionArbreHuffman(UNE_OCCURRENCE);
	assertNotNull(root2);
	assertTrue(estArbreHuffmanValide(root2));
	assertEquals(root2.getCaractere(), 'a');
	assertEquals(root2.getFrequence(), 5);
	assertEquals(root2.getGauche(), null);
	assertEquals(root2.getDroite(), null);
    }

    @Test
    public void testConstructionArbreHuffmanAvecOccurrencesNonRepetees() {
	Noeud root4 = ArbreHuffman.constructionArbreHuffman(OCCURRENCES_NON_REPETEES);
	assertNotNull(root4);
	assertTrue(estArbreHuffmanValide(root4));
    }

    @Test
    public void testArbreHuffmanAvecOccurencesEgales() {
	Noeud root = ArbreHuffman.constructionArbreHuffman(OCCURRENCES_EGALES);
	assertNotNull(root);
	assertTrue(estArbreHuffmanValide(root));
    }

    private boolean estArbreHuffmanValide(Noeud noeud) {
	if ((noeud == null) || (noeud.getGauche() == null && noeud.getDroite() == null)) {
	    return true;
	}
	if (noeud.getGauche() != null && noeud.getDroite() != null) {
	    return noeud.getFrequence() == (noeud.getGauche().getFrequence() + noeud.getDroite().getFrequence())
		    && estArbreHuffmanValide(noeud.getGauche()) && estArbreHuffmanValide(noeud.getDroite());
	}
	return false;
    }
}
