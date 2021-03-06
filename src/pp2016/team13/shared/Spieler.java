package pp2016.team13.shared;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import pp2016.team13.client.gui.*;

/**
 * Spieler-Klasse, die alle Merkmale des Charakters enthaelt Vorlage war
 * gegeben.
 * 
 * @author <unbekannt>
 * @author <Braun, Jan Julius, 6000100>
 * @author Keser, Seyma, 5979919
 *
 */
public class Spieler extends Figur {

	private String name;
	public String bildpfad;
	private boolean hatSchluessel, unverwundbar;
	public boolean trankAktiv;
	private int anzahlHeiltraenke;
	public int anzahlTrank;
	private int heiltrankWirkung = 50;

	public HindiBones fenster;

	public Spieler(int id) {

		setAnzahlHeiltraenke(0);
		setAnzahlTrank(0);
		setPos(0, 0);
		setLebenspunkte(100);
		setMaxHealth(getLebenspunkte());
		setName("Hindi Bones");
		setID(id);

	}

	// Methode, um den Schluessel aufzuheben
	public void nimmSchluessel() {
		hatSchluessel = true;
	}

	// Methode, um den Schluessel zu entfernen
	public void entferneSchluessel() {
		hatSchluessel = false;
	}

	/**
	 * Macht einen Spieler (un-)verwundbar
	 * 
	 * @param zustand
	 *            : true, wenn Spieler unverwundbar werden soll, false, wenn
	 *            nicht
	 * 
	 * @author <Braun, Jan Julius, 6000100>
	 */
	public void setUnverwundbar(boolean zustand) {
		this.unverwundbar = zustand;
	}

	/**
	 * Gibt zurueck, ob der Spieler unverwundbar ist
	 * 
	 * @return: Gibt zurueck, ob der Spieler unverwundbar ist
	 * 
	 * @author <Braun, Jan Julius, 6000100>
	 */
	public boolean istUnverwundbar() {
		return this.unverwundbar;
	}

	/**
	 * Benutzt einen Schutztrank
	 * 
	 * @return: Zeit, zu der der Trank benutzt wurde
	 * 
	 * @author <Braun, Jan Julius, 6000100>
	 */
	public long benutzeTrank() {
		if (anzahlTrank > 0) {
			this.setUnverwundbar(true);
			trankAktiv = true;
			anzahlTrank--;
			return System.currentTimeMillis();
		}
		return 0;
	}

	public int benutzeHeiltrank() {
		if (anzahlHeiltraenke > 0) {
			setAnzahlHeiltraenke(anzahlHeiltraenke - 1);
			return heiltrankWirkung;
		} else
			return 0;
	}

	public void nimmHeiltrank() {
		anzahlHeiltraenke++;
	}

	/**
	 * Gibt dem Spieler einen Schutztrank
	 * 
	 * @author <Braun, Jan Julius, 6000100>
	 * 
	 */
	public void nimmtrank() {
		anzahlTrank++;
	}

	public void setAnzahlHeiltraenke(int anzahl) {
		if (anzahl >= 0)
			anzahlHeiltraenke = anzahl;
	}

	/**
	 * Legt die Anzahl der Schutztraenke des Spielers fest
	 * 
	 * @param anzahl2
	 *            : Die neue Anzahl an Schutztraenken
	 * 
	 * @author <Braun, Jan Julius, 6000100>
	 * 
	 */
	public void setAnzahlTrank(int anzahl2) {
		if (anzahl2 >= 0) {
			anzahlTrank = anzahl2;
		}

	}

	public int getAnzahlHeiltraenke() {
		return anzahlHeiltraenke;
	}

	/**
	 * Gibt die Anzahl der Schutztraenke zurueck
	 * 
	 * @return: Anzahl der Schutztraenke des Spielers
	 * 
	 * @author <Braun, Jan Julius, 6000100>
	 * 
	 */
	public int getAnzahlTrank() {
		return anzahlTrank;
	}

	// Hat der Spieler den Schluessel?
	public boolean hatSchluessel() {
		return hatSchluessel;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Monster angriffsMonster() {
		for (int i = 0; i < fenster.monsterListe.size(); i++) {
			Monster m = fenster.monsterListe.get(i);

			// Kann der Spieler angreifen?
			if ((Math.sqrt(Math.pow(getXPos() - m.getXPos(), 2) + Math.pow(getYPos() - m.getYPos(), 2)) < 1)) {
				return m;
			}
		}

		return null;
	}

	/**
	 * Legt das Fenster, auf dem der Spieler spielt, fest
	 * 
	 * @param f
	 *            : Das HindiBones-Objekt, auf dem der Spieler spielt
	 * 
	 * @author Keser, Seyma, 5979919
	 */
	public void setFenster(HindiBones f) {
		this.fenster = f;
		String imgDatei;
		// Bild fuer den Spieler laden
		if (fenster.zahl == 0) {
			imgDatei = "img/John2.png";

			try {
				setImage(imgDatei);
			} catch (IOException e) {
				System.err.print("Das Bild " + imgDatei + " konnte nicht geladen werden.");
			}
		} else if (fenster.zahl == 1) {

			imgDatei = "img/John1.png";

			try {
				setImage(imgDatei);
			} catch (IOException e) {
				System.err.print("Das Bild " + imgDatei + " konnte nicht geladen werden.");
			}
		}
	}

	@Override
	public void lebenAendern(int change) {
		if (!unverwundbar)
			lp = Math.min(lp + change, getMaxHealth());
	}

	public void setImage(String img) throws IOException {
		bildpfad = img;
		this.image = ImageIO.read(new File(img));

		if (img.equals("img//red_point.png"))
			image = image.getScaledInstance(10, 10, Image.SCALE_DEFAULT);
		else
			image = image.getScaledInstance(72, 72, Image.SCALE_DEFAULT);
	}

	/**
	 * Wechselt die Bilder der Spielfigur von normal zu blau und umgekehrt
	 * 
	 * @author <Braun, Jan Julius, 6000100>
	 */
	public void bildWechseln() {

		try {
			if (this.bildpfad.equals("img//John2.png")) {
				this.setImage("img//Johnblauvor1.png");
			} else if (this.bildpfad.equals("img//John3.png")) {
				this.setImage("img//Johnblauvor1.png");
			} else if (this.bildpfad.equals("img//John4.png")) {
				this.setImage("img//Johnblauvor2.png");
			} else if (this.bildpfad.equals("img//JohnLinks.png")) {
				this.setImage("img//JohnblauLi1.png");
			} else if (this.bildpfad.equals("img//JohnLinks2.png")) {
				this.setImage("img//JohnblauLi2.png");
			} else if (this.bildpfad.equals("img//JohnSeite.png")) {
				this.setImage("img//JohnblauRe1.png");
			} else if (this.bildpfad.equals("img//JohnSeite2.png")) {
				this.setImage("img//JohnblauRe2.png");
			} else if (this.bildpfad.equals("img//John3hinten.png")) {
				this.setImage("img//Johnblauhinten.png");
			} else if (this.bildpfad.equals("img//Johnblauvor1.png")) {
				this.setImage("img//John3.png");
			} else if (this.bildpfad.equals("img//Johnblauvor2.png")) {
				this.setImage("img//John4.png");
			} else if (this.bildpfad.equals("img//Johnblauhinten.png")) {
				this.setImage("img//John3hinten.png");
			} else if (this.bildpfad.equals("img//JohnblauRe1.png")) {
				this.setImage("img//JohnSeite.png");
			} else if (this.bildpfad.equals("img//JohnblauRe2.png")) {
				this.setImage("img//JohnSeite2.png");
			} else if (this.bildpfad.equals("img//JohnblauLi1.png")) {
				this.setImage("img//JohnLinks.png");
			} else if (this.bildpfad.equals("img//JohnblauLi2.png")) {
				this.setImage("img//JohnLinks2.png");
			} else {
				System.err.println("Du hast ein Bild vergessen!");
			}
		} catch (IOException ioe) {
			System.err.println("Bildwechsel fehlgeschlagen!");
		}

	}

}
