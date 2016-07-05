package kommunikation;

import kommunikation.Client;
import kommunikation.Nachricht;

public class ClientMain {
public static Nachricht Test = new Nachricht("Hallo Welt");
	
	public static void main (String []args){
		Client TestenC= new Client("localhost",2345);
		TestenC.SendeAnServer(Test);
	}
}


