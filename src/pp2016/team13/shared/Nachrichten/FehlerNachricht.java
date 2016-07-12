package pp2016.team13.shared.Nachrichten;

/**
 * Fehlermeldung, String enthaelt die Fehlermeldung in Textform
 * 
 * @author <Braun, Jan Julius, 6000100>
 *
 */
public class FehlerNachricht extends Nachricht {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Erstellt ein FehlerNachricht-Objekt. Enthaelt eine Fehlermeldung in
	 * Textform.
	 * 
	 * @param fehler
	 *            : Fehlermeldung in Textform
	 *            
	 * @author <Braun, Jan Julius, 6000100>
	 */
	public FehlerNachricht(String fehler) {
		super(5);
		this.fehlermeldung = fehler;
	}
}
