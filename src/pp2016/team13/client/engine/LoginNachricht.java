package	pp2016.team13.client.engine;

public class LoginNachricht extends Nachricht {

	// Einlognachricht, sendet eingegebenen Benutzernamen und Passwort an den Server, um zu testen, ob der User mit dem Passwort existiert
	/**
	 * @author Julius
	 * @param name: Benutzername
	 * @param pw: Passwort
	 * 
	 * Erstellt eine LoginNachricht, die Benutzername und Passwort enthalten.
	 */
	public LoginNachricht (String name, String pw){
		super(0);
		this.benutzername = name;
		this.passwort = pw;
	}
	
//	public String GetBenutzername(){
//		return benutzername;
//	}
//	public String GetPasswort(){
//		return passwort;
//	}

}
