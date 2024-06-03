/*
 * Decompression.java                                            22 mai 2024
 * IUT de Rodez, pas de copyright.
 */
package huffman;

import java.io.IOException;

import gestion.GestionFichierBinaire;

/**
 * Permet de décompresser des fichiers encodés en utilisant
 * l'algorithme de Huffman.
 * <p>
 * Les fichiers compressés sont décodés à l'aide d'un dictionnaire de
 * caractères et de leurs encodages Huffman correspondants.
 * </p>
 * <p>
 * Cette classe offre des méthodes pour lire le contenu d'un fichier
 * compressé, décompresser les données en utilisant le dictionnaire,
 * puis retourner le texte décompressé.
 * </p>
 *
 * @author LOUBIERE Landry, VIALAS Jules, SEHIL Amjed
 */
public class Decompression {
    /**
     * Décompresse des fichier à partir de l'encodage Huffman
     *
     * @param FichierCompresse fichier à décompresser
     * @param dictionnaire     les caractères avec leurs encodage Huffman
     * @return le texte décompressé
     */
    public static String decompresser(String FichierCompresse,
            Object[][] dictionnaire) {
        String texteDecompresse = "";
        String texteBinaire = null;
        String temp = "";
        try {
            texteBinaire = GestionFichierBinaire.lecture(FichierCompresse);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int rang = 0; rang < texteBinaire.length(); rang++) {
            temp += texteBinaire.charAt(rang);
            for (Object[] element : dictionnaire) {
                if (temp.equals(element[1])) {
                    texteDecompresse += element[0];
                    temp = "";
                }
            }
        }
        return texteDecompresse;
    }
}