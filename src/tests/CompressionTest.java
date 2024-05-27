/*
 * CompressionTest.java                                            16 mai 2024
 * IUT de Rodez, pas de copyright.
 */
package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import huffman.Compression;

class CompressionTest {

    private static final Object[][] DICTIONNAIRE = { { 'c', "00" }, { 'o', "01" }, { 'u', "10" } };

    private static final Object[][] DICTIONNAIRE_VIDE = {};

    private static final Object[][] DICTIONNAIRE_AVEC_NULL = { { 'a', "100" }, { null, null }, { 'c', "110" } };

    @Test
    void testCompressionTexteIdentique() {
	String texteACompresser = "coucou";
	String texteComprimeAttendu = "000110000110";

	String texteComprime = Compression.compresserTexte(texteACompresser, DICTIONNAIRE);

	assertEquals(texteComprimeAttendu, texteComprime);
    }

    @Test
    void testCompressionTexteVideException() {
	assertThrows(IllegalArgumentException.class, () -> {
	    Compression.compresserTexte("", DICTIONNAIRE);
	});
    }

    @Test
    void testCompressionTexteNullException() {
	assertThrows(IllegalArgumentException.class, () -> {
	    Compression.compresserTexte(null, DICTIONNAIRE);
	});
    }

    @Test
    void testTrouverCodeHuffmanCaracterePresent() {
	assertEquals("00", Compression.trouverCodeHuffman('c', DICTIONNAIRE));
	assertEquals("01", Compression.trouverCodeHuffman('o', DICTIONNAIRE));
	assertEquals("10", Compression.trouverCodeHuffman('u', DICTIONNAIRE));
    }

    @Test
    void testTrouverCodeHuffmanCaractereAbsent() {
	assertEquals("", Compression.trouverCodeHuffman('d', DICTIONNAIRE));
	assertEquals("", Compression.trouverCodeHuffman('s', DICTIONNAIRE));
	assertEquals("", Compression.trouverCodeHuffman('w', DICTIONNAIRE));
    }

    @Test
    void testTrouverCodeHuffmanDictionnaireVide() {
	assertEquals("", Compression.trouverCodeHuffman('a', DICTIONNAIRE_VIDE));
	assertEquals("", Compression.trouverCodeHuffman('f', DICTIONNAIRE_VIDE));
    }

    @Test
    void testTrouverCodeHuffmanDictionnaireAvecNull() {
	assertEquals("100", Compression.trouverCodeHuffman('a', DICTIONNAIRE_AVEC_NULL));
	assertEquals("110", Compression.trouverCodeHuffman('c', DICTIONNAIRE_AVEC_NULL));
	assertEquals("", Compression.trouverCodeHuffman('b', DICTIONNAIRE_AVEC_NULL));
	assertEquals("", Compression.trouverCodeHuffman('h', DICTIONNAIRE_AVEC_NULL));
    }
}
