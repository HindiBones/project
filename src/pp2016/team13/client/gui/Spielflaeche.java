package pp2016.team13.client.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import pp2016.team13.shared.Boden;
import pp2016.team13.shared.Heiltrank;
import pp2016.team13.shared.Monster;
import pp2016.team13.shared.Schluessel;
import pp2016.team13.shared.Spieler;
import pp2016.team13.shared.Tuer;
import pp2016.team13.shared.Wand;

public class Spielflaeche extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private Image boden, wand, tuerOffen, tuerZu, schluessel, heiltrank,trank,hintergrund1,
			feuerball, spieler;
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
			hintergrund1= ImageIO.read(new File("img/wand Kopie.png"));

			
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
	 * (Mitscrollend)
	 * Meine Figur Zentriert 
	 * Bei jeder Bewegung und berührung der Grenzpunkt Koordinaten wird das Spielfeld neu gezeichnet
	 * Die Grenzpunkte befinden sich jeweils an allen Seiten von meiner Spieler Figur
	 * Durch die Variable verschieben wird die der Hintergrund immer richtig verschoben
	 * (Da mein Spieler die Koordinaten bzw. Spielfeld Pos. immer steigend ist, steigen auch 
	 * meine Variablen, sie passen sich meiner Spieler Figur an)
	 * 
	 * @author Seyma Keser
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

	
	public void paint(Graphics g) {
		if (anfangszustand==0){
			this.posSpieler();
			this.genMonster();
			anfangszustand++;
		}
		// Beim neuzeichnen wird zunaechst alles uebermalt
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
//		for(int j=0; j<16;j++){
//			for(int i=0; i<=16; i++){
//			g.drawImage(hintergrund1, j*fenster.BOX, i*fenster.BOX, null);
//			}
//		}
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
					
					if (fenster.Level.getBestimmtenLevelInhalt(i, j) == 0) { //WAND
						// Hier kommt eine Wand hin
						g.drawImage(wand, i * fenster.BOX-verschiebenx*fenster.BOX, j * fenster.BOX-verschiebeny*fenster.BOX,
								null);
							//Boden=1  5= Monster
					} else if (fenster.Level.getBestimmtenLevelInhalt(i, j) == 1) {
						// Dieses Feld ist begehbar
						g.drawImage(boden, i * fenster.BOX-verschiebenx*fenster.BOX, j * fenster.BOX-verschiebeny*fenster.BOX,
								null);
					}
					else if (fenster.Level.getBestimmtenLevelInhalt(i, j)== 3){ 
						g.drawImage(boden, i * fenster.BOX-verschiebenx*fenster.BOX, j * fenster.BOX-verschiebeny*fenster.BOX,
								null);
//						if (Gegner ==null){
//						 Gegner = new Monster(i, j, fenster, 0);
//						fenster.monsterListe.add(Gegner);
////						fenster.monsterListe.add(Gegner);
////						Monster m = fenster.monsterListe.get(i);
////						boolean event = fenster.spieler.hatSchluessel();
//						g.drawImage(boden, i * fenster.BOX-verschiebenx*fenster.BOX, j * fenster.BOX-verschiebeny*fenster.BOX,
//								null);}
//						drawMonster(g, Gegner);
						//2== Start 
					} 
					else if (fenster.Level.getBestimmtenLevelInhalt(i, j) == 2) {
						// Hier liegt ein Schluessel
						g.drawImage(boden, i * fenster.BOX-verschiebenx*fenster.BOX, j * fenster.BOX-verschiebeny*fenster.BOX,
								null);
						
						g.drawImage(tuerOffen, i * fenster.BOX-verschiebenx*fenster.BOX, j
								* fenster.BOX-verschiebeny*fenster.BOX, null);
						
					
						//Geschlossene Tür
					} else if (fenster.Level.getBestimmtenLevelInhalt(i, j) == 6) {
						// Hier ist die Tuer
						g.drawImage(boden, i * fenster.BOX-verschiebenx*fenster.BOX, j * fenster.BOX-verschiebeny*fenster.BOX,
								null);
							g.drawImage(tuerZu, i * fenster.BOX-verschiebenx*fenster.BOX, j
									* fenster.BOX-verschiebeny*fenster.BOX, null);
							//offene Tür
					} else if (fenster.Level.getBestimmtenLevelInhalt(i, j) == 7 ) {
						// Hier ist die Tuer
						g.drawImage(boden, i * fenster.BOX-verschiebenx*fenster.BOX, j * fenster.BOX-verschiebeny*fenster.BOX,
								null);
							g.drawImage(trank, i * fenster.BOX-verschiebenx*fenster.BOX, j
									* fenster.BOX-verschiebeny*fenster.BOX, null);
//							//Heiltrank
							
					}else if (fenster.Level.getBestimmtenLevelInhalt(i, j) == 4) {
						
						g.drawImage(boden, i * fenster.BOX-verschiebenx*fenster.BOX, j * fenster.BOX-verschiebeny*fenster.BOX,
								null);
						g.drawImage(heiltrank, i * fenster.BOX-verschiebenx*fenster.BOX,
								j * fenster.BOX-verschiebeny*fenster.BOX, null);
					}else if (fenster.Level.getBestimmtenLevelInhalt(i, j) == 5) {
						
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
	 * @author Seyma Keser
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
	 * 
	 * @author Seyma Keser
	 */
	private boolean inRange(int i, int j) {	
		return (Math.sqrt(Math.pow(fenster.spieler.getXPos() - i, 2)
				+ Math.pow(fenster.spieler.getYPos() - j, 2)) < 3 || !fenster.nebelAn);

	

	}
	
}
