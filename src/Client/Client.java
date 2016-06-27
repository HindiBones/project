package Client;

import java.util.LinkedList;
import java.util.Queue;

public class Client {

	int id;
	Queue<Nachricht> Nachrichten = new LinkedList<Nachricht>();
	Queue<Nachricht> NachrichtenEmpfangen = new LinkedList<Nachricht>();
	Level aktuellesLevel;
	String benutzername, passwort;
	public Client(int i){
		this.id=i;
	}
	
	// Sendet eine Nachricht, Fügt sie in die Nachrichten-Queue ein
	public void sende(Nachricht m){
		Nachrichten.add(m);
	}
	
	
	// Hilfsmethode zum Testen, gibt die Nachrichten aus, die später an den Server gesendet werden
	public void ausgabe(){
		while(!this.Nachrichten.isEmpty()){
				Nachricht m = Nachrichten.poll();
				switch (m.getTyp()){
				/*
				 * !Die hier angegebenen Reaktionen auf die Nachrichten sind nur zu Testzwecken und werden bei der Integration der anderen Komponenten ausgebessert!
				 */
					case 0: this.benutzername=m.benutzername;this.passwort=m.passwort; System.out.println("Einloggen von " + benutzername + " erfolgreich!"); break;
					case 1: System.out.println("Position des Spielers: " + m.getxKoo()+ ", " + m.getyKoo());break;
					case 2: System.out.println("Der Trank an der Position " + m.getxKoo() + ", " + m.getyKoo() + " wurde aufgenommen");break;
					case 3: System.out.println("Das Level wurde abgeschlossen!");break;
					case 4: System.out.println("Der Schluessel an der Stelle "+m.getxKoo()+", "+m.getyKoo()+" wurde aufgenommen");break;
					case 5: System.out.println("Ein Fehler ist aufgetreten!");break;
					case 6: System.out.println("Level wurde geladen!");break;
				}
			
		}
	}
	
	// Überträgt die zu sendenden Nachrichten an einen anderen Client. Nur zu Testzwecken, später wird an einen Server gesendet.
	public void uebertrage(Client empfaenger){
		while(!Nachrichten.isEmpty()){
			Nachricht n = Nachrichten.poll();
			empfaenger.NachrichtenEmpfangen.offer(n);
		}
	}
	
	// Verarbeitet die empfangenen Nachrichten
	public void empfange()
	{
		for(int i=0;i<NachrichtenEmpfangen.size();i++){
			Nachricht m = NachrichtenEmpfangen.poll();
			
				switch (m.getTyp()){
				/*
				 * !Die hier angegebenen Reaktionen auf die Messages sind nur zu Testzwecken und werden bei der Integration der anderen Komponenten ausgebessert!
				 */
					case 0: System.out.println("Einloggen erfolgreich!"); break;
					case 1: System.out.println("Position des Spielers: " + m.getxKoo()+ ", " + m.getyKoo());break;
					case 2: System.out.println("Der Trank an der Position " + m.getxKoo() + ", " + m.getyKoo() + " wurde aufgenommen");break;
					case 3: System.out.println("Das Level wurde abgeschlossen!");break;
					case 4: System.out.println("Der Schluessel an der Stelle "+m.getxKoo()+", "+m.getyKoo()+" wurde aufgenommen");break;
					case 5: System.out.println("Ein Fehler ist aufgetreten!");break;
					case 6: System.out.println("Level wurde geladen!");this.aktuellesLevel=m.leveldaten;break;
				}
			
		}
	}
	/*
	 * Nachrichtentypen
	 * 0 - Einloggen
	 * 1 - Eigene Position
	 * 2 - Heiltrankposition
	 * 3 - Level Abgeschlossen
	 * 4 - Schlüssel aufgenommen
	 * 5 - Fehlermeldung
	 * 6 - Level empfangen
	 */
}
