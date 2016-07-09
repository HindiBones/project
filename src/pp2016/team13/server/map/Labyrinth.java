package pp2016.team13.server.map;

import pp2016.team13.server.map.Labyrinth;

public class Labyrinth {
	private int size = 17;
	public int [][] map = new int [size][size];
	static Koordinaten [] bodenplatten;
	
	
//	public static void main (String[] args){
//		Labyrinth karte = new Labyrinth();
//	
//	}
	
	public Labyrinth (){
		int AnzTrank = 3;
		int AnzTrank2 = 2;
		int AnzMonster = 4;
		bodenplatten = new Koordinaten [size*size];
		FloodFill auffuellen = new FloodFill(size);
		for (int i = 0; i < size ; i++){
			for (int j = 0 ; j< size ; j++){
				this.map [i][j] = auffuellen.map[i][j];
			}
		}
		
		// Speichere alle Felder die Boden sind in bodenplatten
		int zaehler = 0;
		for (int i = 0; i< size-1 ; i++){
			for (int j = 0 ; j< size-1 ; j++){
				
				if (map[i][j] == 1){
					bodenplatten[zaehler] = new Koordinaten (i, j);
					zaehler++;
				}
			}
		}
		// Zufaellige Position für den Charakter
		double zufallsZahl = (Math.random()*zaehler)-1;
		int zz1 = (int) zufallsZahl;
		map[bodenplatten[zz1].xKoordinate][bodenplatten[zz1].yKoordinate]= 2;
		
		// Eingangstuer immer ein Feld hinter der Spielerposition
				if(map[bodenplatten[zz1].xKoordinate-1][bodenplatten[zz1].yKoordinate] == 1){
					map[bodenplatten[zz1].xKoordinate-1][bodenplatten[zz1].yKoordinate] = 6;
				} else if(map[bodenplatten[zz1].xKoordinate+1][bodenplatten[zz1].yKoordinate] == 1){
					map[bodenplatten[zz1].xKoordinate+1][bodenplatten[zz1].yKoordinate] = 6;
				} else if(map[bodenplatten[zz1].xKoordinate][bodenplatten[zz1].yKoordinate-1] == 1){
					map[bodenplatten[zz1].xKoordinate][bodenplatten[zz1].yKoordinate-1] = 6;
				} else if(map[bodenplatten[zz1].xKoordinate][bodenplatten[zz1].yKoordinate+1] == 1){
					map[bodenplatten[zz1].xKoordinate][bodenplatten[zz1].yKoordinate+1] = 6;
				}
				
				//Position Schlüssel
				zufallsZahl = (Math.random()*zaehler)-1;
				int zz2 = (int) zufallsZahl;
				while (zz2 == zz1){
					zufallsZahl = (Math.random()*zaehler)-1;
					zz2 = (int) zufallsZahl;
				}
				map[bodenplatten[zz2].xKoordinate][bodenplatten[zz2].yKoordinate]= 5;
				
				// Position  Tuer
				zufallsZahl = (Math.random()*zaehler)-1;
				int zz3 = (int) zufallsZahl;
				while (zz3 == zz1 || zz3 == zz2){
					zufallsZahl = (Math.random()*zaehler)-1;
					zz3 = (int) zufallsZahl;
				}
				map[bodenplatten[zz3].xKoordinate][bodenplatten[zz3].yKoordinate]= 6;
				
				// 	Zugaellige Position der Monster
				int zz4 = (int)(Math.random()*zaehler)-1;;
				for(int i = 0; i <= AnzMonster; i++){
				zufallsZahl = (Math.random()*zaehler)-1;
				zz4 = (int) zufallsZahl;
				while (zz4 == zz1 ||zz4 == zz2 || zz4 == zz3){
					zufallsZahl = (Math.random()*zaehler)-1;
					zz4 = (int) zufallsZahl;
				}
				map[bodenplatten[zz4].xKoordinate][bodenplatten[zz4].yKoordinate]= 3;
				}
				
				// 	Zugaellige Position der Tränke
				int zz5 = (int) zufallsZahl;
				for(int i = 0; i <= AnzTrank; i++){
					zufallsZahl = (Math.random()*zaehler)-1;
					while (zz5 == zz1 ||zz5 == zz2 || zz5 == zz3 || zz5 == zz4){
						zufallsZahl = (Math.random()*zaehler)-1;
						zz5 = (int) zufallsZahl;
					}
					map[bodenplatten[zz5].xKoordinate][bodenplatten[zz5].yKoordinate]= 4;
					}
				//	Zugaellige Position der Tränke
					for(int i = 0; i <= AnzTrank2; i++){
					zufallsZahl = (Math.random()*zaehler)-1;
					int zz6 = (int) zufallsZahl;
					while (zz6 == zz1 ||zz6 == zz2 || zz6 == zz3 || zz6 == zz4 || zz6 == zz5){
						zufallsZahl = (Math.random()*zaehler)-1;
						zz5 = (int) zufallsZahl;
					}
					map[bodenplatten[zz5].xKoordinate][bodenplatten[zz5].yKoordinate]= 7;
					}
				
		
//		// Zugaellige Position des Monsters
//		zufallsZahl = (Math.random()*zaehler)-1;
//		int zz2 = (int) zufallsZahl;
//		while (zz2 == zz1){
//			zufallsZahl = (Math.random()*zaehler)-1;
//			zz2 = (int) zufallsZahl;
//		}
//		map[bodenplatten[zz2].xKoordinate][bodenplatten[zz2].yKoordinate]= 3;
//		//Position Schlüssel
//		zufallsZahl = (Math.random()*zaehler)-1;
//		int zz3 = (int) zufallsZahl;
//		while (zz3 == zz1 || zz3 == zz2){
//			zufallsZahl = (Math.random()*zaehler)-1;
//			zz2 = (int) zufallsZahl;
//		}
//		map[bodenplatten[zz3].xKoordinate][bodenplatten[zz3].yKoordinate]= 5;
//		// Position  Tuer
//		zufallsZahl = (Math.random()*zaehler)-1;
//		int zz4 = (int) zufallsZahl;
//		while (zz4 == zz1 || zz4 == zz2 || zz4 == zz3){
//			zufallsZahl = (Math.random()*zaehler)-1;
//			zz2 = (int) zufallsZahl;
//		}
//		map[bodenplatten[zz4].xKoordinate][bodenplatten[zz4].yKoordinate]= 6;
		
	
		
	}
	
}
