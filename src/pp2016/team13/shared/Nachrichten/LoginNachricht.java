package pp2016.team13.shared.Nachrichten;

/**
 * LoginNachricht wird beim Einloggen des Benutzers verschickt, um diesen anzumelden oder zu registrieren.
 * 
 * @author <Braun, Jan Julius, 6000100>
 *
 */
public class LoginNachricht extends Nachricht {

	private static final long serialVersionUID = 1L;

	// Einlognachricht, sendet eingegebenen Benutzernamen und Passwort an den
	// Server, um zu testen, ob der User mit dem Passwort existiert
	/**
	 * Erstellt eine LoginNachricht (Typ 0), die Benutzername und Passwort enthalten.
	 * 
	 * @param name
	 *            : Benutzername
	 * @param pw
	 *            : Passwort
	 * @param typ
	 * 			  : Logintyp (0 für Einloggen, 1 für Registrieren)   
	 *         
	 * @author <Braun, Jan Julius, 6000100>           
	 */
	public LoginNachricht(String name, String pw, int typ) {
		super(0);
		this.benutzername = name;
		this.passwort = pw;
		this.logintyp = typ;
	}

}
