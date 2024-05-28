/*
 * CompterOccurrencesTest.java                                            22 avril 2024
 * IUT de Rodez, pas de copyright.
 */
package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;

import huffman.CompterOccurrences;
import huffman.Occurrence;

class CompterOccurrencesTest {

    private static final String[] TEXTE_NORMAL = { "Demain, des l'aube, a l'heure ou blanchit la campagne,",
	    "Je partirai. Vois-tu, je sais que tu m'attends.", "J'irai par la foret, j'irai par la montagne.",
	    "Je ne puis demeurer loin de toi plus longtemps.", "Je marcherai les yeux fixes sur mes pensees,",
	    "Sans rien voir au dehors, sans entendre aucun bruit,", "Seul, inconnu, le dos courbe, les mains croisees,",
	    "Triste, et le jour pour moi sera comme la nuit.", "Je ne regarderai ni l'or du soir qui tombe,",
	    "Ni les voiles au loin descendant vers Harfleur,", "Et quand j'arriverai, je mettrai sur ta tombe",
	    "Un bouquet de houx vert et de bruyere en fleur." };

    private static final int[] RESULTAT_TEXTE_NORMAL = { 21, 21, 18, 16, 18, 17, 16, 18, 19, 18, 19, 18 };

    private static final String TEXTE_VIDE = "";

    private static final String TEXTE_NULL = null;

    private static final String CARACTERES_SPECIAUX = "@#$%^&*()";

    private static final String CARACTERES_SIMILAIRES = "aaaaabbbbccc";

    private static final String KATAKANAS_JAPONNAIS = "片仮名カタカナ";

    private static final String CARACTERES_CYRILLIQUES = "БГД";

    @Test
    void testCompterOccurencesTexteNormal() {
	for (int i = 0; i < TEXTE_NORMAL.length; i++) {
	    List<Occurrence> resultat = CompterOccurrences.compter(TEXTE_NORMAL[i]);
	    // Vérification de la longueur de la liste de résultats
	    assertEquals(RESULTAT_TEXTE_NORMAL[i], resultat.size());
	}
    }

    @Test
    void testCompterOccurencesTexteVide() {
	assertThrows(IndexOutOfBoundsException.class, () -> {
	    CompterOccurrences.compter(TEXTE_VIDE);
	});
    }

    @Test
    void testCompterOccurencesTexteNull() {
	assertThrows(IllegalArgumentException.class, () -> {
	    CompterOccurrences.compter(TEXTE_NULL);
	});
    }

    @Test
    void testCompterOccurencesAvecCaracteresSpeciaux() {
	List<Occurrence> resultat = CompterOccurrences.compter(CARACTERES_SPECIAUX);
	assertEquals(9, resultat.size());
    }

    @Test
    void testCompterOccurencesAvecCaracteresSimilaires() {
	List<Occurrence> resultat = CompterOccurrences.compter(CARACTERES_SIMILAIRES);
	assertEquals(3, resultat.size());
	assertEquals('c', resultat.get(0).getCaractere());
	assertEquals(3, resultat.get(0).getOccurrences());
    }
}
