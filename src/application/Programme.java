package application;

import java.io.IOException;

import gestionFichier.GestionArbreHuffman;
import gestionFichier.GestionFichierBinaire;
import gestionFichier.GestionFichierTexte;
import huffman.ArbreHuffman;
import huffman.Compression;
import huffman.CompterOccurrences;
import huffman.Decompression;
import huffman.Noeud;

public class Programme {

    public static void main(String[] args) {

	System.out.println("Lecture du fichier coucou.txt ...\n");
	String lecture = "";
	String arbreHuffman = "";
	String nomFichier = "coucou";
	try {
	    lecture = GestionFichierTexte.lireFichier(nomFichier + ".txt");
	} catch (IOException e) {
	    e.printStackTrace();
	}

	System.out.println("Résultat de compterOccurrences de coucou.txt : \n");
	Object[][] occurrences = CompterOccurrences.compter(lecture);
	for (Object[] element : occurrences) {
	    char caractere = (char) element[0];
	    if (caractere == '\n') {
		caractere = '↲';
	    }
	    if (caractere == ' ') {
		caractere = '␣';
	    }
	    System.out.println(caractere + " : " + element[1]);
	}

	System.out.println("\nConstruction de l'arbre huffman ...\n");
	Noeud racine = ArbreHuffman.constructionArbreHuffman(occurrences);

	System.out.println("Résultat de la sauvegarde de l'arbre huffman : \n");
	GestionArbreHuffman.sauvegardeArbreHuffman(racine, "arbreHuffman.txt");
	try {
	    arbreHuffman = GestionFichierTexte.lireFichier("arbreHuffman.txt");
	} catch (IOException e) {
	    e.printStackTrace();
	}

	System.out.print(arbreHuffman);
	Object[][] arbreRestaure = null;

	System.out.println("\nRésultat de la restauration de l'arbre huffman : \n");
	try {
	    arbreRestaure = GestionArbreHuffman.restaurerArborescenceHuffman("arbreHuffman.txt");
	} catch (IOException e) {
	    e.printStackTrace();
	}

	for (Object[] element : arbreRestaure) {
	    char caractere = (char) element[0];
	    System.out.println(caractere + " : " + element[1]);
	}

	System.out.println("\nResultat de la compression de coucou.txt : \n");

	String lectureCompresse = Compression.compresserTexte(lecture, arbreRestaure);
	System.out.println(lectureCompresse + "\n");
	System.out.println("Ecriture de coucou.bin ...\n");
	try {
	    GestionFichierBinaire.ecriture(lectureCompresse, nomFichier + ".bin");
	} catch (IOException e) {
	    e.printStackTrace();
	}
	System.out.println("Resultat de la lecture de coucou.bin : \n");
	String lectureBinaire = "";
	try {
	    lectureBinaire = GestionFichierBinaire.lecture(nomFichier + ".bin");
	} catch (IOException e) {
	    e.printStackTrace();
	}
	System.out.println(lectureBinaire + "\n");

	System.out.println("Resultat de la decompression de coucou.bin : \n");
	String coucouDecompresse = Decompression.decompresser("coucou.bin", arbreRestaure);
	System.out.println(coucouDecompresse + "\n");
    }
}
