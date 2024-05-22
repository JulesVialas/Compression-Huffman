package gestionFichier;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import huffman.Noeud;

public class GestionArbreHuffman {

    private static int rang = 0;

    public static Object[][] creerDictionnaire(Noeud racine) {
	int nombreDeCaracteres = compterCaracteres(racine);
	Object[][] dictionnaire = new Object[nombreDeCaracteres][2];
	rang = 0;
	remplirDictionnaire(racine, dictionnaire, "");
	return dictionnaire;
    }

    private static void remplirDictionnaire(Noeud racine,
	    Object[][] dictionnaire, String codeHuffman) {
	if (racine == null) {
	    return;
	}
	if (racine.getGauche() == null && racine.getDroite() == null) {
	    dictionnaire[rang][0] = (char) racine.getCaractere();
	    dictionnaire[rang][1] = codeHuffman;
	    rang++;
	}
	remplirDictionnaire(racine.getGauche(), dictionnaire,
		codeHuffman + "0");
	remplirDictionnaire(racine.getDroite(), dictionnaire,
		codeHuffman + "1");
    }

    private static int compterCaracteres(Noeud racine) {
	if (racine == null) {
	    return 0;
	}
	if (racine.getGauche() == null && racine.getDroite() == null) {
	    return 1;
	}
	return compterCaracteres(racine.getGauche())
		+ compterCaracteres(racine.getDroite());
    }

    public static void sauvegardeArbreHuffman(Noeud racine, String nomFichier) {
	try (BufferedWriter writer = new BufferedWriter(
		new FileWriter(nomFichier))) {
	    parcourirArbre(racine, writer, "");
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    private static void parcourirArbre(Noeud noeud, BufferedWriter writer,
	    String codeHuffman) throws IOException {
	if (noeud == null) {
	    return;
	}
	if (noeud.getGauche() == null && noeud.getDroite() == null) {
	    writer.write("codeHuffman = " + codeHuffman + " ; encode = "
		    + Integer.toBinaryString(noeud.getCaractere())
		    + " ; symbole = " + noeud.getCaractere() + "\n");
	}

	parcourirArbre(noeud.getGauche(), writer, codeHuffman + "0");
	parcourirArbre(noeud.getDroite(), writer, codeHuffman + "1");
    }

    public Noeud restaurerArbreHuffman(String nomFichier) {
	Noeud racine = null;

	return racine;
    }
}
