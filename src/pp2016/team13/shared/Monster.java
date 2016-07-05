package pp2016.team13.shared;

import pp2016.team13.client.gui.HindiBones;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Monster extends Figur {

	private long lastAttack;
	private long lastStep;
	private int cooldownAttack;
	private int cooldownWalk;
	private int id;
	private int lebenspunkte;
	private int monsterSchaden;
	private boolean trankVorhanden;
	private int posX;
	private int posY;
	private int dir; // Laufrichtung: 0 Nord, 1 Ost, 2 Sued, 3 West
	private int typ; // Von Beginn an anwesend: 0, Erscheint sp�ter: 1

	private HindiBones fenster;
	private Spieler spieler;

	public Monster(int x, int y, HindiBones fenster, int typ) {
		this.fenster = fenster;
		this.spieler = fenster.spieler;
		this.typ = typ;
		setPos(x, y);
		setLebenspunkte(32);
		setMaxHealth(getLebenspunkte());
		lastAttack = System.currentTimeMillis();
		lastStep = System.currentTimeMillis();
		cooldownAttack = 500 - 10 * fenster.currentLevel; // ms
		cooldownWalk = 1000;

		setSchaden(5 + fenster.currentLevel * 2);
		Random r = new Random();
		changeDir();

		// Bild fuer das Monster laden
		int i = r.nextInt(3) + 1;

		try {
			setImage(ImageIO.read(new File("img//drache" + i + ".png")));
		} catch (IOException e) {
			System.err
					.print("Das Bild drache.png konnte nicht geladen werden.");
		}
	}
	
	public Monster(int id, int anfangsLebenspunkte, int anfangsSchaden, boolean initialisierungTrankVorhanden, int anfangsX, int anfangsY) throws IOException {
		 this.id = id;
		 this.lebenspunkte=anfangsLebenspunkte;
		 this.monsterSchaden=anfangsSchaden;
		 trankVorhanden=initialisierungTrankVorhanden;
		 posX=anfangsX;
		 posY=anfangsY;
		 
	 }

	public boolean attackiereSpieler(boolean hatSchluessel) {
		// Ist der Spieler im Radius des Monsters?
		boolean spielerImRadius = (Math.sqrt(Math.pow(spieler.getXPos()
				- getXPos(), 2)
				+ Math.pow(spieler.getYPos() - getYPos(), 2)) < 2);

		// Kann das Monster angreifen?
		boolean kannAngreifen = false;
		if (typ == 0)
			kannAngreifen = ((System.currentTimeMillis() - lastAttack) >= cooldownAttack);
		if (typ == 1)
			kannAngreifen = (hatSchluessel && ((System.currentTimeMillis() - lastAttack) >= cooldownAttack));

		if (spielerImRadius && kannAngreifen) {
			lastAttack = System.currentTimeMillis();
			spieler.changeHealth(-getSchaden());
		}
		return spielerImRadius;
	}

	public void changeHealth(int change) {
		super.changeHealth(change);
		if (getLebenspunkte() <= 0) {
			fenster.Level.setLevelInhalt(getXPos(), getYPos(), 3);;
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
			return !(fenster.Level.getBestimmtenLevelInhalt(getXPos(), getYPos()-1) == 0)
					&& !(fenster.Level.getBestimmtenLevelInhalt(getXPos(), getYPos()-1) == 6)
					&& !(fenster.Level.getBestimmtenLevelInhalt(getXPos(), getYPos()-1) == 7)
					&& !(fenster.Level.getBestimmtenLevelInhalt(getXPos(), getYPos()-1) == 4);
		} else if (dir == 1 && getXPos() + 1 < fenster.WIDTH) {
			return !(fenster.Level.getBestimmtenLevelInhalt(getXPos()+1, getYPos()) == 0)
					&& !(fenster.Level.getBestimmtenLevelInhalt(getXPos()+1, getYPos()) == 6)
					&& !(fenster.Level.getBestimmtenLevelInhalt(getXPos()+1, getYPos()) == 7)
					&& !(fenster.Level.getBestimmtenLevelInhalt(getXPos()+1, getYPos()) == 4);
		} else if (dir == 2 && getYPos() + 1 < fenster.HEIGHT) {
			return !(fenster.Level.getBestimmtenLevelInhalt(getXPos(), getYPos()+1) == 0)
					&& !(fenster.Level.getBestimmtenLevelInhalt(getXPos(), getYPos()+1) == 6)
					&& !(fenster.Level.getBestimmtenLevelInhalt(getXPos(), getYPos()+1) == 7)
					&& !(fenster.Level.getBestimmtenLevelInhalt(getXPos(), getYPos()+1) == 4);
		} else if (dir == 3 && getXPos() -1 > 0) {
			return !(fenster.Level.getBestimmtenLevelInhalt(getXPos()-1, getYPos()) == 0)
					&& !(fenster.Level.getBestimmtenLevelInhalt(getXPos()-1, getYPos()) == 6)
					&& !(fenster.Level.getBestimmtenLevelInhalt(getXPos()-1, getYPos()) == 7)
					&& !(fenster.Level.getBestimmtenLevelInhalt(getXPos()-1, getYPos()) == 4);
		} else
			return false;
	}

	
	public void setID(int id)
	{
		this.id = id;
	}
	
	public int getID(){
		return id;
	}

	public int getPosY() {
		return posY;
	}

	public int getPosX() {
		return posY;
	}

	public void setPosX(int x) {
		posX = x;
		
	}

	public void setPosY(int y) {
		posY = y;
	}
}
