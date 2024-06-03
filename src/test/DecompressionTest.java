/*
 * DecompressionTest.java                                            02 mai 2024
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

class DecompressionTest {

    private static final String NOM_FICHIER_TEMPORAIRE_COMPRESSE = "testFichierCompresse.bin";
    private static final String TEXTE = "hello world";
    private static final String TEXTE_COMPRESSE = "01101110100100110111101011001111000001";
    private static final Object[][] DICTIONNAIRE = { { 'h', "0110" },
            { 'e', "1110" }, { 'l', "100" }, { 'o', "110" }, { ' ', "1111" },
            { 'w', "010" }, { 'r', "0111" }, { 'd', "0001" } };

    @BeforeEach
    void setUp() throws IOException {
        GestionFichierBinaire.ecriture(TEXTE_COMPRESSE,
                NOM_FICHIER_TEMPORAIRE_COMPRESSE);
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.deleteIfExists(Paths.get(NOM_FICHIER_TEMPORAIRE_COMPRESSE));
    }

    @Test
    void testDecompresser() throws IOException {
        File fichierCompresse = new File(NOM_FICHIER_TEMPORAIRE_COMPRESSE);
        assertTrue(fichierCompresse.exists(),
                "Le fichier compressé doit " + "exister après l'écriture.");

        String texteDecompresse = Decompression
                .decompresser(NOM_FICHIER_TEMPORAIRE_COMPRESSE, DICTIONNAIRE);
        assertEquals(TEXTE, texteDecompresse, "Le texte décompressé doit "
                + "correspondre au texte original.");
    }
}
