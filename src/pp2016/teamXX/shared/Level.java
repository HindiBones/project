package pp2016.teamXX.shared;

import java.io.IOException;

public class Level {

	public static int levelID;
	public static int [][] levelInhalt;

	public Level(int id, int [][]level) {
		 levelID = id;
		 levelInhalt = level;
	 }
	
	//kein Setter f�r die Level-ID ben�tigt, da diese nicht mehr ver�ndert werden soll
	
	//getter-Methode f�r die Level-ID
	public static int getLevelID(){
		return levelID;
	}
	
	//setter-Methode, um bestimmte Felder im Level zu ver�ndern
	public static void setLevelInhalt(int x, int y, int inhalt){
		levelInhalt[x][y] = inhalt;
	}
	
	//Hilfsmethode, sp�ter wieder l�schen, Array voller 0en f�llen
	public static void setInhaltNull(){
		for(int i = 0; i<levelInhalt.length;i++){
			for (int j  = 0;j<levelInhalt[0].length;j++){
				levelInhalt[j][i]=0;
			}
		}
	}
	/* 0 = Wand
	 * 1 = Boden
	 * 2 = Spieler
	 * 3 = Trank
	 * 4 = Schluessel
	 * 5 = Monster
	 * 6 = TürZu
	 * 7 = TürAuf
	 */
	//getter-Methode, um das gesamte Level auszulesen
	public static int [][] getKomplettesLevel(){
		return levelInhalt;
	}
	
	//getter-Methode, um ein bestimmtes Feld auszulesen
	public static int getBestimmtenLevelInhalt(int x, int y){
		return levelInhalt[x][y];
	}
	
	//Methode um Level auf der Konsole auszugeben
	public void ausgabe(){
		for(int i=0;i<levelInhalt.length;i++){
			for(int j = 0;j<levelInhalt[0].length;j++){
				System.out.print(levelInhalt[j][i]);
			}
			System.out.println();
		}
	}
	
	//L�nge des Arrays in x-Richtung bestimmen
	public int getLaengeX(){
		return levelInhalt.length;
	}
	
	//L�nge des Arrays in y-Richtung bestimmen
	public int getLaengeY(){
		return levelInhalt[0].length;
	}
}
