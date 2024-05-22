package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import gestionFichier.GestionArbreHuffman;
import huffman.ArbreHuffman;
import huffman.Compression;
import huffman.CompterOccurrences;

public class CompressionTest {

    private static final String TEXTE_A_COMPRESSER = "coucou";

    private static final String TEXTE_COMPRESSE_ATTENDU = "1000110001";

    private static final String TEXTE_VIDE = "";

    private static final String TEXTE_NULL = null;

    private static final String TEXTE_SPECIAL = "@[^";

    private static final String TEXTE_SPECIAL_COMPRESSER_ATTENDUE = "10001";

    private static final Object[][] DICTIONNAIRE = { { 'a', "100" },
	    { 'b', "101" }, { 'c', "110" } };

    private static final Object[][] DICTIONNAIRE_VIDE = {};

    private static final Object[][] DICTIONNAIRE_AVEC_NULL = { { 'a', "100" },
	    { null, null }, { 'c', "110" } };

    @Test
    void testCompressionTexteIdentique() {
	Object[][] dictionnaire = GestionArbreHuffman
		.creerDictionnaire(ArbreHuffman.constructionArbreHuffman(
			CompterOccurrences.compter(TEXTE_A_COMPRESSER)));

	String texteComprime = Compression.compresserTexte(TEXTE_A_COMPRESSER,
		dictionnaire);

	assertEquals(TEXTE_COMPRESSE_ATTENDU, texteComprime);
    }

    @Test
    void testCompressionTexteVideException() {
	try {
	    GestionArbreHuffman
		    .creerDictionnaire(ArbreHuffman.constructionArbreHuffman(
			    CompterOccurrences.compter(TEXTE_VIDE)));
	} catch (IllegalArgumentException exception) {
	    assertEquals("Le texte est vide ou null", exception.getMessage());
	}
    }

    @Test
    void testCompressionTexteNullException() {
	try {
	    GestionArbreHuffman
		    .creerDictionnaire(ArbreHuffman.constructionArbreHuffman(
			    CompterOccurrences.compter(TEXTE_NULL)));
	} catch (IllegalArgumentException exception) {
	    assertEquals("Le texte est vide ou null", exception.getMessage());
	}
    }

    @Test
    void testCompressionTexteSpecial() {
	Object[][] dictionnaire = GestionArbreHuffman
		.creerDictionnaire(ArbreHuffman.constructionArbreHuffman(
			CompterOccurrences.compter(TEXTE_SPECIAL)));
	String texteComprime = Compression.compresserTexte(TEXTE_SPECIAL,
		dictionnaire);
	assertEquals(TEXTE_SPECIAL_COMPRESSER_ATTENDUE, texteComprime);
    }

    @Test
    void testTrouverCodeHuffmanCaracterePresent() {
	assertEquals("100", Compression.trouverCodeHuffman('a', DICTIONNAIRE));
	assertEquals("101", Compression.trouverCodeHuffman('b', DICTIONNAIRE));
	assertEquals("110", Compression.trouverCodeHuffman('c', DICTIONNAIRE));
	assertNotEquals("101",
		Compression.trouverCodeHuffman('a', DICTIONNAIRE));
	assertNotEquals("110",
		Compression.trouverCodeHuffman('b', DICTIONNAIRE));
	assertNotEquals("100",
		Compression.trouverCodeHuffman('c', DICTIONNAIRE));
    }

    @Test
    void testTrouverCodeHuffmanCaractereAbsent() {
	assertEquals("", Compression.trouverCodeHuffman('d', DICTIONNAIRE));
	assertEquals("", Compression.trouverCodeHuffman('s', DICTIONNAIRE));
	assertEquals("", Compression.trouverCodeHuffman('w', DICTIONNAIRE));
	assertNotEquals("111",
		Compression.trouverCodeHuffman('f', DICTIONNAIRE));
	assertNotEquals("10",
		Compression.trouverCodeHuffman('p', DICTIONNAIRE));
    }

    @Test
    void testTrouverCodeHuffmanDictionnaireVide() {
	assertEquals("",
		Compression.trouverCodeHuffman('a', DICTIONNAIRE_VIDE));
	assertEquals("",
		Compression.trouverCodeHuffman('f', DICTIONNAIRE_VIDE));
	assertNotEquals("101",
		Compression.trouverCodeHuffman('b', DICTIONNAIRE_VIDE));
	assertNotEquals("100",
		Compression.trouverCodeHuffman('r', DICTIONNAIRE_VIDE));
    }

    @Test
    void testTrouverCodeHuffmanDictionnaireAvecNull() {
	assertEquals("100",
		Compression.trouverCodeHuffman('a', DICTIONNAIRE_AVEC_NULL));
	assertEquals("110",
		Compression.trouverCodeHuffman('c', DICTIONNAIRE_AVEC_NULL));
	assertEquals("",
		Compression.trouverCodeHuffman('b', DICTIONNAIRE_AVEC_NULL));
	assertEquals("",
		Compression.trouverCodeHuffman('h', DICTIONNAIRE_AVEC_NULL));
	assertNotEquals("",
		Compression.trouverCodeHuffman('a', DICTIONNAIRE_AVEC_NULL));
	assertNotEquals("101",
		Compression.trouverCodeHuffman('a', DICTIONNAIRE_AVEC_NULL));
    }
}