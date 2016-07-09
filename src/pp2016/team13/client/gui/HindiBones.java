package pp2016.team13.client.gui;


import java.awt.BorderLayout;      
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import javax.accessibility.Accessible;
import javax.imageio.ImageIO;
import javax.swing.*; 
import pp2016.team13.client.engine.NachrichtenVerwaltung;
import pp2016.team13.client.engine.LoginNachricht;
import pp2016.team13.shared.Boden;
import pp2016.team13.shared.Heiltrank;
import pp2016.team13.shared.Level;
import pp2016.team13.shared.Monster;
import pp2016.team13.shared.Schluessel;
import pp2016.team13.shared.Spielelement;
import pp2016.team13.shared.Spieler;
import pp2016.team13.shared.Tuer;
import pp2016.team13.shared.Wand;

public class HindiBones extends JFrame implements KeyListener,MouseListener,Accessible {

	private static final long serialVersionUID = 1L;

	private Spielflaeche spielflaeche;
	private SeitenLeiste minimap;
	private Highscore highscore;
	private MenuLeiste menuLeiste;
	private Steuerung steuerung;
	private Anmeldung anmeldung;

	
	public LinkedList<Monster> monsterListe;
	public Spieler spieler;
	public Spieler spieler2;
	public Monster monster;
	
	
	public Level Level;
	public Spielelement[][] level;
	public NachrichtenVerwaltung client;
	public int currentLevel = 0;
	public boolean spielende = false;
	public boolean verloren = false;
	public long startZeit;
	public int benoetigteZeit;
	public boolean nebelAn = true;
	private boolean spielerInHighscore = false;
	public boolean highscoreAngezeigt = false;
	public boolean anmeldeanzeige=false;
	
	
	
	
	public final int MAXLEVEL = 5; 
	public final int WIDTH = 16; // LABIRITHGR��E 
	public final int HEIGHT =16 ; 
	public final int BOX = 72;  // Gr��e der Bild Elemente 
	public final int BOX2=9;
	public final int BOX3=32;
	public int zahl=0;
	
	String Bn;
	String Pw;
	


	public HindiBones(int width, int height, String title) {
		

		initialisiereJFrame(width , height, title); 
//		this.setSize(800,600);
		client = new NachrichtenVerwaltung(this);
		client.spieler = spieler;
		// initialisieren des Fensters 
		starteNeuesSpiel();
		
		
	}
	/**
	 * Ergänzt um Minimap/Seitenstatus und der Anmeldung
	 * Entfernt Alter Statusleiste
	 * 
	 * @author Seyma Keser
	 */
	public void initialisiereJFrame(int width, int height, String title) {
		
		this.setLayout(new BorderLayout());
				
		this.spielflaeche = new Spielflaeche(this);
		this.minimap= new SeitenLeiste(this);
		this.steuerung = new Steuerung();
		this.highscore = new Highscore();
		// Erzeuge Menuleiste
		this.menuLeiste = new MenuLeiste(this);
		this.anmeldung = new Anmeldung(this);
		
		
		
		
		// Es wird die gewuenschte Groesse angegeben			
		spielflaeche.setPreferredSize(new Dimension(width+5, height+5));	
		minimap.setPreferredSize(new Dimension(width/3+8, BOX)); //Gr��e meiner Minimap
		steuerung.setPreferredSize(new Dimension(width+50, height-50));
		highscore.setPreferredSize(new Dimension(width+50, height-50));
		anmeldung.setPreferredSize(new Dimension(640,400));
		
		
		// Erstelle das Spielfeld
		//zeigeSpielfeld();

		
		zeigeAnmeldung();
		
		// Zentriere das Fenster auf dem Bildschirm
		final Dimension d = this.getToolkit().getScreenSize();
		this.setLocation((int) ((d.getWidth() - this.getWidth()) /2),
				(int) ((d.getHeight() - this.getHeight()) /2)); // Setzt die Position auf dem Bildschirm fest
		
		
		this.addKeyListener(this);
		spielflaeche.addMouseListener(this);
		// Standardsetup
		this.setResizable(false);
		this.setTitle(title);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		
		
	}

	/**
	 * Ergängt / Verbessert durch mein Seitentool(Minimap) entfernen von früherer Statusleiste
	 * 
	 * @author Seyma Keser
	 */
	public void zeigeSpielfeld() {
		
		// entferne alles
		highscoreAngezeigt = false;
		
		this.remove(anmeldung);
		this.remove(highscore);
		this.remove(steuerung);

		// erstelle das Spielfeld 
		this.add(spielflaeche, BorderLayout.CENTER);
		this.add(minimap, BorderLayout.EAST);
		this.add(menuLeiste, BorderLayout.NORTH);
	

		// aktiviere das fertige Spielfeld
		this.requestFocus();
		this.pack();   
		
		
	
	}
	

	/**
	 * Ergänzt um Minimap 
	 * 
	 * @author Seyma Keser
	 */
	public void zeigeHighscore() {
		// entferne alles
		highscoreAngezeigt = true;
		this.remove(spielflaeche);
		this.remove(minimap);
		this.remove(steuerung);
	
		
		// erstelle die Highscoreanzeige
		this.add(highscore, BorderLayout.CENTER);
		
		
		// aktiviere die Highscoreanzeige
		this.requestFocus();
		
		this.pack();
		highscore.repaint();
	}
	
	/**
	 * Ergänzt um Minimap
	 * @author Seyma Keser
	 */
	public void zeigeSteuerung() {
		// entferne alles
		highscoreAngezeigt = true;
		
		this.remove(minimap);
		this.remove(spielflaeche);
		this.remove(highscore);
		//	this.remove(statusleiste);	
		
		// erstelle die Steuerungsanzeige
		this.add(steuerung, BorderLayout.CENTER);
		
		// aktiviere die Steuerungsanzeige
		this.requestFocus();
		this.pack();
		steuerung.repaint();
	}

	/**
	 * Methode zum Anzeigen des Anmelde Panels
	 * + Verbindung zum Client wird aufgebaut
	 * 
	 * @author Seyma Keser
	 */
	public void zeigeAnmeldung(){

		highscoreAngezeigt=false;
		
		
		this.remove(minimap);
		this.remove(spielflaeche);
		this.remove(highscore);
		this.remove(steuerung);
		try {
			Thread.sleep(50);
			this.add(anmeldung, BorderLayout.CENTER);	
		
		
			// Registrierung
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		this.requestFocus();
		this.pack();
		anmeldung.repaint();
		
	}
	
	/**
	 * Getter- Methoden zur Anmeldung - Benutzername + Passwort
	 * @author Seyma Keser
	 */
	public String GetBenutzername(){
	return Bn;
	}
	public String GetPasswort(){
		return Pw;
	}
	
	/**
	 * Getter für Minimap(mein komplettes Seitentool)
	 * @author Seyma Keser
	 */
	public SeitenLeiste getMinimap(){
		return minimap;
	}
	
	/**
	 * Getter-Methode für Spielfläche
	 * @author Seyma Keser
	 */
	public Spielflaeche getSpielflaeche() {
		return spielflaeche;
	}

	/**
	 * Getter- Methode für Highscore
	 * @author Seyma Keser
	 */
	public Highscore getHighscore() {
		return highscore;
	}
	
	/**
	 * 
	 * @author Seyma Keser
	 */
	public Anmeldung getAnmeldung(){
		return anmeldung;
	}
	
	/**
	 * 
	 * @author Seyma Keser
	 */
	public void keyPressed(KeyEvent e) {
		// Methoden der Schnittstelle KeyListener
		// Aktuelle Position des Spielers
		int xPos = spieler.getXPos();
		int yPos = spieler.getYPos();
		// Frage Tastatureingaben auf den Pfeiltasten ab.
		// Es wird geprueft, ob der naechste Schritt zulaessig ist.
		// Bleibt die Figur innerhalb der Grenzen des Arrays?
		// Wenn ja, ist das naechste Feld begehbar?
		// Falls beides "wahr" ist, dann gehe den naechsten Schritt
		if (!spielende) {
			if (e.getKeyCode()== KeyEvent.VK_UP) {
				if(zahl==0){ zahl=1;
				try { //John Rücken  img//John3hinten
					if(spieler.istUnverwundbar())
						spieler.setImage(ImageIO.read(new File("img//Johnblauhinten.png")).getScaledInstance(72,72, Image.SCALE_DEFAULT));
					else
					spieler.setImage(ImageIO.read(new File("img//John3hinten.png")).getScaledInstance(72,72, Image.SCALE_DEFAULT));
				} catch (IOException en) {
					System.out.println("Bild Failt");
					// TODO Auto-generated catch block
					en.printStackTrace();
				} } 
				else if(zahl==1){ zahl=0;
				
//				try {
//					spieler.setImage(ImageIO.read(new File("img//John4.png")).getScaledInstance(72,72, Image.SCALE_DEFAULT));
//				} catch (IOException en) {
//					// TODO Auto-generated catch block
//					en.printStackTrace();
//				}
				
				}
		
					client.spieler = spieler;
					client.aktuellesLevel = Level;
					client.SpielerBewegung(1);
					Level = client.aktuellesLevel;
					Monster m = spieler.angriffsMonster();
					if (m != null)
						m.changeHealth(-BOX / 4);
			} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {

				if(zahl==0){ zahl=1;
				try { // Passt
					if(spieler.istUnverwundbar())
						spieler.setImage(ImageIO.read(new File("img//Johnblauvor1.png")).getScaledInstance(72,72, Image.SCALE_DEFAULT));
					else
					spieler.setImage(ImageIO.read(new File("img//John3.png")).getScaledInstance(72,72, Image.SCALE_DEFAULT));
				} catch (IOException en) {
					System.out.println("Bild Failt");
					// TODO Auto-generated catch block
					en.printStackTrace();
				} } 
				else if(zahl==1){ zahl=0;
				
				try {
					if(spieler.istUnverwundbar())
						spieler.setImage(ImageIO.read(new File("img//Johnblauvor2.png")).getScaledInstance(72,72, Image.SCALE_DEFAULT));
					else
						spieler.setImage(ImageIO.read(new File("img//John4.png")).getScaledInstance(72,72, Image.SCALE_DEFAULT));
				} catch (IOException en) {
					System.out.println("Bild Failt");
					// TODO Auto-generated catch block
					en.printStackTrace();
				}
				
				}

					client.spieler = spieler;
					client.aktuellesLevel = Level;
					client.SpielerBewegung(0);
					Level = client.aktuellesLevel;
					Monster m = spieler.angriffsMonster();
					if (m != null)
						m.changeHealth(-BOX / 4);
			} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					//Mein John seite img//JohnSeite.png
				
				
				if(zahl==0){ zahl=1;
				try {
					if(spieler.istUnverwundbar())
						spieler.setImage(ImageIO.read(new File("img//JohnblauLi1.png")).getScaledInstance(72,72, Image.SCALE_DEFAULT));
					else
					spieler.setImage(ImageIO.read(new File("img//JohnLinks.png")).getScaledInstance(72,72, Image.SCALE_DEFAULT));
				} catch (IOException en) {
					System.out.println("Bild Failt");
					// TODO Auto-generated catch block
					en.printStackTrace();
				} } 
				else if(zahl==1){ zahl=0;
				
				try {
					if(spieler.istUnverwundbar())
						spieler.setImage(ImageIO.read(new File("img//JohnblauLi2.png")).getScaledInstance(72,72, Image.SCALE_DEFAULT));
					else
					spieler.setImage(ImageIO.read(new File("img//JohnLinks2.png")).getScaledInstance(72,72, Image.SCALE_DEFAULT));
				} catch (IOException en) {
					System.out.println("Bild Failt");
					// TODO Auto-generated catch block
					en.printStackTrace();
				}
				
				}
	
				client.spieler = spieler;
				client.aktuellesLevel = Level;
				client.SpielerBewegung(2);
				Level = client.aktuellesLevel;
				Monster m = spieler.angriffsMonster();
				if (m != null)
					m.changeHealth(-BOX / 4);
			} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {

				if(zahl==0){ zahl=1;
				try {
					if(spieler.istUnverwundbar())
						spieler.setImage(ImageIO.read(new File("img//JohnblauRe1.png")).getScaledInstance(72,72, Image.SCALE_DEFAULT));
					else
					spieler.setImage(ImageIO.read(new File("img//JohnSeite.png")).getScaledInstance(72,72, Image.SCALE_DEFAULT));
				} catch (IOException en) {
					System.out.println("Bild Failt");
					// TODO Auto-generated catch block
					en.printStackTrace();
				} } 
				else if(zahl==1){ zahl=0;
				
				try {
					if(spieler.istUnverwundbar())
						spieler.setImage(ImageIO.read(new File("img//JohnblauRe2.png")).getScaledInstance(72,72, Image.SCALE_DEFAULT));
					else
					spieler.setImage(ImageIO.read(new File("img//JohnSeite2.png")).getScaledInstance(72,72, Image.SCALE_DEFAULT));
				} catch (IOException en) {
					System.out.println("Bild Failt");
					// TODO Auto-generated catch block
					en.printStackTrace();
				}
				
				}

				client.spieler = spieler;
				client.aktuellesLevel = Level;
				client.SpielerBewegung(3);
				Level = client.aktuellesLevel;
				Monster m = spieler.angriffsMonster();
				if (m != null)
					m.changeHealth(-BOX / 4);

				// B f�r 'Heiltrank benutzen'
			} else 
				if (e.getKeyCode() == KeyEvent.VK_B) {
				int change = spieler.benutzeHeiltrank();
				// Heilungseffekt wird verbessert, falls neue Monster durch das
				// Aufheben des Schl�ssels ausgel�st wurden
				if (spieler.hatSchluessel())
					spieler.changeHealth((int) (change * 1.5));
				else
					spieler.changeHealth((int) (change * 0.5));
			} else 
				if (e.getKeyCode() == KeyEvent.VK_B) {
				int change = spieler.benutzeHeiltrank();
				// Heilungseffekt wird verbessert, falls neue Monster durch das
				// Aufheben des Schl�ssels ausgel�st wurden
				if (spieler.hatSchluessel())
					spieler.changeHealth((int) (change * 1.5));
				else
					spieler.changeHealth((int) (change * 0.5));
			}
				else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				System.exit(0);
				}
			
	
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				// Schluessel aufnehmen
				if (Level.getBestimmtenLevelInhalt(spieler.getXPos(),spieler.getYPos()) == 4) {
					spieler.nimmSchluessel();
					Level.setLevelInhalt(spieler.getXPos(), spieler.getYPos(), 1);
				}
				// Heiltrank aufnehmen
				else if (Level.getBestimmtenLevelInhalt(spieler.getXPos(), spieler.getYPos()) == 3) {
					spieler.nimmHeiltrank();
					Level.setLevelInhalt(spieler.getXPos(), spieler.getYPos(), 1);
				}
				// Schluessel benutzen
				if (Level.getBestimmtenLevelInhalt(spieler.getXPos(), spieler.getYPos()) == 6) {
					if (spieler.hatSchluessel()) {
						Level.setLevelInhalt(spieler.getXPos(), spieler.getYPos(), 7);
						// Nach dem Oeffnen der Tuer ist der Schluessel wieder weg
						spieler.entferneSchluessel();
						if (currentLevel < MAXLEVEL)
							nextLevel();
						else {
							spielende = true;
						}
					}
				}
			}
			}
	}
	
	
	/**
	 * 
	 * @author Seyma Keser
	 */
	public void mouseClicked(MouseEvent e) {
		int xKoos = 3;
		int yKoos = 3;
		double mausX= e.getX()/72;
		double mausY=e.getY()/72; 
		int mausX1= e.getX()/72;
		int mausY1= e.getY()/72;
		// Frage Tastatureingaben auf den Pfeiltasten ab.
		// Es wird geprueft, ob der naechste Schritt zulaessig ist.
		// Bleibt die Figur innerhalb der Grenzen des Arrays?
		// Wenn ja, ist das naechste Feld begehbar?
		// Falls beides "wahr" ist, dann gehe den naechsten Schritt
		spielflaeche.setFocusable(true);
		spielflaeche.requestFocusInWindow();
		this.requestFocus();
		
		if(mausX*10>=(mausX1*10+5))
			mausX1+=1;
		if(mausY*10>=(mausY1*10+5))
			mausY1+=1;
		
		int Distanz1= (yKoos-mausY1);
		int Distanz2=(xKoos-mausX1);
		if (Distanz1<0)
			Distanz1=Distanz1*(-1);
		if(Distanz2<0)
			Distanz2= Distanz2*(-1);
			
		
		if (!spielende) {
		
			if(mausY1< yKoos){
				if (Distanz1>Distanz2){
					if(zahl==0){ zahl=1;
					try {
						spieler.setImage(ImageIO.read(new File("img//John3.png")).getScaledInstance(72,72, Image.SCALE_DEFAULT));
					} catch (IOException en) {
						// TODO Auto-generated catch block
						en.printStackTrace();
					} } 
					else if(zahl==1){ zahl=0;
					
					try {
						spieler.setImage(ImageIO.read(new File("img//John4.png")).getScaledInstance(72,72, Image.SCALE_DEFAULT));
					} catch (IOException en) {
						// TODO Auto-generated catch block
						en.printStackTrace();
					}
					
					}
					client.spieler = spieler;
					client.aktuellesLevel = Level;
					client.SpielerBewegung(1);
					Level = client.aktuellesLevel;
				Monster m = spieler.angriffsMonster();
			if (m != null)
				m.changeHealth(-BOX / 4);
				}}else if(mausY1 > yKoos){	
					if(zahl==0){ zahl=1;
					try {
						spieler.setImage(ImageIO.read(new File("img//John3.png")).getScaledInstance(72,72, Image.SCALE_DEFAULT));
					} catch (IOException en) {
						// TODO Auto-generated catch block
						en.printStackTrace();
					} } 
					else if(zahl==1){ zahl=0;
					
					try {
						spieler.setImage(ImageIO.read(new File("img//John4.png")).getScaledInstance(72,72, Image.SCALE_DEFAULT));
					} catch (IOException en) {
						// TODO Auto-generated catch block
						en.printStackTrace();
					}
					
					}
				client.spieler = spieler;
				client.aktuellesLevel = Level;
				client.SpielerBewegung(0);
				Level = client.aktuellesLevel;
				Monster m = spieler.angriffsMonster();
			if (m != null)
				m.changeHealth(-BOX / 4);
			}else if(mausX1<xKoos){	
				if(zahl==0){ zahl=1;
				try {
					spieler.setImage(ImageIO.read(new File("img//John3.png")).getScaledInstance(72,72, Image.SCALE_DEFAULT));
				} catch (IOException en) {
					// TODO Auto-generated catch block
					en.printStackTrace();
				} } 
				else if(zahl==1){ zahl=0;
				
				try {
					spieler.setImage(ImageIO.read(new File("img//John4.png")).getScaledInstance(72,72, Image.SCALE_DEFAULT));
				} catch (IOException en) {
					// TODO Auto-generated catch block
					en.printStackTrace();
				}
				
				}
				client.spieler = spieler;
				client.aktuellesLevel = Level;
				client.SpielerBewegung(2);
				Level = client.aktuellesLevel;
				Monster m = spieler.angriffsMonster();
			if (m != null)
				m.changeHealth(-BOX / 4);
			}else if(mausX1> xKoos){
				if(zahl==0){ zahl=1;
				try {
					spieler.setImage(ImageIO.read(new File("img//John3.png")).getScaledInstance(72,72, Image.SCALE_DEFAULT));
				} catch (IOException en) {
					// TODO Auto-generated catch block
					en.printStackTrace();
				} } 
				else if(zahl==1){ zahl=0;
				
				try {
					spieler.setImage(ImageIO.read(new File("img//John4.png")).getScaledInstance(72,72, Image.SCALE_DEFAULT));
				} catch (IOException en) {
					// TODO Auto-generated catch block
					en.printStackTrace();
				}
				
				}
				client.spieler = spieler;
				client.aktuellesLevel = Level;
				client.SpielerBewegung(3);
				Level = client.aktuellesLevel;
				Monster m = spieler.angriffsMonster();
			if (m != null)
				m.changeHealth(-BOX / 7);
			}
	
		}
		
	}

	
	public void keyReleased(KeyEvent e) {
	}

	public void keyTyped(KeyEvent e) {
	}

	
	/**
	 * 
	 * @author Seyma Keser
	 */
	public void spielZuruecksetzen() {

		try {
			Thread.sleep(100);		
		spieler = new Spieler(0);
		spieler.setFenster(this);
//		
		spieler.setImage(spieler.getImage().getScaledInstance(72,72,Image.SCALE_DEFAULT)); 
		spieler2=new Spieler(1);
		spieler2.setFenster(this);
		try {
			spieler2.setImage(ImageIO.read(new File("img//red_point.png")).getScaledInstance(10, 10, Image.SCALE_DEFAULT));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		monsterListe = new LinkedList<Monster>();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		currentLevel = 0;
		spielende = false;
		verloren = false;
		nebelAn = true;
		nextLevel();
		spielerInHighscore = false;
		startZeit = System.currentTimeMillis();
	}

	/**
	 * 
	 * @author Seyma Keser
	 */
	// Spielschleife
	public void starteNeuesSpiel() {
		try {
			Thread.sleep(100);
			spielZuruecksetzen();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		do {
			
			if (!spielende) {
				// Hier wird alle 50ms neu gezeichnet
				try {
					Thread.sleep(100);
					getSpielflaeche().repaint();
					getMinimap().repaint();
					
				} catch (InterruptedException e) {
				}

				
				//	getStatusleiste().repaint();
				

				if (spieler.getLebenspunkte() <= 0) {
					spielende = true;
					verloren = true;
				}
			} else {
				benoetigteZeit = (int) ((System.currentTimeMillis() - startZeit) / 1000);

				if (!verloren && !spielerInHighscore) {
					getHighscore().addSpielerToHighScore(benoetigteZeit);
					getHighscore().repaint();
					spielerInHighscore = true;
				} else {
					getSpielflaeche().repaint();
					getMinimap().repaint();
				}
			}

		} while (true);
		

	}

	/**
	 * 
	 * @author Seyma Keser
	 */
	public void nextLevel() {
//		currentLevel++;
//		Leser leser = new Leser("lvl//level" + currentLevel + ".txt", this);
//		Level = leser.getLevel();
//		client.aktuellesLevel = Level;
		
		System.out.println("Level wird angefordert!");
		client.levelAnfordern();
		Level = client.aktuellesLevel;
		System.out.println("Level geladen!");
	}

	
	

	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}	

}
