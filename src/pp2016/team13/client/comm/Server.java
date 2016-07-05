package pp2016.team13.client.comm;
import java.io.*; 
import java.net.*;
import java.util.LinkedList;

import pp2016.team13.client.engine.FehlerNachricht;
import pp2016.team13.client.engine.LevelNachricht;
import pp2016.team13.server.engine.Einloggen;
import pp2016.team13.server.engine.Levelverwaltung;
import pp2016.team13.client.engine.Nachricht;


public class Server implements Serializable{
	//die einzelnen Streams werden definiert
	public ServerSocket ServerS;
	public Socket S;
	boolean openServer;
	ObjectOutputStream oos=null;
	ObjectInputStream ois=null;
	OutputStreamWriter osw=null;
	InputStreamReader isw=null;
	LinkedList<Nachricht> ServerList = new LinkedList<Nachricht>();
	Levelverwaltung spiel;
public Server(int port){
	System.out.println("Starte Server");
	try {
		ServerS = new ServerSocket(port);
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
		while (true){
			
			try {
			ServerS.setSoTimeout(600000);

			S = ServerS.accept();
			ServerS.setSoTimeout(60000);

			System.out.println("Starte Server");
//			S = ServerS.accept();
//			System.out.println("Starte Server");
			run();
			

		}catch(IOException e){
			System.out.println("Funkt nicht");
		}}}

	
		


		
		public void run() throws IOException{
			System.out.println("L�uft");
			this.openServer = true;

			Levelverwaltung spiel = new Levelverwaltung(0, 10, 1, 0, 5, 1, 16, 5);
			while (this.openServer) {
				handleconnection();
			}
		}
		
		public void handleconnection(){
			try {
				oos = new ObjectOutputStream(S.getOutputStream());

				Nachricht n = new Nachricht();
				//System.out.println("eine neue message wird erzeugt");

				Paket temp;
				System.out.println("eine neue message wird erzeugt");

				ois = new ObjectInputStream(S.getInputStream());

				//System.out.println("Server empf�ngt message vom Client und versucht zu empfangen");
				//System.out.println("Server versucht message vom Client zu verarbeiten");
				n = (Nachricht)ois.readObject();
				ServerList.add(n);
				System.out.println(n.getMessage(n));
				Nachricht j=new Nachricht(" Der Server reagiert auf den Client");
				oos.writeObject(j);

				System.out.println("Server empf�ngt message vom Client und versucht zu empfangen");
				System.out.println("Server versucht message vom Client zu verarbeiten");
				temp = (Paket)ois.readObject();
				ServerList.add(temp.getMessage());
				Paket antwort = verarbeiteNachricht(temp.getMessage());
				System.out.println("Server hat die Message verarbeitet");
				//Nachricht j=new Nachricht(" Der Server reagiert auf den Client");
				oos.writeObject(antwort);

				oos.flush();

				//System.out.println("Server hat eine message zur�ckgeschickt");

				System.out.println("Server hat eine message zur�ckgeschickt");

			} catch (IOException | ClassNotFoundException e) {
			}
		}

		
		public Paket verarbeiteNachricht(Nachricht n){
			Nachricht antwortNachricht = new FehlerNachricht("Unbekannter Nachrichtentyp!");
			switch(n.getTyp()){
			case 10: antwortNachricht = new LevelNachricht(spiel.levelSendePaket); break;
			}
			Paket antwort = new Paket(antwortNachricht);
			return antwort;
		}

				
}
	
