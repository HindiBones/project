package pp2016.team13.client.engine;

import pp2016.team13.client.gui.HindiBones;
import pp2016.team13.shared.Level;
import pp2016.team13.shared.Spieler;
import pp2016.team13.client.comm.*;

/**
 * NachrichtenVerwaltung ist die zentrale Klasse der Client-Engine. Die
 * clientseitige Kommunikation laeuft komplett ueber diese Klasse.
 * 
 * @author <Braun, Jan Julius, 6000100>
 *
 */
public class NachrichtenVerwaltung {

	public Client socket;
	HindiBones fenster;
	int id;
	public Spieler spieler;
	public Level aktuellesLevel;
	public Level[] alleLevel = new Level[5];
	String benutzername, passwort;

	/**
	 * Erstellt ein NachrichtenVerwaltung-Objekt mit der GUI spiel. Erstellt
	 * einen Socket, ueber den Nachrichten gesendet werden.
	 * 
	 * @param spiel
	 *            : GUI-Objekt, das die Nachrichtenverwaltung aufgerufen hat
	 * 
	 * @author <Braun, Jan Julius, 6000100>
	 */
	public NachrichtenVerwaltung(HindiBones spiel) {
		this.fenster = spiel;
		socket = new Client("localhost", 13001);
	}

	/**
	 * Sendet eine Nachricht, gibt die Serverantwort zurueck
	 * 
	 * @param nachricht
	 *            : Nachricht, die der Client senden soll
	 * 
	 * @return: Vom Server empfangene Antwort
	 * 
	 * @author <Braun, Jan Julius, 6000100>
	 */
	public Paket sende(Nachricht nachricht) {
		return socket.SendeAnServer(new Paket(nachricht));
	}

	/**
	 * Verarbeitet eine im Paket enthaltene Nachricht
	 *
	 * @param empfPaket
	 *            : Paket, das empfangen wurde und nun verarbeitet werden soll
	 * 
	 * @author <Braun, Jan Julius, 6000100>
	 */
	public void auslesen(Paket empfPaket) {
		Nachricht m = empfPaket.getMessage();
		switch (m.getTyp()) {
		case 0:
			systemnachricht("Einloggen erfolgreich!");
			break;
		case 1:
			systemnachricht("Position des Spielers: " + m.getxKoo() + ", "
					+ m.getyKoo());
			break;
		case 2:
			systemnachricht("Der Trank an der Position " + m.getxKoo() + ", "
					+ m.getyKoo() + " wurde aufgenommen");
			break;
		case 3:
			systemnachricht("Das Level wurde abgeschlossen!");
			break;
		case 4:
			systemnachricht("Der Schluessel an der Stelle " + m.getxKoo()
					+ ", " + m.getyKoo() + " wurde aufgenommen");
			break;
		case 5:
			systemnachricht(m.fehlermeldung);
			break;
		case 6: {
			for (int i = 0; i < m.leveldaten.length; i++) {
				alleLevel[i] = new Level(i, m.leveldaten[i]);
			}

		}
			break;
		case 11: {
			if (m.inOrdnung)
				systemnachricht("Anfrage akzeptiert!");
			else
				systemnachricht("Anfrage wurde abgelehnt!");
		}
			break;
		case 13:
			behandleCheat(m);
			break;
		}
	}

	/**
	 * Verarbeitet eine Cheat-Nachricht und fuehrt den Cheat aus.
	 * 
	 * @param cheat
	 *            : Nachricht, die verarbeitet werden soll.
	 * 
	 * @author <Braun, Jan Julius, 6000100>
	 */
	private void behandleCheat(Nachricht cheat) {
		switch (cheat.cheattyp) {
		case 0:
			systemnachricht("Fehler! Eingegebener Cheat wurde nicht erkannt!");
			break;
		case 1:
			fenster.spieler.setUnverwundbar(true);
			fenster.spieler.bildWechseln();
			systemnachricht("Spieler ist unverwundbar!");
			break;
		case 2:
			fenster.nebelAn = false;
			systemnachricht("Nebel gelichtet!");
			break;
		case 3:
			fenster.nextLevel();
			systemnachricht("Level \u00fcbersprungen!");
			break;
		case 4:
			fenster.spieler.setAnzahlHeiltraenke(fenster.spieler
					.getAnzahlHeiltraenke() + 10);
			systemnachricht("10 Heiltr\u00e4nke!");
			break;
		case 5:
			fenster.spieler
					.setAnzahlTrank(fenster.spieler.getAnzahlTrank() + 10);
			systemnachricht("10 Tr\u00e4nke!");
			break;
		case 6:
			this.nimmSchluessel();
			systemnachricht("Schl\u00fcssel erhalten!");
			break;
		}
	}

	/**
	 * Bewegt den Spieler wenn moeglich in die vorgegebene Richtung. Sendet eine
	 * Bewegungsnachricht an den Server.
	 * 
	 * @param richtung
	 *            : Integer, der die Bewegungsrichtung angibt. 0 = runter, 1 =
	 *            hoch, 2 = links, 3 = rechts
	 * 
	 * @author <Braun, Jan Julius, 6000100>
	 */
	public void SpielerBewegung(int richtung) {
		spieler = fenster.spieler;
		switch (richtung) {
		case 0:
			if (spieler.getYPos() < aktuellesLevel.getLaengeY() - 1
					&& fenster.Level.getBestimmtenLevelInhalt(
							spieler.getXPos(), spieler.getYPos() + 1) != 0) {
				spieler.runter();
				sende(new BewegungsNachricht(spieler.getID(),
						spieler.getXPos(), spieler.getYPos()));
			}
			break;

		case 1:
			if (spieler.getYPos() > 0
					&& fenster.Level.getBestimmtenLevelInhalt(
							spieler.getXPos(), spieler.getYPos() - 1) != 0) {
				spieler.hoch();
				sende(new BewegungsNachricht(spieler.getID(),
						spieler.getXPos(), spieler.getYPos()));
			}
			break;

		case 2:
			if (spieler.getXPos() > 0
					&& fenster.Level.getBestimmtenLevelInhalt(
							spieler.getXPos() - 1, spieler.getYPos()) != 0) {
				spieler.links();
				sende(new BewegungsNachricht(spieler.getID(),
						spieler.getXPos(), spieler.getYPos()));
			}
			break;

		case 3:
			if (spieler.getXPos() < aktuellesLevel.getLaengeX() - 1
					&& fenster.Level.getBestimmtenLevelInhalt(
							spieler.getXPos() + 1, spieler.getYPos()) != 0) {
				spieler.rechts();
				sende(new BewegungsNachricht(spieler.getID(),
						spieler.getXPos(), spieler.getYPos()));
			}
			break;

		}
	}

	/**
	 * Falls der Spieler einen Heiltrank besitzt, benutzt er diesen. Sendet eine
	 * entsprechende Nachricht an den Server.
	 * 
	 * @author <Braun, Jan Julius, 6000100>
	 */
	public void benutzeHeiltrank() {
		int change = spieler.benutzeHeiltrank();
		// Heilungseffekt wird verbessert, falls neue Monster durch das
		// Aufheben des Schluessels ausgeloesst wurden
		if (spieler.hatSchluessel())
			spieler.changeHealth((int) (change * 1.5));
		else
			spieler.changeHealth((int) (change * 0.5));
		sende(new ItemNachricht(spieler.getXPos(), spieler.getYPos(), 8));
	}

	/**
	 * 
	 * Der Spieler hebt den Schluessel auf, wenn er auf diesem steht. Sendet
	 * eine entsprechende Nachricht an den Server.
	 * 
	 * @author <Braun, Jan Julius, 6000100>
	 */
	public void nimmSchluessel() {
		spieler = fenster.spieler;
		spieler.nimmSchluessel();

		sende(new ItemNachricht(spieler.getXPos(), spieler.getYPos(), 4));
	}

	/**
	 * Der Spieler hebt einen Heiltrank auf, wenn er auf einem steht. Sendet
	 * eine entsprechende Nachricht an den Server.
	 * 
	 * @author <Braun, Jan Julius, 6000100>
	 * 
	 */
	public void nimmHeiltrank() {
		spieler = fenster.spieler;
		spieler.nimmHeiltrank();
		sende(new ItemNachricht(spieler.getXPos(), spieler.getYPos(), 2));
	}

	/**
	 * Der Spieler hebt einen Schutztrank auf, wenn er auf einem steht. Sendet
	 * eine entsprechende Nachricht an den Server.
	 * 
	 * @author <Braun, Jan Julius, 6000100>
	 */
	public void nimmTrank() {
		spieler = fenster.spieler;
		spieler.nimmtrank();
		sende(new ItemNachricht(spieler.getXPos(), spieler.getYPos(), 12));
	}

	/**
	 * Falls der Spieler den Schluessel besitzt, benutzt er diesen. Sendet eine
	 * entsprechende Nachricht an den Server.
	 * 
	 * @author <Braun, Jan Julius, 6000100>
	 */
	public void benutzeSchluessel() {
		spieler = fenster.spieler;
		if (spieler.hatSchluessel()) {
			fenster.Level.setLevelInhalt(spieler.getXPos(), spieler.getYPos(),
					7);
			// Nach dem Oeffnen der Tuer ist der Schluessel wieder weg
			sende(new Nachricht(3));
			spieler.entferneSchluessel();
		}
	}

	/**
	 * Der Spieler benutzt einen Schutztrank, wenn er einen besitzt.
	 * 
	 * @return: Zeitpunkt, zu dem der Schutztrank benutzt wurde.
	 * 
	 * @author <Braun, Jan Julius, 6000100>
	 */
	public long benutzeTrank() {
		fenster.spieler.bildWechseln();
		return fenster.spieler.benutzeTrank();
	}

	/**
	 * Fordert 5 Level vom Server an. Sendet eine entsprechende Nachricht an den
	 * Server.
	 * 
	 * @author <Braun, Jan Julius, 6000100>
	 */
	public void levelAnfordern() {
		Paket serverAntwort = sende(new LevelAnfordernNachricht());
		auslesen(serverAntwort);
		aktuellesLevel = alleLevel[0];

		aktuellesLevel.ausgabe();
	}

	/**
	 * Versucht, den Benutzer mit der gegebenen Login-Nachricht anzumelden/zu
	 * registrieren.
	 * 
	 * @param login
	 *            : Login-Nachricht, die verarbeitet werden soll.
	 * @return: Gibt zurueck, ob der Benutzer angemeldet/registriert wurde.
	 * 
	 * @author <Braun, Jan Julius, 6000100>
	 */
	public boolean einloggen(LoginNachricht login) {
		Paket serverAntwort = sende(login);
		auslesen(serverAntwort);
		return serverAntwort.getMessage().inOrdnung;
	}

	/**
	 * Sendet eine Chatnachricht bzw einen Cheat an den Server.
	 * 
	 * @param nachricht
	 *            : Chatnachricht, die gesendet werden soll
	 * @return: Gibt zurueck, ob die Chatnachricht/der Cheat korrekt gesendet
	 *          wurde.
	 * 
	 * @author <Braun, Jan Julius, 6000100>
	 */
	public boolean chatte(ChatNachricht nachricht) {
		Paket serverAntwort = new Paket(new FehlerNachricht(
				"Konnte keine Nachricht senden!"));
		if (nachricht.istCheat()) {
			serverAntwort = sende(new Cheat(nachricht.getCheat()));
		} else {
			serverAntwort = sende(nachricht);
		}
		auslesen(serverAntwort);

		return serverAntwort.getMessage().inOrdnung;
	}

	/**
	 * Wechselt das Level, das der Benutzer spielt.
	 * 
	 * @return: Gibt das naechste Level aus
	 * 
	 * @author <Braun, Jan Julius, 6000100>
	 */
	public Level levelWechseln() {
		if (fenster.Level.getLevelID() < fenster.MAXLEVEL - 1) {
			aktuellesLevel = alleLevel[aktuellesLevel.levelID + 1];
			fenster.spieler.entferneSchluessel();
			aktuellesLevel.ausgabe();
			fenster.levelnummer = aktuellesLevel.levelID;
			systemnachricht("Level wurde gewechselt!");
			return aktuellesLevel;
		} else {
			sende(new Nachricht(3));
			return new Level(6, aktuellesLevel.levelInhalt);
		}
	}

	/**
	 * Gibt eine Systemnachricht im Chat aus.
	 * 
	 * @param Text
	 *            : Text der Systemnachricht, die im Chat ausgegeben werden
	 *            soll.
	 * 
	 * @author <Braun, Jan Julius, 6000100>
	 */
	public void systemnachricht(String Text) {

		fenster.getMinimap().getChatFenster().textumfeld.append("<System>: "
				+ Text + "\n");
	}
}
