package pp2016.team13.client.comm;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.LinkedList;

import pp2016.team13.client.engine.FehlerNachricht;
import pp2016.team13.client.engine.Nachricht;

//import komClient.Nachricht;

public class Client extends Paket {
	LinkedList<Paket> ClientList = new LinkedList<Paket>();
	InputStream is;
	OutputStream os;
	ObjectInputStream ois=null;
	ObjectOutputStream oos=null;
	Socket cs;
	
	public Client(String host,int port){
		try{
			cs = new Socket(host, port);
			//System.out.println("connection zum Client steht");
			
		}catch(IOException e){
			
		}
	}
	public Paket SendeAnServer(Paket msg){
					
		try{
			oos = new ObjectOutputStream(cs.getOutputStream());
			//System.out.println("ObjectStream steht");
			oos.writeObject(msg);
			oos.flush();
			//System.out.println("Client sendet an Server");
			Paket serverAntwort = new Paket();
			ois=new ObjectInputStream(cs.getInputStream());
			//System.out.println("ObjectInputStream steht");
			serverAntwort=(Paket)ois.readObject();
			//ClientList.addLast(testmsg);
			return serverAntwort;
		}catch(IOException e){
			Paket serverAntwort = new Paket(new FehlerNachricht(e.getMessage()));
			e.printStackTrace();
			return serverAntwort;
		} catch (ClassNotFoundException e) {
			Paket serverAntwort = new Paket(new FehlerNachricht(e.getMessage()));
			return serverAntwort;
		}
		
	}

}
