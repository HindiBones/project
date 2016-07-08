package pp2016.team13.client.comm;

import java.io.IOException;
import java.io.Serializable;
//import datenstruktur.level




import pp2016.team13.client.engine.Nachricht;
import pp2016.team13.shared.Level;

//Nachricht1 renamen in Nachricht - wurde nur Nachricht1 genannt wegen Doppelbennenung
public class Paket2 implements Serializable {
	
	
	
	private static final long serialVersionUID = 1;
	protected long time;
	public Nachricht inhalt;
	int typ;
	boolean aufgenommen;
	String fehlermeldung, benutzername, passwort;
	Level[] leveldaten;
	int xKoo;
	int yKoo;
	int spielerID;
	
	
	public Paket2(){
		this.time=System.currentTimeMillis();
	}
	public Paket2(Nachricht Test){
		this.time=System.currentTimeMillis();
		this.inhalt=Test;
	}
	
	
	/* 
	 * Typen von Messages:
	 * type 0 : Login-Message
	 * type 1 : Spielerbewegung
	 * type 2 : Itemaufnahme
	 * type 3 : Level geschafft
	 * type 4 : Fehler (z.B. falsche Login-Daten, inkonsistente Bewegungen, usw)
	 * type 5 : Level geladen
	 * type 6 : Kampfnachricht
	 */

	
	// Nachricht, die gesendet wird, wenn das Level geschafft wurde - Nur zum Testen
	public Paket2 (int t){
		this.typ = t;
	}
	
	public int getTyp(){
		return this.typ;
	}

	public int getID(){
		return this.spielerID;
	}
	public int getxKoo(){
		return this.xKoo;
	}
	public int getyKoo(){
		return this.yKoo;
	}
	public String getfehlermeldung(){
    	return this.fehlermeldung;
    }

    public void setfehlermeldung(String fehlermeldung){
    	this.fehlermeldung = fehlermeldung;
    }
    public String getbenutzername(){
    	return this.benutzername;
    }

    public void setbenutzername(String benutzername){
    	this.benutzername = benutzername;
    }
    
    public String getpasswort(){
    	return this.passwort;
    }

    public void setpasswort(String passwort){
    	this.passwort = passwort;
    }
    public Level[] getleveldaten(){
    	return this.leveldaten;
   }

    public void setleveldaten(Level[] leveldaten){
    	this.leveldaten = leveldaten;
    }
    
    public boolean getaufgenommen(){
    	return this.aufgenommen;
    }

    public void setaufgenommenn(boolean aufgenommen){
    	this.aufgenommen = aufgenommen;
    }

	
	private void writeObject(java.io.ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
    }
 
    private void readObject(java.io.ObjectInputStream stream) throws IOException {
        try {
            stream.defaultReadObject();
        } catch (ClassNotFoundException e) {
            throw new IOException("No Class Found");
        }
}
	
	
}

	
	


