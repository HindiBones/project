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
	public LevelNachricht (int[][][] levelDaten){
		super(6);
		for (int k = 0; k<5; k++)
		{

			for (int i = 0; i<15; i++){
				for (int j = 0; j<15 ; j++){
					levelDaten[k][i][j] = this.x[k].levelInhalt[i][j];
				}
			}
		}
	}
}
