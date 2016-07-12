package pp2016.team13.server.map;

import pp2016.team13.server.map.Labyrinth;

/**
 * Groesse der Karte festlegen
 * 
 * @author <Akbulut, Cigdem, 5382718>
 * 
 */
public class Labyrinth {
	private int groesse = 19;
	public int[][] karte = new int[groesse][groesse];
	static Koordinaten[] bodenplatten;

	/**
	 * 
	 * bestimmt Anzahl Heiltraenke, Schutztraenke und Monster. 
	 * Anzahl Monster werden zufaellig bestimmt zwischen 1 und 5.
	 * 
	 * @author <Akbulut, Cigdem, 5382718>
	 * 
	 */
	public Labyrinth(int levelnummer) {
		int AnzHeiltrank = 2;
		int AnzSchutztrank = 1;
		int AnzMonster = (int) ((Math.random()) * 2 * levelnummer + 1);
		bodenplatten = new Koordinaten[groesse * groesse];
		/**
		 * 
		 * FloodFill fuellt die Karte auf
		 * 
		 * @author <Akbulut, Cigdem, 5382718>
		 */

		FloodFill auffuellen = new FloodFill(groesse);
		for (int i = 0; i < groesse - 1; i++) {
			for (int j = 0; j < groesse - 1; j++) {
				this.karte[i][j] = auffuellen.karte[i][j];

			}
		}
		/**
		 * 
		 * speichere alle Bodenelemente in bodenplatten
		 * 
		 * @author <Akbulut, Cigdem, 5382718>
		 */
		// Speichere alle Felder, die Boden sind in bodenplatten
		int zaehler = 0;
		for (int i = 0; i < groesse; i++) {
			for (int j = 0; j < groesse; j++) {
				if (karte[i][j] == 1) {
					bodenplatten[zaehler] = new Koordinaten(i, j);
					zaehler++;
				}
			}
		}
		/**
		 * 
		 * finde zufaellige Positionen fuer Spieler, Tuer,
		 * Heiltraenke,Schutztraenke, Monster und Schlessel
		 * 
		 * @author <Akbulut, Cigdem, 5382718>
		 */
		// Zufaellige Position fuer den Charakter
		double zufallsZahl = (Math.random() * zaehler) - 1;
		int zz1 = (int) zufallsZahl;
		karte[bodenplatten[zz1].xKoordinate][bodenplatten[zz1].yKoordinate] = 2;

		// Position Tuer
		int zz2 = (int) zufallsZahl;
		while (zz2 == zz1) {
			zufallsZahl = (Math.random() * zaehler) - 1;
			zz2 = (int) zufallsZahl;
		}
		karte[bodenplatten[zz2].xKoordinate][bodenplatten[zz2].yKoordinate] = 6;

		// Zufaellige Position der Heiltraenke
		int zz3 = (int) zufallsZahl;
		for (int i = 0; i <= AnzHeiltrank; i++) {
			zufallsZahl = (Math.random() * zaehler) - 1;
			while (zz3 == zz1 || zz3 == zz2) {
				zufallsZahl = (Math.random() * zaehler) - 1;
				zz3 = (int) zufallsZahl;
			}
			karte[bodenplatten[zz3].xKoordinate][bodenplatten[zz3].yKoordinate] = 4;
		}

		// Zufaellige Position der Schutztraenke
		int zz4 = (int) zufallsZahl;
		for (int i = 0; i <= AnzSchutztrank; i++) {
			zufallsZahl = (Math.random() * zaehler) - 1;
			while (zz4 == zz1 || zz4 == zz2 || zz4 == zz3) {
				zufallsZahl = (Math.random() * zaehler) - 1;
			}
			karte[bodenplatten[zz4].xKoordinate][bodenplatten[zz4].yKoordinate] = 7;
		}

		// Zufaellige Position der Monster
		int zz5 = (int) (Math.random() * zaehler) - 1;
		;
		for (int i = 0; i <= AnzMonster; i++) {
			zufallsZahl = (Math.random() * zaehler) - 1;
			while (zz5 == zz1 || zz5 == zz2 || zz5 == zz3 || zz5 == zz4) {
				zufallsZahl = (Math.random() * zaehler) - 1;
			}
			karte[bodenplatten[zz5].xKoordinate][bodenplatten[zz5].yKoordinate] = 3;
		}

		// Zufaellige Position des Schluessels
		int zz6 = (int) (Math.random() * zaehler) - 1;
		;
		zufallsZahl = (Math.random() * zaehler) - 1;
		while (zz6 == zz1 || zz6 == zz2 || zz6 == zz3 || zz6 == zz4 || zz6 == zz5) {
			zufallsZahl = (Math.random() * zaehler) - 1;
			zz6 = (int) ((Math.random() * zaehler) - 1);
		}

		karte[bodenplatten[zz6].xKoordinate][bodenplatten[zz6].yKoordinate] = 5;

	}

}
