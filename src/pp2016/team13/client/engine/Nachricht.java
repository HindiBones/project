package pp2016.team13.client.engine;

import java.io.Serializable;
import pp2016.team13.shared.Level;

/**
 * Oberklasse aller Nachrichten-Klassen. Enthaelt alle noetigen Attribute und Methoden der Nachrichten
 * 
 * @author <Braun, Jan Julius, 6000100>
 *
 */
public class Nachricht implements Serializable {
	private static final long serialVersionUID = 1L;
	int typ, logintyp;
	public long zeit;
	public int cheattyp = 0, punktzahl;
	public boolean aufgenommen, angegriffen;
	public String fehlermeldung, benutzername, passwort, nachricht;
	public Level[] Levels = new Level[5];
	public int[][][] leveldaten;
	int xKoo;
	int yKoo;
	public int spielerID, monsterID, trankID;
	public boolean inOrdnung;

	/*
	 * Typen von Messages: type 0 : Login-Message type 1 : Spielerbewegung type
	 * 2 : Trankaufnahme -- Itemaufnahme type 3 : Level geschafft type 4 :
	 * Schluesselaufnahme -- Fehler (z.B. falsche Login-Daten, inkonsistente
	 * Bewegungen, usw) type 5 : Fehlermeldung -- Level geladen type 6 : Level
	 * empfangen -- Kampfnachricht type 7 : Spieler �berspringt Level type 8 :
	 * Chat Nachricht
	 */

	/**
	 * Erstellt eine Nachricht, die von Client-Engine und Server-Engine verarbeitet werden kann
	 * 
	 * @param typ
	 * 			  : Setzt den Typ der Nachricht fest, damit Client und Server entsprechend reagieren koennen
	 * 
	 * @author <Braun, Jan Julius, 6000100>
	 * 
	 */
	public Nachricht(int typ) {
		this.typ = typ;
	}

	/**
	 * @return: Gibt den Typ der Nachricht zurueck.
	 * 
	 * @author <Braun, Jan Julius, 6000100>
	 * 
	 */
	public int getTyp() {
		return this.typ;
	}

	/**
	 * @return: Gibt die ID des Spielers zurueck.
	 * 
	 * @author <Braun, Jan Julius, 6000100>
	 */
	public int getID() {
		return this.spielerID;
	}

	/**
	 * @return: Gibt die gespeicherte X-Koordinate zurueck.
	 * 
	 * @author <Braun, Jan Julius, 6000100>
	 */
	public int getxKoo() {
		return this.xKoo;
	}

	/**
	 * @return: Gibt die gespeicherte Y-Koordinate zurueck.
	 * 
	 * author <Braun, Jan Julius, 6000100>
	 */
	public int getyKoo() {
		return this.yKoo;
	}
	
	/**
	 * @return: Gibt den gespeicherten Logintypen zurueck.
	 * 
	 * @author <Braun, Jan Julius, 6000100>
	 */
	public int getLoginTyp() {
		return logintyp;
	}
}
