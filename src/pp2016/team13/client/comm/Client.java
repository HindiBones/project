package pp2016.team13.client.comm;

/**
 *@author Kirthika Jeyakumar 
 *Kommunikation
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.LinkedList;

import pp2016.team13.client.engine.FehlerNachricht;
import pp2016.team13.client.engine.Nachricht;

/**
 * 
 * @author Kiki Jeyakumar
 * Klasse Client - Stellt die Verbindung zum Server da und sendet Nachrichten
 *
 */
public class Client extends Paket {
	// Liste wird genriert für die Nachrichten
	LinkedList<Paket> ClientList = new LinkedList<Paket>();
	// einzelne Streams werden erzeugt
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

	// Ich bin noch da periodisch senden
	
	public class sendeLebenszeichen extends Thread{
		public void run(){
			try{
			   while(binAmLeben){
				  sendeNachricht(new Lebenszeichen());
				  Thread.sleep(1000);
				}
		    }
			catch(Exception e){
				e.printStackTrace();
			}
	    }
	
	 }
	}
