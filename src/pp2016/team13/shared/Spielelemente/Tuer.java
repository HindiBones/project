package pp2016.team13.shared.Spielelemente;
/**
 *  War gegeben
 *  
 * @author <unbekannt>
 *
 */
public class Tuer extends Spielelement {
	private boolean offen;

	public Tuer(boolean offen) {
		this.offen = offen;
	}
	
	public int getXTuerOffen(){
		if(offen==true)
			return 1;
		else 
			return 2;
		
	}
	public int getYTuerOffen(){
		return 1;
	}
		
	public void setOffen() {
		offen = true;
	}

	public void setVerschlossen() {
		offen = false;
	}

	public boolean istOffen() {
		return offen;
	}
}
