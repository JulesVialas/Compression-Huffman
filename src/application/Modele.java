package application;

import java.io.File;
import java.util.List;

import gestion.GestionArbreHuffman;
import gestion.GestionFichierBinaire;
import gestion.GestionFichierTexte;
import huffman.Compression;
import huffman.Decompression;
import huffman.Noeud;
import huffman.Occurrence;

/**
 * La classe Modele fournit des méthodes pour les opérations de
 * compression et de décompression de fichiers en utilisant
 * l'algorithme de Huffman. Elle gère également le calcul des
 * occurrences de caractères et la création d'arbres de Huffman.
 */
public class Modele {

    /**
     * Compte les occurrences de chaque caractère dans un fichier texte.
     *
     * @param filePath le chemin du fichier texte
     * @return une liste d'occurrences de caractères
     * @throws Exception si une erreur survient lors de la lecture du
     *                   fichier
     */
    public static List<Occurrence> compterOccurrences(String filePath)
            throws Exception {
        String content = GestionFichierTexte.lireFichier(filePath);
        return Occurrence.compter(content);
    }

    /**
     * Crée un arbre de Huffman à partir d'un fichier texte et le
     * sauvegarde dans un fichier.
     *
     * @param filePath       le chemin du fichier texte
     * @param outputFilePath le chemin du fichier de sortie pour l'arbre
     *                       de Huffman
     * @throws Exception si une erreur survient lors de la lecture ou de
     *                   l'écriture des fichiers
     */
    public void créerArbreHuffman(String filePath, String outputFilePath)
            throws Exception {
        String content = GestionFichierTexte.lireFichier(filePath);
        List<Occurrence> occurrences = Occurrence.compter(content);
        Noeud racine = Noeud.constructionArbreHuffman(occurrences);
        GestionArbreHuffman.sauvegardeArbreHuffman(racine, outputFilePath);
    }

    /**
     * Compresse un fichier texte en utilisant un dictionnaire Huffman et
     * sauvegarde le résultat en format binaire.
     *
     * @param inputFilePath  le chemin du fichier texte à compresser
     * @param dictionaryPath le chemin du fichier contenant le
     *                       dictionnaire Huffman
     * @param outputFilePath le chemin du fichier de sortie pour le
     *                       fichier compressé
     * @throws Exception si une erreur survient lors de la lecture ou de
     *                   l'écriture des fichiers
     */
    public void compresserFichier(String inputFilePath, String dictionaryPath,
            String outputFilePath) throws Exception {
        String content = GestionFichierTexte.lireFichier(inputFilePath);
        String compressedText = Compression.compresserTexte(content,
                GestionArbreHuffman.restaurerArbreHuffman(dictionaryPath));
        GestionFichierBinaire.ecriture(compressedText, outputFilePath);
    }

    /**
     * Décompresse un fichier binaire en utilisant un dictionnaire Huffman
     * et sauvegarde le résultat en format texte.
     *
     * @param inputFilePath  le chemin du fichier binaire à décompresser
     * @param dictionaryPath le chemin du fichier contenant le
     *                       dictionnaire Huffman
     * @param outputFilePath le chemin du fichier de sortie pour le
     *                       fichier décompressé
     * @throws Exception si une erreur survient lors de la lecture ou de
     *                   l'écriture des fichiers
     */
    public void decompresserFichier(String inputFilePath, String dictionaryPath,
            String outputFilePath) throws Exception {
        String decompressedText = Decompression.decompresser(inputFilePath,
                GestionArbreHuffman.restaurerArbreHuffman(dictionaryPath));
        GestionFichierTexte.ecrireFichier(decompressedText, outputFilePath);
    }

    /**
     * Calcule le taux de compression entre un fichier original et un
     * fichier compressé.
     *
     * @param originalFilePath   le chemin du fichier original
     * @param compressedFilePath le chemin du fichier compressé
     * @return le taux de compression
     */
    public double calculerTauxCompression(String originalFilePath,
            String compressedFilePath) {
        File originalFile = new File(originalFilePath);
        File compressedFile = new File(compressedFilePath);
        double originalSize = originalFile.length();
        double compressedSize = compressedFile.length();
        return originalSize / compressedSize;
    }
}
