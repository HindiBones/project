package pp2016.team13.client.engine;

public class ChatNachricht extends Nachricht {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Nachrichten-Objekt, das eine Nachricht in Textform enth�lt, die im Chat
	 * gesendet wurde
	 * 
	 * @author Julius
	 * @param Nachricht
	 *            : Nachricht in Textform, die gesendet werden soll
	 */
	public ChatNachricht(String Nachricht) {
		super(8);
		this.nachricht = Nachricht;
	}

	public boolean istCheat() {
		boolean istCheat = false;

		switch (this.nachricht) {

		case ("<#unbesiegbar>"):
			istCheat = true;
			break;
		case ("<#durchsicht>"):
			istCheat = true;
			break;
		case ("<#levelskip>"):
			istCheat = true;
			break;
		case ("<#heiltrank>"):
			istCheat = true;
			break;
		case ("<#trank>"):
			istCheat = true;
			break;
		case ("<#schluessel>"):
			istCheat = true;
			break;
		}

		return istCheat;
	}

	public int getCheat() {
		int ergebnis = 0;
		if (this.istCheat()) {
			switch (this.nachricht) {
			case ("<#unbesiegbar>"):
				ergebnis = 1;
				break;
			case ("<#durchsicht>"):
				ergebnis = 2;
				break;
			case ("<#levelskip>"):
				ergebnis = 3;
				break;
			case ("<#heiltrank>"):
				ergebnis = 4;
				break;
			case ("<#trank>"):
				ergebnis = 5;
				break;
			case ("<#schluessel>"):
				ergebnis = 6;
				break;
			}
		}

		return ergebnis;
	}
}
