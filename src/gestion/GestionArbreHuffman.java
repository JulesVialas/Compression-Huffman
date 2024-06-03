/*
 * GestonArbreHuffman.java                               	12 mai 2024
 * IUT de Rodez, pas de copyright.
 */
package gestion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import huffman.Noeud;

/**
 * L'arbre Huffman doit être écrit dans un fichier pour être
 * sauvegardé sous la forme d'un dictionnaire. Pour ce faire il faut
 * en premier lieu enregistrer l'arbre de huffman dans un fichier txt
 * sous la forme: codeHuffman = 00 ; encode = 01100101 ; symbole = e
 * Voici à quoi correspondent ces trois parties:
 * <ul>
 * <li>codeHuffman étant le nouveau encodage donné par l'arbre;</li>
 * <li>encode étant l'encodage UTF8 du caractère;</li>
 * <li>symbole étant le caractère du fichier</li>
 * </ul>
 * L'arbre huffman doit aussi mettre en évidence les retours à la
 * ligne et les espaces pour ce faire on remplace les caractères
 * espace par CF et les sauts de lignes par VT. Finalement nous
 * récupérons les données du fichier dictionnaire dans un tableau.
 */
public class GestionArbreHuffman {

    /**
     * Sauvegarde l'arbre Huffman dans un fichier.
     *
     * @param racine     La racine de l'arbre de Huffman.
     * @param nomFichier Le nom du fichier où sauvegarder l'arbre.
     */
    public static void sauvegardeArbreHuffman(Noeud racine, String nomFichier) {
        try (BufferedWriter ecrivainFichier = new BufferedWriter(
                new FileWriter(nomFichier))) {
            parcourirArbreHuffman(racine, ecrivainFichier, "");
        } catch (IOException erreurEntreeSortie) {
            System.out.println(erreurEntreeSortie.getMessage());
        }
    }

    /**
     * Parcours l'arbre de Huffman et écrit chaque noeud dans le fichier.
     *
     * @param racine          noeud actuel de l'arbre.
     * @param ecrivainFichier ecrivain du contenu du dictionnaire.
     * @param codeHuffman     code Huffman pour le noeud actuel.
     * @throws IOException Si une erreur d'entrée ou de sortie survient.
     */
    private static void parcourirArbreHuffman(Noeud racine,
            BufferedWriter ecrivainFichier, String codeHuffman)
            throws IOException {
        if (racine == null) {
            return;
        }
        if (racine.getGauche() == null && racine.getDroite() == null) {
            if (racine.getCaractere() == '\n') {
                ecrivainFichier.write("codeHuffman = " + codeHuffman
                        + " ; encode = " + Integer.toBinaryString('\n')
                        + " ; symbole = VT\n"); // VT = saut de ligne
            } else if (racine.getCaractere() == ' ') {
                ecrivainFichier.write("codeHuffman = " + codeHuffman
                        + " ; encode = " + Integer.toBinaryString(' ')
                        + " ; symbole = CF\n"); // CF = espace
            } else {
                ecrivainFichier.write("codeHuffman = " + codeHuffman
                        + " ; encode = "
                        + Integer.toBinaryString(racine.getCaractere())
                        + " ; symbole = " + racine.getCaractere() + "\n");
            }
        }
        parcourirArbreHuffman(racine.getGauche(), ecrivainFichier,
                codeHuffman + "0");
        parcourirArbreHuffman(racine.getDroite(), ecrivainFichier,
                codeHuffman + "1");

    }

    /**
     * Restaure l'arbre de Huffman à partir d'un fichier.
     *
     * @param nomFichier Le nom du fichier contenant l'arbre de Huffman.
     * @return Un tableau de dictionnaire Huffman.
     * @throws IOException Si une erreur d'entrée ou de sortie survient.
     */
    public static Object[][] restaurerArbreHuffman(String nomFichier)
            throws IOException {
        List<Object[]> dictionnaire = new ArrayList<>();

        try (BufferedReader lecteur = new BufferedReader(
                new FileReader(nomFichier))) {
            String ligne;
            while ((ligne = lecteur.readLine()) != null) {

                String[] parties = ligne.split(";");

                String[] partieCodeHuffman = parties[0].split("=");
                String[] partieCaractere = parties[2].split("=");

                String codeHuffman = partieCodeHuffman[1].trim();
                String caractere = partieCaractere[1].trim();
                char symbole = decoderSymbole(caractere);

                dictionnaire.add(new Object[] { symbole, codeHuffman });
            }
        }

        return dictionnaire.toArray(new Object[0][0]);
    }

    /**
     * Décode le symbole en fonction du caractère représenté dans le
     * fichier.
     *
     * @param caractere Le caractère sous forme de chaîne.
     * @return Le caractère décodé.
     */
    private static char decoderSymbole(String caractere) {
        switch (caractere) {
        case "VT":
            return '\n';
        case "CF":
            return ' ';
        default:
            return caractere.length() == 1 ? caractere.charAt(0) : '\0';
        }
    }
}
