package kommunikation;

import kommunikation.Client; 
import kommunikation.Paket;

public class ClientMain {
public static Paket Test = new Paket("Hallo Welt");
	
	public static void main (String []args){
		Client TestenC= new Client("localhost",2345);
		TestenC.SendeAnServer(Test);
	}
}






