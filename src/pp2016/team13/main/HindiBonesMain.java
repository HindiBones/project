package pp2016.team13.main;

import pp2016.team13.client.gui.HindiBones;

/*
 * Team 13: Ocean 13
 * 
 * Akbulut, Cigdem, 5382718
 * Braun, Julius, 6000100
 * Fiehn, Marius, 6024602
 * Jeyakumar, Kirthika, 7302822
 * Keser, Seyma, 5979919
 * 
 * 
 */

public class HindiBonesMain {

	public static final int BOX = 32;
	public static final int WIDTH = 16, HEIGHT = 16;

	public static void main(String[] args) {
		try {
			Thread.sleep(1000);
			new HindiBones(BOX * WIDTH, BOX * HEIGHT, "Hindi Bones");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
