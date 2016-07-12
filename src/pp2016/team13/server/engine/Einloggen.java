package pp2016.team13.server.engine;

import pp2016.team13.client.gui.Verschluesselung;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Klasse, die den einlogprozess auf serverseite verwaltet
 * 
 * @author <Fiehn, Marius, 6024602>
 */
public class Einloggen {
	static String[] passworte;
	static String[] benutzer;
	static String pwt;

	/**
	 * Login Methode liest die in der externen Datei gespeicherten Nutzerdaten
	 * ein und vergleicht dann die Nutzerdaten mit den eingespeicherten Werten
	 * 
	 * @author <Fiehn, Marius, 6024602>
	 * 
	 * @param benutzername:
	 *            String mit dem Benutzernamen als Inhalt
	 * @param passwort:
	 *            String mti dem Passwort als Inhalt
	 * @return boolean, je nachdem, ob der Einlogvorgang erfolgreich war
	 * @throws Exception,
	 *             falls die Datei nicht eingelesen werden kann
	 */
	public static boolean logIn(String benutzername, String passwort) throws Exception {
		Verschluesselung Ver = new Verschluesselung();
		boolean fertig = false;
		try {
			FileInputStream fileinptstrm = new FileInputStream("./src/pp2016/team13/server/engine/NutzerDaten.txt");
			InputStreamReader inptstrmreader = new InputStreamReader(fileinptstrm);
			BufferedReader buffreader = new BufferedReader(inptstrmreader);
			String rLine = buffreader.readLine();
			int i = 0;
			String[] Nutzerdaten = new String[10];
			passworte = new String[10];
			benutzer = new String[10];
			while (rLine != null) { // Passwort und Benutzername wird getrennt
				Nutzerdaten[i] = rLine;
				String[] a = Nutzerdaten[i].split(" ");
				passworte[i] = a[1];
				pwt = passworte[i];
				if (Ver.verschluesseln(passwort, benutzername).equals(pwt)) {
					System.out.println("Willkommen zurueck");
					fertig = true;
					break;
				} else {
					Nutzerdaten[i] = rLine;
					rLine = buffreader.readLine();
					i++;
					fertig = false;
				}
			}
			if (!fertig) {
				System.out.println("Falsche eingabe");
			}
			buffreader.close();
			return fertig;
		} catch (IOException e) {
			System.out.println("Fehler " + e);
			return fertig;
		}
	}

	/**
	 * Registrierungs Methode auf Server Seite
	 * 
	 * @author <Fiehn, Marius, 6024602>
	 * 
	 * @param benutzername:
	 *            String mit dem Benutzernamen als Inhalt
	 * @param passwort:
	 *            String mti dem Passwort als Inhalt
	 * 
	 * @return je nachdem, ob es erfolgreich war, wird ein Boolean zurueck
	 *         gegeben
	 */
	public static boolean regIn(String Benutzername, String Passwort) {
		boolean fertig = false;
		try {
			FileInputStream fileinptstrm = new FileInputStream("./src/pp2016/team13/server/engine/NutzerDaten.txt");
			InputStreamReader inptstrmreader = new InputStreamReader(fileinptstrm);
			BufferedReader buffreader = new BufferedReader(inptstrmreader);
			String rLine = buffreader.readLine();
			int i = 0;
			String[] Nutzerdaten = new String[10];

			while (rLine != null) {
				if (rLine.equals(Benutzername + " " + Passwort)) {
					System.out.println("Benutzername ist schon Vergeben");
					fertig = true;
					Nutzerdaten[i] = rLine;
					fertig = true;
					break;
				} else {
				}
				Nutzerdaten[i] = rLine;
				rLine = buffreader.readLine();
				i++;
				fertig = false;
			}
			if (!fertig) {
				buffreader.close();
				System.out.println("Hallo neuer Spieler");
				BufferedWriter buffwriter = new BufferedWriter(
						new FileWriter("./src/pp2016/team13/server/engine/NutzerDaten.txt", false));
				for (int j = 0; j < i; j++) {
					buffwriter.write(Nutzerdaten[j]);
					buffwriter.write(System.getProperty("line.separator"));
				}
				buffwriter.write(Benutzername + " " + Passwort);
				buffwriter.flush();
				buffwriter.close();
				fertig = true;
			}
			return true;
		} catch (IOException e) {
			System.out.println("Fehler " + e);
			return fertig;
		}
	}
}