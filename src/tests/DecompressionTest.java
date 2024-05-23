package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gestionFichier.GestionFichierBinaire;
import huffman.Decompression;

public class DecompressionTest {

    private static final String TEMP_COMPRESSED_FILE_PATH = "testFichierCompresse.bin";
    private static final String TEXT_ORIGINAL = "hello world";
    private static final String TEXT_COMPRESSED = "01101110100100110111101011001111000001";
    private static final Object[][] DICTIONARY = { { 'h', "0110" }, { 'e', "1110" }, { 'l', "100" }, { 'o', "110" },
	    { ' ', "1111" }, { 'w', "010" }, { 'r', "0111" }, { 'd', "0001" } };

    @BeforeEach
    public void setUp() throws IOException {
	GestionFichierBinaire.ecriture(TEXT_COMPRESSED, TEMP_COMPRESSED_FILE_PATH);
    }

    @AfterEach
    public void tearDown() throws IOException {
	Files.deleteIfExists(Paths.get(TEMP_COMPRESSED_FILE_PATH));
    }

    @Test
    public void testDecompresser() throws IOException {
	File fichierCompresse = new File(TEMP_COMPRESSED_FILE_PATH);
	assertTrue(fichierCompresse.exists(), "Le fichier compressé doit exister après l'écriture.");

	String texteDecompresse = Decompression.decompresser(TEMP_COMPRESSED_FILE_PATH, DICTIONARY);
	assertEquals(TEXT_ORIGINAL, texteDecompresse, "Le texte décompressé doit correspondre au texte original.");
    }
}
