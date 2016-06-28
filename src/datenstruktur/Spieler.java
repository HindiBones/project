package datenstruktur;

import gui.HindiBones;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Spieler extends Figur {

	private String name;
	
	private boolean hatSchluessel;
	private int anzahlHeiltraenke;
	private int heiltrankWirkung = 50;

	private HindiBones fenster;

	public Spieler(int id, HindiBones f) {
		
		setAnzahlHeiltraenke(0);
		setPos(0, 0);
		setHealth(100);
		setMaxHealth(getHealth());
		setName("Hindi Bones");
		setID(id);
		this.fenster = f;
		// Bild fuer den Spieler laden
		String imgDatei = "img//spieler.png";
		
		try {
			setImage(ImageIO.read(new File(imgDatei)));
		} catch (IOException e) {
			System.err.print("Das Bild " + imgDatei
					+ " konnte nicht geladen werden.");
		}
	}

	// Methode, um den Schluessel aufzuheben
	public void nimmSchluessel() {
		hatSchluessel = true;
	}

	// Methode, um den Schluessel zu entfernen
	public void entferneSchluessel() {
		hatSchluessel = false;
	}

	public int benutzeHeiltrank() {
		setAnzahlHeiltraenke(anzahlHeiltraenke - 1);
		return heiltrankWirkung;
	}

	public void nimmHeiltrank() {
		anzahlHeiltraenke++;
	}

	public void setAnzahlHeiltraenke(int anzahl) {
		if (anzahl >= 0)
			anzahlHeiltraenke = anzahl;
	}

	public int getAnzahlHeiltraenke() {
		return anzahlHeiltraenke;
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
			boolean kannAngreifen = false;
			if (m.getTyp() == 0)
				kannAngreifen = true;
			if (m.getTyp() == 1)
				kannAngreifen = hatSchluessel;

			if ((Math.sqrt(Math.pow(getXPos() - m.getXPos(), 2)
					+ Math.pow(getYPos() - m.getYPos(), 2)) < 1)
					&& kannAngreifen) {
				return m;
			}
		}

		return null;
	}

}
