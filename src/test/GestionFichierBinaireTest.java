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

/**
 * La classe GestionFichierBinaireTest contient les tests unitaires
 * pour la classe GestionFichierBinaire. Elle vérifie le bon
 * fonctionnement des méthodes de lecture et d'écriture des fichiers
 * binaires.
 *
 * @author Jules Vialas, Aurélien Valat, Robin Montes, Amjed Sehil,
 *         Landry Loubière
 */
class GestionFichierBinaireTest {

    /** Le chemin du fichier temporaire utilisé pour les tests. */
    private static final String TEMP_FILE_PATH = "testFichierBinaire.bin";

    /** La chaîne binaire utilisée pour les tests. */
    private static final String STRING_BINAIRE = "1010101011001100"
            + "1100110010101010";

    /**
     * Méthode exécutée avant chaque test. Elle écrit la chaîne binaire
     * dans un fichier binaire temporaire.
     *
     * @throws IOException si une erreur d'entrée ou de sortie survient
     *                     lors de l'écriture du fichier binaire
     *                     temporaire.
     */
    @BeforeEach
    void setUp() throws IOException {
        GestionFichierBinaire.ecriture(STRING_BINAIRE, TEMP_FILE_PATH);
    }

    /**
     * Méthode exécutée après chaque test. Elle supprime le fichier
     * binaire temporaire utilisé pour les tests.
     *
     * @throws IOException si une erreur d'entrée ou de sortie survient
     *                     lors de la suppression du fichier binaire
     *                     temporaire.
     */
    @AfterEach
    void tearDown() throws IOException {
        Files.deleteIfExists(Paths.get(TEMP_FILE_PATH));
    }

    /**
     * Test unitaire pour la lecture d'un fichier binaire. Vérifie que le
     * contenu lu correspond au contenu écrit dans le fichier binaire.
     *
     * @throws IOException si une erreur d'entrée ou de sortie survient
     *                     lors de la lecture du fichier binaire.
     */
    @Test
    void testLecture() throws IOException {
        String contenuLu = GestionFichierBinaire.lecture(TEMP_FILE_PATH);
        assertEquals(STRING_BINAIRE, contenuLu,
                "Le contenu lu doit correspondre au contenu écrit.");
    }

    /**
     * Test unitaire pour l'écriture d'un fichier binaire. Vérifie que le
     * fichier binaire est créé avec succès et que son contenu correspond
     * à la chaîne binaire écrite.
     *
     * @throws IOException si une erreur d'entrée ou de sortie survient
     *                     lors de la lecture du fichier binaire écrit.
     */
    @Test
    void testEcriture() throws IOException {
        File fichierBinaire = new File(TEMP_FILE_PATH);
        assertTrue(fichierBinaire.exists(),
                "Le fichier binaire doit exister après l'écriture.");

        String contenuLu = GestionFichierBinaire.lecture(TEMP_FILE_PATH);
        assertEquals(STRING_BINAIRE, contenuLu,
                "Le contenu lu doit correspondre au contenu écrit.");
    }
}
