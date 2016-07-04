package Client;

public class ChatNachricht extends Nachricht{

	/**
	 * @author Julius
	 * @param Nachricht: Nachricht in Textform, die gesendet werden soll
	 * 
	 * Nachrichten-Objekt, das eine Nachricht in Textform enthält, die im Chat gesendet wurde
	 */
	public ChatNachricht(String Nachricht)
	{
		super(9);
		this.nachricht = Nachricht;
	}
}
