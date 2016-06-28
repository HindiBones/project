package	Client;

public class LoginNachricht extends Nachricht {

	// Einlognachricht, sendet eingegebenen Benutzernamen und Passwort an den Server, um zu testen, ob der User mit dem Passwort existiert
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
