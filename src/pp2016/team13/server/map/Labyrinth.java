package pp2016.team13.server.map;

import pp2016.team13.server.map.Labyrinth;
/**
 * Groesse der Map festlegen
 * @author Cigdem
 * 
 */
public class Labyrinth {
	private int size = 19;
	public int [][] map = new int [size][size];
	static Koordinaten [] bodenplatten;

/**
 * 
 * bestimmt Anzahl Heiltränke, Schutztränke und Monster.
 * Anzahl Monster werden zufällig bestimmt zwischen 1 und 5.
 * @author Cigdem
 * 
 */
	public Labyrinth (int levelnummer){
		int AnzHeiltrank = 2;
		int AnzSchutztrank = 1;
		int AnzMonster = (int)((Math.random()) * 2 * levelnummer + 1);;
		bodenplatten = new Koordinaten [size*size];
/**
 * 
 * FloodFill füllt die map auf
 * @author Cigdem
 */
		FloodFill auffuellen = new FloodFill(size);
		for (int i = 0; i < size-1 ; i++){
			for (int j = 0 ; j< size-1 ; j++){
				this.map [i][j] = auffuellen.map[i][j];
			}
		}
/**
 * 
 * speichere alle Bodenelemente in bodenplatten
 * @author Cigdem
 */
		// Speichere alle Felder die Boden sind in bodenplatten
		int zaehler = 0;
		for (int i = 0; i< size ; i++){
			for (int j = 0 ; j< size ; j++){
				if (map[i][j] == 1){
					bodenplatten[zaehler] = new Koordinaten (i, j);
					zaehler++;
				}
			}
		}
/**
 * 
 * finde Zufällige Position für Spieler, Tuer, Heiltraenke,Schutztraenke, Monster und ein Schlessel
 *@author Cigdem
 */
		// Zufaellige Position fï¿½r den Charakter
		double zufallsZahl = (Math.random()*zaehler)-1;
		int zz1 = (int) zufallsZahl;
		map[bodenplatten[zz1].xKoordinate][bodenplatten[zz1].yKoordinate]= 2;

		// Position  Tuer
//		zufallsZahl = (Math.random()*zaehler)-1;
		int zz2 = (int) zufallsZahl;
		while (zz2 == zz1){
			zufallsZahl = (Math.random()*zaehler)-1;
			zz2 = (int) zufallsZahl;
		}
		map[bodenplatten[zz2].xKoordinate][bodenplatten[zz2].yKoordinate]= 6;
				
		// 	Zufaellige Position der Heiltraenke
		int zz3 = (int) zufallsZahl;
			for(int i = 0; i <= AnzHeiltrank; i++){
				zufallsZahl = (Math.random()*zaehler)-1;
				while (zz3 == zz1 ||zz3 == zz2){
					zufallsZahl = (Math.random()*zaehler)-1;
					zz3 = (int) zufallsZahl;
				}
		map[bodenplatten[zz3].xKoordinate][bodenplatten[zz3].yKoordinate]= 4;
			}
	
		//	Zufaellige Position der Schutztraenke
		int zz4 = (int) zufallsZahl;
			for(int i = 0; i <= AnzSchutztrank; i++){
				zufallsZahl = (Math.random()*zaehler)-1;
				while (zz4 == zz1 ||zz4 == zz2 || zz4 == zz3){
					zufallsZahl = (Math.random()*zaehler)-1;
				}
		map[bodenplatten[zz4].xKoordinate][bodenplatten[zz4].yKoordinate]= 7;
			}
				
		// 	Zufaellige Position der Monster
		int zz5 = (int)(Math.random()*zaehler)-1;;
			for(int i = 0; i <= AnzMonster; i++){
				zufallsZahl = (Math.random()*zaehler)-1;
				while (zz5 == zz1 ||zz5 == zz2 || zz5 == zz3|| zz5 == zz4){
					zufallsZahl = (Math.random()*zaehler)-1;
				}
			map[bodenplatten[zz5].xKoordinate][bodenplatten[zz5].yKoordinate]= 3;
			}
					

//	 	Zufaellige Position des Schluessels
			int zz6 = (int)(Math.random()*zaehler)-1;;
					zufallsZahl = (Math.random()*zaehler)-1;
					while (zz6 == zz1 ||zz6 == zz2 || zz6 == zz3|| zz6 == zz4|| zz6 == zz5){
						zufallsZahl = (Math.random()*zaehler)-1;
						zz6 = (int) ((Math.random()*zaehler)-1);
						}
					
				map[bodenplatten[zz6].xKoordinate][bodenplatten[zz6].yKoordinate]= 5;
							
		
		
	
		
	}
	
	}
	
