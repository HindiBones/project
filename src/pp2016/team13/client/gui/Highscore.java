package pp2016.team13.client.gui;

import java.awt.Color;   
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * Highscore Liste wird erstellt
 * 
 * Wurde grossenteils uebernommen vom urspruenglichen code einige ergaenzungen 
 * wurden hinzugefuegt
 * 
 * @author <Keser, Seyma, 5979919 >
 * @author <unbekannt>
 *
 */
public class Highscore extends JPanel {

	private static final long serialVersionUID = 1L;
	public int letztername=-1;
	private LinkedList<HighScoreElement> highScore;

	
	/**
	 * Liste wird erzeugt mit Highscore elementen 
	 * 
	 * @author <Keser, Seyma, 5979919>
	 * @author <unbekannt>
	 */
	public Highscore() {

		highScore = new LinkedList<HighScoreElement>();

		//Highscore Liste wird erzeugt
		try {
			FileReader reader = new FileReader(new File("highscore.txt"));
			int c;
			String line = "";

			while ((c = reader.read()) != -1) {

				if (c == '\n') {
					String[] temp = line.split("\t");
					highScore.add(new HighScoreElement(Integer.parseInt(temp[0]
							.trim()), temp[1].trim()));
					line = "";
				} else {
					line += (char) c;
				}
			}

			reader.close();

		} catch (IOException e) {
			System.out.println("Highscore konnte nicht gelesen werden");
		}
		while (highScore.size() < 10) {
			highScore.add(new HighScoreElement(1000, "Anonym"));
		}
	}

	
	/**
	 * Hinzugefuegen des Spielers in die Highscore Liste
	 * 
	 * Ergaeunzt um eine Variable letztername die die Position in 
	 * der Hightscore Listte des Letzten Spielers speichern soll
	 * 
	 * @author <Keser, Seyma, 5979919>
	 * @author <unbekannt>
	 * @param zeit: Zeit sind Punkte des Spielers
	 * @param name: Benutzername der eingelogt wird, dessen name wird uebernommen
	 */
	public void addSpielerToHighScore(int zeit,String name) {

		for (int i = 0; i < highScore.size(); i++) {
			if (highScore.get(i).zeit > zeit) {
				highScore.add(i, new HighScoreElement(zeit, name));
				letztername=i;
				i = highScore.size();
			}
		}

		try {
			FileWriter writer = new FileWriter(new File("highscore.txt"));
			for (int i = 0; i < 10; i++) {
				writer.write(highScore.get(i).zeit + "\t"
						+ highScore.get(i).name + "\n");
			}
			writer.close();
			
		} catch (IOException e) {
			System.out.println("Highscore konnte nicht geschrieben werden");
		}

	}
	/**
	 * @author <unbekannt>
	 * @return highScore: Highscore Elemente werden ausgegeben
	 */
	public LinkedList<HighScoreElement> getHighScore() {
		return highScore;
	}

	/**
	 * Zeichnet die Ganze Highscore Ansicht 
	 * Methode wurde nur ergaenzt um einige Zeile um den letzten Eintrag Rot zu markieren
	 * 
	 * @author <Keser, Seyma, 5979919>
	 * @author <unbekannt>
	 * 
	 */
	public void paint(Graphics g) {
		Image img = null, boden = null;

		try {
			img = ImageIO.read(new File("img//highscoreText.png"));
			boden = ImageIO.read(new File("img/highscore.jpg"));

		} catch (IOException e) {
		} 
		g.drawImage(boden, 0,0, null);
		g.drawImage(img, 20, 0, null);
		g.setColor(Color.WHITE);

		for (int i = 0; i < 10; i++) {

			String name = highScore.get(i).name;
			int zeit = highScore.get(i).zeit;

			g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
			//markiert letzten Namen Rot
			if(letztername>0){
				if(letztername==i){
				g.setColor(Color.RED);
				g.drawString((i + 1) + ".  " + name, 80, 130 + 30 * (i + 1));
				g.drawString("" + zeit, 400, 130 + 30 * (i + 1));

				}else{
					g.setColor(Color.WHITE);
					g.drawString((i + 1) + ".  " + name, 80, 130 + 30 * (i + 1));
					g.drawString("" + zeit, 400, 130 + 30 * (i + 1));
				}
				
			}else {
			g.drawString((i + 1) + ".  " + name, 80, 130 + 30 * (i + 1));
			g.drawString("" + zeit, 400, 130 + 30 * (i + 1));
			}
		}
	}
}
	/**
	 * @author <unbekannt>
	 *
	 */
	class HighScoreElement {
		String name;
		int zeit;

	/**
	 * @author <unbekannt>
	 *
	 */
	public HighScoreElement(int punkte, String name) {
		this.name = name;
		this.zeit = punkte;
	}

}
