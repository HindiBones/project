package pp2016.team13.shared.Nachrichten;

/**
 * Klasse, die ein Cheat-Objekt beschreibt
 * 
 * @author <Braun, Jan Julius, 6000100>
 *
 */
public class Cheat extends Nachricht {

	private static final long serialVersionUID = 1L;

	/**
	 * Erstellt ein Objekt vom Typ Cheat
	 * @param typ
	 * 			 : Art des Cheats
	 * 
	 * @author <Braun, Jan Julius, 6000100>
	 */
	public Cheat(int typ) {
		super(13);
		this.cheattyp = typ;
		this.inOrdnung = true;
	}
}
