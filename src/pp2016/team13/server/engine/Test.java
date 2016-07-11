package pp2016.team13.server.engine;

import java.io.IOException;

	public class Test {
		public static  int anzahlLevel = 5;
		public static  int size = 16;
		public  int [][] levelInhalt = new int[size][size];
		public  int [][][] levelSpeicherort = new int [anzahlLevel][size][size];

		public static void main(String[] args) throws IOException {
			
			
			Chat.setAnzahlNachrichten(0);
			int charakterLebenspunkte = 10;
			int charakterSchaden = 1;
			int charakterTraenke = 0;
			int monsterLebenspunkte = 5;
			int monsterSchaden = 1;
				
			Levelverwaltung spiel = new Levelverwaltung(0, charakterLebenspunkte, charakterSchaden, charakterTraenke, monsterLebenspunkte, monsterSchaden, size, anzahlLevel);
			//�bertragen aller Level Beim Start -- Zum Testen ausgeben
			for (int k = 0; k<spiel.anzahlLevel ; k++){
				System.out.println("Level: " + (k+1) );
				for (int i = 0; i<Levelverwaltung.groesse; i++){
					for (int j = 0; j< Levelverwaltung.groesse ; j++){
						System.out.print(Levelverwaltung.levelSpeicherort[k][i][j] + " ");
					}
					System.out.println();
				}
			}

			//			System.out.println("Spielerposition in Level 1 "+ (spiel.spielerListe[0].getPosX() + 1) + " "+ (spiel.spielerListe[0].getPosY() + 1));

			//Test: Ver�nderung  einzelner Mapelemente
			spiel.setLevelInhalt(0, 0, 0, 4, spiel);
			Levelverwaltung.levelSpeicherort[0][0][0] = Levelverwaltung.levelInhalt[0][0];
			System.out.println("Veraenderter Ort " + Levelverwaltung.levelSpeicherort[0][0][0]);
			
			//Test, ob ein Level gewechselt werden kann
			Levelverwaltung.levelID = Levelverwaltung.levelID++;
			System.out.println("Veraendertes Level: ");
			for (int i = 0; i<Levelverwaltung.groesse ; i++){
				for (int j = 0; j<Levelverwaltung.groesse ; j++){
					Levelverwaltung.levelSpeicherort[Levelverwaltung.levelID][j][i] = Levelverwaltung.levelInhalt[j][i];
				}
			}
			//Ausgabe des zweiten Levels mit der ID 1
			for (int i = 0; i < Levelverwaltung.levelInhalt.length-1 ; i++){
				for (int j = 0; j < Levelverwaltung.levelInhalt.length-1; j++){
					System.out.print(Levelverwaltung.levelSpeicherort[1][i][j] + " ");
				}
				System.out.println();
			}
			
			
			//Nachrichten Behandlung
			
			//Login-Message
			//Externe Textdatei f�r Benutzername und Passwort
			//PlayerID wird beim Aufruf vergeben. Hier nicht mehr sinnvoll
			
			
			
//			Einloggen.LogIn("Hallo", "Welt");
//			Einloggen.LogIn("Tschuess", "Welt");
			
		}
	}
