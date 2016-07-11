package pp2016.team13.server.engine;

import java.util.ArrayList;

public class Chat {
	 static int anzahlNachrichten = 0;
	 ArrayList<String> chat;
	public Chat (){
		chat = new ArrayList<String>();
	}
		/**
		 * @author Marius
		 * @param nachricht: ankommende Chat Nachricht
		 * 
		 * Nachricht zum Chat hinzufügen
		 */
	public  boolean nachrichtEmpfangen(String nachricht){
		chat.add(nachricht);
		return true;
	
	}
	
	/**
	 * @author Marius
	 * 
	 * Chatverlauf senden
	 */
	public  String [] chat(){
		return (String[]) chat.toArray();
	}
	
	/**
	 * @author Marius
	 * 
	 * Anzahl der bisher gesendeten Chat Nachrichten wird festgelegt
	 */
	public static  void setAnzahlNachrichten(int i) {
		anzahlNachrichten = i;
		
	}
	
}
