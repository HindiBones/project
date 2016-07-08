package pp2016.team13.client.engine;

import java.util.LinkedList;
import java.util.Queue;

import pp2016.team13.client.gui.HindiBones;
import pp2016.team13.shared.Level;
import pp2016.team13.shared.Spieler;
import pp2016.team13.client.comm.*;

public class NachrichtenVerwaltung {
	
	public Client socket;
	HindiBones fenster;
	int id;
	public Spieler spieler;
	Queue<Nachricht> Nachrichten = new LinkedList<Nachricht>();
	Queue<Nachricht> NachrichtenEmpfangen = new LinkedList<Nachricht>();
	public Level aktuellesLevel;
	Level[] alleLevel = new Level[5];
	String benutzername, passwort;
	/**
	 * @author Julius
	 * @param i: ID des Clients
	 * 
	 * Erstellt ein Client-Objekt mit der ID i
	 */
	public NachrichtenVerwaltung(int i){
		socket = new Client("localhost", 13001);
	}
	
	/**
	 * @author Julius
	 * @param m: Nachricht, die der Client senden soll
	 * 
	 * Sendet eine Nachricht, fuegt sie in die Nachrichten-Queue ein
	 * @return 
	 */
	public Paket sende(Nachricht m){
		Nachrichten.add(m);
		Paket temp = new Paket(m);
		return socket.SendeAnServer(temp);
	}
	
	/**
	 * @author Julius
	 * 
	 * Hilfsmethode zum Testen, gibt die Nachrichten aus, die spaeter an den Server gesendet werden
	 */
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
					case 5: System.out.println("Ein Fehler ist aufgetreten!!!!");break;
					case 6: System.out.println("Level wurde geladen!");break;
					case 7: System.out.println(benutzername + " hat ein Monster angegriffen!");break;
					case 8: System.out.println(benutzername + " hat einen Trank benutzt!");break;
					case 9: System.out.println(benutzername + ": " + m.nachricht);
				}
			
		}
	}
	

	// Verarbeitet die empfangenen Nachrichten
	/**
	 * @author Julius
	 * 
	 * Verarbeitet die Nachrichten, die in der NachrichtenEmpfangen Queue sind
	 */
	public void auslesen(Paket x)
	{
			Nachricht m = x.getMessage();
				switch (m.getTyp()){
				/*
				 * !Die hier angegebenen Reaktionen auf die Messages sind nur zu Testzwecken und werden bei der Integration der anderen Komponenten ausgebessert!
				 */
					case 0: System.out.println("Einloggen erfolgreich!"); break;
					case 1: System.out.println("Position des Spielers: " + m.getxKoo()+ ", " + m.getyKoo());break;
					case 2: System.out.println("Der Trank an der Position " + m.getxKoo() + ", " + m.getyKoo() + " wurde aufgenommen");break;
					case 3: System.out.println("Das Level wurde abgeschlossen!");break;
					case 4: System.out.println("Der Schluessel an der Stelle "+m.getxKoo()+", "+m.getyKoo()+" wurde aufgenommen");break;
					case 5: System.out.println(m.fehlermeldung);break;
					case 6: System.out.println( m.leveldaten);break;
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
	
	/**
	 * @author Julius
	 * @param richtung: Integer, der die Bewegungsrichtung angibt. 0 = runter, 1 = hoch, 2 = links, 3 = rechts
	 * 
	 * Bewegt den Spieler wenn m�glich in die vorgegebene Richtung. Sendet eine Bewegungsnachricht an den Server.
	 */
	public void SpielerBewegung(int richtung){
		switch(richtung){
		case 0:
			if(spieler.getYPos() < aktuellesLevel.getLaengeY()-1 && Level.getBestimmtenLevelInhalt(spieler.getXPos(), spieler.getYPos()+1) != 0)
			{
				spieler.runter();
				sende(new BewegungsNachricht(spieler.getID(),spieler.getXPos(),spieler.getYPos()));
			}
			break;
			
			
		case 1:
			if(spieler.getYPos() > 0 && Level.getBestimmtenLevelInhalt(spieler.getXPos(), spieler.getYPos()-1) != 0)
			{
				spieler.hoch();
				sende(new BewegungsNachricht(spieler.getID(), spieler.getXPos(), spieler.getYPos()));
			}
			break;
			
		case 2:
			if(spieler.getXPos() > 0 && Level.getBestimmtenLevelInhalt(spieler.getXPos()-1, spieler.getYPos()) != 0)
			{
				spieler.links();
				sende(new BewegungsNachricht(spieler.getID(), spieler.getXPos(), spieler.getYPos()));
			}
			break;
			
		case 3:
			if(spieler.getXPos() < aktuellesLevel.getLaengeX()-1 && Level.getBestimmtenLevelInhalt(spieler.getXPos()+1, spieler.getYPos()) != 0)
			{
				spieler.rechts();
				sende(new BewegungsNachricht(spieler.getID(), spieler.getXPos(), spieler.getYPos()));
			}
			break;
			
		}
	}
	
	/**
	 * @author Julius
	 * 
	 * Falls der Spieler einen Heiltrank besitzt, benutzt er diesen. Sendet eine entsprechende Nachricht an den Server.
	 */
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
	
	/**
	 * @author Julius
	 * 
	 * Der Spieler hebt den Schluessel auf, wenn er auf diesem steht. Sendet eine entsprechende Nachricht an den Server.
	 */
	public void nimmSchluessel(){
		spieler.nimmSchluessel();
		sende(new ItemNachricht(spieler.getXPos(), spieler.getYPos(), 4));
	}
	
	/**
	 * @author Julius
	 * 
	 * Der Spieler hebt einen Heiltrank auf, wenn er auf einem steht. Sendet eine entsprechende Nachricht an den Server.
	 */
	public void nimmHeiltrank(){
		spieler.nimmHeiltrank();
		sende(new ItemNachricht(spieler.getXPos(), spieler.getYPos(), 2));
	}
	
	/**
	 * @author Julius
	 * 
	 * Falls der Spieler den Schluessel besitzt, benutzt er diesen. Sendet eine entsprechende Nachricht an den Server.
	 */
	public void benutzeSchluessel(){
		if (spieler.hatSchluessel()) {
			Level.setLevelInhalt(spieler.getXPos(), spieler.getYPos(), 7);
			// Nach dem Oeffnen der Tuer ist der Schluessel wieder weg
			sende(new Nachricht(3));
			spieler.entferneSchluessel();}
	}
	
	/**
	 * @author Julius
	 * 
	 * Wechselt das Level. Sendet eine entsprechende Nachricht an den Server.
	 */
	public void levelAnfordern(){
		Paket serverAntwort = sende(new LevelAnfordernNachricht());
		auslesen(serverAntwort);
		aktuellesLevel = alleLevel[0];
	}
//	public void levelWechseln(){
//		if(aktuellesLevel.getLevelID() < 4)
//		aktuellesLevel = alleLevel[aktuellesLevel.getLevelID()+1];
//		else
//		{
//			sende(new Nachricht(3));
//			ausgabe();
//			System.exit(0);
//		}
//	}
}
