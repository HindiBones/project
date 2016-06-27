package Client;

public class Schluessel extends Spielelement{
	
	//Ein Key wird benötigt, damit man das nächste Level erreichen kann
	//Er liegt an einer Stelle x,y im Level
	public Schluessel (int x, int y){
		this.posx=x;
		this.posy=y;
		this.aufgenommen= false;
	}
	
}
