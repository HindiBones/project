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


public class HindiBones extends JFrame implements KeyListener,MouseListener,Accessible {

	private static final long serialVersionUID = 1L;

	Spielflaeche spielflaeche;
	private SeitenLeiste minimap;
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
	
	
	public Level Level;
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
	public boolean steuerungAngezeigt= false;
	public boolean anmeldeanzeige=false;
	public boolean levelNeustarten = false;
	
	
	public final int MAXLEVEL = 5; 
	public final int WIDTH = 19; // Labirinth grosse
	public final int HEIGHT =19 ; 
	public final int BOX = 72;  // Grosse der Bild Elemente 
	public final int BOX2=9;
	public final int BOX3=32;
	public int zahl=0;
	public int timer=0;
	
	String Bn;
	String Pw;
		

	public HindiBones(int width, int height, String title) {


		initialisiereJFrame(width , height, title); 
		//Verbindung zum Client
		client = new NachrichtenVerwaltung(this);
		client.spieler = spieler;
		// initialisieren des Fensters 
		starteNeuesSpiel();
		
		
	}
	/**
	 * Ergänzt um Minimap/Seitenstatus und der Anmeldung
	 * Entfernt Alter Statusleiste
	 * 
	 * @author Keser, Seyma, 5979919
	 * @author <unbekannt>
	 * @param width: Die Breite des Fensters
	 * @param height: Die Höhe des Fensters
	 * @param title: Name des Fensters
	 */
	public void initialisiereJFrame(int width, int height, String title) {
		
		this.setLayout(new BorderLayout());
		Level = new Level(-1, null);
		this.spielflaeche = new Spielflaeche(this);
		this.minimap= new SeitenLeiste(this);
		this.steuerung = new Steuerung();
		this.highscore = new Highscore();
		// Erzeuge Menuleiste
		this.menuLeiste = new MenuLeiste(this);
		this.anmeldung = new Anmeldung(this);
		
		// Es wird die gewuenschte Groesse angegeben			
		spielflaeche.setPreferredSize(new Dimension(width+5, height+5));	
		minimap.setPreferredSize(new Dimension(width/3+4, BOX)); //Gr��e meiner Minimap
		steuerung.setPreferredSize(new Dimension(width+50, height-50));
		highscore.setPreferredSize(new Dimension(width+50, height-50));
		anmeldung.setPreferredSize(new Dimension(640,400));

		//Das Startfenster soll die Anmeldung sein
		zeigeAnmeldung();
		
		// Zentriere das Fenster auf dem Bildschirm
		final Dimension d = this.getToolkit().getScreenSize();
		this.setLocation((int) ((d.getWidth() - this.getWidth()) /2),
				(int) ((d.getHeight() - this.getHeight()) /2)); // Setzt die Position auf dem Bildschirm fest
		
		//Anbindung an Listener
		this.addKeyListener(this);
		spielflaeche.addMouseListener(this);
		// Standardsetup
		this.setResizable(false);
		this.setTitle(title);
		this.setVisible(true);
		this.addWindowListener(exitListener);
		
		
		
		
	}

	/**
	 * Ergängt / Verbessert durch mein Seitentool(Minimap) entfernen von früherer Statusleiste
	 * 
	 * @author Keser, Seyma, 5979919
	 */
	public void zeigeSpielfeld() {
		
		// entferne alles
		highscoreAngezeigt = false;
		steuerungAngezeigt=false;
		spielfeldSichtbar = true;
		this.remove(steuerung);
		this.remove(anmeldung);
		this.remove(highscore);
		

		// erstelle das Spielfeld 
		this.add(spielflaeche, BorderLayout.CENTER);
		this.add(minimap, BorderLayout.EAST);
		this.add(menuLeiste, BorderLayout.NORTH);
	

		// aktiviere das fertige Spielfeld
		this.requestFocus();
		this.pack();   
	}
	
	/**
	 * Sendet Nachricht ueber Client zum Server das, dass das Spiel geschlossen werden soll
	 * 
	 * @author Keser, Seyma, 5979919
	 */
	WindowListener exitListener = new WindowAdapter() {		
	    @Override
	    public void windowClosing(WindowEvent e) {
	    	if(client.socket.cs.isBound())
	    	{
	    		client.socket.SendeLogout(new Paket(new AusloggenNachricht()));
	    	}
	        System.exit(0);
	    }
	};
	

	/**
	 * Ergaenzt um einige Elemente 
	 * Anzeigen des Highscores
	 * 
	 * @author Keser, Seyma, 5979919
	 */
	public void zeigeHighscore() {
		// entferne alles
		highscoreAngezeigt = true;
		steuerungAngezeigt=false;
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
	 * Ergaenzt um Seitenleiste
	 * 
	 * @author Keser, Seyma, 5979919
	 */
	public void zeigeSteuerung() {
		// entferne alles
		steuerungAngezeigt=true;
		highscoreAngezeigt = false;
		
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
	 * Erzaenzt um Seitenleiste
	 * Methode zum Anzeigen des Anmelde Panels
	 * + Verbindung zum Client wird aufgebaut
	 * 
	 * @author Seyma Keser
	 */
	public void zeigeAnmeldung(){
		steuerungAngezeigt=false;
		highscoreAngezeigt=false;
		
		
		this.remove(minimap);
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
	 * @author <Keser, Seyma, 5979919>
	 */
	public SeitenLeiste getMinimap(){
		return minimap;
	}
	
	/**
	 * Getter-Methode fuer Spielflaeche
	 * @author <Keser, Seyma, 5979919>
	 */
	public Spielflaeche getSpielflaeche() {
		return spielflaeche;
	}

	/**
	 * Getter- Methode fuer Highscore
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
	int monsterda=0;
	int monsterstaerke=4;
	int mult=1;
	public void keyPressed(KeyEvent e) {
		//Soll die Monster pro Level staerker werden
		if(Level.getLevelID()==0){
			mult=1;
		}else if (Level.getLevelID()==1){
			mult=2;
		}	else if (Level.getLevelID()==2){
			mult=3;
		}else if (Level.getLevelID()==3){
			mult=4;
		}else if (Level.getLevelID()==4){
			mult=5;
		}
		// Methoden der Schnittstelle KeyListener
		// Aktuelle Position des Spielers
		// Frage Tastatureingaben auf den Pfeiltasten ab.
		// Es wird geprueft, ob der naechste Schritt zulaessig ist.
		// Bleibt die Figur innerhalb der Grenzen des Arrays?
		// Wenn ja, ist das naechste Feld begehbar?
		// Falls beides "wahr" ist, dann gehe den naechsten Schritt
		if (!spielende) {
			if (e.getKeyCode()== KeyEvent.VK_UP) {
				if(zahl==0){ 
					zahl=1;
				try {
					//Wechselt zum den Blauen Spielfigur bildern wenn unzerstoerber
					if(spieler.istUnverwundbar()){
						spieler.setImage("img//Johnblauhinten.png");
					}else{
						//Wechselt das Bilder der Spielfigur 
						spieler.setImage("img//John3hinten.png");
					}	
				} catch (IOException en) {
					System.out.println("Bild Failt");
					en.printStackTrace();
				} 
			} 
			else if(zahl==1){ 
				zahl=0;
				
				}
				//testspieler auf dem der zumachende Schritt vorgespeichert werde
				//soll um zu schauen ob ein Monster auf dem Feld ist 
				//Um beim Kampf nicht auf das Monster gehen zu muessen
				testspieler = new Spieler(2);
				testspieler.setFenster(this);
				testspieler.setXPos(spieler.getXPos());
				testspieler.setYPos(spieler.getYPos()-1);
				Monster m = testspieler.angriffsMonster();
				if (m != null){
					System.out.println("vordir ist ein monster");
					monsterda=1;
				}else {
					monsterda=0;
				}

				client.spieler = spieler;
				client.aktuellesLevel = Level;
				Level = client.aktuellesLevel;
				m = testspieler.angriffsMonster();
				if(monsterda==1){
					//Schaden am Monster hinterer Teil Regulierung der Staerke
					m.changeHealth(-BOX / (monsterstaerke*mult));
	
				}	else {
					// 1= Bewegung nach Oben 
					client.SpielerBewegung(1);
				}
					
					

			} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {

				if(zahl==0){ 
					zahl=1;
					
				try { 
					//Wechselt zum den Blauen Spielfigur bildern wenn unzerstoerber
				if(spieler.istUnverwundbar()){
						spieler.setImage("img//Johnblauvor1.png");
				}else{
					//Wechselt das Bilder der Spielfigur 
					spieler.setImage("img//John3.png");
				}} catch (IOException en) {
					System.out.println("Bild Failt");
					en.printStackTrace();
				} } 
				else if(zahl==1){ zahl=0;
				
				try {
					//Wechselt zum den Blauen Spielfigur bildern wenn unzerstoerber
					if(spieler.istUnverwundbar()){
						spieler.setImage("img//Johnblauvor2.png");
					}else{
						//Wechselt das Bild der Spielfigur 
						spieler.setImage("img//John4.png");
					}} catch (IOException en) {
					System.out.println("Bild Failt");
					en.printStackTrace();
				}
				
				}
			
				//testspieler auf dem der zumachende Schritt vorgespeichert werde
				//soll um zu schauen ob ein Monster auf dem Feld ist 
				//Um beim Kampf nicht auf das Monster gehen zu muessen
				testspieler = new Spieler(2);
				testspieler.setFenster(this);
				testspieler.setXPos(spieler.getXPos());
				testspieler.setYPos(spieler.getYPos()+1);
				Monster m = testspieler.angriffsMonster();
				if (m != null){
					System.out.println("vordir ist ein monster");
					monsterda=1;
				}else {
					monsterda=0;
				}
				client.spieler = spieler;
				client.aktuellesLevel = Level;
				Level = client.aktuellesLevel;
				m = testspieler.angriffsMonster();
				if(monsterda==1){
					//Schaden am Monster hinterer Teil Regulierung der Staerke
					m.changeHealth(-BOX / (monsterstaerke*mult));
				}	else {

					//0= Bewegung nach unten
					client.SpielerBewegung(0);
				}
					
					
					

			

				
			
				
			} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				monsterda=0;
					//Mein John seite img//JohnSeite.png
				
				
				if(zahl==0){ 
					zahl=1;
				try {
					//Wechselt zum den Blauen Spielfigur bildern wenn unzerstoerber
					if(spieler.istUnverwundbar()){
						spieler.setImage("img//JohnblauLi1.png");
				}else{
					//Wechselt das Bilder der Spielfigur 
				spieler.setImage("img//JohnLinks.png");
				}} catch (IOException en) {
					System.out.println("Bild Failt");
					en.printStackTrace();
				} } 
				else if(zahl==1){ 
					zahl=0;
				
				try {
					if(spieler.istUnverwundbar()){
						//Wechselt zum den Blauen Spielfigur bildern wenn unzerstoerber
						spieler.setImage("img//JohnblauLi2.png");
					}else{
						//Wechselt das Bilder der Spielfigur 
					spieler.setImage("img//JohnLinks2.png");
					}} catch (IOException en) {
					System.out.println("Bild Failt");
					en.printStackTrace();
				}
				
				}

				//testspieler auf dem der zumachende Schritt vorgespeichert werde
				//soll um zu schauen ob ein Monster auf dem Feld ist 
				//Um beim Kampf nicht auf das Monster gehen zu muessen
				testspieler = new Spieler(2);
				testspieler.setFenster(this);
				testspieler.setXPos(spieler.getXPos()-1);
				testspieler.setYPos(spieler.getYPos());
				Monster m = testspieler.angriffsMonster();
				if (m != null){
					System.out.println("vordir ist ein monster");
					monsterda=1;
				}else {
					monsterda=0;
				}
				m=null;
				m = testspieler.angriffsMonster();
				if(monsterda==1){
					//Schaden am Monster hinterer Teil Regulierung der Staerke
					m.changeHealth(-BOX / (monsterstaerke*mult));
				client.spieler = spieler;
				client.aktuellesLevel = Level;

				}	else {
				client.spieler = spieler;
				client.aktuellesLevel = Level;
				
				client.SpielerBewegung(2);	
				testspieler = null;
								
					

			}} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) { 
				monsterda=0;

				if(zahl==0){ 
					zahl=1;
				try {
					//Wechselt zum den Blauen Spielfigur bildern wenn unzerstoerber
					if(spieler.istUnverwundbar() ){
						spieler.setImage("img//JohnblauRe1.png");
						}
					else{
						//Wechselt das Bilder der Spielfigur 
					spieler.setImage("img//JohnSeite.png");
				}} catch (IOException en) {
					System.out.println("Bild Failt");
					en.printStackTrace();
				} } 
				else if(zahl==1){ 
					zahl=0;
				
				try {
					//Wechselt zum den Blauen Spielfigur bildern wenn unzerstoerber
					if(spieler.istUnverwundbar()){
						spieler.setImage("img//JohnblauRe2.png");
					}else{
						//Wechselt das Bilder der Spielfigur 
					spieler.setImage("img//JohnSeite2.png");
					}} catch (IOException en) {
					System.out.println("Bild Failt");
					en.printStackTrace();
				}
				
				}


				
				//testspieler auf dem der zumachende Schritt vorgespeichert werde
				//soll um zu schauen ob ein Monster auf dem Feld ist 
				//Um beim Kampf nicht auf das Monster gehen zu muessen
				testspieler = new Spieler(2);
				testspieler.setFenster(this);
				testspieler.setXPos(spieler.getXPos()+1);
				testspieler.setYPos(spieler.getYPos());
				Monster m = testspieler.angriffsMonster();
				if (m != null){
					monsterda=1;
				}else {
					monsterda=0;
				}
				client.spieler = spieler;
				client.aktuellesLevel = Level;
				Level = client.aktuellesLevel;
				m = testspieler.angriffsMonster();
				if(monsterda==1){
					//Schaden am Monster hinterer Teil Regulierung der Staerke
					m.changeHealth(-BOX / (monsterstaerke*mult));
					
				}	else {
					
				client.SpielerBewegung(3);
				}
					
					
					
					

				
			} else 
				// B fuer 'Heiltrank benutzen'
				if (e.getKeyCode() == KeyEvent.VK_B) {
					if(spieler.getAnzahlHeiltraenke()>0){
				int change = spieler.benutzeHeiltrank();
				// Heilungseffekt wird verbessert, falls neue Monster durch das
				// Aufheben des Schluessels ausgeloest wurden
				if (spieler.hatSchluessel() )
					spieler.changeHealth((int) (change * 1.5));
				else
					spieler.changeHealth((int) (change * 0.5));
					}
			} else 
				if (e.getKeyCode() == KeyEvent.VK_N) {
					//Schutztrank aufnehmen mit N
					if(trankTimer == 0)
				trankTimer = client.benutzeTrank();	
			}
				else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				System.exit(0);
				}else
			
	
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				// Schluessel aufnehmen
				if (Level.getBestimmtenLevelInhalt(spieler.getXPos(),spieler.getYPos()) == 8) {
					client.nimmSchluessel();
					System.out.println("Spieler hat den Schluessel!");
					Level.setLevelInhalt(spieler.getXPos(), spieler.getYPos(), 1);
				}
				// Heiltrank aufnehmen
				else if (Level.getBestimmtenLevelInhalt(spieler.getXPos(), spieler.getYPos()) == 4) {
					client.nimmHeiltrank();
					System.out.println("Spieler hat einen Heiltrank!");
					Level.setLevelInhalt(spieler.getXPos(), spieler.getYPos(), 1);
				}
				//Blauentrank aufnehmen
				else if (Level.getBestimmtenLevelInhalt(spieler.getXPos(), spieler.getYPos()) == 7) {
					client.nimmTrank();
					System.out.println("Spieler hat einen Trank!");
					Level.setLevelInhalt(spieler.getXPos(), spieler.getYPos(), 1);
				}
				
				// Schluessel benutzen
				if (Level.getBestimmtenLevelInhalt(spieler.getXPos(), spieler.getYPos()) == 6) {
					if (spieler.hatSchluessel()) {
						System.out.println("Spieler hat den Schluessel benutzt!");
						benoetigteZeit = (int) ((System.currentTimeMillis() - startZeit) / 1000);

						nextLevel();

					}
				}
			}else if(e.getKeyCode() == KeyEvent.VK_ENTER){
				minimap.p.textfeld.setFocusable(true);
				minimap.p.textfeld.requestFocusInWindow();
				minimap.p.textfeld.requestFocus();
				minimap.p.textfeld.setText("");
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
					try {
						//Wechselt zum den Blauen Spielfigur bildern wenn unzerstoerber
						if(spieler.istUnverwundbar()){
							spieler.setImage("img//Johnblauhinten.png");
					}else{
						//Wechselt das Bilder der Spielfigur 
						spieler.setImage("img//John3hinten.png");
					}
					} catch (IOException en) {
						en.printStackTrace();
					}
					
					//testspieler auf dem der zumachende Schritt vorgespeichert werde
					//soll um zu schauen ob ein Monster auf dem Feld ist 
					//Um beim Kampf nicht auf das Monster gehen zu muessen
					testspieler = new Spieler(2);
					testspieler.setFenster(this);
					testspieler.setXPos(spieler.getXPos());
					testspieler.setYPos(spieler.getYPos()-1);
					Monster m = testspieler.angriffsMonster();
					if (m != null){
						System.out.println("vordir ist ein monster");
						monsterda=1;
					}else {
						monsterda=0;
					}
					client.spieler = spieler;
					client.aktuellesLevel = Level;
					Level = client.aktuellesLevel;
					m = testspieler.angriffsMonster();
					if(monsterda==1){
						//Schaden am Monster hinterer Teil Regulierung der Staerke
						m.changeHealth(-BOX / (monsterstaerke*mult));


					}	else {
					

						client.SpielerBewegung(1);
					}
						
			
				}}else if(mausY1 > yKoos){	
					if(zahl==0){ zahl=1;
					try {
						//Wechselt zum den Blauen Spielfigur bildern wenn unzerstoerber
						if(spieler.istUnverwundbar()){
							spieler.setImage("img//Johnblauvor1.png");
						}else{
							//Wechselt das Bilder der Spielfigur 
						spieler.setImage("img//John3.png");
						}
					} catch (IOException en) {
						en.printStackTrace();
					} } 
					else if(zahl==1){ zahl=0;
					
					try {
						//Wechselt zum den Blauen Spielfigur bildern wenn unzerstoerber
						if(spieler.istUnverwundbar()){
						spieler.setImage("img//Johnblauvor2.png");
					}else{
						//Wechselt das Bilder der Spielfigur 
					spieler.setImage("img//John4.png");
					}
					} catch (IOException en) {
						en.printStackTrace();
					}
					
					}
					//testspieler auf dem der zumachende Schritt vorgespeichert werde
					//soll um zu schauen ob ein Monster auf dem Feld ist 
					//Um beim Kampf nicht auf das Monster gehen zu muessen
					testspieler = new Spieler(2);
					testspieler.setFenster(this);
					testspieler.setXPos(spieler.getXPos());
					testspieler.setYPos(spieler.getYPos()+1);
					Monster m = testspieler.angriffsMonster();
					if (m != null){
						System.out.println("vordir ist ein monster");
						monsterda=1;
					}else {
						monsterda=0;
					}
					client.spieler = spieler;
					client.aktuellesLevel = Level;
					Level = client.aktuellesLevel;
					m = testspieler.angriffsMonster();
					if(monsterda==1){
						//Schaden am Monster hinterer Teil Regulierung der Staerke
						m.changeHealth(-BOX / (monsterstaerke*mult));
			

					}	else {
	

						client.SpielerBewegung(0);
					}
				
			}else if(mausX1<xKoos){	
				if(zahl==0){ zahl=1;
				try {
					//Wechselt zum den Blauen Spielfigur bildern wenn unzerstoerber
					if(spieler.istUnverwundbar()){
						spieler.setImage("img//JohnblauLi1.png");
					}else{
						//Wechselt das Bilder der Spielfigur 
					spieler.setImage("img//JohnLinks.png");
					}
				} catch (IOException en) {
					en.printStackTrace(); 
				} } 
				else if(zahl==1){ zahl=0;
				
				try {
					//Wechselt zum den Blauen Spielfigur bildern wenn unzerstoerber
					if(spieler.istUnverwundbar()){
						spieler.setImage("img//JohnblauLi2.png");
					}else{
						//Wechselt das Bilder der Spielfigur 
					spieler.setImage("img//JohnLinks2.png");
					}
				} catch (IOException en) {
					en.printStackTrace();
				}
				
				}
				//testspieler auf dem der zumachende Schritt vorgespeichert werde
				//soll um zu schauen ob ein Monster auf dem Feld ist 
				//Um beim Kampf nicht auf das Monster gehen zu muessen
				testspieler = new Spieler(2);
				testspieler.setFenster(this);
				testspieler.setXPos(spieler.getXPos()-1);
				testspieler.setYPos(spieler.getYPos());
				Monster m = testspieler.angriffsMonster();
				if (m != null){
					System.out.println("vordir ist ein monster");
					monsterda=1;
				}else {
					monsterda=0;
				}
				client.spieler = spieler;
				client.aktuellesLevel = Level;
				Level = client.aktuellesLevel;
				m = testspieler.angriffsMonster();
				if(monsterda==1){
					//Schaden am Monster hinterer Teil Regulierung der Staerke
					m.changeHealth(-BOX / (monsterstaerke*mult));
	
				}	else {

					client.SpielerBewegung(2);
				}
			}else if(mausX1> xKoos){
				if(zahl==0){ zahl=1;
				try {
					//Wechselt zum den Blauen Spielfigur bildern wenn unzerstoerber
					if(spieler.istUnverwundbar()){
						spieler.setImage("img//JohnblauRe1.png");
					}else{
						//Wechselt das Bilder der Spielfigur 
					spieler.setImage("img//JohnSeite.png");
					}
				} catch (IOException en) {
					en.printStackTrace();
				} } 
				else if(zahl==1){ zahl=0;
				
				try {
					//Wechselt zum den Blauen Spielfigur bildern wenn unzerstoerber
					if(spieler.istUnverwundbar()){
						spieler.setImage("img//JohnblauRe2.png");
					}else{
						//Wechselt das Bilder der Spielfigur 
					spieler.setImage("img//JohnSeite2.png");
					}
				} catch (IOException en) {
					en.printStackTrace();
				}
				
				}
				//testspieler auf dem der zumachende Schritt vorgespeichert werde
				//soll um zu schauen ob ein Monster auf dem Feld ist 
				//Um beim Kampf nicht auf das Monster gehen zu muessen
				testspieler = new Spieler(2);
				testspieler.setFenster(this);
				testspieler.setXPos(spieler.getXPos());
				testspieler.setYPos(spieler.getYPos()+1);
				Monster m = testspieler.angriffsMonster();
				if (m != null){
					System.out.println("vordir ist ein monster");
					monsterda=1;
				}else {
					monsterda=0;
				}
				client.spieler = spieler;
				client.aktuellesLevel = Level;
				Level = client.aktuellesLevel;
				m = testspieler.angriffsMonster();
				if(monsterda==1){
					//Schaden am Monster hinterer Teil Regulierung der Staerke
					m.changeHealth(-BOX / (monsterstaerke*mult));
		

				}	else {
			

					client.SpielerBewegung(3);
				}
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
		spielende=false;
		spielflaeche.anfangszustand=0;

		try {
			Thread.sleep(100);		
		spieler = new Spieler(0);
		spieler.setFenster(this);
	
		try {
			spieler.setImage(spieler.bildpfad);
		} catch (IOException e1) {
			e1.printStackTrace();
		} 
		spieler2=new Spieler(1);
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
	 * 
	 * @author Seyma Keser
	 */
	
	// Spielschleife
	public void starteNeuesSpiel() {
		spielende=false;
		spielflaeche.anfangszustand=0;
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
					
					getSpielflaeche().repaint();
					getMinimap().repaint();

					if(!(client.socket.cs.getPort() == 13000 || client.socket.cs.getPort() == 13001)){
						System.exit(0);
					}

				} catch (InterruptedException e) {
				}

				if(spieler.trankAktiv){
					if((System.currentTimeMillis() - trankTimer) / 1000 > 5)
					{
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
					getHighscore().addSpielerToHighScore(benoetigteZeit, anmeldung.benutzername);
					getHighscore().repaint();
					try {
						Thread.sleep(1000);
						this.zeigeHighscore();
						for (int n=0; n<=monsterListe.size();n++ ){
							this.monsterListe.remove();
						}
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					this.spielZuruecksetzen();
					
					spielerInHighscore = true;
				} else {
					
					getSpielflaeche().repaint();
					getMinimap().repaint();
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
		spielflaeche.anfangszustand=0;
		if(Level.getLevelID() == -1){
		client.levelAnfordern();
		Level = client.aktuellesLevel;
		}
		else if(levelNeustarten)
		{
			Level = client.alleLevel[0];
			levelnummer = 0;
			levelNeustarten = false;
		}
		else
		{
			Level.ausgabe();
			System.out.println();
			Level = client.levelWechseln();
		}
		//6 = Ende der level
		if(Level.getLevelID() == 6 ){
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
