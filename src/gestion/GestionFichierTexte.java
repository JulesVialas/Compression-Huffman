/*
 * GestionFichierTexte.java                                        22 avril 2024
 * IUT de Rodez, pas de copyright.
 */

package gestion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * La classe GestionFichierTexte fournit des méthodes pour lire et
 * écrire des fichiers texte.
 */
public class GestionFichierTexte {

    /**
     * Lit le contenu d'un fichier texte et retourne ce contenu sous forme
     * de chaîne de caractères.
     *
     * @param cheminFichier Le chemin du fichier à lire.
     * @return Le contenu du fichier sous forme de chaîne de caractères.
     * @throws IOException Si une erreur d'entrée/sortie se produit.
     */
    public static String lireFichier(String cheminFichier) throws IOException {
        StringBuilder contenuBuilder = new StringBuilder();
        try (BufferedReader lecteur = new BufferedReader(
                new FileReader(cheminFichier))) {
            String ligne;
            boolean premiereLigne = true;
            while ((ligne = lecteur.readLine()) != null) {
                if (!premiereLigne) {
                    contenuBuilder.append('\n');
                }
                contenuBuilder.append(ligne);
                premiereLigne = false;
            }
        }
        return contenuBuilder.toString();
    }

    /**
     * Écrit une chaîne de caractères dans un fichier texte.
     *
     * @param contenu      Le contenu à écrire dans le fichier.
     * @param nomDeFichier Le nom du fichier dans lequel écrire le
     *                     contenu.
     */
    public static void ecrireFichier(String contenu, String nomDeFichier) {
        try (BufferedWriter contenuFichier = new BufferedWriter(
                new FileWriter(nomDeFichier))) {
            contenuFichier.write(contenu);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
