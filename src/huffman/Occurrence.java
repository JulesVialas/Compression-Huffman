/*
 * Occurrence.java                                            22 avril 2024
 * IUT de Rodez, pas de copyright.
 */
package huffman;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Cette classe représente une occurrence d'un caractère dans un
 * contexte donné. Elle encapsule les informations relatives à ce
 * caractère, à savoir le caractère lui-même, le nombre d'occurrences
 * de ce caractère et sa fréquence dans le contexte.
 */
public class Occurrence {
    private final char caractere;
    private final int occurrences;
    private final double frequence;

    /**
     * Initialise une occurrence avec les paramètres spécifiés.
     *
     * @param caractere   Le caractère concerné par cette occurrence.
     * @param occurrences Le nombre d'occurrences du caractère dans le
     *                    contexte.
     * @param frequence   La fréquence du caractère dans le contexte.
     */
    public Occurrence(char caractere, int occurrences, double frequence) {
        this.caractere = caractere;
        this.occurrences = occurrences;
        this.frequence = frequence;
    }

    /**
     * Récupère le caractère associé à cette occurrence.
     *
     * @return Le caractère associé à cette occurrence.
     */
    public char getCaractere() {
        return caractere;
    }

    /**
     * Récupère le nombre d'occurrences du caractère dans le contexte.
     *
     * @return Le nombre d'occurrences du caractère dans le contexte.
     */
    public int getOccurrences() {
        return occurrences;
    }

    /**
     * Récupère la fréquence du caractère dans le contexte.
     *
     * @return La fréquence du caractère dans le contexte.
     */
    public double getFrequence() {
        return frequence;
    }

    private static final int MAX_UTF8 = 1114112;

    /**
     * Compte les occurrences de chaque caractère dans un texte donné.
     *
     * @param texte le texte dont on veut compter les occurrences des
     *              caractères
     * @return une liste d'objets Occurrence contenant les caractères et
     *         leurs occurrences triées par ordre croissant des fréquences
     */
    public static List<Occurrence> compter(String texte) {
        verifierTexteValide(texte);
        estUtf8(texte);
        int[] tableauOccurrences = new int[MAX_UTF8 + 1];
        List<Occurrence> resultat = remplirListeOccurrences(texte,
                tableauOccurrences);
        return trierParFrequence(resultat);
    }

    /**
     * Vérifie si le texte est valide. Le texte est invalide s'il est null
     * ou vide.
     *
     * @param texte le texte à vérifier
     * @throws IllegalArgumentException si le texte est null ou vide
     */
    private static void verifierTexteValide(String texte) {
        if (texte == null || texte.isEmpty()) {
            throw new IllegalArgumentException("Le texte est vide ou null");
        }
    }

    /**
     * Vérifie si le texte conforme à la norme utf-8.
     *
     * @param texte
     * @return true si le texte ne contient pas de caractères non UTF-8
     *         false si le texte comporte des caractères non UTF-8
     */

    public static boolean estUtf8(String texte) {
        CharsetDecoder verificateur = StandardCharsets.UTF_8.newDecoder();

        byte[] octets = texte.getBytes(StandardCharsets.UTF_8);
        ByteBuffer byteBuffer = ByteBuffer.wrap(octets);

        try {
            CharBuffer charBuffer = verificateur.decode(byteBuffer);
            return true;
        } catch (Exception erreur) {
            return false;
        }
    }

    /**
     * Remplit une liste avec les caractères et leurs occurrences.
     *
     * @param texte       le texte dont on veut compter les occurrences
     * @param occurrences le tableau des occurrences des caractères
     * @return une liste d'objets Occurrence contenant les caractères et
     *         leurs occurrences
     */
    private static List<Occurrence> remplirListeOccurrences(String texte,
            int[] occurrences) {
        int longueurTexte = texte.length();
        for (int rang = 0; rang < texte.length(); rang++) {
            char caractere = texte.charAt(rang);
            occurrences[caractere]++;
        }

        List<Occurrence> liste = new ArrayList<>();
        for (int rang = 0; rang <= MAX_UTF8; rang++) {
            if (occurrences[rang] > 0) {
                char caractere = (char) rang;
                double frequence = (double) occurrences[rang] / longueurTexte;
                liste.add(new Occurrence(caractere, occurrences[rang],
                        frequence));
            }
        }
        return liste;
    }

    /**
     * Trie la liste d'occurrences en ordre croissant de fréquence des
     * caractères.
     *
     * @param liste la liste d'objets Occurrence à trier
     * @return la liste triée d'objets Occurrence
     */
    private static List<Occurrence> trierParFrequence(List<Occurrence> liste) {
        for (int rang = 1; rang < liste.size(); rang++) {
            Occurrence element = liste.get(rang);
            int secondRang = rang - 1;
            while (secondRang >= 0 && liste.get(secondRang)
                    .getOccurrences() > element.getOccurrences()) {
                liste.set(secondRang + 1, liste.get(secondRang));
                secondRang = secondRang - 1;
            }
            liste.set(secondRang + 1, element);
        }
        return liste;
    }
}
