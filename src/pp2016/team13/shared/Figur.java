package pp2016.team13.shared;

import java.awt.Image;

public abstract class Figur {

	private int xPos, yPos, id;
	private Image image;

	protected int lp;
	private int schaden;

	private int maxHealth;

	// Getter und Setter

	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	public void setSchaden(int schaden) {
		this.schaden = schaden;
	}

	public int getSchaden() {
		return schaden;
	}

	public void changeHealth(int change) {
		lp = Math.min(lp + change, getMaxHealth());
	}

	public void setLebenspunkte(int lp) {
		this.lp = lp;
	}

	public int getLebenspunkte() {
		return lp;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image img) {
		image = img;
	}

	public void setPos(int xPos, int yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
	}

	public int getYPos() {
		return yPos;
	}

	public int getXPos() {
		return xPos;
	}
	
	public void setYPos(int y) {
		yPos = y;
	}

	public void setXPos(int x) {
		xPos = x;
	}
	
	public void setID(int id){
		this.id = id;
	}
	public int getID(){
		return id;
	}
	public void hoch() {
		yPos--;
	}

	public void runter() {
		yPos++;
	}

	public void links() {
		xPos--;
	}

	public void rechts() {
		xPos++;
	}
}
