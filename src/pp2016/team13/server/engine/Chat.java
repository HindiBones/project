package pp2016.team13.server.engine;

import java.util.ArrayList;

/**
 * Klasse, die den Chat verwaltet Einzelne Nachrichten werden als Listenelemente
 * gespeichert
 * 
 * @author Marius
 *
 */
public class Chat {
	static int anzahlNachrichten = 0;
	ArrayList<String> chat;

	public Chat() {
		chat = new ArrayList<String>();
	}

	/**
	 * 
	 * 
	 * Nachricht zum Chat hinzufuegen
	 * 
	 * @author Marius
	 * @param nachricht:
	 *            ankommende Chat Nachricht
	 */
	public boolean nachrichtEmpfangen(String nachricht) {
		chat.add(nachricht);
		return true;

	}

	/**
	 *
	 * 
	 * Chatverlauf senden
	 * 
	 * @author Marius
	 */
	public String[] chat() {
		return (String[]) chat.toArray();
	}

	/**
	 * 
	 * Anzahl der bisher gesendeten Chat Nachrichten wird festgelegt
	 * 
	 * @author Marius
	 */
	public static void setAnzahlNachrichten(int i) {
		anzahlNachrichten = i;

	}

}
