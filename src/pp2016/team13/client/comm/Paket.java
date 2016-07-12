package pp2016.team13.client.comm;

import java.io.Serializable;
import pp2016.team13.shared.Nachrichten.Nachricht;

/**
 * Paket, das gesendet wird
 * 
 * @author <Jeyakumar, Kirthika, 7302822>
 * 
 */
public class Paket implements Serializable {

	private static final long serialVersionUID = 1;
	protected long zeit;
	public Nachricht inhalt;

	/**
	 * Erstellt ein Objekt der Klasse Paket. Setzt die Zeit auf 0.
	 * 
	 * @author Kirthika Jeyakumar
	 */
	public Paket() {
		this.zeit = 0;
	}

	/**
	 * Erstellt ein Objekt der Klasse Paket. Setzt die Zeit auf 0 und die
	 * Nachricht des Pakets auf nachricht
	 * 
	 * @param nachricht
	 *            : Nachricht, die an das Paket uebergeben wird
	 * 
	 * @author Kirthika Jeyakumar
	 */
	public Paket(Nachricht nachricht) {
		this.zeit = 0;
		this.inhalt = nachricht;
	}

	/**
	 * Gibt die Nachricht des Pakets zurueck
	 * 
	 * @return: Die Nachricht des Pakets
	 * 
	 * @author Kirthika Jeyakumar
	 */
	public Nachricht getMessage() {
		return inhalt;
	}

}
