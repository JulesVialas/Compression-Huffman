package application;

import java.io.File;
import java.util.List;

import gestion.GestionArbreHuffman;
import gestion.GestionFichierBinaire;
import gestion.GestionFichierTexte;
import huffman.ArbreHuffman;
import huffman.Compression;
import huffman.CompterOccurrences;
import huffman.Decompression;
import huffman.Noeud;
import huffman.Occurrence;

public class Modele {

    public static List<Occurrence> compterOccurrences(String filePath) throws Exception {
	String content = GestionFichierTexte.lireFichier(filePath);
	return CompterOccurrences.compter(content);
    }

    public void créerArbreHuffman(String filePath, String outputFilePath) throws Exception {
	String content = GestionFichierTexte.lireFichier(filePath);
	List<Occurrence> occurrences = CompterOccurrences.compter(content);
	Noeud racine = ArbreHuffman.constructionArbreHuffman(occurrences);
	GestionArbreHuffman.sauvegardeArbreHuffman(racine, outputFilePath);
    }

    public void compresserFichier(String inputFilePath, String dictionaryPath, String outputFilePath) throws Exception {
	String content = GestionFichierTexte.lireFichier(inputFilePath);
	String compressedText = Compression.compresserTexte(content,
		GestionArbreHuffman.restaurerArbreHuffman(dictionaryPath));
	GestionFichierBinaire.ecriture(compressedText, outputFilePath);
    }

    public void decompresserFichier(String inputFilePath, String dictionaryPath, String outputFilePath)
	    throws Exception {
	String decompressedText = Decompression.decompresser(inputFilePath,
		GestionArbreHuffman.restaurerArbreHuffman(dictionaryPath));
	GestionFichierTexte.ecrireFichier(decompressedText, outputFilePath);
    }

    public double calculerTauxCompression(String originalFilePath, String compressedFilePath) {
	File originalFile = new File(originalFilePath);
	File compressedFile = new File(compressedFilePath);
	double originalSize = originalFile.length();
	double compressedSize = compressedFile.length();
	return originalSize / compressedSize;
    }
}
