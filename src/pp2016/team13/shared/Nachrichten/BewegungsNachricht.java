package pp2016.team13.shared.Nachrichten;

/**
 * Bewegungsnachricht wird gesendet, wenn der Spieler sich bewegt hat.
 * Beinhaltet dessen neue Koordinaten
 * 
 * @author <Braun, Jan Julius, 6000100>
 *
 */
public class BewegungsNachricht extends Nachricht {

	private static final long serialVersionUID = 1L;

	/**
	 * Nachrichten-Objekt, das nach einer Bewegung eines Spielers dessen neue
	 * Koordinaten beinhaltet
	 * 
	 * @author Julius
	 * @param p
	 *            : Spieler ID
	 * @param x
	 *            : Spieler x-Koordinate
	 * @param y
	 *            : Spieler y-Koordinate
	 */
	public BewegungsNachricht(int p, int x, int y) {
		super(1);
		this.spielerID = p;
		this.xKoo = x;
		this.yKoo = y;
	}

}
