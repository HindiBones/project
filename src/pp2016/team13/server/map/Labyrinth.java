package pp2016.team13.server.map;

import pp2016.team13.server.map.Labyrinth;

public class Labyrinth {
	private int size = 17;
	public int [][] map = new int [size][size];
	static Koordinaten [] bodenplatten;
	static Koordinaten [] monsterplatten;
	
	
//	public static void main (String[] args){
//		Labyrinth karte = new Labyrinth();
//	
//	}
	
	public Labyrinth (){
		int AnzHeiltrank = 2;
		int AnzSchutztrank = 1;
		int AnzMonster = (int)((Math.random()) * 5 + 1);;
		bodenplatten = new Koordinaten [size*size];
		
		FloodFill auffuellen = new FloodFill(size-1);
		for (int i = 0; i < size-1 ; i++){
			for (int j = 0 ; j< size-1 ; j++){
				this.map [i][j] = auffuellen.map[i][j];
			}
		}
		
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
		// Zufaellige Position für den Charakter
		double zufallsZahl = (Math.random()*zaehler)-1;
		int zz1 = (int) zufallsZahl;
		map[bodenplatten[zz1].xKoordinate][bodenplatten[zz1].yKoordinate]= 2;
		

			// Position  Tuer
		// Eingangstuer immer ein Feld hinter der Spielerposition
//				if(map[bodenplatten[zz1].xKoordinate-1][bodenplatten[zz1].yKoordinate] == 1){
//					map[bodenplatten[zz1].xKoordinate-1][bodenplatten[zz1].yKoordinate] = 6;
//				} else if(map[bodenplatten[zz1].xKoordinate+1][bodenplatten[zz1].yKoordinate] == 1){
//					map[bodenplatten[zz1].xKoordinate+1][bodenplatten[zz1].yKoordinate] = 6;
//				} else if(map[bodenplatten[zz1].xKoordinate][bodenplatten[zz1].yKoordinate-1] == 1){
//					map[bodenplatten[zz1].xKoordinate][bodenplatten[zz1].yKoordinate-1] = 6;
//				} else if(map[bodenplatten[zz1].xKoordinate][bodenplatten[zz1].yKoordinate+1] == 1){
//					map[bodenplatten[zz1].xKoordinate][bodenplatten[zz1].yKoordinate+1] = 6;
//				}
				//Position Schlüssel
				zufallsZahl = (Math.random()*zaehler)-1;
				int zz2 = (int) zufallsZahl;
				while (zz2 == zz1){
					zufallsZahl = (Math.random()*zaehler)-1;
					zz2 = (int) zufallsZahl;
				}
				map[bodenplatten[zz2].xKoordinate][bodenplatten[zz2].yKoordinate]= 6;
				
			// 	Zufaellige Position der Heiltränke
				int zz3 = (int) zufallsZahl;
				for(int i = 0; i <= AnzHeiltrank; i++){
					zufallsZahl = (Math.random()*zaehler)-1;
					while (zz3 == zz1 ||zz3 == zz2){
						zufallsZahl = (Math.random()*zaehler)-1;
						zz3 = (int) zufallsZahl;
					}
					map[bodenplatten[zz3].xKoordinate][bodenplatten[zz3].yKoordinate]= 4;
					}
			//	Zufaellige Position der Schutztränke
					int zz4 = (int) zufallsZahl;
				for(int i = 0; i <= AnzSchutztrank; i++){
					zufallsZahl = (Math.random()*zaehler)-1;
					while (zz4 == zz1 ||zz4 == zz2 || zz4 == zz3){
						zufallsZahl = (Math.random()*zaehler)-1;
						zz4 = (int) zufallsZahl;
					}
					map[bodenplatten[zz4].xKoordinate][bodenplatten[zz4].yKoordinate]= 7;
					}
				
				//	 	Zufaellige Position der Monster
					int zz5 = (int)(Math.random()*zaehler)-1;;
					for(int i = 0; i <= AnzMonster; i++){
					zufallsZahl = (Math.random()*zaehler)-1;
					zz5 = (int) zufallsZahl;
					while (zz5 == zz1 ||zz5 == zz2 || zz5 == zz3|| zz5 == zz4){
						zufallsZahl = (Math.random()*zaehler)-1;
						zz5 = (int) zufallsZahl;
					}
					map[bodenplatten[zz5].xKoordinate][bodenplatten[zz5].yKoordinate]= 3;
					}
					
					// Speichere alle Felder die Monster sind in Monsterplatten
					monsterplatten = new Koordinaten [size*size];
					int zaehlerMonster = 0;
					for (int i = 0; i< size ; i++){
						for (int j = 0 ; j< size ; j++){
							if (map[i][j] == 3){
								monsterplatten[zaehlerMonster] = new Koordinaten (i, j);
								zaehlerMonster++;
							}
						}
					}
				//	Position Schlüssel
						zufallsZahl = (Math.random()*zaehlerMonster)-1;
						int zz6 = (int) zufallsZahl;
						map[monsterplatten[zz6].xKoordinate][monsterplatten[zz6].yKoordinate]= 5;
						
		
		
	
		
	}
	
	}
	
