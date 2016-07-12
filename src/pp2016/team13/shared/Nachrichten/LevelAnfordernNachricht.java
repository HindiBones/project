package pp2016.team13.shared.Nachrichten;

/**
 * LevelAnfordernNachricht wird beim Einloggen verschickt, um 5 Level vom Server
 * anzufordern
 * 
 * @author <Braun, Jan Julius, 6000100>
 */
public class LevelAnfordernNachricht extends Nachricht {

	private static final long serialVersionUID = 1L;

	/**
	 * Erstellt ein Objekt der Klasse LevelAnfordernNachricht (Typ 10)
	 * 
	 * @author <Braun, Jan Julius, 6000100>
	 * 
	 */
	public LevelAnfordernNachricht() {
		super(10);
	}
}
