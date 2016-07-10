package pp2016.team13.client.comm;

/**
 * @author Kirthika Jeyakumar
 * 
 */

import java.io.*; 
import java.net.*;
import java.util.Date;
import java.util.LinkedList;
import pp2016.team13.client.comm.Lebenszeichen;
import pp2016.team13.client.engine.AntwortNachricht;
import pp2016.team13.client.engine.Cheat;
import pp2016.team13.client.engine.FehlerNachricht;
import pp2016.team13.client.engine.LevelNachricht;
import pp2016.team13.server.engine.Einloggen;
import pp2016.team13.server.engine.Levelverwaltung;
import pp2016.team13.shared.Level;
import pp2016.team13.client.engine.Nachricht;


public class Server implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//die einzelnen Streams werden definiert
	public ServerSocket ServerS;
	public Socket S;
	boolean login = false;
	boolean openServer;
	ObjectOutputStream oos=null;
	ObjectInputStream ois=null;
	OutputStreamWriter osw=null;
	InputStreamReader isw=null;
	LinkedList<Paket> ServerList = new LinkedList<Paket>();
	Levelverwaltung spiel;
	Date letztesLebenszeichen = null;
	
	//Server wird gestartet - Verbindung wird hergestellt
public Server(int port){
	System.out.println("Starte Server");
	try {
		ServerS = new ServerSocket(port);
		spiel = new Levelverwaltung(0, 10, 1, 0, 5, 1, 20, 5);
	} catch (IOException e1) {
		System.out.println("FEHLER");
	}
	
		while (true){
			
			try {
			//Server wartet auf eingehende Verbindungen
			S = ServerS.accept();
			ServerS.setSoTimeout(1);

			System.out.println("Starte Server");
//			S = ServerS.accept();
//			System.out.println("Starte Server");
			run();
			

		}catch(IOException e){
			System.out.println("Funkt nicht");
		}}}

	
		

		
		public void run() throws IOException{
			letztesLebenszeichen = new Date();
			letztesLebenszeichen.setTime(System.currentTimeMillis());
			System.out.println("Laeuft");
			this.openServer = true;
			while (this.openServer) {
				handleconnection();
				if(login && (Lebenszeichen.run(S, letztesLebenszeichen.getTime()))){
					this.openServer = false;
					System.out.println("Server Timeout");
					ServerS.close();
					S.close();
					System.exit(0);
				}else{
				}
			}
		}
		// eingehende Verbindung wird verarbeitet
		public void handleconnection(){
			try {
				oos = new ObjectOutputStream(S.getOutputStream());

				Paket n = new Paket();

				System.out.println("eine neue message wird erzeugt");

				ois = new ObjectInputStream(S.getInputStream());
				System.out.println("Nachricht kommt an");
				//System.out.println("Server empf�ngt message vom Client und versucht zu empfangen");
				//System.out.println("Server versucht message vom Client zu verarbeiten");
				n = (Paket)ois.readObject();
				ServerList.add(n);
				System.out.println("Server empfaengt message vom Client und versucht zu empfangen");
				System.out.println("Server versucht message vom Client zu verarbeiten");
				Paket antwort = verarbeiteNachricht(n.getMessage());
				System.out.println("Server hat die Message verarbeitet");
				oos.writeObject(antwort);
				
				oos.flush();

				System.out.println("Server hat eine message zurueckgeschickt");

			} catch (IOException | ClassNotFoundException e) {
			}
		}

		
		public Paket verarbeiteNachricht(Nachricht n){
			try{
			Nachricht antwortNachricht = new FehlerNachricht("Fehler!");
			switch(n.getTyp()){
			
			case 0: System.out.println("Login");antwortNachricht = new AntwortNachricht(Levelverwaltung.verarbeiteClientNachricht(n, spiel));login = true; letztesLebenszeichen.setTime(System.currentTimeMillis());; break;
			case 1: antwortNachricht = new AntwortNachricht(Levelverwaltung.verarbeiteClientNachricht(n, spiel));break;
			case 2: antwortNachricht = new AntwortNachricht(Levelverwaltung.verarbeiteClientNachricht(n, spiel));break;
			case 3: antwortNachricht = new AntwortNachricht(Levelverwaltung.verarbeiteClientNachricht(n, spiel));break;
			case 4: antwortNachricht = new AntwortNachricht(Levelverwaltung.verarbeiteClientNachricht(n, spiel));break;
			case 5: antwortNachricht = new AntwortNachricht(Levelverwaltung.verarbeiteClientNachricht(n, spiel));break;
			case 6: antwortNachricht = new AntwortNachricht(Levelverwaltung.verarbeiteClientNachricht(n, spiel));break;
			case 7: antwortNachricht = new AntwortNachricht(Levelverwaltung.verarbeiteClientNachricht(n, spiel));break;
			case 8: antwortNachricht = new AntwortNachricht(Levelverwaltung.verarbeiteClientNachricht(n, spiel));break;
			case 9: antwortNachricht = new AntwortNachricht(Levelverwaltung.verarbeiteClientNachricht(n, spiel));break;
			case 10: antwortNachricht = new LevelNachricht(Levelverwaltung.levelSpeicherort); break;
			case 13: Levelverwaltung.verarbeiteClientNachricht(n, spiel);antwortNachricht = new Cheat(n.cheattyp);break;
			case 14: letztesLebenszeichen.setTime(antwortNachricht.zeit);System.out.println("alive"); break;
			case 15: System.out.println("Spieler Logout");System.out.println("Server Beendet");System.exit(0);break;

			}
			Paket antwort = new Paket(antwortNachricht);
						return antwort;
			}
			catch(NullPointerException e){
				e.printStackTrace();
				return new Paket(new FehlerNachricht("NullPointerException!"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return new Paket(new FehlerNachricht("Exception!"));
			}
		}

}
	
