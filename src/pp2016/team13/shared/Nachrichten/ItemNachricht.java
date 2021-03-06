package pp2016.team13.shared.Nachrichten;

/**
 * Item-Nachricht, wird verschickt, wenn ein Item (Heiltrank, Trank, Schluessel)
 * aufgehoben oder benutzt wird
 * 
 * @author <Braun, Jan Julius, 6000100>
 *
 */
public class ItemNachricht extends Nachricht {

	private static final long serialVersionUID = 1L;

	/**
	 * Erstellt eine ItemNachricht.
	 * 
	 * @param a
	 *            : X-Koordinate des Items
	 * @param b
	 *            : Y-Koordinate des Items
	 * @param item
	 *            : Art der Item-Nachricht
	 * 
	 * @author <Braun, Jan Julius, 6000100>
	 * 
	 */
	public ItemNachricht(int a, int b, int item) {
		super(item);
		this.xKoo = a;
		this.yKoo = b;
	}

	/**
	 * 
	 * Gibt die X-Koordinate des Items zur�ck.
	 * 
	 * @author <Braun, Jan Julius, 6000100>
	 * 
	 */
	public int getxKoo() {
		return this.xKoo;
	}

	/**
	 * 
	 * 
	 * Gibt die Y-Koordinate des Items zurueck.
	 * 
	 * @author <Braun, Jan Julius, 6000100>
	 */
	public int getyKoo() {
		return this.yKoo;
	}
}
