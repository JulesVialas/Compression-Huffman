package tests;

import org.junit.jupiter.api.Test;

import huffman.Noeud;

import static org.junit.jupiter.api.Assertions.*;

public class NoeudTest {

    @Test
    public void testNoeud() {
        char caractere = 'a';
        int frequence = 5;
        Noeud noeud = new Noeud(caractere, frequence);

        // Test des getters
        assertEquals(caractere, noeud.getCaractere(), "Le caractère du nœud doit être 'a'.");
        assertEquals(frequence, noeud.getFrequence(), "La fréquence du nœud doit être 5.");

        // Test des setters et des getters pour les enfants
        Noeud gauche = new Noeud('b', 2);
        Noeud droite = new Noeud('c', 3);
        noeud.setGauche(gauche);
        noeud.setDroite(droite);

        assertEquals(gauche, noeud.getGauche(), "Le nœud gauche doit être défini correctement.");
        assertEquals(droite, noeud.getDroite(), "Le nœud droit doit être défini correctement.");

        // Test des setters et des getters des enfants du nœud gauche
        Noeud gaucheGauche = new Noeud('d', 1);
        gauche.setGauche(gaucheGauche);
        assertEquals(gaucheGauche, gauche.getGauche(), "Le nœud gauche du nœud gauche doit être défini correctement.");

        // Test des setters et des getters des enfants du nœud droit
        Noeud droiteDroite = new Noeud('e', 1);
        droite.setDroite(droiteDroite);
        assertEquals(droiteDroite, droite.getDroite(), "Le nœud droit du nœud droit doit être défini correctement.");
    }
}
