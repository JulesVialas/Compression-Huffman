/*
 * CompressionTest.java                                            16 mai 2024
 * IUT de Rodez, pas de copyright.
 */
package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import huffman.Compression;

/**
 * <p>
 * Sert à tester la classe Compression, qui fournit des
 * fonctionnalités de compression utilisant l'algorithme de Huffman.
 * Les tests incluent la compression de texte, la gestion des cas
 * d'erreur, et la recherche de codes Huffman pour les caractères.
 * </p>
 */
class CompressionTest {

    // Définition des dictionnaires de test

    private static final Object[][] DICTIONNAIRE = { { 'c', "00" },
            { 'o', "01" }, { 'u', "10" } };

    private static final Object[][] DICTIONNAIRE_VIDE = {};

    private static final Object[][] DICTIONNAIRE_AVEC_NULL = { { 'a', "100" },
            { null, null }, { 'c', "110" } };

    /**
     * <p>
     * Test de compression d'un texte identique avec un dictionnaire
     * donné.
     * </p>
     */
    @Test
    void testCompressionTexteIdentique() {
        String texteACompresser = "coucou";
        String texteComprimeAttendu = "000110000110";

        String texteComprime = Compression.compresserTexte(texteACompresser,
                DICTIONNAIRE);

        assertEquals(texteComprimeAttendu, texteComprime);
    }

    /**
     * <p>
     * Test de gestion d'une exception lorsque le texte à compresser est
     * vide.
     * </p>
     */
    @Test
    void testCompressionTexteVideException() {
        assertThrows(IllegalArgumentException.class, () -> {
            Compression.compresserTexte("", DICTIONNAIRE);
        });
    }

    /**
     * <p>
     * Test de gestion d'une exception lorsque le texte à compresser est
     * null.
     * </p>
     */
    @Test
    void testCompressionTexteNullException() {
        assertThrows(IllegalArgumentException.class, () -> {
            Compression.compresserTexte(null, DICTIONNAIRE);
        });
    }

    /**
     * <p>
     * Test de recherche du code Huffman pour des caractères présents dans
     * le dictionnaire.
     * </p>
     */
    @Test
    void testTrouverCodeHuffmanCaracterePresent() {
        assertEquals("00", Compression.trouverCodeHuffman('c', DICTIONNAIRE));
        assertEquals("01", Compression.trouverCodeHuffman('o', DICTIONNAIRE));
        assertEquals("10", Compression.trouverCodeHuffman('u', DICTIONNAIRE));
    }

    /**
     * <p>
     * Test de recherche du code Huffman pour des caractères absents du
     * dictionnaire.
     * </p>
     */
    @Test
    void testTrouverCodeHuffmanCaractereAbsent() {
        assertEquals("", Compression.trouverCodeHuffman('d', DICTIONNAIRE));
        assertEquals("", Compression.trouverCodeHuffman('s', DICTIONNAIRE));
        assertEquals("", Compression.trouverCodeHuffman('w', DICTIONNAIRE));
    }

    /**
     * <p>
     * Test de recherche du code Huffman dans un dictionnaire vide.
     * </p>
     */
    @Test
    void testTrouverCodeHuffmanDictionnaireVide() {
        assertEquals("",
                Compression.trouverCodeHuffman('a', DICTIONNAIRE_VIDE));
        assertEquals("",
                Compression.trouverCodeHuffman('f', DICTIONNAIRE_VIDE));
    }

    /**
     * <p>
     * Test de recherche du code Huffman dans un dictionnaire contenant
     * des valeurs null.
     * </p>
     */
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
    }
}
