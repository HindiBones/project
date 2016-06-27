package gui;

import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

import datenstruktur.Boden;
import datenstruktur.Level;
import datenstruktur.Monster;
import datenstruktur.Schluessel;
import datenstruktur.Spielelement;
import datenstruktur.Tuer;
import datenstruktur.Wand;

public class Leser {

	private String dateiname;
	private int[][] karte;
	Level level;
	private HindiBones fenster;

	public Leser(String fileName, HindiBones fenster) {
		this.fenster = fenster;
		this.dateiname = fileName;

		readLevel();
	}

	private void readLevel() {
		try {
			FileReader reader = new FileReader(dateiname);

			int i = 0, j = 0, c;

			karte = new int[fenster.WIDTH][fenster.HEIGHT];
			fenster.monsterListe = new LinkedList<Monster>();

			while ((c = reader.read()) != -1) {
				if (c == '\n') {
					i = 0;
					j++;
				} else {
					if (c >= '0' && c <= '9') {

						switch (c) {
						case '0':
							karte[i][j] = 0;
							break;
						case '1':
							karte[i][j] = 1;
							break;
						case '2':
							karte[i][j] = 2;
							break;
						case '3':
							karte[i][j] = 6;
							break;
						case '4':
							karte[i][j] = 6;
							fenster.spieler.setPos(i, j);
							fenster.spieler2.setPos(i,j);
							break;
						case '5':
							karte[i][j] = 5;
							fenster.monsterListe.add(new Monster(i, j, fenster,
									0));
							break;
						// Monster, welche erst nach dem Aufheben des
						// Schluessels erscheinen
						case '6':
							karte[i][j] = 5;
							fenster.monsterListe.add(new Monster(i, j, fenster,
									1));
							break;
						}
						i++;
					}

				}

			}

			reader.close();

		} catch (IOException e) {
			System.err.println(e);
		}
	}

	public Level getLevel() {
		return new Level(0, karte);
	}
}
