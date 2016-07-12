package pp2016.team13.server.engine;

import java.io.IOException;
import pp2016.team13.client.engine.*;
import pp2016.team13.server.map.Labyrinth;
import pp2016.team13.shared.*;

/**
 * verwaltet das Spielarray auf Serverseite beinhaltet ebenfalls die
 * Nachrichtenverwaltung
 * 
 * @author Marius
 *
 */
public class Levelverwaltung {
	/**
	 * @author Marius
	 * @param levelID:
	 *            Identifikationszahl des Levels
	 * @param spielerListe:
	 *            speichert alle Spieler, inkl. ihrer Eigenschaften; Zugriff
	 *            ueber spielerID
	 * @param gegnerListe:
	 *            speichert alle Gegner, inkl. ihrer Eigenschaften; Zugriff
	 *            ueber gegnerID
	 * @param trankListe:
	 *            speichert alle Traenke; Zugriff ueber TrankID
	 * @param levelInhalt:
	 *            Speichert alle Zellen des Levels
	 * @param anzahlLevel:
	 *            Die Anzahl der Level wird festgelegt
	 * @param groesse:
	 *            Die Groesse der Level wird festgelegt
	 * @param levelSpeicherort:
	 *            Initialisierung des Speicherortes fuer alle Level
	 * @param SchluesselX:
	 *            X-Koordinate des Schluessels
	 * @param SchluesselY:
	 *            Y-Koordinate des Schluessels
	 * @param tuerX:
	 *            X-Koordinate der Tuer
	 * @param tuerY:
	 *            Y-Koordinate der Tuer
	 */
	// Level ID
	public static int levelID;
	// speichert alle Spieler, inkl. ihrer Eigenschaften; Zugriff ueber
	// spielerID
	Spieler[] spielerListe;
	// speichert alle Gegner, inkl. ihrer Eigenschaften; Zugriff ueber gegnerID
	Monster[] gegnerListe;
	// speichert alle Traenke; Zugriff ueber TrankID
	Heiltrank[] trankListe;
	// Speichert alle Zellen des Levels
	// Level Inhalt : 0=Wand, 1 = Boden, 2 = Charakter, 3 = Monster, 4 = Trank,
	// 5=Schluessel, 6 = Tuer
	public static int[][] levelInhalt;
	// Die Anzahl der Level wird festgelegt
	public int anzahlLevel;
	// Die Groesse der Level wird festgelegt
	public static int groesse;
	// Initialisierung des Speicherortes fuer alle Level
	public static int[][][] levelSpeicherort;
	// Koordinaten des Schluessels
	static int SchluesselX;
	static int SchluesselY;
	// Koordinaten der Tuer
	static int tuerX;
	static int tuerY;
	static Chat chat;

	/**
	 * @author Marius
	 * 
	 * @param levelID:
	 *            Identifikationszahl des Levels
	 * @param charakterLebenspunkte:
	 *            setzt die Anzahl der Lebenspunkte, die der Charakter am Anfang
	 *            hat
	 * @param charakterSchaden:
	 *            setzt den Schaden, den der Spieler pro Angriff verursacht
	 * @param monsterLebenspunkte:setzt
	 *            die Standard Lebenspunkte der Monster
	 * @param monsterSchaden:
	 *            setzt den Standard Schaden der Monster
	 * @param groesse:
	 *            Legt die Groesse des Spielfelds fest
	 * @param anzLevel:
	 *            Legt die Anzahl der Level fest
	 * 
	 *            Setzt einen Konstruktor, der das Level auf der Server Seite
	 *            verwaltet
	 */
	// Konstruktor Levelverwaltung ; Spielwelt
	public Levelverwaltung(int levelID, int charakterLebenspunkte, int charakterSchaden, int charakterTraenke,
			int monsterLebenspunkte, int monsterSchaden, int groesse, int anzLevel) throws IOException {
		Levelverwaltung.levelID = levelID;
		chat = new Chat();
		anzahlLevel = anzLevel;
		Levelverwaltung.groesse = groesse;
		levelSpeicherort = new int[anzahlLevel][groesse][groesse];
		levelInhalt = new int[groesse][groesse];
		// Level anlegen
		int levelZaehler = 1;
		// Vom Levelgenerator ankommendes zweidimensionales Integer Array
		while (levelZaehler < anzahlLevel + 1) {
			Labyrinth map = new Labyrinth(levelZaehler);
			for (int i = 0; i < groesse - 1; i++) {
				for (int j = 0; j < groesse - 1; j++) {
					levelSpeicherort[levelZaehler - 1][i][j] = map.map[i][j];
					levelInhalt[i][j] = map.map[i][j];
				}
			}
			levelZaehler++;
		}
		for (int i = 0; i < groesse; i++) {
			for (int j = 0; j < groesse; j++) {
				levelInhalt[j][i] = levelSpeicherort[levelID][j][i];
			}
		}
		// Initialisierung der IDs
		int spielerID = 0;
		int monsterID = 0;
		int trankID = 0;
		// Definierung der Arrays
		spielerListe = new Spieler[anzahlLevel];
		gegnerListe = new Monster[anzahlLevel * 3];
		trankListe = new Heiltrank[anzahlLevel];

		// Das Level durchsuchen, um
		for (int i = 0; i < levelInhalt.length; i++) {
			for (int j = 0; j < levelInhalt.length; j++) {
				if (levelInhalt[i][j] == 2) {
					// Charakter zu finden und die ID zuzuordnen
					Spieler spieler = new Spieler(spielerID);
					spieler.setPos(i, j);
					spielerListe[spielerID] = spieler;
					spielerID++;
				} else if (levelInhalt[i][j] == 3) {
					// Monster zu finden und ihnen eine ID zuzuordnen ;
					// festlegen, ob Monster Trank traegt
					Monster gegner = new Monster(monsterID, monsterLebenspunkte, monsterSchaden, false, j, i);
					gegnerListe[monsterID] = gegner;
					monsterID++;
				} else if (levelInhalt[i][j] == 4) {
					// Traenke zu finden und ihnen ihre ID zuzuordnen
					Heiltrank trank = new Heiltrank(trankID, j, i);
					trankListe[trankID] = trank;
					trankID++;
				} else if (levelInhalt[i][j] == 5) {
					// Schluessel zu finden und die Koordinaten zu speichern
					Monster gegner = new Monster(monsterID, monsterLebenspunkte, monsterSchaden, true, j, i);
					gegnerListe[monsterID] = gegner;
					monsterID++;
				} else if (levelInhalt[i][j] == 6) {
					// Tuer zu finden und ihre Koordinaten zu speichern
					tuerX = j;
					tuerY = i;
				}
			}
		}
	}

	/**
	 *
	 * Getter Methoden die Spieler Liste
	 * 
	 * @author Marius
	 */
	public Spieler[] getSpielerListe() {
		return spielerListe;
	}

	/**
	 * 
	 * 
	 * Getter Methode fuer die Gegner Liste
	 * 
	 * @author Marius
	 */
	public Monster[] getGegnerListe() {
		return gegnerListe;
	}

	/**
	 * 
	 * 
	 * Getter Methode fuer die Trank Liste
	 * 
	 * @author Marius
	 */
	public Heiltrank[] getTrankListe() {
		return trankListe;
	}

	/**
	 * setter-Methode, um bestimmte Felder im Level zu veraendern
	 * 
	 * @author Marius
	 * @param levelID:
	 *            Identifikationszahl des Levels
	 * @param x:
	 *            x-Koordinate fuer die Position im Level
	 * @param y:
	 *            y-Koordinate fuer die Position im Level
	 * @param inhalt:
	 *            Zelle, die an diesem Punkt im Labyrinth ist (Wand = 0, Boden =
	 *            1, ...)
	 * @param spiel:
	 *            Die zu verwaltende Levelverwaltung
	 * 
	 * 
	 */
	public void setLevelInhalt(int levelID, int x, int y, int inhalt, Levelverwaltung spiel) {
		levelInhalt[x][y] = inhalt;
		// Wenn es um den Charakter geht ; der inhalt = 2 ist, dann
		if (inhalt == 2) {
			int spielerID = 0;
			// wird seine neue Position in die Spielerliste uebertragen
			spiel.spielerListe[spielerID].setXPos(x);
			spiel.spielerListe[spielerID].setYPos(y);
		} else if (inhalt == 3) {
			// Erklaerung fuer die Monstersuche ist identisch zur Erklaerung in
			// der Spielersuche
			boolean gefunden = false;
			int gegnerID = 0;
			while (!gefunden) {
				if (Levelverwaltung.levelInhalt[x][y] != 0 && gegnerListe[gegnerID].getPosX() == x
						&& gegnerListe[gegnerID].getPosY() == y) {
					gefunden = true;
					Levelverwaltung.levelInhalt[gegnerListe[gegnerID].getPosX()][gegnerListe[gegnerID].getPosY()] = 1;
					spiel.gegnerListe[gegnerID].setPosX(x);
					spiel.gegnerListe[gegnerID].setPosY(y);
				} else {
					if (gegnerID < gegnerListe.length - 1) {
						gegnerID++;
					} else {
						gefunden = true;
					}
				}
			}
		} else if (inhalt == 4) {
			// Tranksuche identisch zur Monster- und Spielersuche
			boolean gefunden = false;
			int trankID = 0;
			while (!gefunden) {
				if (trankListe[trankID].getPosX() == x && trankListe[trankID].getPosY() == y) {
					gefunden = true;
					trankListe[trankID].setPosX(x);
					trankListe[trankID].setPosY(y);
					trankListe[trankID].aufgehoben = false;
				} else {
					if (trankID < trankListe.length - 1) {
						trankID++;
					} else {
						gefunden = true;
					}
				}
			}
		}
	}

	/**
	 * @author Marius
	 * @param spieler:
	 *            Identifikationszahl des Spielers
	 * 
	 *            ueberprueft, ob der Spieler mit der SpielerID einen Trank
	 *            benutzen kann wird ueberprueft, indem die Anzahl der Traenke
	 *            ueberprueft wird
	 */
	public boolean trankBenutzbar(int spielerID) {
		boolean funktioniert;
		if (spielerListe[spielerID].getAnzahlHeiltraenke() > 0) {
			funktioniert = true;
		} else {
			funktioniert = false;
		}
		return funktioniert;
	}

	/**
	 * @author Marius
	 * @param spielerID:
	 *            Identifikationszahl des Spielers
	 * 
	 *            void Methode, um einen Trank zu benutzen. Hierbei werden die
	 *            Lebenspunkte des Spielers wieder aufgefuellt
	 */
	public void benutzeTrank(int spielerID) {
		if (trankBenutzbar(spielerID)) {
			spielerListe[spielerID].setAnzahlHeiltraenke(spielerListe[spielerID].getAnzahlHeiltraenke() - 1);
			spielerListe[spielerID].setLebenspunkte(10);
		}
	}

	/**
	 * @author Marius
	 * @param Nachricht:
	 *            Die vom Client empfangene Nachricht
	 * @param spiel:
	 *            Die zu verwaltende Levelverwaltung
	 * 
	 *            Einordnung der Nachrichten. Je nachdem welche Art von
	 *            Nachricht ankommt, so wird sie zum jeweiligen Nachrichten
	 *            Behandler weiter geleitet. Dieser gibt dann einen Boolean
	 *            zur�ck. Je nachdem wird eine bestimmte Aussage ausgegeben
	 */
	public static boolean verarbeiteClientNachricht(Nachricht Nachricht, Levelverwaltung spiel) throws Exception {
		switch (Nachricht.getTyp()) {
		/*
		 * !Die hier angegebenen Reaktionen auf die Nachrichten sind nur zu
		 * Testzwecken und werden bei der Integration der anderen Komponenten
		 * ausgebessert!
		 */
		case 0:
			if (Nachricht.getLoginTyp() == 0)
				return Einloggen.LogIn(Nachricht.benutzername, Nachricht.passwort);
			else
				return Einloggen.RegIn(Nachricht.benutzername, Nachricht.passwort);// Login
		case 1:
			return behandleSpielerbewegung(Nachricht, spiel);// Spielerbewegung
		case 2:
			return behandleTrankaufnahme(Nachricht, spiel); // Trankaufnahme
		case 3:
			return behandleLevelGeschafft(Nachricht.getID(), spiel); // Level geschafft																
		case 4:
			return behandleschluesselaufgehoben(Nachricht, spiel);// Schluesselaufnahme
		case 5:
			System.out.println(Nachricht.nachricht);
			return true;// Fehlermeldung
		case 6:
			return true;// Level empfangen
		case 7:
			return behandleLevelUebersprungen(spiel);// Spieler ueberspringt Level
		case 8:
			return chat.nachrichtEmpfangen(Nachricht.nachricht);// Chat Nachricht																
		case 9:
			return behandleKampfnachrichten((KampfNachricht) Nachricht, spiel);// Kampnachricht
		case 13:
			behandleCheat(Nachricht);
		}
		return false;
	}

	/**
	 * Abwicklung der Cheats ueber den Client
	 * Auswirkungen der Cheats werden ueber andere Nachrichten versendet
	 * 
	 * @param nachricht
	 */
	private static void behandleCheat(Nachricht nachricht) {
	}

	/**
	 * @author Marius
	 * @param Spielerbewegung:
	 *            Die vom Client empfangene Nachricht, die eine Spielerbewegung
	 *            beinhaltet
	 * @param spiel:
	 *            Die zu verwaltende Levelverwaltung
	 * 
	 *Behandelt die Nachrichten, die eine Spielerbewegung beinhalten. Zunaechst wird ueberprueft, ob der Spieler an
	 *diese Position gehen darf. danach wird seine Position geaendert
	 */
	public static boolean behandleSpielerbewegung(Nachricht Spielerbewegung, Levelverwaltung spiel) {
		boolean moeglich;
		System.out.print("Spielerbewegung");
		if (Levelverwaltung.levelInhalt[Spielerbewegung.getxKoo()][Spielerbewegung.getyKoo()] != 0
				&& ((Spielerbewegung.getxKoo() == spiel.spielerListe[Spielerbewegung.getID()].getXPos())
						&& (Spielerbewegung.getyKoo() == (spiel.spielerListe[Spielerbewegung.getID()].getYPos() + 1))
						|| (Spielerbewegung.getyKoo() == (spiel.spielerListe[Spielerbewegung.getID()].getYPos() - 1)))
				|| ((Spielerbewegung.getyKoo() == spiel.spielerListe[Spielerbewegung.getID()].getYPos())
						&& (((Spielerbewegung.getxKoo() == (spiel.spielerListe[Spielerbewegung.getID()].getXPos() + 1))
								|| (Spielerbewegung
										.getxKoo() == (spiel.spielerListe[Spielerbewegung.getID()].getXPos() - 1)))))) {

			System.out.println("erlaubt");
			spiel.setLevelInhalt(0, Spielerbewegung.getxKoo(), Spielerbewegung.getyKoo(), 2, spiel);
			moeglich = true;
		} else {
			System.out.println("verboten");
			System.out.println("Spielerposition" + spiel.spielerListe[Spielerbewegung.getID()].getXPos() + " "
					+ spiel.spielerListe[Spielerbewegung.getID()].getYPos());
			System.out.println("Nachrichtenbewegung" + Spielerbewegung.getxKoo() + " " + Spielerbewegung.getyKoo());
			moeglich = false;
		}
		return moeglich;
	}

	/**
	 * @author Marius
	 * @param trankAufnahme:
	 *            Die vom Client empfangene Nachricht, die eine Trank Aufnahme
	 *            beinhaltet
	 * @param spiel:
	 *            Die zu verwaltende Levelverwaltung
	 * 
	 *            Nachrichten Behandler fuer die Aufnhame eines Trankes
	 *            zunaechst wird ueberprueft, ob der Trank aufgenommen werden
	 *            darf danach wird er zum Inventar hinzugefuegt
	 */
	public static boolean behandleTrankaufnahme(Nachricht trankAufnahme, Levelverwaltung spiel) {
		boolean moeglich;
		if (!(spiel.trankListe[trankAufnahme.trankID].aufgehoben)
				&& spiel.trankListe[trankAufnahme.trankID].getPosX() == spiel.spielerListe[trankAufnahme.getID()]
						.getXPos()
				&& spiel.trankListe[trankAufnahme.trankID].getPosY() == spiel.spielerListe[trankAufnahme.getID()]
						.getYPos()) {
			spiel.trankListe[0].aufgehoben = true;
			spiel.spielerListe[trankAufnahme.getID()]
					.setAnzahlHeiltraenke(spiel.spielerListe[trankAufnahme.getID()].getAnzahlHeiltraenke() + 1);
			moeglich = true;
		} else {
			System.out.println(spiel.trankListe[trankAufnahme.trankID].getPosX() + " "
					+ spiel.trankListe[trankAufnahme.trankID].getPosY());
			System.out.println(spiel.spielerListe[trankAufnahme.getID()].getXPos() + " "
					+ spiel.spielerListe[trankAufnahme.getID()].getYPos());
			moeglich = false;
		}
		return moeglich;
	}

	/**
	 * @author Marius
	 * @param spielerID:
	 *            Identifikationszahl des Spielers
	 * @param spiel:
	 *            Die zu verwaltende Levelverwaltung
	 * 
	 *            Nachrichten Behandler fuer das beendete Level zunaechst
	 *            Ueberpruefung, ob der Schluessel aufgehoben wurde und ob der
	 *            Spieler bei der Tuer ist danach wird true zurueck gegeben und
	 *            das naechste Level kann gestartet werden.
	 */
	public static boolean behandleLevelGeschafft(int spielerID, Levelverwaltung spiel) {
		boolean moeglich;
		if (spiel.spielerListe[spielerID].hatSchluessel() && spiel.spielerListe[spielerID].getXPos() == tuerX
				&& spiel.spielerListe[spielerID].getYPos() == tuerY) {
			// Level wechseln
			moeglich = true;
			levelID++;
			for (int i = 0; i < groesse; i++) {
				for (int j = 0; j < groesse; j++) {
					levelInhalt[j][i] = levelSpeicherort[levelID][j][i];
				}
			}
		} else {
			moeglich = false;
		}
		return moeglich;
	}

	/**
	 * @author Marius
	 * @param schluesselAufnahme:
	 *            Die vom Client empfangene Nachricht, die eine Schluessel
	 *            Aufnahme beinhaltet
	 * @param spiel:
	 *            Die zu verwaltende Levelverwaltung
	 * 
	 *            Nachrichtenbehandler fuer einen aufgehobenen Schluessel
	 *            zunaechst wird ueberprueft, ob er aufgehoben werden kann
	 *            anschliessend wird der schluesselstatus auf wahr gesetzt
	 */
	public static boolean behandleschluesselaufgehoben(Nachricht schluesselAufnahme, Levelverwaltung spiel) {
		boolean moeglich;
		if (spiel.spielerListe[schluesselAufnahme.getID()].getXPos() == SchluesselX
				&& spiel.spielerListe[schluesselAufnahme.getID()].getYPos() == SchluesselY) {
			spiel.spielerListe[schluesselAufnahme.getID()].nimmSchluessel();
			moeglich = true;
		} else {
			moeglich = false;
		}
		return moeglich;
	}

	/**
	 * @author Marius
	 * @param spiel:
	 *            Die zu verwaltende Levelverwaltung
	 * 
	 *            Nachrichtenbehandler fuer das Uebersprungene Level Der Spieler
	 *            wird dafuer auf die Tuer gesetzt und ihm wird der Schluessel
	 *            uebergeben
	 */
	public static boolean behandleLevelUebersprungen(Levelverwaltung spiel) {
		Levelverwaltung.levelInhalt[spiel.spielerListe[0].getXPos()][spiel.spielerListe[0].getYPos()] = 1;
		Levelverwaltung.levelInhalt[tuerX][tuerY] = 2;
		spiel.spielerListe[0].setXPos(tuerX);
		spiel.spielerListe[0].setYPos(tuerY);
		spiel.spielerListe[0].nimmSchluessel();
		behandleLevelGeschafft(0, spiel);
		return true;

	}

	/**
	 * @author Marius
	 * @param Nachricht:
	 *            Eine Nachricht, die vom Server kommt ; Beinhaltet eine
	 *            Kampfnachricht
	 * @param spiel:
	 *            Die zu verwaltende Levelverwaltung
	 * 
	 *            Nachrichtenbehandler fuer Kampfnachrichten Je Nachdem wer
	 *            angegriffen wird werden die Lebenspunkte veraendert
	 */
	public static boolean behandleKampfnachrichten(KampfNachricht Nachricht, Levelverwaltung spiel) {
		if (Nachricht.angegriffen) {
			spiel.spielerListe[Nachricht.getID()]
					.setLebenspunkte(spiel.spielerListe[Nachricht.getID()].getLebenspunkte() - 1);
		} else {
			spiel.gegnerListe[Nachricht.monsterID]
					.setLebenspunkte(spiel.gegnerListe[Nachricht.monsterID].getLebenspunkte() - 1);
		}
		return true;
	}
}