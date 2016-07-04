package gui;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Steuerung extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * 	Bild für Steuerungs Erklärungen erstellt, wir bei Paint gezeichnet
	 * 
	 * @author Seyma Keser
	 */
	public void paint(Graphics g) {
		Image boden = null;

		try {
			boden = ImageIO.read(new File("img/hintergrund2.jpg"));
		} catch (IOException e) {
		}

				g.drawImage(boden, -14, 0, null);


	}

}
