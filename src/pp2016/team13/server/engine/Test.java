package pp2016.team13.server.engine;

import java.io.IOException;

import pp2016.team13.server.map.Labyrinth;

	public class Test {
		public static int anzahlLevel = 5;
		public static int size = 16;
		public static int [][] levelInhalt = new int[size][size];
		public static int [][][] levelSpeicherort = new int [anzahlLevel][size][size];

		public static void main(String[] args) throws IOException {
			
			
			Chat chat = new Chat();
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
				for (int i = 0; i<spiel.groesse; i++){
					for (int j = 0; j< spiel.groesse ; j++){
						System.out.print(spiel.levelSpeicherort[k][i][j] + " ");
					}
					System.out.println();
				}
			}

//			System.out.println("Spielerposition in Level 1 "+ (spiel.spielerListe[0].getPosX() + 1) + " "+ (spiel.spielerListe[0].getPosY() + 1));

			//Test: Ver�nderung  einzelner Mapelemente
			spiel.setLevelInhalt(0, 0, 0, 4, spiel);
			spiel.levelSpeicherort[0][0][0] = spiel.levelInhalt[0][0];
			System.out.println("Veraenderter Ort " + spiel.levelSpeicherort[0][0][0]);
			
			//Test, ob ein Level gewechselt werden kann
			spiel.levelID = spiel.levelID++;
			System.out.println("Veraendertes Level: ");
			for (int i = 0; i<spiel.groesse ; i++){
				for (int j = 0; j<spiel.groesse ; j++){
					spiel.levelSpeicherort[spiel.levelID][j][i] = spiel.levelInhalt[j][i];
				}
			}
			//Ausgabe des zweiten Levels mit der ID 1
			for (int i = 0; i < spiel.levelInhalt.length-1 ; i++){
				for (int j = 0; j < spiel.levelInhalt.length-1; j++){
					System.out.print(spiel.levelSpeicherort[1][i][j] + " ");
				}
				System.out.println();
			}
			
			
			//Nachrichten Behandlung
			
			//Login-Message
			//Externe Textdatei f�r Benutzername und Passwort
			//PlayerID wird beim Aufruf vergeben. Hier nicht mehr sinnvoll
			
			//Spielerbewegung
			Nachricht Spielerbewegung = new Nachricht (1 , 0, 1, 1);
			spiel.verarbeiteNachricht(Spielerbewegung, spiel);
			
			//Trankaufnahme
			Nachricht Trankaufnahme = new Nachricht (2, 0, 0, "aufnehmen");
			spiel.verarbeiteNachricht(Trankaufnahme, spiel);

			//Spielerbewegung
			Spielerbewegung = new Nachricht (1 , 1, 0);
			spiel.verarbeiteNachricht(Spielerbewegung, spiel);

			//Trankaufnahme
			Trankaufnahme = new Nachricht (2);
			spiel.verarbeiteNachricht(Trankaufnahme, spiel);

			//Level geschafft
			Nachricht levelGeschafft = new Nachricht (3, 0);
			spiel.verarbeiteNachricht(levelGeschafft, spiel);

			
			//Schluesselaufnahme
			Nachricht schluesselAufnahme = new Nachricht (4, 0);
			spiel.verarbeiteNachricht(schluesselAufnahme, spiel);
			
			//Chat Nachricht
			Nachricht chatNachricht = new Nachricht (5, "HI");
			spiel.verarbeiteNachricht(chatNachricht, spiel);
			for (int i = 0 ; i<Chat.anzahlNachrichten ; i++){
				System.out.println(Chat.chat[i]);
			}
			
			//Spieler�berspringt Level
			Nachricht ueberspringen = new Nachricht (7);
			spiel.verarbeiteNachricht(ueberspringen, spiel);
			
			//Behandlung Nachrichten Kampfsystem
			//Abfrage der Lebenspunkte nach jedem Durchlauf der while Schleife
			//Bei jedem Durchlauf �bermittlung aller Lebenspunkte an Client
			//als Beispiel:
			spiel.spielerListe[0].setLebenspunkte(2);
			System.out.println(spiel.spielerListe[0].getLebenspunkte());
			Nachricht lebenspunkteVeraendert = new Nachricht (6, true , spiel.spielerListe[0].getLebenspunkte());
			
			Einloggen.LogIn("Hallo", "Welt");
			Einloggen.LogIn("Tschuess", "Welt");
			
		}
	}
