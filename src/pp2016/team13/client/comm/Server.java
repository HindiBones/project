package pp2016.team13.client.comm;
import java.io.*; 
import java.net.*;
import java.util.LinkedList;
import pp2016.team13.client.engine.LevelNachricht;
import pp2016.team13.server.engine.Levelverwaltung;
import pp2016.team13.client.engine.Nachricht;


public class Server {
	//die einzelnen Streams werden definiert
	public ServerSocket ServerS;
	public Socket S;
	boolean openServer;
	ObjectOutputStream oos=null;
	ObjectInputStream ois=null;
	OutputStreamWriter osw=null;
	InputStreamReader isw=null;
	LinkedList<Nachricht> ServerList = new LinkedList<Nachricht>();
	
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
			LevelNachricht sendeLevel = new LevelNachricht(spiel.levelSendePaket);
			while (this.openServer) {
				handleconnection();
			}
		}
		
		public void handleconnection(){
			try {
				oos = new ObjectOutputStream(S.getOutputStream());
				Nachricht n = new Nachricht();
				//System.out.println("eine neue message wird erzeugt");
				ois = new ObjectInputStream(S.getInputStream());
				//System.out.println("Server empf�ngt message vom Client und versucht zu empfangen");
				//System.out.println("Server versucht message vom Client zu verarbeiten");
				n = (Nachricht)ois.readObject();
				ServerList.add(n);
				System.out.println(n.getMessage(n));
				Nachricht j=new Nachricht(" Der Server reagiert auf den Client");
				oos.writeObject(j);
				oos.flush();
				//System.out.println("Server hat eine message zur�ckgeschickt");
			} catch (IOException | ClassNotFoundException e) {
			}
		}
				
}
	
