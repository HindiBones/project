package pp2016.team13.server.engine;

import java.io.IOException;

import pp2016.team13.server.map.Labyrinth;

public class Levelverwaltung {
	//Level ID
	public static int levelID;
	//speichert alle Spieler, inkl. ihrer Eigenschaften; Zugriff über spielerID
	static Charakter []spielerListe;
	//speichert alle Gegner, inkl. ihrer Eigenschaften; Zugriff über gegnerID
	static Monster[]gegnerListe;
	//speichert alle Traenke; Zugriff ueber TrankID
	static Trank [] trankListe;
	//Speichert alle Zellen des Levels
	//Level Inhalt : 0=Wand, 1 = Boden, 2 = Charakter, 3 = Monster, 4 = Trank, 5=Schluessel, 6 = Tuer
	public static int [][] levelInhalt;
	//Die Anzahl der Level wird festgelegt
	public static int anzahlLevel;
	//Die Groesse der Level wird festgelegt
	public static int groesse;
	//Initialisierung des Speicherortes für alle Level
	public static int [][][] levelSpeicherort;
	//Koordinaten des Schluessels
	static int SchluesselX;
	static int SchluesselY;
	//Koordinaten der Tuer
	static int tuerX;
	static int tuerY;
	
	//Konstruktor Levelverwaltung ; Spielwelt
	public Levelverwaltung(int levelID, int charakterLebenspunkte, int charakterSchaden, int charakterTraenke, int monsterLebenspunkte, int monsterSchaden, int groesse, int anzLevel) throws IOException{
		this.levelID = levelID;
		anzahlLevel = anzLevel;
		this.groesse = groesse;
		levelSpeicherort = new int [anzahlLevel][groesse][groesse];
		levelInhalt = new int [groesse][groesse];
		//Level anlegen
		int levelZaehler = 0;
		//Vom Levelgenerator ankommendes zweidimensionales Integer Array
		while (levelZaehler < anzahlLevel){
			Labyrinth map = new Labyrinth();
			levelInhalt = map.map;
			for (int i = 0; i<groesse; i++){
				for (int j = 0; j<groesse ; j++){
					levelSpeicherort[levelZaehler][j][i] = levelInhalt[j][i];
				}
			}
			levelZaehler++;
		}
		for (int i = 0; i<groesse ; i++){
			for (int j = 0; j<groesse ; j++){
				 levelInhalt[j][i] = levelSpeicherort[levelID][j][i];
			}
		}
		//Initialisierung der IDs
		int spielerID = 0;
		int monsterID = 0;
		int trankID = 0;
		//Definierung der Arrays
		spielerListe = new Charakter[anzahlLevel];
		gegnerListe = new Monster [anzahlLevel];
		trankListe = new Trank [anzahlLevel];
		
		//Das Level durchsuchen, um
		for (int i = 0; i<levelInhalt.length ; i++){
			for (int j = 0; j<levelInhalt.length ; j++){
				if(levelInhalt[j][i]==2){
					//Charakter zu finden und die ID zuzuordnen
					Charakter spieler = new Charakter (spielerID, charakterLebenspunkte, charakterSchaden, charakterTraenke, j, i);
					spielerListe[spielerID] = spieler;
					spielerID++;
					levelInhalt[j][i]=1;
					levelSpeicherort[this.levelID][j][i] = 1;
					
				}else if(levelInhalt[j][i] == 3){
					//Monster zu finden und ihnen eine ID zuzuordnen ; festlegen, ob Monster Trank trägt
					boolean trankVorhanden;
					double zufallszahl = Math.random();
					if(zufallszahl<=0.5){
						trankVorhanden = true;
					}else{
						trankVorhanden = false;
					}
					Monster gegner = new Monster (monsterID, monsterLebenspunkte, monsterSchaden, trankVorhanden, j, i);
					gegnerListe [monsterID] = gegner;
					monsterID++;
					
				}else if(levelInhalt[j][i] == 4){
					//Tränke zu finden und ihnen ihre ID zuzuordnen
					Trank trank = new Trank(trankID , j , i);
					trankListe [trankID] = trank;
					trankID ++;
					
				}else if(levelInhalt[j][i] == 5){
					//Schluessel zu finden und die Koordinaten zu speichern
					Schluessel key = new Schluessel(j, i);
					SchluesselX = j;
					SchluesselY = i;
				}else if(levelInhalt[j][i] == 6){
					//Tuer zu finden und ihre Koordinaten zu speichern
					tuerX = j;
					tuerY = i;
				}
			}
		}
	}
	
	
	//Getter Methoden die Spieler Liste
	public static Charakter [] getSpielerListe(){
		return spielerListe;
	}
	
	//Getter Methode für die Gegner Liste
	public static Monster [] getGegnerListe(){
		return gegnerListe;
	}
	
	public static Trank [] getTrankListe(){
		return trankListe;
	}
	//setter-Methode, um bestimmte Felder im Level zu verändern
	public static void setLevelInhalt(int levelID, int x, int y, int inhalt, Levelverwaltung spiel){
		levelInhalt[x][y] = inhalt;
		
		//Wenn es um den Charakter geht ; der inhalt = 2 ist, dann
		if(inhalt == 2){
			boolean gefunden = false;
			int spielerID = 0;
			while(!gefunden){
				//sucht er nach der SpielerID ; dem Spieler
				if (spielerListe[spielerID].getPosX() == x && spielerListe[spielerID].getPosY() == y){
					//wenn er gefunden wird, dann wird seine neue Position in die Spielerliste übertragen
					gefunden = true;
					spiel.spielerListe[spielerID].setPosX(x);
					spiel.spielerListe[spielerID].setPosY(y);
				}else{
					//wenn der Spieler nicht gefunden wird, so wird der nächste Spieler ausprobiert.
					//Dies geht solange, bis die ganze Spielerliste durchgegangen wurde
					if (spielerID < spielerListe.length-1)
					{
						spielerID++;
					}else{
						Nachricht Fehlermeldung = new Nachricht (7, "Spieler nicht auffindbar");
						gefunden = true;
					}
					
				}
			}
		}else if (inhalt == 3){
			//Erklaerung für die Monstersuche ist identisch zur Erklaerung in der Spielersuche
			boolean gefunden = false;
			int gegnerID = 0;
			while(!gefunden){
				if (spiel.levelInhalt[x][y] != 0 && gegnerListe[gegnerID].getPosX() == x && gegnerListe[gegnerID].getPosY() == y){
					gefunden = true;
					spiel.levelInhalt[gegnerListe[gegnerID].getPosX()][gegnerListe[gegnerID].getPosY()] = 1;
					spiel.gegnerListe[gegnerID].setPosX(x);
					spiel.gegnerListe[gegnerID].setPosY(y);
				}else{
					if (gegnerID < gegnerListe.length-1)
					{
						gegnerID++;
					}else{
						Nachricht Fehlermeldung = new Nachricht (7, "Monster nicht auffindbar");
						gefunden = true;
					}
				}
			}
		}else if (inhalt == 4){
			//Tranksuche identisch zur Monster- und Spielersuche
			boolean gefunden = false;
			int trankID = 0;
			while(!gefunden){
				if (trankListe[trankID].getPosX() == x && trankListe[trankID].getPosY() == y){
					gefunden = true;
					trankListe[trankID].setPosX(x);
					trankListe[trankID].setPosY(y);
					trankListe[trankID].aufgehoben = false;
				}else{
					if (trankID < trankListe.length-1)
					{
						trankID++;
					}else{
						Nachricht Fehlermeldung = new Nachricht (7, "Trank nicht auffindbar");
						gefunden = true;
					}
				}
			}
		}
	}
	
	//ueberprueft, ob der Spieler mit der SpielerID einen Trank benutzen kann
	//wird ueberprueft, indem die Anzahl der Traenke ueberprueft wird
	public static boolean trankBenutzbar(int spielerID){
		boolean funktioniert;
		if (spielerListe[spielerID].anzahlTraenke>0){
			funktioniert = true;
		}else {
			funktioniert = false;
		}
		return funktioniert;
	}
	
	//void Methode, um einen Trank zu benutzen. Hierbei werden die Lebenspunkte des Spielers wieder aufgefüllt
	public static void benutzeTrank(int spielerID){
		if (trankBenutzbar(spielerID)){
			spielerListe[spielerID].setAnzahlTraenke(spielerListe[spielerID].getAnzahlTraenke()-1);
			spielerListe[spielerID].setLebenspunkte(10);
		}
	}
	
	//Einordnung der Nachrichten. Je nachdem welche Art von Nachricht ankommt, so wird sie zum jeweiligen Nachrichten Behandler weiter geleitet.
	//Dieser gibt dann einen Boolean zurück. Je nachdem wird eine bestimmte Aussage ausgegeben
	public static void verarbeiteNachricht(Nachricht Nachricht, Levelverwaltung spiel){
		if (Nachricht.typ == 1){
			if (spiel.behandleSpielerbewegung(Nachricht, spiel)){
				System.out.println("Spieler darf bewegt werden. Neue Position: " + spiel.spielerListe[Nachricht.getID()].getPosX() + " " + spiel.spielerListe[Nachricht.getID()].getPosY());
			}else{
				Nachricht Fehlermeldung = new Nachricht (7, "Spieler zu weit vom Feld entfernt, oder Wand im Weg. Bewegung nicht möglich");
				System.out.println("Spieler darf nicht bewegt werden");
			}
		}else if (Nachricht.typ == 2){
			System.out.println(spiel.spielerListe[Nachricht.getID()].getAnzahlTraenke());
			if(spiel.behandleTrankaufnahme(Nachricht, spiel)){
				System.out.println("Spieler beim Trank");
				System.out.println(spiel.spielerListe[Nachricht.getID()].getAnzahlTraenke());
			}else{
				System.out.println("Spieler nicht beim Trank");
				System.out.println(spiel.spielerListe[Nachricht.getID()].getAnzahlTraenke());
			}
		}else if (Nachricht.typ == 3){
			if(spiel.behandleLevelGeschafft(Nachricht.getID(), spiel)){
				System.out.println("Nächstes Level");
			}else{
				System.out.println("Level konnte nicht beendet werden");
				Nachricht Fehlermeldung = new Nachricht (7, "level nicht beendet");
			}
		}else if (Nachricht.typ == 4){
			if(spiel.behandleschluesselaufgehoben(Nachricht.getID(), spiel)){
				System.out.println("Schlüssel aufgehoben");
			}else{
				System.out.println("Schlüssel nicht aufgehoben");
				Nachricht Fehlermeldung = new Nachricht (7, "Schlüssel nicht bei Spieler");
			}
		}else if (Nachricht.typ == 5){
			Chat.nachrichtEmpfanden(Nachricht.getNachricht());
		}else if (Nachricht.typ == 7){
			if(spiel.behandleLevelUebersprungen(spiel)){
				System.out.println("Level Übersprungen");
			}else{
				System.out.println("Level konnte nicht übersprungen werden");
			}
		}
	}
	
	//Behandelt die Nachrichten, die eine Spielerbewegung beinhalten.
	//Zunächst wird ueberprueft, ob der Spieler an diese Position gehen darf.
	//danach wird seine Position geändert
	public static boolean behandleSpielerbewegung(Nachricht Spielerbewegung, Levelverwaltung spiel){
		boolean moeglich;
		if (spiel.levelInhalt[Spielerbewegung.getxKoo()][Spielerbewegung.getyKoo()] != 0 &&spiel.levelInhalt[Spielerbewegung.getxKoo()][Spielerbewegung.getyKoo()] != 3 && ((Spielerbewegung.getxKoo() == spiel.spielerListe[Spielerbewegung.getID()].getPosX() && (Spielerbewegung.getyKoo() == spiel.spielerListe[Spielerbewegung.getID()].getPosY() +1 || Spielerbewegung.getyKoo() == spiel.spielerListe[Spielerbewegung.getID()].getPosY() -1))
				|| (Spielerbewegung.getyKoo() == spiel.spielerListe[Spielerbewegung.getID()].getPosY() && (Spielerbewegung.getxKoo() == spiel.spielerListe[Spielerbewegung.getID()].getPosX() +1 || Spielerbewegung.getxKoo() == spiel.spielerListe[Spielerbewegung.getID()].getPosX() -1)))){
			spiel.setLevelInhalt(0, Spielerbewegung.getxKoo(), Spielerbewegung.getyKoo(), 2, spiel);
			moeglich = true;
		}else {
			moeglich = false;
		}
		return moeglich;
	}


	//Nachrichten Behandler für die Aufnhame eines Trankes
	//zunächst wird ueberprueft, ob der Trank aufgenommen werden darf
	//danach wird er zum Inventar hinzugefuegt
	public static boolean behandleTrankaufnahme(Nachricht trankAufnahme, Levelverwaltung spiel) {
		boolean moeglich;
		if (!(spiel.trankListe[trankAufnahme.getTrankID()].aufgehoben) && spiel.trankListe[trankAufnahme.getTrankID()].getPosX() == spiel.spielerListe[trankAufnahme.getID()].getPosX() && spiel.trankListe[trankAufnahme.getTrankID()].getPosY() == spiel.spielerListe[trankAufnahme.getID()].getPosY()){
			spiel.trankListe[0].aufgehoben = true;
			spiel.spielerListe[trankAufnahme.getID()].setAnzahlTraenke(spiel.spielerListe[trankAufnahme.getID()].getAnzahlTraenke()+1);
			moeglich = true;
		}else{
			System.out.println(spiel.trankListe[trankAufnahme.getTrankID()].getPosX() + " "+ spiel.trankListe[trankAufnahme.getTrankID()].getPosY());
			System.out.println(spiel.spielerListe[trankAufnahme.getID()].getPosX() + " " + spiel.spielerListe[trankAufnahme.getID()].getPosY());
			moeglich = false;
		}
		return moeglich;
	}


	//Nachrichten Behandler fuer das beendete Level
	//zunaechst Ueberpruefung, ob der Schluessel aufgehoben wurde und ob der Spieler bei der Tuer ist
	//danach wird true zurueck gegeben und das naechste Level kann gestartet werden.
	public static boolean behandleLevelGeschafft(int spielerID, Levelverwaltung spiel) {
		boolean moeglich;
		if(spiel.spielerListe[spielerID].getSchluessel() && spiel.spielerListe[spielerID].getPosX() == tuerX && spiel.spielerListe[spielerID].getPosY() == tuerY){
			//Level wechseln -- wenn Levelgenerator da ist. Möglichkeit besteht, siehe Test
			moeglich= true;
			levelID++;
			for (int i = 0; i<groesse ; i++){
				for (int j = 0; j<groesse ; j++){
					 levelInhalt[j][i] = levelSpeicherort[levelID][j][i];
				}
			}
		}else{
			moeglich= false;
		}
		return moeglich;
	}


	//Nachrichtenbehandler fuer einen aufgehobenen Schluessel
	//zunaechst wird ueberprueft, ob er aufgehoben werden kann
	//anschließend wird der schluesselstatus auf wahr gesetzt
	public static boolean behandleschluesselaufgehoben(int id, Levelverwaltung spiel) {
		boolean moeglich;
		if(spiel.spielerListe[id].getPosX() == SchluesselX && spiel.spielerListe[id].getPosY() == SchluesselY){
			spiel.spielerListe[id].setSchluessel(true);
			moeglich = true;
		}else{
			moeglich = false;
		}
		return moeglich;
	}
	
	//Nachrichtenbehandler fuer das Uebersprungene Level
	//Der Spieler wird dafuer auf die Tuer gesetzt und ihm wird der Schluessel uebergeben
	public static boolean behandleLevelUebersprungen (Levelverwaltung spiel){
		spiel.levelInhalt[spiel.spielerListe[0].getPosX()][spiel.spielerListe[0].getPosY()]=1;
		spiel.levelInhalt[tuerX][tuerY] = 2;
		spiel.spielerListe[0].setPosX(tuerX);
		spiel.spielerListe[0].setPosY(tuerY);
		spiel.spielerListe[0].setSchluessel(true);
		behandleLevelGeschafft(0, spiel);
		return true;
		
	}
	
}
