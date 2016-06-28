package Client;

//Der Spieler ist eine Unterklasse der Klasse Figur
public class Spieler extends Figur {

	boolean hatSchluessel;
	int anzHeiltraenke;
	
	//Spieler erh�lt bei der Erstellung eine ID
	public Spieler(int i){
		setID(i);
		setPos(0,0);
		hatSchluessel=false;
		anzHeiltraenke=0;
		setGesundheit(100);
		setMaxGesundheit(200);
	}
	
	//Spieler nimmt den Schl�ssel auf
	public void SchluesselAufnehmen(){
		this.hatSchluessel=true;
	}
	
	//Spieler legt den Schl�ssel ab
	public void SchluesselEntfernen(){
		this.hatSchluessel=false;
	}
	
	//Falls vorhanden, benutzt der Spieler einen Heiltrank
	public void benutzeHeiltrank(Heiltrank h){
		if(hatHeiltraenke()){
			this.setGesundheit(this.getGesundheit()+h.getMenge());
			anzHeiltraenke--;
		}
	}
	
	//Spieler hebt einen Heiltrank vom Boden auf
	public void HeiltrankAufheben(){
		anzHeiltraenke++;
	}
	
	//Es wird gepr�ft, ob der Spieler Heiltr�nke besitzt
	public boolean hatHeiltraenke(){
		if(anzHeiltraenke>0){
			return true;
		}
		return false;
	}
	
	//Es wird gepr�ft, ob der Schl�ssel in Besitz des Spielers ist
	public boolean SchluesselInBesitz(){
		return hatSchluessel;
	}
	
	//Anzahl der Tr�nke wird Zur�ckgegeben
	public int AnzahlTrank(){
		return this.anzHeiltraenke;
	}
}
