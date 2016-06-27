package Client;

// Nachricht, die gesendet wird, wenn sich der Spieler bewegt, die Koordinaten sind die neuen Koordinaten
public class BewegungsNachricht extends Nachricht{
	
	
	public BewegungsNachricht(int p,int x, int y){
		super(1);
		this.spielerID=p;
		this.xKoo=x;
		this.yKoo=y;
	}
	

}
