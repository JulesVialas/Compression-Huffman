/*
 * GestionFichierTexteTest.java                   23/04/2024
 * IUT de Rodez, pas de copyright
 */

package tests;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;

import org.junit.Test;

import gestionFichier.GestionFichierTexte;

public class GestionFichierTexteTest {

    @Test
    public void testLireFichierInexistant() {
	String cheminFichierInexistant = "fichier_inexistant.txt";

	assertThrows(IOException.class, () -> {
	    GestionFichierTexte.lireFichier(cheminFichierInexistant);
	});
    }
}
