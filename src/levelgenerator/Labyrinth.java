package levelgenerator;

public class Labyrinth {
	private int size = 17;
	public int [][] map = new int [size][size];
	static Koordinaten [] bodenplatten;
	
	
	public static void main (String[] args){
		Labyrinth karte = new Labyrinth();
	}
	
	public Labyrinth (){
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
		// Zugaellige Position des Monsters
		zufallsZahl = (Math.random()*zaehler)-1;
		int zz2 = (int) zufallsZahl;
		while (zz2 == zz1){
			zufallsZahl = (Math.random()*zaehler)-1;
			zz2 = (int) zufallsZahl;
		}
		map[bodenplatten[zz2].xKoordinate][bodenplatten[zz2].yKoordinate]= 3;
		//Position Schlüssel
		zufallsZahl = (Math.random()*zaehler)-1;
		int zz3 = (int) zufallsZahl;
		while (zz3 == zz1 || zz3 == zz2){
			zufallsZahl = (Math.random()*zaehler)-1;
			zz2 = (int) zufallsZahl;
		}
		map[bodenplatten[zz3].xKoordinate][bodenplatten[zz3].yKoordinate]= 5;
		// Position  Tuer
		zufallsZahl = (Math.random()*zaehler)-1;
		int zz4 = (int) zufallsZahl;
		while (zz4 == zz1 || zz4 == zz2 || zz4 == zz3){
			zufallsZahl = (Math.random()*zaehler)-1;
			zz2 = (int) zufallsZahl;
		}
		map[bodenplatten[zz4].xKoordinate][bodenplatten[zz4].yKoordinate]= 6;
		
		
	}
	
}
