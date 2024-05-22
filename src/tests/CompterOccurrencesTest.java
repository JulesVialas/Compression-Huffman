package tests;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import huffman.CompterOccurrences;

class CompterOccurrencesTest {

    private static final String[] TEXTE_NORMAL = {
	    "Demain, des l'aube, a l'heure ou blanchit la campagne,",
	    "Je partirai. Vois-tu, je sais que tu m'attends.",
	    "J'irai par la foret, j'irai par la montagne.",
	    "Je ne puis demeurer loin de toi plus longtemps.",
	    "Je marcherai les yeux fixes sur mes pensees,",
	    "Sans rien voir au dehors, sans entendre aucun bruit,",
	    "Seul, inconnu, le dos courbe, les mains croisees,",
	    "Triste, et le jour pour moi sera comme la nuit.",
	    "Je ne regarderai ni l'or du soir qui tombe,",
	    "Ni les voiles au loin descendant vers Harfleur,",
	    "Et quand j'arriverai, je mettrai sur ta tombe",
	    "Un bouquet de houx vert et de bruyere en fleur." };

    private static final int[] RESULTAT_TEXTE_NORMAL = { 21, 21, 18, 16, 18, 17,
	    16, 18, 19, 18, 19, 18 };

    private static final String TEXTE_VIDE = "";

    private static final String TEXTE_NULL = null;

    private static final String CARACTERES_SPECIAUX = "@#$%^&*()";

    private static final String CARACTERES_SIMILAIRES = "aaaaabbbbccc";

    private static final String KATAKANAS_JAPONNAIS = "片仮名カタカナ";

    private static final String CARACTERES_CYRILLIQUES = "БГД";

    @Test
    public void testCompterOccurencesTexteNormal() {

	for (int rang = 0; rang < TEXTE_NORMAL.length; rang++) {
	    Object[][] resultat = CompterOccurrences
		    .compter(TEXTE_NORMAL[rang]);
	    // Vérification la longueur du tableau de résultats
	    assertEquals(RESULTAT_TEXTE_NORMAL[rang], resultat.length);
	}
    }

    @Test
    public static void testCompterOccurencesTexteVide() {
	try {
	    assertThrows(IndexOutOfBoundsException.class, () -> {
		CompterOccurrences.compter(TEXTE_VIDE);
	    });
	} catch (IndexOutOfBoundsException erreur) {
	    System.out.print(erreur.getMessage());
	}
    }

    @Test
    public void testCompterOccurencesTexteNull() {
	assertThrows(IllegalArgumentException.class, () -> {
	    CompterOccurrences.compter(TEXTE_NULL);
	});
    }

    @Test
    public void testCompterOccurencesAvecCaracteresSpeciaux() {
	Object[][] resultat = CompterOccurrences.compter(CARACTERES_SPECIAUX);
	assertEquals(9, resultat.length);
    }

    @Test
    public void testCompterOccurencesAvecCaracteresSimilaires() {
	Object[][] resultat = CompterOccurrences.compter(CARACTERES_SIMILAIRES);
	assertEquals(3, resultat.length);
	assertEquals('c', resultat[0][0]);
	assertEquals(0.25, resultat[0][1]);
    }

    @Test
    public void testCaracteresEtranges() {
	try {
	    assertThrows(IllegalArgumentException.class, () -> {
		CompterOccurrences.compter(KATAKANAS_JAPONNAIS);
	    });
	} catch (IllegalArgumentException erreur) {
	    System.out.print(erreur.getMessage());
	}
	try {
	    assertThrows(IllegalArgumentException.class, () -> {
		CompterOccurrences.compter(CARACTERES_CYRILLIQUES);
	    });
	} catch (IllegalArgumentException erreur) {
	    System.out.print(erreur.getMessage());
	}
    }
}