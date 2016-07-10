package pp2016.team13.server.map;
/**
 * 
 * @author Cigdem
 * werden gebraucht um die Positionen zu speichern
 */
public class Koordinaten {
	public int xKoordinate;
	public int yKoordinate;

	public Koordinaten (int x , int y){
		xKoordinate = x;
		yKoordinate = y;
		}
	
	public void setKoordinaten(int x, int y){
		xKoordinate = x;
		yKoordinate = y;
	}
}
