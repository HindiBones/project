package pp2016.team13.client.comm;
import java.io.*; 
import java.net.*;
import java.util.LinkedList;

import pp2016.team13.client.engine.ChatNachricht;
import pp2016.team13.client.engine.Nachricht;
import pp2016.team13.server.engine.Levelverwaltung;


public class Server {
	//die einzelnen Streams werden definiert
	public ServerSocket ServerS;
	public Socket S;
	boolean openServer;
	ObjectOutputStream oos=null;
	ObjectInputStream ois=null;
	OutputStreamWriter osw=null;
	InputStreamReader isw=null;
	LinkedList<Paket> ServerList = new LinkedList<Paket>();
	
public Server(int port){
		
		while (true){
			try {
			ServerS = new ServerSocket(port);
			ServerS.setSoTimeout(60000);
			S = ServerS.accept();
			System.out.println("Starte Server");
			run();
			
		}catch(IOException e){}
		}
}

		
		public void run() throws IOException{
			this.openServer = true;
			Levelverwaltung spiel = new Levelverwaltung(0, 10, 1, 0, 5, 1, 15, 5);
			while (this.openServer) {
				handleconnection();
			}
		}
		
		public void handleconnection(){
			try {
				oos = new ObjectOutputStream(S.getOutputStream());
				Paket n = new Paket();
				//System.out.println("eine neue message wird erzeugt");
				ois = new ObjectInputStream(S.getInputStream());
				//System.out.println("Server empf�ngt message vom Client und versucht zu empfangen");
				//System.out.println("Server versucht message vom Client zu verarbeiten");
				n = (Paket)ois.readObject();
				ServerList.add(n);
				System.out.println(n.getMessage());
				Nachricht m = new ChatNachricht(" Der Server reagiert auf den Client");
				Paket j=new Paket(m);
				oos.writeObject(j);
				oos.flush();
				//System.out.println("Server hat eine message zur�ckgeschickt");
			} catch (IOException | ClassNotFoundException e) {
			}
		}
				
}
	
