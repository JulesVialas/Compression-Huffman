/*
 * GestionArbreHuffmanTest.java                         13 mai 2024
 * IUT de Rodez, pas de copyright
 */

package test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gestion.GestionArbreHuffman;
import huffman.Noeud;

/**
 * La classe GestionArbreHuffmanTest contient les tests unitaires pour
 * la classe GestionArbreHuffman. Elle vérifie le bon fonctionnement
 * des méthodes de sauvegarde et de restauration d'un arbre de
 * Huffman.
 *
 * @author Jules Vialas, Aurélien Valat
 */
class GestionArbreHuffmanTest {

    /** Le chemin du fichier temporaire utilisé pour les tests. */
    private static final String TEMP_FILE_PATH = "testArbreHuffman.txt";

    /** Le nœud racine de l'arbre de Huffman utilisé pour les tests. */
    private Noeud racine;

    /**
     * Méthode exécutée avant chaque test. Elle initialise un arbre de
     * Huffman avec deux feuilles.
     */
    @BeforeEach
    void setUp() {
        Noeud feuilleA = new Noeud('a', 5);
        Noeud feuilleB = new Noeud('b', 9);

        Noeud ArbreHuffmanInterne = new Noeud('\0',
                feuilleA.getFrequence() + feuilleB.getFrequence());

        ArbreHuffmanInterne.setGauche(feuilleA);
        ArbreHuffmanInterne.setDroite(feuilleB);

        racine = ArbreHuffmanInterne;
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
        Files.deleteIfExists(Paths.get(TEMP_FILE_PATH));
    }

    /**
     * Test unitaire pour la sauvegarde et la restauration de l'arbre de
     * Huffman. Vérifie que l'arbre de Huffman restauré correspond à
     * l'arbre de Huffman sauvegardé.
     *
     * @throws IOException si une erreur d'entrée ou de sortie survient
     *                     lors de la sauvegarde ou de la restauration.
     */
    @Test
    void testSauvegardeEtRestaurerArbreHuffman() throws IOException {
        GestionArbreHuffman.sauvegardeArbreHuffman(racine, TEMP_FILE_PATH);

        File fichierSauvegarde = new File(TEMP_FILE_PATH);
        assertTrue(fichierSauvegarde.exists(),
                "Le fichier de sauvegarde devrait exister.");

        Object[][] dictionnaire = GestionArbreHuffman
                .restaurerArbreHuffman(TEMP_FILE_PATH);

        Object[][] dictionnaireAttendu = { { 'a', "0" }, { 'b', "1" } };

        assertArrayEquals(dictionnaireAttendu, dictionnaire,
                "Le dictionnaire restauré ne correspond pas au dictionnaire attendu.");
    }
}
