/*
 * GestonFichierBinaireTest.java                              26 mai 2024
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

class GestionFichierBinaireTest {

    private static final String TEMP_FILE_PATH = "testFichierBinaire.bin";
    private static final String STRING_BINAIRE = "1010101011001100" + "1100110010101010";

    @BeforeEach
    void setUp() throws IOException {
	GestionFichierBinaire.ecriture(STRING_BINAIRE, TEMP_FILE_PATH);
    }

    @AfterEach
    void tearDown() throws IOException {
	Files.deleteIfExists(Paths.get(TEMP_FILE_PATH));
    }

    @Test
    void testLecture() throws IOException {
	String contenuLu = GestionFichierBinaire.lecture(TEMP_FILE_PATH);
	assertEquals(STRING_BINAIRE, contenuLu, "Le contenu lu doit " + "correspondre au contenu écrit.");
    }

    @Test
    void testEcriture() throws IOException {
	File fichierBinaire = new File(TEMP_FILE_PATH);
	assertTrue(fichierBinaire.exists(), "Le fichier binaire doit exister " + "après l'écriture.");

	String contenuLu = GestionFichierBinaire.lecture(TEMP_FILE_PATH);
	assertEquals(STRING_BINAIRE, contenuLu, "Le contenu lu doit correspondre" + " au contenu écrit.");
    }
}
