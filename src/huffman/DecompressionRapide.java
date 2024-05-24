/*
 * DecompressionRapide.java                                            02 mai 2024
 * IUT de Rodez, pas de copyright.
 */
package huffman;

import java.io.IOException;

import gestion.GestionFichierBinaire;
/**
 * DecompressionRapide propose plusieurs maniere de faire la décompression
 */
public class DecompressionRapide {
    /**
     * Décompresse des fichier à partir de la racine
     *
     * @param FichierCompresse fichier à décompresser
     * @param la racine donnée par l'arbre Huffman
     * 
     * @return le texte décompressé
     */
    public static String decompresser(String nomFichierCompresse, Noeud racine) {
        String texteDecompresse = "";
        String texteBinaire = null;
        try {
            texteBinaire = GestionFichierBinaire.lecture(nomFichierCompresse);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        texteDecompresse = decompresserAvecArbre(texteBinaire, racine);
        return texteDecompresse;
    }
    
    /**
     * Décompresse des fichier binaire à partir de la racine
     *
     * @param TexteBinaire texte Binaire à décompresser
     * @param la racine donnée par l'arbre Huffman
     * 
     * @return le texte décompressé
     */
    private static String decompresserAvecArbre(String texteBinaire, Noeud racine) {
        StringBuilder texteDecompresse = new StringBuilder();
        Noeud noeudCourant = racine;
        
        for (int i = 0; i < texteBinaire.length(); i++) {
            char bit = texteBinaire.charAt(i);
            noeudCourant = (bit == '0') ? noeudCourant.getGauche() : noeudCourant.getDroite();
            
            if (noeudCourant.getGauche() == null && noeudCourant.getDroite() == null) {
                texteDecompresse.append(noeudCourant.getCaractere());
                noeudCourant = racine; 
            }
        }
        
        return texteDecompresse.toString();
    }
}
