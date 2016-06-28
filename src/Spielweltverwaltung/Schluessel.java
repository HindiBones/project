package Spielweltverwaltung;

import java.io.IOException;

public class Schluessel {
	
	public static int posX;
	public static int posY;
	public static boolean aufgehoben;
	
	public Schluessel(int anfangsX, int anfangsY) throws IOException {
		 setAufgehoben(false);
		 setPosX(anfangsX);
		 setPosY(anfangsY);
		 
	 }
	
	//setter-Methode, die den Zustand des Schlüssels ändert
	public static void setAufgehoben(boolean neuAufgehoben){
		aufgehoben=neuAufgehoben;
	}
	
	//getter-Methode für den Zustand des Schlüssels
	public static boolean getAufgehoben(){
		return aufgehoben;
	}
	
	//setter-Methode für die x-Koordinate des Schlüssels
	public static void setPosX(int neuPosX){
		posX=neuPosX;
	}
	
	//getter-Methode für die x-Koordinate des Schlüssels
	public static int getPosX(){
		return posX;
	}
	
	//setter-Methode für die y-Koordinate des Schlüssels
	public static void setPosY(int neuPosY){
		posY=neuPosY;
	}
	
	//getter-Methode für die y-Koordinate des Schlüssels
	public static  int getPosY(){
		return posY;
	}

}
