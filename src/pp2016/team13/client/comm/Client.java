package pp2016.team13.client.comm;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.LinkedList;

import pp2016.team13.shared.Nachrichten.FehlerNachricht;

/**
 * 
 * Klasse Client: Stellt die Verbindung zum Server da und sendet Nachrichten
 * 
 * @author <Jeyakumar, Kirthika, 7302822>
 *  
 * @param cs
 *            : Socket
 * @param binAmLeben
 *            : Ich bin noch da Nachricht - nicht implementiert
 * @param host
 *            : host fuer die Verbindung
 * @param port
 *            : port fuer die Verbindung
 *
 */
public class Client extends Paket {
	// Liste wird genriert fuer die Nachrichten
	LinkedList<Paket> clientListe = new LinkedList<Paket>();
	// einzelne Streams werden erzeugt
	InputStream is;
	OutputStream os;
	ObjectInputStream ois = null;
	ObjectOutputStream oos = null;
	public Socket cs;
	boolean binAmLeben;

	public Client(String host, int port) {
		try {
			cs = new Socket(host, port);
		} catch (IOException e) {
		}
	}

	/**
	 * Nachricht wird an Server gesendet
	 * 
	 * @author <Jeyakumar, Kirthika, 7302822>
	 * @param msg
	 *            Message, das gesendet wird
	 * @return Serverantwort wird zurueckgegeben
	 */
	public Paket sendeAnServer(Paket msg) {

		Paket serverAntwort = new Paket();
		try {
			oos = new ObjectOutputStream(cs.getOutputStream());
			oos.writeObject(msg);
			oos.flush();
			serverAntwort = new Paket();
			ois = new ObjectInputStream(cs.getInputStream());
			serverAntwort = (Paket) ois.readObject();
		} catch (SocketException se) {
			serverAntwort = new Paket(new FehlerNachricht(
					"Server antwortet nicht!"));
		} catch (IOException e) {
			serverAntwort = new Paket(new FehlerNachricht(e.getMessage()));
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			serverAntwort = new Paket(new FehlerNachricht(e.getMessage()));
		}

		return serverAntwort;
	}

	/**
	 * @author <Jeyakumar, Kirthika, 7302822>
	 * @param msg
	 *            : Sendepaket
	 */
	public void sendeLogout(Paket msg) {
		try {
			oos = new ObjectOutputStream(cs.getOutputStream());
			oos.writeObject(msg);
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
