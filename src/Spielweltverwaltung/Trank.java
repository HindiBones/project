package Spielweltverwaltung;

import java.io.IOException;

public class Trank {
	public static int posX;
	public static int posY;
	public static boolean aufgehoben;
	public static boolean gedroppt;
	public static int trankID;

	public Trank(int id, int anfangsX, int anfangsY) throws IOException {
		 trankID = id;
		 setGedroppt(false);
		 setAufgehoben(false);
		 setPosX(anfangsX);
		 setPosY(anfangsY);
		 
	 }
	
	//Keine setter-Methode für die Trank-ID, da diese nicht verändert werden soll
	
	//getter-Methode für die Trank-ID;
	public static int getTrankID(){
		return trankID;
	}
	
	//setter-Methode, die festlegt, ob der Trank bereits gedroppt wurde
	public static void setGedroppt(boolean neuGedroppt){
		gedroppt = neuGedroppt;
	}
	
	//getter-Methode, die anzeigt, ob der Trank bereits gedroppt wurde
	public static boolean getGedroppt(){
		return gedroppt;
	}
	
	//setter-Methode, die den Zustand des Tranks ändert
	public static void setAufgehoben(boolean neuAufgehoben){
		aufgehoben=neuAufgehoben;
	}
	
	//getter-Methode für den Zustand des Tranks
	public static boolean getAufgehoben(){
		return aufgehoben;
	}
	
	//setter-Methode für die x-Koordinate des Tranks
	public static void setPosX(int neuPosX){
		posX=neuPosX;
	}
	
	//getter-Methode für die x-Koordinate des Tranks
	public static int getPosX(){
		return posX;
	}
	
	//setter-Methode für die y-Koordinate des Tranks
	public static void setPosY(int neuPosY){
		posY=neuPosY;
	}
	
	//getter-Methode für die y-Koordinate des Tranks
	public static  int getPosY(){
		return posY;
	}
	
}
