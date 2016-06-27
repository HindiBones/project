package Client;

// Nachricht, die ein Level enthält (wird am Anfang vom Server empfangen)
public class LevelNachricht extends Nachricht {
	public LevelNachricht (Level x){
		super(6);
		this.leveldaten = x;
	}
}
