package pp2016.team13.shared;

/**
 * Level Klasse, die das gerade benutzte Level abspeichert
 * 
 * @author <Fiehn, Marius, 6024602>
 *
 */
public class Level {

	public int levelID;
	public int[][] levelInhalt;

	/**
	 * Konstruktor, der ein Level erstellt
	 * 
	 * @author <Fiehn, Marius, 6024602>
	 * 
	 * @param id
	 * @param level
	 */
	public Level(int id, int[][] level) {
		levelID = id;
		levelInhalt = level;
	}

	// kein Setter fuer die Level-ID benoetigt, da diese nicht mehr veraendert
	// werden soll

	/**
	 * getter-Methode fuer die Level-ID
	 * 
	 * @author <Fiehn, Marius, 6024602>
	 * 
	 * @return gibt die levelID als Integer zurueck
	 */
	public int getLevelID() {
		return levelID;
	}

	/**
	 * setter-Methode, um bestimmte Felder im Level zu veraendern
	 * 
	 * @author <Fiehn, Marius, 6024602>
	 * 
	 * @param x
	 *            x Koordinate des zu veraendernden Level-Inhalts
	 * @param y
	 *            y Koordinate des zu veraendernden Level-Inhalts
	 * @param inhalt
	 *            Der neue Inhalt des zu veraendernden Ortes
	 */
	public void setLevelInhalt(int x, int y, int inhalt) {
		levelInhalt[x][y] = inhalt;
	}

	/**
	 * getter-Methode, um das gesamte Level auszulesen
	 * 
	 * @author <Fiehn, Marius, 6024602>
	 * 
	 * @return gibt den Level-Inhalt als zweidimensionales int-Array zurueck
	 */
	public int[][] getKomplettesLevel() {
		return levelInhalt;
	}

	/**
	 * getter-Methode, um ein bestimmtes Feld auszulesen
	 * 
	 * @author <Fiehn, Marius, 6024602>
	 * 
	 * @param x
	 *            die x-Koordinate auf die zugegriffen werden soll
	 * @param y
	 *            die y-Koorindate auf die zugegriffen werden soll
	 * @return gibt den Inhalt einer bestimmten Koordinate als int zurueck
	 */
	public int getBestimmtenLevelInhalt(int x, int y) {
		return levelInhalt[x][y];
	}

	/**
	 * Methode, um Level auf der Konsole auszugeben
	 * 
	 * @author <Fiehn, Marius, 6024602>
	 */
	public void ausgabe() {
		for (int i = 0; i < levelInhalt.length; i++) {
			for (int j = 0; j < levelInhalt[0].length; j++) {
				System.out.print(levelInhalt[j][i]);
			}
			System.out.println();
		}
	}

	/**
	 * getter Methode fuer alle x-Werte
	 * 
	 * @author <Fiehn, Marius, 6024602>
	 * 
	 * @param yKoordinate
	 *            y Koordinate fuer die die x-Werte ausgegeben werden sollen
	 * @return gibt den Inhalt der x-Werte als eindimensionales Integer Array
	 *         zurueck
	 */
	@SuppressWarnings("null")
	public int[] getAlleXWerte(int yKoordinate) {
		int[] xWerte = null;
		for (int i = 0; i <= levelInhalt.length; i++) {
			xWerte[i] = levelInhalt[i][yKoordinate];
		}
		return xWerte;
	}

	/**
	 * getter Methode fuer alle y-Werte
	 * 
	 * @author <Fiehn, Marius, 6024602>
	 * 
	 * @param xKoordinate
	 *            x Koordinate fuer die die x-Werte ausgegeben werden sollen
	 * @return
	 */
	@SuppressWarnings("null")
	public int[] getAlleYWerte(int xKoordinate) {
		int[] yWerte = null;
		for (int i = 0; i <= levelInhalt.length; i++) {
			yWerte[i] = levelInhalt[i][xKoordinate];
		}
		return yWerte;
	}

	/**
	 * Laenge des Arrays in x-Richtung bestimmen
	 * 
	 * @author <Fiehn, Marius, 6024602>
	 * 
	 * @return gibt die Laenge als int zurueck
	 */
	public int getLaengeX() {
		return levelInhalt.length;
	}

	/**
	 * Laenge des Arrays in y-Richtung bestimmen
	 * 
	 * @author <Fiehn, Marius, 6024602>
	 * 
	 * @return gibt die Laenge als int zurueck
	 */
	public int getLaengeY() {
		return levelInhalt[0].length;
	}

	/**
	 * Methode, die den Inhalt des kompletten Levels zurueck gibt
	 * 
	 * @author <Fiehn, Marius, 6024602>
	 * 
	 * @return gibt den Inhalt des Levels als zweidimensionales int Array zurueck
	 */
	public int[][] getlevelInhalt() {
		return levelInhalt;
	}
}
