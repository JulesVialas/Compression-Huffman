package gestion;
import java.io.File;

public class TailleFichiers {

    public static double tauxCompression(String fichier, String fichierCompresse) {
	File tailleFichier = new File(fichier);
	File tailleFichierCompresse = new File(fichierCompresse);
	double tailleFichierEnKiloBytes = tailleFichier.length() / 1024.0;
	double tailleFichierCompresseEnKiloBytes = tailleFichierCompresse.length() / 1024.0;
	System.out.println(tailleFichierEnKiloBytes + " ko");
	System.out.println(tailleFichierCompresseEnKiloBytes + " ko");

	return  tailleFichierEnKiloBytes / tailleFichierCompresseEnKiloBytes;
    }
}
