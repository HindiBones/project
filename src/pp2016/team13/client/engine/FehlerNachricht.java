package pp2016.team13.client.engine;

// Fehlermeldung, String enthaelt die Fehlermeldung in Textform
public class FehlerNachricht extends Nachricht{
	/**
	 * @author Julius
	 * @param fehler: Fehlermeldung in Textform
	 * 
	 * Erstellt ein FehlerNachricht-Objekt. Enthaelt eine Fehlermeldung in Textform.
	 */
	public FehlerNachricht (String fehler){
		super(5);
		this.fehlermeldung = fehler;
	}
}
