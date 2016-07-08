package pp2016.team13.client.engine;

public class KampfNachricht extends Nachricht{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @author Julius
	 * @param spielerID: ID des ausloesenden Spielers
	 * 
	 * Erstellt eine KampfNachricht. Beinhaltet die ID des Spielers, der gerade kaempft
	 */
	public KampfNachricht(int spielerID){
		super(7);
		this.spielerID = spielerID;
	}
}
