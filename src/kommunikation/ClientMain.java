package kommunikation;

import pp2016.team13.client.comm.Client;
import pp2016.team13.client.engine.*;

public class ClientMain {
public static Nachricht Test = new Nachricht("Hallo Welt");
	
	public static void main (String []args){
		Client TestenC= new Client("localhost",2345);
		TestenC.SendeAnServer(Test);
	}
}


