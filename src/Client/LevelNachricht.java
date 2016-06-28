package Client;
import datenstruktur.Level;
// Nachricht, die ein Level enth�lt (wird am Anfang vom Server empfangen)
public class LevelNachricht extends Nachricht {
	public LevelNachricht (Level[] x){
		super(6);
		this.leveldaten = x;
	}
}
