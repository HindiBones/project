package pp2016.team13.shared.Nachrichten;

import java.io.Serializable;

/**
 * Oberklasse aller Nachrichten-Klassen. Enthaelt alle noetigen Attribute und
 * Methoden der Nachrichten
 * 
 * @author <Braun, Jan Julius, 6000100>
 *
 */
public class Nachricht implements Serializable {
	private static final long serialVersionUID = 1L;
	int typ, logintyp, xKoo, yKoo;
	public long zeit;
	public int cheattyp, punktzahl, spielerID, monsterID, trankID;
	public boolean aufgenommen, angegriffen, inOrdnung;
	public String fehlermeldung, benutzername, passwort, nachricht;
	public int[][][] leveldaten;

	/*
	 * Typen von Messages: type 0 : Login-Message type 1 : Spielerbewegung type
	 * 2 : Heiltrank aufnehmen type 5 : Fehlermeldung type 6 : Levelnachricht
	 * type 7 : Kampfnachricht type 8 : ChatNachricht type 10:
	 * LevelAnfordernNachricht type 11: AntwortNachricht type 12: Schutztrank
	 * aufnehmen type 13: Cheat type 15: AusloggenNachricht
	 */

	/**
	 * Erstellt eine Nachricht, die von Client-Engine und Server-Engine
	 * verarbeitet werden kann
	 * 
	 * @param typ
	 *            : Setzt den Typ der Nachricht fest, damit Client und Server
	 *            entsprechend reagieren koennen
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
	 *          author <Braun, Jan Julius, 6000100>
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
