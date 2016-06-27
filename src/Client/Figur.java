package Client;

import java.awt.Image;

//Monster und Spieler sind Figuren
public class Figur {

	//Attribute
	int ID;
	int posx,posy;
	int Gesundheit;
	int MaxGesundheit;
	int Schaden;
	
	//Getter und Setter
	public void setPos(int x,int y){
		this.posx=x;
		this.posy=y;
	}
	
	public int getPosY(){
		return posy;
	}
	
	public int getPosX(){
		return posx;
	}
	
	public int getGesundheit(){
		return Gesundheit;
	}
	
	public void setGesundheit(int g){
		this.Gesundheit=g;
		if(Gesundheit>MaxGesundheit){
			Gesundheit=MaxGesundheit;
		}
	}
	
	public void setMaxGesundheit(int g){
		this.MaxGesundheit=g;
	}
	
	public int getSchaden(){
		return Schaden;
	}
	
	public int getID(){
		return ID;
	}
	
	public void setID(int i){
		this.ID=i;
	}
	
	
	//Bewegung einer Figur
	public void hoch(){
		posy--;
	}
	
	public void runter(){
		posy++;
	}
	
	public void rechts(){
		posx++;
	}
	
	public void links(){
		posx--;
	}
	
}
