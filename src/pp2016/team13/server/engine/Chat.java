package pp2016.team13.server.engine;

public class Chat {
	static int anzahlNachrichten;
	static String chat[];
	public Chat (){
		chat = new String [20];
		for (int i = 0 ; i < chat.length ; i++){
			chat[i] = null;
		}
	}
		/**
		 * @author Marius
		 * @param nachricht: ankommende Chat Nachricht
		 * 
		 * Nachricht zum Chat hinzufügen
		 */
	public static boolean nachrichtEmpfanden(String nachricht){
		chat[anzahlNachrichten] = nachricht;
		anzahlNachrichten++;
		return true;
	
	}
	/**
	 * @author Marius
	 * 
	 * Chatverlauf senden
	 */
	public static String [] chat(){
		return chat;
	}
	
	/**
	 * @author Marius
	 * 
	 * Anzahl der bisher gesendeten Chat Nachrichten wird festgelegt
	 */
	public static void setAnzahlNachrichten(int i) {
		anzahlNachrichten = i;
		
	}
	
}
