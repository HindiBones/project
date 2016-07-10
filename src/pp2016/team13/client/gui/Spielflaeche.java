package pp2016.team13.client.gui;

import java.awt.Color; 
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import pp2016.team13.shared.Monster;
import pp2016.team13.shared.Spieler;


public class Spielflaeche extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private Image boden, wand, tuerOffen, tuerZu, schluessel, heiltrank,trank,
			feuerball;
	private HindiBones fenster;
	public int  wechselX=0;
	public int wechselY=0;
  
	public Spielflaeche(HindiBones fenster) {
		
		
		this.fenster = fenster;
	

		// Lade die Bilder
		try {  
			boden = ImageIO.read(new File("img//ground.png"));
			wand = ImageIO.read(new File("img//wand Kopie.png")); 
			tuerZu = ImageIO.read(new File("img//tuer2.png")); 
			tuerOffen = ImageIO.read(new File("img//tueroffen2.png"));
			schluessel = ImageIO.read(new File("img//schluessel.png"));
			heiltrank = ImageIO.read(new File("img//heiltrank.png"));
			feuerball = ImageIO.read(new File("img//feuerball.png"));
			trank=ImageIO.read(new File("img//heiltrankblau.png"));


			
		} catch (IOException e) {
			System.err.println("Ein Bild konnte nicht geladen werden.");
		}
		}
	
	//Positionierung von HindiBones in die Mitte
	//Das Bild wird immer dann neu gezeichnet wenn HindiBones auf den Koordinaten 
	//der GrenzPunkteX/Y kommt, dann wird das ganze aktuelle Bild neu 
	//gezeichnet. Da sich das Bild immer mitbewegt wird durch verschiebenx /y
	//Das richtige Bild Zentriert
	
	/**
	 * 
	 *  
	 * @author Seyma Keser
	 *  
	 *  
	 * (Mitscrollend)
	 * Meine Figur Zentriert 
	 * Bei jeder Bewegung und berührung der Grenzpunkt Koordinaten wird das Spielfeld neu gezeichnet
	 * Die Grenzpunkte befinden sich jeweils an allen Seiten von meiner Spieler Figur
	 * Durch die Variable verschieben wird die der Hintergrund immer richtig verschoben
	 * (Da mein Spieler die Koordinaten bzw. Spielfeld Pos. immer steigend ist, steigen auch 
	 * meine Variablen, sie passen sich meiner Spieler Figur an)
	 * 
	 *
	 */
	int verschiebenx=0;
	int verschiebeny=0;
	int grenzPunktX=4;
	int grenzPunktY=4;
	int grenzPunktx=2;
	int grenzPunkty=2;
	Monster Gegner=null;
	int Px;
	int Py;
	int anfangszustand= 0;
	int MonsterStandpunktx[];
	int MonsterStandpunkty[];
	
	/**
	 * @author Seyma 
	 * 
	 * Setzt überall im Labirinth wo eine 3 ist ein Monster Objekt in die die monsterliste 
	 * (die monsterliste wird dann bei spaeter aufgerufen um die Monster zu zeichnen)
	 * 
	 */
	public void genMonster(){
		for (int i =wechselX ; i <fenster.WIDTH ; i++) {
			for (int j = wechselY; j < fenster.HEIGHT; j++) {
				if (fenster.Level.getBestimmtenLevelInhalt(i, j) == 3) {
					Gegner = new Monster(i, j, fenster, 0);
					fenster.monsterListe.add(Gegner);
				}
				
			}
		}
		
	}
	/**
	 * @author Seyma
	 * 
	 * Die Position der Offenen Türe bei 2 wird als Start Position des Spielers gesetzt
	 * (muss wie genMonster() außerhalb der paint Mehtode passieren, damit die monster und Spieler nicht immer auf der Selben
	 * Position gezeichnet werden durch Repaint())
	 * 
	 */
	public void posSpieler(){
		for (int i =wechselX ; i <fenster.WIDTH ; i++) {
		for (int j = wechselY; j < fenster.HEIGHT; j++) {
			if (fenster.Level.getBestimmtenLevelInhalt(i, j) == 2) {
				fenster.spieler.setPos(i, j);
				fenster.spieler2.setPos(i,j);
			}
			
		}
	}
	
	}
	/**
	 * @author Seyma 
	 * 
	 *Meine Felder Generieren sich im ersten Schritt um meinen Spieler
	 *Grund: Mein Spieler wird in die Mitte gesetzt (d.h Pos X= 3 Pos Y=3 Aber Bezogen auf die Sichtbare Flaeche)
	 *		Da sich die Grenzpunkte immer einen Schritt von dem Spieler entfernt befinden
	 *		Wird bei jedem Schritt mein Spielfeld um 1 Verschoben und die Stelle neu gezeichnet.
	 *
	 *Rest ist die Generierung der Spielflaeche durch abfrage ueber den Client welcher inhalt sich auf welcher Koordinate
	 *Befindet, wird ein Bild gezeichnet. (Grundgeruest vom urspruenglichen Spiel Code)
	 * 
	 * 
	 * 
	 */
	public void paint(Graphics g) {
		if (anfangszustand==0){
			this.posSpieler();
			this.genMonster();
			anfangszustand++;
		}
		// Beim neuzeichnen wird zunaechst alles uebermalt
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());

		// Male die einzelnen Felder
		for (int i =wechselX ; i < fenster.WIDTH; i++) {
			for (int j = wechselY; j < fenster.HEIGHT; j++) {
				if (inRange(i, j)) {

					if(fenster.spieler.getXPos()>=grenzPunktX ){
						verschiebenx+=1;
						grenzPunktX+=1;
						grenzPunktx+=1;
					}
				
					if(fenster.spieler.getXPos()<=grenzPunktx ){
						verschiebenx-=1;
						grenzPunktx-=1;
						grenzPunktX-=1;
					}
					if(fenster.spieler.getYPos()<=grenzPunkty ){
						verschiebeny-=1;
						grenzPunkty-=1;
						grenzPunktY-=1;
					}
					
					if(fenster.spieler.getYPos()>=grenzPunktY){
						verschiebeny+=1;
						grenzPunktY+=1;
						grenzPunkty+=1;
						
					}
					
					if (fenster.Level.getBestimmtenLevelInhalt(i, j) == 0) { //Wand==0
						// Hier kommt eine Wand hin 
						g.drawImage(wand, i * fenster.BOX-verschiebenx*fenster.BOX, j * fenster.BOX-verschiebeny*fenster.BOX,
								null);
							
					} else if (fenster.Level.getBestimmtenLevelInhalt(i, j) == 1) { //Boden ==1
						// Dieses Feld ist begehbar
						g.drawImage(boden, i * fenster.BOX-verschiebenx*fenster.BOX, j * fenster.BOX-verschiebeny*fenster.BOX,
								null);
					}
					else if (fenster.Level.getBestimmtenLevelInhalt(i, j)== 3){ //Monster ==3
						g.drawImage(boden, i * fenster.BOX-verschiebenx*fenster.BOX, j * fenster.BOX-verschiebeny*fenster.BOX,
								null);
						//Monster werden vor der Paint-Methode in genMonster in eine Lister Getzt ueberall wo Monster==3 ist
					} 
					else if (fenster.Level.getBestimmtenLevelInhalt(i, j) == 2) { //Offene Tuere ==2
						g.drawImage(boden, i * fenster.BOX-verschiebenx*fenster.BOX, j * fenster.BOX-verschiebeny*fenster.BOX,
								null);
						g.drawImage(tuerOffen, i * fenster.BOX-verschiebenx*fenster.BOX, j
								* fenster.BOX-verschiebeny*fenster.BOX, null);
					
					} else if (fenster.Level.getBestimmtenLevelInhalt(i, j) == 6) { //Geschlossene Tuere==6
						// Hier ist die Tuer
						g.drawImage(boden, i * fenster.BOX-verschiebenx*fenster.BOX, j * fenster.BOX-verschiebeny*fenster.BOX,
								null);
							g.drawImage(tuerZu, i * fenster.BOX-verschiebenx*fenster.BOX, j
									* fenster.BOX-verschiebeny*fenster.BOX, null);
			
					} else if (fenster.Level.getBestimmtenLevelInhalt(i, j) == 7 ) { //Blauer Trank==7
						// Hier ist ein Blauer Trank
						g.drawImage(boden, i * fenster.BOX-verschiebenx*fenster.BOX, j * fenster.BOX-verschiebeny*fenster.BOX,
								null);
							g.drawImage(trank, i * fenster.BOX-verschiebenx*fenster.BOX, j
									* fenster.BOX-verschiebeny*fenster.BOX, null);
							
					}else if (fenster.Level.getBestimmtenLevelInhalt(i, j) == 4) { //Heiltrank==4 
						//Hier ist ein Heiltrank
						g.drawImage(boden, i * fenster.BOX-verschiebenx*fenster.BOX, j * fenster.BOX-verschiebeny*fenster.BOX,
								null);
						g.drawImage(heiltrank, i * fenster.BOX-verschiebenx*fenster.BOX,
								j * fenster.BOX-verschiebeny*fenster.BOX, null);
				
					}else if (fenster.Level.getBestimmtenLevelInhalt(i, j) == 5) { //Schluessel ==5
						//HIER LIEGT NOCH EIN SCHLÜSSEL
						g.drawImage(boden, i * fenster.BOX-verschiebenx*fenster.BOX, j * fenster.BOX-verschiebeny*fenster.BOX,
								null);
						g.drawImage(schluessel, i * fenster.BOX-verschiebenx*fenster.BOX,
								j * fenster.BOX-verschiebeny*fenster.BOX, null);
					}
				}
			}
		}

		
			
		// Male die Monster an ihrer Position
		for (int k = 0; k < fenster.monsterListe.size(); k++) {
			
			Monster m = fenster.monsterListe.get(k);
//			MonsterStandpunktx[k]= m.getPosX();
//			MonsterStandpunkty[k]= m.getPosY();
			
			boolean event = fenster.spieler.hatSchluessel();
			// Da hier alle Monster aufgerufen werden, wird an dieser
			// Stelle auch ein Angriffsbefehl fuer die Monster
			// abgegeben, falls der Spieler in der Naehe ist.
			// Ansonsten soll das Monster laufen
			if (!m.attackiereSpieler(event)) {
				m.move();
			} else {
				int box = fenster.BOX;
				Spieler s = fenster.spieler;

				double p = m.cooldownProzent();
				g.setColor(Color.RED);
				g.drawImage(
						feuerball,
						(int) (((1 - p) * m.getXPos() + (p) * s.getXPos()) * box )
								+ box / 2 -verschiebenx*fenster.BOX, (int) (((1 - p) * m.getYPos() + (p)
								* s.getYPos()) * box)
								+ box / 2 -verschiebeny*fenster.BOX, 8, 8, null);
			}

			// Male das Monster, falls es von anfang an anwesend ist
			if (m.getTyp() == 0)
				drawMonster(g, m);
			// Male das Monster, falls es erst durch das Event 'Schluessel
			// aufheben' erscheint
			else if (event && m.getTyp() == 1)
				drawMonster(g, m);
		
}
		// Male den Spieler an seiner Position
		g.drawImage(fenster.spieler.getImage(), fenster.spieler.getXPos()
				* fenster.BOX-verschiebenx*fenster.BOX, fenster.spieler.getYPos() * fenster.BOX-verschiebeny*fenster.BOX, null);

		if (fenster.verloren) {
			g.setColor(Color.WHITE);
			g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
			g.drawString("GAME OVER", getWidth() / 2 - 120, getHeight() / 2);
		} else {
			if (fenster.spielende) {
				g.setColor(Color.WHITE);
				g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
				g.drawString("GEWONNEN", getWidth() / 2 - 120, getHeight() / 2);
			}
		}
		
	}

	/**
	 * 
	 * @author Seyma Keser + (unbekannt)
	 * Methode vom Urspruenglichen Code uebernommen + Erweiterung durch einbauen der Verschiebungen fuer 
	 * scrollenden Bildschirm
	 * 
	 */
	private void drawMonster(Graphics g, Monster m) {
		// Monster Health Points
		if (inRange(m.getXPos(), m.getYPos())) {
			g.drawImage(m.getImage(), m.getXPos() * fenster.BOX -verschiebenx*fenster.BOX, m.getYPos()
					* fenster.BOX-verschiebeny*fenster.BOX, null);
			g.setColor(Color.GREEN);
			g.fillRect(m.getXPos() * fenster.BOX-verschiebenx*fenster.BOX +10,
					m.getYPos() * fenster.BOX -verschiebeny*fenster.BOX- 2, m.getLebenspunkte()+20, 2);

		}
	}
	/**
	 * @author Unbekannt 
	 * 
	 * Methode uebernommen nichts veraendert
	 * 
	 */
	private boolean inRange(int i, int j) {	
		return (Math.sqrt(Math.pow(fenster.spieler.getXPos() - i, 2)
				+ Math.pow(fenster.spieler.getYPos() - j, 2)) < 3 || !fenster.nebelAn);

	

	}
	
}
