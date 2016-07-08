package pp2016.team13.client.engine;
import java.io.Serializable;

import pp2016.team13.shared.Level;
// Nachricht, die ein Level enth�lt (wird am Anfang vom Server empfangen)
public class LevelNachricht extends Nachricht implements Serializable{
	/**
	 * @author Julius
	 * @param x: Array mit Leveldaten
	 * 
	 * Erstellt eine LevelNachricht, die alle Level der Sitzung enthaelt. Wird am Anfang der Sitzung vom Server an den Client geschickt.
	 */
	public LevelNachricht (int[][] levelInhalt){
		super(6);
		this.leveldaten = levelInhalt;
	}
}
