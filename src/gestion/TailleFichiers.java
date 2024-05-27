/*
 * TailleFichiers.java                                            [METTRE DATE COHERENTE]
 * IUT de Rodez, pas de copyright.
 */
package gestion;
import java.io.File;

/**
 * TailleFichiers calcul le taux de compression entre le fichier
 * original et le fichier compressé.
 */
public class TailleFichiers {

    /**
     * Calcul le taux de compression entre un fichier original et un
     * compressé
     * 
     * @param fichier Le chemin du fichier original
     * @param fichierCompresse Le chemin du fichier compressé
     * @return Le taux de compression
     */
    public static double tauxCompression(String fichier, 
	    					String fichierCompresse) {
	File tailleFichier = new File(fichier);
	File tailleFichierCompresse = new File(fichierCompresse);
	double tailleFichierEnKiloBytes = tailleFichier.length() / 1024.0;
	double tailleFichierCompresseEnKiloBytes 
				= tailleFichierCompresse.length() / 1024.0;

	return  tailleFichierEnKiloBytes / tailleFichierCompresseEnKiloBytes;
    }
}
