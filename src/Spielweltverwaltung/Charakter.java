package Spielweltverwaltung;

import java.io.IOException;


public class Charakter {
	//Speicherung der spielerbezogenen Daten
	public static int spielerID;
	public static int lebenspunkte;
	public static int spielerSchaden;
	public static int anzahlTraenke;
	public static boolean schluessel;
	//Speichert die Position des Spielers
	public static int posX;
	public static int posY;
	
	//Konstruktor
	public Charakter(int id, int anfangsLebenspunkte, int anfangsSchaden, int anfangsTraenke, int anfangsX, int anfangsY) throws IOException {
		 spielerID = id;
		 setLebenspunkte(anfangsLebenspunkte);
		 setSchaden(anfangsSchaden);
		 setAnzahlTraenke(anfangsTraenke);
		 setSchluessel(false);
		 setPosX(anfangsX);
		 setPosY(anfangsY);
		 
	 }
	
	//Keine setter-Methode für die Spieler-ID, da diese nicht verändert werden soll
	
	//getter-Methode für die Spieler-ID
	public static int getSpielerID(){
		return spielerID;
	}
	
	//setter-Methode für die Lebenspunkte
	public static void setLebenspunkte(int neueLebenspunkte){
		lebenspunkte = neueLebenspunkte;
	}
	
	//getter-Methode für die Lebenspunkte
	public static int getLebenspunkte(){
		return lebenspunkte;
	}
	
	//setter-Methode für den Schaden, den der Spieler verursacht
	public static void setSchaden (int neuSpielerSchaden){
		spielerSchaden = neuSpielerSchaden;
	}
	
	//getter-Methode für den Schaden, den der Spieler verursacht
	public static int getspielerSchaden(){
		return spielerSchaden;
	}
	
	//setter-Methode für die Anzahl der Tränke, die der Spieler bei sich trägt
	public static void setAnzahlTraenke(int neuAnzahlTraenke){
		anzahlTraenke = neuAnzahlTraenke;
	}
	
	//getter-Methode für die Anzahl der Tränke, die der Spieler bei sich trägt
	public static int getAnzahlTraenke(){
		return anzahlTraenke;
	}
	
	//setter-Methode für den Schlüssel. Wird dieser gefunden, so wird er auf true gesetzt. Bei jedem Level-Neustart auf false
	public static void setSchluessel(boolean neuschluessel){
		schluessel=neuschluessel;
	}
	
	//getter-Methode, die zurück gibt, ob der Schlüssel bisher von diesem Charakter gefunden wurde
	public static boolean getSchluessel(){
		return schluessel;
	}
	
	//setter-Methode für die x-Koordinate des Spielers
	public static void setPosX(int neuPosX){
		posX=neuPosX;
	}
	
	//getter-Methode für die x-Koordinate des Spielers
	public static int getPosX(){
		return posX;
	}
	
	//setter-Methode für die y-Koordinate des Spielers
	public static void setPosY(int neuPosY){
		posY=neuPosY;
	}
	
	//getter-Methode für die y-Koordinate des Spielers
	public static  int getPosY(){
		return posY;
	}
}
