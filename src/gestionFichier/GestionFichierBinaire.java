package gestionFichier;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class GestionFichierBinaire {

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

    private static String byteVersStringBinaire(int octet) {
	return String.format("%8s", Integer.toBinaryString(octet & 0xFF))
		.replace(' ', '0');
    }

    public static void ecriture(String stringBinaire, String nomFichier)
	    throws IOException {
	try (DataOutputStream output = new DataOutputStream(
		new FileOutputStream(nomFichier))) {
	    output.writeInt(stringBinaire.length());
	    byte[] bytes = stringBinaireVersBytes(stringBinaire);
	    output.write(bytes);
	}
    }

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
	    byteValue <<= (8 - (end - start));
	    bytes[i] = (byte) byteValue;
	}
	return bytes;
    }
}
