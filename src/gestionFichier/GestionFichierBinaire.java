package gestionFichier;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Cette classe gère la lecture et l'écriture de fichiers binaires.
 * Elle fournit des méthodes pour lire le contenu d'un fichier binaire
 * sous forme de chaîne binaire et pour écrire une chaîne binaire dans
 * un fichier binaire.
 */
public class GestionFichierBinaire {

    /**
     * Lit le contenu d'un fichier binaire et le retourne sous forme de
     * chaîne binaire.
     *
     * @param nomFichier Le nom du fichier à lire.
     * @return Le contenu du fichier sous forme de chaîne binaire.
     * @throws IOException En cas d'erreur lors de la lecture du fichier.
     */
    public static String lecture(String nomFichier) throws IOException {
	try (DataInputStream input = new DataInputStream(
		new FileInputStream(nomFichier))) {
	    int longueurBinaire = input.readInt();
	    StringBuilder stringBinaire = new StringBuilder();
	    int byteLu;
	    int bitsLus = 0;
	    while ((byteLu = input.read()) != -1 && bitsLus < longueurBinaire) {
		String byteString = byteVersStringBinaire(byteLu);
		if (bitsLus + 8 > longueurBinaire) {
		    byteString = byteString.substring(0,
			    longueurBinaire - bitsLus);
		}
		stringBinaire.append(byteString);
		bitsLus += 8;
	    }
	    return stringBinaire.toString();
	}
    }

    /**
     * Convertit un octet en une chaîne binaire sur 8 bits.
     *
     * @param octet L'octet à convertir.
     * @return La représentation binaire de l'octet sur 8 bits.
     */
    private static String byteVersStringBinaire(int octet) {
	return String.format("%8s", Integer.toBinaryString(octet & 0xFF))
		.replace(' ', '0');
    }

    /**
     * Écrit une chaîne binaire dans un fichier binaire.
     *
     * @param stringBinaire La chaîne binaire à écrire dans le fichier.
     * @param nomFichier    Le nom du fichier dans lequel écrire.
     * @throws IOException En cas d'erreur lors de l'écriture dans le
     *                     fichier.
     */
    public static void ecriture(String stringBinaire, String nomFichier)
	    throws IOException {
	try (DataOutputStream output = new DataOutputStream(
		new FileOutputStream(nomFichier))) {
	    output.writeInt(stringBinaire.length());
	    byte[] bytes = stringBinaireVersBytes(stringBinaire);
	    output.write(bytes);
	}
    }

    /**
     * Convertit une chaîne binaire en tableau d'octets.
     *
     * @param stringBinaire La chaîne binaire à convertir.
     * @return Un tableau d'octets représentant la chaîne binaire.
     */
    private static byte[] stringBinaireVersBytes(String stringBinaire) {
	int length = stringBinaire.length();
	int byteLength = (length + 7) / 8;
	byte[] bytes = new byte[byteLength];
	for (int i = 0; i < byteLength; i++) {
	    int byteValue = 0;
	    int start = i * 8;
	    int end = Math.min(start + 8, length);
	    for (int j = start; j < end; j++) {
		byteValue <<= 1;
		if (stringBinaire.charAt(j) == '1') {
		    byteValue |= 1;
		}
	    }
	    byteValue <<= (8 - (end - start)); // Alignement des bits
	    bytes[i] = (byte) byteValue;
	}
	return bytes;
    }

    public static void main(String[] args) {
	try {
	    // Exemple d'utilisation
	    String fichierTexte = "coucou.txt";
	    String fichierBinaire = "coucou.bin";

	    // Exemple de chaîne binaire à écrire (représentant "coucou" encodé
	    // avec
	    // Huffman)
	    String stringBinaire = "0111000100010111110010";
	    ecriture(stringBinaire, fichierBinaire);

	    // Lecture du fichier binaire et affichage du résultat
	    String stringBinaireLue = lecture(fichierBinaire);
	    System.out.println("Resultat de la lecture de " + fichierBinaire
		    + " : " + stringBinaireLue);

	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
}
