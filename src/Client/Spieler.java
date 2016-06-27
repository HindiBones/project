package Client;

//Der Spieler ist eine Unterklasse der Klasse Figur
public class Spieler extends Figur {

	boolean hatSchluessel;
	int anzHeiltraenke;
	
	//Spieler erhält bei der Erstellung eine ID
	public Spieler(int i){
		setID(i);
		setPos(0,0);
		hatSchluessel=false;
		anzHeiltraenke=0;
		setGesundheit(100);
		setMaxGesundheit(200);
	}
	
	//Spieler nimmt den Schlüssel auf
	public void SchluesselAufnehmen(){
		this.hatSchluessel=true;
	}
	
	//Spieler legt den Schlüssel ab
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
	public void HeiltrankAufheben(Heiltrank h){
		anzHeiltraenke++;
	}
	
	//Es wird geprüft, ob der Spieler Heiltränke besitzt
	public boolean hatHeiltraenke(){
		if(anzHeiltraenke>0){
			return true;
		}
		return false;
	}
	
	//Es wird geprüft, ob der Schlüssel in Besitz des Spielers ist
	public boolean SchluesselInBesitz(){
		return hatSchluessel;
	}
	
	//Anzahl der Tränke wird Zurückgegeben
	public int AnzahlTrank(){
		return this.anzHeiltraenke;
	}
}
