package pp2016.team13.client.engine;

// Fehlermeldung, String enthaelt die Fehlermeldung in Textform
public class FehlerNachricht extends Nachricht{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Erstellt ein FehlerNachricht-Objekt. Enthaelt eine Fehlermeldung in Textform.
	 * 
	 * @author Julius
	 * @param fehler: Fehlermeldung in Textform
	 */
	public FehlerNachricht (String fehler){
		super(5);
		this.fehlermeldung = fehler;
	}
}
