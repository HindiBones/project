package Client;

// Nachricht, die gesendet wird, wenn ein Trank (typ 2) oder der Schluessel (typ 4) aufgenommen wurden
public class ItemNachricht extends Nachricht{
	
	
	/*
	 * item = 2: Trank aufgehoben
	 * item = 4: Schluessel aufgehoben
	 * item = 8: Trank benutzt
	 */
	public ItemNachricht(int a, int b, int item){
		super(item);
		this.xKoo=a;
		this.yKoo=b;
	}
	public int getxKoo(){
		return this.xKoo;
	}
	public int getyKoo(){
		return this.yKoo;
	}
}
