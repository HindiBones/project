package pp2016.team13.server.comm;

import java.io.*;
import java.net.*;
import java.util.LinkedList;

import pp2016.team13.client.comm.Paket;
import pp2016.team13.server.engine.Levelverwaltung;
import pp2016.team13.shared.Nachrichten.AntwortNachricht;
import pp2016.team13.shared.Nachrichten.Cheat;
import pp2016.team13.shared.Nachrichten.FehlerNachricht;
import pp2016.team13.shared.Nachrichten.LevelNachricht;
import pp2016.team13.shared.Nachrichten.Nachricht;

/**
 * Server wird erzeugt, Verbindungen werden verarbeitet
 * 
 * @author <Jeyakumar, Kirthika, 7302822>
 * 
 */
public class Server implements Serializable {

	private static final long serialVersionUID = 1L;
	// die einzelnen Streams werden definiert
	public ServerSocket serverS;
	public Socket s;
	boolean einloggenErfolgreich = false;
	boolean serverOffen;
	ObjectOutputStream oos = null;
	ObjectInputStream ois = null;
	OutputStreamWriter osw = null;
	InputStreamReader isw = null;
	LinkedList<Paket> serverListe = new LinkedList<Paket>();
	Levelverwaltung spiel;

	/**
	 * Server wird gestartet
	 * 
	 * @author <Jeyakumar, Kirthika, 7302822>
	 * @param port
	 */
	public Server(int port) {
		try {
			serverS = new ServerSocket(port);
			spiel = new Levelverwaltung(0, 10, 1, 0, 5, 1, 20, 5);
		} catch (IOException e1) {
			System.out.println("FEHLER");
		}

		while (true) {

			try {
				// Server wartet auf eingehende Verbindungen
				s = serverS.accept();
				serverS.setSoTimeout(1);

				// eingehende Verbindung wird verarbeitet

				System.out.println("Starte Server");
				laufen();

			} catch (IOException e) {
				System.out.println("FEHLER");
			}
		}
	}

	/**
	 * Server laeuft und wendet die Methode handleconnection an
	 * 
	 * @author <Jeyakumar, Kirthika, 7302822>
	 * 
	 */

	public void laufen() throws IOException {
		this.serverOffen = true;
		while (this.serverOffen) {
			handleconnection();
		}
	}

	/**
	 * Verbindung wird verarbeitet
	 * 
	 * @author <Jeyakumar, Kirthika, 7302822>
	 */
	// eingehende Verbindung wird verarbeitet
	public void handleconnection() {
		try {
			oos = new ObjectOutputStream(s.getOutputStream());
			Paket n = new Paket();
			ois = new ObjectInputStream(s.getInputStream());
			n = (Paket) ois.readObject();
			Paket antwort = verarbeiteNachricht(n.getMessage());
			oos.writeObject(antwort);
			oos.flush();
		} catch (IOException | ClassNotFoundException e) {
		}
	}

	/**
	 * Nachricht wird verarbeitet
	 * 
	 * @author <Jeyakumar, Kirthika, 7302822>
	 * @param n
	 *            : Nachricht
	 * @return gibt die Antwort zurueck
	 */

	@SuppressWarnings("static-access")
	public Paket verarbeiteNachricht(Nachricht n) {
		try {
			Nachricht antwortNachricht = new FehlerNachricht("Fehler!");
			switch (n.getTyp()) {

			case 0:
				antwortNachricht = new AntwortNachricht(Levelverwaltung.verarbeiteClientNachricht(n, spiel));
				einloggenErfolgreich = true;
				break;
			case 1:
				antwortNachricht = new AntwortNachricht(Levelverwaltung.verarbeiteClientNachricht(n, spiel));
				break;
			case 2:
				antwortNachricht = new AntwortNachricht(Levelverwaltung.verarbeiteClientNachricht(n, spiel));
				break;
			case 3:
				antwortNachricht = new AntwortNachricht(Levelverwaltung.verarbeiteClientNachricht(n, spiel));
				break;
			case 4:
				antwortNachricht = new AntwortNachricht(Levelverwaltung.verarbeiteClientNachricht(n, spiel));
				break;
			case 5:
				antwortNachricht = new AntwortNachricht(Levelverwaltung.verarbeiteClientNachricht(n, spiel));
				break;
			case 6:
				antwortNachricht = new AntwortNachricht(Levelverwaltung.verarbeiteClientNachricht(n, spiel));
				break;
			case 7:
				antwortNachricht = new AntwortNachricht(Levelverwaltung.verarbeiteClientNachricht(n, spiel));
				break;
			case 8:
				antwortNachricht = new AntwortNachricht(spiel.verarbeiteClientNachricht(n, spiel));
				break;
			case 9:
				antwortNachricht = new AntwortNachricht(Levelverwaltung.verarbeiteClientNachricht(n, spiel));
				break;
			case 10:
				antwortNachricht = new LevelNachricht(Levelverwaltung.levelSpeicherort);
				break;
			case 13:
				Levelverwaltung.verarbeiteClientNachricht(n, spiel);
				antwortNachricht = new Cheat(n.cheattyp);
				break;
			case 15:
				System.out.println("Spieler Logout");
				System.out.println("Server Beendet");
				System.exit(0);
				break;
			}
			Paket antwort = new Paket(antwortNachricht);
			return antwort;
		} catch (NullPointerException e) {
			e.printStackTrace();
			return new Paket(new FehlerNachricht("NullPointerException!"));
		} catch (Exception e) {
			e.printStackTrace();
			return new Paket(new FehlerNachricht("Exception!"));
		}
	}
}