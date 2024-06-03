package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import huffman.Occurrence;

class OccurrenceTest {

    @Test
    void testCompter() {
        String texte = "aabbc";
        List<Occurrence> occurrences = Occurrence.compter(texte);

        // Vérifier que la liste d'occurrences n'est pas vide
        assertNotNull(occurrences);

        // Vérifier le nombre d'occurrences de chaque caractère
        assertEquals(2, occurrences.get(0).getOccurrences()); // 'a' a 2
                                                              // occurrences
        assertEquals(2, occurrences.get(1).getOccurrences()); // 'b' a 2
                                                              // occurrences
        assertEquals(1, occurrences.get(2).getOccurrences()); // 'c' a 1
                                                              // occurrence

        // Vérifier l'ordre de tri par fréquence
        assertTrue(occurrences.get(0).getFrequence() > occurrences.get(1)
                .getFrequence());
        assertTrue(occurrences.get(1).getFrequence() > occurrences.get(2)
                .getFrequence());
    }

    @Test
    void testEstUtf8() {
        String texteUtf8 = "abc";
        assertTrue(Occurrence.estUtf8(texteUtf8));

        // Caractère non UTF-8 (octet avec valeur > 127)
        String texteNonUtf8 = "é"; // Caractère 'é' (UTF-8: \u00e9)
        assertFalse(Occurrence.estUtf8(texteNonUtf8));
    }

}
