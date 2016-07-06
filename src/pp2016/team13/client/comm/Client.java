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
			if(cs.getPort() == 13000)
			System.out.println("connection zum Server steht");
		}catch(IOException e){
			System.err.println("Konnte keine Verbindung herstellen!");
		}
	}
	public Paket SendeAnServer(Paket msg){

		Paket serverAntwort = new Paket();
		try{
			Thread.sleep(100);
			oos = new ObjectOutputStream(cs.getOutputStream());
			System.out.println("ObjectStream steht");
			oos.writeObject(msg);
			System.out.println(msg.inhalt.getTyp());
			oos.flush();
			System.out.println("Client sendet an Server");
			serverAntwort = new Paket();
			ois=new ObjectInputStream(cs.getInputStream());
			System.out.println("ObjectInputStream steht");
			serverAntwort=(Paket)ois.readObject();
			serverAntwort.inhalt.leveldaten[0].ausgabe();
			ClientList.addLast(serverAntwort);
		}
		catch(IOException e){
			 serverAntwort = new Paket(new FehlerNachricht(e.getMessage()));
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			 serverAntwort = new Paket(new FehlerNachricht(e.getMessage()));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			 serverAntwort = new Paket(new FehlerNachricht(e.getMessage()));
			e.printStackTrace();
		}

		return serverAntwort;
	}

}
