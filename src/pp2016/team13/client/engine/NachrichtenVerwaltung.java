package pp2016.team13.client.engine;



import pp2016.team13.client.gui.HindiBones;
import pp2016.team13.shared.Level;
import pp2016.team13.shared.Spieler;
import pp2016.team13.client.comm.*;

public class NachrichtenVerwaltung {
	
	public Client socket;
	HindiBones fenster;
	int id;
	public Spieler spieler;
	public Level aktuellesLevel;
	public Level[] alleLevel = new Level[5];
	String benutzername, passwort;
	/**
	 * @author Julius
	 * @param i: ID des Clients
	 * 
	 * Erstellt ein Client-Objekt mit der ID i
	 */
	public NachrichtenVerwaltung(HindiBones f){
		this.fenster = f;
		socket = new Client("localhost", 13001);
	}
	
	/**
	 * Sendet eine Nachricht, fuegt sie in die Nachrichten-Queue ein
	 * @author Julius
	 * @param m: Nachricht, die der Client senden soll
	 * 
	 * @return 
	 */
	public Paket sende(Nachricht m){
		Paket temp = new Paket(m);
		return socket.SendeAnServer(temp);
	}
	
	/**
	 * @author Julius
	 * 
	 * Hilfsmethode zum Testen, gibt die Nachrichten aus, die spaeter an den Server gesendet werden
	 */

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
					case 0: systemnachricht("Einloggen erfolgreich!"); break;
					case 1: System.out.println("Position des Spielers: " + m.getxKoo()+ ", " + m.getyKoo());break;
					case 2: System.out.println("Der Trank an der Position " + m.getxKoo() + ", " + m.getyKoo() + " wurde aufgenommen");break;
					case 3: System.out.println("Das Level wurde abgeschlossen!");break;
					case 4: System.out.println("Der Schluessel an der Stelle "+m.getxKoo()+", "+m.getyKoo()+" wurde aufgenommen");break;
					case 5: System.out.println(m.fehlermeldung);System.out.println(m.getTyp());break;
					case 6: 
					{				
							for(int i = 0; i < m.leveldaten.length; i++){
								alleLevel[i] = new Level(i, m.leveldaten[i]);
							}

					}break;
					case 11: {
						if(m.inOrdnung)
							System.out.println("Anfrage akzeptiert!");
						else
							System.err.println("Anfrage wurde abgelehnt!");
					}break;
					case 13: behandleCheat(m);break;
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
	
	private void behandleCheat(Nachricht cheat) {
		// TODO Auto-generated method stub
		switch(cheat.cheattyp){
			case 0: System.err.println("Fehler! Eingegebener Cheat wurde nicht erkannt!");break;
			case 1: fenster.spieler.setUnverwundbar(true);fenster.spieler.bildWechseln();systemnachricht("Spieler ist unverwundbar!");break;
			case 2: fenster.nebelAn = false;systemnachricht("Nebel gelichtet!");break;
			case 3: fenster.nextLevel();systemnachricht("Level \u00fcbersprungen!"); break;
			case 4: fenster.spieler.setAnzahlHeiltraenke(fenster.spieler.getAnzahlHeiltraenke() + 10);systemnachricht("10 Heiltr\u00e4nke!");break;
			case 5: fenster.spieler.setAnzahlTrank(fenster.spieler.getAnzahlTrank() + 10); systemnachricht("10 Tr\u00e4nke!");break;
			case 6: this.nimmSchluessel();systemnachricht("Schl\u00fcssel erhalten!");break;
		}
	}

	/**
	 * @author Julius
	 * @param richtung: Integer, der die Bewegungsrichtung angibt. 0 = runter, 1 = hoch, 2 = links, 3 = rechts
	 * 
	 * Bewegt den Spieler wenn m�glich in die vorgegebene Richtung. Sendet eine Bewegungsnachricht an den Server.
	 */
	public void SpielerBewegung(int richtung){
		spieler = fenster.spieler;
		switch(richtung){
		case 0:
			if(spieler.getYPos() < aktuellesLevel.getLaengeY()-1 && fenster.Level.getBestimmtenLevelInhalt(spieler.getXPos(), spieler.getYPos()+1) != 0)
			{
				spieler.runter();
				sende(new BewegungsNachricht(spieler.getID(),spieler.getXPos(),spieler.getYPos()));
			}
			break;
			
			
		case 1:
			if(spieler.getYPos() > 0 && fenster.Level.getBestimmtenLevelInhalt(spieler.getXPos(), spieler.getYPos()-1) != 0)
			{
				spieler.hoch();
				sende(new BewegungsNachricht(spieler.getID(), spieler.getXPos(), spieler.getYPos()));
			}
			break;
			
		case 2:
			if(spieler.getXPos() > 0 && fenster.Level.getBestimmtenLevelInhalt(spieler.getXPos()-1, spieler.getYPos()) != 0)
			{
				spieler.links();
				sende(new BewegungsNachricht(spieler.getID(), spieler.getXPos(), spieler.getYPos()));
			}
			break;
			
		case 3:
			if(spieler.getXPos() < aktuellesLevel.getLaengeX()-1 && fenster.Level.getBestimmtenLevelInhalt(spieler.getXPos()+1, spieler.getYPos()) != 0)
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
		spieler = fenster.spieler;
		spieler.nimmSchluessel();

		sende(new ItemNachricht(spieler.getXPos(), spieler.getYPos(), 4));
	}
	
	/**
	 * @author Julius
	 * 
	 * Der Spieler hebt einen Heiltrank auf, wenn er auf einem steht. Sendet eine entsprechende Nachricht an den Server.
	 */
	public void nimmHeiltrank(){
		spieler = fenster.spieler;
		spieler.nimmHeiltrank();
		sende(new ItemNachricht(spieler.getXPos(), spieler.getYPos(), 2));
	}
	
	public void nimmTrank(){
		spieler = fenster.spieler;
		spieler.nimmtrank();
		sende(new ItemNachricht(spieler.getXPos(), spieler.getYPos(), 12));
	}
	/**
	 * @author Julius
	 * 
	 * Falls der Spieler den Schluessel besitzt, benutzt er diesen. Sendet eine entsprechende Nachricht an den Server.
	 */
	public void benutzeSchluessel(){
		spieler = fenster.spieler;
		if (spieler.hatSchluessel()) {
			fenster.Level.setLevelInhalt(spieler.getXPos(), spieler.getYPos(), 7);
			// Nach dem Oeffnen der Tuer ist der Schluessel wieder weg
			sende(new Nachricht(3));
			spieler.entferneSchluessel();}
	}
	
	public long benutzeTrank(){
		fenster.spieler.bildWechseln();
		return fenster.spieler.benutzeTrank();
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
		
		aktuellesLevel.ausgabe();
	}
	
	public boolean einloggen(LoginNachricht login){
		Paket serverAntwort = sende(login);
		auslesen(serverAntwort);
		return serverAntwort.getMessage().inOrdnung;
	}
	
	public boolean chatte(ChatNachricht nachricht){
		Paket serverAntwort = new Paket(new FehlerNachricht("Konnte keine Nachricht senden!"));
		if(nachricht.istCheat()){
			serverAntwort = sende(new Cheat(nachricht.getCheat()));
		}
		else{
			serverAntwort = sende(nachricht);
		}
		auslesen(serverAntwort);
		
		return serverAntwort.getMessage().inOrdnung;
	}
	public Level levelWechseln(){
		if(fenster.Level.getLevelID() < fenster.MAXLEVEL-1){
		aktuellesLevel = alleLevel[aktuellesLevel.levelID+1];
		fenster.spieler.entferneSchluessel();
		aktuellesLevel.ausgabe();
		fenster.levelnummer = aktuellesLevel.levelID;
		systemnachricht("Level wurde gewechselt!");
		return aktuellesLevel;
		}
		else
		{
			sende(new Nachricht(3));
			return new Level(6, aktuellesLevel.levelInhalt);
		}
	}
	
	public void systemnachricht(String Text){

		fenster.getMinimap().getChatFenster().textumfeld.append("<System>: " + Text + "\n");
	}
}
