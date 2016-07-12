package pp2016.team13.shared.Nachrichten;

/**
 * AntwortNachricht, die einen Boolean enthaelt, ob die Clientanfrage akzeptiert wurde
 * 
 * @author <Braun, Jan Julius, 6000100>
 *
 */
public class AntwortNachricht extends Nachricht {

	private static final long serialVersionUID = 1L;

	/**
	 * Erstellt ein AntwortNachricht-Objekt.
	 * 
	 * @param reaktion
	 * 				  : Boolean, ob die Clientanfrage in Ordnung war
	 * 
	 * @author <Braun, Jan Julius, 6000100>
	 */
	public AntwortNachricht(boolean reaktion) {
		super(11);
		this.inOrdnung = reaktion;
	}
}
