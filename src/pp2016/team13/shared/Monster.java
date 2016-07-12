package pp2016.team13.shared;

import pp2016.team13.client.gui.HindiBones;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

/**
 * Klasse Monster, die sowohl auf Server, als auch auf Client Seite alle
 * Informationen speichert
 * 
 * @author <Fiehn, Marius, 6024602>
 *
 */
public class Monster extends Figur {

	private long lastAttack, lastStep;
	private int cooldownAttack, cooldownWalk, id;
	public int lebenspunkte, monsterSchaden, posX, posY;
	private boolean hatSchluessel;
	private int dir; // Laufrichtung: 0 Nord, 1 Ost, 2 Sued, 3 West
	private int typ; // Von Beginn an anwesend: 0, Erscheint spaeter:
	private HindiBones fenster;
	private Spieler spieler;

	public Monster(int x, int y, HindiBones fenster, int typ) {
		if (typ == 0) {
			hatSchluessel = false;
		} else {
			hatSchluessel = true;
		}
		this.fenster = fenster;
		this.spieler = fenster.spieler;
		this.typ = typ;
		setPos(x, y);
		setLebenspunkte(32);
		setMaxHealth(getLebenspunkte());
		lastAttack = System.currentTimeMillis();
		lastStep = System.currentTimeMillis();
		cooldownAttack = 500 - 10 * fenster.levelnummer; // ms
		cooldownWalk = 1000;
		setSchaden(5 + fenster.levelnummer * 2);
		Random r = new Random();
		changeDir();
		// Bild fuer das Monster laden
		int i = r.nextInt(3) + 1;
		try {
			setImage(ImageIO.read(new File("img//drache" + i + ".png")));
		} catch (IOException e) {
			System.err.print("Das Bild drache.png konnte nicht geladen werden.");
		}
	}

	/**
	 * Monster Konstruktor fuer die Server Seite
	 * 
	 * @author <Fiehn, Marius, 6024602>
	 * 
	 * @param id
	 *            speichert die Monster ID in einem Integer
	 * @param anfangsLebenspunkte
	 *            speichert die anfaenglichen Lebenspunkte des Monsters
	 * @param anfangsSchaden
	 *            speichert den anfaenglichen Schaden des Monsters
	 * @param schluessel
	 *            speichert, ob das Monster einen Schluessel traegt
	 * @param anfangsX
	 *            speichert die anfangs x-Koordinate
	 * @param anfangsY
	 *            speichert die anfangs y-Koordinate
	 */
	public Monster(int id, int anfangsLebenspunkte, int anfangsSchaden, boolean schluessel, int anfangsX,
			int anfangsY) {
		this.id = id;
		this.lebenspunkte = anfangsLebenspunkte;
		this.monsterSchaden = anfangsSchaden;
		hatSchluessel = schluessel;
		posX = anfangsX;
		posY = anfangsY;
	}

	public boolean attackiereSpieler(boolean hatSchluessel) {
		// Ist der Spieler im Radius des Monsters?
		boolean spielerImRadius = (Math
				.sqrt(Math.pow(spieler.getXPos() - getXPos(), 2) + Math.pow(spieler.getYPos() - getYPos(), 2)) < 2);

		// Kann das Monster angreifen?
		boolean kannAngreifen = false;
		if (typ == 0)
			kannAngreifen = ((System.currentTimeMillis() - lastAttack) >= cooldownAttack);
		if (typ == 1)
			kannAngreifen = ((System.currentTimeMillis() - lastAttack) >= cooldownAttack);

		if (spielerImRadius && kannAngreifen && !spieler.istUnverwundbar() && spieler.getLebenspunkte() > 0) {
			lastAttack = System.currentTimeMillis();
			spieler.lebenAendern(-getSchaden());
		}
		return spielerImRadius;
	}

	public void lebenAendern(int change) {
		super.lebenAendern(change);
		if (getLebenspunkte() <= 0) {
			if (hatSchluessel)
				fenster.level.setLevelInhalt(getXPos(), getYPos(), 8);
			fenster.monsterListe.remove(this);
		}
	}

	public double cooldownProzent() {
		return 1.0 * (System.currentTimeMillis() - lastAttack) / cooldownAttack;
	}

	// Bewege das Monster
	public void move() {
		boolean nextWalk = (System.currentTimeMillis() - lastStep) >= cooldownWalk;
		if (zulaessig()) {
			if (nextWalk) {
				switch (dir) {
				case 0:
					hoch();
					break;
				case 1:
					rechts();
					break;
				case 2:
					runter();
					break;
				case 3:
					links();
					break;
				}
				lastStep = System.currentTimeMillis();
			}
		} else {
			changeDir();
		}
	}

	// Laufrichtung des Monsters aendern
	public void changeDir() {
		Random random = new Random();
		dir = random.nextInt(4);
	}

	public int getTyp() {
		return typ;
	}

	// Pruefe, ob naechster Schritt zulaessig ist
	private boolean zulaessig() {
		if (dir == -1)
			return true;

		if (dir == 0 && getYPos() - 1 > 0) {
			return !(fenster.level.getBestimmtenLevelInhalt(getXPos(), getYPos() - 1) == 0)
					&& !(fenster.level.getBestimmtenLevelInhalt(getXPos(), getYPos() - 1) == 6)
					&& !(fenster.level.getBestimmtenLevelInhalt(getXPos(), getYPos() - 1) == 7)
					&& !(fenster.level.getBestimmtenLevelInhalt(getXPos(), getYPos() - 1) == 4);
		} else if (dir == 1 && getXPos() + 1 < fenster.WIDTH) {
			return !(fenster.level.getBestimmtenLevelInhalt(getXPos() + 1, getYPos()) == 0)
					&& !(fenster.level.getBestimmtenLevelInhalt(getXPos() + 1, getYPos()) == 6)
					&& !(fenster.level.getBestimmtenLevelInhalt(getXPos() + 1, getYPos()) == 7)
					&& !(fenster.level.getBestimmtenLevelInhalt(getXPos() + 1, getYPos()) == 4);
		} else if (dir == 2 && getYPos() + 1 < fenster.HEIGHT) {
			return !(fenster.level.getBestimmtenLevelInhalt(getXPos(), getYPos() + 1) == 0)
					&& !(fenster.level.getBestimmtenLevelInhalt(getXPos(), getYPos() + 1) == 6)
					&& !(fenster.level.getBestimmtenLevelInhalt(getXPos(), getYPos() + 1) == 7)
					&& !(fenster.level.getBestimmtenLevelInhalt(getXPos(), getYPos() + 1) == 4);
		} else if (dir == 3 && getXPos() - 1 > 0) {
			return !(fenster.level.getBestimmtenLevelInhalt(getXPos() - 1, getYPos()) == 0)
					&& !(fenster.level.getBestimmtenLevelInhalt(getXPos() - 1, getYPos()) == 6)
					&& !(fenster.level.getBestimmtenLevelInhalt(getXPos() - 1, getYPos()) == 7)
					&& !(fenster.level.getBestimmtenLevelInhalt(getXPos() - 1, getYPos()) == 4);
		} else
			return false;
	}

	// Serverseitige Setter und Getter

	/**
	 * setter Methode fuer die Monster ID
	 * 
	 * @author <Fiehn, Marius, 6024602>
	 * 
	 * @param id
	 *            speichert die uebergebene id als Monster ID
	 */
	public void setID(int id) {
		this.id = id;
	}

	/**
	 * Getter Methode fuer die Monster ID
	 * 
	 * @author <Fiehn, Marius, 6024602>
	 */
	public int getID() {
		return id;
	}

	/**
	 * Getter Methode fuer die gespeicherte y Koordinate
	 * 
	 * @author <Fiehn, Marius, 6024602>
	 * 
	 * @return gibt die y Position als int zurueck
	 */
	public int getPosY() {
		return posY;
	}

	/**
	 * Getter Methode fuer die gespeicherte x Koordinate
	 * 
	 * @author <Fiehn, Marius, 6024602>
	 * 
	 * @return gibt die x Koordinate des Monsters zurueck
	 */
	public int getPosX() {
		return posX;
	}

	/**
	 * Setter Methode fuer die einzuspeichernde x Koordinate
	 * 
	 * @author <Fiehn, Marius, 6024602>
	 * 
	 * @param x
	 *            die einzuspeichernde x Koordinate
	 */
	public void setPosX(int x) {
		posX = x;

	}

	/**
	 * Setter Methode fuer die einzuspeichernde y Koordinate
	 * 
	 * @author <Fiehn, Marius, 6024602>
	 * 
	 * @param y
	 *            die einzuspeichernde y Koordinate
	 */
	public void setPosY(int y) {
		posY = y;
	}

	/**
	 * Getter Methode fuer den Schluessel
	 * 
	 * @author <Fiehn, Marius, 6024602>
	 * 
	 * @return gibt einen boolean zurueck, der beinhaltet, ob das Monster den
	 *         Schluessel hat oder nicht
	 */
	public boolean hatSchluessel() {
		return hatSchluessel;
	}
}
