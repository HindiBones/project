package clientengine;

//Das Ziel, welches zur Bewltigung des Levels erreicht werden muss
public class Ziel {
	int posx,posy;
	
	public Ziel(int x, int y){
		this.posx=x;
		this.posy=y;
	}
	
	public int getPosY(){
		return this.posy;
	}
	
	public int getPosX(){
		return this.posx;
	}
}
