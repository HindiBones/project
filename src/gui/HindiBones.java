package gui;


import java.awt.BorderLayout;     
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;

import javax.accessibility.Accessible;
import javax.swing.*; 

import datenstruktur.Boden;
import datenstruktur.Heiltrank;
import datenstruktur.Monster;
import datenstruktur.Schluessel;
import datenstruktur.Spielelement;
import datenstruktur.Spieler;
import datenstruktur.Tuer;
import datenstruktur.Wand;

public class HindiBones extends JFrame implements KeyListener,MouseListener,Accessible {

	private static final long serialVersionUID = 1L;

	private Spielflaeche spielflaeche;
	private Minimap minimap;
	private Highscore highscore;
	private MenuLeiste menuLeiste;
	private Steuerung steuerung;
	private Anmeldung anmeldung;

	
	public LinkedList<Monster> monsterListe;
	public Spieler spieler;
	public Spieler spieler2;
	public Monster monster;
	

	public Spielelement[][] level;

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
	public final int WIDTH = 16; // LABIRITHGR…§E 
	public final int HEIGHT =16 ; 
	public final int BOX = 72;  // Grš§e der Bild Elemente 
	public final int BOX2=10;
	public final int BOX3=32;
	


	public HindiBones(int width, int height, String title) {
		
		initialisiereJFrame(width , height, title); 
//		this.setSize(800,600);
		
		// initialisieren des Fensters 
		starteNeuesSpiel();
		
		
	}

	public void initialisiereJFrame(int width, int height, String title) {
		
		this.setLayout(new BorderLayout());
				
		this.spielflaeche = new Spielflaeche(this);
		this.minimap= new Minimap(this);
		this.steuerung = new Steuerung();
		this.highscore = new Highscore();
		// Erzeuge Menuleiste
		this.menuLeiste = new MenuLeiste(this);
		this.anmeldung = new Anmeldung(this);
		
		
		
		
		
		// Es wird die gewuenschte Groesse angegeben			
		spielflaeche.setPreferredSize(new Dimension(width+5, height+5));	
		minimap.setPreferredSize(new Dimension(width/3, BOX)); //Grš§e meiner Minimap
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

	public void zeigeSpielfeld() {
		
		// entferne alles
		highscoreAngezeigt = false;
		
		this.remove(anmeldung);
		this.remove(highscore);
		this.remove(steuerung);
		

		this.add(spielflaeche, BorderLayout.CENTER);
		//Versuch auf Scall Panel
						

		// erstelle das Spielfeld 
		this.add(minimap, BorderLayout.EAST);
		this.add(menuLeiste, BorderLayout.NORTH);
	

		// aktiviere das fertige Spielfeld
		this.requestFocus();
		this.pack();   
	
	}
	


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

	
	public void zeigeAnmeldung(){
		highscoreAngezeigt=false;
		
		this.remove(minimap);
		this.remove(spielflaeche);
		this.remove(highscore);
		this.remove(steuerung);
		try {
			Thread.sleep(50);
			this.add(anmeldung, BorderLayout.CENTER);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		this.requestFocus();
		this.pack();
		anmeldung.repaint();
		
	}
	
	
	//	 Getter fuer die Spielflaeche bzw. Statusleiste
	public Minimap getMinimap(){
		return minimap;
	}
	
	public Spielflaeche getSpielflaeche() {
		return spielflaeche;
	}

	public Highscore getHighscore() {
		return highscore;
	}
	
	public Anmeldung getAnmeldung(){
		return anmeldung;
	}
	

	
	
	// Methoden der Schnittstelle KeyListener
	public void keyPressed(KeyEvent e) {
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
				if (yPos > 0 && !(level[xPos][yPos - 1] instanceof Wand)){ // Was ist mit Instanceof gemeint??
					
//					spieler.hoch();
			} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				if (yPos < HEIGHT - 1
						&& !(level[xPos][yPos + 1] instanceof Wand))
					spieler.runter();
			} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				if (xPos > 0 && !(level[xPos - 1][yPos] instanceof Wand))
					spieler.links();
			} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				if (xPos < WIDTH - 1
						&& !(level[xPos + 1][yPos] instanceof Wand))
					spieler.rechts();

				// B für 'Heiltrank benutzen'
			} else 
				if (e.getKeyCode() == KeyEvent.VK_B) {
				int change = spieler.benutzeHeiltrank();
				// Heilungseffekt wird verbessert, falls neue Monster durch das
				// Aufheben des Schlüssels ausgelöst wurden
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
				if (level[spieler.getXPos()][spieler.getYPos()] instanceof Schluessel) {
					spieler.nimmSchluessel();
					level[spieler.getXPos()][spieler.getYPos()] = new Boden();
				}
				// Heiltrank aufnehmen
				else if (level[spieler.getXPos()][spieler.getYPos()] instanceof Heiltrank) {
					spieler.nimmHeiltrank((Heiltrank) level[spieler.getXPos()][spieler
							.getYPos()]);
					level[spieler.getXPos()][spieler.getYPos()] = new Boden();
				}
				// Schluessel benutzen
				if (level[spieler.getXPos()][spieler.getYPos()] instanceof Tuer) {
					if (!((Tuer) level[spieler.getXPos()][spieler.getYPos()])
							.istOffen() && spieler.hatSchluessel()) {
						((Tuer) level[spieler.getXPos()][spieler.getYPos()])
								.setOffen();
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
			}}
	}

	public void mouseClicked(MouseEvent e) {
		int xKoos = 3;
		int yKoos = 3;
		int xPos=spieler.getXPos();
		int yPos=spieler.getYPos();
		double mausX= e.getX()/72;
		double mausY=e.getY()/72; 
		int mausX1= e.getX()/72;
		int mausY1= e.getY()/72;
		// Frage Tastatureingaben auf den Pfeiltasten ab.
		// Es wird geprueft, ob der naechste Schritt zulaessig ist.
		// Bleibt die Figur innerhalb der Grenzen des Arrays?
		// Wenn ja, ist das naechste Feld begehbar?
		// Falls beides "wahr" ist, dann gehe den naechsten Schritt
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
				if (Distanz1>Distanz2 &&!(level[xPos][yPos - 1] instanceof Wand))
					spieler.hoch();
				Monster m = spieler.angriffsMonster();
			if (m != null)
				m.changeHealth(-BOX / 4);
			}else if(mausY1 > yKoos){	
				if ( Distanz1>Distanz2 &&!(level[xPos][yPos + 1] instanceof Wand))
					spieler.runter();
				Monster m = spieler.angriffsMonster();
			if (m != null)
				m.changeHealth(-BOX / 4);
			}else if(mausX1<xKoos){	
				if (!(level[xPos - 1][yPos] instanceof Wand))
					spieler.links();
				Monster m = spieler.angriffsMonster();
			if (m != null)
				m.changeHealth(-BOX / 4);
			}else if(mausX1> xKoos){
				if ( !(level[xPos + 1][yPos] instanceof Wand))
					spieler.rechts();
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

	
	
	public void spielZuruecksetzen() {

		
		try {
			Thread.sleep(100);		
		spieler = new Spieler("img//spieler.png", this);
		spieler.setImage(spieler.getImage().getScaledInstance(72,72,Image.SCALE_DEFAULT)); 
		spieler2=new Spieler("img//red_point.png",this);
		spieler2.setImage(spieler2.getImage().getScaledInstance(10,10,Image.SCALE_DEFAULT)); 
		monsterListe = new LinkedList<Monster>();
		level = new Spielelement[WIDTH][HEIGHT];
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
				

				if (spieler.getHealth() <= 0) {
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


	public void nextLevel() {
		currentLevel++;
		Leser leser = new Leser("lvl//level" + currentLevel + ".txt", this);
		level = leser.getLevel();

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
