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


/**
 * Seitenleiste beeinhaltet, die Minimap, die Statusleiste und das Chatfenster das von einer 
 * anderen Klasse geaddet wird
 * 
 * @author <Keser, Seyma, 5979919>
 *
 */
public class SeitenLeiste extends JPanel {
	
	JTextField ChatText;

	private static final long serialVersionUID = 1L;
	
	//Bild Elemente werden erstellt
	private Image boden2, wand2, tuerOffen2, tuerZu2, heiltrank2, schluessel2, john, sblase, hintergrund1, heiltrankblau ;
	ChatFenster p;
	HindiBones fenster;
	
	/**
	 * Wird an das Haupfenster gebunden
	 * Zeichnet groeßtenteils nur
	 * 
	 * @author <Keser, Seyma, 5979919>
	 * @param fenster: Wird im HindiBones fenster erstellt
	 */
	public SeitenLeiste(HindiBones fenster) {
		this.fenster = fenster;
		 //Benutzer Name Eingabe Feld
		 ChatText = new JTextField(); //Erzeugen eines Textfeldes fuer Nicknamen
		p= new ChatFenster("Chat", this);
		
		this.setLayout(new BorderLayout());
		this.add(p,BorderLayout.SOUTH);
		 
		 //Bilder werden geladen
		try {
			sblase= ImageIO.read(new File("img//sprechblase.png"));
			hintergrund1= ImageIO.read(new File("img/wall1.png"));
			schluessel2=ImageIO.read(new File("img//schluessel2.png"));
			heiltrank2 = ImageIO.read(new File("img//heiltrank2.png"));
			heiltrankblau = ImageIO.read(new File("img//heiltrankpanel.png"));
			boden2 = ImageIO.read(new File("img//boden11.png"));
			wand2 = ImageIO.read(new File("img//wandklein.png"));
			tuerZu2 = ImageIO.read(new File("img//tuer.png"));
			tuerOffen2 = ImageIO.read(new File("img//tueroffen.png"));
			john= ImageIO.read(new File("img//John1.png"));

			
			} catch (IOException e) {
			System.err.println("Ein Bild konnte nicht geladen werden.Hier Spechblase!");
		}

		//Standardeinstellung
		this.setVisible(true);

	}
	
	/**
	 * Getter Klasse fuer Chatfenster 
	 * @author <Keser, Seyma, 5979919>
	 * @return Chatfenster
	 */
	public ChatFenster getChatFenster(){
		return p;
	}
	
	/**
	 * Zeichnet die Einzelnen Elemente der Seitenleiste
	 * 
	 * @author <Keser, Seyma, 5979919>
	 */
	public void paint(Graphics g) {
		
		this.setLayout(new BorderLayout());
	
		g.fillRect(0, 0, 180, 180 );
		g.setColor(Color.GRAY);
		
		// Zeichnen der Minimap
		if(true){
		// Male die einzelnen Felder 
		for (int i = 0; i < fenster.WIDTH; i++) {
			for (int j = 0; j < fenster.HEIGHT; j++) {
				//if (inRange(i, j)) {
					if (fenster.level.getBestimmtenLevelInhalt(i, j) == 0) {
						// Hier kommt eine Wand hin
						g.drawImage(wand2, i * fenster.BOX2, j * fenster.BOX2,
								null);
					}
					else if (fenster.level.getBestimmtenLevelInhalt(i, j) == 1  || fenster.level.getBestimmtenLevelInhalt(i, j) == 5 || fenster.level.getBestimmtenLevelInhalt(i, j) == 4 || fenster.level.getBestimmtenLevelInhalt(i, j)==7) {
						// Die Begehbaren Elemente 
						g.drawImage(boden2, i * fenster.BOX2, j * fenster.BOX2,
								null);
					} 
					else if (fenster.level.getBestimmtenLevelInhalt(i, j) == 3) {
						// Auf Monster Pos. kommen Boden Elemente
						g.drawImage(boden2, i * fenster.BOX2, j * fenster.BOX2,
								null);
					}
					if ( fenster.level.getBestimmtenLevelInhalt(i, j) == 2 ){
						//Anfangs Punkt + wird eine offene Tuer gesetzt
						g.drawImage(boden2, i * fenster.BOX, j * fenster.BOX,
								null);	
						g.drawImage(tuerOffen2, i * (fenster.BOX2) , j
									* (fenster.BOX2), null);
					}
					if(fenster.level.getBestimmtenLevelInhalt(i, j) == 6){
						//Geschlossene Tuer gesetzt
						g.drawImage(boden2, i * fenster.BOX, j * fenster.BOX,
								null);	
						g.drawImage(tuerZu2, i * (fenster.BOX2), j
									* (fenster.BOX2), null);
					}		
			}

	}
		
		//Roter Punkt- fuer Hindi Bones
		g.drawImage(fenster.spieler2.getImage(), fenster.spieler.getXPos()
				* fenster.BOX2, fenster.spieler.getYPos() * fenster.BOX2, null);

		//Alles unter der Statusleiste
		g.setColor(Color.LIGHT_GRAY);

		 
		
		try {
			Thread.sleep(50);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Standarteinstellung
		this.setVisible(true);
	
		
		//Hier beginnt der des Statuspanels
		//Hintergrund wird gesetzt
		g.setColor(Color.BLACK);
		g.fillRect(0, 180, 180, 180);
		int Hintergrundpixel= 192;
		for (int i = 0; i < fenster.WIDTH; i++) {
			for(int j=0; j<8;j++){
			g.drawImage(hintergrund1, i * Hintergrundpixel, 170+j*Hintergrundpixel, null);

		}
		}
		
		int itemKy= 210;
		int itemKx= 10;
		int Luecke=60;
		
		//Item Hintergrund Kaestchen 3x (Falls spaeter noch ein Item hinzugefuegt wird)
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

		//Mini John neben Lebensleiste wird gezeichnet
		g.drawImage(john,20,itemKy+60 , fenster.BOX3,
				fenster.BOX3 , null);
		//Schluessel Element wird gezeichnet
		if (fenster.spieler.hatSchluessel()) {
			g.drawImage(schluessel2, itemKx+3+Luecke*2-15, itemKy, null);
			
		}
		
		g.setColor(Color.WHITE);
		//Zeit und Level Anzeige wird gezeichnet
		g.drawString(fenster.spieler.getName(), fenster.BOX + 2, itemKy+75);
		g.drawString("Zeit: "
				+ (System.currentTimeMillis() - fenster.startZeit) / 1000,
				10, 190);
		g.drawString("Level " + (fenster.levelnummer+1) + "/" + fenster.MAXLEVEL,
				 100, 190);

		// Heiltrankanzeige
		int anzahlTrank=fenster.spieler.getAnzahlTrank();
		int anzahlHeiltraenke = fenster.spieler.getAnzahlHeiltraenke();
		String s;
		
		//Gruener Heiltrank
		if (anzahlHeiltraenke < 10)
			s = "  " + anzahlHeiltraenke;
		else
			s = String.valueOf(anzahlHeiltraenke);
		g.drawString(s, itemKx-5, itemKy+12);
		g.drawImage(heiltrank2, itemKx-2,itemKy, null);//63
		
		//Blauer Schutztrank
		if (anzahlTrank < 10)
			s = "  " + anzahlTrank;
		else
			s = String.valueOf(anzahlTrank);
		g.drawString(s, itemKx-5-5+Luecke, itemKy+12);
		g.drawImage(heiltrankblau, itemKx-7+Luecke,itemKy, null);

		
		//Sprechblase + Text bei Aktionen
		int feld = fenster.level.getBestimmtenLevelInhalt(fenster.spieler.getXPos(),fenster.spieler.getYPos());
		g.setColor(Color.BLACK);
		if (feld == 8) {
			g.drawImage(sblase,0,itemKy*2-130,null);
			g.drawString(" Nimm den Schluessel", itemKx, itemKy*2-130+40);
		} else if (feld == 6) {
			
				if (fenster.spieler.hatSchluessel()){
					g.drawImage(sblase,0,itemKy*2-130,null);
					g.drawString("Oeffne die Tuer",itemKx, itemKy*2-130+40);}
				else{
				
					g.drawImage(sblase,0,itemKy*2-130,null);
					g.drawString("Tuer ist verschlossen!", itemKx, itemKy*2-130+40);}
		} else if (feld == 4 || feld ==7) {
			g.drawImage(sblase,0,itemKy*2-130,null);
			g.drawString(" Ein Trank !", itemKx,itemKy*2-130+40);
		}

		
		//Lebensleiste Positioniert und gezeichnet	
		g.setColor(Color.RED);
		g.fillRect(50, itemKy+80,
				fenster.spieler.getMaxHealth(), 5);
		g.setColor(Color.GREEN);
		g.fillRect(50, itemKy+80,
				fenster.spieler.getLebenspunkte(), 5);

		
		//Positionierung des Chat panels
		this.add(ChatText);
		 ChatText.setBounds(0,410,210,35);//Groesse+Koord. wird festgelegt 
		 ChatText.setFocusable(true);
		 this.setVisible(true);
		 


		}
	
	
	}
	

}

