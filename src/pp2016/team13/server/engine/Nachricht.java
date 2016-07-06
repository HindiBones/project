package pp2016.team13.server.engine;

import pp2016.team13.shared.Level;

public class Nachricht {
	int typ, ID, xKoo, yKoo, itemTyp, lebenspunkte, trankID, monsterID;
	boolean aufgenommen, charakter, angegriffen;
	Level leveldaten;
	String nachricht, benutzername, passwort;
	
	/* 
	 * Typen von Messages:
	 * type 0 : Login-Message
	 * type 1 : Spielerbewegung
	 * type 2 : Trankaufnahme
	 * type 3 : Level geschafft
	 * type 4 : Schluesselaufnahme
	 * type 5 : Fehlermeldung
	 * type 6 : Level empfangen
	 * type 7 : Spieler überspringt Level
	 * type 8 : Chat Nachricht
	 * type 9 : Kampfnachricht
	 */
	
	//Nachricht Login Nachricht
	public Nachricht (int t, String benutzername, String passwort){
		this.typ = t;
		this.benutzername = benutzername;
		this.passwort = passwort;
	}
	
	//Nachricht Spielerbewegung / Trankaufnahme
	public Nachricht (int t, int ID, int posX, int posY){
		this.typ = t;
		this.ID = ID;
		this.xKoo = posX;
		this.yKoo = posY;
	}
	
	//Nachricht Level Geschafft / Schluesselaufnahme
	public Nachricht (int t, int spielerID){
		this.typ = t;
		this.ID = spielerID;
	}
	
	
	//Fehlermeldung /ChatNachricht
	public Nachricht (int t, String Fehlernachricht){
		this.typ = t;
		this.nachricht = Fehlernachricht;
	}
	
	//Nachricht LevelUeberspringen
	public Nachricht (int t){
		this.typ = t;
	}
	
	//Kampfnachricht
	public Nachricht (int t, boolean spielerAngegriffen, int spielerID, int monsterID){
		this.typ = t;
		this.angegriffen = spielerAngegriffen;
		this.ID = spielerID;
		this.monsterID = monsterID;
	}
	

	public int getType(){
		return this.typ;
	}
	public int getID(){
		return this.ID;
	}
	public int getxKoo(){
		return this.xKoo;
	}
	public int getyKoo(){
		return this.yKoo;
	}
	public String getNachricht (){
		return nachricht;
	}
	public boolean getCharakter(){
		return charakter;
	}
	public int getLebenspunkte (){
		return lebenspunkte;
	}
	public int getTrankID (){
		return trankID;
	}
}
