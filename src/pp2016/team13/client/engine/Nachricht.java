package pp2016.team13.client.engine;

import java.io.Serializable;
import pp2016.team13.shared.Level;

public class Nachricht implements Serializable {
	/**
	 * 
	 */
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
	 * Typen von Messages:
	 * type 0 : Login-Message
	 * type 1 : Spielerbewegung
	 * type 2 : Trankaufnahme -- Itemaufnahme
	 * type 3 : Level geschafft
	 * type 4 : Schluesselaufnahme -- Fehler (z.B. falsche Login-Daten, inkonsistente Bewegungen, usw)
	 * type 5 : Fehlermeldung -- Level geladen
	 * type 6 : Level empfangen -- Kampfnachricht
	 * type 7 : Spieler �berspringt Level
	 * type 8 : Chat Nachricht
	 */

	
	
	// Nachricht, die gesendet wird, wenn das Level geschafft wurde - Nur zum Testen
	/**
	 * @author Julius
	 * @param t: Typ der Nachricht
	 * 
	 * Erstellt eine Nachricht. Je nach Typ reagieren Server/Client entsprechend.
	 */
	public Nachricht (int typ, String string){
		this.typ=typ;
		nachricht = string;
	}
	
	public Nachricht (int typ){
		this.typ = typ;
	}
	
	public Nachricht() {
	}

	/**
	 * @author Julius
	 * @return: Gibt den Typ der Nachricht zurueck.
	 */
	public int getTyp(){
		return this.typ;
	}

	/**
	 * @author Julius
	 * @return: Gibt die ID des Spielers zurueck.
	 */
	public int getID(){
		return this.spielerID;
	}
	
	/**
	 * @author Julius
	 * @return: Gibt die gespeicherte X-Koordinate zurueck.
	 */
	public int getxKoo(){
		return this.xKoo;
	}
	
	/**
	 * @author Julius
	 * @return: Gibt die gespeicherte Y-Koordinate zurueck.
	 */
	public int getyKoo(){
		return this.yKoo;
	}

	public char[] getMessage(Nachricht n) {
		return null;
	}
	
	public int getLoginTyp(){
		return logintyp;
	}
}
