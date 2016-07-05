package pp2016.team13.client.engine;

import pp2016.team13.shared.Level;

public class Nachricht {
	int typ;
	boolean aufgenommen;
	String fehlermeldung, benutzername, passwort, nachricht;
	Level[] leveldaten;
	int xKoo;
	int yKoo;
	int spielerID;
	/* 
	 * Typen von Messages:
	 * type 0 : Login-Message
	 * type 1 : Spielerbewegung
	 * type 2 : Itemaufnahme
	 * type 3 : Level geschafft
	 * type 4 : Fehler (z.B. falsche Login-Daten, inkonsistente Bewegungen, usw)
	 * type 5 : Level geladen
	 * type 6 : Kampfnachricht
	 */

	
	
	// Nachricht, die gesendet wird, wenn das Level geschafft wurde - Nur zum Testen
	/**
	 * @author Julius
	 * @param t: Typ der Nachricht
	 * 
	 * Erstellt eine Nachricht. Je nach Typ reagieren Server/Client entsprechend.
	 */
	public Nachricht (String string){
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
}
