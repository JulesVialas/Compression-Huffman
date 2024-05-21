/*
 * ArbreHuffmanTest.java                   14/05/2024
 * IUT de Rodez, pas de copyright
 */

package tests;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import huffman.ArbreHuffman;
import huffman.ArbreHuffman.Noeud;
import huffman.CompterOccurrences;

/**
 * Classe de tests pour la classe ArbreHuffman.
 */
class ArbreHuffmanTest {

    private static final Object[][] OCCURRENCES = CompterOccurrences
	    .compter("Bonjour a tous");

    private static final Object[][] UNE_OCCURRENCE = CompterOccurrences
	    .compter("aaaaa");

    private static final Object[][] OCCURRENCES_NON_REPETEES = CompterOccurrences
	    .compter("abcdefghij");

    private static final Object[][] OCCURRENCES_EGALES = CompterOccurrences
	    .compter("aabbccddee");

    /**
     * Teste la construction de l'arbre de Huffman avec des occurrences de
     * caractères répétées.
     */
    @Test
    public void testConstructionArbreHuffmanAvecOccurrencesRepetees() {
	Noeud root1 = ArbreHuffman.constructionArbreHuffman(OCCURRENCES);
	assertNotNull(root1);
    }

    /**
     * Teste la construction de l'arbre de Huffman avec une seule
     * occurrence de caractère.
     */
    @Test
    public void testConstructionArbreHuffmanAvecUneOccurrence() {
	Noeud root2 = ArbreHuffman.constructionArbreHuffman(UNE_OCCURRENCE);
	assertNotNull(root2);
    }

    /**
     * Teste la construction de l'arbre de Huffman avec des caractères non
     * répétés.
     */
    @Test
    public void testConstructionArbreHuffmanAvecOccurrencesNonRepetees() {
	Noeud root4 = ArbreHuffman
		.constructionArbreHuffman(OCCURRENCES_NON_REPETEES);
	assertNotNull(root4);
    }

    /**
     * Teste la construction de l'arbre de Huffman avec des occurrences
     * égales de caractères.
     */
    @Test
    public void testArbreHuffmanAvecOccurencesEgales() {
	Noeud root = ArbreHuffman.constructionArbreHuffman(OCCURRENCES_EGALES);
	assertNotNull(root);
    }
}
