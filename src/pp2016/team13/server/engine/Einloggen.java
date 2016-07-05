package pp2016.team13.server.engine;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;

public class Einloggen {

		private static String [] nutzer = new String [10];
	
		public static boolean einloggenErfolgreich(String benutzername, String passwort){
			boolean erfolgreich = false;
			for (int i = 0; i< nutzer.length-1 ; i++){
				if (nutzer[i]!= null && nutzer[i].equals(benutzername+passwort)){
					System.out.println("Willkommen zurück");
					return true;
				}else{
					if (!(i<nutzer.length-2)){
						nutzer[0]=benutzername+passwort;
						System.out.println("Hallo Neuer Nutzer");
						break;
					}
				}
			}
			return erfolgreich;
		}
	
		public static boolean LogIn(String benutzername, String passwort){
			try{
				boolean fertig = false;
				FileInputStream fileinptstrm = new FileInputStream("./src/Spielweltverwaltung/NutzerDaten.txt");
				InputStreamReader inptstrmreader = new InputStreamReader(fileinptstrm);
				BufferedReader buffreader = new BufferedReader(inptstrmreader);
				String rLine = buffreader.readLine();
				int i = 0;
				String[] Nutzerdaten = new String [10];
				while(rLine != null)
				{
					if (rLine.equals(benutzername+passwort)){
						System.out.println("Willkommen zurück");
						fertig = true;
						Nutzerdaten [i] = rLine;
						break;
					}else{
						Nutzerdaten [i] = rLine;
						rLine = buffreader.readLine();
						i++;
					}
				}
				if (!fertig)
				{
					buffreader.close();  
					System.out.println("Hallo neuer Spieler");
					BufferedWriter buffwriter = new BufferedWriter(new FileWriter("./src/Spielweltverwaltung/Nutzerdaten.txt", false));
					for(int j = 0; j<i;j++)
					{
						buffwriter.write(Nutzerdaten[j]);
						buffwriter.write(System.getProperty("line.separator"));
					}
					buffwriter.write(benutzername+passwort);
					buffwriter.flush();
		        	buffwriter.close();
		        	fertig = true;
				}
	        	return true;
			}catch(IOException e){
			System.out.println("Fehler " + e);
			return false;
		}
	}
}
