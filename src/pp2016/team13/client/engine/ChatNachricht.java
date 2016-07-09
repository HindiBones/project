package pp2016.team13.client.engine;

public class ChatNachricht extends Nachricht{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @author Julius
	 * @param Nachricht: Nachricht in Textform, die gesendet werden soll
	 * 
	 * Nachrichten-Objekt, das eine Nachricht in Textform enthält, die im Chat gesendet wurde
	 */
	public ChatNachricht(String Nachricht)
	{
		super(8);
		this.nachricht = Nachricht;
	}
	
	public boolean istCheat(){
		boolean istCheat = false;
		
		switch(this.nachricht){
		
		case ("<#unbesiegbar>"): istCheat = true;
		case ("<#durchsicht>"): istCheat = true;
		case ("<#levelskip>"): istCheat = true;
		}
		
		return istCheat;
	}
	
	public int getCheat(){
		int ergebnis = 0;
		if(this.istCheat())
		{
			switch(this.nachricht)
			{
			case ("<#unbesiegbar>"): ergebnis = 1;break;
			case ("<#durchsicht>"): ergebnis = 2; break;
			case ("<#levelskip>"): ergebnis = 3;break;
			}
		}
		
		return ergebnis;
	}
}
