package pp2016.team13.shared.Spielelemente;

/**
 * 
 * @author Marius, Rest war gegeben
 *
 */
public class Heiltrank extends Spielelement {
	private int wirkung;
	
	//Fuer die Server Engine ; fuer die Trankliste
	 public int posX;
	 public int posY;
	 public boolean aufgehoben;
	 public boolean gedroppt;
	 public int trankID;

	public Heiltrank(int wirkung) {
		this.wirkung = wirkung;
	}

	//Konstruktor fuer die Server Engine
		public Heiltrank(int trankID, int j, int i) {
			this.trankID = trankID;
			setGedroppt(false);
			setAufgehoben(false);
			setPosX(j);
			setPosY(i);
		}

		// Methoden fuer die Server Engine
		//Keine setter-Methode fuer die Trank-ID, da diese nicht veraendert werden soll
						 
		//getter-Methode fuer die Trank-ID;
		public int getTrankID(){
			return trankID;
		}
						 
		//setter-Methode, die festlegt, ob der Trank bereits gedroppt wurde
		public void setGedroppt(boolean neuGedroppt){
			gedroppt = neuGedroppt;
		}
						 
		//getter-Methode, die anzeigt, ob der Trank bereits gedroppt wurde
		public boolean getGedroppt(){
			return gedroppt;
		}
						 
		//setter-Methode, die den Zustand des Tranks aendert
		public void setAufgehoben(boolean neuAufgehoben){
			aufgehoben=neuAufgehoben;
		}
				 
		//getter-Methode fuer den Zustand des Tranks
		 public boolean getAufgehoben(){
			 return aufgehoben;
		 }
				 
		 //setter-Methode fuer die x-Koordinate des Tranks
		 public void setPosX(int neuPosX){
			 posX=neuPosX;
		 }
		 
		 //getter-Methode fuer die x-Koordinate des Tranks
		 public int getPosX(){
		  return posX;
		 }
		 
		 //setter-Methode fuer die y-Koordinate des Tranks
		 public void setPosY(int neuPosY){
		  posY=neuPosY;
		 }
		 
		 //getter-Methode fuer die y-Koordinate des Tranks
		 public int getPosY(){
		  return posY;
		 }
	
	//Methoden fuer den Client
	public int getWirkung() {
		return wirkung;
	}
}
