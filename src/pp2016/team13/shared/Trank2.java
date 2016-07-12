package pp2016.team13.shared;

public class Trank2 {
		private int wirkung;
		
		//Fuer die Server Engine ; fuer die Trankliste
		 public static int posX;
		 public static int posY;
		 public static boolean aufgehoben;
		 public static boolean gedroppt;
		 public int trankID;

		public Trank2(int wirkung) {
			this.wirkung = wirkung;
		}

		//Konstruktor fuer die Server Engine
			public Trank2(int trankID, int j, int i) {
				this.trankID = trankID;
				setGedroppt(false);
				setAufgehoben(false);
				setPosX(j);
				setPosY(i);
			}

			// Methoden f�r die Server Engine
			//Keine setter-Methode fuer die Trank-ID, da diese nicht veraendert werden soll
							 
			//getter-Methode fuer die Trank-ID;
			public int getTrankID(){
				return trankID;
			}
							 
			//setter-Methode, die festlegt, ob der Trank bereits gedroppt wurde
			public static void setGedroppt(boolean neuGedroppt){
				gedroppt = neuGedroppt;
			}
							 
			//getter-Methode, die anzeigt, ob der Trank bereits gedroppt wurde
			public static boolean getGedroppt(){
				return gedroppt;
			}
							 
			//setter-Methode, die den Zustand des Tranks aendert
			public static void setAufgehoben(boolean neuAufgehoben){
				aufgehoben=neuAufgehoben;
			}
					 
			//getter-Methode fuer den Zustand des Tranks
			 public static boolean getAufgehoben(){
				 return aufgehoben;
			 }
					 
			 //setter-Methode fuer die x-Koordinate des Tranks
			 public static void setPosX(int neuPosX){
				 posX=neuPosX;
			 }
			 
			 //getter-Methode fuer die x-Koordinate des Tranks
			 public static int getPosX(){
			  return posX;
			 }
			 
			 //setter-Methode fuer die y-Koordinate des Tranks
			 public static void setPosY(int neuPosY){
			  posY=neuPosY;
			 }
			 
			 //getter-Methode fuer die y-Koordinate des Tranks
			 public static  int getPosY(){
			  return posY;
			 }
		
		//Methoden fuer den Client
		public int getWirkung() {
			return wirkung;
		}
	

}
