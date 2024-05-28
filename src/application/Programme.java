/*
 * Programme.java                                        20 mai 2024
 * IUT de Rodez, pas de copyright.
 */
package application;

import java.io.IOException;

import gestion.GestionArbreHuffman;
import gestion.GestionFichierBinaire;
import gestion.GestionFichierTexte;
import gestion.TailleFichiers;
import huffman.ArbreHuffman;
import huffman.Compression;
import huffman.CompterOccurrences;
import huffman.Decompression;
import huffman.Noeud;

/**
 * TODO commenter la responsabilité de cette classe (SRP)
 */
public class Programme {

    /**
     * TODO commenter le rôle de cette méthode
     * @param args
     */
    public static void main(String[] args) {
	String lecture = "";
	String arbreHuffman = "";
	String nomFichier = "coucou";
	System.out.println("Lecture du fichier " + nomFichier + "txt ...");

	try {
	    lecture = GestionFichierTexte.lireFichier(nomFichier + ".txt");
	} catch (IOException e) {
	    e.printStackTrace();
	}

	System.out.println("Résultat de compterOccurrences de " + nomFichier 
		+ ".txt : ");
	Object[][] occurrences = CompterOccurrences.compter(lecture);

	for (Object[] element : occurrences) {
	    char caractere = (char) element[0];
	    if (caractere == '\n') {
		caractere = '↲';
	    }
	    if (caractere == ' ') {
		caractere = '␣';
	    }

	    System.out.println(
		    caractere + " : " + element[1] + " : " + (double) (int)
		    element[1] / lecture.length()); 
	}

	System.out.println("Construction de l'arbre huffman ...");
	Noeud racine = ArbreHuffman.constructionArbreHuffman(occurrences);

	System.out.println("Résultat de la sauvegarde de l'arbre huffman : ");
	GestionArbreHuffman.sauvegardeArbreHuffman(racine, "arbreHuffman" 
		+ nomFichier + ".txt");
	try {
	    arbreHuffman = GestionFichierTexte.lireFichier("arbreHuffman"
		    + nomFichier + ".txt");
	} catch (IOException e) {
	    e.printStackTrace();
	}
	System.out.println(arbreHuffman);

	Object[][] arbreRestaure = null;
	System.out.println("Résultat de la restauration de l'arbre huffman : ");
	try {
	    arbreRestaure = GestionArbreHuffman.restaurerArbreHuffman(
		    "arbreHuffman" + nomFichier + ".txt");
	} catch (IOException e) {
	    e.printStackTrace();
	}
	for (Object[] element : arbreRestaure) {
	    char caractere = (char) element[0];
	    System.out.println(caractere + " : " + element[1]);
	}

	System.out.println("Resultat de la compression de " + nomFichier 
		+ ".txt");
	long startTime = System.currentTimeMillis();
	String lectureCompresse = Compression.compresserTexte(
		lecture, arbreRestaure);
	try {
	    GestionFichierBinaire.ecriture(lectureCompresse, nomFichier 
		    + ".bin");
	} catch (IOException e) {
	    e.printStackTrace();
	}
	long compressionTime = System.currentTimeMillis() - startTime;
	System.out.println(lectureCompresse + "");
	System.out.println(
		"Taux de compression " + TailleFichiers.tauxCompression(
			nomFichier + ".txt", nomFichier + ".bin"));
	System.out.println("Durée de la compression : " + compressionTime
		+ " ms");

	System.out.println("Resultat de la decompression de coucou.bin : ");
	startTime = System.currentTimeMillis();
	String texteDecompresse = Decompression.decompresser(
		"coucou.bin", arbreRestaure);
	GestionFichierTexte.ecrireFichier(texteDecompresse, nomFichier 
		+ "Decompresse.txt");
	long decompressionTime = System.currentTimeMillis() - startTime;
	System.out.println(texteDecompresse);
	System.out.println("Taux de decompression "
		+ TailleFichiers.tauxCompression(nomFichier + ".txt",
			nomFichier + "Decompresse.txt"));
	System.out.println("Durée de la decompression : " + decompressionTime 
		+ " ms");
	System.out.println("Ecriture de " + nomFichier + ".txt");
    }
}
