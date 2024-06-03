/*
 * GestionFichierTexteTest.java                                   22 avril 2024
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

import gestion.GestionFichierTexte;

/**
 * La classe GestionFichierTexteTest contient les tests unitaires pour
 * la classe GestionFichierTexte. Elle vérifie le bon fonctionnement
 * des méthodes de lecture et d'écriture des fichiers texte.
 *
 * @author Aurélien Valat, Landry Loubière
 */
class GestionFichierTexteTest {

    /** Le chemin du fichier temporaire utilisé pour les tests. */
    private static final String TEMP_FILE_PATH = "testFile.txt";

    /** Le contenu du fichier texte utilisé pour les tests. */
    private static final String FILE_CONTENT = "Ligne 1\nLigne 2\nLigne 3";

    /**
     * Méthode exécutée avant chaque test. Elle écrit le contenu dans un
     * fichier texte temporaire.
     *
     * @throws IOException si une erreur d'entrée ou de sortie survient
     *                     lors de l'écriture du fichier texte temporaire.
     */
    @BeforeEach
    void setUp() throws IOException {
        Files.write(Paths.get(TEMP_FILE_PATH), FILE_CONTENT.getBytes());
    }

    /**
     * Méthode exécutée après chaque test. Elle supprime le fichier texte
     * temporaire utilisé pour les tests.
     *
     * @throws IOException si une erreur d'entrée ou de sortie survient
     *                     lors de la suppression du fichier texte
     *                     temporaire.
     */
    @AfterEach
    void tearDown() throws IOException {
        Files.deleteIfExists(Paths.get(TEMP_FILE_PATH));
    }

    /**
     * Test unitaire pour la lecture d'un fichier texte. Vérifie que le
     * contenu lu correspond au contenu écrit dans le fichier texte.
     *
     * @throws IOException si une erreur d'entrée ou de sortie survient
     *                     lors de la lecture du fichier texte.
     */
    @Test
    void testLireFichier() throws IOException {
        String contenu = GestionFichierTexte.lireFichier(TEMP_FILE_PATH);
        assertEquals(FILE_CONTENT, contenu,
                "Le contenu lu ne correspond pas au contenu attendu.");
    }

    /**
     * Test unitaire pour l'écriture d'un fichier texte. Vérifie que le
     * fichier texte est créé avec succès et que son contenu correspond à
     * la chaîne écrite.
     *
     * @throws IOException si une erreur d'entrée ou de sortie survient
     *                     lors de la lecture du fichier texte écrit.
     */
    @Test
    void testEcrireFichier() throws IOException {
        String nouveauContenu = "Nouveau Contenu\nPour Test";
        String nouveauFichierPath = "nouveauTestFile.txt";

        GestionFichierTexte.ecrireFichier(nouveauContenu, nouveauFichierPath);

        File nouveauFichier = new File(nouveauFichierPath);
        assertTrue(nouveauFichier.exists(), "Le fichier devrait exister.");

        String contenuLu = GestionFichierTexte.lireFichier(nouveauFichierPath);
        assertEquals(nouveauContenu, contenuLu,
                "Le contenu du fichier ne correspond pas au contenu écrit.");

        Files.deleteIfExists(Paths.get(nouveauFichierPath));
    }
}
