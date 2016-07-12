package pp2016.team13.client.engine;
import java.io.Serializable;
// Nachricht, die ein Level enth�lt (wird am Anfang vom Server empfangen)
public class LevelNachricht extends Nachricht implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Erstellt eine LevelNachricht, die alle Level der Sitzung enthaelt. Wird am Anfang der Sitzung vom Server an den Client geschickt.
	 * 
	 * @author Julius
	 * @param x: Array mit Leveldaten
	 */
	public LevelNachricht (int[][][] levelDaten){
		super(6);
		this.leveldaten = levelDaten;
	}
}
