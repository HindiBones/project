package pp2016.team13.server.map;

/**
 * werden gebraucht um die Positionen zu speichern x und y Werte sind int Werte
 * 
 * @author <Akbulut, Cigdem, 5382718>
 */
public class Koordinaten {
	public int xKoordinate;
	public int yKoordinate;

	public Koordinaten(int x, int y) {
		xKoordinate = x;
		yKoordinate = y;
	}

	public void setKoordinaten(int x, int y) {
		xKoordinate = x;
		yKoordinate = y;
	}
}
