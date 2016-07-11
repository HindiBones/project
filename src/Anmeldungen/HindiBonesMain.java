package Anmeldungen;

import pp2016.team13.client.gui.HindiBones;

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
