/*
 * GestonFichierBinaire.java                                         26 mai 2024
 * IUT de Rodez, pas de copyright.
 */
package gestion;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * GestionFichierBinaire contient des méthodes pour lire et écrire 
 * des fichiers binaires contenant des chaînes de bits. 
 */
public class GestionFichierBinaire {

    /**
     * Lit le contenu d'un fichier et le retourne sous chaîne de bits.
     * 
     * @param nomFichier Le nom du fichier à lire.
     * @return La chaîne de bits lue depuis le fichier.
     * @throws IOException Si une erreur d'entrée ou de sortie 
     *		survient.
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
		    byteString 
		    	= byteString.substring(0, longueurBinaire - bitsLus);
		}
		stringBinaire.append(byteString);
		bitsLus += 8;
	    }
	    return stringBinaire.toString();
	}
    }
    
    /**
     * Convertit un octet en sa représentation binaire sous forme
     * de chaîne.
     *
     * @param octet L'octet à convertir.
     * @return La représentation binaire de l'octet sous forme de 
     * 	       chaîne.
     */
    private static String byteVersStringBinaire(int octet) {
	return String.format(
		"%8s", Integer.toBinaryString(octet & 0xFF)).replace(' ', '0');
    }

    /**
     * Écrit une chaîne binaire dans un fichier binaire
     * 
     * @param stringBinaire la chaîne binaire
     * @param nomFichier le fichier binaire dans lequel la chaîne
     * 			 va être écrite
     * @throws IOException Si une erreur d'entrée ou 
     * 			   de sortie survient.
     */
    public static void ecriture(String stringBinaire, String nomFichier) 
	    						throws IOException {
	try (DataOutputStream output 
		= new DataOutputStream(new FileOutputStream(nomFichier))) {
	    output.writeInt(stringBinaire.length());
	    byte[] bytes = stringBinaireVersBytes(stringBinaire);
	    output.write(bytes);
	}
    }

    /**
     * Écrit une chaîne de bits dans un fichier binaire.
     *
     * @param stringBinaire La chaîne de bits à écrire.
     * @param nomFichier    Le nom du fichier où écrire.
     * @throws IOException  Si une erreur d'entrée ou 
     * 			    de sortie survient.
     */
    private static byte[] stringBinaireVersBytes(String stringBinaire) {
	int length = stringBinaire.length();
	int byteLength = (length + 7) / 8;
	byte[] bytes = new byte[byteLength];
	for (int rang = 0; rang < byteLength; rang++) {
	    int byteValue = 0;
	    int start = rang * 8;
	    int end = Math.min(start + 8, length);
	    for (int secondRang = start; secondRang < end; secondRang++) {
		byteValue <<= 1;
		if (stringBinaire.charAt(secondRang) == '1') {
		    byteValue |= 1;
		}
	    }
	    byteValue <<= (8 - (end - start));
	    bytes[rang] = (byte) byteValue;
	}
	return bytes;
    }
}
