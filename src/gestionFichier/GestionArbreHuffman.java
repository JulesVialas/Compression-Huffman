package gestionFichier;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import huffman.Noeud;

public class GestionArbreHuffman {

    public static void sauvegardeArbreHuffman(Noeud racine, String nomFichier) {
	try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomFichier))) {
	    parcourirArbre(racine, writer, "");
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    private static void parcourirArbre(Noeud noeud, BufferedWriter writer, String codeHuffman) throws IOException {
	if (noeud == null) {
	    return;
	}
	if (noeud.getGauche() == null && noeud.getDroite() == null) {
	    if (noeud.getCaractere() == '\n') {
		writer.write(
			"codeHuffman = " + codeHuffman + " ; frequence = " + noeud.getFrequence() + " ; symbole = ↲\n");
	    } else if (noeud.getCaractere() == ' ') {
		writer.write(
			"codeHuffman = " + codeHuffman + " ; frequence = " + noeud.getFrequence() + " ; symbole = ␣\n");
	    } else {
		writer.write("codeHuffman = " + codeHuffman + " ; frequence = " + noeud.getFrequence() + " ; symbole = "
			+ noeud.getCaractere() + "\n");
	    }
	}
	parcourirArbre(noeud.getGauche(), writer, codeHuffman + "0");
	parcourirArbre(noeud.getDroite(), writer, codeHuffman + "1");

    }

    public static Object[][] restaurerArborescenceHuffman(String nomFichier) throws IOException {
	List<Object[]> dictionnaire = new ArrayList<>();

	try (BufferedReader reader = new BufferedReader(new FileReader(nomFichier))) {
	    String ligne;
	    while ((ligne = reader.readLine()) != null) {
		if (ligne.trim().isEmpty()) {
		    continue; // Ignorer les lignes vides
		}

		String[] parties = ligne.split(";");

		if (parties.length != 3) {
		    continue; // Ignorer les lignes mal formatées
		}

		String[] codePart = parties[0].split("=");
		String[] encodePart = parties[1].split("=");
		String[] symbolePart = parties[2].split("=");

		if (codePart.length != 2 || encodePart.length != 2 || symbolePart.length != 2) {
		    continue; // Ignorer les lignes mal formatées
		}

		String codeHuffman = codePart[1].trim();
		String symboleStr = symbolePart[1].trim();
		char symbole = decodeSymbole(symboleStr);

		dictionnaire.add(new Object[] { symbole, codeHuffman });
	    }
	}

	return dictionnaire.toArray(new Object[0][0]);
    }

    private static char decodeSymbole(String symboleStr) {
	switch (symboleStr) {
	case "↲":
	    return '\n';
	case "␣":
	    return ' ';
	default:
	    return symboleStr.length() == 1 ? symboleStr.charAt(0) : '\0';
	}
    }
}