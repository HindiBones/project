package pp2016.team13.server.engine;

import java.io.IOException;

public class Level {
	
	public static int levelID;
	public static int [][] levelInhalt;

	public Level(int id, int [][]level) throws IOException {
		 levelID = id;
		 levelInhalt = level;
	 }
	
	//kein Setter für die Level-ID benötigt, da diese nicht mehr verändert werden soll
	
	//getter-Methode für die Level-ID
	public static int getLevelID(){
		return levelID;
	}
	
	//setter-Methode, um bestimmte Felder im Level zu verändern
	public static void setLevelInhalt(int x, int y, int inhalt){
		levelInhalt[x][y] = inhalt;
	}
	
	//getter-Methode, um das gesamte Level auszulesen
	public static int [][] getKomplettesLevel(){
		return levelInhalt;
	}
	
	//getter-Methode, um ein bestimmtes Feld auszulesen
	public static int getBestimmtenLevelInhalt(int x, int y){
		return levelInhalt[x][y];
	}
	
	//getter Methode für alle x-Werte
	public static int[] getAlleXWerte(int yKoordinate){
		int []xWerte = null;
		for(int i = 0; i<=levelInhalt.length; i++){
			xWerte[i]= levelInhalt[i][yKoordinate];
		}
		return xWerte;
	}
	
	//getter Methode für alle y-Werte
	public static int[] getAlleYWerte(int xKoordinate){
		int []yWerte = null;
		for(int i = 0; i<=levelInhalt.length; i++){
			yWerte[i]= levelInhalt[i][xKoordinate];
		}
		return yWerte;
	}
}
