package pp2016.team13.client.engine;
import pp2016.team13.shared.Level;
// Nachricht, die ein Level enth�lt (wird am Anfang vom Server empfangen)
public class LevelNachricht extends Nachricht {
	/**
	 * @author Julius
	 * @param x: Array mit Leveldaten
	 * 
	 * Erstellt eine LevelNachricht, die alle Level der Sitzung enthaelt. Wird am Anfang der Sitzung vom Server an den Client geschickt.
	 */
	public LevelNachricht (Level[] x){
		super(6);
		this.leveldaten = x;
	}
}
