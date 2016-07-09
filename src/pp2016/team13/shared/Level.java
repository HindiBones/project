package pp2016.team13.shared;

import java.io.IOException;
import java.io.Serializable;

public class Level implements Serializable{

	public int levelID;
	public int [][] levelInhalt;

	public Level(int id, int [][]level) {
		 levelID = id;
		 levelInhalt = level;
	 }
	
	//kein Setter fï¿½r die Level-ID benï¿½tigt, da diese nicht mehr verï¿½ndert werden soll
	
	//getter-Methode fï¿½r die Level-ID
	public int getLevelID(){
		return levelID;
	}
	
	//setter-Methode, um bestimmte Felder im Level zu verï¿½ndern
	public  void setLevelInhalt(int x, int y, int inhalt){
		levelInhalt[x][y] = inhalt;
	}
	
	
	//getter-Methode, um das gesamte Level auszulesen
	public  int [][] getKomplettesLevel(){
		return levelInhalt;
	}
	
	//getter-Methode, um ein bestimmtes Feld auszulesen
	public  int getBestimmtenLevelInhalt(int x, int y){
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
	
	//getter Methode für alle x-Werte
		public  int[] getAlleXWerte(int yKoordinate){
			int []xWerte = null;
			for(int i = 0; i<=levelInhalt.length; i++){
				xWerte[i]= levelInhalt[i][yKoordinate];
			}
			return xWerte;
		}
		
		//getter Methode für alle y-Werte
		public  int[] getAlleYWerte(int xKoordinate){
			int []yWerte = null;
			for(int i = 0; i<=levelInhalt.length; i++){
				yWerte[i]= levelInhalt[i][xKoordinate];
			}
			return yWerte;
		}
	
	//Lï¿½nge des Arrays in x-Richtung bestimmen
	public int getLaengeX(){
		return levelInhalt.length;
	}
	
	//Lï¿½nge des Arrays in y-Richtung bestimmen
	public int getLaengeY(){
		return levelInhalt[0].length;
	}
	
	public  int[][] getlevelInhalt (){
		return levelInhalt;
	}
}
