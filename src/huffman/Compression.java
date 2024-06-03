/*
 * Compression.java                                            16 mai 2024
 * IUT de Rodez, pas de copyright.
 */
package huffman;

/**
 * Offre des outils pour compresser du texte en utilisant l'algorithme
 * de Huffman. Cet algorithme intelligent utilise la fréquence des
 * lettres pour créer un code binaire compact.
 * <p>
 * Pour compresser, on utilise un arbre de Huffman construit à partir
 * des fréquences des lettres dans le texte d'origine. Ce code Huffman
 * est ensuite utilisé pour convertir chaque lettre, en une séquence
 * binaire.
 * </p>
 *
 * @author LOUBIERE Landry, MONTES Robin, SEHIL Amjed, VALAT Aurélien
 *         et VIALAS Jules
 */
public class Compression {

    /**
     * Compresse un fichier grâce à son code Huffman
     *
     * @param texte        chaîne de caractère à compresser
     * @param dictionnaire arbre de Huffman du fichier donnée
     * @return texte compressé
     */
    public static String compresserTexte(String texte,
            Object[][] dictionnaire) {
        if (texte == null || texte.isEmpty()) {
            throw new IllegalArgumentException("Le texte est vide ou null");
        }
        StringBuilder codeBinaire = new StringBuilder();
        for (int rang = 0; rang < texte.length(); rang++) {
            char caractere = texte.charAt(rang);
            String codeHuffman = trouverCodeHuffman(caractere, dictionnaire);
            codeBinaire.append(codeHuffman);
        }
        return codeBinaire.toString();
    }

    /**
     * Refonte de l'arbre Huffman pour effectuer la compression
     *
     * @param caractere    caractère dont on veut le code huffman
     * @param dictionnaire
     * @return Si le caractère est trouvé dans le dictionnaire le code
     *         Huffman. Sinon rien n'est retourné.
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