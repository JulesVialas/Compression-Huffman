/*
 * OccurrenceTest.java                                            22 avril 2024
 * IUT de Rodez, pas de copyright.
 */
package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import huffman.Occurrence;

/**
 * La classe OccurrenceTest contient les tests unitaires pour la
 * classe Occurrence. Elle vérifie le comptage des occurrences de
 * caractères dans un texte ainsi que la détection de l'encodage
 * UTF-8.
 *
 * @author Jules Vialas, Robin Montes, Aurélien Valat, Landry
 *         Loubière, Amjed Sehil
 */
class OccurrenceTest {

    /**
     * Test unitaire pour la méthode compter. Vérifie que le comptage des
     * occurrences de caractères dans un texte fonctionne correctement.
     */
    @Test
    void testCompter() {
        String texte = "aabbc";
        List<Occurrence> occurrences = Occurrence.compter(texte);

        // Vérifier que la liste d'occurrences n'est pas vide
        assertNotNull(occurrences);

        // Vérifier le nombre d'occurrences de chaque caractère
        assertEquals(2, occurrences.get(2).getOccurrences()); // 'a' a 2
                                                              // occurrences
        assertEquals(2, occurrences.get(1).getOccurrences()); // 'b' a 2
                                                              // occurrences
        assertEquals(1, occurrences.get(0).getOccurrences()); // 'c' a 1
                                                              // occurrence

        assertEquals(3, occurrences.size());

        // Vérifier l'ordre de tri par fréquence
        assertTrue(occurrences.get(1).getFrequence() > occurrences.get(0)
                .getFrequence());
        assertTrue(occurrences.get(1).getFrequence() <= occurrences.get(2)
                .getFrequence());
    }

    /**
     * Test unitaire pour la méthode estUtf8. Vérifie que la méthode
     * détecte correctement si un texte est encodé en UTF-8.
     */
    @Test
    void testEstUtf8() {
        String texteUtf8 = "abc";
        assertTrue(Occurrence.estUtf8(texteUtf8));
    }

}
