package pp2016.team13.shared.Nachrichten;

public class Cheat extends Nachricht {

	private static final long serialVersionUID = 1L;

	public Cheat(int typ) {
		super(13);
		this.cheattyp = typ;
		this.inOrdnung = true;
	}
}
