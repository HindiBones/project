package pp2016.team13.client.engine;

public class AntwortNachricht extends Nachricht {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AntwortNachricht(boolean reaktion) {
		super(11);
		this.inOrdnung = reaktion;
	}
}
