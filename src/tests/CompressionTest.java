/*
 * CompressionTest.java                   08/05/2024
 * IUT de Rodez, pas de copyright
 */

package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import gestionFichier.GestionArbreHuffman;
import huffman.ArbreHuffman;
import huffman.Compression;
import huffman.CompterOccurrences;

/**
 * Classe de tests pour la classe Compression.
 */
public class CompressionTest {

    private static final String TEXTE_A_COMPRESSER = "coucou";

    private static final String TEXTE_COMPRESSE_ATTENDU = "0101101011";

    private static final String TEXTE_VIDE = "";

    private static final String TEXTE_NULL = null;

    private static final String TEXTE_SPECIAL = "@[^";

    private static final String TEXTE_SPECIAL_COMPRESSER_ATTENDUE = "01011";

    private static final Object[][] DICTIONNAIRE = { { 'a', "100" },
	    { 'b', "101" }, { 'c', "110" } };

    private static final Object[][] DICTIONNAIRE_VIDE = {};

    private static final Object[][] DICTIONNAIRE_AVEC_NULL = { { 'a', "100" },
	    { null, null }, { 'c', "110" } };

    /**
     * Teste la compression d'un texte identique.
     *
     * Vérifie que le texte compressé est égal à la représentation binaire
     * UTF-8 attendue.
     */
    @Test
    void testCompressionTexteIdentique() {
	// Création du dictionnaire de codage de Huffman
	Object[][] dictionnaire = GestionArbreHuffman
		.creerDictionnaire(ArbreHuffman.constructionArbreHuffman(
			CompterOccurrences.compter(TEXTE_A_COMPRESSER)));

	// Compression du texte grâce à la méthode compresserTexte
	String texteComprime = Compression.compresserTexte(TEXTE_A_COMPRESSER,
		dictionnaire);

	// Vérification que le texte compressé est égal à la représentation
	// UTF-8 attendue
	assertEquals(TEXTE_COMPRESSE_ATTENDU, texteComprime);
    }

    /**
     * Teste la compression d'un texte vide.
     *
     * Vérifie qu'une IllegalArgumentException est lancée lorsque le texte
     * est vide.
     */
    @Test
    void testCompressionTexteVideException() {
	// Création du dictionnaire de codage de Huffman
	try {
	    GestionArbreHuffman
		    .creerDictionnaire(ArbreHuffman.constructionArbreHuffman(
			    CompterOccurrences.compter(TEXTE_VIDE)));
	} catch (IllegalArgumentException exception) {
	    assertEquals("Le texte est vide ou null", exception.getMessage());
	}
    }

    /**
     * Teste la compression d'un texte null.
     *
     * Vérifie qu'une IllegalArgumentException est lancée lorsque le texte
     * est null.
     */
    @Test
    void testCompressionTexteNullException() {
	// Création du dictionnaire de codage de Huffman
	try {
	    GestionArbreHuffman
		    .creerDictionnaire(ArbreHuffman.constructionArbreHuffman(
			    CompterOccurrences.compter(TEXTE_NULL)));
	} catch (IllegalArgumentException exception) {
	    assertEquals("Le texte est vide ou null", exception.getMessage());
	}
    }

    /**
     * Teste la compression d'un texte avec des caractères spéciaux.
     *
     * Vérifie que le texte compressé est égal à la représentation binaire
     * UTF-8 attendue.
     */
    @Test
    void testCompressionTexteSpecial() {
	// Création du dictionnaire de codage de Huffman
	Object[][] dictionnaire = GestionArbreHuffman
		.creerDictionnaire(ArbreHuffman.constructionArbreHuffman(
			CompterOccurrences.compter(TEXTE_SPECIAL)));

	// Compression du texte spécial
	String texteComprime = Compression.compresserTexte(TEXTE_SPECIAL,
		dictionnaire);

	// Vérification que le texte compressé est égal à la représentation
	// UTF-8 attendue
	assertEquals(TEXTE_SPECIAL_COMPRESSER_ATTENDUE, texteComprime);
    }

    /**
     * Teste la méthode trouverCodeHuffman lorsque le caractère est
     * présent dans le dictionnaire.
     *
     * Vérifie que le code Huffman retourné est correct pour chaque
     * caractère.
     */
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

    /**
     * Teste la méthode trouverCodeHuffman lorsque le caractère est absent
     * du dictionnaire.
     *
     * Vérifie que la méthode retourne une chaîne vide pour un caractère
     * non trouvé.
     */
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

    /**
     * Teste la méthode trouverCodeHuffman lorsque le dictionnaire est
     * vide.
     *
     * Vérifie que la méthode retourne une chaîne vide pour tout caractère
     * dans un dictionnaire vide.
     */
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

    /**
     * Teste la méthode trouverCodeHuffman lorsque le dictionnaire
     * contient des entrées nulles.
     *
     * Vérifie que la méthode ignore les entrées nulles et retourne les
     * codes corrects pour les caractères présents.
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
	assertNotEquals("",
		Compression.trouverCodeHuffman('a', DICTIONNAIRE_AVEC_NULL));
	assertNotEquals("101",
		Compression.trouverCodeHuffman('a', DICTIONNAIRE_AVEC_NULL));
    }
}