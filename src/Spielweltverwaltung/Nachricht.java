package Spielweltverwaltung;

import datenstruktur.Level;

public class Nachricht {
	int typ;
	int ID;
	int xKoo;
	int yKoo;
	boolean aufgenommen;
	Level leveldaten;
	String nachricht;
	boolean charakter;
	int lebenspunkte;
	int trankID;
	
	/* Typen von Messages:
	 * typ 0 : Login-Message
	 * typ 1 : Spielerbewegung
	 * typ 2 : Trankaufnahme
	 * typ 3 : Level geschafft
	 * typ 4 : Schlüsselaufnahme
	 * typ 5 : Chat Nachricht
	 * typ 6 : LebenspunkteVeraendert
	 * typ 6 : Fehler (z.B. falsche Login-Daten, inkonsistente Bewegungen, usw)
	 * typ 7 : Spieler überspringt Level
	 */
	public Nachricht(int t, int p,int x, int y){
		this.typ=t;
		this.ID=p;
		this.xKoo=x;
		this.yKoo=y;
	}
	
	//Message level Geschafft, typ, SpielerID
	public Nachricht(int t, int ID){
		typ = t;
		this.ID = ID;
	}
	
	public Nachricht(int t,int a, int b){
		this.typ=t;
		this.xKoo=a;
		this.yKoo=b;
	}
	
	public Nachricht (int t, Level x){
		this.typ = t;
		this.leveldaten = x;
	}

	public Nachricht (int t, String Antwort){
		nachricht = Antwort;
		this.typ=t;
	}
	
	public Nachricht (int t, boolean charakter, int lebenspunkte){
		typ = t;
		this.charakter = charakter ;
		this.lebenspunkte = lebenspunkte;
	}
	
	public Nachricht(int t) {
		this.typ=t;
	}

	public Nachricht(int t, int p, int x, String string) {
		typ = t;
		trankID = p;
		ID = x;
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
