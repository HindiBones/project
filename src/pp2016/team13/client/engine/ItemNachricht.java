package pp2016.team13.client.engine;

// Nachricht, die gesendet wird, wenn ein Trank (typ 2) oder der Schluessel (typ 4) aufgenommen wurden
public class ItemNachricht extends Nachricht{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * item = 2: Trank aufgehoben
	 * item = 4: Schluessel aufgehoben
	 * item = 8: Trank benutzt
	 */
	/**
	 *
	 * Erstellt eine ItemNachricht. 
	 * 
	 *  @author Julius
	 * @param a: X-Koordinate des Items
	 * @param b: Y-Koordinate des Items
	 * @param item: Art der Item-Nachricht. 2 = Trank aufgehoben, 4 = Schluessel aufgehoben, 12 = Trank benutzt
	 * 
	 */
	public ItemNachricht(int a, int b, int item){
		super(item);
		this.xKoo=a;
		this.yKoo=b;
	}
	
	/**
	 * 
	 * Gibt die X-Koordinate des Items zurück.
	 * @author Julius
	 * 
	 */
	public int getxKoo(){
		return this.xKoo;
	}
	
	/**
	 * 
	 * 
	 * Gibt die Y-Koordinate des Items zurueck.
	 * @author Julius
	 */
	public int getyKoo(){
		return this.yKoo;
	}
}
