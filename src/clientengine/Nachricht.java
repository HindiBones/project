package clientengine;

public class Nachricht {
	int typ;
	boolean aufgenommen;
	String fehlermeldung, benutzername, passwort;
	Level[] leveldaten;
	Spielelement item;
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
	public Nachricht (int t){
		this.typ = t;
	}
	
	public int getTyp(){
		return this.typ;
	}

	public int getID(){
		return this.spielerID;
	}
	public int getxKoo(){
		return this.xKoo;
	}
	public int getyKoo(){
		return this.yKoo;
	}
}
