package Client;

public class KampfNachricht extends Nachricht{
	
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
