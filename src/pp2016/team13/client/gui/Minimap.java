package pp2016.team13.client.gui;


import java.awt.BorderLayout; 
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JTextField;

import pp2016.team13.shared.Boden;
import pp2016.team13.shared.Heiltrank;
import pp2016.team13.shared.Schluessel;
import pp2016.team13.shared.Spielelement;
import pp2016.team13.shared.Tuer;
import pp2016.team13.shared.Wand;




public class Minimap extends JPanel {
	
	JTextField ChatText;

	private static final long serialVersionUID = 1L;

	private Image boden2, wand2, tuerOffen2, tuerZu2,hintergrund, heiltrank2, schluessel2, john, sblase, hintergrund1, heiltrankblau ;
	ChatFenster p;
	private HindiBones fenster;
	public Minimap(HindiBones fenster) {
		this.fenster = fenster;
		 //Benutzer Name Eingabe Feld
		 ChatText = new JTextField(); //Erzeugen eines Textfeldes f�r Nicknamen
		p= new ChatFenster("Chat", this);
//		p.setBounds(100, 500, 40, 40);
//		JPanel Center= new JPanel();
//		Center.setVisible(false);
		//this.add(Center, BorderLayout.CENTER);
		this.setLayout(new BorderLayout());
		 this.add(p,BorderLayout.SOUTH);
		 
		 
//		 p.add(this);
//		 

		 

		try {
			hintergrund = ImageIO.read(new File("img//status.png"));
			hintergrund1= ImageIO.read(new File("img/wall1.png"));
			schluessel2=ImageIO.read(new File("img//schluessel2.png"));
			heiltrank2 = ImageIO.read(new File("img//heiltrank2.png"));
			heiltrankblau = ImageIO.read(new File("img//heiltrankpanel.png"));
			boden2 = ImageIO.read(new File("img//boden.png"));
			wand2 = ImageIO.read(new File("img//wand.png"));
			tuerZu2 = ImageIO.read(new File("img//tuer.png"));
			tuerOffen2 = ImageIO.read(new File("img//tueroffen.png"));
			john= ImageIO.read(new File("img//john.png"));
			sblase= ImageIO.read(new File("img//sprechblase.png"));
			} catch (IOException e) {
			System.err.println("Ein Bild konnte nicht geladen werden.Hier Spechblase??");
		}

		
		this.setVisible(true);

	}
	
	/**
	 * 
	 * @author Seyma Keser
	 */
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

					}
					else if (fenster.Level.getBestimmtenLevelInhalt(i, j) == 1 || fenster.Level.getBestimmtenLevelInhalt(i, j) == 2 || fenster.Level.getBestimmtenLevelInhalt(i, j) == 5 || fenster.Level.getBestimmtenLevelInhalt(i, j) == 4) {
						// Dieses Feld ist begehbar
						g.drawImage(boden2, i * fenster.BOX2, j * fenster.BOX2,
								null);
					} 
	
					else if (fenster.Level.getBestimmtenLevelInhalt(i, j) == 6) {
						// Hier ist die Tuer
						
						g.drawImage(boden2, i * fenster.BOX2, j * fenster.BOX2,
								null);
						
						
					}if (fenster.Level.getBestimmtenLevelInhalt(i, j) == 7 || fenster.Level.getBestimmtenLevelInhalt(i, j) == 2 ){
						//System.out.println("Sollte Offene Türe zechenen");
						g.drawImage(boden2, i * fenster.BOX, j * fenster.BOX,
								null);	
						g.drawImage(tuerOffen2, i * 11 , j
									* 10, null);
					} if(fenster.Level.getBestimmtenLevelInhalt(i, j) == 6){
						//System.out.println("Sollte Offene Türe zechenen");
						g.drawImage(boden2, i * fenster.BOX, j * fenster.BOX,
								null);	
						g.drawImage(tuerZu2, i * 11, j
									* 11, null);
			}
					
			}

		}
		
		//Roter Punkt- f�r Hindi Bones
		g.drawImage(fenster.spieler2.getImage(), fenster.spieler.getXPos()
				* fenster.BOX2, fenster.spieler.getYPos() * fenster.BOX2, null);

		//Alles unter der Statusleiste
		g.setColor(Color.LIGHT_GRAY);
//		g.fillRect(0, 360, 180, 300);
//		g.drawImage(john,35,410,null);
		
//		 p.setBounds(0, 500, 170, 105);
//		 this.add(p);
		 
		
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
int Hintergrundpixel= 192;
		for (int i = 0; i < fenster.WIDTH; i++) {
			for(int j=0; j<8;j++){
			g.drawImage(hintergrund1, i * Hintergrundpixel, 180+j*Hintergrundpixel, null);

		}
			
			
			}
		
		int itemKy= 220;
		int itemKx= 10;
		int Luecke=60;
		
		//Item Hintergrund K�stchen 3x (Falls sp�ter noch ein Item hinzugef�gt wird)
		g.setColor(Color.GRAY);
		g.fillRect(itemKx, itemKy,40, 40);
		g.setColor(Color.BLACK);
		g.drawRect(itemKx, itemKy,40, 40);
		g.setColor(Color.GRAY);
		g.fillRect(itemKx+55, itemKy,40, 40);
		g.setColor(Color.BLACK);
		g.drawRect(itemKx+55, itemKy,40, 40);
		g.setColor(Color.GRAY);
		g.fillRect(itemKx+55+55, itemKy,40, 40);
		g.setColor(Color.BLACK);
		g.drawRect(itemKx+55+55, itemKy,40, 40);

		//Mini John neben Lebensleiste
		g.drawImage(fenster.spieler.getImage(),20,itemKy+60 , fenster.BOX3,
				fenster.BOX3 , null);

		if (fenster.spieler.hatSchluessel()) {
			g.drawImage(schluessel2, itemKx+3+Luecke*2-15, itemKy, null);
			
		}

		g.setColor(Color.WHITE);
		g.drawString(fenster.spieler.getName(), fenster.BOX + 2, itemKy+75);
		g.drawString("Zeit: "
				+ (System.currentTimeMillis() - fenster.startZeit) / 1000,
				10, 200);
		g.drawString("Level " + fenster.currentLevel + "/" + fenster.MAXLEVEL,
				 100, 200);

		// Heiltrankanzeige
		int anzahlHeiltraenke = fenster.spieler.getAnzahlHeiltraenke();
		String s;
		
		//Grüner Heiltrank
		if (anzahlHeiltraenke < 10)
			s = "  " + anzahlHeiltraenke;
		else
			s = String.valueOf(anzahlHeiltraenke);
		g.drawString(s, itemKx-5, itemKy+12);
		g.drawImage(heiltrank2, itemKx-2,itemKy, null);//63
		
		//Blauer Heiltrank
		if (anzahlHeiltraenke < 10)
			s = "  " + anzahlHeiltraenke;
		else
			s = String.valueOf(anzahlHeiltraenke);
		g.drawString(s, itemKx-5-5+Luecke, itemKy+12);
		g.drawImage(heiltrankblau, itemKx-7+Luecke,itemKy, null);

		
		//Sprechblase + Text
		int feld = fenster.Level.getBestimmtenLevelInhalt(fenster.spieler.getXPos(),fenster.spieler.getYPos());
		g.setColor(Color.BLACK);
		if (feld == 4) {
			
			g.drawImage(sblase,0,itemKy*2-130,null);
			g.drawString(" Nimm den Schluessel", itemKx, itemKy*2-130+40);
		} else if (feld == 6) {
				if (fenster.spieler.hatSchluessel()){
					g.drawImage(sblase,0,itemKy*2-130,null);
					g.drawString("Oeffne die Tuer",itemKx, itemKy*2-130+40);}
				else{
					g.drawImage(sblase,0,itemKy*2-130,null);
					g.drawString("Tuer ist verschlossen!", itemKx, itemKy*2-130+40);}
		} else if (feld == 3) {
			g.drawImage(sblase,0,itemKy*2-130,null);
			g.drawString(" Ein Heiltrank !", itemKx,itemKy*2-130+40);
		}

		
		//Lebensleiste Positioniert und gezeichnet	
		g.setColor(Color.RED);
		g.fillRect(50, itemKy+80,
				fenster.spieler.getMaxHealth(), 5);
		g.setColor(Color.GREEN);
		g.fillRect(50, itemKy+80,
				fenster.spieler.getHealth(), 5);

		
		this.add(ChatText);
		 ChatText.setBounds(0,410,230,35);//Gr��e+Koord. wird festgelegt 
		 ChatText.setFocusable(true);
		 this.setVisible(true);
		 
		// this.add(p);

//		 p.setBounds(0,500,10,10);
//		 p.add(Chatfenster);
//		 
		 //Mini John und seine sprechblase


		}
	
	
	}
	
	
//(Hier, noch unentschlossen ob wir als Team auf Neben in der Minimap oder nicht)

//	private boolean inRange(int i, int j) {
//		return (Math.sqrt(Math.pow(fenster.spieler2.getXPos() - i, 2)
//				+ Math.pow(fenster.spieler2.getYPos() - j, 2)) < 3 || !fenster.nebelAn);
//	}
	

}

