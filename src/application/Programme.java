package application;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

import gestionFichier.GestionArbreHuffman;
import gestionFichier.GestionFichierBinaire;
import gestionFichier.GestionFichierTexte;
import huffman.ArbreHuffman;
import huffman.ArbreHuffman.Noeud;
import huffman.Compression;
import huffman.CompterOccurrences;
import huffman.Decompression;

public class Programme {

    public static void main(String[] args) {

	System.out.println("Resultat de la lecture du fichier coucou.txt : \n");
	String lecture = " ";
	String nomFichier = "coucou";
	try {
	    lecture = GestionFichierTexte.lireFichier(nomFichier + ".txt");
	} catch (IOException e) {
	    e.printStackTrace();
	}
	System.out.println(lecture);

	System.out.println("Résultat de compterOccurrences de coucou.txt : \n");
	Object[][] occurrences = CompterOccurrences.compter(lecture);
	for (Object[] element : occurrences) {
	    System.out.println(element[0] + " : " + element[1]);
	}

	System.out.println(
		"\nResultat de la construction de l'arbre huffman : \n");
	Noeud racine = ArbreHuffman.constructionArbreHuffman(occurrences);
	if (racine == null) {
	    System.out.println("L'arbre est vide.");
	    return;
	}
	Queue<Noeud> queue = new LinkedList<>();
	queue.offer(racine);
	while (!queue.isEmpty()) {
	    int tailleNiveau = queue.size();
	    for (int i = 0; i < tailleNiveau; i++) {
		Noeud noeud = queue.poll();
		System.out.print(
			noeud.caractere + " : " + noeud.frequence + "   ");

		if (noeud.gauche != null) {
		    queue.offer(noeud.gauche);
		}
		if (noeud.droite != null) {
		    queue.offer(noeud.droite);
		}
	    }
	    System.out.println();
	}

	System.out.println("\nResultat de la compression de coucou.txt : \n");
	Object[][] dictionnaire = GestionArbreHuffman.creerDictionnaire(racine);
	String lectureCompresse = Compression.compresserTexte(lecture,
		dictionnaire);
	System.out.println(lectureCompresse + "\n");

	System.out.println("Ecriture de coucou.bin : \n");
	try {
	    GestionFichierBinaire.ecriture(lectureCompresse,
		    nomFichier + ".bin");
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
	String coucouDecompresse = Decompression.decompresser("coucou.bin");
	System.out.println(coucouDecompresse + "\n");
    }
}
