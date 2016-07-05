package pp2016.team13.server.engine;

import java.io.IOException;

public class Monster {
	//Speicherung der monsterbezogenen Daten
	public static int monsterID;
	public static int lebenspunkte;
	public static int monsterSchaden;
	public static boolean trankVorhanden;
	//Speichert die Position des Monsters
	public static int posX;
	public static int posY;
	
	public Monster(int id, int anfangsLebenspunkte, int anfangsSchaden, boolean initialisierungTrankVorhanden, int anfangsX, int anfangsY) throws IOException {
		 monsterID = id;
		 setLebenspunkte(anfangsLebenspunkte);
		 setMonsterSchaden(anfangsSchaden);
		 setTrankVorhanden(initialisierungTrankVorhanden);
		 setPosX(anfangsX);
		 setPosY(anfangsY);
		 
	 }
		
	//Keine setter-Methode für die Monster-ID, da diese nicht verändert werden soll
	
	//getter-Methode für die Monster-ID
	public static int getSpielerID(){
		return monsterID;
	}
		
	//setter-Methode für die Lebenspunkte
	public static void setLebenspunkte(int neueLebenspunkte){
		lebenspunkte = neueLebenspunkte;
	}
		
	//getter-Methode für die Lebenspunkte
	public static int getLebenspunkte(){
			return lebenspunkte;
	}
		
	//setter-Methode für den Schaden, den das Monster verursacht
	public static void setMonsterSchaden (int neumonsterSchaden){
		monsterSchaden = neumonsterSchaden;
	}
		
	//getter-Methode für den Schaden, den das Monster verursacht
	public static int getMonsterSchaden(){
		return monsterSchaden;
	}
		
	//setter-Methode, ob das Monster einen Trank bei sich trägt
	public static void setTrankVorhanden(boolean neuTrankVorhanden){
		trankVorhanden = neuTrankVorhanden;
	}
		
	//getter-Methode für die Abfrage, ob das Monster einen Trank bei sich hat
	public static boolean getTrankVorhanden(){
		return trankVorhanden;
	}
		
	//setter-Methode für die x-Koordinate des Monsters
	public static void setPosX(int neuPosX){
		posX=neuPosX;
	}
		
	//getter-Methode für die x-Koordinate des Monsters
	public static int getPosX(){
		return posX;
	}
		
	//setter-Methode für die y-Koordinate des Monsters
	public static void setPosY(int neuPosY){
			posY=neuPosY;
	}
		
	//getter-Methode für die y-Koordinate des Monsters
	public static  int getPosY(){
		return posY;
	}

}
