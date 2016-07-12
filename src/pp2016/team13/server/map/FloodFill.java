package pp2016.team13.server.map;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 
 * @author Cigdem
 * @param groesse:
 *            Spielfeldgroesse
 * @param karte:
 *            speichert integer
 * @param kRichtungHoch,
 *            kRichtungUnten, kRichtungRechts, kRichtungLinks:
 *            Integerzuordnungen fuer Vergleiche in welche Richtung
 * @param kDirFirst,
 *            kDirLast: Erste und Letzte Richtung
 * @param startpunkt:
 *            Startpunkt nicht gesetzt
 * @param zielposition:
 *            Zielpunkt nicht gesetzt
 * @param jetzigePosition:
 *            wird gebraucht wenn die Karte durchlaufen wird
 */

public class FloodFill {
	private int groesse;
	public int[][] karte;
	private static final int kRichtungHoch = 0;
	private static final int kRichtungUnten = 1;
	private static final int kRichtungRechts = 2;
	private static final int kRichtungLinks = 3;
	private static Point startpunkt = null;
	private static Point zielposition = null;
	private static Point jetzigePosition = null;

	// Level Inhalt : 0=Wand, 1 = Boden, 2 = Charakter, 3 = Monster, 4 =
	// Heiltrank, 5=Schluessel, 6 = Tuer,7 = Schutztrank

	/**
	 * FloodFill ruft sich selber auf und geht in die jeweilige Richtung
	 * 
	 * @author Cigdem
	 * @param size:
	 *            legt die Groesse des Labyrinths fest
	 */
	// Level Inhalt : 0=Wand, 1 = Boden, 2 = Charakter, 3 = Monster, 4 = Trank,
	// 5=Schluessel, 6 = Tuer,7 = Trank2
	FloodFill(int groesse) {
		this.groesse = groesse;
		Random zufall = new Random();
		karte = new int[groesse][groesse];
		floodFill(new Point(groesse / 2, groesse / 2), zufall);
		// finde freie Start- und Zielkoordinaten
		List<Point> frei = new ArrayList<Point>();
		for (int x = 0; x < groesse; x++)
			for (int y = 0; y < groesse; y++)
				if (karte[x][y] == 0)
					frei.add(new Point(x, y));
		if (frei.size() == 0)
			throw new RuntimeException("There are not free fields in the labyrinth");
		startpunkt = frei.get(zufall.nextInt(frei.size()));
		zielposition = frei.get(zufall.nextInt(frei.size()));
		jetzigePosition = startpunkt;
	}

	/**
	 * @author Cigdem
	 * @return: gibt die Groesse des Feldes zurueck
	 */
	public int getKartenGroesse() {
		return groesse;
	}

	/**
	 * @author Cigdem
	 * @return: gibt den Startpunkt zurueck
	 */
	public Point getStartpunkt() {
		return startpunkt;
	}

	/**
	 * @author Cigdem
	 * @return: gibt den Zielpunkt zurueck
	 */
	public Point getZielpunkt() {
		return zielposition;
	}

	/**
	 * @author Cigdem
	 * @return: gibt die jetzige Position zurueck
	 */
	public Point getJetzigePosition() {
		return jetzigePosition;
	}

	/**
	 * @author Cigdem
	 * @param x
	 *            x Koordinate
	 * @param y
	 *            y Koordinate
	 * @return gibt den zugehoerigen int Wert zurueck
	 */
	public int getFeldBlockiert(int x, int y) {
		return karte[x][y];
	}

	/**
	 * @author Cigdem
	 * 
	 * @param p
	 *            Punkt p
	 * @return ueberpruefung, ob der Punkt auf der Karte ist, je nachdem wird
	 *         ein Boolean zurueck gegeben
	 */
	private boolean aufKarte(Point p) {
		return p.x >= 0 && p.x < groesse && p.y >= 0 && p.y < groesse;
	}

	/**
	 * setzt die Richtung
	 * 
	 * @param p:aktuelle
	 *            Punkt
	 * @param direction:gesetzte
	 *            Richtung
	 * 
	 */
	public Point setzeRichtung(Point p, int richtung) {
		switch (richtung) {
		case kRichtungHoch:
			return new Point(p.x, p.y - 1);
		case kRichtungUnten:
			return new Point(p.x, p.y + 1);
		case kRichtungRechts:
			return new Point(p.x + 1, p.y);
		case kRichtungLinks:
			return new Point(p.x - 1, p.y);
		default:
			throw new IllegalArgumentException("Richtung nicht moeglich");
		}
	}

	/**
	 * Geht zufaellig in eine Richtung bis der Zielpunkt erreicht ist
	 * 
	 * @param punkt
	 * @param zufall
	 */
	private void floodFill(Point punkt, Random zufall) {
		int zufall1, zufall2, zufall3, zufall4;
		zufall1 = zufall.nextInt(4);
		zufall2 = zufall1;
		zufall3 = zufall1;
		zufall4 = zufall1;
		while (zufall1 == zufall2) {
			zufall2 = zufall.nextInt(4);
		}
		while (zufall1 == zufall3 || zufall2 == zufall3) {
			zufall3 = zufall.nextInt(4);
		}
		while (zufall1 == zufall4 || zufall2 == zufall4 || zufall3 == zufall4) {
			zufall4 = zufall.nextInt(4);
		}
		karte[punkt.x][punkt.y] = 1;

		for (int i = 0; i < 4; i++) {

			if (i == zufall1 && aufKarte(new Point(punkt.x, punkt.y - 2)) && karte[punkt.x][punkt.y - 2] == 0
					&& karte[punkt.x][punkt.y - 1] == 0) {
				karte[punkt.x][punkt.y - 1] = 1;
				floodFill(new Point(punkt.x, punkt.y - 2), zufall);
			}
			if (i == zufall2 && aufKarte(new Point(punkt.x, punkt.y + 2)) && karte[punkt.x][punkt.y + 2] == 0
					&& karte[punkt.x][punkt.y + 1] == 0) {
				karte[punkt.x][punkt.y + 1] = 1;
				floodFill(new Point(punkt.x, punkt.y + 2), zufall);
			}
			if (i == zufall3 && aufKarte(new Point(punkt.x - 2, punkt.y)) && karte[punkt.x - 2][punkt.y] == 0
					&& karte[punkt.x - 1][punkt.y] == 0) {
				karte[punkt.x - 1][punkt.y] = 1;
				floodFill(new Point(punkt.x - 2, punkt.y), zufall);
			}
			if (i == zufall4 && aufKarte(new Point(punkt.x + 2, punkt.y)) && karte[punkt.x + 2][punkt.y] == 0
					&& karte[punkt.x + 1][punkt.y] == 0) {
				karte[punkt.x + 1][punkt.y] = 1;
				floodFill(new Point(punkt.x + 2, punkt.y), zufall);
			}
		}

	}

}
