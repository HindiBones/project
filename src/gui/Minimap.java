package gui;


import java.awt.BorderLayout; 
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JTextField;

import datenstruktur.Boden;
import datenstruktur.Heiltrank;
import datenstruktur.Schluessel;
import datenstruktur.Spielelement;
import datenstruktur.Tuer;
import datenstruktur.Wand;




public class Minimap extends JPanel {
	
	JTextField ChatText;

	private static final long serialVersionUID = 1L;

	private Image boden2, wand2, tuerOffen2, tuerZu2,hintergrund, heiltrank2, schluessel2, john, sblase;

	private HindiBones fenster;
	public Minimap(HindiBones fenster) {
		this.fenster = fenster;
		 //Benutzer Name Eingabe Feld
		 ChatText = new JTextField(); //Erzeugen eines Textfeldes f�r Nicknamen
		

		 

		try {
			hintergrund = ImageIO.read(new File("img//status.png"));
			schluessel2=ImageIO.read(new File("img//schluessel2.png"));
			heiltrank2 = ImageIO.read(new File("img//heiltrank2.png"));
			boden2 = ImageIO.read(new File("img//boden.png"));
			wand2 = ImageIO.read(new File("img//wand.png"));
			tuerZu2 = ImageIO.read(new File("img//tuer.png"));
			tuerOffen2 = ImageIO.read(new File("img//tueroffen.png"));
			john= ImageIO.read(new File("img//john.png"));
			sblase= ImageIO.read(new File("img//sprechblase.png"));
			} catch (IOException e) {
			System.err.println("Ein Bild konnte nicht geladen werden.");
		}

		
		this.setVisible(true);

	}

	public void paint(Graphics g) {
		// Zeichnen der Minimap
		this.setLayout(new BorderLayout());

		g.fillRect(0, 0, 180, 180 );
		g.setColor(Color.GRAY);
		
		if(true){
		
		// Male die einzelnen Felder 
		for (int i = 0; i < fenster.WIDTH; i++) {
			for (int j = 0; j < fenster.HEIGHT; j++) {
				//if (inRange(i, j)) {
					if (fenster.Level.getBestimmtenLevelInhalt(i, j) == 0) {
						// Hier kommt eine Wand hin
						g.drawImage(wand2, i * fenster.BOX2, j * fenster.BOX2,
								null);

					} else if (fenster.Level.getBestimmtenLevelInhalt(i, j) == 1) {
						// Dieses Feld ist begehbar
						g.drawImage(boden2, i * fenster.BOX2, j * fenster.BOX2,
								null);
					} 
	
					else if (fenster.Level.getBestimmtenLevelInhalt(i, j) == 6) {
						// Hier ist die Tuer
						
						g.drawImage(boden2, i * fenster.BOX2, j * fenster.BOX2,
								null);
//						if (((Tuer) fenster.level[i][j]).istOffen())
//							g.drawImage(tuerOffen2, i * 10 , j
//									* 10, null);
//						else
//							g.drawImage(tuerZu2, i * 10, j
//									* 10, null);
				} 
						

					
			}

		}
		
		//Roter Punkt- f�r Hindi Bones
		g.drawImage(fenster.spieler2.getImage(), fenster.spieler.getXPos()
				* fenster.BOX2, fenster.spieler.getYPos() * fenster.BOX2, null);

		//Alles unter der Statusleiste
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 360, 180, 300);
		g.drawImage(john,35,410,null);
		

		
		try {
			Thread.sleep(50);

			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		this.setVisible(true);
	
		
		//Hier beginnt der des Statuspanels
		g.setColor(Color.BLACK);
		g.fillRect(0, 180, 180, 180);

		for (int i = 0; i < fenster.WIDTH; i++) {
			for(int j=0; j<6;j++){
			g.drawImage(hintergrund, i * fenster.BOX3, 180+j*fenster.BOX3, null);

		}
			
			
			}
		//Item Hintergrund K�stchen 3x (Falls sp�ter noch ein Item hinzugef�gt wird)
		g.setColor(Color.GRAY);
		g.fillRect(10, 210,40, 40);
		g.setColor(Color.BLACK);
		g.drawRect(10, 210,40, 40);
		g.setColor(Color.GRAY);
		g.fillRect(10+55, 210,40, 40);
		g.setColor(Color.BLACK);
		g.drawRect(10+55, 210,40, 40);
		g.setColor(Color.GRAY);
		g.fillRect(65+55, 210,40, 40);
		g.setColor(Color.BLACK);
		g.drawRect(65+55, 210,40, 40);

		g.drawImage(fenster.spieler.getImage(),5,340 , fenster.BOX3,
				fenster.BOX3 , null);

		if (fenster.spieler.hatSchluessel()) {
			g.drawImage(schluessel2, 12, 210, null);
			
		}

		g.setColor(Color.WHITE);
		g.drawString(fenster.spieler.getName(), fenster.BOX + 2, 350);
		g.drawString("Zeit: "
				+ (System.currentTimeMillis() - fenster.startZeit) / 1000,
				10, 200);
		g.drawString("Level " + fenster.currentLevel + "/" + fenster.MAXLEVEL,
				 100, 200);

		// Heiltrankanzeige
		int anzahlHeiltraenke = fenster.spieler.getAnzahlHeiltraenke();
		String s;
		if (anzahlHeiltraenke < 10)
			s = "  " + anzahlHeiltraenke;
		else
			s = String.valueOf(anzahlHeiltraenke);
		g.drawString(s, 60, 222);
		g.drawImage(heiltrank2, 63,210, null);

		Spielelement feld = fenster.level[fenster.spieler.getXPos()][fenster.spieler
				.getYPos()];
		g.setColor(Color.BLACK);
		if (feld instanceof Schluessel) {
			g.drawImage(sblase,0,375,null);
			g.drawString(" Nimm den Schl�ssel", 7, 400);
		} else if (feld instanceof Tuer) {
			if (!((Tuer) feld).istOffen()) {
				
				if (fenster.spieler.hatSchluessel()){
					g.drawImage(sblase,0,375,null);
					g.drawString("�ffne die T�r",7, 400);}
				else{
					g.drawImage(sblase,0,375,null);
					g.drawString("T�r ist verschlossen!", 7, 400);}
			}
		} else if (feld instanceof Heiltrank) {
			g.drawImage(sblase,0,375,null);
			g.drawString(" Ein Heiltrank !", 7,400);
		}
		
		
		//Lebensleiste Positioniert und gezeichnet	
		g.setColor(Color.RED);
		g.fillRect(50, 355,
				fenster.spieler.getMaxHealth(), 5);
		g.setColor(Color.GREEN);
		g.fillRect(50, 355,
				fenster.spieler.getHealth(), 5);

		
		this.add(ChatText);
		 ChatText.setBounds(0,410,230,35);//Gr��e+Koord. wird festgelegt 
		 ChatText.setFocusable(true);
		 this.setVisible(true);

		}
	
	
	}
	
	
//(Hier, noch unentschlossen ob wir als Team auf Neben in der Minimap oder nicht)

//	private boolean inRange(int i, int j) {
//		return (Math.sqrt(Math.pow(fenster.spieler2.getXPos() - i, 2)
//				+ Math.pow(fenster.spieler2.getYPos() - j, 2)) < 3 || !fenster.nebelAn);
//	}
	

}

