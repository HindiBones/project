package Client;

// Fehlermeldung, String enthaelt die Fehlermeldung in Textform
public class FehlerNachricht extends Nachricht{
	public FehlerNachricht (String fehler){
		super(5);
		this.fehlermeldung = fehler;
	}
}
