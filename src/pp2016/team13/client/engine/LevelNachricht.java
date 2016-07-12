package pp2016.team13.client.engine;

import java.io.Serializable;

	/**
	 * LevelNachricht, enthaelt ein Array mit den Levelinhalten aller Spielfelder der Sitzung.
	 * Wird vom Server versendet, wenn der Client Level anfordert.
	 * 
	 * @author <Braun, Jan Julius, 6000100>
	 *
	 */
public class LevelNachricht extends Nachricht implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Erstellt eine LevelNachricht, die alle Level der Sitzung enthaelt. Wird
	 * am Anfang der Sitzung vom Server an den Client geschickt.
	 * 
	 * @param x
	 *            : Array mit Leveldaten
	 *            
	 * @author <Braun, Jan Julius, 6000100>           
	 */
	public LevelNachricht(int[][][] levelDaten) {
		super(6);
		this.leveldaten = levelDaten;
	}
}
