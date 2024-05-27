/*
 * NoeudTest.java                                            02 mai 2024
 * IUT de Rodez, pas de copyright.
 */
package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import huffman.Noeud;

class NoeudTest {

    @Test
    void testNoeud() {
	char caractere = 'a';
	int frequence = 5;
	Noeud noeud = new Noeud(caractere, frequence);

	assertEquals(caractere, noeud.getCaractere(), "Le caractère du noeud doit être 'a'.");
	assertEquals(frequence, noeud.getFrequence(), "La fréquence du noeud doit être 5.");

	Noeud gauche = new Noeud('b', 2);
	Noeud droite = new Noeud('c', 3);
	noeud.setGauche(gauche);
	noeud.setDroite(droite);

	assertEquals(gauche, noeud.getGauche(), "Le noeud gauche doit être défini correctement.");
	assertEquals(droite, noeud.getDroite(), "Le noeud droit doit être défini correctement.");

	Noeud gaucheGauche = new Noeud('d', 1);
	gauche.setGauche(gaucheGauche);
	assertEquals(gaucheGauche, gauche.getGauche(), "Le noeud gauche du noeud gauche doit être défini correctement.");

	Noeud droiteDroite = new Noeud('e', 1);
	droite.setDroite(droiteDroite);
	assertEquals(droiteDroite, droite.getDroite(), "Le noeud droit du noeud droit doit être défini correctement.");
    }
}
