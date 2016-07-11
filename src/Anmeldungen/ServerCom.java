package pp2016.team12.client.comm;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import pp2016.team12.shared.nachrichten.*;

/**
 * 
 * ueber diese Klasse wird der Client mit dem Server verbunden
 * jegliche Kommunikation von Nachrichten laeuft ueber diese Klasse
 * sende "ich bin noch da" Nachrichten in regelmaessigen Abstaenden, damit der Server weiss, dass Client noch da
 *
 * @author Noever, Lena, 5844312
 */

public class ClientKom {
	private ObjectInputStream streamInput;
	private ObjectOutputStream streamOutput;
	private Socket socket;
	ClientEngine cEngine;
	private InetAddress serverIp;
	private String server;
	private int port;
	
	boolean binAmLeben;
		
	/**
	 * 
	 * Konstruktor
	 *
	 * @param server
	 * @param port
	 * @param cEngine
	 *
	 * @author Esser, Janina, 5958229
	 */

	public ClientKom(String server, int port, ClientEngine cEngine){
		try {
			this.server = server;
			this.port = port;
			this.cEngine = cEngine;
			serverIp = InetAddress.getByName(server);
		} catch (UnknownHostException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * erstelle Socket (ueber serverIp) und mit im Konstruktor uebergebenen Port
	 * und stelle so Verbindung her
	 * 
	 * @return
	 *
	 * @author Noever, Lena, 5844312
	 */

	public boolean verbinden(){
		try{
			socket = new Socket(serverIp.getHostAddress(), port);
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
		
		/**
		 * ueber socket ObjectInputStream und ObjectOutputStream erstellen zur Serialisierung und Deserialisierung
		 */
		try{
			streamInput = new ObjectInputStream(socket.getInputStream());
			streamOutput = new ObjectOutputStream(socket.getOutputStream());
		}
		catch(IOException e){
			e.printStackTrace();
			return false;
		}
		
		/**
		 * starte hier die Threads
		 */
		new empfangevomServer().start();
		new sendeLebenszeichen().start();
		binAmLeben = true;
		return true;
		
	}
	
	/**
	 * 
	 * trennt Client vom Server (schließt Input/OutputStream und socket)
	 *
	 *
	 * @author Noever, Lena, 5844312
	 */

	public void trennen(){
		try{
			if(streamOutput != null) {
				streamOutput.close();
				System.out.println("OutputStream geschlossen.");
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		try{
			if(streamInput != null){
				streamInput.close();
				System.out.println("InputStream geschlossen.");
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		try{
			if(socket != null){
				socket.close();
				System.out.println("Socket geschlossen.");
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * sende Nachricht an Server
	 * hier kann jeder Typ von Nachricht verschickt werden
	 *
	 * @param msg
	 *
	 * @author Noever, Lena, 5844312
	 */

	public synchronized void sendeNachricht(Nachricht msg){
		try{
			streamOutput.writeObject(msg);
			streamOutput.flush();
			//sofern keine Lebenszeichen Nachricht:
			if(!(msg.getNachrichtTyp() == 0)){
				System.out.println("Nachricht gesendet [Typ] : " + msg.getNachrichtTyp());
			}
			else{
				System.out.println("Lebenszeichen gesendet");
			}
		}
		catch(IOException e){
			System.out.println("Server ist nicht mehr erreichbar. Nachricht wurde nicht gesendet.");
			//schliesseHintergrundAufgaben beendet durch binAmLeben = false die Threads 
			schliesseHintergrundAufgaben();
		}
	}
	
	/**
	 * 
	 * durch binAmLeben = false werden keine Lebenszeichen mehr gesendet
	 *
	 *
	 * @author Noever, Lena, 5844312
	 */

	private void schliesseHintergrundAufgaben(){
		binAmLeben = false;
	}
	
	/**
	 * 
	 * Ich bin noch da Meldung (thread)
	 *
	 * @author Noever, Lena, 5844312
	 */

	public class sendeLebenszeichen extends Thread {
		public void run(){
			try{
				while(binAmLeben){
					sendeNachricht(new Lebenszeichen());
				    Thread.sleep(1000);
				}
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 
	 * empfange Nachrichten vom Server (thread)
	 *
	 * @author Noever, Lena, 5844312
	 */

	class empfangevomServer extends Thread{
		public void run(){
			while(true){
				try{					
					Nachricht rawMsg = (Nachricht) streamInput.readObject();
					if(rawMsg instanceof Verbindungsabbruch){
						trennen();
					}
					else if (rawMsg!=null){
						System.out.println("Client hat Nachricht empfangen");
						cEngine.empfangeNachricht(rawMsg); 
					}	
				}
				catch(EOFException e){
					System.out.println("Daten vom Server werden nicht mehr richtig gelesen");
					break;
				}
				catch(IOException e){
					e.printStackTrace();
					break;
				}
				catch(ClassNotFoundException cnf_event){
					cnf_event.printStackTrace();
				}
			}
		}
	}
}

	
	