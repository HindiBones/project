package Client;

import gui.HindiBones;

import java.util.LinkedList;
import java.util.Queue;

import datenstruktur.Spieler;
import datenstruktur.Level;

public class Client {

	HindiBones fenster;
	int id;
	public Spieler spieler;
	Queue<Nachricht> Nachrichten = new LinkedList<Nachricht>();
	Queue<Nachricht> NachrichtenEmpfangen = new LinkedList<Nachricht>();
	public Level aktuellesLevel;
	Level[] alleLevel;
	String benutzername, passwort;
	public Client(int i){
		this.id=i;
	}
	
	// Sendet eine Nachricht, F�gt sie in die Nachrichten-Queue ein
	public void sende(Nachricht m){
		Nachrichten.add(m);
	}
	
	
	// Hilfsmethode zum Testen, gibt die Nachrichten aus, die sp�ter an den Server gesendet werden
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
					case 7: System.out.println(benutzername + " hat ein Monster angegriffen!");break;
					case 8: System.out.println(benutzername + " hat einen Trank benutzt!");break;
				}
			
		}
	}
	
	// �bertr�gt die zu sendenden Nachrichten an einen anderen Client. Nur zu Testzwecken, sp�ter wird an einen Server gesendet.
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
					case 6: this.alleLevel=m.leveldaten;this.aktuellesLevel=m.leveldaten[0];System.out.println("Level " + aktuellesLevel.getLevelID()+ " wurde geladen!");break;
				}
			
		}
	}
	/*
	 * Nachrichtentypen
	 * 0 - Einloggen
	 * 1 - Eigene Position
	 * 2 - Heiltrankposition
	 * 3 - Level Abgeschlossen
	 * 4 - Schl�ssel aufgenommen
	 * 5 - Fehlermeldung
	 * 6 - Level empfangen
	 */
	
	public void SpielerBewegung(int richtung){
		switch(richtung){
		case 0:
			if(spieler.getYPos() < aktuellesLevel.getLaengeY()-1 && aktuellesLevel.getBestimmtenLevelInhalt(spieler.getXPos(), spieler.getYPos()+1) != 0)
			{
				spieler.runter();
				sende(new BewegungsNachricht(spieler.getID(),spieler.getXPos(),spieler.getYPos()));
			}
			break;
			
			
		case 1:
			if(spieler.getYPos() > 0 && aktuellesLevel.getBestimmtenLevelInhalt(spieler.getXPos(), spieler.getYPos()-1) != 0)
			{
				spieler.hoch();
				sende(new BewegungsNachricht(spieler.getID(), spieler.getXPos(), spieler.getYPos()));
			}
			break;
			
		case 2:
			if(spieler.getXPos() > 0 && aktuellesLevel.getBestimmtenLevelInhalt(spieler.getXPos()-1, spieler.getYPos()) != 0)
			{
				spieler.links();
				sende(new BewegungsNachricht(spieler.getID(), spieler.getXPos(), spieler.getYPos()));
			}
			break;
			
		case 3:
			if(spieler.getXPos() < aktuellesLevel.getLaengeX()-1 && aktuellesLevel.getBestimmtenLevelInhalt(spieler.getXPos()+1, spieler.getYPos()) != 0)
			{
				spieler.rechts();
				sende(new BewegungsNachricht(spieler.getID(), spieler.getXPos(), spieler.getYPos()));
			}
			break;
			
		}
	}

	public void benutzeHeiltrank(){
		int change = spieler.benutzeHeiltrank();
		// Heilungseffekt wird verbessert, falls neue Monster durch das
		// Aufheben des Schl�ssels ausgel�st wurden
		if (spieler.hatSchluessel())
			spieler.changeHealth((int) (change * 1.5));
		else
			spieler.changeHealth((int) (change * 0.5));
		sende(new ItemNachricht(spieler.getXPos(), spieler.getYPos(), 8));
	}
	
	public void nimmSchluessel(){
		spieler.nimmSchluessel();
		sende(new ItemNachricht(spieler.getXPos(), spieler.getYPos(), 4));
	}
	
	public void nimmHeiltrank(){
		spieler.nimmHeiltrank();
		sende(new ItemNachricht(spieler.getXPos(), spieler.getYPos(), 2));
	}
	
	public void benutzeSchluessel(){
		if (spieler.hatSchluessel()) {
			aktuellesLevel.setLevelInhalt(spieler.getXPos(), spieler.getYPos(), 7);
			// Nach dem Oeffnen der Tuer ist der Schluessel wieder weg
			sende(new Nachricht(3));
			spieler.entferneSchluessel();}
	}
	
	public void levelWechseln(){
		if(aktuellesLevel.getLevelID() < 4)
		aktuellesLevel = alleLevel[aktuellesLevel.getLevelID()+1];
		else
		{
			sende(new Nachricht(3));
			ausgabe();
			System.exit(0);
		}
	}
}
