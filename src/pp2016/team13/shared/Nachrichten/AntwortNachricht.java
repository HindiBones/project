package pp2016.team13.shared.Nachrichten;

public class AntwortNachricht extends Nachricht {

	/**
	 * @author <Braun, Jan Julius, 6000100>
	 */
	private static final long serialVersionUID = 1L;

	public AntwortNachricht(boolean reaktion) {
		super(11);
		this.inOrdnung = reaktion;
	}
}
