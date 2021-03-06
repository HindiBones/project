package pp2016.team13.client.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.LinkedList;

import javax.accessibility.Accessible;
import javax.swing.*;

import pp2016.team13.client.comm.Paket;
import pp2016.team13.client.engine.NachrichtenVerwaltung;
import pp2016.team13.shared.Level;
import pp2016.team13.shared.Monster;
import pp2016.team13.shared.Spieler;
import pp2016.team13.shared.Nachrichten.AusloggenNachricht;

/**
 * Hindi Bones Fenster auf dem das ganze Spiel laueft plus die
 * Hauptschnittstelle zum Client
 * 
 * @author <Keser, Seyma, 5979919>
 *
 */
public class HindiBones extends JFrame implements KeyListener, MouseListener, Accessible {

	private static final long serialVersionUID = 1L;

	Spielflaeche spielflaeche;
	private SeitenLeiste seitenleiste;
	private Highscore highscore;
	private MenuLeiste menuLeiste;
	private Steuerung steuerung;
	private Anmeldung anmeldung;
	private long trankTimer;

	public LinkedList<Monster> monsterListe;
	public Spieler spieler;
	public Spieler spieler2;
	public Spieler testspieler;
	public Monster monster;

	public Level level;
	public NachrichtenVerwaltung client;
	public int levelnummer = 0;
	public boolean spielende = false;
	public boolean verloren = false;
	public boolean spielfeldSichtbar = false;
	public long startZeit;
	public int benoetigteZeit;
	public boolean nebelAn = true;
	private boolean spielerInHighscore = false;
	public boolean highscoreAngezeigt = false;
	public boolean steuerungAngezeigt = false;
	public boolean anmeldeanzeige = false;
	public boolean levelNeustarten = false;

	public final int MAXLEVEL = 5;
	public final int WIDTH = 19; // Labyrinth grosse
	public final int HEIGHT = 19;
	public final int BOX = 72; // Grosse der Bild Elemente
	public final int BOX2 = 9;
	public final int BOX3 = 32;
	public int zahl = 0;
	public int timer = 0;

	String bn;
	String pw;

	/**
	 * Das Spiel Fenster wird erzeugt
	 * 
	 * @author Keser, Seyma, 5979919
	 * @param dicke
	 *            : Die Breite des Fensters
	 * @param hoehe
	 *            : Die Hoehe des Fensters
	 * @param titel:
	 *            Name des Fensters
	 */
	public HindiBones(int dicke, int hoehe, String titel) {

		initialisiereJFrame(dicke, hoehe, titel);
		// Verbindung zum Client
		client = new NachrichtenVerwaltung(this);
		client.spieler = spieler;
		// initialisieren des Fensters
		starteNeuesSpiel();

	}

	/**
	 * Ergaenzt um Minimap/Seitenstatus und der Anmeldung Entfernt Alter
	 * Statusleiste
	 * 
	 * @author Keser, Seyma, 5979919
	 * @author <unbekannt>
	 * @param dicke
	 *            : Die Breite des Fensters
	 * @param hoehe
	 *            : Die Hoehe des Fensters
	 * @param titel
	 *            : Name des Fensters
	 */
	public void initialisiereJFrame(int dicke, int hoehe, String titel) {

		this.setLayout(new BorderLayout());
		level = new Level(-1, null);
		this.spielflaeche = new Spielflaeche(this);
		this.seitenleiste = new SeitenLeiste(this);
		this.steuerung = new Steuerung();
		this.highscore = new Highscore();
		// Erzeuge Menuleiste
		this.menuLeiste = new MenuLeiste(this);
		this.anmeldung = new Anmeldung(this);

		// Es wird die gewuenschte Groesse angegeben
		spielflaeche.setPreferredSize(new Dimension(dicke + 5, hoehe + 5));
		seitenleiste.setPreferredSize(new Dimension(dicke / 3 + 4, BOX)); // Groessee
																			// der
																			// Seitenleiste
		steuerung.setPreferredSize(new Dimension(dicke + 50, hoehe - 50));
		highscore.setPreferredSize(new Dimension(dicke + 50, hoehe - 50));
		anmeldung.setPreferredSize(new Dimension(640, 400));

		// Es wird die gewuenschte Groesse angegeben
		spielflaeche.setPreferredSize(new Dimension(dicke + 5, hoehe + 5));
		seitenleiste.setPreferredSize(new Dimension(dicke / 3 + 4, BOX)); // Groesse
																			// meiner
																			// Seitenleiste
		steuerung.setPreferredSize(new Dimension(dicke + 50, hoehe - 50));
		highscore.setPreferredSize(new Dimension(dicke + 50, hoehe - 50));
		anmeldung.setPreferredSize(new Dimension(640, 400));

		// Das Startfenster soll die Anmeldung sein
		zeigeAnmeldung();

		// Zentriere das Fenster auf dem Bildschirm
		final Dimension d = this.getToolkit().getScreenSize();
		this.setLocation((int) ((d.getWidth() - this.getWidth()) / 2), (int) ((d.getHeight() - this.getHeight()) / 2)); // Setzt
																														// die
																														// Position
																														// auf
																														// dem
																														// Bildschirm
																														// fest

		// Anbindung an Listener
		this.addKeyListener(this);
		spielflaeche.addMouseListener(this);
		// Standardsetup
		this.setResizable(false);
		this.setTitle(titel);
		this.setVisible(true);
		this.addWindowListener(exitListener);

	}

	/**
	 * Ergaenzt / Verbessert durch mein Seitentool(Minimap) entfernen der
	 * vorherigen Statusleiste
	 * 
	 * @author Keser, Seyma, 5979919
	 */
	public void zeigeSpielfeld() {

		// entferne alles
		highscoreAngezeigt = false;
		steuerungAngezeigt = false;
		spielfeldSichtbar = true;
		this.remove(steuerung);
		this.remove(anmeldung);
		this.remove(highscore);

		// erstelle das Spielfeld
		this.add(spielflaeche, BorderLayout.CENTER);
		this.add(seitenleiste, BorderLayout.EAST);
		this.add(menuLeiste, BorderLayout.NORTH);

		// aktiviere das fertige Spielfeld
		this.requestFocus();
		this.pack();
	}

	/**
	 * Sendet Nachricht ueber Client zum Server das, dass das Spiel geschlossen
	 * werden soll
	 * 
	 * @author Keser, Seyma, 5979919
	 */
	WindowListener exitListener = new WindowAdapter() {
		@Override
		public void windowClosing(WindowEvent e) {
			if (client.socket.cs.isBound()) {
				client.socket.sendeLogout(new Paket(new AusloggenNachricht()));
			}
			System.exit(0);
		}
	};

	/**
	 * Ergaenzt um einige Elemente Anzeigen des Highscores
	 * 
	 * @author Keser, Seyma, 5979919
	 */
	public void zeigeHighscore() {
		// entferne alles
		highscoreAngezeigt = true;
		steuerungAngezeigt = false;
		this.remove(spielflaeche);
		this.remove(seitenleiste);
		this.remove(steuerung);

		// erstelle die Highscoreanzeige
		this.add(highscore, BorderLayout.CENTER);

		// aktiviere die Highscoreanzeige
		this.requestFocus();

		this.pack();
		highscore.repaint();
	}

	/**
	 * Ergaenzt um Seitenleiste
	 * 
	 * @author Keser, Seyma, 5979919
	 */
	public void zeigeSteuerung() {
		// entferne alles
		steuerungAngezeigt = true;
		highscoreAngezeigt = false;

		this.remove(seitenleiste);
		this.remove(spielflaeche);
		this.remove(highscore);

		// erstelle die Steuerungsanzeige
		this.add(steuerung, BorderLayout.CENTER);

		// aktiviere die Steuerungsanzeige
		this.requestFocus();
		this.pack();
		steuerung.repaint();
	}

	/**
	 * Erzaenzt um Seitenleiste Methode zum Anzeigen des Anmelde Panels +
	 * Verbindung zum Client wird aufgebaut
	 * 
	 * @author Seyma Keser
	 */
	public void zeigeAnmeldung() {
		steuerungAngezeigt = false;
		highscoreAngezeigt = false;

		this.remove(seitenleiste);
		this.remove(spielflaeche);
		this.remove(highscore);
		this.remove(steuerung);
		try {
			Thread.sleep(50);
			this.add(anmeldung, BorderLayout.CENTER);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		this.requestFocus();
		this.pack();
		anmeldung.repaint();

	}

	/**
	 * Getter fuer Seitenleiste(mein komplettes Seitentool)
	 * 
	 * @author <Keser, Seyma, 5979919>
	 */
	public SeitenLeiste getSeitenleiste() {
		return seitenleiste;
	}

	/**
	 * Getter-Methode fuer Spielflaeche
	 * 
	 * @author <Keser, Seyma, 5979919>
	 */
	public Spielflaeche getSpielflaeche() {
		return spielflaeche;
	}

	/**
	 * Getter- Methode fuer Highscore
	 * 
	 * @author <Keser,Seyma, 5979919>
	 */
	public Highscore getHighscore() {
		return highscore;
	}

	/**
	 * 
	 * @author <Keser, Seyma, 5979919>
	 * @author <unbekannt>
	 */
	int monsterda = 0;
	int monsterstaerke = 4;
	int mult = 1;

	public void keyPressed(KeyEvent e) {
		// Soll die Monster pro Level staerker werden
		if (level.getLevelID() == 0) {
			mult = 1;
		} else if (level.getLevelID() == 1) {
			mult = 2;
		} else if (level.getLevelID() == 2) {
			mult = 3;
		} else if (level.getLevelID() == 3) {
			mult = 4;
		} else if (level.getLevelID() == 4) {
			mult = 5;
		}
		// Methoden der Schnittstelle KeyListener
		// Aktuelle Position des Spielers
		// Frage Tastatureingaben auf den Pfeiltasten ab.
		// Es wird geprueft, ob der naechste Schritt zulaessig ist.
		// Bleibt die Figur innerhalb der Grenzen des Arrays?
		// Wenn ja, ist das naechste Feld begehbar?
		// Falls beides "wahr" ist, dann gehe den naechsten Schritt
		if (!spielende) {
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				if (zahl == 0) {
					zahl = 1;
					try {
						// Wechselt zum den Blauen Spielfigur bildern wenn
						// unzerstoerber
						if (spieler.istUnverwundbar()) {
							spieler.setImage("img//Johnblauhinten.png");
						} else {
							// Wechselt das Bilder der Spielfigur
							spieler.setImage("img//John3hinten.png");
						}
					} catch (IOException en) {
						en.printStackTrace();
					}
				} else if (zahl == 1) {
					zahl = 0;

				}
				// testspieler auf dem der zumachende Schritt vorgespeichert
				// werde
				// soll um zu schauen ob ein Monster auf dem Feld ist
				// Um beim Kampf nicht auf das Monster gehen zu muessen
				testspieler = new Spieler(2);
				testspieler.setFenster(this);
				testspieler.setXPos(spieler.getXPos());
				testspieler.setYPos(spieler.getYPos() - 1);
				Monster m = testspieler.angriffsMonster();
				if (m != null) {
					monsterda = 1;
				} else {
					monsterda = 0;
				}
				client.spieler = spieler;
				client.aktuellesLevel = level;
				level = client.aktuellesLevel;
				m = testspieler.angriffsMonster();
				if (monsterda == 1) {
					// Schaden am Monster hinterer Teil Regulierung der Staerke
					m.lebenAendern(-BOX / (monsterstaerke * mult));

				} else {
					// 1= Bewegung nach Oben
					client.spielerBewegung(1);
				}

			} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {

				if (zahl == 0) {
					zahl = 1;
					try {
						// Wechselt zum den Blauen Spielfigur bildern wenn
						// unzerstoerber
						if (spieler.istUnverwundbar()) {
							spieler.setImage("img//Johnblauvor1.png");
						} else {
							// Wechselt das Bilder der Spielfigur
							spieler.setImage("img//John3.png");
						}
					} catch (IOException en) {
						en.printStackTrace();
					}
				} else if (zahl == 1) {
					zahl = 0;

					try {
						// Wechselt zu den Bildern der blauen Spielfigur, wenn
						// unzerstoerbar
						if (spieler.istUnverwundbar()) {
							spieler.setImage("img//Johnblauvor2.png");
						} else {
							// Wechselt das Bild der Spielfigur
							spieler.setImage("img//John4.png");
						}
					} catch (IOException en) {

						en.printStackTrace();
					}

				}

				// testspieler auf dem der zumachende Schritt vorgespeichert
				// werde
				// soll um zu schauen ob ein Monster auf dem Feld ist
				// Um beim Kampf nicht auf das Monster gehen zu muessen
				testspieler = new Spieler(2);
				testspieler.setFenster(this);
				testspieler.setXPos(spieler.getXPos());
				testspieler.setYPos(spieler.getYPos() + 1);
				Monster m = testspieler.angriffsMonster();
				if (m != null) {

					monsterda = 1;
				} else {
					monsterda = 0;
				}
				client.spieler = spieler;
				client.aktuellesLevel = level;
				level = client.aktuellesLevel;
				m = testspieler.angriffsMonster();
				if (monsterda == 1) {
					// Schaden am Monster hinterer Teil Regulierung der Staerke
					m.lebenAendern(-BOX / (monsterstaerke * mult));
				} else {
					// 0= Bewegung nach unten
					client.spielerBewegung(0);
				}

			} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				monsterda = 0;
				if (zahl == 0) {
					zahl = 1;
					try {
						// Wechselt zum den Blauen Spielfigur bildern wenn
						// unzerstoerber
						if (spieler.istUnverwundbar()) {
							spieler.setImage("img//JohnblauLi1.png");
						} else {
							// Wechselt das Bilder der Spielfigur
							spieler.setImage("img//JohnLinks.png");
						}
					} catch (IOException en) {
						en.printStackTrace();
					}
				} else if (zahl == 1) {
					zahl = 0;
					try {
						if (spieler.istUnverwundbar()) {
							// Wechselt zum den Blauen Spielfigur bildern wenn
							// unzerstoerber
							spieler.setImage("img//JohnblauLi2.png");
						} else {
							// Wechselt das Bilder der Spielfigur
							spieler.setImage("img//JohnLinks2.png");
						}
					} catch (IOException en) {
						en.printStackTrace();
					}

				}

				// testspieler auf dem der zumachende Schritt vorgespeichert
				// werde
				// soll um zu schauen ob ein Monster auf dem Feld ist
				// Um beim Kampf nicht auf das Monster gehen zu muessen
				testspieler = new Spieler(2);
				testspieler.setFenster(this);
				testspieler.setXPos(spieler.getXPos() - 1);
				testspieler.setYPos(spieler.getYPos());
				Monster m = testspieler.angriffsMonster();
				if (m != null) {
					monsterda = 1;
				} else {
					monsterda = 0;
				}
				m = null;
				m = testspieler.angriffsMonster();
				if (monsterda == 1) {
					// Schaden am Monster hinterer Teil Regulierung der Staerke
					m.lebenAendern(-BOX / (monsterstaerke * mult));
					client.spieler = spieler;
					client.aktuellesLevel = level;
				} else {
					client.spieler = spieler;
					client.aktuellesLevel = level;
					// 2= Bewegung nach Links
					client.spielerBewegung(2);
					testspieler = null;

				}
			} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				monsterda = 0;
				if (zahl == 0) {
					zahl = 1;
					try {
						// Wechselt zum den Blauen Spielfigur bildern wenn
						// unzerstoerber
						if (spieler.istUnverwundbar()) {
							spieler.setImage("img//JohnblauRe1.png");
						} else {
							// Wechselt das Bilder der Spielfigur
							spieler.setImage("img//JohnSeite.png");
						}
					} catch (IOException en) {

						en.printStackTrace();
					}
				} else if (zahl == 1) {
					zahl = 0;

					try {
						// Wechselt zum den Blauen Spielfigur bildern wenn
						// unzerstoerber
						if (spieler.istUnverwundbar()) {
							spieler.setImage("img//JohnblauRe2.png");
						} else {
							// Wechselt das Bilder der Spielfigur
							spieler.setImage("img//JohnSeite2.png");
						}
					} catch (IOException en) {

						en.printStackTrace();
					}

				}

				// testspieler auf dem der zumachende Schritt vorgespeichert
				// werde
				// soll um zu schauen ob ein Monster auf dem Feld ist
				// Um beim Kampf nicht auf das Monster gehen zu muessen
				testspieler = new Spieler(2);
				testspieler.setFenster(this);
				testspieler.setXPos(spieler.getXPos() + 1);
				testspieler.setYPos(spieler.getYPos());
				Monster m = testspieler.angriffsMonster();
				if (m != null) {
					monsterda = 1;
				} else {
					monsterda = 0;
				}
				client.spieler = spieler;
				client.aktuellesLevel = level;
				level = client.aktuellesLevel;
				m = testspieler.angriffsMonster();
				if (monsterda == 1) {
					// Schaden am Monster hinterer Teil Regulierung der Staerke
					m.lebenAendern(-BOX / (monsterstaerke * mult));

				} else {
					// 3= Bewegung nach rechts
					client.spielerBewegung(3);
				}

			} else
			// B fuer 'Heiltrank benutzen'
			if (e.getKeyCode() == KeyEvent.VK_B) {
				if (spieler.getAnzahlHeiltraenke() > 0) {
					int change = spieler.benutzeHeiltrank();
					// Heilungseffekt wird verbessert, falls neue Monster durch
					// das
					// Aufheben des Schluessels ausgeloest wurden
					if (spieler.hatSchluessel())
						spieler.lebenAendern((int) (change * 1.5));
					else
						spieler.lebenAendern((int) (change * 0.5));
				}
			} else if (e.getKeyCode() == KeyEvent.VK_N) {
				// Schutztrank aufnehmen mit N
				if (trankTimer == 0)
					trankTimer = client.benutzeTrank();
			} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				System.exit(0);
			} else

			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				// Schluessel aufnehmen
				if (level.getBestimmtenLevelInhalt(spieler.getXPos(), spieler.getYPos()) == 8) {
					client.nimmSchluessel();
					level.setLevelInhalt(spieler.getXPos(), spieler.getYPos(), 1);
				}
				// Heiltrank aufnehmen
				else if (level.getBestimmtenLevelInhalt(spieler.getXPos(), spieler.getYPos()) == 4) {
					client.nimmHeiltrank();
					level.setLevelInhalt(spieler.getXPos(), spieler.getYPos(), 1);
				}
				// Blauentrank aufnehmen
				else if (level.getBestimmtenLevelInhalt(spieler.getXPos(), spieler.getYPos()) == 7) {
					client.nimmTrank();
					level.setLevelInhalt(spieler.getXPos(), spieler.getYPos(), 1);
				}

				// Schluessel benutzen
				if (level.getBestimmtenLevelInhalt(spieler.getXPos(), spieler.getYPos()) == 6) {
					if (spieler.hatSchluessel()) {
						benoetigteZeit = (int) ((System.currentTimeMillis() - startZeit) / 1000);

						nextLevel();

					}
				}
			} else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				seitenleiste.p.textfeld.setFocusable(true);
				seitenleiste.p.textfeld.requestFocusInWindow();
				seitenleiste.p.textfeld.requestFocus();
				seitenleiste.p.textfeld.setText("");
			}
		}
	}

	/**
	 * Bewegung ueber Maus ist moeglich
	 * 
	 * @author Seyma Keser
	 */
	public void mouseClicked(MouseEvent e) {
		int xKoos = 3;
		int yKoos = 3;
		double mausX = e.getX() / 72;
		double mausY = e.getY() / 72;
		int mausX1 = e.getX() / 72;
		int mausY1 = e.getY() / 72;
		// Frage Tastatureingaben auf den Pfeiltasten ab.
		// Es wird geprueft, ob der naechste Schritt zulaessig ist.
		// Bleibt die Figur innerhalb der Grenzen des Arrays
		// Wenn ja, ist das naechste Feld begehbar
		// Falls beides "wahr" ist, dann gehe den naechsten Schritt

		spielflaeche.setFocusable(true);
		spielflaeche.requestFocusInWindow();
		this.requestFocus();

		if (mausX * 10 >= (mausX1 * 10 + 5))
			mausX1 += 1;
		if (mausY * 10 >= (mausY1 * 10 + 5))
			mausY1 += 1;

		int Distanz1 = (yKoos - mausY1);
		int Distanz2 = (xKoos - mausX1);
		if (Distanz1 < 0)
			Distanz1 = Distanz1 * (-1);
		if (Distanz2 < 0)
			Distanz2 = Distanz2 * (-1);

		if (!spielende) {

			if (mausY1 < yKoos) {
				if (Distanz1 > Distanz2) {
					try {
						// Wechselt zum den Blauen Spielfigur bildern wenn
						// unzerstoerber
						if (spieler.istUnverwundbar()) {
							spieler.setImage("img//Johnblauhinten.png");
						} else {
							// Wechselt das Bilder der Spielfigur
							spieler.setImage("img//John3hinten.png");
						}
					} catch (IOException en) {
						en.printStackTrace();
					}

					// testspieler auf dem der zumachende Schritt vorgespeichert
					// werde
					// soll um zu schauen ob ein Monster auf dem Feld ist
					// Um beim Kampf nicht auf das Monster gehen zu muessen
					testspieler = new Spieler(2);
					testspieler.setFenster(this);
					testspieler.setXPos(spieler.getXPos());
					testspieler.setYPos(spieler.getYPos() - 1);
					Monster m = testspieler.angriffsMonster();
					if (m != null) {
						monsterda = 1;
					} else {
						monsterda = 0;
					}
					client.spieler = spieler;
					client.aktuellesLevel = level;
					level = client.aktuellesLevel;
					m = testspieler.angriffsMonster();
					if (monsterda == 1) {
						// Schaden am Monster hinterer Teil Regulierung der
						// Staerke
						m.lebenAendern(-BOX / (monsterstaerke * mult));

					} else {

						client.spielerBewegung(1);
					}

				}
			} else if (mausY1 > yKoos) {
				if (zahl == 0) {
					zahl = 1;
					try {
						// Wechselt zum den Blauen Spielfigur bildern wenn
						// unzerstoerber
						if (spieler.istUnverwundbar()) {
							spieler.setImage("img//Johnblauvor1.png");
						} else {
							// Wechselt das Bilder der Spielfigur
							spieler.setImage("img//John3.png");
						}
					} catch (IOException en) {
						en.printStackTrace();
					}
				} else if (zahl == 1) {
					zahl = 0;

					try {
						// Wechselt zum den Blauen Spielfigur bildern wenn
						// unzerstoerber
						if (spieler.istUnverwundbar()) {
							spieler.setImage("img//Johnblauvor2.png");
						} else {
							// Wechselt das Bilder der Spielfigur
							spieler.setImage("img//John4.png");
						}
					} catch (IOException en) {
						en.printStackTrace();
					}

				}
				// testspieler auf dem der zumachende Schritt vorgespeichert
				// werde
				// soll um zu schauen ob ein Monster auf dem Feld ist
				// Um beim Kampf nicht auf das Monster gehen zu muessen
				testspieler = new Spieler(2);
				testspieler.setFenster(this);
				testspieler.setXPos(spieler.getXPos());
				testspieler.setYPos(spieler.getYPos() + 1);
				Monster m = testspieler.angriffsMonster();
				if (m != null) {
					System.out.println("vor Dir ist ein monster");
					monsterda = 1;
				} else {
					monsterda = 0;
				}
				client.spieler = spieler;
				client.aktuellesLevel = level;
				level = client.aktuellesLevel;
				m = testspieler.angriffsMonster();
				if (monsterda == 1) {
					// Schaden am Monster hinterer Teil Regulierung der Staerke
					m.lebenAendern(-BOX / (monsterstaerke * mult));

				} else {

					client.spielerBewegung(0);
				}

			} else if (mausX1 < xKoos) {
				if (zahl == 0) {
					zahl = 1;
					try {
						// Wechselt zum den Blauen Spielfigur bildern wenn
						// unzerstoerber
						if (spieler.istUnverwundbar()) {
							spieler.setImage("img//JohnblauLi1.png");
						} else {
							// Wechselt das Bilder der Spielfigur
							spieler.setImage("img//JohnLinks.png");
						}
					} catch (IOException en) {
						en.printStackTrace();
					}
				} else if (zahl == 1) {
					zahl = 0;

					try {
						// Wechselt zum den Blauen Spielfigur bildern wenn
						// unzerstoerber
						if (spieler.istUnverwundbar()) {
							spieler.setImage("img//JohnblauLi2.png");
						} else {
							// Wechselt das Bilder der Spielfigur
							spieler.setImage("img//JohnLinks2.png");
						}
					} catch (IOException en) {
						en.printStackTrace();
					}
				}
				
				// testspieler auf dem der zumachende Schritt vorgespeichert
				// werde
				// soll um zu schauen ob ein Monster auf dem Feld ist
				// Um beim Kampf nicht auf das Monster gehen zu muessen
				testspieler = new Spieler(2);
				testspieler.setFenster(this);
				testspieler.setXPos(spieler.getXPos() - 1);
				testspieler.setYPos(spieler.getYPos());
				Monster m = testspieler.angriffsMonster();
				if (m != null) {
					monsterda = 1;
				} else {
					monsterda = 0;
				}
				client.spieler = spieler;
				client.aktuellesLevel = level;
				level = client.aktuellesLevel;
				m = testspieler.angriffsMonster();
				if (monsterda == 1) {
					// Schaden am Monster hinterer Teil Regulierung der Staerke
					m.lebenAendern(-BOX / (monsterstaerke * mult));

				} else {
					client.spielerBewegung(2);
				}
			} else if (mausX1 > xKoos) {
				if (zahl == 0) {
					zahl = 1;
					try {
						// Wechselt zum den Blauen Spielfigur Bildern wenn
						// unzerstoerber
						if (spieler.istUnverwundbar()) {
							spieler.setImage("img//JohnblauRe1.png");
						} else {
							// Wechselt das Bilder der Spielfigur
							spieler.setImage("img//JohnSeite.png");
						}
					} catch (IOException en) {
						en.printStackTrace();
					}
				} else if (zahl == 1) {
					zahl = 0;

					try {
						// Wechselt zum den Blauen Spielfigur bildern wenn
						// unzerstoerber
						if (spieler.istUnverwundbar()) {
							spieler.setImage("img//JohnblauRe2.png");
						} else {
							// Wechselt das Bilder der Spielfigur
							spieler.setImage("img//JohnSeite2.png");
						}
					} catch (IOException en) {
						en.printStackTrace();
					}

				}
				// testspieler auf dem der zumachende Schritt vorgespeichert
				// werde
				// soll um zu schauen ob ein Monster auf dem Feld ist
				// Um beim Kampf nicht auf das Monster gehen zu muessen
				testspieler = new Spieler(2);
				testspieler.setFenster(this);
				testspieler.setXPos(spieler.getXPos());
				testspieler.setYPos(spieler.getYPos() + 1);
				Monster m = testspieler.angriffsMonster();
				if (m != null) {
					monsterda = 1;
				} else {
					monsterda = 0;
				}
				client.spieler = spieler;
				client.aktuellesLevel = level;
				level = client.aktuellesLevel;
				m = testspieler.angriffsMonster();
				if (monsterda == 1) {
					// Schaden am Monster hinterer Teil Regulierung der Staerke
					m.lebenAendern(-BOX / (monsterstaerke * mult));

				} else {
					client.spielerBewegung(3);
				}
			}
		}
	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyTyped(KeyEvent e) {
	}

	/**
	 * Spiel wird auf den Anfangszustand zurueckgesetzt
	 * 
	 * 
	 * @author <unbekannt>
	 * @author <Keser, Seyma, 5979919>
	 */
	public void spielZuruecksetzen() {
		spielende = false;
		spielflaeche.anfangszustand = 0;

		try {
			Thread.sleep(100);
			spieler = new Spieler(0);
			spieler.setFenster(this);

			try {
				spieler.setImage(spieler.bildpfad);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			spieler2 = new Spieler(1);
			spieler2.setFenster(this);
			try {
				spieler2.setImage("img//red_point.png");
			} catch (IOException e) {
				e.printStackTrace();
			}
			monsterListe = new LinkedList<Monster>();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		spielende = false;
		verloren = false;
		nebelAn = true;
		nextLevel();
		spielerInHighscore = false;
		startZeit = System.currentTimeMillis();
	}

	/**
	 * Spielschleife wurde ergaenzt
	 * 
	 * @author <Keser, Seyma, 5979919>
	 * @author <unbekannt>
	 */
	public void starteNeuesSpiel() {
		spielende = false;
		spielflaeche.anfangszustand = 0;
		try {
			Thread.sleep(100);
			spielZuruecksetzen();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		do {

			if (!spielende) {
				// Hier wird alle 50ms neu gezeichnet
				try {
					Thread.sleep(100);
					// Spieleflaeche und Seitenleiste
					getSpielflaeche().repaint();
					getSeitenleiste().repaint();

					if (!(client.socket.cs.getPort() == 13000 || client.socket.cs.getPort() == 13001)) {
						System.exit(0);
					}

				} catch (InterruptedException e) {
				}

				if (spieler.trankAktiv) {
					if ((System.currentTimeMillis() - trankTimer) / 1000 > 5) {
						spieler.setUnverwundbar(false);
						spieler.bildWechseln();
						spieler.trankAktiv = false;
						trankTimer = 0;
					}
				}

				if (spieler.getLebenspunkte() <= 0) {
					spielende = true;
					verloren = true;
				}
			} else {
				benoetigteZeit = (int) ((System.currentTimeMillis() - startZeit) / 1000);

				if (!verloren && !spielerInHighscore) {
					// Das Hinzufuegen des Spielers in die Highscore Liste
					getHighscore().addSpielerToHighScore(benoetigteZeit, anmeldung.benutzername);
					getHighscore().repaint();
					try {
						Thread.sleep(1000);
						this.zeigeHighscore();
						// Loeschen der Monster Liste
						for (int n = 0; n <= monsterListe.size(); n++) {
							this.monsterListe.remove();
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					//
					this.spielZuruecksetzen();

					spielerInHighscore = true;
				} else {
					// Spielflaeche und SeitenLeiste
					getSpielflaeche().repaint();
					getSeitenleiste().repaint();
				}
			}

		} while (true);

	}

	/**
	 * Wechsel in die naechste Klasse
	 * 
	 * @author <Keser, Seyma, 5979919>
	 * 
	 */
	public void nextLevel() {
		spielflaeche.anfangszustand = 0;
		if (level.getLevelID() == -1) {
			client.levelAnfordern();
			level = client.aktuellesLevel;
		} else if (levelNeustarten) {
			level = client.alleLevel[0];
			levelnummer = 0;
			levelNeustarten = false;
		} else {
			level = client.levelWechseln();
		}
		// 6 = Ende der level
		if (level.getLevelID() == 6) {
			spielende = true;
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

}
