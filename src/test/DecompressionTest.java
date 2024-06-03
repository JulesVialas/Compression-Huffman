/*
 * DecompressionTest.java
 * 22 mai 2024
 * IUT de Rodez, pas de copyright.
 */

package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gestion.GestionFichierBinaire;
import huffman.Decompression;

/**
 * La classe DecompressionTest contient les tests unitaires pour la
 * classe Decompression. Elle vérifie le bon fonctionnement de la
 * méthode de décompression en utilisant JUnit 5.
 *
 * @author Jules Vialas, Amjed Sehil, Landry Loubière
 */
class DecompressionTest {

    /** Le nom du fichier temporaire compressé utilisé pour les tests. */
    private static final String NOM_FICHIER_TEMPORAIRE_COMPRESSE = "testFichierCompresse.bin";

    /** Le texte original utilisé pour les tests. */
    private static final String TEXTE = "hello world";

    /**
     * Le texte compressé correspondant au texte original utilisé pour les
     * tests.
     */
    private static final String TEXTE_COMPRESSE = "01101110100100110111101011001111000001";

    /** Le dictionnaire Huffman utilisé pour la décompression. */
    private static final Object[][] DICTIONNAIRE = { { 'h', "0110" },
            { 'e', "1110" }, { 'l', "100" }, { 'o', "110" }, { ' ', "1111" },
            { 'w', "010" }, { 'r', "0111" }, { 'd', "0001" } };

    /**
     * Méthode exécutée avant chaque test. Elle écrit le texte compressé
     * dans un fichier temporaire pour effectuer les tests.
     *
     * @throws IOException si une erreur d'entrée ou de sortie survient
     *                     lors de l'écriture du fichier temporaire.
     */
    @BeforeEach
    void setUp() throws IOException {
        GestionFichierBinaire.ecriture(TEXTE_COMPRESSE,
                NOM_FICHIER_TEMPORAIRE_COMPRESSE);
    }

    /**
     * Méthode exécutée après chaque test. Elle supprime le fichier
     * temporaire utilisé pour les tests.
     *
     * @throws IOException si une erreur d'entrée ou de sortie survient
     *                     lors de la suppression du fichier temporaire.
     */
    @AfterEach
    void tearDown() throws IOException {
        Files.deleteIfExists(Paths.get(NOM_FICHIER_TEMPORAIRE_COMPRESSE));
    }

    /**
     * Test unitaire pour la méthode de décompression. Vérifie que le
     * texte décompressé correspond au texte original.
     *
     * @throws IOException si une erreur d'entrée ou de sortie survient
     *                     lors de la décompression.
     */
    @Test
    void testDecompresser() throws IOException {
        File fichierCompresse = new File(NOM_FICHIER_TEMPORAIRE_COMPRESSE);
        assertTrue(fichierCompresse.exists(),
                "Le fichier compressé doit exister après l'écriture.");

        String texteDecompresse = Decompression
                .decompresser(NOM_FICHIER_TEMPORAIRE_COMPRESSE, DICTIONNAIRE);
        assertEquals(TEXTE, texteDecompresse,
                "Le texte décompressé doit correspondre au texte original.");
    }
}
