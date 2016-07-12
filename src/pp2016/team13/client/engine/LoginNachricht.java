package	pp2016.team13.client.engine;

public class LoginNachricht extends Nachricht {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Einlognachricht, sendet eingegebenen Benutzernamen und Passwort an den Server, um zu testen, ob der User mit dem Passwort existiert
	/**
	 * Erstellt eine LoginNachricht, die Benutzername und Passwort enthalten.
	 * 
	 * @author Julius
	 * @param name: Benutzername
	 * @param pw: Passwort
	 */
	public LoginNachricht (String name, String pw, int typ){
		super(0);
		this.benutzername = name;
		this.passwort = pw;
		this.logintyp = typ;
	}
	
//	public String GetBenutzername(){
//		return benutzername;
//	}
//	public String GetPasswort(){
//		return passwort;
//	}

}
