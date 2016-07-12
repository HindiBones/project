package pp2016.team13.shared.Nachrichten;

/**
 * Kampf-Nachricht wird verschickt, wenn ein Spieler ein Monster angreift
 * 
 * @author <Braun, Jan Julius, 6000100>
 *
 */
public class KampfNachricht extends Nachricht {

	private static final long serialVersionUID = 1L;

	/**
	 * Erstellt eine KampfNachricht (Typ 7). Beinhaltet die ID des Spielers, der
	 * gerade kaempft
	 * 
	 * @param spielerID
	 *            : ID des ausloesenden Spielers
	 * 
	 * 
	 * @author <Braun, Jan Julius, 6000100>
	 * 
	 */
	public KampfNachricht(int spielerID) {
		super(7);
		this.spielerID = spielerID;
	}
}
