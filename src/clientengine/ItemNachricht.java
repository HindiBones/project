package clientengine;

// Nachricht, die gesendet wird, wenn ein Trank (typ 2) oder der Schluessel (typ 4) aufgenommen wurden
public class ItemNachricht extends Nachricht{
	
	
	
	public ItemNachricht(int a, int b, Schluessel item){
		super(4);
		this.xKoo=a;
		this.yKoo=b;
		this.item = item;
	}
	public ItemNachricht(int a, int b, Heiltrank trank){
		super(2);
		this.xKoo=a;
		this.yKoo=b;
		this.item = trank;
	}

	public int getxKoo(){
		return this.xKoo;
	}
	public int getyKoo(){
		return this.yKoo;
	}
}
