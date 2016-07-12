package pp2016.team13.shared.Spielelemente;
/**
 * 
 * Einen Zweiten Trank erstellt zum schutz 10 sec lang
 * 
 * @author <Keser, Seyma, 5979919>
 *
 */
public class Schutztrank {
		private int wirkung;
		
		//Fuer die Server Engine ; fuer die Trankliste
		 public static int posX;
		 public static int posY;
		 public static boolean aufgehoben;
		 public static boolean gedroppt;
		 public int trankID;

		/**
		 * Schutztrank wird erzeugt
		 * 
		 * @param wirkung: staueke wird uebergeben
		 * @author <Keser, Seyma, 5979919>
		 */
		 public Schutztrank(int wirkung) {
			this.wirkung = wirkung;
		}

		/**
		 * Konstruktor fuer die Server Engine
		 * @param trankID
		 * @param j
		 * @param i : Koord. fuer trank wird gemacht
		 * @author <Keser, Seyma, 5979919>
		 */
			public Schutztrank(int trankID, int j, int i) {
				this.trankID = trankID;
				setGedroppt(false);
				setAufgehoben(false);
				setPosX(j);
				setPosY(i);
			}

			// Methoden f�r die Server Engine
			//Keine setter-Methode fuer die Trank-ID, da diese nicht veraendert werden soll
							 
			/**
			 * getter-Methode fuer die Trank-ID;
			 * 
			 * @return ID des Trankes wird zurueck gegeben
			 * @author <Keser, Seyma, 5979919>
			 * 
			 */
			public int getTrankID(){
				return trankID;
			}
							 
			/**
			 * setter-Methode, die festlegt, ob der Trank bereits gedroppt wurde
			 * 
			 * @param neuGedroppt
			 * @author <Keser, Seyma, 5979919>
			 */
			public static void setGedroppt(boolean neuGedroppt){
				gedroppt = neuGedroppt;
			}
							 
			/**
			 * getter-Methode, die anzeigt, ob der Trank bereits gedroppt wurde
			 * @return gedroppt
			 * @author <Keser, Seyma, 5919919>
			 */
			public static boolean getGedroppt(){
				return gedroppt;
			}
							 
			/**
			 * 
			 * setter-Methode, die den Zustand des Tranks aendert
			 * @param neuAufgehoben
			 * @author <Keser, Seyma, 5979919>
			 */
			public static void setAufgehoben(boolean neuAufgehoben){
				aufgehoben=neuAufgehoben;
			}
					 
			/**
			 * 
			 * getter-Methode fuer den Zustand des Tranks
			 * @return aufeben
			 * @author <Keser, Seyma, 5979919>
			 */
			 public static boolean getAufgehoben(){
				 return aufgehoben;
			 }
					 
			 /**
			  * setter-Methode fuer die x-Koordinate des Tranks
			  * @param neuPosX
			  * @author <Keser, Seyma, 5979919>
			  */
			 public static void setPosX(int neuPosX){
				 posX=neuPosX;
			 }
			 
			 /**
			  * getter-Methode fuer die x-Koordinate des Tranks
			  * @author <Keser, Seyma, 5979919>
			  * 
			  *
			  */
			 public static int getPosX(){
			  return posX;
			 }
			 
			 /**
			  * setter-Methode fuer die y-Koordinate des Tranks
			  * @param neuPosY
			  * @author <Keser, Seyma, 5979919>
			  */
			 public static void setPosY(int neuPosY){
			  posY=neuPosY;
			 }
			 
			 /**
			  * getter-Methode fuer die y-Koordinate des Tranks
			  * @return posY
			  * @author <Keser, Seyma, 5979919>
			  */
			 public static  int getPosY(){
			  return posY;
			 }
		
		/**
		 * Methoden fuer den Client
		 * @return wirkung
		 * @author <Keser, Seyma, 5979919>
		 */
		public int getWirkung() {
			return wirkung;
		}
	

}
