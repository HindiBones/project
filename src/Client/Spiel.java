package Client;
//Tester Kann raus

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Spiel implements KeyListener {

	Level level, LevelServerseite;
	Spieler s;
	Heiltrank t;
	Ziel z;
	Schluessel k;
	Client client;
	JFrame fenster;
	
	//Das Level wird als Array generiert
	//Die 5 steht für eine Wand
	//Die 1 steht für den Spieler
	//Die 2 steht für das Ziel
	//Die 3 steht für den Heiltrank
	//Die 4 steht für den Schluessel
	
	public Spiel(){

		s = new Spieler(1);
		t = new Heiltrank(50,0,4);
		z = new Ziel (3,3);
		k = new Schluessel (4,4);
		client = new Client(1);
		// Behelfsmaessiger Server, der dem Client ein Level sendet
		Client server = new Client(0);
		//Generierung
		LevelServerseite = new Level(1,new int[5][5]);
		
		// Level wird an Client gesendet
		server.sende(new LevelNachricht( LevelServerseite));
		server.uebertrage(client);
		
		// BIS HIERHIN FINDET SPAETER ALLES AUF SERVERSEITE STATT!
		
		// Client empfaengt Level und benutzt dieses
		client.empfange();
		level = client.aktuellesLevel;
		// Testbenutzer wird eingeloggt
		client.sende(new LoginNachricht( "Test-Benutzer", "Passwort123"));
		client.ausgabe();
		//Solange das Ziel nicht erreicht ist
		aktualisiere();
		
		// Das Level wird ein letztes Mal gezeichnet, mit dem Spieler auf der Position der Tür
	}
	
	// Methode zur Überpüfung der Trank-Aufnahme
	public boolean KannTrankAufgenommenwerden(){
		if(t.getPosX()==s.getPosX()&&t.getPosY()==s.getPosY()){
			if(!t.getStatus()){

				return true;
			}	
		}
		return false;
	}
	
	// Methode zur Überpüfung der Schluessel-Aufnahme
	public boolean KannKeyAufgenommenwerden(){
		if(k.getPosX()==s.getPosX()&&k.getPosY()==s.getPosY()){
			if(!k.getStatus()){

				return true;
			}	
		}
		return false;
	}
	
	// Methode zur Überpüfung ob der Spieler im Ziel ist und den Schlüssel besitzt
	public boolean ImZielAngekommen(){
		if(s.getPosX()==z.getPosX()&&s.getPosY()==z.getPosY() && k.getStatus()){
			return true;
		}
		if(s.getPosX()==z.getPosX()&&s.getPosY()==z.getPosY() && !k.getStatus()){
			System.err.println("Du hast den Schluessel noch nicht!");
		}
		return false;
	}
	
	// Konsistenzcheck, ob bewegen in die Richtung möglich ist
	
	public static void main(String[] args){
		Spiel s = new Spiel();
		JFrame fenster = new JFrame("New Window");
		fenster.setSize(50,50);
		fenster.setVisible(true);
		fenster.addKeyListener(s);
		fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		if(arg0.getKeyCode() == KeyEvent.VK_DOWN){
			if(s.getPosY()!=level.getLaengeY() && level.getBestimmtenLevelInhalt(s.getPosX(), s.getPosY()+1) != 5){
				s.runter();
				client.sende(new BewegungsNachricht(s.getID(),s.getPosX(),s.getPosY()));
				aktualisiere();
				}
		}
		else if(arg0.getKeyCode() == KeyEvent.VK_UP){
			if(s.getPosY()!=0 && level.getBestimmtenLevelInhalt(s.getPosX(), s.getPosY()-1) != 5){
				s.hoch();
				client.sende(new BewegungsNachricht(s.getID(),s.getPosX(),s.getPosY()));
				aktualisiere();
				}
		}
		else if(arg0.getKeyCode() == KeyEvent.VK_RIGHT){
			if(s.getPosX()!=level.getLaengeX() && level.getBestimmtenLevelInhalt(s.getPosX()+1, s.getPosY()) != 5){
				s.rechts();
				client.sende(new BewegungsNachricht(s.getID(),s.getPosX(),s.getPosY()));
				aktualisiere();
				}
		}
		else if(arg0.getKeyCode() == KeyEvent.VK_LEFT){
			if(s.getPosX()!=0 && level.getBestimmtenLevelInhalt(s.getPosX()-1, s.getPosY()) != 5){
				s.links();
				client.sende(new BewegungsNachricht(s.getID(),s.getPosX(),s.getPosY()));
				aktualisiere();
				}
		}
		else if(arg0.getKeyCode() == KeyEvent.VK_T){
			s.benutzeHeiltrank(t);
			aktualisiere();
		}
		else if(arg0.getKeyCode() == KeyEvent.VK_PERIOD){
			k.aufnehmen();
			aktualisiere();
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void aktualisiere(){
		//Level wird mit 0en ueberschrieben
		Level.setInhaltNull();
	
	//Felder mit Spieler etc. werden entsprechend gesetzt; Nur als Testlevel, spaeter wird das Level vom Server uebernommen

		Level.setLevelInhalt(1,0, 5);
		Level.setLevelInhalt(1,1, 5);
		Level.setLevelInhalt(1, 2, 5);
		Level.setLevelInhalt(1,3, 5);
		Level.setLevelInhalt(3,4, 5);
		Level.setLevelInhalt(3, 2, 5);
		Level.setLevelInhalt(3,1, 5);
		
		level.setLevelInhalt(z.getPosX(), z.getPosY(), 2);
		if(!t.getStatus()){
			level.setLevelInhalt(t.getPosX(), t.getPosY(), 3);
		}
		if (!k.getStatus()){
			level.setLevelInhalt(k.getPosX(), k.getPosY(), 4);
		}
		level.setLevelInhalt(s.getPosX(),s.getPosY(), 1);
		level.ausgabe();
		System.out.println("Gesundheit: "+s.getGesundheit()+"  Anzahl Traenke: "+s.AnzahlTrank() + " Schluessel:" + k.getStatus());
	
	//Spieler kann sich bewegen, durch eingabe von hoch, runter, links, rechts; cheat gibt dem Spieler sofort den Schlüssel
		
	//Wenn der Spieler auf ein Feld mit einem Trank kommt, wird dieser gefragt ob er ihn aufnehmen will
		if(KannTrankAufgenommenwerden()){
			switch(JOptionPane.showInputDialog("Du stehts auf einem Trank, moechtest du ihn aufnehmen?")){
			case "ja":	s.anzHeiltraenke++;
						t.aufnehmen();
						client.sende(new ItemNachricht(t.getPosX(),t.getPosY(), t));
						break;
			}
	}
			
	//Wenn der Spieler auf ein Feld mit einem Schlüssel kommt, wird dieser gefragt ob er ihn aufnehmen will
		if(KannKeyAufgenommenwerden()){
			switch(JOptionPane.showInputDialog("Du stehst auf einem Schluessel, moechtest du ihn aufnehmen?")){
			case "ja":	k.aufnehmen();
						client.sende(new ItemNachricht(k.getPosX(),k.getPosY(), k));
						break;
			}
	}
		
		if(ImZielAngekommen()){
		level.setInhaltNull();
		level.setLevelInhalt(z.getPosX(), z.getPosY(), 2);
		if(!t.getStatus()){
			level.setLevelInhalt(t.getPosX(),t.getPosY(), 3);
		}
		if (!k.getStatus()){
			level.setLevelInhalt(k.getPosX(), k.getPosY(), 4);
		}
		level.setLevelInhalt(s.getPosX(), s.getPosY(), 1);
		level.ausgabe();
		
		// Das Level ist nun geschafft und der Spieler (testweise) und der Server werden informiert
		System.out.println("Du hast gewonnen!!!");
		client.sende(new Nachricht(3));
		System.out.println("");
		client.ausgabe();
		System.exit(0);
		}
	}
}

