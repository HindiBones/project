package pp2016.team13.server.comm;

/**
 * Server wird erzeugt, Verbindungen werden verarbeitet
 * @author Kirthika Jeyakumar
 * 
 */

import java.io.*;  
import java.net.*;
import java.util.LinkedList;

import pp2016.team13.client.comm.Paket;
import pp2016.team13.server.engine.Levelverwaltung;
import pp2016.team13.shared.Nachrichten.AntwortNachricht;
import pp2016.team13.shared.Nachrichten.Cheat;
import pp2016.team13.shared.Nachrichten.FehlerNachricht;
import pp2016.team13.shared.Nachrichten.LevelNachricht;
import pp2016.team13.shared.Nachrichten.Nachricht;


public class Server implements Serializable{
 
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
 
 //Server wird gestartet - Verbindung wird hergestellt
 /**
  * Server wird gestartet
  * @author Kirthika Jeyakumar
  * @param port
  */
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

		
		// eingehende Verbindung wird verarbeitet

   System.out.println("Starte Server");
//   S = ServerS.accept();
//   System.out.println("Starte Server");
   run();
   
  }catch(IOException e){
   System.out.println("Funkt nicht");
  }}}

 
  
/**
 * Server laeuft und wendet die Methode handleconnection an
 * @author Kirthika Jeyakumar
 * 
 */
  
  public void run() throws IOException{
   System.out.println("Laeuft");
   this.openServer = true;
   while (this.openServer) {
    handleconnection();
   }
  }
  
  /**
   * Verbindung wird verarbeitet
   * @author Kirthika Jeyakumar
   */
  // eingehende Verbindung wird verarbeitet
  public void handleconnection(){
   try {
    oos = new ObjectOutputStream(S.getOutputStream());

    Paket n = new Paket();

    System.out.println("eine neue message wird erzeugt");

    ois = new ObjectInputStream(S.getInputStream());
    System.out.println("Nachricht kommt an");

    //System.out.println("Server versucht message vom Client zu verarbeiten");
    n = (Paket)ois.readObject();
    //ServerList.add(n);
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

  /**
   * Nachricht wird verarbeitet
   * @author Kirthika Jeyakumar
   * @param n : Nachricht
   * @return gibt die Antwort zurück
   */
  
  @SuppressWarnings("static-access")
  public Paket verarbeiteNachricht(Nachricht n){
   try{
   Nachricht antwortNachricht = new FehlerNachricht("Fehler!");
   switch(n.getTyp()){
   
   case 0: System.out.println("Login");antwortNachricht = new AntwortNachricht(Levelverwaltung.verarbeiteClientNachricht(n, spiel));login = true; break;
   case 1: antwortNachricht = new AntwortNachricht(Levelverwaltung.verarbeiteClientNachricht(n, spiel));break;
   case 2: antwortNachricht = new AntwortNachricht(Levelverwaltung.verarbeiteClientNachricht(n, spiel));break;
   case 3: antwortNachricht = new AntwortNachricht(Levelverwaltung.verarbeiteClientNachricht(n, spiel));break;
   case 4: antwortNachricht = new AntwortNachricht(Levelverwaltung.verarbeiteClientNachricht(n, spiel));break;
   case 5: antwortNachricht = new AntwortNachricht(Levelverwaltung.verarbeiteClientNachricht(n, spiel));break;
   case 6: antwortNachricht = new AntwortNachricht(Levelverwaltung.verarbeiteClientNachricht(n, spiel));break;
   case 7: antwortNachricht = new AntwortNachricht(Levelverwaltung.verarbeiteClientNachricht(n, spiel));break;
   case 8: antwortNachricht = new AntwortNachricht(spiel.verarbeiteClientNachricht(n, spiel));System.out.println(antwortNachricht.inOrdnung);break;
   case 9: antwortNachricht = new AntwortNachricht(Levelverwaltung.verarbeiteClientNachricht(n, spiel));break;
   case 10: antwortNachricht = new LevelNachricht(Levelverwaltung.levelSpeicherort); break;
   case 13: Levelverwaltung.verarbeiteClientNachricht(n, spiel);antwortNachricht = new Cheat(n.cheattyp);break;
   case 15: System.out.println("Spieler Logout");System.out.println("Server Beendet");System.exit(0);break;

   }
   Paket antwort = new Paket(antwortNachricht);
    System.out.println(antwort.getMessage().getTyp());
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