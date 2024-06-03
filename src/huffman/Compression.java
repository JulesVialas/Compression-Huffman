/*
 * Compression.java                                            16 mai 2024
 * IUT de Rodez, pas de copyright.
 */
package huffman;

/**
 * Cette classe offre des outils pour compresser du texte en utilisant
 * l'algorithme de Huffman. L'algorithme de Huffman utilise la
 * fréquence des lettres pour créer un code binaire compact.
 * <p>
 * Pour compresser, on utilise un arbre de Huffman construit à partir
 * des fréquences des lettres dans le texte d'origine. Ce code Huffman
 * est ensuite utilisé pour convertir chaque lettre en une séquence
 * binaire.
 * </p>
 *
 * @author Jules Vialas, Robin Montes, Aurélien Valat, Landry
 *         Loubière, Amjed Sehil
 */
public class Compression {

    /**
     * Compresse un texte en utilisant un arbre de Huffman.
     *
     * @param texte        Le texte à compresser.
     * @param dictionnaire L'arbre de Huffman utilisé pour la compression.
     * @return Le texte compressé sous forme de chaîne binaire.
     * @throws IllegalArgumentException Si le texte est vide ou null.
     */
    public static String compresserTexte(String texte,
            Object[][] dictionnaire) {
        if (texte == null || texte.isEmpty()) {
            throw new IllegalArgumentException("Le texte est vide ou null");
        }
        StringBuilder codeBinaire = new StringBuilder();
        for (int i = 0; i < texte.length(); i++) {
            char caractere = texte.charAt(i);
            String codeHuffman = trouverCodeHuffman(caractere, dictionnaire);
            codeBinaire.append(codeHuffman);
        }
        return codeBinaire.toString();
    }

    /**
     * Trouve le code Huffman d'un caractère dans un dictionnaire.
     *
     * @param caractere    Le caractère dont on veut le code Huffman.
     * @param dictionnaire Le dictionnaire contenant les codes Huffman.
     * @return Le code Huffman du caractère, ou une chaîne vide si le
     *         caractère n'est pas trouvé.
     */
    public static String trouverCodeHuffman(char caractere,
            Object[][] dictionnaire) {
        for (Object[] element : dictionnaire) {
            if (element[0] != null && caractere == (char) element[0]) {
                return element[1].toString();
            }
        }
        return "";
    }

}
