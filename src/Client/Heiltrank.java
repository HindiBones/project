package Client;

//Hat Attribute und Methoden des Spielelements
public class Heiltrank extends Spielelement{
	private int menge;
	
		//Ein Heiltrank heilt einen Spieler und wird auf einer Position x,y generiert
		public Heiltrank(int menge,int x,int y){
			this.menge = menge;
			this.posx=x;
			this.posy=y;
		}
		
		//Heilwirkung des Tranks zurückgeben
		public int getMenge(){
			return menge;
		}
}
